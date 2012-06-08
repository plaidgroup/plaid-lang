package plaid.fastruntime.errors;

/**
 * Used for unexpected failures in the runtime that are not the result of errors in the Plaid program. 
 */
public class PlaidInternalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9159987244931588288L;

	
	public PlaidInternalException(String message) {
		super(message);
	}
	
	public PlaidInternalException(String message, Throwable cause) {
		super(message, cause);
	}
}
