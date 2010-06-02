package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "StringLiteral", toplevel = true)
public class StringLiteral{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1437$plaid;
	static {
		vAr1437$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1437$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "StringLiteral", toplevel = false)
	public static final plaid.runtime.PlaidObject StringLiteral;
	static {
		final plaid.runtime.PlaidState vAr1438$plaid;
		vAr1438$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1440$plaid;
		vAr1440$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1441$plaid) {
				plaid.runtime.PlaidScope vAr1442$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1442$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1439$plaid;
				vAr1439$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1439$plaid;
			}
		}
		);
		vAr1438$plaid.addMember("token",vAr1440$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "string", toplevel = false)
		final plaid.runtime.PlaidObject vAr1444$plaid;
		vAr1444$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1445$plaid) {
				plaid.runtime.PlaidScope vAr1446$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1446$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1443$plaid;
				vAr1443$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1443$plaid;
			}
		}
		);
		vAr1438$plaid.addMember("string",vAr1444$plaid);
		StringLiteral = vAr1438$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.StringLiteral")
	public static final plaid.runtime.PlaidTag StringLiteral$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1447$plaid;
		final plaid.runtime.PlaidObject vAr1448$plaid;
		vAr1448$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1447$plaid = plaid.runtime.Util.toPlaidState(vAr1448$plaid);
		StringLiteral$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.StringLiteral", vAr1447$plaid);
	}
}
