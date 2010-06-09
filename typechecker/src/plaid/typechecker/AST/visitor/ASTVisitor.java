package plaid.typechecker.AST.visitor;
@plaid.runtime.annotations.RepresentsState(name = "ASTVisitor", toplevel = true)
public class ASTVisitor{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1368$plaid;
	static {
		vAr1368$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1368$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST.visitor",vAr1368$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ASTVisitor", toplevel = false)
	public static final plaid.runtime.PlaidObject ASTVisitor;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1369$plaid;
		vAr1369$plaid = plaid.runtime.Util.newState();
		ASTVisitor = vAr1369$plaid.getPrototype();
	}
}
