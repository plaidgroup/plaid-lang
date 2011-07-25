package plaid.parser.ast;

public interface Type extends ASTNode{
	public static final Type DYN = new ObjectType(new ID("dyn"));
	public static final Type UNIT = new ObjectType(new ID("unit"));
	
	
}
