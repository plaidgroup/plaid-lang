package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "NewInstance", toplevel = true)
public class NewInstance{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1389$plaid;
	static {
		vAr1389$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1389$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "NewInstance", toplevel = false)
	public static final plaid.runtime.PlaidObject NewInstance;
	static {
		final plaid.runtime.PlaidState vAr1390$plaid;
		vAr1390$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1392$plaid;
		vAr1392$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1393$plaid) {
				plaid.runtime.PlaidScope vAr1394$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1394$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1391$plaid;
				vAr1391$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1391$plaid;
			}
		}
		);
		vAr1390$plaid.addMember("token",vAr1392$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "st", toplevel = false)
		final plaid.runtime.PlaidObject vAr1396$plaid;
		vAr1396$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1397$plaid) {
				plaid.runtime.PlaidScope vAr1398$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1398$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1395$plaid;
				vAr1395$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1395$plaid;
			}
		}
		);
		vAr1390$plaid.addMember("st",vAr1396$plaid);
		NewInstance = vAr1390$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.NewInstance")
	public static final plaid.runtime.PlaidTag NewInstance$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1399$plaid;
		final plaid.runtime.PlaidObject vAr1400$plaid;
		vAr1400$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1399$plaid = plaid.runtime.Util.toPlaidState(vAr1400$plaid);
		NewInstance$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.NewInstance", vAr1399$plaid);
	}
}
