package plaid.parser.ast;

public class MetaVariable implements MetaArgument {
	private final Identifier id;
	
	public MetaVariable(Identifier id) {
		super();
		this.id = id;
	}
	
	public Identifier getID() {
		return id;
	}

	@Override
	public String toString() {
		return "MetaVariable(" + id.getName() +")";
	}
}
