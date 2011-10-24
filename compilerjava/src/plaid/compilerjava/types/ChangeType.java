package plaid.compilerjava.types;

public class ChangeType {

	public static final ChangeType DEFAULTPARAMTYPE = new ChangeType(PermType.getVoidPT(), PermType.getVoidPT(), true);
	public static final ChangeType DYN = new ChangeType(PermType.getDynPT(), PermType.getDynPT(), false);
	
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
