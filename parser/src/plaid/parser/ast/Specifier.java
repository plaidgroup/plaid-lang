package plaid.parser.ast;

import plaid.parser.Token;

public class Specifier extends ASTNode {
	
	protected Specifier(Token token) {
		super(token);
	}

	public  static Specifier VAL(Token token) {
		return new Val(token);
	}

	public static Specifier VAR(Token token) {
		return new Var(token);
	}
	
	public static class Val extends Specifier {
		protected Val(Token token) {
			super(token);
		}
	}
	
	public static class Var extends Specifier {
		protected Var(Token token) {
			super(token);
		}
	}
}
