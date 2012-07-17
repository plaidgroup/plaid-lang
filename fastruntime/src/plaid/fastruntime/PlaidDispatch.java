package plaid.fastruntime;

/**
 * A PlaidState is functional. All methods return new objects.  
 */
public interface PlaidDispatch {

	public abstract PlaidState change(PlaidState s);
	
	public abstract boolean matches(String tag);
	
	public abstract ObjectValue getObjectValue();
}
