package plaid.lang;
@plaid.runtime.annotations.RepresentsState(name = "WhileState", toplevel = true)
public class WhileState{
	public static java.util.List<plaid.runtime.utils.Import> vAr59$plaid;
	static {
		vAr59$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr59$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "WhileState", toplevel = false)
	public static plaid.runtime.PlaidObject WhileState;
	static {
		WhileState = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
		plaid.runtime.PlaidObject vAr61$plaid;
		vAr61$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr62$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr60$plaid;
				vAr60$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr60$plaid;
			}
		}
		);
		WhileState.addMember("condition",vAr61$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
		plaid.runtime.PlaidObject vAr64$plaid;
		vAr64$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr65$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr63$plaid;
				vAr63$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr63$plaid;
			}
		}
		);
		WhileState.addMember("whileBody",vAr64$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "whileLoop", toplevel = false)
		plaid.runtime.PlaidObject vAr66$plaid;
		vAr66$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject result) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr67$plaid;
				{
					final plaid.runtime.PlaidObject test;
					plaid.runtime.PlaidObject vAr68$plaid;
					plaid.runtime.PlaidObject vAr69$plaid;
					vAr68$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("condition", current$c0pe);
					vAr69$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					test = plaid.runtime.Util.call(vAr68$plaid, vAr69$plaid);
					plaid.runtime.PlaidObject vAr70$plaid;
					vAr70$plaid = test;
					plaid.runtime.PlaidObject vAr71$plaid;
					plaid.runtime.PlaidObject vAr72$plaid;
					vAr72$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					plaid.runtime.PlaidObject vAr73$plaid;
					vAr73$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr72$plaid);
					plaid.runtime.PlaidObject vAr74$plaid;
					vAr74$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr73$plaid);
					vAr71$plaid = plaid.runtime.Util.toPlaidState(vAr74$plaid);
					if (vAr70$plaid.getStates().contains(vAr71$plaid)) {
						final plaid.runtime.PlaidObject t;
						t = vAr70$plaid;
						{
							final plaid.runtime.PlaidObject newResult;
							plaid.runtime.PlaidObject vAr75$plaid;
							plaid.runtime.PlaidObject vAr76$plaid;
							vAr75$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileBody", current$c0pe);
							vAr76$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							newResult = plaid.runtime.Util.call(vAr75$plaid, vAr76$plaid);
							plaid.runtime.PlaidObject vAr77$plaid;
							plaid.runtime.PlaidObject vAr78$plaid;
							vAr77$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", current$c0pe);
							vAr78$plaid = newResult;
							vAr67$plaid = plaid.runtime.Util.call(vAr77$plaid, vAr78$plaid);
						}
					}
					else {
						plaid.runtime.PlaidObject vAr79$plaid;
						plaid.runtime.PlaidObject vAr80$plaid;
						vAr80$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
						plaid.runtime.PlaidObject vAr81$plaid;
						vAr81$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr80$plaid);
						plaid.runtime.PlaidObject vAr82$plaid;
						vAr82$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr81$plaid);
						vAr79$plaid = plaid.runtime.Util.toPlaidState(vAr82$plaid);
						if (vAr70$plaid.getStates().contains(vAr79$plaid)) {
							final plaid.runtime.PlaidObject f;
							f = vAr70$plaid;
							vAr67$plaid = result;
						}
						else {
							throw new RuntimeException("Pattern match exhausted.");
						}
					}
				}
				return vAr67$plaid;
			}
		}
		);
		WhileState.addMember("whileLoop",vAr66$plaid);
	}
}
