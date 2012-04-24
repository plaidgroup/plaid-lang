package plaid.fastruntime.reference;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public class SimplePlaidJavaObject extends SimplePlaidObject implements PlaidJavaObject {

	protected Object javaObject;

	public SimplePlaidJavaObject(PlaidDispatch dispatch, PlaidObject[] storage, Object javaObject) {
		super(dispatch, storage);
		this.javaObject = javaObject;
	}

	@Override
	public Object getJavaObject() {
		return javaObject;
	}
	
	@Override
	public void changeState(PlaidState s) {
		throw new PlaidIllegalOperationException("Cannot change the state of a Java Object.");
	}
	
	@Override
	public boolean canBePrimitive(JavaPrimitive p) { return false; }
	
	@Override
	public Object asPrimitive(JavaPrimitive p) {
		throw new PlaidIllegalOperationException("Class" + javaObject.getClass().getSimpleName() + " cannot be used as a " + p.name + " primitive.");
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof PlaidJavaObject) {
			PlaidJavaObject otherObject = (PlaidJavaObject)other;
			return this.getJavaObject().equals(otherObject.getJavaObject());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.javaObject.hashCode();
	}
}
