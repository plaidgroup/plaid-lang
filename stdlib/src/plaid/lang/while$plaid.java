package plaid.lang;
@plaid.runtime.annotations.RepresentsField(name = "while$plaid", toplevel = true)
public class while$plaid{
	public static java.util.List<plaid.runtime.utils.Import> vAr43$plaid;
	static {
		vAr43$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr43$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "while$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject while$plaid;
	static {
		while$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject cond) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr44$plaid;
				vAr44$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject body) throws plaid.runtime.PlaidException {
						plaid.runtime.PlaidObject vAr45$plaid;
						plaid.runtime.PlaidObject vAr46$plaid;
						plaid.runtime.PlaidObject vAr47$plaid;
						plaid.runtime.PlaidObject vAr48$plaid;
						plaid.runtime.PlaidState vAr49$plaid;
						plaid.runtime.PlaidState vAr50$plaid;
						plaid.runtime.PlaidState vAr51$plaid;
						plaid.runtime.PlaidObject vAr52$plaid;
						vAr52$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("WhileState", current$c0pe);
						vAr50$plaid = plaid.runtime.Util.toPlaidState(vAr52$plaid);
						vAr51$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
						plaid.runtime.PlaidObject vAr54$plaid;
						vAr54$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr55$plaid) {
								plaid.runtime.PlaidScope the$c0pe = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
								plaid.runtime.PlaidObject vAr53$plaid;
								vAr53$plaid = cond;
								return vAr53$plaid;
							}
						}
						);
						vAr51$plaid.addMember("condition",vAr54$plaid);
						@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
						plaid.runtime.PlaidObject vAr57$plaid;
						vAr57$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr58$plaid) {
								plaid.runtime.PlaidScope the$c0pe = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
								plaid.runtime.PlaidObject vAr56$plaid;
								vAr56$plaid = body;
								return vAr56$plaid;
							}
						}
						);
						vAr51$plaid.addMember("whileBody",vAr57$plaid);
						vAr49$plaid = vAr50$plaid.with(vAr51$plaid);
						vAr48$plaid = vAr49$plaid.instantiate();
						vAr46$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", vAr48$plaid);
						vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr45$plaid = plaid.runtime.Util.call(vAr46$plaid, vAr47$plaid);
						return vAr45$plaid;
					}
				}
				);
				return vAr44$plaid;
			}
		}
		);
	}
}
