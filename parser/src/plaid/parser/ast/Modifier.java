package plaid.parser.ast;

import plaid.parser.Token;

public class Modifier extends ASTNode {
	public enum ModifierKind {OVERRIDE,REQUIRES,IMMUTABLE};

	private final ModifierKind modiferKind;
	
	protected Modifier(Token t, ModifierKind modiferKind) {
		super(t);
		this.modiferKind = modiferKind;
	}

	public ModifierKind getModiferKind() {
		return modiferKind;
	}
	
}
