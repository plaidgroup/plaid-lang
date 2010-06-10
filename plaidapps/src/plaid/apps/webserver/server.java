package plaid.apps.webserver;
@plaid.runtime.annotations.RepresentsState(name = "server", toplevel = true)
public class server{
	public static final java.util.List<plaid.runtime.utils.Import> vAr14$plaid;
	static {
		vAr14$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr14$plaid",vAr14$plaid);
		vAr14$plaid.add(new plaid.runtime.utils.Import("java.lang.*"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("java.io.BufferedReader"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("java.io.InputStreamReader"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("java.io.PrintWriter"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("java.net.ServerSocket"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("java.net.Socket"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.apps.webserver",vAr14$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "server", toplevel = false)
	public static final plaid.runtime.PlaidObject server;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr15$plaid;
		vAr15$plaid = plaid.runtime.Util.newState();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr15$plaid",vAr15$plaid);
		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,13);
		@plaid.runtime.annotations.RepresentsField(name = "serverSocket", toplevel = false)
		final plaid.runtime.PlaidObject vAr17$plaid;
		vAr17$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr18$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr16$plaid;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,50);
				final plaid.runtime.PlaidObject vAr19$plaid;
				final plaid.runtime.PlaidObject vAr20$plaid;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,50);
				final plaid.runtime.PlaidObject vAr21$plaid;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,37);
				final plaid.runtime.PlaidObject vAr22$plaid;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,33);
				final plaid.runtime.PlaidObject vAr23$plaid;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,28);
				vAr23$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr23$plaid",vAr23$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,28);
				vAr22$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("net", vAr23$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr22$plaid",vAr22$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,33);
				vAr21$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ServerSocket", vAr22$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr21$plaid",vAr21$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,37);
				vAr19$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr21$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr19$plaid",vAr19$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,54);
				vAr20$plaid = plaid.runtime.Util.integer(8080);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr20$plaid",vAr20$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",31,50);
				vAr16$plaid = plaid.runtime.Util.call(vAr19$plaid, vAr20$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr16$plaid",vAr16$plaid);
				return vAr16$plaid;
			}
		}
		);
		vAr15$plaid.addMember("serverSocket",vAr17$plaid, true);
		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",33,9);
		@plaid.runtime.annotations.RepresentsMethod(name = "start", toplevel = false)
		final plaid.runtime.PlaidObject vAr24$plaid;
		vAr24$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr12$plaid) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				local$c0pe.insert("vAr12$plaid", vAr12$plaid, false);
				local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr25$plaid=null;
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr12$plaid",vAr12$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,28);
				{
					final plaid.runtime.PlaidObject vAr11$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,28);
					final plaid.runtime.PlaidObject vAr26$plaid;
					final plaid.runtime.PlaidObject vAr27$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,28);
					final plaid.runtime.PlaidObject vAr28$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,24);
					final plaid.runtime.PlaidObject vAr29$plaid;
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,17);
					vAr29$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("System", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr29$plaid",vAr29$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,17);
					vAr28$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr29$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr28$plaid",vAr28$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,24);
					vAr26$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr28$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr26$plaid",vAr26$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,36);
					vAr27$plaid = plaid.runtime.Util.string("Webserver starting up on port 8080");
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr27$plaid",vAr27$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",34,28);
					vAr11$plaid = plaid.runtime.Util.call(vAr26$plaid, vAr27$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr11$plaid",vAr11$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,28);
					{
						final plaid.runtime.PlaidObject vAr10$plaid;
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,28);
						final plaid.runtime.PlaidObject vAr30$plaid;
						final plaid.runtime.PlaidObject vAr31$plaid;
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,28);
						final plaid.runtime.PlaidObject vAr32$plaid;
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,24);
						final plaid.runtime.PlaidObject vAr33$plaid;
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,17);
						vAr33$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("System", local$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr33$plaid",vAr33$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,17);
						vAr32$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr33$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr32$plaid",vAr32$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,24);
						vAr30$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr32$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr30$plaid",vAr30$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,36);
						vAr31$plaid = plaid.runtime.Util.string("press ctrl-c to exit");
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr31$plaid",vAr31$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",35,28);
						vAr10$plaid = plaid.runtime.Util.call(vAr30$plaid, vAr31$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr10$plaid",vAr10$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,28);
						{
							final plaid.runtime.PlaidObject vAr9$plaid;
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,28);
							final plaid.runtime.PlaidObject vAr34$plaid;
							final plaid.runtime.PlaidObject vAr35$plaid;
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,28);
							final plaid.runtime.PlaidObject vAr36$plaid;
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,24);
							final plaid.runtime.PlaidObject vAr37$plaid;
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,17);
							vAr37$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("System", local$c0pe);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr37$plaid",vAr37$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,17);
							vAr36$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr37$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr36$plaid",vAr36$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,24);
							vAr34$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr36$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr34$plaid",vAr34$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,36);
							vAr35$plaid = plaid.runtime.Util.string("Waiting for connection");
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr35$plaid",vAr35$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",36,28);
							vAr9$plaid = plaid.runtime.Util.call(vAr34$plaid, vAr35$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr9$plaid",vAr9$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,17);
							final plaid.runtime.PlaidObject vAr38$plaid;
							final plaid.runtime.PlaidObject vAr39$plaid;
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,17);
							final plaid.runtime.PlaidObject vAr40$plaid;
							final plaid.runtime.PlaidObject vAr41$plaid;
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,17);
							vAr40$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("while$plaid", local$c0pe);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr40$plaid",vAr40$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,24);
							vAr41$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr0$plaid) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
									local$c0pe.insert("vAr0$plaid", vAr0$plaid, false);
									plaid.runtime.PlaidObject vAr42$plaid=null;
									plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,24);
									vAr42$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
									plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr42$plaid",vAr42$plaid);
									return vAr42$plaid;
								}
							}
							);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,17);
							vAr38$plaid = plaid.runtime.Util.call(vAr40$plaid, vAr41$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr38$plaid",vAr38$plaid);
							vAr39$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr8$plaid) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
									local$c0pe.insert("vAr8$plaid", vAr8$plaid, false);
									plaid.runtime.PlaidObject vAr43$plaid=null;
									{
										final plaid.runtime.PlaidObject remote;
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,56);
										final plaid.runtime.PlaidObject vAr44$plaid;
										final plaid.runtime.PlaidObject vAr45$plaid;
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,56);
										final plaid.runtime.PlaidObject vAr46$plaid;
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,43);
										final plaid.runtime.PlaidObject vAr47$plaid;
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,38);
										vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
										plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr47$plaid",vAr47$plaid);
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,38);
										vAr46$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("serverSocket", vAr47$plaid);
										plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr46$plaid",vAr46$plaid);
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,43);
										vAr44$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("accept", vAr46$plaid);
										plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr44$plaid",vAr44$plaid);
										vAr45$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr45$plaid",vAr45$plaid);
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",40,56);
										remote = plaid.runtime.Util.call(vAr44$plaid, vAr45$plaid);
										plaid.runtime.PlaidRuntime.getRuntime().updateVar("remote",remote);
										local$c0pe.insert("remote", remote, false);
										plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,36);
										{
											final plaid.runtime.PlaidObject vAr7$plaid;
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,36);
											final plaid.runtime.PlaidObject vAr48$plaid;
											final plaid.runtime.PlaidObject vAr49$plaid;
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,36);
											final plaid.runtime.PlaidObject vAr50$plaid;
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,32);
											final plaid.runtime.PlaidObject vAr51$plaid;
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,25);
											vAr51$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("System", local$c0pe);
											plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr51$plaid",vAr51$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,25);
											vAr50$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr51$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr50$plaid",vAr50$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,32);
											vAr48$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr50$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr48$plaid",vAr48$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,44);
											vAr49$plaid = plaid.runtime.Util.string("Connection, sending data.");
											plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr49$plaid",vAr49$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",43,36);
											vAr7$plaid = plaid.runtime.Util.call(vAr48$plaid, vAr49$plaid);
											plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr7$plaid",vAr7$plaid);
											{
												final plaid.runtime.PlaidObject in;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,57);
												final plaid.runtime.PlaidObject vAr52$plaid;
												final plaid.runtime.PlaidObject vAr53$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,57);
												final plaid.runtime.PlaidObject vAr54$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,42);
												final plaid.runtime.PlaidObject vAr55$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,39);
												final plaid.runtime.PlaidObject vAr56$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,34);
												vAr56$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr56$plaid",vAr56$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,34);
												vAr55$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr56$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr55$plaid",vAr55$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,39);
												vAr54$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("BufferedReader", vAr55$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr54$plaid",vAr54$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,42);
												vAr52$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr54$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr52$plaid",vAr52$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,87);
												final plaid.runtime.PlaidObject vAr57$plaid;
												final plaid.runtime.PlaidObject vAr58$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,87);
												final plaid.runtime.PlaidObject vAr59$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,69);
												final plaid.runtime.PlaidObject vAr60$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,66);
												final plaid.runtime.PlaidObject vAr61$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,61);
												vAr61$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr61$plaid",vAr61$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,61);
												vAr60$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr61$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr60$plaid",vAr60$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,66);
												vAr59$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("InputStreamReader", vAr60$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr59$plaid",vAr59$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,69);
												vAr57$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr59$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr57$plaid",vAr57$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,98);
												final plaid.runtime.PlaidObject vAr62$plaid;
												final plaid.runtime.PlaidObject vAr63$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,98);
												final plaid.runtime.PlaidObject vAr64$plaid;
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,91);
												vAr64$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("remote", local$c0pe);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr64$plaid",vAr64$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,91);
												vAr62$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("getInputStream", vAr64$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr62$plaid",vAr62$plaid);
												vAr63$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr63$plaid",vAr63$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,98);
												vAr58$plaid = plaid.runtime.Util.call(vAr62$plaid, vAr63$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr58$plaid",vAr58$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,87);
												vAr53$plaid = plaid.runtime.Util.call(vAr57$plaid, vAr58$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr53$plaid",vAr53$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",44,57);
												in = plaid.runtime.Util.call(vAr52$plaid, vAr53$plaid);
												plaid.runtime.PlaidRuntime.getRuntime().updateVar("in",in);
												local$c0pe.insert("in", in, false);
												{
													final plaid.runtime.PlaidObject out;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,55);
													final plaid.runtime.PlaidObject vAr65$plaid;
													final plaid.runtime.PlaidObject vAr66$plaid;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,55);
													final plaid.runtime.PlaidObject vAr67$plaid;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,43);
													final plaid.runtime.PlaidObject vAr68$plaid;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,40);
													final plaid.runtime.PlaidObject vAr69$plaid;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,35);
													vAr69$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr69$plaid",vAr69$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,35);
													vAr68$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr69$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr68$plaid",vAr68$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,40);
													vAr67$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("PrintWriter", vAr68$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr67$plaid",vAr67$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,43);
													vAr65$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr67$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr65$plaid",vAr65$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,66);
													final plaid.runtime.PlaidObject vAr70$plaid;
													final plaid.runtime.PlaidObject vAr71$plaid;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,66);
													final plaid.runtime.PlaidObject vAr72$plaid;
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,59);
													vAr72$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("remote", local$c0pe);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr72$plaid",vAr72$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,59);
													vAr70$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("getOutputStream", vAr72$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr70$plaid",vAr70$plaid);
													vAr71$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr71$plaid",vAr71$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,66);
													vAr66$plaid = plaid.runtime.Util.call(vAr70$plaid, vAr71$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr66$plaid",vAr66$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",45,55);
													out = plaid.runtime.Util.call(vAr65$plaid, vAr66$plaid);
													plaid.runtime.PlaidRuntime.getRuntime().updateVar("out",out);
													local$c0pe.insert("out", out, false);
													plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,29);
													{
														final plaid.runtime.PlaidObject vAr6$plaid;
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,29);
														final plaid.runtime.PlaidObject vAr73$plaid;
														final plaid.runtime.PlaidObject vAr74$plaid;
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,29);
														final plaid.runtime.PlaidObject vAr75$plaid;
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,25);
														vAr75$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", local$c0pe);
														plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr75$plaid",vAr75$plaid);
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,25);
														vAr73$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr75$plaid);
														plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr73$plaid",vAr73$plaid);
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,37);
														vAr74$plaid = plaid.runtime.Util.string("HTTP/1.0 200 OK");
														plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr74$plaid",vAr74$plaid);
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",49,29);
														vAr6$plaid = plaid.runtime.Util.call(vAr73$plaid, vAr74$plaid);
														plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr6$plaid",vAr6$plaid);
														plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,29);
														{
															final plaid.runtime.PlaidObject vAr5$plaid;
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,29);
															final plaid.runtime.PlaidObject vAr76$plaid;
															final plaid.runtime.PlaidObject vAr77$plaid;
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,29);
															final plaid.runtime.PlaidObject vAr78$plaid;
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,25);
															vAr78$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", local$c0pe);
															plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr78$plaid",vAr78$plaid);
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,25);
															vAr76$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr78$plaid);
															plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr76$plaid",vAr76$plaid);
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,37);
															vAr77$plaid = plaid.runtime.Util.string("Content-Type: text/html");
															plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr77$plaid",vAr77$plaid);
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",50,29);
															vAr5$plaid = plaid.runtime.Util.call(vAr76$plaid, vAr77$plaid);
															plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr5$plaid",vAr5$plaid);
															plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,29);
															{
																final plaid.runtime.PlaidObject vAr4$plaid;
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,29);
																final plaid.runtime.PlaidObject vAr79$plaid;
																final plaid.runtime.PlaidObject vAr80$plaid;
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,29);
																final plaid.runtime.PlaidObject vAr81$plaid;
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,25);
																vAr81$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", local$c0pe);
																plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr81$plaid",vAr81$plaid);
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,25);
																vAr79$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr81$plaid);
																plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr79$plaid",vAr79$plaid);
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,37);
																vAr80$plaid = plaid.runtime.Util.string("Server: Bot");
																plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr80$plaid",vAr80$plaid);
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",51,29);
																vAr4$plaid = plaid.runtime.Util.call(vAr79$plaid, vAr80$plaid);
																plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr4$plaid",vAr4$plaid);
																plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,29);
																{
																	final plaid.runtime.PlaidObject vAr3$plaid;
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,29);
																	final plaid.runtime.PlaidObject vAr82$plaid;
																	final plaid.runtime.PlaidObject vAr83$plaid;
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,29);
																	final plaid.runtime.PlaidObject vAr84$plaid;
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,25);
																	vAr84$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", local$c0pe);
																	plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr84$plaid",vAr84$plaid);
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,25);
																	vAr82$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr84$plaid);
																	plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr82$plaid",vAr82$plaid);
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,37);
																	vAr83$plaid = plaid.runtime.Util.string("");
																	plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr83$plaid",vAr83$plaid);
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",53,29);
																	vAr3$plaid = plaid.runtime.Util.call(vAr82$plaid, vAr83$plaid);
																	plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr3$plaid",vAr3$plaid);
																	plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,29);
																	{
																		final plaid.runtime.PlaidObject vAr2$plaid;
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,29);
																		final plaid.runtime.PlaidObject vAr85$plaid;
																		final plaid.runtime.PlaidObject vAr86$plaid;
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,29);
																		final plaid.runtime.PlaidObject vAr87$plaid;
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,25);
																		vAr87$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", local$c0pe);
																		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr87$plaid",vAr87$plaid);
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,25);
																		vAr85$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("println", vAr87$plaid);
																		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr85$plaid",vAr85$plaid);
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,37);
																		vAr86$plaid = plaid.runtime.Util.string("<H1>Welcome to the Ultra Mini-WebServer</H2>");
																		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr86$plaid",vAr86$plaid);
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",56,29);
																		vAr2$plaid = plaid.runtime.Util.call(vAr85$plaid, vAr86$plaid);
																		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr2$plaid",vAr2$plaid);
																		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",57,29);
																		{
																			final plaid.runtime.PlaidObject vAr1$plaid;
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",57,29);
																			final plaid.runtime.PlaidObject vAr88$plaid;
																			final plaid.runtime.PlaidObject vAr89$plaid;
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",57,29);
																			final plaid.runtime.PlaidObject vAr90$plaid;
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",57,25);
																			vAr90$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", local$c0pe);
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr90$plaid",vAr90$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",57,25);
																			vAr88$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("flush", vAr90$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr88$plaid",vAr88$plaid);
																			vAr89$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr89$plaid",vAr89$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",57,29);
																			vAr1$plaid = plaid.runtime.Util.call(vAr88$plaid, vAr89$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr1$plaid",vAr1$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",58,32);
																			final plaid.runtime.PlaidObject vAr91$plaid;
																			final plaid.runtime.PlaidObject vAr92$plaid;
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",58,32);
																			final plaid.runtime.PlaidObject vAr93$plaid;
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",58,25);
																			vAr93$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("remote", local$c0pe);
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr93$plaid",vAr93$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",58,25);
																			vAr91$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("close", vAr93$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr91$plaid",vAr91$plaid);
																			vAr92$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr92$plaid",vAr92$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",58,32);
																			vAr43$plaid = plaid.runtime.Util.call(vAr91$plaid, vAr92$plaid);
																			plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr43$plaid",vAr43$plaid);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
									return vAr43$plaid;
								}
							}
							);
							plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/plaid-lang/plaidapps/apps/webserver/webserver.plaid",38,17);
							vAr25$plaid = plaid.runtime.Util.call(vAr38$plaid, vAr39$plaid);
							plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr25$plaid",vAr25$plaid);
						}
					}
				}
				return vAr25$plaid;
			}
		}
		);
		vAr15$plaid.addMember("start",vAr24$plaid);
		server = vAr15$plaid.getPrototype();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("server",server);
	}
}
