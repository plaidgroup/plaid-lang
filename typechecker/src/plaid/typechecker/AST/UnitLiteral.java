package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "UnitLiteral", toplevel = true)
public class UnitLiteral{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1449$plaid;
	static {
		vAr1449$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1449$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "UnitLiteral", toplevel = false)
	public static final plaid.runtime.PlaidObject UnitLiteral;
	static {
		final plaid.runtime.PlaidState vAr1450$plaid;
		vAr1450$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1452$plaid;
		vAr1452$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1453$plaid) {
				plaid.runtime.PlaidScope vAr1454$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1454$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1451$plaid;
				vAr1451$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1451$plaid;
			}
		}
		);
		vAr1450$plaid.addMember("token",vAr1452$plaid);
		UnitLiteral = vAr1450$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.UnitLiteral")
	public static final plaid.runtime.PlaidTag UnitLiteral$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1455$plaid;
		final plaid.runtime.PlaidObject vAr1456$plaid;
		vAr1456$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1455$plaid = plaid.runtime.Util.toPlaidState(vAr1456$plaid);
		UnitLiteral$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.UnitLiteral", vAr1455$plaid);
	}
}
