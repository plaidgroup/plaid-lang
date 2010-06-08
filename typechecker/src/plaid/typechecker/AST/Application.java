package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Application", toplevel = true)
public class Application{
	public static final java.util.List<plaid.runtime.utils.Import> vAr252$plaid;
	static {
		vAr252$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr252$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr252$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Application", toplevel = false)
	public static final plaid.runtime.PlaidObject Application;
	static {
		final plaid.runtime.PlaidState vAr253$plaid;
		vAr253$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr255$plaid;
		vAr255$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr256$plaid) {
				final plaid.runtime.PlaidObject vAr254$plaid;
				vAr254$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr254$plaid;
			}
		}
		);
		vAr253$plaid.addMember("token",vAr255$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "f", toplevel = false)
		final plaid.runtime.PlaidObject vAr258$plaid;
		vAr258$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr259$plaid) {
				final plaid.runtime.PlaidObject vAr257$plaid;
				vAr257$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr257$plaid;
			}
		}
		);
		vAr253$plaid.addMember("f",vAr258$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "arg", toplevel = false)
		final plaid.runtime.PlaidObject vAr261$plaid;
		vAr261$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr262$plaid) {
				final plaid.runtime.PlaidObject vAr260$plaid;
				vAr260$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr260$plaid;
			}
		}
		);
		vAr253$plaid.addMember("arg",vAr261$plaid, true);
		Application = vAr253$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Application")
	public static final plaid.runtime.PlaidTag Application$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr263$plaid;
		final plaid.runtime.PlaidObject vAr264$plaid;
		vAr264$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr263$plaid = plaid.runtime.Util.toPlaidState(vAr264$plaid);
		Application$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Application", vAr263$plaid);
	}
}
