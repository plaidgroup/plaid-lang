package plaid.fastruntime.reference;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.errors.PlaidInternalException;

public class ObjectValueFactory {
	private AbstractObjectValue currentValue = null;
	
	public ObjectValueFactory() {
	}
	
	
	public void parent(ObjectValue ov) {
		if(ov instanceof DimensionValue) {
			if(currentValue instanceof DimensionValue) {
				DimensionValue dvCurrent = (DimensionValue) this.currentValue;
				DimensionValue dvParent = (DimensionValue) ov;
				this.currentValue = new DimensionValue(dvCurrent.getTag(), dvCurrent.getInnerValue(), dvParent);
			} else {
				throw new PlaidInternalException("Cannot add a parent to non-dimension value.");
			}
		} else {
			throw new PlaidInternalException("Cannot add parent object that was not a dimension value.");
		}
	}
	
	public void tag(String tag) {
		if (this.currentValue == null) {
			this.currentValue = new DimensionValue(tag, null, null);
		} else {
			throw new PlaidInternalException("Tag must be the first method called on the object value factory.");
		}
	}
	
	/**
	 * Name is strangely spelled to avoid collision with plaid keyword.
	 */
	public void mthd(String name, int numArgs, String fullyQualifiedClassName) {
		String internalClassName = fullyQualifiedClassName.replace('.', '/');
		MethodValue toAdd = MethodValue.createMethodWithStaticDefinition(name, numArgs, internalClassName);
		member(toAdd);
	}
	
	public void dynMthd(String name, int numArgs) {
		MethodValue toAdd = MethodValue.createMethodWithDynamicDefinition(name, numArgs);
		member(toAdd);
	}

	public void field(boolean settable, String name, String fullyQualifiedClassName) {
		String internalClassName = fullyQualifiedClassName.replace('.', '/');
		FieldValue toAdd = FieldValue.createFieldWithStaticDefinition(name, settable, internalClassName);
		member(toAdd);
	}
	
	public void dynField(boolean settable, String name) {	
		FieldValue toAdd = FieldValue.createFieldWithDynamicDefinition(name,settable);
		member(toAdd);
	}
	
	private void member(MemberValue toAdd) {	
		if (this.currentValue instanceof DimensionValue) {
			DimensionValue currentDV = (DimensionValue) this.currentValue;
			AbstractObjectValue newInnerValue;
			if (currentDV.getInnerValue() == null) {
				newInnerValue = toAdd;
			} else if (currentDV.getInnerValue() instanceof SingleValue) {
				newInnerValue = new ListValue((SingleValue)currentDV.getInnerValue(), toAdd);
			} else { // innerValue is a ListValue
				newInnerValue = ((ListValue)currentDV.getInnerValue()).addValue(toAdd);
			}
			this.currentValue = new DimensionValue(currentDV.getTag(), newInnerValue, currentDV.getParent());
		} else if (this.currentValue instanceof MemberValue) {
			this.currentValue = new ListValue((MemberValue)this.currentValue, toAdd);
		} else if (this.currentValue instanceof ListValue) {
			this.currentValue = ((ListValue)this.currentValue).addValue(toAdd);
		} else if(this.currentValue == null) {
			this.currentValue = toAdd;
		} else {
			throw new PlaidInternalException("Current value stored in the object value factory has unexpected type. " +
					"It's type is " + this.currentValue.getClass().getCanonicalName());
		}
	}
	
	
	public ObjectValue value() {
		// QUESTION: should we kill the object factory after returning this current value to ensure
		// its methods aren't accidentally called after??
		return this.currentValue;
	}
}
