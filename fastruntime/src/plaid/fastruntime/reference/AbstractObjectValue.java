package plaid.fastruntime.reference;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidFieldInitializer;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.PlaidLambda$0;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;


public abstract class AbstractObjectValue implements ObjectValue {
	
	protected static final UnmodifiableList<MethodInfo> NIL_METHOD_INFO = UnmodifiableList.<MethodInfo>makeEmpty();
	protected static final UnmodifiableList<FieldInfo> NIL_FIELD_INFO = UnmodifiableList.<FieldInfo>makeEmpty();
	protected static final UnmodifiableList<SingleValue> NIL_SINGLE_VALUE = UnmodifiableList.<SingleValue>makeEmpty();
	protected static final TagSet EMPTY_TAGS = TagSet.makeEmpty();
	
	/*
	 * This will not work if we use a custom class loader
	 */
	private static final ClassLoader CL = AbstractObjectValue.class.getClassLoader();
	
	private static final HashMap<String, PlaidFieldInitializer> fieldInitializerCache = 
		new HashMap<String, PlaidFieldInitializer>();
	
	// all of these are caches and should be assigned to exactly once. Unfortunately,
	// they cannot be called from the constructor because of ordering constraints.
	private String canonicalRep; 
	private UnmodifiableList<MethodInfo> methods;
	private UnmodifiableList<FieldInfo> fields;
	private TagSet tags;
	private TagSet outerTags;
	private TagSet innerTags;
	private Map<String, Integer> storageIndexMap;
	
