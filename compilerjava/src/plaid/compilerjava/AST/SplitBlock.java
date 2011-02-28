package plaid.compilerjava.AST;

import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;

public class SplitBlock implements Expression {
		private final Token token;
        private final ID id;
        private final List<Expression> body;

        public SplitBlock(Token token, ID id, List<Expression> body) {
                super();
                this.token = token;
                this.id = id;
                this.body = body;
        }

        @Override
        public void codegenExpr(CodeGen out, ID y, IDList localVars,  Set<ID> stateVars) {
            for (Expression e : body ) {
           	 e.codegenExpr(out, y, localVars, stateVars);
            }
        }

        @Override
        public boolean hasToken() {
                return true;
        }

        @Override
        public Token getToken() {
                return token;
        }

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
        	return visitor.visitNode(this);
        }
        
        @Override
        public <T> void visitChildren(ASTVisitor<T> visitor) {
             id.accept(visitor);
             for (Expression e : body ) {
            	 e.accept(visitor);
             }
        }

}
        
        
