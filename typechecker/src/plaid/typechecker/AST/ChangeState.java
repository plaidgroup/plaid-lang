package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ChangeState", toplevel = true)
public class ChangeState{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1186$plaid;
	static {
		vAr1186$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1186$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ChangeState", toplevel = false)
	public static final plaid.runtime.PlaidObject ChangeState;
	static {
		final plaid.runtime.PlaidState vAr1187$plaid;
		vAr1187$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1189$plaid;
		vAr1189$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1190$plaid) {
				plaid.runtime.PlaidScope vAr1191$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1191$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1188$plaid;
				vAr1188$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1188$plaid;
			}
		}
		);
		vAr1187$plaid.addMember("token",vAr1189$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1193$plaid;
		vAr1193$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1194$plaid) {
				plaid.runtime.PlaidScope vAr1195$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1195$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1192$plaid;
				vAr1192$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1192$plaid;
			}
		}
		);
		vAr1187$plaid.addMember("e",vAr1193$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "s", toplevel = false)
		final plaid.runtime.PlaidObject vAr1197$plaid;
		vAr1197$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1198$plaid) {
				plaid.runtime.PlaidScope vAr1199$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1199$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1196$plaid;
				vAr1196$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1196$plaid;
			}
		}
		);
		vAr1187$plaid.addMember("s",vAr1197$plaid);
		ChangeState = vAr1187$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ChangeState")
	public static final plaid.runtime.PlaidTag ChangeState$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1200$plaid;
		final plaid.runtime.PlaidObject vAr1201$plaid;
		vAr1201$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1200$plaid = plaid.runtime.Util.toPlaidState(vAr1201$plaid);
		ChangeState$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ChangeState", vAr1200$plaid);
	}
}
