package plaid.compilerjava.AST;

import java.util.*;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

/**
 * Represents a structural type without permissions.  Can contain both type 
 * abbreviations and type declarations.  When multiple types are composed 
 * using the "with" keyword, we currently union the sets of type abbreviations 
 * and type declarations together.  Not sure if this is the correct way to do it.
 * 
 * @author mhahnenberg
 *
 */
public class Type implements ASTnode {
	public static final Type DYN = new Type(new ID("dyn"));
	public static final Type UNIT = new Type(new ID("unit"));
	// This is a marker to indicate that the type of the receiver should remain unchanged
	public static final Type RECEIVER = new Type(new ID("receiver"));
	
	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final Set<ID> typeAbbrevs;
	private final Set<TypeDecl> typeDecls;
	
	public Type() {
		this.typeAbbrevs = new HashSet<ID>();
		this.typeDecls = new HashSet<TypeDecl>();
	}
	
	public Type(ID typeAbbrev) {
		this();
		this.typeAbbrevs.add(typeAbbrev);
	}
	
	public Type(Collection<TypeDecl> typeDecls) {
		this();
		this.typeDecls.addAll(typeDecls);
	}
	
	private Type(Type t1, Type t2) {
		this();
		this.typeAbbrevs.addAll(t1.getTypeAbbrevs());
		this.typeAbbrevs.addAll(t2.getTypeAbbrevs());
		this.typeDecls.addAll(t1.getTypeDecls());
		this.typeDecls.addAll(t2.getTypeDecls());
	}
	
	public Set<ID> getTypeAbbrevs() {
		return Collections.unmodifiableSet(typeAbbrevs);
	}

	public Set<TypeDecl> getTypeDecls() {
		return Collections.unmodifiableSet(typeDecls);
	}

	public Type compose(Type other) {
		if (other == null)
			return this;
		return new Type(this, other);
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		for (ID id : this.typeAbbrevs) {
			id.accept(visitor);
		}
		for (TypeDecl td : this.typeDecls) {
			td.accept(visitor);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Structural Type: {");
		if (this.typeAbbrevs.size() > 0) {
			sb.append("Type abbrevs: " + this.typeAbbrevs.toString());
			if (this.typeDecls.size() > 0) {
				sb.append(", ");
			}
		}
		if (this.typeDecls.size() > 0) {
			sb.append("Type decls: " + this.typeDecls.toString());
		}
		sb.append("}");
		return sb.toString();
	}
}
