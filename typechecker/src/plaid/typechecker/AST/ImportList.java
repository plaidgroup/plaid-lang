package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsState(name = "ImportList", toplevel = true)
public class ImportList{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1228$plaid;
	static {
		vAr1228$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1228$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1228$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "ImportList", toplevel = false)
	public static final plaid.runtime.PlaidObject ImportList;
	static {
		final plaid.runtime.PlaidState vAr1229$plaid;
		vAr1229$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "imports", toplevel = false)
		final plaid.runtime.PlaidObject vAr1231$plaid;
		vAr1231$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1232$plaid) {
				final plaid.runtime.PlaidObject vAr1230$plaid;
				vAr1230$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1230$plaid;
			}
		}
		);
		vAr1229$plaid.addMember("imports",vAr1231$plaid, true);
		@plaid.runtime.annotations.RepresentsField(name = "token", toplevel = false)
		final plaid.runtime.PlaidObject vAr1234$plaid;
		vAr1234$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1235$plaid) {
				final plaid.runtime.PlaidObject vAr1233$plaid;
				vAr1233$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr1233$plaid;
			}
		}
		);
		vAr1229$plaid.addMember("token",vAr1234$plaid, true);
		ImportList = vAr1229$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.typechecker.AST.ImportList")
	public static final plaid.runtime.PlaidTag ImportList$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr1236$plaid;
		final plaid.runtime.PlaidObject vAr1237$plaid;
		vAr1237$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTNode", global$c0pe);
		vAr1236$plaid = plaid.runtime.Util.toPlaidState(vAr1237$plaid);
		ImportList$Tag$plaid = plaid.runtime.Util.tag("plaid.typechecker.AST.ImportList", vAr1236$plaid);
	}
}
