package plaid.runtime;

public interface PlaidMemberDef {

	public String getMemberName();

	public String definedIn();

	public boolean isAnonymous();
	
	public boolean isMutable();
	
	public boolean overrides();
	
	public boolean overrideIsBound();
	
	public void bindOverride(String overridenDef) throws PlaidRuntimeException;
	
	public String overridenDef();

	public PlaidObject getValue();
	
	public void setValue(PlaidObject value);
	
	public PlaidMemberDef copy();
}
