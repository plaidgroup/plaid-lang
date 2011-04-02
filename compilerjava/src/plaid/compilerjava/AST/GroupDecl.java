package plaid.compilerjava.AST;

import java.io.File;
import java.util.Set;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.QualifiedID;

public class GroupDecl implements Decl {
	private final Token token; 
	private final ID id;
	private final boolean isAbstract;
		
	public GroupDecl(Token token, ID id, boolean isAbstract) {
		super();
		this.token = token;
		this.id = id;
		this.isAbstract = isAbstract;
	}

	@Override
	public void codegenNestedDecl(CodeGen out, ID y, IDList localVars, 	Set<ID> stateVars, ID tagContext) {
		// TODO Auto-generated method stub

	}

	public ID getId() {
		return id;
	}
	
	public boolean isAbstract() {
		return isAbstract;
	}
	
	@Override
	public File codegenTopDecl(QualifiedID qid, ImportList imports,	CompilerConfiguration cc, Set<ID> globalVars, PackageRep plaidPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberRep generateHeader(PackageRep plaidpath, ImportList imports, String inPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return id.getName();
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Token getToken() {
		return token;
	}

	@Override
	public boolean hasToken() {
		return true;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		// TODO Auto-generated method stub

	}

}
