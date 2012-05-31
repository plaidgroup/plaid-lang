package plaid.fastruntime.errors;

public class PlaidIllegalArgumentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6933004735284543350L;

	public PlaidIllegalArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlaidIllegalArgumentException(String message) {
		super(message);
	}
	
}
