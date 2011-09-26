package plaid.fastruntime;

public abstract class PlaidObject {
	
	public PlaidState dispatch;
	
	public PlaidObject[] storage;
	
	public abstract PlaidState freeze();
	
	public void changeState(PlaidState s) {
		this.dispatch = this.dispatch.change(s);
	}
	
	public void replaceState(PlaidState s) {
		this.dispatch = s;
	}
}
