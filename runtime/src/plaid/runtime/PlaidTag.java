package plaid.runtime;

public interface PlaidTag {
	
	public PlaidState caseOf();
	
	public PlaidTag superTag();
	
	public boolean hasSuperTag();
}
