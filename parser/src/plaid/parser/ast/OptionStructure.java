package plaid.parser.ast;

import plaid.parser.Token;

public class OptionStructure extends Structure {

	private final NominalStructure optStructure;
	
	public OptionStructure(Token t, NominalStructure s) {
		super(t);
		this.optStructure = s;
	}
	
	public NominalStructure getOptStructure() {
		return this.optStructure;
	}

	@Override
	public String toString() {
		return "Option(" + this.optStructure.toString() + ")";
	}
	
	
}
