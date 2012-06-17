package plaid.fastruntime.reference;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidFieldInitializer;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.PlaidLambda$0;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;
import fj.F;
import fj.Ord;
import fj.Ordering;
import fj.data.List;
import fj.data.Set;

public abstract class AbstractObjectValue implements ObjectValue {
	
	protected static final Ord<String> STRING_ORD = Ord.stringOrd;
	protected static final Ord<FieldInfo> FIELD_ORD = Ord.comparableOrd();
	protected static final Ord<SingleValue> SINGLE_VALUE_ORD;
	static {
		F<SingleValue, F<SingleValue, Ordering>> orderSingleValues = new F<SingleValue, F<SingleValue, Ordering>>() {
			@Override
			public F<SingleValue, Ordering> f(SingleValue a) {
				final String thisRep = a.getCanonicalRep();
				return new F<SingleValue, Ordering>() {
					public Ordering f(SingleValue other) {
						return Ord.stringOrd.compare(thisRep, other.getCanonicalRep());
					}
				};
			}
		};
		
		SINGLE_VALUE_ORD = Ord.ord(orderSingleValues);
	}
	
	protected static final List<MethodInfo> NIL_METHOD_INFO = List.nil();
	protected static final List<FieldInfo> NIL_FIELD_INFO = List.nil();
	protected static final List<SingleValue> NIL_SINGLE_VALUE = List.nil();
	protected static final Set<String> EMPTY_TAGS = Set.empty(STRING_ORD);
	
	
	// all of these are caches and should be assigned to exactly once. Unfortunately,
	// they cannot be called from the constructor because of ordering constraints.
	private String canonicalRep; 
	private List<MethodInfo> methods;
	private List<FieldInfo> fields;
	private Set<String> tags;
	private Set<String> outerTags;
	private Set<String> innerTags;
	private Map<String, Integer> storageIndexMap;
	
	private String singleTag = null;
	
	/*
	 * Must be called in last line of construct of every concrete subtype.
	 */
	protected final void init() {
		this.canonicalRep = this.constructCanonicalRep();
		this.methods = this.constructMethods();
		this.fields = this.constructFields();
		this.tags = this.constructTags();
		this.outerTags = this.constructOuterTags();
		this.innerTags = this.constructInnerTags();
		this.storageIndexMap = this.constructStorageIndexMap();
		
		if ( this.tags.size() == 1 ) {
			singleTag = tags.iterator().next().intern();
		}
	}


	@Override
	public final String getCanonicalRep() {
		return this.canonicalRep;
	}
	
	@Override
	public final List<MethodInfo> getMethods() {
		return this.methods;
	}

	@Override
	public final List<FieldInfo> getFields() {
		return this.fields;
	}
	
	protected final Set<String> getTags() {
		return this.tags;
	}
	
	protected final Set<String> getOuterTags() {
		return this.outerTags;
		
	}
	
	protected final Set<String> getInnerTags() {
		return this.innerTags;
	}
	
	/**
	 * This method returns a string that should represent this object value. All ObjectValues that
	 * are logically equivalent  have the same canonical string. All ObjectValues that are 
	 * logically unequal have different canonical strings. Canonical strings can be compared
	 * with ==. 
	 * @see java.lang.String#intern String interning.
	 * @return Value is a canonical instance. The returned String instance should be the result 
	 * of a call to String.intern() method.
	 */
	protected abstract String constructCanonicalRep();
	
	protected abstract List<FieldInfo> constructFields();
	
	protected abstract List<MethodInfo> constructMethods();
	
	protected abstract Set<String> constructTags();
	
	protected abstract Set<String> constructOuterTags();
	
	protected abstract Set<String> constructInnerTags();
	
	private final Map<String, Integer> constructStorageIndexMap() {
		TreeSet<String> storageNameSet = new TreeSet<String>(); 
		for (FieldInfo field : this.getFields()) {
			storageNameSet.add(field.getName());
		}
		for (MethodInfo method : this.getMethods()) {
			if(!method.isStaticallyDefined()) {
				storageNameSet.add(method.getName());
			}
		}
		
		Map<String, Integer> toReturn = new HashMap<String, Integer>();
		
		// TreeSet.iterator() returns an iterator over the elements in ascending order
		int i = 0;
		for(String storageName : storageNameSet) { 
			toReturn.put(storageName, i);
			i++;
		}
		
		return toReturn;
	}

	

