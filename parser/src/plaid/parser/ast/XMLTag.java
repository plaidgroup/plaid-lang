package plaid.parser.ast;

import java.util.List;
import plaid.parser.Token;

public class XMLTag extends XMLElt {

	private Identifier tag;
	private List<XMLAttr> attrs;
	private List<XMLElt> body;
	
	public XMLTag(Token t, Identifier tag, List<XMLAttr> attrs, List<XMLElt> body) {
		super(t);
		this.tag = tag;
		this.attrs = attrs;
		this.body = body;
	}
	
	public Identifier getTag() {
		return tag;
	}
	
	public List<XMLAttr> getAttrs() {
		return attrs;
	}
	
	public List<XMLElt> getBody() {
		return body;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(getTag().toString());
		for(XMLAttr a : getAttrs()) {
			sb.append(" ");
			sb.append(a.toString());
		}
		sb.append("]");
		for(XMLElt e : getBody()) {
			sb.append(e.toString());
		}
		sb.append("[/");
		sb.append(getTag().toString());
		sb.append("]");
		
		return sb.toString();
	}
}
