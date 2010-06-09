package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "FieldDecl", toplevel = true)
public class FieldDecl{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1202$plaid;
	static {
		vAr1202$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1202$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "FieldDecl", toplevel = false)
	public static final plaid.runtime.PlaidObject FieldDecl;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1203$plaid;
		vAr1203$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1205$plaid;
		vAr1205$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1206$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1204$plaid;
				vAr1204$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1204$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("token",vAr1205$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "f", toplevel = false)
		final plaid.runtime.PlaidObject vAr1208$plaid;
		vAr1208$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1209$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1207$plaid;
				vAr1207$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1207$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("f",vAr1208$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1211$plaid;
		vAr1211$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1212$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1210$plaid;
				vAr1210$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1210$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("e",vAr1211$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "abstractField", toplevel = false)
		final plaid.runtime.PlaidObject vAr1214$plaid;
		vAr1214$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1215$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1213$plaid;
				vAr1213$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1213$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("abstractField",vAr1214$plaid, true);
		FieldDecl = vAr1203$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.FieldDecl")
	public static final plaid.runtime.PlaidTag FieldDecl$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1216$plaid;
		final plaid.runtime.PlaidObject vAr1217$plaid;
		vAr1217$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1216$plaid = plaid.runtime.Util.toPlaidState(vAr1217$plaid);
		FieldDecl$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.FieldDecl", vAr1216$plaid);
	}
}
