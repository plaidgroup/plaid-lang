package plaid.parser.ast;

import plaid.parser.Token;

public class XMLEmbeddedPlaid extends XMLElt {

	private XMLEmbeddable content;
	
	public XMLEmbeddedPlaid(Token t, XMLEmbeddable n) {
		super(t);
		
		content = n;
	}
	
	public XMLEmbeddable getContent() {
		return content;
	}
	
	public String toString() {
		return "{ " + getContent().toString() + " }";
	}
}
