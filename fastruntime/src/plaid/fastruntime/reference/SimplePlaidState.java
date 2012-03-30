package plaid.fastruntime.reference;

import java.util.Map;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.Util;

public final class SimplePlaidState extends AbstractPlaidState {
	
	private final Map<String, PlaidLambda> memberDefinitions;
	
	private SimplePlaidState(PlaidDispatch dispatch, Map<String, PlaidLambda> memberDefinitions) {
		super(dispatch);
		this.memberDefinitions = memberDefinitions;
	}
	
	public static final PlaidState makeStaticallyDefinedState(PlaidDispatch dispatch) {
		return new SimplePlaidState(dispatch,null);
	}
	
	public static final PlaidState makeDynamicallyDefinedState(PlaidDispatch dispatch, Map<String, PlaidLambda> memberDefinitions) {
		return new SimplePlaidState(dispatch, memberDefinitions);
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
	public PlaidObject[] getStorage() {
		return this.getObjectValue().getDefaultStorage(memberDefinitions);
	}
}
