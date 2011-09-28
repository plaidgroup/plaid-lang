package plaid.fastruntime;

public interface PlaidObject {
	
	public PlaidState getDispatch();
	
	public PlaidStorage getStorage();
	
	public void changeState(PlaidState s);
}
