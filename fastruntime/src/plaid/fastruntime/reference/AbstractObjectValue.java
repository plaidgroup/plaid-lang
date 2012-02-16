package plaid.fastruntime.reference;

import java.lang.reflect.Field;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.PlaidFieldInitializer;
import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;
import fj.Ord;
import fj.data.List;
import fj.data.Set;

public abstract class AbstractObjectValue implements ObjectValue {

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
	
	private List<FieldInfo> sortedFields;
	
	@Override
	/*
	 * This method uses a reflective mechanism that is pretty slow. We should consider generating code for this
	 * method.
	 * Returns a new instance every time it is called.
	 * @see plaid.fastruntime.ObjectValue#getDefaultStorage()
	 */
	public PlaidObject[] getDefaultStorage() {
		List<FieldInfo> sortedFields = this.getSortedFields();
		PlaidObject[] storage = new PlaidObject[sortedFields.length()];
		int i = 0;
		for(FieldInfo field : sortedFields) {
			ClassLoader cl = this.getClass().getClassLoader();
			String className = field.getStaticClassInternalName().replace('/', '.');
			try {
				Class<?> fieldClass = cl.loadClass(className);
				Field myField = fieldClass.getField(field.getName());
				Object value = myField.get(null); // static field so object can be null, see JavaDoc
				PlaidFieldInitializer init = (PlaidFieldInitializer) value;
				storage[i] = init.invoke$plaid();
			} catch (ClassNotFoundException e) {
				throw new PlaidInternalException("Could not load field class", e);
			} catch (SecurityException e) {
				throw new PlaidInternalException("Could not load field", e);
			} catch (NoSuchFieldException e) {
				throw new PlaidInternalException("Could not load field", e);
			} catch (IllegalArgumentException e) {
				throw new PlaidInternalException("Could not load field", e);
			} catch (IllegalAccessException e) {
				throw new PlaidInternalException("Could not load field", e);
			}
			i++;
		}
		return storage;
	}
	
	private List<FieldInfo> getSortedFields() {
		if (this.sortedFields == null) {
			List<FieldInfo> fields = this.getFields();
			Ord<FieldInfo> fieldOrd = Ord.comparableOrd();
			this.sortedFields = fields.sort(fieldOrd);
		} 
		return this.sortedFields;
	}

	@Override
	public int getFieldIndex(final String fieldName) {
		List<FieldInfo> sortedFields = this.getSortedFields();
		int i = 0;
		for(FieldInfo field : sortedFields) {
			if(field.getName().equals(fieldName)) {
				return i;
			}
			i++;
		}
		throw new PlaidInternalException("Cannot retrieve field index because the field name does not appear in the field list.");
	}
	
	public boolean uniqueTags() {
		//TODO: Implement unique tags correctly
		//TODO: Check what is going on here
		return true;
	}
	
	public boolean matches(String tag) {
		return getTags().member(tag);
	}
	
	public String getTopTag() {
		if (this instanceof DimensionValue) {
			return ((DimensionValue)this).getTag();
		} else {
			throw new PlaidIllegalOperationException("Asked for top tag of object value without top tag");
		}
	}
	
	public abstract Set<String> getTags();
	
	public abstract Set<String> getOuterTags();
	
	public abstract Set<String> getInnerTags();
	
	public abstract ListValue addValue(SingleValue other);

}
