package plaid.fastruntime;

public interface PlaidObject {
	
	public PlaidDispatch getDispatch();
	
	public PlaidObject[] getStorage();
	
	public Object[] getMemberDefs();
	
	public void changeState(PlaidState s);
}
