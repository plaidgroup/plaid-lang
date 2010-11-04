package plaid.compilerjava.types;

public class ChangeType {

	private final PermType input;
	private final PermType output;
	
	public ChangeType(PermType input, PermType output) {
		
		this.input = input;
		this.output = output;
	}

	public PermType getInput() {
		return input;
	}

	public PermType getOutput() {
		return output;
	}
}
