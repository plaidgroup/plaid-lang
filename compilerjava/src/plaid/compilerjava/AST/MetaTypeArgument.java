package plaid.compilerjava.AST;

import plaid.compilerjava.types.PermType;


public class MetaTypeArgument implements MetaArgument {
	private final PermType permType;
	
	public MetaTypeArgument(PermType permType) {
		this.permType = permType;
	}
	
	public PermType getPermType() {
		return permType;
	}
}
