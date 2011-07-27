package plaid.parser.ast;

import plaid.parser.Token;

public class AtomicBlock extends Expression {
        private final Identifier id;
        private final Expression body;

		public AtomicBlock(Token token, Identifier id, Expression body) {
                super(token);
                this.id = id;
                this.body = body;
        }

        public Identifier getId() {
			return id;
		}

		public Expression getBody() {
			return body;
		}

}
        
        
