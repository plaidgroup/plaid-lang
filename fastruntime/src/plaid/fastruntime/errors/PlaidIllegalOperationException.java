package plaid.fastruntime.errors;

public class PlaidIllegalOperationException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3554337185625935052L;


	public PlaidIllegalOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlaidIllegalOperationException(String message) {
		super(message);
	}
}
