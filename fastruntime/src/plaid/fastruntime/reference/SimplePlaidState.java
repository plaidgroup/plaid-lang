package plaid.fastruntime.reference;

import java.util.Map;

import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;

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
	public final PlaidObject instantiate() {
		return new SimplePlaidObject(this.getDispatch(), this.getObjectValue().getDefaultStorage(memberDefinitions)); 
	}
	
	public boolean isStatic() {
		return memberDefinitions == null;
	}
	
	public Map<String, PlaidLambda> getMemberDefinitions() {
		return this.memberDefinitions;
	}
}
