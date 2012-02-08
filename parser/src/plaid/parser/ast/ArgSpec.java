package plaid.parser.ast;

import plaid.parser.Token;

public class ArgSpec extends ASTNode {
	
	public static final ArgSpec EMPTY = EmptyArgSpec.getEmptyArgSpec();
	private final Type inputType;
	private final Type outputType;
	
	public ArgSpec(Token t, Type inputType, Type outputType) {
		super(t);
		this.inputType = inputType;
		if (outputType == null || outputType == Type.EMPTY) {
			this.outputType = inputType;
		} 
		else {
			this.outputType = outputType;
		}
	}
	
	public Type getInputType() {
		return inputType;
	}

	public Type getOutputType() {
		return outputType;
	}

	@Override
	public String toString() {
		if ( this == EMPTY ) {
			return "";
		} else {
			return inputType + ((outputType!=Type.EMPTY)?">>"+outputType:"");
		}
	}
}
