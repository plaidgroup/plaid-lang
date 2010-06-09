package plaid.typechecker.AST;
@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = true)
public class main{
	public static final java.util.List<plaid.runtime.utils.Import> vAr1104$plaid;
	static {
		vAr1104$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr1104$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.typechecker.AST",vAr1104$plaid);
	public static void main(String[] args) {
		plaid.lang.Sys.setArgs(args,0);
		plaid.runtime.PlaidRuntime.getRuntime().init();
		main_func.invoke(plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit());
		plaid.runtime.PlaidRuntime.getRuntime().shutdown();
	}
	@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = false)
	public static final plaid.runtime.PlaidMethod main_func;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		main_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr18$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				local$c0pe.insert("vAr18$plaid", vAr18$plaid, false);
				plaid.runtime.PlaidObject vAr1103$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject javaAST;
					final plaid.runtime.PlaidObject vAr1105$plaid;
					final plaid.runtime.PlaidObject vAr1106$plaid;
					final plaid.runtime.PlaidObject vAr1107$plaid;
					final plaid.runtime.PlaidObject vAr1108$plaid;
					final plaid.runtime.PlaidObject vAr1109$plaid;
					vAr1109$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", local$c0pe);
					vAr1108$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("compilerjava", vAr1109$plaid);
					vAr1107$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ParserCore", vAr1108$plaid);
					vAr1105$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("parse", vAr1107$plaid);
					final plaid.runtime.PlaidObject vAr1110$plaid;
					final plaid.runtime.PlaidObject vAr1111$plaid;
					final plaid.runtime.PlaidObject vAr1112$plaid;
					final plaid.runtime.PlaidObject vAr1113$plaid;
					final plaid.runtime.PlaidObject vAr1114$plaid;
					vAr1114$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
					vAr1113$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr1114$plaid);
					vAr1112$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("FileInputStream", vAr1113$plaid);
					vAr1110$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr1112$plaid);
					final plaid.runtime.PlaidObject vAr1115$plaid;
					final plaid.runtime.PlaidObject vAr1116$plaid;
					final plaid.runtime.PlaidObject vAr1117$plaid;
					final plaid.runtime.PlaidObject vAr1118$plaid;
					final plaid.runtime.PlaidObject vAr1119$plaid;
					vAr1119$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
					vAr1118$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr1119$plaid);
					vAr1117$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("File", vAr1118$plaid);
					vAr1115$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr1117$plaid);
					vAr1116$plaid = plaid.runtime.Util.string("pld/addFive.plaid");
					vAr1111$plaid = plaid.runtime.Util.call(vAr1115$plaid, vAr1116$plaid);
					vAr1106$plaid = plaid.runtime.Util.call(vAr1110$plaid, vAr1111$plaid);
					javaAST = plaid.runtime.Util.call(vAr1105$plaid, vAr1106$plaid);
					local$c0pe.insert("javaAST", javaAST, false);
					{
						final plaid.runtime.PlaidObject translator;
						final plaid.runtime.PlaidState vAr1120$plaid;
						final plaid.runtime.PlaidObject vAr1121$plaid;
						vAr1121$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ASTTranslator", global$c0pe);
						vAr1120$plaid = plaid.runtime.Util.toPlaidState(vAr1121$plaid);
						translator = vAr1120$plaid.instantiate();
						local$c0pe.insert("translator", translator, true);
						{
							final plaid.runtime.PlaidObject plaidAST;
							final plaid.runtime.PlaidObject vAr1122$plaid;
							final plaid.runtime.PlaidObject vAr1123$plaid;
							final plaid.runtime.PlaidObject vAr1124$plaid;
							vAr1124$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("translator", local$c0pe);
							vAr1122$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("translateAST", vAr1124$plaid);
							vAr1123$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("javaAST", local$c0pe);
							plaidAST = plaid.runtime.Util.call(vAr1122$plaid, vAr1123$plaid);
							local$c0pe.insert("plaidAST", plaidAST, false);
							vAr1103$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						}
					}
				}
				return vAr1103$plaid;
			}
		}
		);
	}
}
