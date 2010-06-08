package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ASTNode", toplevel = true)
public class ASTNode{
	public static final java.util.List<plaid.runtime.utils.Import> vAr281$plaid;
	static {
		vAr281$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr281$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr281$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ASTNode", toplevel = false)
	public static final plaid.runtime.PlaidObject ASTNode;
	static {
		final plaid.runtime.PlaidState vAr282$plaid;
		vAr282$plaid = plaid.runtime.Util.newState();
		ASTNode = vAr282$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ASTNode")
	public static final plaid.runtime.PlaidTag ASTNode$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr283$plaid;
		final plaid.runtime.PlaidObject vAr284$plaid;
		vAr284$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr283$plaid = plaid.runtime.Util.toPlaidState(vAr284$plaid);
		ASTNode$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ASTNode", vAr283$plaid);
	}
}
