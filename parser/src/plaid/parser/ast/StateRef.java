package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class StateRef extends StatePrim {
	private final Expr stateExpr;
	private final List<Expr> staticArgs;
	private final List<DeclOrStateOp> specializations;
	
	public StateRef(Token t, Expr st,
			List<Expr> staticArgs,
			List<DeclOrStateOp> specializations) {
		super(t);
		this.stateExpr = st;
		this.staticArgs = staticArgs;
		this.specializations = specializations;
	}

	public Expr getStateExpr() {
		return stateExpr;
	}

	public List<DeclOrStateOp> getSpecializations() {
		return specializations;
	}
	
	public List<Expr> getStaticArgs() {
		return staticArgs;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(stateExpr.toString());
		if ( specializations.size() > 0 ) {
			sb.append("{" + specializations.get(0));
			for(int i = 1; i<specializations.size(); i++) {
				sb.append(specializations.get(i));
			}
			sb.append("}");
		}
		return sb.toString();
	}
	
}
