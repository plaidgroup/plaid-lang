package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class SplitBlock extends Expression {
        
	private final List<MetaArgument> metaArgs;
        private final List<Expression> body;

        public SplitBlock(Token token, List<MetaArgument> metaArgs, List<Expression> body) {
                super(token);
                this.metaArgs = metaArgs;
                this.body = body;
                if ( this.body.isEmpty() ) {
                	this.body.add(new UnitLiteral(DEFAULT_TOKEN));
                }
        }
        
        public List<MetaArgument> getMetaArguments() {
        	return metaArgs;
        }

        @Override
        public Token getToken() {
                return token;
        }

        public List<MetaArgument> getMetaArgs() {
			return metaArgs;
		}

		public List<Expression> getBody() {
			return body;
		}
}
        
        
