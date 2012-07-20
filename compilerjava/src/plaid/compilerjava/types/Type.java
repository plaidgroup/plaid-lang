package plaid.compilerjava.types;

import plaid.compilerjava.AST.ID;

public interface Type {
	public static final Type DYN = new ObjectType(new ID("dyn"));
	public static final Type UNIT = new ObjectType(new ID("unit"));
	
	
}
