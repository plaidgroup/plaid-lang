package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Assignment", toplevel = true)
public class Assignment{
	public static final java.util.List<plaid.runtime.utils.Import> vAr213$plaid;
	static {
		vAr213$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr213$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Assignment", toplevel = false)
	public static final plaid.runtime.PlaidObject Assignment;
	static {
		final plaid.runtime.PlaidState vAr214$plaid;
		vAr214$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr216$plaid;
		vAr216$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr217$plaid) {
				plaid.runtime.PlaidScope vAr218$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr218$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr215$plaid;
				vAr215$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr215$plaid;
			}
		}
		);
		vAr214$plaid.addMember("token",vAr216$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "target", toplevel = false)
		final plaid.runtime.PlaidObject vAr220$plaid;
		vAr220$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr221$plaid) {
				plaid.runtime.PlaidScope vAr222$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr222$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr219$plaid;
				vAr219$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr219$plaid;
			}
		}
		);
		vAr214$plaid.addMember("target",vAr220$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "field", toplevel = false)
		final plaid.runtime.PlaidObject vAr224$plaid;
		vAr224$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr225$plaid) {
				plaid.runtime.PlaidScope vAr226$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr226$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr223$plaid;
				vAr223$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr223$plaid;
			}
		}
		);
		vAr214$plaid.addMember("field",vAr224$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr228$plaid;
		vAr228$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr229$plaid) {
				plaid.runtime.PlaidScope vAr230$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr230$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr227$plaid;
				vAr227$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr227$plaid;
			}
		}
		);
		vAr214$plaid.addMember("value",vAr228$plaid);
		Assignment = vAr214$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Assignment")
	public static final plaid.runtime.PlaidTag Assignment$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr231$plaid;
		final plaid.runtime.PlaidObject vAr232$plaid;
		vAr232$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr231$plaid = plaid.runtime.Util.toPlaidState(vAr232$plaid);
		Assignment$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Assignment", vAr231$plaid);
	}
}
