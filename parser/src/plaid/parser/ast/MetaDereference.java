package plaid.parser.ast;

public class MetaDereference implements MetaArgument {
	private final Identifier var;
	private final Identifier selector;
	
	public MetaDereference(Identifier var, Identifier selector) {
		super();
		this.var = var;
		this.selector = selector;
	}

	public Identifier getVariable() {
		return var;
	}

	public Identifier getSelector() {
		return selector;
	}
	
	@Override
	public String toString() {
		return "MetaDereference("+var.toString() +"." + selector.toString() +")";
	}
}
