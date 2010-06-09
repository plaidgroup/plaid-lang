package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "UnitLiteral", toplevel = true)
public class UnitLiteral{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1361$plaid;
	static {
		vAr1361$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1361$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1361$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "UnitLiteral", toplevel = false)
	public static final plaid.runtime.PlaidObject UnitLiteral;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1362$plaid;
		vAr1362$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1364$plaid;
		vAr1364$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1365$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1363$plaid;
				vAr1363$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1363$plaid;
			}
		}
		);
		vAr1362$plaid.addMember("token",vAr1364$plaid, true);
		UnitLiteral = vAr1362$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.UnitLiteral")
	public static final plaid.runtime.PlaidTag UnitLiteral$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1366$plaid;
		final plaid.runtime.PlaidObject vAr1367$plaid;
		vAr1367$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1366$plaid = plaid.runtime.Util.toPlaidState(vAr1367$plaid);
		UnitLiteral$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.UnitLiteral", vAr1366$plaid);
	}
}
