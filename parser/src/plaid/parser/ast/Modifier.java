package plaid.parser.ast;

import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class Modifier extends ASTNode {
	public static final List<Modifier> EMPTY = Collections.emptyList();
	
	public enum ModifierKind {OVERRIDE,REQUIRES,IMMUTABLE};

	private final ModifierKind modiferKind;
	
	public Modifier(Token t, ModifierKind modiferKind) {
		super(t);
		this.modiferKind = modiferKind;
	}

	public ModifierKind getModiferKind() {
		return modiferKind;
	}
	
	@Override
	public String toString() {
		return modiferKind.name().toLowerCase();		
	}
}