	//This cache is mutable after object value initialization.
	private Map<ObjectValue, AbstractObjectValue> stateChangeCache = new HashMap<ObjectValue, AbstractObjectValue>();
	
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
	}


	@Override
	public final String getCanonicalRep() {
		return this.canonicalRep;
	}
	
	@Override
	public final UnmodifiableList<MethodInfo> getMethods() {
		return this.methods;
	}

	@Override
	public final UnmodifiableList<FieldInfo> getFields() {
		return this.fields;
	}
	
	protected final TagSet getTags() {
		return this.tags;
	}
	
	protected final TagSet getOuterTags() {
		return this.outerTags;
		
	}
	
	protected final TagSet getInnerTags() {
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
	
	protected abstract UnmodifiableList<FieldInfo> constructFields();
	
	protected abstract UnmodifiableList<MethodInfo> constructMethods();
	
	protected abstract TagSet constructTags();
	
	protected abstract TagSet constructOuterTags();
	
	protected abstract TagSet constructInnerTags();
	
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
		if (stateChangeCache.containsKey(other)) {
			return stateChangeCache.get(other);
		} else {
			AbstractObjectValue output = changeStateInternal(other);
			stateChangeCache.put(other, output);
			return output;
		}
	}
		 
	public final AbstractObjectValue changeStateInternal(ObjectValue other) {

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
					this.getTags().emptyIntersection(otherDV.getTags())) {
				return this.addValue(otherDV);
			}
			//SU-MatchDim
			else if(this instanceof ListValue) {
				ListValue thisLV = (ListValue) this;
				SingleValue firstOV = thisLV.getFirst();
				if (firstOV instanceof DimensionValue) {
					DimensionValue thisDV = (DimensionValue)firstOV;
					if (!thisDV.getTags().emptyIntersection(otherDV.getOuterTags()) &&
							otherDV.getTags().emptyIntersection(thisLV.getRest().getTags())) {
						// JSS: This clause is different than the last premise in the SU-MatchDim
						// This operates only on inputs, while the premise operates on the results, which seems unnecessary 
						return thisLV.getRest().addValue((SingleValue)firstOV.changeState(otherDV));
					}
				} 
				return thisLV.getRest().changeState(otherDV).addValue(firstOV);
				
			} else if (this instanceof DimensionValue) {
				DimensionValue thisDV = (DimensionValue) this;	
				// SU-MatchInner
				if(thisDV.getInnerValue()!= null &&
						!thisDV.getInnerValue().getTags().emptyIntersection(otherDV.getOuterTags()) &&
					!otherDV.getTags().member(thisDV.getTag()) &&
					(thisDV.getParent() == null ||
							thisDV.getParent().getTags().emptyIntersection(otherDV.getTags()))) {
					AbstractObjectValue newInnerValue = thisDV.getInnerValue().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), newInnerValue, thisDV.getParent());
				}
				//SU-MatchSuperInner
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().emptyIntersection(thisDV.getParent().getInnerTags()) &&
						thisDV.withoutParent().getTags().emptyIntersection(otherDV.getTags())) {
					DimensionValue newParent = (DimensionValue)thisDV.getParent().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), thisDV.getInnerValue(), newParent);
				}
				//SU-MatchSuperInner
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().emptyIntersection(thisDV.getParent().getInnerTags()) &&
						thisDV.withoutParent().getTags().emptyIntersection(otherDV.getTags())) {
					DimensionValue newParent = (DimensionValue)thisDV.getParent().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), thisDV.getInnerValue(), newParent);
				}
				//SU-MatchSuper
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().member(thisDV.getTag()) &&
						!otherDV.getOuterTags().emptyIntersection(thisDV.getParent().getOuterTags())) 
				{
					return thisDV.getParent().changeState(otherDV);
				}
				//SU-Match
				else if(otherDV.getOuterTags().member(thisDV.getTag())) {
					DimensionValue dvsub = childrenOfTag(thisDV.getTag(), otherDV, null);
					if (dvsub == null) {
						return thisDV;
					} else {
						if(dvsub.getTags().emptyIntersection(thisDV.getTags()) &&
								dvsub.uniqueTags()) {
							return childrenOfTag(thisDV.getTag(), otherDV, thisDV);
						}
					}
				}
			}
		}
		throw new PlaidInternalException("Ooops ... State change failed because because of an unknown case.");
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
	public PlaidObject[] getPostChangeStorage(ObjectValue oldObjectValue, PlaidObject[] oldStorage) {
		PlaidObject[] storage = new PlaidObject[this.getStorageLength()];
		for(FieldInfo field : fields) {
			boolean isOld = false;
			for(FieldInfo oldField : oldObjectValue.getFields()) {
				if (oldField.getName().equals(field.getName())) {
					storage[this.getStorageIndex(field.getName())] = oldStorage[oldObjectValue.getStorageIndex(field.getName())];
					isOld = true;
					break;
				}
			}
			if (!isOld) {
				if(field.isStaticallyDefined()) {
					initStaticField(storage,field);

				} else {
					throw new PlaidInternalException("Post state change storage cannot have any dynamic definitions");
	 			}
			}
		}
		for (MethodInfo method : methods) {
			boolean isOld = false;
			for(MethodInfo oldMethod : oldObjectValue.getMethods()) {
				if (oldMethod.getName().equals(method.getName()) && !method.isStaticallyDefined()) {
					storage[this.getStorageIndex(method.getName())] = oldStorage[oldObjectValue.getStorageIndex(method.getName())];
					isOld = true;
					break;
				}
			}
			if (!isOld && !method.isStaticallyDefined()) {
//				String name = method.getName();
//				storage[this.getStorageIndex(name)] = dynamicDefinitions.get(name);
				throw new PlaidInternalException("Post state change storage cannot have any dynamic definitions");
			}
		}
		return storage;
	}


	private void initStaticField(PlaidObject[] storage,
			FieldInfo field) {
		String className = NamingConventions.getGeneratedFQN(
				field.getStaticClassInternalName().replace('/', '.')
				);
		String key = className + "$" + field.getName();
		if(fieldInitializerCache.containsKey(key)) {
			PlaidFieldInitializer init = fieldInitializerCache.get(key);
			storage[this.getStorageIndex(field.getName())] = init.invoke$plaid();
		} else {
			try {
				Class<?> fieldClass = CL.loadClass(className);
				Field myField = fieldClass.getField(field.getName());
				Object value = myField.get(null); // static field so object can be null, see JavaDoc
				PlaidFieldInitializer init = (PlaidFieldInitializer) value;
				fieldInitializerCache.put(key, init);
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
			if(field.isStaticallyDefined()) {
				initStaticField(storage,field);
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
		return getTags().member(tag);
	}
	
	public final String getTopTag() {
		if (this instanceof DimensionValue) {
			return ((DimensionValue)this).getTag();
		} else {
			throw new PlaidIllegalOperationException("Asked for top tag of object value without top tag");
		}
	}
	
	public abstract ListValue addValue(SingleValue other);
	
	public abstract AbstractObjectValue add(MemberValue mv);
	
	@Override
	public final boolean equals(Object other) {
		if (other instanceof AbstractObjectValue) {
			return this.getCanonicalRep().equals(((AbstractObjectValue)other).getCanonicalRep());
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
	
	@Override
	public ObjectValue addParent(ObjectValue ov) {
		if(ov instanceof DimensionValue) {
			if(this instanceof DimensionValue) {
				DimensionValue dvCurrent = (DimensionValue) this;
				DimensionValue dvParent = (DimensionValue) ov;
				return new DimensionValue(dvCurrent.getTag(), dvCurrent.getInnerValue(), dvParent);
			} else {
				throw new PlaidInternalException("Cannot add a parent to non-dimension value.");
			}
		} else {
			throw new PlaidInternalException("Cannot add parent object that was not a dimension value.");
		}
	}
	
	@Override
	public ObjectValue addTag(String tag) {
		if (this instanceof DimensionValue) {
			DimensionValue thisDV = (DimensionValue) this;
			if (thisDV.getTag() == null) {
				return new DimensionValue(tag, thisDV.getInnerValue(), thisDV.getParent());
			}
			else {
				throw new PlaidInternalException("Cannot add a tag to a DimensionValue that already has a tag.");
			}
		} else {
			//convert into a dimension value with no parent and this current object value as the innerValue
			return new DimensionValue(tag, this, null);
		}
	}
	
	/**
	 * Name is strangely spelled to avoid collision with plaid keyword.
	 */
	@Override
	public ObjectValue addMethod(String name, int numArgs, String fullyQualifiedClassName) {
		String internalClassName = fullyQualifiedClassName.replace('.', '/');
		MethodValue toAdd = MethodValue.createMethodWithStaticDefinition(name, numArgs, internalClassName);
		return member(toAdd);
	}
	
	@Override
	public ObjectValue addDynMethod(String name, int numArgs) {
		MethodValue toAdd = MethodValue.createMethodWithDynamicDefinition(name, numArgs);
		return member(toAdd);
	}

	@Override
	public ObjectValue addField(boolean settable, String name, String fullyQualifiedClassName) {
		String internalClassName = fullyQualifiedClassName.replace('.', '/');
		FieldValue toAdd = FieldValue.createFieldWithStaticDefinition(name, settable, internalClassName);
		return member(toAdd);
	}
	
	@Override
	public ObjectValue addDynField(boolean settable, String name) {	
		FieldValue toAdd = FieldValue.createFieldWithDynamicDefinition(name,settable);
		return member(toAdd);
	}
	
	private ObjectValue member(MemberValue toAdd) {	
		return this.add(toAdd);
		
		
//		if (this instanceof DimensionValue) {
//			DimensionValue currentDV = (DimensionValue) this;
//			AbstractObjectValue newInnerValue;
//			if (currentDV.getInnerValue() == null) {
//				newInnerValue = toAdd;
//			} else if (currentDV.getInnerValue() instanceof SingleValue) {
//				newInnerValue = new ListValue((SingleValue)currentDV.getInnerValue(), toAdd);
//			} else { // innerValue is a ListValue
//				newInnerValue = ((ListValue)currentDV.getInnerValue()).addValue(toAdd);
//			}
//			return new DimensionValue(currentDV.getTag(), newInnerValue, currentDV.getParent());
//		} else if (this instanceof MemberValue) {
//			return new ListValue((MemberValue)this, toAdd);
//		} else if (this instanceof ListValue) {
//			return ((ListValue)this).addValue(toAdd);
//		} else if (this instanceof EmptySingleValue) {
//			return toAdd;
//		} else if(this == null) {
//			return toAdd;
//		} else {
//			throw new PlaidInternalException("Current value stored in the object value factory has unexpected type. " +
//					"It's type is " + this.getClass().getCanonicalName());
//		}
	}
	
	@Override
	public ObjectValue with(ObjectValue other) {
		if(this instanceof ListValue) {
			if(other instanceof ListValue) {
				//merge lists
				return ((ListValue)this).addListValue((ListValue)other);
			} else {
				// other is a single value
				return ((ListValue)this).addValue((SingleValue)other);
			}
		} else {
			// this is a single value
			if(other instanceof ListValue) {
				//add this value to other list
				return ((ListValue)other).addValue((SingleValue)this);
			} else {
				// other is a single value
				return new ListValue((SingleValue) this, (SingleValue) other);
			}
		}
	}

}
