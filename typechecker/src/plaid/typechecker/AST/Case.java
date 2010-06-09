package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Case", toplevel = true)
public class Case{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1125$plaid;
	static {
		vAr1125$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1125$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1125$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Case", toplevel = false)
	public static final plaid.runtime.PlaidObject Case;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1126$plaid;
		vAr1126$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1128$plaid;
		vAr1128$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1129$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1127$plaid;
				vAr1127$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1127$plaid;
			}
		}
		);
		vAr1126$plaid.addMember("token",vAr1128$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "qi", toplevel = false)
		final plaid.runtime.PlaidObject vAr1131$plaid;
		vAr1131$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1132$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1130$plaid;
				vAr1130$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1130$plaid;
			}
		}
		);
		vAr1126$plaid.addMember("qi",vAr1131$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "x", toplevel = false)
		final plaid.runtime.PlaidObject vAr1134$plaid;
		vAr1134$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1135$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1133$plaid;
				vAr1133$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1133$plaid;
			}
		}
		);
		vAr1126$plaid.addMember("x",vAr1134$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1137$plaid;
		vAr1137$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1138$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1136$plaid;
				vAr1136$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1136$plaid;
			}
		}
		);
		vAr1126$plaid.addMember("e",vAr1137$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "defaultCase", toplevel = false)
		final plaid.runtime.PlaidObject vAr1140$plaid;
		vAr1140$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1141$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1139$plaid;
				vAr1139$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1139$plaid;
			}
		}
		);
		vAr1126$plaid.addMember("defaultCase",vAr1140$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "boundVar", toplevel = false)
		final plaid.runtime.PlaidObject vAr1143$plaid;
		vAr1143$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1144$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1142$plaid;
				vAr1142$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1142$plaid;
			}
		}
		);
		vAr1126$plaid.addMember("boundVar",vAr1143$plaid, true);
		Case = vAr1126$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Case")
	public static final plaid.runtime.PlaidTag Case$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1145$plaid;
		final plaid.runtime.PlaidObject vAr1146$plaid;
		vAr1146$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1145$plaid = plaid.runtime.Util.toPlaidState(vAr1146$plaid);
		Case$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Case", vAr1145$plaid);
	}
}
