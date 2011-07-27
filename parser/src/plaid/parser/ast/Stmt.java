package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class Stmt extends ASTNode {

	public Stmt(Token token) {
		super(token);
	}
	
	 protected String stmtListToString(List<Stmt> exprs) {
		 StringBuilder sb = new StringBuilder();
		 
		 if ( exprs.size() == 1 ) {
			 sb.append(exprs.get(0).toString());
		 } else  if ( exprs.size() > 1 ){
			 sb.append(exprs.get(0));
			 for (int i = 1; i < exprs.size(); i++) {
				 sb.append(";");
				 sb.append(exprs.get(i));
			 }
		 }
		 
		 return sb.toString();
	 }
}
