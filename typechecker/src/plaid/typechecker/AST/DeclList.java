package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "DeclList", toplevel = true)
public class DeclList{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1225$plaid;
	static {
		vAr1225$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1225$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "DeclList", toplevel = false)
	public static final plaid.runtime.PlaidObject DeclList;
	static {
		final plaid.runtime.PlaidState vAr1226$plaid;
		vAr1226$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1228$plaid;
		vAr1228$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1229$plaid) {
				plaid.runtime.PlaidScope vAr1230$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1230$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1227$plaid;
				vAr1227$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1227$plaid;
			}
		}
		);
		vAr1226$plaid.addMember("token",vAr1228$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "decls", toplevel = false)
		final plaid.runtime.PlaidObject vAr1232$plaid;
		vAr1232$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1233$plaid) {
				plaid.runtime.PlaidScope vAr1234$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1234$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1231$plaid;
				vAr1231$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1231$plaid;
			}
		}
		);
		vAr1226$plaid.addMember("decls",vAr1232$plaid);
		DeclList = vAr1226$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.DeclList")
	public static final plaid.runtime.PlaidTag DeclList$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1235$plaid;
		final plaid.runtime.PlaidObject vAr1236$plaid;
		vAr1236$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1235$plaid = plaid.runtime.Util.toPlaidState(vAr1236$plaid);
		DeclList$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.DeclList", vAr1235$plaid);
	}
}