	@Override
	public final AbstractObjectValue changeState(ObjectValue other) {
		if(other instanceof ListValue) { 
			// SU-List
			ListValue list = (ListValue)other;
			AbstractObjectValue result = this.changeState(list.getFirst());
			return result.changeState(list.getRest());
		} else if (other instanceof MemberValue) {
			// SU-Mv
			return this.addValue((MemberValue)other);
		} else if (other instanceof DimensionValue) {
			DimensionValue otherDV = (DimensionValue) other;
			// SU-AddH
			if(otherDV.uniqueTags() && 
					this.getTags().intersect(otherDV.getTags()).size() == 0) {
				return this.addValue(otherDV);
			}
			//SU-MatchDim
			else if(this instanceof ListValue) {
				ListValue thisLV = (ListValue) this;
				AbstractObjectValue firstOV = thisLV.getFirst();
				if (firstOV instanceof DimensionValue) {
					DimensionValue thisDV = (DimensionValue)firstOV;
					if (!thisDV.getTags().intersect(otherDV.getOuterTags()).isEmpty() &&
							otherDV.getTags().intersect(thisLV.getRest().getTags()).isEmpty())
					// JSS: This clause is different than the last premise in the SU-MatchDim
						// This operates only on inputs, while the premise operates on the results, which seems unnecessary {
						return thisDV.changeState(otherDV);
				}	
				return thisLV.getRest().changeState(otherDV);
			} else if (this instanceof DimensionValue) {
				DimensionValue thisDV = (DimensionValue) this;	
				// SU-MatchInner
				if(thisDV.getInnerValue()!= null &&
						!thisDV.getInnerValue().getTags().intersect(otherDV.getOuterTags()).isEmpty() &&
					!otherDV.getTags().member(thisDV.getTag()) &&
					(thisDV.getParent() == null ||
							thisDV.getParent().getTags().intersect(otherDV.getTags()).isEmpty())) {
					AbstractObjectValue newInnerValue = thisDV.getInnerValue().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), newInnerValue, thisDV.getParent());
				}
				//SU-MatchSuperInner
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().intersect(thisDV.getParent().getInnerTags()).isEmpty() &&
						thisDV.withoutParent().getTags().intersect(otherDV.getTags()).isEmpty()) {
					DimensionValue newParent = (DimensionValue)thisDV.getParent().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), thisDV.getInnerValue(), newParent);
				}
				//SU-MatchSuperInner
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().intersect(thisDV.getParent().getInnerTags()).isEmpty() &&
						thisDV.withoutParent().getTags().intersect(otherDV.getTags()).isEmpty()) {
					DimensionValue newParent = (DimensionValue)thisDV.getParent().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), thisDV.getInnerValue(), newParent);
				}
				//SU-MatchSuper
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().member(thisDV.getTag()) &&
						!otherDV.getOuterTags().intersect(thisDV.getParent().getOuterTags()).isEmpty()) 
				{
					return thisDV.getParent().changeState(otherDV);
				}
				//SU-Match
				else if(otherDV.getOuterTags().member(thisDV.getTag())) {
					DimensionValue dvsub = childrenOfTag(thisDV.getTag(), otherDV, null);
					if (dvsub == null) {
						return thisDV;
					} else {
						if(dvsub.getTags().intersect(thisDV.getTags()).isEmpty() &&
								dvsub.uniqueTags()) {
							return childrenOfTag(thisDV.getTag(), otherDV, thisDV);
						}
					}
				}
			}
		}
		throw new RuntimeException("Ooops ... State change failed because because of an unknown case.");
	}

	/*
	 * Returns DimensionValue of all children of the argument tag. Used by SU-Match. 
	 * Returns null if the top-level tag of this DimensionValue is equal to the argument. 
	 * Throws an exception if the argument does not appear in the outer-tags of the DimensionValue.
	 * @param tag the tag whose children to return
	 * @param currentValue the dimension value to search
	 * @param newParent new parent to replace this tag, if null will return children without parent
	 */
	private static DimensionValue childrenOfTag(String tag, 
			DimensionValue currentValue, DimensionValue newParent) {
		if(tag.equals(currentValue.getTag())) {
			return newParent;
		} else if (currentValue.getParent() == null) {
			throw new RuntimeException("The argument does not appear in the outer-tags of the DimensionValue");
		} else {
			return new DimensionValue(currentValue.getTag(), 
					currentValue.getInnerValue(), childrenOfTag(tag, currentValue.getParent(), newParent));
		}
	}
	
	@Override
	/*
	 * This method uses a reflective mechanism that is pretty slow. We should consider generating code for this
	 * method. 
	 * @return A new instance every time it is called. The length of the array is the same 
	 * @see plaid.fastruntime.ObjectValue#getDefaultStorage()
	 */
	public PlaidObject[] getDefaultStorage(Map<String,PlaidLambda> dynamicDefinitions){
		PlaidObject[] storage = new PlaidObject[this.getStorageLength()];
		for(FieldInfo field : fields) {
			ClassLoader cl = this.getClass().getClassLoader();
			if(field.isStaticallyDefined()) {
				String className = field.getStaticClassInternalName().replace('/', '.');
				try {
					Class<?> fieldClass = cl.loadClass(className);
					Field myField = fieldClass.getField(field.getName());
					Object value = myField.get(null); // static field so object can be null, see JavaDoc
					PlaidFieldInitializer init = (PlaidFieldInitializer) value;
					storage[this.getStorageIndex(field.getName())] = init.invoke$plaid();
				} catch (ClassNotFoundException e) {
					throw new PlaidInternalException("Could not load field class " + className, e);
				} catch (SecurityException e) {
					throw new PlaidInternalException("Could not load field", e);
				} catch (NoSuchFieldException e) {
					throw new PlaidInternalException("Could not load field", e);
				} catch (IllegalArgumentException e) {
					throw new PlaidInternalException("Could not load field", e);
				} catch (IllegalAccessException e) {
					throw new PlaidInternalException("Could not load field", e);
				}
			} else { //dynamically defined
				try {
					PlaidLambda$0 init = (PlaidLambda$0)dynamicDefinitions.get(field.getName());
					storage[this.getStorageIndex(field.getName())] = init.invoke$plaid();
				} catch (ClassCastException e) {
					throw new plaid.fastruntime.errors.PlaidInternalException("Field initializer must be a 0 argument Lambda.", e);
				}
 			}
		}
		for (MethodInfo method : methods) {
			if (!method.isStaticallyDefined()) {
				String name = method.getName();
				storage[this.getStorageIndex(name)] = dynamicDefinitions.get(name);
			}
		}
		return storage;
	}

	@Override
	final public int getStorageIndex(final String name) {
		Integer index = this.storageIndexMap.get(name);
		if (index == null) {		
			throw new PlaidInternalException("Cannot retrieve storage index because the name " +
				"does not refer to any value.");
		} else {
			return index;
		}
	}
	
	@Override
	public int getStorageLength() {
		return this.storageIndexMap.size();
	}

	
	public final boolean uniqueTags() {
		//TODO: Implement unique tags correctly
		//TODO: Check what is going on here
		return true;
	}
	
	public final boolean matches(String tag) {
		if ( singleTag != null ) {
			return singleTag == tag;
		} else {
			return getTags().member(tag);
		}
	}
	
	public final String getTopTag() {
		if (this instanceof DimensionValue) {
			return ((DimensionValue)this).getTag();
		} else {
			throw new PlaidIllegalOperationException("Asked for top tag of object value without top tag");
		}
	}
	
	@Override
	public final ObjectValue specialize(ObjectValue newMembers) {
		AbstractObjectValue currentValue = this;
		if (newMembers instanceof ListValue) {
			for (SingleValue sv : ((ListValue) newMembers).getAll()) {
				if (sv instanceof DimensionValue) {
					throw new plaid.fastruntime.errors.PlaidInternalException("Cannot specialize with DimensionValue.");
				} else {
					MemberValue mv = (MemberValue)sv;
					currentValue = (AbstractObjectValue)currentValue.remove(mv.getName());
					currentValue = currentValue.add(mv);
				}
			}
		} else if(newMembers instanceof MemberValue) {
			MemberValue mv = (MemberValue)newMembers;
			currentValue = (AbstractObjectValue)currentValue.remove(mv.getName());
			currentValue = currentValue.add(mv);
		} else {
			throw new PlaidInternalException("Cannot specialize with anything exception ListValue or MemberValue");
		}
		return currentValue;
	}
	
	public abstract ListValue addValue(SingleValue other);
	
	public abstract AbstractObjectValue add(MemberValue mv);
	
	@Override
	public final boolean equals(Object other) {
		if (other instanceof AbstractObjectValue) {
			return this.getCanonicalRep() == ((AbstractObjectValue)other).getCanonicalRep(); // == okay because canonical
		} else {
			return false;
		}
		
	}
	
	@Override
	public final int hashCode() {
		return this.getCanonicalRep().hashCode();
	}
	
	@Override
	public final String toString() {
		return this.getCanonicalRep();
	}

}
