package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "MethodDecl", toplevel = true)
public class MethodDecl{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1365$plaid;
	static {
		vAr1365$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1365$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "MethodDecl", toplevel = false)
	public static final plaid.runtime.PlaidObject MethodDecl;
	static {
		final plaid.runtime.PlaidState vAr1366$plaid;
		vAr1366$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1368$plaid;
		vAr1368$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1369$plaid) {
				plaid.runtime.PlaidScope vAr1370$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1370$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1367$plaid;
				vAr1367$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1367$plaid;
			}
		}
		);
		vAr1366$plaid.addMember("token",vAr1368$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "name", toplevel = false)
		final plaid.runtime.PlaidObject vAr1372$plaid;
		vAr1372$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1373$plaid) {
				plaid.runtime.PlaidScope vAr1374$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1374$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1371$plaid;
				vAr1371$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1371$plaid;
			}
		}
		);
		vAr1366$plaid.addMember("name",vAr1372$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "body", toplevel = false)
		final plaid.runtime.PlaidObject vAr1376$plaid;
		vAr1376$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1377$plaid) {
				plaid.runtime.PlaidScope vAr1378$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1378$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1375$plaid;
				vAr1375$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1375$plaid;
			}
		}
		);
		vAr1366$plaid.addMember("body",vAr1376$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "arg", toplevel = false)
		final plaid.runtime.PlaidObject vAr1380$plaid;
		vAr1380$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1381$plaid) {
				plaid.runtime.PlaidScope vAr1382$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1382$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1379$plaid;
				vAr1379$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1379$plaid;
			}
		}
		);
		vAr1366$plaid.addMember("arg",vAr1380$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "abstractMethod", toplevel = false)
		final plaid.runtime.PlaidObject vAr1384$plaid;
		vAr1384$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1385$plaid) {
				plaid.runtime.PlaidScope vAr1386$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1386$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1383$plaid;
				vAr1383$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1383$plaid;
			}
		}
		);
		vAr1366$plaid.addMember("abstractMethod",vAr1384$plaid);
		MethodDecl = vAr1366$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.MethodDecl")
	public static final plaid.runtime.PlaidTag MethodDecl$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1387$plaid;
		final plaid.runtime.PlaidObject vAr1388$plaid;
		vAr1388$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1387$plaid = plaid.runtime.Util.toPlaidState(vAr1388$plaid);
		MethodDecl$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.MethodDecl", vAr1387$plaid);
	}
}
