package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "CompilationUnit", toplevel = true)
public class CompilationUnit{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1160$plaid;
	static {
		vAr1160$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1160$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1160$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "CompilationUnit", toplevel = false)
	public static final plaid.runtime.PlaidObject CompilationUnit;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1161$plaid;
		vAr1161$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "decls", toplevel = false)
		final plaid.runtime.PlaidObject vAr1163$plaid;
		vAr1163$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1164$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1162$plaid;
				vAr1162$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1162$plaid;
			}
		}
		);
		vAr1161$plaid.addMember("decls",vAr1163$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "packageName", toplevel = false)
		final plaid.runtime.PlaidObject vAr1166$plaid;
		vAr1166$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1167$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1165$plaid;
				vAr1165$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1165$plaid;
			}
		}
		);
		vAr1161$plaid.addMember("packageName",vAr1166$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "imports", toplevel = false)
		final plaid.runtime.PlaidObject vAr1169$plaid;
		vAr1169$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1170$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1168$plaid;
				vAr1168$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1168$plaid;
			}
		}
		);
		vAr1161$plaid.addMember("imports",vAr1169$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "sourceFile", toplevel = false)
		final plaid.runtime.PlaidObject vAr1172$plaid;
		vAr1172$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1173$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1171$plaid;
				final plaid.runtime.PlaidObject vAr1174$plaid;
				final plaid.runtime.PlaidObject vAr1175$plaid;
				final plaid.runtime.PlaidObject vAr1176$plaid;
				vAr1176$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("File", local$c0pe);
				vAr1174$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("create", vAr1176$plaid);
				vAr1175$plaid = plaid.runtime.Util.string(">>UNKNOWN<<");
				vAr1171$plaid = plaid.runtime.Util.call(vAr1174$plaid, vAr1175$plaid);
				return vAr1171$plaid;
			}
		}
		);
		vAr1161$plaid.addMember("sourceFile",vAr1172$plaid, true);
		CompilationUnit = vAr1161$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.CompilationUnit")
	public static final plaid.runtime.PlaidTag CompilationUnit$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1177$plaid;
		final plaid.runtime.PlaidObject vAr1178$plaid;
		vAr1178$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1177$plaid = plaid.runtime.Util.toPlaidState(vAr1178$plaid);
		CompilationUnit$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.CompilationUnit", vAr1177$plaid);
	}
}
