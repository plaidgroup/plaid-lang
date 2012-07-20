package plaid.runtime;

import java.util.List;

public interface PlaidTag {
	
	//public PlaidState caseOf();
	
	public PlaidTag superTag();
	
	public boolean hasSuperTag();
	
	public boolean matches(PlaidTag tag);
	
	public String getName();
	
	public PlaidTag rootTag();
	
	public List<PlaidTag> getHierarchy();

	public String getPath();
	
	public void nest(PlaidState state);
}
