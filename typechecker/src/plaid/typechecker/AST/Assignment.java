package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Assignment", toplevel = true)
public class Assignment{
	public static final java.util.List<plaid.runtime.utils.Import> vAr265$plaid;
	static {
		vAr265$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr265$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr265$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Assignment", toplevel = false)
	public static final plaid.runtime.PlaidObject Assignment;
	static {
		final plaid.runtime.PlaidState vAr266$plaid;
		vAr266$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr268$plaid;
		vAr268$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr269$plaid) {
				final plaid.runtime.PlaidObject vAr267$plaid;
				vAr267$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr267$plaid;
			}
		}
		);
		vAr266$plaid.addMember("token",vAr268$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "target", toplevel = false)
		final plaid.runtime.PlaidObject vAr271$plaid;
		vAr271$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr272$plaid) {
				final plaid.runtime.PlaidObject vAr270$plaid;
				vAr270$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr270$plaid;
			}
		}
		);
		vAr266$plaid.addMember("target",vAr271$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "field", toplevel = false)
		final plaid.runtime.PlaidObject vAr274$plaid;
		vAr274$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr275$plaid) {
				final plaid.runtime.PlaidObject vAr273$plaid;
				vAr273$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr273$plaid;
			}
		}
		);
		vAr266$plaid.addMember("field",vAr274$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr277$plaid;
		vAr277$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr278$plaid) {
				final plaid.runtime.PlaidObject vAr276$plaid;
				vAr276$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr276$plaid;
			}
		}
		);
		vAr266$plaid.addMember("value",vAr277$plaid, true);
		Assignment = vAr266$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Assignment")
	public static final plaid.runtime.PlaidTag Assignment$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr279$plaid;
		final plaid.runtime.PlaidObject vAr280$plaid;
		vAr280$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr279$plaid = plaid.runtime.Util.toPlaidState(vAr280$plaid);
		Assignment$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Assignment", vAr279$plaid);
	}
}
