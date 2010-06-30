package plaid.runtime;

public interface PlaidMemberDef {

	public String getMemberName();

	public PlaidTag definedIn();

	public boolean isAnonymous();
	
	public boolean isMutable();
	
}
