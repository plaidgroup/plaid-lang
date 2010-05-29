package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ID", toplevel = true)
public class ID{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1273$plaid;
	static {
		vAr1273$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1273$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ID", toplevel = false)
	public static final plaid.runtime.PlaidObject ID;
	static {
		final plaid.runtime.PlaidState vAr1274$plaid;
		vAr1274$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1276$plaid;
		vAr1276$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1277$plaid) {
				plaid.runtime.PlaidScope vAr1278$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1278$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1275$plaid;
				vAr1275$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1275$plaid;
			}
		}
		);
		vAr1274$plaid.addMember("token",vAr1276$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "name", toplevel = false)
		final plaid.runtime.PlaidObject vAr1280$plaid;
		vAr1280$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1281$plaid) {
				plaid.runtime.PlaidScope vAr1282$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1282$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1279$plaid;
				vAr1279$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1279$plaid;
			}
		}
		);
		vAr1274$plaid.addMember("name",vAr1280$plaid);
		ID = vAr1274$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ID")
	public static final plaid.runtime.PlaidTag ID$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1283$plaid;
		final plaid.runtime.PlaidObject vAr1284$plaid;
		vAr1284$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1283$plaid = plaid.runtime.Util.toPlaidState(vAr1284$plaid);
		ID$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ID", vAr1283$plaid);
	}
}
