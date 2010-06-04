package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Lambda", toplevel = true)
public class Lambda{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1309$plaid;
	static {
		vAr1309$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1309$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Lambda", toplevel = false)
	public static final plaid.runtime.PlaidObject Lambda;
	static {
		final plaid.runtime.PlaidState vAr1310$plaid;
		vAr1310$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1312$plaid;
		vAr1312$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1313$plaid) {
				plaid.runtime.PlaidScope vAr1314$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1314$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1311$plaid;
				vAr1311$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1311$plaid;
			}
		}
		);
		vAr1310$plaid.addMember("token",vAr1312$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "x", toplevel = false)
		final plaid.runtime.PlaidObject vAr1316$plaid;
		vAr1316$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1317$plaid) {
				plaid.runtime.PlaidScope vAr1318$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1318$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1315$plaid;
				vAr1315$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1315$plaid;
			}
		}
		);
		vAr1310$plaid.addMember("x",vAr1316$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "body", toplevel = false)
		final plaid.runtime.PlaidObject vAr1320$plaid;
		vAr1320$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1321$plaid) {
				plaid.runtime.PlaidScope vAr1322$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1322$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1319$plaid;
				vAr1319$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1319$plaid;
			}
		}
		);
		vAr1310$plaid.addMember("body",vAr1320$plaid);
		Lambda = vAr1310$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Lambda")
	public static final plaid.runtime.PlaidTag Lambda$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1323$plaid;
		final plaid.runtime.PlaidObject vAr1324$plaid;
		vAr1324$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1323$plaid = plaid.runtime.Util.toPlaidState(vAr1324$plaid);
		Lambda$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Lambda", vAr1323$plaid);
	}
}
