package plaid.runtime;

public interface PlaidTag {
		
	public PlaidTag superTag();
	
	public boolean hasSuperTag();
	
	public boolean matches(String tag);
	
	public String getName();
}
