package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ASTNode", toplevel = true)
public class ASTNode{
	public static final java.util.List<plaid.runtime.utils.Import> vAr233$plaid;
	static {
		vAr233$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr233$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ASTNode", toplevel = false)
	public static final plaid.runtime.PlaidObject ASTNode;
	static {
		final plaid.runtime.PlaidState vAr234$plaid;
		vAr234$plaid = plaid.runtime.Util.newState();
		ASTNode = vAr234$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ASTNode")
	public static final plaid.runtime.PlaidTag ASTNode$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr235$plaid;
		final plaid.runtime.PlaidObject vAr236$plaid;
		vAr236$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr235$plaid = plaid.runtime.Util.toPlaidState(vAr236$plaid);
		ASTNode$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ASTNode", vAr235$plaid);
	}
}
