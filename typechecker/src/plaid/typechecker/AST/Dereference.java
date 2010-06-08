package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Dereference", toplevel = true)
public class Dereference{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1189$plaid;
	static {
		vAr1189$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1189$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1189$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Dereference", toplevel = false)
	public static final plaid.runtime.PlaidObject Dereference;
	static {
		final plaid.runtime.PlaidState vAr1190$plaid;
		vAr1190$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1192$plaid;
		vAr1192$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1193$plaid) {
				final plaid.runtime.PlaidObject vAr1191$plaid;
				vAr1191$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1191$plaid;
			}
		}
		);
		vAr1190$plaid.addMember("token",vAr1192$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "left", toplevel = false)
		final plaid.runtime.PlaidObject vAr1195$plaid;
		vAr1195$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1196$plaid) {
				final plaid.runtime.PlaidObject vAr1194$plaid;
				vAr1194$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1194$plaid;
			}
		}
		);
		vAr1190$plaid.addMember("left",vAr1195$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "right", toplevel = false)
		final plaid.runtime.PlaidObject vAr1198$plaid;
		vAr1198$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1199$plaid) {
				final plaid.runtime.PlaidObject vAr1197$plaid;
				vAr1197$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1197$plaid;
			}
		}
		);
		vAr1190$plaid.addMember("right",vAr1198$plaid, true);
		Dereference = vAr1190$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Dereference")
	public static final plaid.runtime.PlaidTag Dereference$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1200$plaid;
		final plaid.runtime.PlaidObject vAr1201$plaid;
		vAr1201$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1200$plaid = plaid.runtime.Util.toPlaidState(vAr1201$plaid);
		Dereference$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Dereference", vAr1200$plaid);
	}
}
