package plaid.parser.ast;


public final class EmptyPermission extends Permission {

	private static EmptyPermission singleton = new EmptyPermission();
	private EmptyPermission() {
		super(DEFAULT_TOKEN);
	}
	protected static EmptyPermission getEmptyPermission() {
		return singleton;
	}
	
	public String toString() {
		return "EMPTYPERMISSION";
	}

}
