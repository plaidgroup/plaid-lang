package plaid.parser.ast;

import plaid.parser.Token;

public class AtomicBlock extends Expression {
        private final ID id;
        private final Expression body;

		public AtomicBlock(Token token, ID id, Expression body) {
                super(token);
                this.id = id;
                this.body = body;
        }

        public ID getId() {
			return id;
		}

		public Expression getBody() {
			return body;
		}

}
        
        
