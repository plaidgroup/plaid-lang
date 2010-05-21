package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "IntLiteral", toplevel = true)
public class IntLiteral{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1297$plaid;
	static {
		vAr1297$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1297$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "IntLiteral", toplevel = false)
	public static final plaid.runtime.PlaidObject IntLiteral;
	static {
		final plaid.runtime.PlaidState vAr1298$plaid;
		vAr1298$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1300$plaid;
		vAr1300$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1301$plaid) {
				plaid.runtime.PlaidScope vAr1302$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1302$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1299$plaid;
				vAr1299$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1299$plaid;
			}
		}
		);
		vAr1298$plaid.addMember("token",vAr1300$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "integer", toplevel = false)
		final plaid.runtime.PlaidObject vAr1304$plaid;
		vAr1304$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1305$plaid) {
				plaid.runtime.PlaidScope vAr1306$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1306$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1303$plaid;
				vAr1303$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1303$plaid;
			}
		}
		);
		vAr1298$plaid.addMember("integer",vAr1304$plaid);
		IntLiteral = vAr1298$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.IntLiteral")
	public static final plaid.runtime.PlaidTag IntLiteral$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1307$plaid;
		final plaid.runtime.PlaidObject vAr1308$plaid;
		vAr1308$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1307$plaid = plaid.runtime.Util.toPlaidState(vAr1308$plaid);
		IntLiteral$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.IntLiteral", vAr1307$plaid);
	}
}
