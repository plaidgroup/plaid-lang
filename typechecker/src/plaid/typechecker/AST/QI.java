package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "QI", toplevel = true)
public class QI{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1401$plaid;
	static {
		vAr1401$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1401$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "QI", toplevel = false)
	public static final plaid.runtime.PlaidObject QI;
	static {
		final plaid.runtime.PlaidState vAr1402$plaid;
		vAr1402$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1404$plaid;
		vAr1404$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1405$plaid) {
				plaid.runtime.PlaidScope vAr1406$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1406$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1403$plaid;
				vAr1403$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1403$plaid;
			}
		}
		);
		vAr1402$plaid.addMember("token",vAr1404$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "qid", toplevel = false)
		final plaid.runtime.PlaidObject vAr1408$plaid;
		vAr1408$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1409$plaid) {
				plaid.runtime.PlaidScope vAr1410$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1410$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1407$plaid;
				vAr1407$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1407$plaid;
			}
		}
		);
		vAr1402$plaid.addMember("qid",vAr1408$plaid);
		QI = vAr1402$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.QI")
	public static final plaid.runtime.PlaidTag QI$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1411$plaid;
		final plaid.runtime.PlaidObject vAr1412$plaid;
		vAr1412$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1411$plaid = plaid.runtime.Util.toPlaidState(vAr1412$plaid);
		QI$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.QI", vAr1411$plaid);
	}
}
