package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "Match", toplevel = true)
public class Match{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1280$plaid;
	static {
		vAr1280$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1280$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1280$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Match", toplevel = false)
	public static final plaid.runtime.PlaidObject Match;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr1281$plaid;
		vAr1281$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1283$plaid;
		vAr1283$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1284$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1282$plaid;
				vAr1282$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1282$plaid;
			}
		}
		);
		vAr1281$plaid.addMember("token",vAr1283$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "e", toplevel = false)
		final plaid.runtime.PlaidObject vAr1286$plaid;
		vAr1286$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1287$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1285$plaid;
				vAr1285$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1285$plaid;
			}
		}
		);
		vAr1281$plaid.addMember("e",vAr1286$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "caseList", toplevel = false)
		final plaid.runtime.PlaidObject vAr1289$plaid;
		vAr1289$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1290$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr1288$plaid;
				vAr1288$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1288$plaid;
			}
		}
		);
		vAr1281$plaid.addMember("caseList",vAr1289$plaid, true);
		Match = vAr1281$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.Match")
	public static final plaid.runtime.PlaidTag Match$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1291$plaid;
		final plaid.runtime.PlaidObject vAr1292$plaid;
		vAr1292$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1291$plaid = plaid.runtime.Util.toPlaidState(vAr1292$plaid);
		Match$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.Match", vAr1291$plaid);
	}
}
