package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ASTNode", toplevel = true)
public class ASTNode{
	public static final java.util.List<plaid.runtime.utils.Import> vAr234$plaid;
	static {
		vAr234$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr234$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ASTNode", toplevel = false)
	public static final plaid.runtime.PlaidObject ASTNode;
	static {
		final plaid.runtime.PlaidState vAr235$plaid;
		vAr235$plaid = plaid.runtime.Util.newState();
		ASTNode = vAr235$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ASTNode")
	public static final plaid.runtime.PlaidTag ASTNode$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr236$plaid;
		final plaid.runtime.PlaidObject vAr237$plaid;
		vAr237$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr236$plaid = plaid.runtime.Util.toPlaidState(vAr237$plaid);
		ASTNode$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ASTNode", vAr236$plaid);
	}
}
