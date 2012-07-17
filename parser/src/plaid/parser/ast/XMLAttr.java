package plaid.parser.ast;

import plaid.parser.Token;

public class XMLAttr extends ASTNode {
	
	private Identifier name;
	private XMLEmbeddedPlaid value;
	
	public XMLAttr(Token t, Identifier name, XMLEmbeddedPlaid val) {
		super(t);
		this.name = name;
		this.value = val;
	}
	
	public Identifier getName() {
		return name;
	}
	
	public XMLEmbeddedPlaid getValue() {
		return value;
	}
	
	public String toString() {
		return getName().toString() + "=" + getValue().toString();
	}
}
