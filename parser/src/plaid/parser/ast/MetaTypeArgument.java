package plaid.parser.ast;


public class MetaTypeArgument implements MetaArgument {
	private final PermType permType;
	
	public MetaTypeArgument(PermType permType) {
		this.permType = permType;
	}
	
	public PermType getPermType() {
		return permType;
	}
}
