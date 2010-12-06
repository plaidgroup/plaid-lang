package plaid.compilerjava.types;

public class ChangeType {

	public static final ChangeType DEFAULTPARAMTYPE = new ChangeType(PermType.getVoidPT(), PermType.getVoidPT(), true);
	public static final ChangeType DYN = new ChangeType(PermType.getDynPT(), PermType.getDynPT(), false);
	
	private final PermType input;
	private final PermType output;
	private final boolean borrow;
	
	public ChangeType(PermType input, PermType output, boolean borrow) {
		this.input = input;
		this.output = output;
		this.borrow = borrow;
	}

	public PermType getInput() {
		return input;
	}

	public PermType getOutput() {
		return output;
	}
	
	public boolean borrows() {
		return borrow;
	}
	
	@Override 
	public String toString() {
		return input.toString() + " >> " + output.toString();
		
	}
}
