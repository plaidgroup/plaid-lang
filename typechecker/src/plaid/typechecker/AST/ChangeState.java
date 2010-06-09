package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ChangeState", toplevel = true)
public class ChangeState{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1147$plaid;
	static {
		vAr1147$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1147$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1147$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ChangeState", toplevel = false)
	public static final plaid.runtime.PlaidObject ChangeState;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1148$plaid;
		vAr1148$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1150$plaid;
		vAr1150$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1151$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1149$plaid;
				vAr1149$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1149$plaid;
			}
		}
		);
		vAr1148$plaid.addMember("token",vAr1150$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1153$plaid;
		vAr1153$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1154$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1152$plaid;
				vAr1152$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1152$plaid;
			}
		}
		);
		vAr1148$plaid.addMember("e",vAr1153$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "s", toplevel = false)
		final plaid.runtime.PlaidObject vAr1156$plaid;
		vAr1156$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1157$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1155$plaid;
				vAr1155$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1155$plaid;
			}
		}
		);
		vAr1148$plaid.addMember("s",vAr1156$plaid, true);
		ChangeState = vAr1148$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ChangeState")
	public static final plaid.runtime.PlaidTag ChangeState$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1158$plaid;
		final plaid.runtime.PlaidObject vAr1159$plaid;
		vAr1159$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1158$plaid = plaid.runtime.Util.toPlaidState(vAr1159$plaid);
		ChangeState$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ChangeState", vAr1158$plaid);
	}
}
