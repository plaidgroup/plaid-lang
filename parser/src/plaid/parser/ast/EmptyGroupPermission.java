package plaid.parser.ast;

public final class EmptyGroupPermission extends GroupPermission {

	/*package-private*/ 
	final static EmptyGroupPermission singleton = new EmptyGroupPermission();
	
	private EmptyGroupPermission() {
		super(DEFAULT_TOKEN);
	}
	
	@Override
	public String toString() {
		return "empty";
	}

}
