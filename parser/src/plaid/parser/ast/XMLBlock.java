package plaid.parser.ast;

import java.util.ArrayList;
import java.util.List;
import plaid.parser.Token;

public class XMLBlock extends ASTNode {

	public static final XMLBlock EMPTY = new XMLBlock(DEFAULT_TOKEN, new ArrayList<XMLElt>());
	
	private List<XMLElt> xmlElts;
	
	public XMLBlock(Token t, List<XMLElt> xmlElts) {
		super(t);
		this.xmlElts = xmlElts;
	}
	
	public List<XMLElt> getXmlElts() {
		return xmlElts;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("xml {");
		
		for(XMLElt e : getXmlElts()) {
			sb.append(e.toString());
		}
		
		sb.append("}");
		return sb.toString();
	}
}
