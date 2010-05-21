package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ImportList", toplevel = true)
public class ImportList{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1285$plaid;
	static {
		vAr1285$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1285$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ImportList", toplevel = false)
	public static final plaid.runtime.PlaidObject ImportList;
	static {
		final plaid.runtime.PlaidState vAr1286$plaid;
		vAr1286$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "imports", toplevel = false)
		final plaid.runtime.PlaidObject vAr1288$plaid;
		vAr1288$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1289$plaid) {
				plaid.runtime.PlaidScope vAr1290$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1290$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1287$plaid;
				vAr1287$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1287$plaid;
			}
		}
		);
		vAr1286$plaid.addMember("imports",vAr1288$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1292$plaid;
		vAr1292$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1293$plaid) {
				plaid.runtime.PlaidScope vAr1294$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1294$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1291$plaid;
				vAr1291$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1291$plaid;
			}
		}
		);
		vAr1286$plaid.addMember("token",vAr1292$plaid);
		ImportList = vAr1286$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ImportList")
	public static final plaid.runtime.PlaidTag ImportList$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1295$plaid;
		final plaid.runtime.PlaidObject vAr1296$plaid;
		vAr1296$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1295$plaid = plaid.runtime.Util.toPlaidState(vAr1296$plaid);
		ImportList$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ImportList", vAr1295$plaid);
	}
}
