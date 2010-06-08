package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "QI", toplevel = true)
public class QI{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1322$plaid;
	static {
		vAr1322$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1322$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1322$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "QI", toplevel = false)
	public static final plaid.runtime.PlaidObject QI;
	static {
		final plaid.runtime.PlaidState vAr1323$plaid;
		vAr1323$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1325$plaid;
		vAr1325$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1326$plaid) {
				final plaid.runtime.PlaidObject vAr1324$plaid;
				vAr1324$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1324$plaid;
			}
		}
		);
		vAr1323$plaid.addMember("token",vAr1325$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "qid", toplevel = false)
		final plaid.runtime.PlaidObject vAr1328$plaid;
		vAr1328$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1329$plaid) {
				final plaid.runtime.PlaidObject vAr1327$plaid;
				vAr1327$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1327$plaid;
			}
		}
		);
		vAr1323$plaid.addMember("qid",vAr1328$plaid, true);
		QI = vAr1323$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.QI")
	public static final plaid.runtime.PlaidTag QI$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1330$plaid;
		final plaid.runtime.PlaidObject vAr1331$plaid;
		vAr1331$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1330$plaid = plaid.runtime.Util.toPlaidState(vAr1331$plaid);
		QI$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.QI", vAr1330$plaid);
	}
}
