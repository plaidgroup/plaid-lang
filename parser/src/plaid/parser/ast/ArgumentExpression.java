package plaid.parser.ast;

import java.util.Iterator;
import java.util.List;

import plaid.parser.Token;

public class ArgumentExpression extends Expression {
	protected final List<Expression> args;

	public ArgumentExpression(Token token, List<Expression> args) {
		super(token);
		this.args = args;
	}

	public List<Expression> getArgs() {
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
