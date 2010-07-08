package plaid.apps.webserver;
@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = true)
public class main{
	public static final java.util.List<plaid.runtime.utils.Import> vAr494$plaid;
	static {
		vAr494$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr494$plaid",vAr494$plaid);
		vAr494$plaid.add(new plaid.runtime.utils.Import("java.lang.*"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("java.io.BufferedReader"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("java.io.InputStreamReader"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("java.io.PrintWriter"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("java.net.ServerSocket"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("java.net.Socket"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
		vAr494$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.apps.webserver",vAr494$plaid);
	public static void main(String[] args) {
		plaid.lang.Sys.setArgs(args,0);
		plaid.runtime.PlaidRuntime.getRuntime().init();
		plaid.runtime.PlaidRuntime.getRuntime().enterCall(plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit(),"main");
		main_func.invoke(plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit());
		plaid.runtime.PlaidRuntime.getRuntime().leaveCall(plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit(),"main");
		plaid.runtime.PlaidRuntime.getRuntime().shutdown();
	}
	@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = false)
	public static final plaid.runtime.PlaidMethod main_func;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		main_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr412$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				local$c0pe.insert("vAr412$plaid", vAr412$plaid, false);
				plaid.runtime.PlaidObject vAr493$plaid=null;
				{
					final plaid.runtime.PlaidObject z;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",65,17);
					final plaid.runtime.PlaidState vAr495$plaid;
					final plaid.runtime.PlaidObject vAr496$plaid;
					vAr496$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("server", global$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr496$plaid",vAr496$plaid);
					vAr495$plaid = plaid.runtime.Util.toPlaidState(vAr496$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr495$plaid",vAr495$plaid);
					z = vAr495$plaid.instantiate();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("z",z);
					local$c0pe.insert("z", z, false);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,11);
					final plaid.runtime.PlaidObject vAr497$plaid;
					final plaid.runtime.PlaidObject vAr498$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,11);
					final plaid.runtime.PlaidObject vAr499$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,9);
					vAr499$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("z", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr499$plaid",vAr499$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,9);
					vAr497$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("start", vAr499$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr497$plaid",vAr497$plaid);
					vAr498$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr498$plaid",vAr498$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,11);
					vAr493$plaid = plaid.runtime.Util.call(vAr497$plaid, vAr498$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr493$plaid",vAr493$plaid);
				}
				return vAr493$plaid;
			}
		}
		);
	}
}
