package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ID", toplevel = true)
public class ID{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1218$plaid;
	static {
		vAr1218$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1218$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1218$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ID", toplevel = false)
	public static final plaid.runtime.PlaidObject ID;
	static {
		final plaid.runtime.PlaidState vAr1219$plaid;
		vAr1219$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1221$plaid;
		vAr1221$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1222$plaid) {
				final plaid.runtime.PlaidObject vAr1220$plaid;
				vAr1220$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1220$plaid;
			}
		}
		);
		vAr1219$plaid.addMember("token",vAr1221$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "name", toplevel = false)
		final plaid.runtime.PlaidObject vAr1224$plaid;
		vAr1224$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1225$plaid) {
				final plaid.runtime.PlaidObject vAr1223$plaid;
				vAr1223$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1223$plaid;
			}
		}
		);
		vAr1219$plaid.addMember("name",vAr1224$plaid, true);
		ID = vAr1219$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ID")
	public static final plaid.runtime.PlaidTag ID$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1226$plaid;
		final plaid.runtime.PlaidObject vAr1227$plaid;
		vAr1227$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1226$plaid = plaid.runtime.Util.toPlaidState(vAr1227$plaid);
		ID$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ID", vAr1226$plaid);
	}
}
