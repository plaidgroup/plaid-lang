package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "NewInstance", toplevel = true)
public class NewInstance{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1312$plaid;
	static {
		vAr1312$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1312$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1312$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "NewInstance", toplevel = false)
	public static final plaid.runtime.PlaidObject NewInstance;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1313$plaid;
		vAr1313$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1315$plaid;
		vAr1315$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1316$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1314$plaid;
				vAr1314$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1314$plaid;
			}
		}
		);
		vAr1313$plaid.addMember("token",vAr1315$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "st", toplevel = false)
		final plaid.runtime.PlaidObject vAr1318$plaid;
		vAr1318$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1319$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1317$plaid;
				vAr1317$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1317$plaid;
			}
		}
		);
		vAr1313$plaid.addMember("st",vAr1318$plaid, true);
		NewInstance = vAr1313$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.NewInstance")
	public static final plaid.runtime.PlaidTag NewInstance$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1320$plaid;
		final plaid.runtime.PlaidObject vAr1321$plaid;
		vAr1321$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1320$plaid = plaid.runtime.Util.toPlaidState(vAr1321$plaid);
		NewInstance$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.NewInstance", vAr1320$plaid);
	}
}
