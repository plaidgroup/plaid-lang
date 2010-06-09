package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsMethod(name = "map", toplevel = true)
public class map{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1060$plaid;
	static {
		vAr1060$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1060$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1060$plaid);
	@plaid.runtime.annotations.RepresentsMethod(name = "map", toplevel = false)
	public static final plaid.runtime.PlaidMethod map_func;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		map_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject pA1R$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				local$c0pe.insert("pA1R$plaid", pA1R$plaid, false);
				plaid.runtime.PlaidObject vAr1059$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr1061$plaid;
					final plaid.runtime.PlaidObject vAr1062$plaid;
					final plaid.runtime.PlaidObject vAr1063$plaid;
					vAr1063$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
					vAr1061$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr1063$plaid);
					vAr1062$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					f = plaid.runtime.Util.call(vAr1061$plaid, vAr1062$plaid);
					local$c0pe.insert("f", f, true);
					{
						final plaid.runtime.PlaidObject list;
						final plaid.runtime.PlaidObject vAr1064$plaid;
						final plaid.runtime.PlaidObject vAr1065$plaid;
						final plaid.runtime.PlaidObject vAr1066$plaid;
						final plaid.runtime.PlaidObject vAr1067$plaid;
						final plaid.runtime.PlaidObject vAr1068$plaid;
						final plaid.runtime.PlaidObject vAr1069$plaid;
						vAr1069$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
						vAr1067$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr1069$plaid);
						vAr1068$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr1066$plaid = plaid.runtime.Util.call(vAr1067$plaid, vAr1068$plaid);
						vAr1064$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr1066$plaid);
						vAr1065$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						list = plaid.runtime.Util.call(vAr1064$plaid, vAr1065$plaid);
						local$c0pe.insert("list", list, true);
						{
							final plaid.runtime.PlaidObject iter;
							final plaid.runtime.PlaidObject vAr1070$plaid;
							final plaid.runtime.PlaidObject vAr1071$plaid;
							final plaid.runtime.PlaidObject vAr1072$plaid;
							vAr1072$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("list", local$c0pe);
							vAr1070$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iterator", vAr1072$plaid);
							vAr1071$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							iter = plaid.runtime.Util.call(vAr1070$plaid, vAr1071$plaid);
							local$c0pe.insert("iter", iter, true);
							{
								final plaid.runtime.PlaidObject newList;
								final plaid.runtime.PlaidState vAr1073$plaid;
								final plaid.runtime.PlaidState vAr1074$plaid;
								final plaid.runtime.PlaidState vAr1075$plaid;
								final plaid.runtime.PlaidObject vAr1076$plaid;
								vAr1076$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
								final plaid.runtime.PlaidObject vAr1077$plaid;
								vAr1077$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr1076$plaid);
								final plaid.runtime.PlaidObject vAr1078$plaid;
								vAr1078$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", vAr1077$plaid);
								vAr1074$plaid = plaid.runtime.Util.toPlaidState(vAr1078$plaid);
								vAr1075$plaid = plaid.runtime.Util.newState();
								@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
								final plaid.runtime.PlaidObject vAr1080$plaid;
								vAr1080$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr1081$plaid) {
										final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
										final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
										final plaid.runtime.PlaidObject vAr1079$plaid;
										final plaid.runtime.PlaidState vAr1082$plaid;
										final plaid.runtime.PlaidObject vAr1083$plaid;
										vAr1083$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
										final plaid.runtime.PlaidObject vAr1084$plaid;
										vAr1084$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr1083$plaid);
										final plaid.runtime.PlaidObject vAr1085$plaid;
										vAr1085$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", vAr1084$plaid);
										vAr1082$plaid = plaid.runtime.Util.toPlaidState(vAr1085$plaid);
										vAr1079$plaid = vAr1082$plaid.instantiate();
										return vAr1079$plaid;
									}
								}
								);
								vAr1075$plaid.addMember("head",vAr1080$plaid, false);
								vAr1073$plaid = vAr1074$plaid.with(vAr1075$plaid);
								newList = vAr1073$plaid.instantiate();
								local$c0pe.insert("newList", newList, true);
								{
									final plaid.runtime.PlaidObject vAr17$plaid;
									final plaid.runtime.PlaidObject vAr1086$plaid;
									final plaid.runtime.PlaidObject vAr1087$plaid;
									final plaid.runtime.PlaidObject vAr1088$plaid;
									final plaid.runtime.PlaidObject vAr1089$plaid;
									vAr1088$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("while$plaid", local$c0pe);
									vAr1089$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr15$plaid) throws plaid.runtime.PlaidException {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
											local$c0pe.insert("vAr15$plaid", vAr15$plaid, false);
											plaid.runtime.PlaidObject vAr1090$plaid = plaid.runtime.Util.unit();
											final plaid.runtime.PlaidObject vAr1091$plaid;
											final plaid.runtime.PlaidObject vAr1092$plaid;
											final plaid.runtime.PlaidObject vAr1093$plaid;
											vAr1093$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iter", local$c0pe);
											vAr1091$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("hasNext", vAr1093$plaid);
											vAr1092$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
											vAr1090$plaid = plaid.runtime.Util.call(vAr1091$plaid, vAr1092$plaid);
											return vAr1090$plaid;
										}
									}
									);
									vAr1086$plaid = plaid.runtime.Util.call(vAr1088$plaid, vAr1089$plaid);
									vAr1087$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr16$plaid) throws plaid.runtime.PlaidException {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
											local$c0pe.insert("vAr16$plaid", vAr16$plaid, false);
											plaid.runtime.PlaidObject vAr1094$plaid = plaid.runtime.Util.unit();
											final plaid.runtime.PlaidObject vAr1095$plaid;
											final plaid.runtime.PlaidObject vAr1096$plaid;
											final plaid.runtime.PlaidObject vAr1097$plaid;
											vAr1097$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("newList", local$c0pe);
											vAr1095$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("prepend", vAr1097$plaid);
											final plaid.runtime.PlaidObject vAr1098$plaid;
											final plaid.runtime.PlaidObject vAr1099$plaid;
											vAr1098$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("f", local$c0pe);
											final plaid.runtime.PlaidObject vAr1100$plaid;
											final plaid.runtime.PlaidObject vAr1101$plaid;
											final plaid.runtime.PlaidObject vAr1102$plaid;
											vAr1102$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iter", local$c0pe);
											vAr1100$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", vAr1102$plaid);
											vAr1101$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
											vAr1099$plaid = plaid.runtime.Util.call(vAr1100$plaid, vAr1101$plaid);
											vAr1096$plaid = plaid.runtime.Util.call(vAr1098$plaid, vAr1099$plaid);
											vAr1094$plaid = plaid.runtime.Util.call(vAr1095$plaid, vAr1096$plaid);
											return vAr1094$plaid;
										}
									}
									);
									vAr17$plaid = plaid.runtime.Util.call(vAr1086$plaid, vAr1087$plaid);
									vAr1059$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("newList", local$c0pe);
								}
							}
						}
					}
				}
				return vAr1059$plaid;
			}
		}
		);
	}
}
