package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Application", toplevel = true)
public class Application{
	public static final java.util.List<plaid.runtime.utils.Import> vAr198$plaid;
	static {
		vAr198$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr198$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Application", toplevel = false)
	public static final plaid.runtime.PlaidObject Application;
	static {
		final plaid.runtime.PlaidState vAr199$plaid;
		vAr199$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr201$plaid;
		vAr201$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr202$plaid) {
				plaid.runtime.PlaidScope vAr203$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr203$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr200$plaid;
				vAr200$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr200$plaid;
			}
		}
		);
		vAr199$plaid.addMember("token",vAr201$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "f", toplevel = false)
		final plaid.runtime.PlaidObject vAr205$plaid;
		vAr205$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr206$plaid) {
				plaid.runtime.PlaidScope vAr207$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr207$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr204$plaid;
				vAr204$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr204$plaid;
			}
		}
		);
		vAr199$plaid.addMember("f",vAr205$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "arg", toplevel = false)
		final plaid.runtime.PlaidObject vAr209$plaid;
		vAr209$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr210$plaid) {
				plaid.runtime.PlaidScope vAr211$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr211$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr208$plaid;
				vAr208$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr208$plaid;
			}
		}
		);
		vAr199$plaid.addMember("arg",vAr209$plaid);
		Application = vAr199$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Application")
	public static final plaid.runtime.PlaidTag Application$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr212$plaid;
		final plaid.runtime.PlaidObject vAr213$plaid;
		vAr213$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr212$plaid = plaid.runtime.Util.toPlaidState(vAr213$plaid);
		Application$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Application", vAr212$plaid);
	}
}
