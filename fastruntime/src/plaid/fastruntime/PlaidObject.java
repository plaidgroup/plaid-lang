package plaid.fastruntime;

public interface PlaidObject {
	
	public PlaidDispatch getDispatch();
	
	public PlaidObject[] getStorage();
	
	public void changeState(PlaidState s);
}
