package plaid.fastruntime;


public interface PlaidState extends PlaidObject {
	
	public abstract PlaidState with(PlaidState other);
	
	public abstract PlaidObject instantiate();
	
	public abstract PlaidState remove(String member);
	
	public abstract PlaidState rename(String from, String to);
	
	public abstract PlaidState specialize(PlaidState other);
	
	public abstract String getTopTag();
	
	public abstract PlaidDispatch getDispatch();
	
	public abstract ObjectValue getObjectValue();
}
