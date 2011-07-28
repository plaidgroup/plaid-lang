package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class StateRef extends StatePrim {
	private final Expression st;
	private final List<DeclOrStateOp> specializations;
	
	public StateRef(Token t, Expression st,
			List<DeclOrStateOp> specializations) {
		super(t);
		this.st = st;
		this.specializations = specializations;
	}

	public Expression getSt() {
		return st;
	}

	public List<DeclOrStateOp> getSpecializations() {
		return specializations;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(st.toString());
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
