package plaid.parser.ast;

public class ChangeType {
	
	private final PermType input;
	private final PermType output;
	private final boolean strongBorrow;
	
	public ChangeType(PermType input, PermType output, boolean borrow) {
		this.input = input;
		this.output = output;
		this.strongBorrow = borrow;
	}

	public PermType getInput() {
		return input;
	}

	public PermType getOutput() {
		return output;
	}
	
	public boolean stronglyBorrows() {
		return strongBorrow;
	}
	
	@Override 
	public String toString() {
		String b = this.strongBorrow ? "(B) " : " ";
		return input.toString() + " >>" + b + output.toString();
		
	}
}
