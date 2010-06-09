package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "DeclList", toplevel = true)
public class DeclList{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1179$plaid;
	static {
		vAr1179$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1179$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1179$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "DeclList", toplevel = false)
	public static final plaid.runtime.PlaidObject DeclList;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1180$plaid;
		vAr1180$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1182$plaid;
		vAr1182$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1183$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1181$plaid;
				vAr1181$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1181$plaid;
			}
		}
		);
		vAr1180$plaid.addMember("token",vAr1182$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "decls", toplevel = false)
		final plaid.runtime.PlaidObject vAr1185$plaid;
		vAr1185$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1186$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1184$plaid;
				vAr1184$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1184$plaid;
			}
		}
		);
		vAr1180$plaid.addMember("decls",vAr1185$plaid, true);
		DeclList = vAr1180$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.DeclList")
	public static final plaid.runtime.PlaidTag DeclList$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1187$plaid;
		final plaid.runtime.PlaidObject vAr1188$plaid;
		vAr1188$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1187$plaid = plaid.runtime.Util.toPlaidState(vAr1188$plaid);
		DeclList$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.DeclList", vAr1187$plaid);
	}
}
