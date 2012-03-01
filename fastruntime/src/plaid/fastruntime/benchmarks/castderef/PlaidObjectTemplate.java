package plaid.fastruntime.benchmarks.castderef;
public class PlaidObjectTemplate {
	
	public PlaidObjectTemplate(Object dispatch, Object[] fields) {
		this.dispatch = dispatch;
		this.fields = fields;
	}
	
	public Object dispatch;
	public Object[] fields;
}
