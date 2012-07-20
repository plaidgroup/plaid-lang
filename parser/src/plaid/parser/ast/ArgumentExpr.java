package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ArgumentExpr extends Expr {
	protected final List<Expr> args;

	public ArgumentExpr(Token token, List<Expr> args) {
		super(token);
		this.args = args;
	}

	public List<Expr> getArgs() {
		return args;
	}
	
	@Override 
	public String toString() {
		if ( args.size() == 0 ) {
			return "()";
		} else if ( args.size() == 1 ) {
			return "(" + args.get(0) +")";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			sb.append(args.get(0));
			for(int i = 1; i < args.size(); i++ ) {
				sb.append(",");
				sb.append(args.get(i));
			}
			sb.append(")");
			return sb.toString();
		}
	}
}
