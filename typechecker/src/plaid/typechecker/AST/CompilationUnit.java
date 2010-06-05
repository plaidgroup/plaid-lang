package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "CompilationUnit", toplevel = true)
public class CompilationUnit{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1202$plaid;
	static {
		vAr1202$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1202$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "CompilationUnit", toplevel = false)
	public static final plaid.runtime.PlaidObject CompilationUnit;
	static {
		final plaid.runtime.PlaidState vAr1203$plaid;
		vAr1203$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "decls", toplevel = false)
		final plaid.runtime.PlaidObject vAr1205$plaid;
		vAr1205$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1206$plaid) {
				plaid.runtime.PlaidScope vAr1207$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1207$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1204$plaid;
				vAr1204$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1204$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("decls",vAr1205$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "packageName", toplevel = false)
		final plaid.runtime.PlaidObject vAr1209$plaid;
		vAr1209$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1210$plaid) {
				plaid.runtime.PlaidScope vAr1211$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1211$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1208$plaid;
				vAr1208$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1208$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("packageName",vAr1209$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "imports", toplevel = false)
		final plaid.runtime.PlaidObject vAr1213$plaid;
		vAr1213$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1214$plaid) {
				plaid.runtime.PlaidScope vAr1215$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1215$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1212$plaid;
				vAr1212$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1212$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("imports",vAr1213$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "sourceFile", toplevel = false)
		final plaid.runtime.PlaidObject vAr1217$plaid;
		vAr1217$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1218$plaid) {
				plaid.runtime.PlaidScope vAr1219$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1219$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1216$plaid;
				final plaid.runtime.PlaidObject vAr1220$plaid;
				final plaid.runtime.PlaidObject vAr1221$plaid;
				final plaid.runtime.PlaidObject vAr1222$plaid;
				vAr1222$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("File", current$c0pe);
				vAr1220$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("create", vAr1222$plaid);
				vAr1221$plaid = plaid.runtime.Util.string(">>UNKNOWN<<");
				vAr1216$plaid = plaid.runtime.Util.call(vAr1220$plaid, vAr1221$plaid);
				return vAr1216$plaid;
			}
		}
		);
		vAr1203$plaid.addMember("sourceFile",vAr1217$plaid);
		CompilationUnit = vAr1203$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.CompilationUnit")
	public static final plaid.runtime.PlaidTag CompilationUnit$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1223$plaid;
		final plaid.runtime.PlaidObject vAr1224$plaid;
		vAr1224$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1223$plaid = plaid.runtime.Util.toPlaidState(vAr1224$plaid);
		CompilationUnit$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.CompilationUnit", vAr1223$plaid);
	}
}
