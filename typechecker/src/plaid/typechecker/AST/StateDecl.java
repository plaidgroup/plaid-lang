package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "StateDecl", toplevel = true)
public class StateDecl{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1413$plaid;
	static {
		vAr1413$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1413$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "StateDecl", toplevel = false)
	public static final plaid.runtime.PlaidObject StateDecl;
	static {
		final plaid.runtime.PlaidState vAr1414$plaid;
		vAr1414$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1416$plaid;
		vAr1416$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1417$plaid) {
				plaid.runtime.PlaidScope vAr1418$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1418$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1415$plaid;
				vAr1415$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1415$plaid;
			}
		}
		);
		vAr1414$plaid.addMember("token",vAr1416$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "name", toplevel = false)
		final plaid.runtime.PlaidObject vAr1420$plaid;
		vAr1420$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1421$plaid) {
				plaid.runtime.PlaidScope vAr1422$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1422$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1419$plaid;
				vAr1419$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1419$plaid;
			}
		}
		);
		vAr1414$plaid.addMember("name",vAr1420$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "stateDef", toplevel = false)
		final plaid.runtime.PlaidObject vAr1424$plaid;
		vAr1424$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1425$plaid) {
				plaid.runtime.PlaidScope vAr1426$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1426$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1423$plaid;
				vAr1423$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1423$plaid;
			}
		}
		);
		vAr1414$plaid.addMember("stateDef",vAr1424$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "caseOf", toplevel = false)
		final plaid.runtime.PlaidObject vAr1428$plaid;
		vAr1428$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1429$plaid) {
				plaid.runtime.PlaidScope vAr1430$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1430$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1427$plaid;
				vAr1427$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1427$plaid;
			}
		}
		);
		vAr1414$plaid.addMember("caseOf",vAr1428$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "isCaseOf", toplevel = false)
		final plaid.runtime.PlaidObject vAr1432$plaid;
		vAr1432$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1433$plaid) {
				plaid.runtime.PlaidScope vAr1434$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1434$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1431$plaid;
				vAr1431$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1431$plaid;
			}
		}
		);
		vAr1414$plaid.addMember("isCaseOf",vAr1432$plaid);
		StateDecl = vAr1414$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.StateDecl")
	public static final plaid.runtime.PlaidTag StateDecl$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1435$plaid;
		final plaid.runtime.PlaidObject vAr1436$plaid;
		vAr1436$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1435$plaid = plaid.runtime.Util.toPlaidState(vAr1436$plaid);
		StateDecl$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.StateDecl", vAr1435$plaid);
	}
}
