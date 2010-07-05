package plaid.runtime;

public interface PlaidMemberDef {

	public String getMemberName();

	public String definedIn();

	public boolean isAnonymous();
	
	public boolean isMutable();
	
}
