package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Dereference", toplevel = true)
public class Dereference{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1237$plaid;
	static {
		vAr1237$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1237$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Dereference", toplevel = false)
	public static final plaid.runtime.PlaidObject Dereference;
	static {
		final plaid.runtime.PlaidState vAr1238$plaid;
		vAr1238$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1240$plaid;
		vAr1240$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1241$plaid) {
				plaid.runtime.PlaidScope vAr1242$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1242$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1239$plaid;
				vAr1239$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1239$plaid;
			}
		}
		);
		vAr1238$plaid.addMember("token",vAr1240$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "left", toplevel = false)
		final plaid.runtime.PlaidObject vAr1244$plaid;
		vAr1244$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1245$plaid) {
				plaid.runtime.PlaidScope vAr1246$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1246$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1243$plaid;
				vAr1243$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1243$plaid;
			}
		}
		);
		vAr1238$plaid.addMember("left",vAr1244$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "right", toplevel = false)
		final plaid.runtime.PlaidObject vAr1248$plaid;
		vAr1248$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1249$plaid) {
				plaid.runtime.PlaidScope vAr1250$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1250$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1247$plaid;
				vAr1247$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1247$plaid;
			}
		}
		);
		vAr1238$plaid.addMember("right",vAr1248$plaid);
		Dereference = vAr1238$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Dereference")
	public static final plaid.runtime.PlaidTag Dereference$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1251$plaid;
		final plaid.runtime.PlaidObject vAr1252$plaid;
		vAr1252$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1251$plaid = plaid.runtime.Util.toPlaidState(vAr1252$plaid);
		Dereference$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Dereference", vAr1251$plaid);
	}
}
