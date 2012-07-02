package plaid.compilerjava.AST;

public class MetaDereference implements MetaArgument {
	private final ID var;
	private final ID selector;
	
	public MetaDereference(ID var, ID selector) {
		super();
		this.var = var;
		this.selector = selector;
	}

	public ID getVariable() {
		return var;
	}

	public ID getSelector() {
		return selector;
	}
	
	@Override
	public String toString() {
		return "MetaDereference("+var.toString() +"." + selector.toString() +")";
	}
}
