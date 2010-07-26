package plaid.apps.webserver;
@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = true)
public class main{
	public static final java.util.List<plaid.runtime.utils.Import> vAr327$plaid;
	static {
		vAr327$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr327$plaid",vAr327$plaid);
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.net.ServerSocket"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.lang.System"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.net.Socket"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.net.ServerSocket"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.net.InetAddress"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.io.InputStream"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.io.OutputStream"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("java.lang.System"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
		vAr327$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.apps.webserver",vAr327$plaid);
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
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		main_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr102$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				if (vAr102$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("vAr102$plaid", vAr102$plaid, false);
				plaid.runtime.PlaidObject vAr326$plaid=null;
				{
					final plaid.runtime.PlaidObject httpServer;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",89,26);
					final plaid.runtime.PlaidState vAr328$plaid;
					final plaid.runtime.PlaidObject vAr329$plaid;
					vAr329$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("HttpServer", global$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr329$plaid",vAr329$plaid);
					vAr328$plaid = plaid.runtime.Util.toPlaidState(vAr329$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr328$plaid",vAr328$plaid);
					httpServer = vAr328$plaid.instantiate();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("httpServer",httpServer);
					if (httpServer instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("httpServer", httpServer, false);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",90,20);
					final plaid.runtime.PlaidObject vAr330$plaid;
					final plaid.runtime.PlaidObject vAr331$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",90,20);
					final plaid.runtime.PlaidObject vAr332$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",90,9);
					vAr332$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("httpServer", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr332$plaid",vAr332$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",90,9);
					vAr330$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("start", vAr332$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr330$plaid",vAr330$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",90,26);
					vAr331$plaid = plaid.runtime.Util.integer(8080);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr331$plaid",vAr331$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/plaidapps/apps/webserver/HttpServer.plaid",90,20);
					vAr326$plaid = plaid.runtime.Util.call(vAr330$plaid, vAr331$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr326$plaid",vAr326$plaid);
				}
				return vAr326$plaid;
			}
		}
		);
	}
}
