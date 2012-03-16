package plaid.fastruntime;

public interface PlaidObject {
	
	public PlaidState getDispatch();
	
	public PlaidObject[] getStorage();
	
	public void changeState(PlaidState s);
}
