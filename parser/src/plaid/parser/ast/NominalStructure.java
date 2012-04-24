package plaid.parser.ast;


import java.util.List;

import plaid.parser.Token;

public class NominalStructure extends Structure {

	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final QualifiedIdentifier stateQi;
	private final List<Expr> staticArgs;
	
	public NominalStructure(Token t,QualifiedIdentifier st, List<Expr> staticArgs) {
		super(t);
		this.stateQi = st;
		this.staticArgs = staticArgs;
	}
	
	public QualifiedIdentifier getStateQi() {
		return stateQi;
	}
	
	public List<Expr> getStaticArgs() {
		return staticArgs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(stateQi.toString());
		if ( staticArgs.size() > 0 ) {
			sb.append("<");
			sb.append(staticArgs.toString());
			for (int i = 1; i < staticArgs.size(); i++) {
				sb.append(","+staticArgs.toString());
			}
			sb.append(">");
		}
		return sb.toString();
	}
}
