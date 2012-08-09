package plaid.fastruntime.reference;

import java.util.HashMap;
import java.util.Map;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public abstract class AbstractPlaidState implements PlaidState {

	private final PlaidDispatch dispatch;
	
	public AbstractPlaidState(PlaidDispatch dispatch) {
		this.dispatch = dispatch;
	}
	
	@Override
	public PlaidState with(PlaidState other) {
		if(this.getObjectValue() instanceof ListValue) {
			if(other.getObjectValue() instanceof ListValue) {
				//merge lists
				ObjectValue newMetadata = ((ListValue)this.getObjectValue()).addListValue((ListValue)other.getObjectValue());
				return SimplePlaidState.makeStaticallyDefinedState(Util.DISPATCH_GEN.createStateInstance(newMetadata));
			} else {
				// other is a single value
				ObjectValue newMetadata = ((ListValue)this.getObjectValue()).addValue((SingleValue)other.getObjectValue());
				return SimplePlaidState.makeStaticallyDefinedState(Util.DISPATCH_GEN.createStateInstance(newMetadata));
			}
		} else {
			// this is a single value
			if(other.getObjectValue() instanceof ListValue) {
				//add this value to other list
				ObjectValue newMetadata = ((ListValue)other.getObjectValue()).addValue((SingleValue)this.getObjectValue());
				return SimplePlaidState.makeStaticallyDefinedState(Util.DISPATCH_GEN.createStateInstance(newMetadata));
			} else {
				// other is a single value
				ObjectValue newMetadata = new ListValue((SingleValue) this.getObjectValue(), (SingleValue) other.getObjectValue());
				return SimplePlaidState.makeStaticallyDefinedState(Util.DISPATCH_GEN.createStateInstance(newMetadata));
			}
		}
	}
	
	@Override
	public PlaidObject instantiate() {
		return new SimplePlaidObject(this.getDispatch(), this.getObjectValue().getDefaultStorage(new HashMap<String,PlaidLambda>())); 
	}

	@Override
	public final PlaidObject[] getStorage() {
		throw new PlaidIllegalOperationException("Tried to get Storage on PlaidState." +
				"States do not have storage."); 
	}

	@Override
	public final ObjectValue getObjectValue() {
		return this.dispatch.getObjectValue();
	}
	
	@Override
	public String getTopTag() {
		return getObjectValue().getTopTag();
	}
	
	@Override
	public final PlaidState remove(String member) {
		ObjectValue newOV = this.getObjectValue().remove(member);
		return SimplePlaidState.makeStaticallyDefinedState(Util.DISPATCH_GEN.createStateInstance(newOV));
	}

	@Override
	public final PlaidState rename(String from, String to) {
		ObjectValue newOV = this.getObjectValue().rename(from, to);
		return SimplePlaidState.makeStaticallyDefinedState(Util.DISPATCH_GEN.createStateInstance(newOV));
	}
	
	@Override
	public final PlaidDispatch getDispatch() {
		return this.dispatch;
	}
	
	@Override
	public final void changeState(PlaidState s) {
		throw new PlaidIllegalOperationException("Tried to change state on PlaidState." +
				"States cannot change .");
	}

	@Override
	public String toString() {
		return "ObjectValue : " + this.getObjectValue().getCanonicalRep();
	}
	
	public boolean isStatic() {
		return true;
	}
	
	public Map<String, PlaidLambda> getMemberDefinitions() {
		return null;
	}

}
