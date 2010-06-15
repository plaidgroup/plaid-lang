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
	public void accept(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
