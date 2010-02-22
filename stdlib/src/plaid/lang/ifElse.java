package plaid.lang;
@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = true)
public class ifElse{
	public static java.util.List<plaid.runtime.utils.Import> vAr24$plaid;
	static {
		vAr24$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr24$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = false)
	public static plaid.runtime.PlaidObject ifElse;
	static {
		ifElse = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr25$plaid;
				plaid.runtime.PlaidObject vAr26$plaid;
				vAr26$plaid = bool;
				plaid.runtime.PlaidObject vAr27$plaid;
				plaid.runtime.PlaidObject vAr28$plaid;
				vAr28$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				plaid.runtime.PlaidObject vAr29$plaid;
				vAr29$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr28$plaid);
				plaid.runtime.PlaidObject vAr30$plaid;
				vAr30$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr29$plaid);
				vAr27$plaid = plaid.runtime.Util.toPlaidState(vAr30$plaid);
				if (vAr26$plaid.getStates().contains(vAr27$plaid)) {
					final plaid.runtime.PlaidObject x;
					x = vAr26$plaid;
					vAr25$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
							plaid.runtime.PlaidObject vAr31$plaid;
							vAr31$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
									plaid.runtime.PlaidObject vAr32$plaid;
									plaid.runtime.PlaidObject vAr33$plaid;
									plaid.runtime.PlaidObject vAr34$plaid;
									vAr33$plaid = trueClause;
									vAr34$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									vAr32$plaid = plaid.runtime.Util.call(vAr33$plaid, vAr34$plaid);
									return vAr32$plaid;
								}
							}
							);
							return vAr31$plaid;
						}
					}
					);
				}
				else {
					plaid.runtime.PlaidObject vAr35$plaid;
					plaid.runtime.PlaidObject vAr36$plaid;
					vAr36$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					plaid.runtime.PlaidObject vAr37$plaid;
					vAr37$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr36$plaid);
					plaid.runtime.PlaidObject vAr38$plaid;
					vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr37$plaid);
					vAr35$plaid = plaid.runtime.Util.toPlaidState(vAr38$plaid);
					if (vAr26$plaid.getStates().contains(vAr35$plaid)) {
						final plaid.runtime.PlaidObject x;
						x = vAr26$plaid;
						vAr25$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
								plaid.runtime.PlaidObject vAr39$plaid;
								vAr39$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
										plaid.runtime.PlaidObject vAr40$plaid;
										plaid.runtime.PlaidObject vAr41$plaid;
										plaid.runtime.PlaidObject vAr42$plaid;
										vAr41$plaid = falseClause;
										vAr42$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr40$plaid = plaid.runtime.Util.call(vAr41$plaid, vAr42$plaid);
										return vAr40$plaid;
									}
								}
								);
								return vAr39$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr25$plaid;
			}
		}
		);
	}
}
