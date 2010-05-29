package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "LetBinding", toplevel = true)
public class LetBinding{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1325$plaid;
	static {
		vAr1325$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1325$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "LetBinding", toplevel = false)
	public static final plaid.runtime.PlaidObject LetBinding;
	static {
		final plaid.runtime.PlaidState vAr1326$plaid;
		vAr1326$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1328$plaid;
		vAr1328$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1329$plaid) {
				plaid.runtime.PlaidScope vAr1330$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1330$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1327$plaid;
				vAr1327$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1327$plaid;
			}
		}
		);
		vAr1326$plaid.addMember("token",vAr1328$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "x", toplevel = false)
		final plaid.runtime.PlaidObject vAr1332$plaid;
		vAr1332$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1333$plaid) {
				plaid.runtime.PlaidScope vAr1334$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1334$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1331$plaid;
				vAr1331$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1331$plaid;
			}
		}
		);
		vAr1326$plaid.addMember("x",vAr1332$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "exp", toplevel = false)
		final plaid.runtime.PlaidObject vAr1336$plaid;
		vAr1336$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1337$plaid) {
				plaid.runtime.PlaidScope vAr1338$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1338$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1335$plaid;
				vAr1335$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1335$plaid;
			}
		}
		);
		vAr1326$plaid.addMember("exp",vAr1336$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "body", toplevel = false)
		final plaid.runtime.PlaidObject vAr1340$plaid;
		vAr1340$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1341$plaid) {
				plaid.runtime.PlaidScope vAr1342$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1342$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1339$plaid;
				vAr1339$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1339$plaid;
			}
		}
		);
		vAr1326$plaid.addMember("body",vAr1340$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "mutable", toplevel = false)
		final plaid.runtime.PlaidObject vAr1344$plaid;
		vAr1344$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1345$plaid) {
				plaid.runtime.PlaidScope vAr1346$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1346$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr1343$plaid;
				vAr1343$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1343$plaid;
			}
		}
		);
		vAr1326$plaid.addMember("mutable",vAr1344$plaid);
		LetBinding = vAr1326$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.LetBinding")
	public static final plaid.runtime.PlaidTag LetBinding$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1347$plaid;
		final plaid.runtime.PlaidObject vAr1348$plaid;
		vAr1348$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", current$c0pe);
		vAr1347$plaid = plaid.runtime.Util.toPlaidState(vAr1348$plaid);
		LetBinding$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.LetBinding", vAr1347$plaid);
	}
}
