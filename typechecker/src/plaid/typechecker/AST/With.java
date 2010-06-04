package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "With", toplevel = true)
public class With{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1459$plaid;
	static {
		vAr1459$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1459$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "With", toplevel = false)
	public static final plaid.runtime.PlaidObject With;
	static {
		final plaid.runtime.PlaidState vAr1460$plaid;
		vAr1460$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1462$plaid;
		vAr1462$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1463$plaid) {
				plaid.runtime.PlaidScope vAr1464$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1464$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1461$plaid;
				vAr1461$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1461$plaid;
			}
		}
		);
		vAr1460$plaid.addMember("token",vAr1462$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "s1", toplevel = false)
		final plaid.runtime.PlaidObject vAr1466$plaid;
		vAr1466$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1467$plaid) {
				plaid.runtime.PlaidScope vAr1468$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1468$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1465$plaid;
				vAr1465$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1465$plaid;
			}
		}
		);
		vAr1460$plaid.addMember("s1",vAr1466$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "s2", toplevel = false)
		final plaid.runtime.PlaidObject vAr1470$plaid;
		vAr1470$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1471$plaid) {
				plaid.runtime.PlaidScope vAr1472$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1472$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1469$plaid;
				vAr1469$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1469$plaid;
			}
		}
		);
		vAr1460$plaid.addMember("s2",vAr1470$plaid);
		With = vAr1460$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.With")
	public static final plaid.runtime.PlaidTag With$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1473$plaid;
		final plaid.runtime.PlaidObject vAr1474$plaid;
		vAr1474$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1473$plaid = plaid.runtime.Util.toPlaidState(vAr1474$plaid);
		With$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.With", vAr1473$plaid);
	}
}
