package plaid.parser.ast;

import plaid.parser.Token;

public class UnpackInnerGroups extends Expression {
        private final Expression body;

        public UnpackInnerGroups(Token token, Expression body) {
                super(token);
                this.body = body;
        }

        public Expression getBody() {
			return body;
		}
}
        
        
