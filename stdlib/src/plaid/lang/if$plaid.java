package plaid.lang;
@plaid.runtime.annotations.RepresentsField(name = "if$plaid", toplevel = true)
public class if$plaid{
	public static java.util.List<plaid.runtime.utils.Import> vAr9$plaid;
	static {
		vAr9$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr9$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "if$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject if$plaid;
	static {
		if$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr10$plaid;
				plaid.runtime.PlaidObject vAr11$plaid;
				vAr11$plaid = bool;
				plaid.runtime.PlaidObject vAr12$plaid;
				plaid.runtime.PlaidObject vAr13$plaid;
				vAr13$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				plaid.runtime.PlaidObject vAr14$plaid;
				vAr14$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr13$plaid);
				plaid.runtime.PlaidObject vAr15$plaid;
				vAr15$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr14$plaid);
				vAr12$plaid = plaid.runtime.Util.toPlaidState(vAr15$plaid);
				if (vAr11$plaid.getStates().contains(vAr12$plaid)) {
					final plaid.runtime.PlaidObject x;
					x = vAr11$plaid;
					vAr10$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
							plaid.runtime.PlaidObject vAr16$plaid;
							plaid.runtime.PlaidObject vAr17$plaid;
							plaid.runtime.PlaidObject vAr18$plaid;
							vAr17$plaid = func;
							vAr18$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							vAr16$plaid = plaid.runtime.Util.call(vAr17$plaid, vAr18$plaid);
							return vAr16$plaid;
						}
					}
					);
				}
				else {
					plaid.runtime.PlaidObject vAr19$plaid;
					plaid.runtime.PlaidObject vAr20$plaid;
					vAr20$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					plaid.runtime.PlaidObject vAr21$plaid;
					vAr21$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr20$plaid);
					plaid.runtime.PlaidObject vAr22$plaid;
					vAr22$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr21$plaid);
					vAr19$plaid = plaid.runtime.Util.toPlaidState(vAr22$plaid);
					if (vAr11$plaid.getStates().contains(vAr19$plaid)) {
						final plaid.runtime.PlaidObject x;
						x = vAr11$plaid;
						vAr10$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
								plaid.runtime.PlaidObject vAr23$plaid;
								vAr23$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
								return vAr23$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr10$plaid;
			}
		}
		);
	}
}
