package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "StateDecl", toplevel = true)
public class StateDecl{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1332$plaid;
	static {
		vAr1332$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1332$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1332$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "StateDecl", toplevel = false)
	public static final plaid.runtime.PlaidObject StateDecl;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1333$plaid;
		vAr1333$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1335$plaid;
		vAr1335$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1336$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1334$plaid;
				vAr1334$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1334$plaid;
			}
		}
		);
		vAr1333$plaid.addMember("token",vAr1335$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "name", toplevel = false)
		final plaid.runtime.PlaidObject vAr1338$plaid;
		vAr1338$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1339$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1337$plaid;
				vAr1337$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1337$plaid;
			}
		}
		);
		vAr1333$plaid.addMember("name",vAr1338$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "stateDef", toplevel = false)
		final plaid.runtime.PlaidObject vAr1341$plaid;
		vAr1341$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1342$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1340$plaid;
				vAr1340$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1340$plaid;
			}
		}
		);
		vAr1333$plaid.addMember("stateDef",vAr1341$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "caseOf", toplevel = false)
		final plaid.runtime.PlaidObject vAr1344$plaid;
		vAr1344$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1345$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1343$plaid;
				vAr1343$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1343$plaid;
			}
		}
		);
		vAr1333$plaid.addMember("caseOf",vAr1344$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "isCaseOf", toplevel = false)
		final plaid.runtime.PlaidObject vAr1347$plaid;
		vAr1347$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1348$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1346$plaid;
				vAr1346$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1346$plaid;
			}
		}
		);
		vAr1333$plaid.addMember("isCaseOf",vAr1347$plaid, true);
		StateDecl = vAr1333$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.StateDecl")
	public static final plaid.runtime.PlaidTag StateDecl$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1349$plaid;
		final plaid.runtime.PlaidObject vAr1350$plaid;
		vAr1350$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1349$plaid = plaid.runtime.Util.toPlaidState(vAr1350$plaid);
		StateDecl$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.StateDecl", vAr1349$plaid);
	}
}
