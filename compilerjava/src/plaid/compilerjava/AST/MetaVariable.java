package plaid.compilerjava.AST;

public class MetaVariable implements MetaArgument {
	private final ID id;
	
	public MetaVariable(ID id) {
		super();
		this.id = id;
	}
	
	public ID getID() {
		return id;
	}

	@Override
	public String toString() {
		return "MetaVariable(" + id.getName() +")";
	}
}
