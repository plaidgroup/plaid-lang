package plaid.fastruntime.benchmarks.castderef;
public class PlaidObject {
	
	public PlaidObject(Object dispatch, Object[] fields) {
		this.dispatch = dispatch;
		this.fields = fields;
	}
	
	public Object dispatch;
	public Object[] fields;
}
