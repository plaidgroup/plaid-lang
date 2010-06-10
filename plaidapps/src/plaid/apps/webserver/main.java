package plaid.apps.webserver;
@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = true)
public class main{
	public static final java.util.List<plaid.runtime.utils.Import> vAr95$plaid;
	static {
		vAr95$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr95$plaid",vAr95$plaid);
		vAr95$plaid.add(new plaid.runtime.utils.Import("java.lang.*"));
		vAr95$plaid.add(new plaid.runtime.utils.Import("java.io.BufferedReader"));
		vAr95$plaid.add(new plaid.runtime.utils.Import("java.io.InputStreamReader"));
		vAr95$plaid.add(new plaid.runtime.utils.Import("java.io.PrintWriter"));
		vAr95$plaid.add(new plaid.runtime.utils.Import("java.net.ServerSocket"));
		vAr95$plaid.add(new plaid.runtime.utils.Import("java.net.Socket"));
		vAr95$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.apps.webserver",vAr95$plaid);
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
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr13$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				local$c0pe.insert("vAr13$plaid", vAr13$plaid, false);
				plaid.runtime.PlaidObject vAr94$plaid=null;
				{
					final plaid.runtime.PlaidObject z;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",65,17);
					final plaid.runtime.PlaidState vAr96$plaid;
					final plaid.runtime.PlaidObject vAr97$plaid;
					vAr97$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("server", global$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr97$plaid",vAr97$plaid);
					vAr96$plaid = plaid.runtime.Util.toPlaidState(vAr97$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr96$plaid",vAr96$plaid);
					z = vAr96$plaid.instantiate();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("z",z);
					local$c0pe.insert("z", z, false);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,11);
					final plaid.runtime.PlaidObject vAr98$plaid;
					final plaid.runtime.PlaidObject vAr99$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,11);
					final plaid.runtime.PlaidObject vAr100$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,9);
					vAr100$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("z", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr100$plaid",vAr100$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,9);
					vAr98$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("start", vAr100$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr98$plaid",vAr98$plaid);
					vAr99$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr99$plaid",vAr99$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",66,11);
					vAr94$plaid = plaid.runtime.Util.call(vAr98$plaid, vAr99$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr94$plaid",vAr94$plaid);
				}
				return vAr94$plaid;
			}
		}
		);
	}
}
