package plaid.compilerjava.AST;

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;

public class AtomicBlock implements Expression {
		private final Token token;
        private final ID id;
        private final Expression body;

		public AtomicBlock(Token token, ID id, Expression body) {
                super();
                this.token = token;
                this.id = id;
                this.body = body;
        }

        @Override
        public void codegenExpr(CodeGen out, ID y, IDList localVars,  Set<ID> stateVars) {
        	body.codegenExpr(out, y, localVars, stateVars);
        }

        @Override
        public boolean hasToken() {
                return true;
        }

        @Override
        public Token getToken() {
                return token;
        }

        public ID getId() {
			return id;
		}

		public Expression getBody() {
			return body;
		}

        @Override
        public <T> T accept(ASTVisitor<T> visitor) {
        	return visitor.visitNode(this);
        }
        
        @Override
        public <T> void visitChildren(ASTVisitor<T> visitor) {
             id.accept(visitor);
             body.accept(visitor);
        }

}
        
        
