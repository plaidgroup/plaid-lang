package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "FieldDecl", toplevel = true)
public class FieldDecl{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1253$plaid;
	static {
		vAr1253$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1253$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "FieldDecl", toplevel = false)
	public static final plaid.runtime.PlaidObject FieldDecl;
	static {
		final plaid.runtime.PlaidState vAr1254$plaid;
		vAr1254$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1256$plaid;
		vAr1256$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1257$plaid) {
				plaid.runtime.PlaidScope vAr1258$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1258$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1255$plaid;
				vAr1255$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1255$plaid;
			}
		}
		);
		vAr1254$plaid.addMember("token",vAr1256$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "f", toplevel = false)
		final plaid.runtime.PlaidObject vAr1260$plaid;
		vAr1260$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1261$plaid) {
				plaid.runtime.PlaidScope vAr1262$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1262$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1259$plaid;
				vAr1259$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1259$plaid;
			}
		}
		);
		vAr1254$plaid.addMember("f",vAr1260$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1264$plaid;
		vAr1264$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1265$plaid) {
				plaid.runtime.PlaidScope vAr1266$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1266$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1263$plaid;
				vAr1263$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1263$plaid;
			}
		}
		);
		vAr1254$plaid.addMember("e",vAr1264$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "abstractField", toplevel = false)
		final plaid.runtime.PlaidObject vAr1268$plaid;
		vAr1268$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1269$plaid) {
				plaid.runtime.PlaidScope vAr1270$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1270$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1267$plaid;
				vAr1267$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1267$plaid;
			}
		}
		);
		vAr1254$plaid.addMember("abstractField",vAr1268$plaid);
		FieldDecl = vAr1254$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.FieldDecl")
	public static final plaid.runtime.PlaidTag FieldDecl$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1271$plaid;
		final plaid.runtime.PlaidObject vAr1272$plaid;
		vAr1272$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1271$plaid = plaid.runtime.Util.toPlaidState(vAr1272$plaid);
		FieldDecl$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.FieldDecl", vAr1271$plaid);
	}
}
