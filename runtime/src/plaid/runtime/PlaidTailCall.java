package plaid.runtime;

public interface PlaidTailCall extends PlaidObject {
	public PlaidMethod getMethod();
	
	public PlaidObject getArgs();
}
