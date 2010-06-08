package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Lambda", toplevel = true)
public class Lambda{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1248$plaid;
	static {
		vAr1248$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1248$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Lambda", toplevel = false)
	public static final plaid.runtime.PlaidObject Lambda;
	static {
		final plaid.runtime.PlaidState vAr1249$plaid;
		vAr1249$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1251$plaid;
		vAr1251$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1252$plaid) {
				final plaid.runtime.PlaidObject vAr1250$plaid;
				vAr1250$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1250$plaid;
			}
		}
		);
		vAr1249$plaid.addMember("token",vAr1251$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "x", toplevel = false)
		final plaid.runtime.PlaidObject vAr1254$plaid;
		vAr1254$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1255$plaid) {
				final plaid.runtime.PlaidObject vAr1253$plaid;
				vAr1253$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1253$plaid;
			}
		}
		);
		vAr1249$plaid.addMember("x",vAr1254$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "body", toplevel = false)
		final plaid.runtime.PlaidObject vAr1257$plaid;
		vAr1257$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1258$plaid) {
				final plaid.runtime.PlaidObject vAr1256$plaid;
				vAr1256$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1256$plaid;
			}
		}
		);
		vAr1249$plaid.addMember("body",vAr1257$plaid, true);
		Lambda = vAr1249$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Lambda")
	public static final plaid.runtime.PlaidTag Lambda$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1259$plaid;
		final plaid.runtime.PlaidObject vAr1260$plaid;
		vAr1260$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1259$plaid = plaid.runtime.Util.toPlaidState(vAr1260$plaid);
		Lambda$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Lambda", vAr1259$plaid);
	}
}
