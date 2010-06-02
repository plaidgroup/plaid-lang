package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Match", toplevel = true)
public class Match{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1349$plaid;
	static {
		vAr1349$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1349$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Match", toplevel = false)
	public static final plaid.runtime.PlaidObject Match;
	static {
		final plaid.runtime.PlaidState vAr1350$plaid;
		vAr1350$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1352$plaid;
		vAr1352$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1353$plaid) {
				plaid.runtime.PlaidScope vAr1354$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1354$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1351$plaid;
				vAr1351$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1351$plaid;
			}
		}
		);
		vAr1350$plaid.addMember("token",vAr1352$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1356$plaid;
		vAr1356$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1357$plaid) {
				plaid.runtime.PlaidScope vAr1358$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1358$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1355$plaid;
				vAr1355$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1355$plaid;
			}
		}
		);
		vAr1350$plaid.addMember("e",vAr1356$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "caseList", toplevel = false)
		final plaid.runtime.PlaidObject vAr1360$plaid;
		vAr1360$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1361$plaid) {
				plaid.runtime.PlaidScope vAr1362$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1362$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1359$plaid;
				vAr1359$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1359$plaid;
			}
		}
		);
		vAr1350$plaid.addMember("caseList",vAr1360$plaid);
		Match = vAr1350$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Match")
	public static final plaid.runtime.PlaidTag Match$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1363$plaid;
		final plaid.runtime.PlaidObject vAr1364$plaid;
		vAr1364$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1363$plaid = plaid.runtime.Util.toPlaidState(vAr1364$plaid);
		Match$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Match", vAr1363$plaid);
	}
}
