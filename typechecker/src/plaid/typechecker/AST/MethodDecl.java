package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "MethodDecl", toplevel = true)
public class MethodDecl{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1293$plaid;
	static {
		vAr1293$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1293$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1293$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "MethodDecl", toplevel = false)
	public static final plaid.runtime.PlaidObject MethodDecl;
	static {
		final plaid.runtime.PlaidState vAr1294$plaid;
		vAr1294$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1296$plaid;
		vAr1296$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1297$plaid) {
				final plaid.runtime.PlaidObject vAr1295$plaid;
				vAr1295$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1295$plaid;
			}
		}
		);
		vAr1294$plaid.addMember("token",vAr1296$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "name", toplevel = false)
		final plaid.runtime.PlaidObject vAr1299$plaid;
		vAr1299$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1300$plaid) {
				final plaid.runtime.PlaidObject vAr1298$plaid;
				vAr1298$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1298$plaid;
			}
		}
		);
		vAr1294$plaid.addMember("name",vAr1299$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "body", toplevel = false)
		final plaid.runtime.PlaidObject vAr1302$plaid;
		vAr1302$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1303$plaid) {
				final plaid.runtime.PlaidObject vAr1301$plaid;
				vAr1301$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1301$plaid;
			}
		}
		);
		vAr1294$plaid.addMember("body",vAr1302$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "arg", toplevel = false)
		final plaid.runtime.PlaidObject vAr1305$plaid;
		vAr1305$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1306$plaid) {
				final plaid.runtime.PlaidObject vAr1304$plaid;
				vAr1304$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1304$plaid;
			}
		}
		);
		vAr1294$plaid.addMember("arg",vAr1305$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "abstractMethod", toplevel = false)
		final plaid.runtime.PlaidObject vAr1308$plaid;
		vAr1308$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1309$plaid) {
				final plaid.runtime.PlaidObject vAr1307$plaid;
				vAr1307$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1307$plaid;
			}
		}
		);
		vAr1294$plaid.addMember("abstractMethod",vAr1308$plaid, true);
		MethodDecl = vAr1294$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.MethodDecl")
	public static final plaid.runtime.PlaidTag MethodDecl$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1310$plaid;
		final plaid.runtime.PlaidObject vAr1311$plaid;
		vAr1311$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1310$plaid = plaid.runtime.Util.toPlaidState(vAr1311$plaid);
		MethodDecl$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.MethodDecl", vAr1310$plaid);
	}
}
