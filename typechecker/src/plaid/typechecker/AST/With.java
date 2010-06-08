package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "With", toplevel = true)
public class With{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1370$plaid;
	static {
		vAr1370$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1370$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1370$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "With", toplevel = false)
	public static final plaid.runtime.PlaidObject With;
	static {
		final plaid.runtime.PlaidState vAr1371$plaid;
		vAr1371$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1373$plaid;
		vAr1373$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1374$plaid) {
				final plaid.runtime.PlaidObject vAr1372$plaid;
				vAr1372$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1372$plaid;
			}
		}
		);
		vAr1371$plaid.addMember("token",vAr1373$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "s1", toplevel = false)
		final plaid.runtime.PlaidObject vAr1376$plaid;
		vAr1376$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1377$plaid) {
				final plaid.runtime.PlaidObject vAr1375$plaid;
				vAr1375$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1375$plaid;
			}
		}
		);
		vAr1371$plaid.addMember("s1",vAr1376$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "s2", toplevel = false)
		final plaid.runtime.PlaidObject vAr1379$plaid;
		vAr1379$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1380$plaid) {
				final plaid.runtime.PlaidObject vAr1378$plaid;
				vAr1378$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1378$plaid;
			}
		}
		);
		vAr1371$plaid.addMember("s2",vAr1379$plaid, true);
		With = vAr1371$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.With")
	public static final plaid.runtime.PlaidTag With$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1381$plaid;
		final plaid.runtime.PlaidObject vAr1382$plaid;
		vAr1382$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1381$plaid = plaid.runtime.Util.toPlaidState(vAr1382$plaid);
		With$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.With", vAr1381$plaid);
	}
}
