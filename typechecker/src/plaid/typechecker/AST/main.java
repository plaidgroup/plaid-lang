package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = true)
public class main{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1137$plaid;
	static {
		vAr1137$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1137$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.typechecker.AST",vAr1137$plaid);
	public static void main(String[] args) {
		plaid.lang.System.setArgs(args,0);
		plaid.runtime.PlaidRuntime.getRuntime().init();
		main_func.invoke(plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit());
		plaid.runtime.PlaidRuntime.getRuntime().shutdown();
	}
	@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = false)
	public static final plaid.runtime.PlaidMethod main_func;
	static {
		main_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr18$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr1136$plaid;
				{
					plaid.runtime.PlaidObject javaAST;
					final plaid.runtime.PlaidObject vAr1138$plaid;
					final plaid.runtime.PlaidObject vAr1139$plaid;
					final plaid.runtime.PlaidObject vAr1140$plaid;
					final plaid.runtime.PlaidObject vAr1141$plaid;
					final plaid.runtime.PlaidObject vAr1142$plaid;
					vAr1142$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					vAr1141$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("compilerjava", vAr1142$plaid);
					vAr1140$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ParserCore", vAr1141$plaid);
					vAr1138$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("parse", vAr1140$plaid);
					final plaid.runtime.PlaidObject vAr1143$plaid;
					final plaid.runtime.PlaidObject vAr1144$plaid;
					final plaid.runtime.PlaidObject vAr1145$plaid;
					final plaid.runtime.PlaidObject vAr1146$plaid;
					final plaid.runtime.PlaidObject vAr1147$plaid;
					vAr1147$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", current$c0pe);
					vAr1146$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr1147$plaid);
					vAr1145$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("FileInputStream", vAr1146$plaid);
					vAr1143$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new", vAr1145$plaid);
					final plaid.runtime.PlaidObject vAr1148$plaid;
					final plaid.runtime.PlaidObject vAr1149$plaid;
					final plaid.runtime.PlaidObject vAr1150$plaid;
					final plaid.runtime.PlaidObject vAr1151$plaid;
					final plaid.runtime.PlaidObject vAr1152$plaid;
					vAr1152$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", current$c0pe);
					vAr1151$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr1152$plaid);
					vAr1150$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("File", vAr1151$plaid);
					vAr1148$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new", vAr1150$plaid);
					vAr1149$plaid = plaid.runtime.Util.string("pld/addFive.plaid");
					vAr1144$plaid = plaid.runtime.Util.call(vAr1148$plaid, vAr1149$plaid);
					vAr1139$plaid = plaid.runtime.Util.call(vAr1143$plaid, vAr1144$plaid);
					javaAST = plaid.runtime.Util.call(vAr1138$plaid, vAr1139$plaid);
					{
						final plaid.runtime.PlaidObject translator;
						final plaid.runtime.PlaidState vAr1153$plaid;
						final plaid.runtime.PlaidObject vAr1154$plaid;
						vAr1154$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTTranslator", current$c0pe);
						vAr1153$plaid = plaid.runtime.Util.toPlaidState(vAr1154$plaid);
						translator = vAr1153$plaid.instantiate();
						{
							plaid.runtime.PlaidObject plaidAST;
							final plaid.runtime.PlaidObject vAr1155$plaid;
							final plaid.runtime.PlaidObject vAr1156$plaid;
							final plaid.runtime.PlaidObject vAr1157$plaid;
							vAr1157$plaid = translator;
							vAr1155$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("translateAST", vAr1157$plaid);
							vAr1156$plaid = javaAST;
							plaidAST = plaid.runtime.Util.call(vAr1155$plaid, vAr1156$plaid);
							vAr1136$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						}
					}
				}
				return vAr1136$plaid;
			}
		}
		);
	}
}
