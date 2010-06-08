package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "StringLiteral", toplevel = true)
public class StringLiteral{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1351$plaid;
	static {
		vAr1351$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1351$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1351$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "StringLiteral", toplevel = false)
	public static final plaid.runtime.PlaidObject StringLiteral;
	static {
		final plaid.runtime.PlaidState vAr1352$plaid;
		vAr1352$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1354$plaid;
		vAr1354$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1355$plaid) {
				final plaid.runtime.PlaidObject vAr1353$plaid;
				vAr1353$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1353$plaid;
			}
		}
		);
		vAr1352$plaid.addMember("token",vAr1354$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "string", toplevel = false)
		final plaid.runtime.PlaidObject vAr1357$plaid;
		vAr1357$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1358$plaid) {
				final plaid.runtime.PlaidObject vAr1356$plaid;
				vAr1356$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1356$plaid;
			}
		}
		);
		vAr1352$plaid.addMember("string",vAr1357$plaid, true);
		StringLiteral = vAr1352$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.StringLiteral")
	public static final plaid.runtime.PlaidTag StringLiteral$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1359$plaid;
		final plaid.runtime.PlaidObject vAr1360$plaid;
		vAr1360$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1359$plaid = plaid.runtime.Util.toPlaidState(vAr1360$plaid);
		StringLiteral$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.StringLiteral", vAr1359$plaid);
	}
}
