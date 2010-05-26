package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Assignment", toplevel = true)
public class Assignment{
	public static final java.util.List<plaid.runtime.utils.Import> vAr214$plaid;
	static {
		vAr214$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr214$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Assignment", toplevel = false)
	public static final plaid.runtime.PlaidObject Assignment;
	static {
		final plaid.runtime.PlaidState vAr215$plaid;
		vAr215$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr217$plaid;
		vAr217$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr218$plaid) {
				plaid.runtime.PlaidScope vAr219$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr219$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr216$plaid;
				vAr216$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr216$plaid;
			}
		}
		);
		vAr215$plaid.addMember("token",vAr217$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "target", toplevel = false)
		final plaid.runtime.PlaidObject vAr221$plaid;
		vAr221$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr222$plaid) {
				plaid.runtime.PlaidScope vAr223$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr223$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr220$plaid;
				vAr220$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr220$plaid;
			}
		}
		);
		vAr215$plaid.addMember("target",vAr221$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "field", toplevel = false)
		final plaid.runtime.PlaidObject vAr225$plaid;
		vAr225$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr226$plaid) {
				plaid.runtime.PlaidScope vAr227$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr227$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr224$plaid;
				vAr224$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr224$plaid;
			}
		}
		);
		vAr215$plaid.addMember("field",vAr225$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr229$plaid;
		vAr229$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr230$plaid) {
				plaid.runtime.PlaidScope vAr231$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr231$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr228$plaid;
				vAr228$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr228$plaid;
			}
		}
		);
		vAr215$plaid.addMember("value",vAr229$plaid);
		Assignment = vAr215$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Assignment")
	public static final plaid.runtime.PlaidTag Assignment$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr232$plaid;
		final plaid.runtime.PlaidObject vAr233$plaid;
		vAr233$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr232$plaid = plaid.runtime.Util.toPlaidState(vAr233$plaid);
		Assignment$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Assignment", vAr232$plaid);
	}
}
