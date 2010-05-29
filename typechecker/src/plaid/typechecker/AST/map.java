package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsMethod(name = "map", toplevel = true)
public class map{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1091$plaid;
	static {
		vAr1091$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1091$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1091$plaid);
	@plaid.runtime.annotations.RepresentsMethod(name = "map", toplevel = false)
	public static final plaid.runtime.PlaidMethod map_func;
	static {
		map_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject pA1R$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr1090$plaid;
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr1092$plaid;
					final plaid.runtime.PlaidObject vAr1093$plaid;
					final plaid.runtime.PlaidObject vAr1094$plaid;
					vAr1094$plaid = pA1R$plaid;
					vAr1092$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr1094$plaid);
					vAr1093$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					f = plaid.runtime.Util.call(vAr1092$plaid, vAr1093$plaid);
					{
						final plaid.runtime.PlaidObject list;
						final plaid.runtime.PlaidObject vAr1095$plaid;
						final plaid.runtime.PlaidObject vAr1096$plaid;
						final plaid.runtime.PlaidObject vAr1097$plaid;
						final plaid.runtime.PlaidObject vAr1098$plaid;
						final plaid.runtime.PlaidObject vAr1099$plaid;
						final plaid.runtime.PlaidObject vAr1100$plaid;
						vAr1100$plaid = pA1R$plaid;
						vAr1098$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr1100$plaid);
						vAr1099$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr1097$plaid = plaid.runtime.Util.call(vAr1098$plaid, vAr1099$plaid);
						vAr1095$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr1097$plaid);
						vAr1096$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						list = plaid.runtime.Util.call(vAr1095$plaid, vAr1096$plaid);
						{
							final plaid.runtime.PlaidObject iter;
							final plaid.runtime.PlaidObject vAr1101$plaid;
							final plaid.runtime.PlaidObject vAr1102$plaid;
							final plaid.runtime.PlaidObject vAr1103$plaid;
							vAr1103$plaid = list;
							vAr1101$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iterator", vAr1103$plaid);
							vAr1102$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							iter = plaid.runtime.Util.call(vAr1101$plaid, vAr1102$plaid);
							{
								final plaid.runtime.PlaidObject newList;
								final plaid.runtime.PlaidState vAr1104$plaid;
								final plaid.runtime.PlaidState vAr1105$plaid;
								final plaid.runtime.PlaidState vAr1106$plaid;
								final plaid.runtime.PlaidObject vAr1107$plaid;
								vAr1107$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
								final plaid.runtime.PlaidObject vAr1108$plaid;
								vAr1108$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr1107$plaid);
								final plaid.runtime.PlaidObject vAr1109$plaid;
								vAr1109$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", vAr1108$plaid);
								vAr1105$plaid = plaid.runtime.Util.toPlaidState(vAr1109$plaid);
								vAr1106$plaid = plaid.runtime.Util.newState();
								@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
								final plaid.runtime.PlaidObject vAr1111$plaid;
								vAr1111$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1112$plaid) {
										plaid.runtime.PlaidScope vAr1113$plaid = current$c0pe;
										final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr1113$plaid, this$plaid);
										final plaid.runtime.PlaidObject vAr1110$plaid;
										final plaid.runtime.PlaidState vAr1114$plaid;
										final plaid.runtime.PlaidObject vAr1115$plaid;
										vAr1115$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
										final plaid.runtime.PlaidObject vAr1116$plaid;
										vAr1116$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr1115$plaid);
										final plaid.runtime.PlaidObject vAr1117$plaid;
										vAr1117$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", vAr1116$plaid);
										vAr1114$plaid = plaid.runtime.Util.toPlaidState(vAr1117$plaid);
										vAr1110$plaid = vAr1114$plaid.instantiate();
										return vAr1110$plaid;
									}
								}
								);
								vAr1106$plaid.addMember("head",vAr1111$plaid);
								vAr1104$plaid = vAr1105$plaid.with(vAr1106$plaid);
								newList = vAr1104$plaid.instantiate();
								{
									final plaid.runtime.PlaidObject vAr13$plaid;
									final plaid.runtime.PlaidObject vAr1118$plaid;
									final plaid.runtime.PlaidObject vAr1119$plaid;
									final plaid.runtime.PlaidObject vAr1120$plaid;
									final plaid.runtime.PlaidObject vAr1121$plaid;
									vAr1120$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("while", current$c0pe);
									vAr1121$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr11$plaid) throws plaid.runtime.PlaidException {
											final plaid.runtime.PlaidObject vAr1122$plaid;
											final plaid.runtime.PlaidObject vAr1123$plaid;
											final plaid.runtime.PlaidObject vAr1124$plaid;
											final plaid.runtime.PlaidObject vAr1125$plaid;
											vAr1125$plaid = iter;
											vAr1123$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("hasNext", vAr1125$plaid);
											vAr1124$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
											vAr1122$plaid = plaid.runtime.Util.call(vAr1123$plaid, vAr1124$plaid);
											return vAr1122$plaid;
										}
									}
									);
									vAr1118$plaid = plaid.runtime.Util.call(vAr1120$plaid, vAr1121$plaid);
									vAr1119$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr12$plaid) throws plaid.runtime.PlaidException {
											final plaid.runtime.PlaidObject vAr1126$plaid;
											final plaid.runtime.PlaidObject vAr1127$plaid;
											final plaid.runtime.PlaidObject vAr1128$plaid;
											final plaid.runtime.PlaidObject vAr1129$plaid;
											vAr1129$plaid = newList;
											vAr1127$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("prepend", vAr1129$plaid);
											final plaid.runtime.PlaidObject vAr1130$plaid;
											final plaid.runtime.PlaidObject vAr1131$plaid;
											vAr1130$plaid = f;
											final plaid.runtime.PlaidObject vAr1132$plaid;
											final plaid.runtime.PlaidObject vAr1133$plaid;
											final plaid.runtime.PlaidObject vAr1134$plaid;
											vAr1134$plaid = iter;
											vAr1132$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", vAr1134$plaid);
											vAr1133$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
											vAr1131$plaid = plaid.runtime.Util.call(vAr1132$plaid, vAr1133$plaid);
											vAr1128$plaid = plaid.runtime.Util.call(vAr1130$plaid, vAr1131$plaid);
											vAr1126$plaid = plaid.runtime.Util.call(vAr1127$plaid, vAr1128$plaid);
											return vAr1126$plaid;
										}
									}
									);
									vAr13$plaid = plaid.runtime.Util.call(vAr1118$plaid, vAr1119$plaid);
									vAr1090$plaid = newList;
								}
							}
						}
					}
				}
				return vAr1090$plaid;
			}
		}
		);
	}
}
