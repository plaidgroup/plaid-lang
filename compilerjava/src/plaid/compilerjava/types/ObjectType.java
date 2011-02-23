package plaid.compilerjava.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import plaid.compilerjava.AST.ID;
import plaid.compilerjava.AST.MetaArgument;

/**
 * Represents a structural type without permissions.  Can contain both type 
 * abbreviations and type declarations.  When multiple types are composed 
 * using the "with" keyword, we currently union the sets of type abbreviations 
 * and type declarations together.  Not sure if this is the correct way to do it.
 * 
 * KBN 2010-11-4 added nominal component instead of type abbreviations
 * quick hack until we come back to structural types...
 * 
 * @author mhahnenberg
 *
 */
public class ObjectType implements Type, /*ASTnode,*/ JSONAware {
	public static final ObjectType DYN = new ObjectType(new ID("dyn"));
	public static final ObjectType UNIT = new ObjectType(new ID("unit"));
	// This is a marker to indicate that the type of the receiver should remain unchanged
	//public static final ObjectType RECEIVER = new ObjectType(new ID("receiver"));
	
	/**
	 * Set of type abbreviations contained in this type.
	 */
	private final Set<ID> typeAbbrevs;
	private final Set<TypeDecl> typeDecls;
	private final ID nominalType;
	private final boolean isNominal;
	private final List<MetaArgument> metaArgs;
	
	public ObjectType() {
		this.typeAbbrevs = new HashSet<ID>();
		this.typeDecls = new HashSet<TypeDecl>();
		this.nominalType = null;
		this.isNominal = false;
		this.metaArgs = new ArrayList<MetaArgument>();
	}
	
	public ObjectType(ID typeAbbrev) {
		//this();
		this.typeAbbrevs = null;
		this.typeDecls = null;
		this.nominalType = typeAbbrev;
		this.isNominal = true;
		this.metaArgs = null;		
	}
	
	public ObjectType(ID typeAbbrev, List<MetaArgument> metaArgs) {
		//this();
		this.typeAbbrevs = null;
		this.typeDecls = null;
		this.nominalType = typeAbbrev;
		this.isNominal = true;
		this.metaArgs = metaArgs;		
	}
	
	public ObjectType(Collection<TypeDecl> typeDecls) {
		this();
		this.typeDecls.addAll(typeDecls);
	}
	
	private ObjectType(ObjectType t1, ObjectType t2) {
		this();
		if (t1.isNominal()) this.typeAbbrevs.add(t1.getNominalType());
		else {
			this.typeAbbrevs.addAll(t1.getTypeAbbrevs());
			this.typeDecls.addAll(t1.getTypeDecls());

		}
		if (!t2.isNominal()) {
			this.typeAbbrevs.addAll(t2.getTypeAbbrevs());
			this.typeDecls.addAll(t2.getTypeDecls());
		}
	}
	
	public Set<ID> getTypeAbbrevs() {
		return Collections.unmodifiableSet(typeAbbrevs);
	}

	public Set<TypeDecl> getTypeDecls() {
		return Collections.unmodifiableSet(typeDecls);
	}

	public ID getNominalType() {
		return nominalType;
	}

	public boolean isNominal() {
		return isNominal;
	}

	public ObjectType compose(ObjectType other) {
		if (other == null)
			return this;
		return new ObjectType(this, other);
	}

//	@Override
//	public <T> T accept(ASTVisitor<T> visitor) {
//		return visitor.visitNode(this);
//	}
//
//	@Override
//	public Token getToken() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public boolean hasToken() {
//		return false;
//	}
//
//	@Override
//	public <T> void visitChildren(ASTVisitor<T> visitor) {
//		for (ID id : this.typeAbbrevs) {
//			id.accept(visitor);
//		}
//		for (TypeDecl td : this.typeDecls) {
//			td.accept(visitor);
//		}
//	}
	
	public String toString() {
		if (isNominal) return "N(" + nominalType.getName() + ")";
		
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

	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		//obj.put("abbrevs", value);
		//obj.put("members", value);
		return obj.toJSONString();
	}
}
