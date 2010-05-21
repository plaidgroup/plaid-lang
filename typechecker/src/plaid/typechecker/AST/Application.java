package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Application", toplevel = true)
public class Application{
	public static final java.util.List<plaid.runtime.utils.Import> vAr197$plaid;
	static {
		vAr197$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr197$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Application", toplevel = false)
	public static final plaid.runtime.PlaidObject Application;
	static {
		final plaid.runtime.PlaidState vAr198$plaid;
		vAr198$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr200$plaid;
		vAr200$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr201$plaid) {
				plaid.runtime.PlaidScope vAr202$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr202$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr199$plaid;
				vAr199$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr199$plaid;
			}
		}
		);
		vAr198$plaid.addMember("token",vAr200$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "f", toplevel = false)
		final plaid.runtime.PlaidObject vAr204$plaid;
		vAr204$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr205$plaid) {
				plaid.runtime.PlaidScope vAr206$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr206$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr203$plaid;
				vAr203$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr203$plaid;
			}
		}
		);
		vAr198$plaid.addMember("f",vAr204$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "arg", toplevel = false)
		final plaid.runtime.PlaidObject vAr208$plaid;
		vAr208$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr209$plaid) {
				plaid.runtime.PlaidScope vAr210$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr210$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr207$plaid;
				vAr207$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr207$plaid;
			}
		}
		);
		vAr198$plaid.addMember("arg",vAr208$plaid);
		Application = vAr198$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Application")
	public static final plaid.runtime.PlaidTag Application$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr211$plaid;
		final plaid.runtime.PlaidObject vAr212$plaid;
		vAr212$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr211$plaid = plaid.runtime.Util.toPlaidState(vAr212$plaid);
		Application$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Application", vAr211$plaid);
	}
}
