package plaid.fastruntime;

import java.util.Map;


public interface PlaidState extends PlaidObject {
	
	public abstract PlaidState with(PlaidState other);
	
	public abstract PlaidObject instantiate();
	
	public abstract PlaidState remove(String member);
	
	public abstract PlaidState rename(String from, String to);
	
	public abstract String getTopTag();
	
	public abstract PlaidDispatch getDispatch();
	
	public abstract ObjectValue getObjectValue();
	
	public boolean isStatic();
	
	/**
	 * @return Always returns null if isStatic is true.
	 */
	public Map<String, PlaidLambda> getMemberDefinitions();
}
