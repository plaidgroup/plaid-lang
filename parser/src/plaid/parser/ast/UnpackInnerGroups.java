package plaid.parser.ast;

import plaid.parser.Token;

public class UnpackInnerGroups extends Expr {
        private final Expr body;

        public UnpackInnerGroups(Token token, Expr body) {
                super(token);
                this.body = body;
        }

        public Expr getBody() {
			return body;
		}

        @Override
        public String toString() {
        	return "unpackInnerGroups" + body.toString();        	
        }
}
        
        
