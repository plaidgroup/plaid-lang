package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "LetBinding", toplevel = true)
public class LetBinding{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1261$plaid;
	static {
		vAr1261$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1261$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1261$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "LetBinding", toplevel = false)
	public static final plaid.runtime.PlaidObject LetBinding;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1262$plaid;
		vAr1262$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1264$plaid;
		vAr1264$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1265$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1263$plaid;
				vAr1263$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1263$plaid;
			}
		}
		);
		vAr1262$plaid.addMember("token",vAr1264$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "x", toplevel = false)
		final plaid.runtime.PlaidObject vAr1267$plaid;
		vAr1267$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1268$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1266$plaid;
				vAr1266$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1266$plaid;
			}
		}
		);
		vAr1262$plaid.addMember("x",vAr1267$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "exp", toplevel = false)
		final plaid.runtime.PlaidObject vAr1270$plaid;
		vAr1270$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1271$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1269$plaid;
				vAr1269$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1269$plaid;
			}
		}
		);
		vAr1262$plaid.addMember("exp",vAr1270$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "body", toplevel = false)
		final plaid.runtime.PlaidObject vAr1273$plaid;
		vAr1273$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1274$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1272$plaid;
				vAr1272$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1272$plaid;
			}
		}
		);
		vAr1262$plaid.addMember("body",vAr1273$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "mutable", toplevel = false)
		final plaid.runtime.PlaidObject vAr1276$plaid;
		vAr1276$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1277$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1275$plaid;
				vAr1275$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1275$plaid;
			}
		}
		);
		vAr1262$plaid.addMember("mutable",vAr1276$plaid, true);
		LetBinding = vAr1262$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.LetBinding")
	public static final plaid.runtime.PlaidTag LetBinding$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1278$plaid;
		final plaid.runtime.PlaidObject vAr1279$plaid;
		vAr1279$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1278$plaid = plaid.runtime.Util.toPlaidState(vAr1279$plaid);
		LetBinding$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.LetBinding", vAr1278$plaid);
	}
}
