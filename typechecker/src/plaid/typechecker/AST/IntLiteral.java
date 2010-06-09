package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "IntLiteral", toplevel = true)
public class IntLiteral{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1238$plaid;
	static {
		vAr1238$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1238$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1238$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "IntLiteral", toplevel = false)
	public static final plaid.runtime.PlaidObject IntLiteral;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1239$plaid;
		vAr1239$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1241$plaid;
		vAr1241$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1242$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1240$plaid;
				vAr1240$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1240$plaid;
			}
		}
		);
		vAr1239$plaid.addMember("token",vAr1241$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "integer", toplevel = false)
		final plaid.runtime.PlaidObject vAr1244$plaid;
		vAr1244$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1245$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1243$plaid;
				vAr1243$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1243$plaid;
			}
		}
		);
		vAr1239$plaid.addMember("integer",vAr1244$plaid, true);
		IntLiteral = vAr1239$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.IntLiteral")
	public static final plaid.runtime.PlaidTag IntLiteral$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1246$plaid;
		final plaid.runtime.PlaidObject vAr1247$plaid;
		vAr1247$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1246$plaid = plaid.runtime.Util.toPlaidState(vAr1247$plaid);
		IntLiteral$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.IntLiteral", vAr1246$plaid);
	}
}
