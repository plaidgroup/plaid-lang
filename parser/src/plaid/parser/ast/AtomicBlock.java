package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class AtomicBlock extends Expression {
        private final List<Expression> datagroups;
        private final Expression body;

		public AtomicBlock(Token token, List<Expression> datagroups, Expression body) {
                super(token);
                this.datagroups = datagroups;
                this.body = body;
        }

        public List<Expression> getDataGroups() {
			return datagroups;
		}

		public Expression getBody() {
			return body;
		}
		
		@Override
		public boolean equivalent(ASTNode other) {
			if (other instanceof AtomicBlock) {
				AtomicBlock otherAtomic = (AtomicBlock) other;
				return 
					this.body.equivalent(otherAtomic.body);
					
			} else {
				return false;
			}
		}

}
        
        
