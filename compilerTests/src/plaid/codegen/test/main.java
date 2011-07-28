package plaid.codegen.test;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsMethod(name = "main", inPackage = "plaid.codegen.test", toplevel = true)
public class main{
	public static final java.util.List<plaid.runtime.utils.Import> vAr2$plaid;
	static {
		vAr2$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Double"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.codegen.CodeGenerator"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.codegen.test.main"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.codegen.test",vAr2$plaid);
	public static void main(String[] args) {
		plaid.lang.Sys.setArgs(args,0);
		plaid.runtime.PlaidRuntime.getRuntime().init();
		plaid.runtime.PlaidObject arg1$plaid$init = new plaid.runtime.models.map.PlaidJavaObjectMap(plaid.lang.Sys.getCommandLineArguments());
		plaid.runtime.PlaidObject convert$plaid$init = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("makeListFromJava", global$c0pe);
		plaid.runtime.PlaidObject arg2$plaid$init;
		arg2$plaid$init = plaid.runtime.Util.call(convert$plaid$init, arg1$plaid$init);
		plaid.runtime.Util.call(main_func, arg2$plaid$init);
		plaid.runtime.PlaidRuntime.getRuntime().shutdown();
		
	}
	@plaid.runtime.annotations.RepresentsMethod(name = "main", toplevel = false)
	public static final plaid.runtime.PlaidMethod main_func;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		main_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject _) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				plaid.runtime.PlaidObject vAr1$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject codeGenerator;
					final plaid.runtime.PlaidState vAr3$plaid;
					final plaid.runtime.PlaidObject vAr4$plaid;
					vAr4$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("CodeGenerator", global$c0pe);
					vAr3$plaid = plaid.runtime.Util.toPlaidState(vAr4$plaid);
					codeGenerator = vAr3$plaid.instantiate();
					if (codeGenerator instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("codeGenerator", codeGenerator, false);
					{
						final plaid.runtime.PlaidObject plaidPathEntries;
						final plaid.runtime.PlaidState vAr5$plaid;
						final plaid.runtime.PlaidObject vAr6$plaid;
						vAr6$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", global$c0pe);
						final plaid.runtime.PlaidState vAr8$plaid;
						vAr8$plaid = plaid.runtime.Util.toPlaidState(vAr6$plaid);
						final plaid.runtime.PlaidTag vAr9$plaid;
						vAr9$plaid = vAr8$plaid.getTag();
						final plaid.runtime.PlaidState vAr7$plaid;
						vAr7$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
						final plaid.runtime.PlaidObject vAr11$plaid;
						vAr11$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr12$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr10$plaid;
								final plaid.runtime.PlaidState vAr13$plaid;
								final plaid.runtime.PlaidObject vAr14$plaid;
								vAr14$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
								final plaid.runtime.PlaidObject vAr15$plaid;
								vAr15$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr14$plaid);
								final plaid.runtime.PlaidObject vAr16$plaid;
								vAr16$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", vAr15$plaid);
								vAr13$plaid = plaid.runtime.Util.toPlaidState(vAr16$plaid);
								vAr10$plaid = vAr13$plaid.instantiate();
								return vAr10$plaid;
								
							}
							
						}
						);
						final plaid.runtime.PlaidMemberDef vAr17$plaid;
						vAr17$plaid = plaid.runtime.Util.memberDef("head", vAr9$plaid, true, false);
						vAr7$plaid.addMember(vAr17$plaid,vAr11$plaid);
						vAr5$plaid = vAr8$plaid.initState(vAr7$plaid);
						plaidPathEntries = vAr5$plaid.instantiate();
						if (plaidPathEntries instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("plaidPathEntries", plaidPathEntries, false);
						{
							final plaid.runtime.PlaidObject vAr0$plaid;
							final plaid.runtime.PlaidObject vAr18$plaid;
							vAr18$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaidPathEntries", local$c0pe);
							final plaid.runtime.PlaidObject vAr19$plaid;
							vAr19$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("add", vAr18$plaid);
							final plaid.runtime.PlaidObject vAr20$plaid;
							vAr20$plaid = plaid.runtime.Util.string("../stdlib/bin/");
							final plaid.runtime.PlaidObject vAr21$plaid;
							vAr0$plaid = plaid.runtime.Util.call(vAr19$plaid, vAr20$plaid);
							final plaid.runtime.PlaidObject vAr22$plaid;
							vAr22$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("codeGenerator", local$c0pe);
							final plaid.runtime.PlaidObject vAr23$plaid;
							vAr23$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("compileFiles", vAr22$plaid);
							final plaid.runtime.PlaidObject vAr24$plaid;
							final plaid.runtime.PlaidState vAr25$plaid;
							final plaid.runtime.PlaidObject vAr26$plaid;
							vAr26$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
							final plaid.runtime.PlaidObject vAr27$plaid;
							vAr27$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr26$plaid);
							final plaid.runtime.PlaidObject vAr28$plaid;
							vAr28$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr27$plaid);
							final plaid.runtime.PlaidState vAr30$plaid;
							vAr30$plaid = plaid.runtime.Util.toPlaidState(vAr28$plaid);
							final plaid.runtime.PlaidTag vAr31$plaid;
							vAr31$plaid = vAr30$plaid.getTag();
							final plaid.runtime.PlaidState vAr29$plaid;
							vAr29$plaid = plaid.runtime.Util.newState();
							@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
							final plaid.runtime.PlaidObject vAr33$plaid;
							vAr33$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr34$plaid) {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									final plaid.runtime.PlaidObject vAr32$plaid;
									vAr32$plaid = plaid.runtime.Util.string("coreExamples");
									return vAr32$plaid;
									
								}
								
							}
							);
							final plaid.runtime.PlaidMemberDef vAr35$plaid;
							vAr35$plaid = plaid.runtime.Util.memberDef("fst", vAr31$plaid, false, false);
							vAr29$plaid.addMember(vAr35$plaid,vAr33$plaid);
							@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
							final plaid.runtime.PlaidObject vAr37$plaid;
							vAr37$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr38$plaid) {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									final plaid.runtime.PlaidObject vAr36$plaid;
									final plaid.runtime.PlaidState vAr39$plaid;
									final plaid.runtime.PlaidObject vAr40$plaid;
									vAr40$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
									final plaid.runtime.PlaidObject vAr41$plaid;
									vAr41$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr40$plaid);
									final plaid.runtime.PlaidObject vAr42$plaid;
									vAr42$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr41$plaid);
									final plaid.runtime.PlaidState vAr44$plaid;
									vAr44$plaid = plaid.runtime.Util.toPlaidState(vAr42$plaid);
									final plaid.runtime.PlaidTag vAr45$plaid;
									vAr45$plaid = vAr44$plaid.getTag();
									final plaid.runtime.PlaidState vAr43$plaid;
									vAr43$plaid = plaid.runtime.Util.newState();
									@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
									final plaid.runtime.PlaidObject vAr47$plaid;
									vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr48$plaid) {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											final plaid.runtime.PlaidObject vAr46$plaid;
											vAr46$plaid = plaid.runtime.Util.string("coreOutput");
											return vAr46$plaid;
											
										}
										
									}
									);
									final plaid.runtime.PlaidMemberDef vAr49$plaid;
									vAr49$plaid = plaid.runtime.Util.memberDef("fst", vAr45$plaid, false, false);
									vAr43$plaid.addMember(vAr49$plaid,vAr47$plaid);
									@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
									final plaid.runtime.PlaidObject vAr51$plaid;
									vAr51$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr52$plaid) {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											final plaid.runtime.PlaidObject vAr50$plaid;
											final plaid.runtime.PlaidState vAr53$plaid;
											final plaid.runtime.PlaidObject vAr54$plaid;
											vAr54$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
											final plaid.runtime.PlaidObject vAr55$plaid;
											vAr55$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr54$plaid);
											final plaid.runtime.PlaidObject vAr56$plaid;
											vAr56$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr55$plaid);
											final plaid.runtime.PlaidState vAr58$plaid;
											vAr58$plaid = plaid.runtime.Util.toPlaidState(vAr56$plaid);
											final plaid.runtime.PlaidTag vAr59$plaid;
											vAr59$plaid = vAr58$plaid.getTag();
											final plaid.runtime.PlaidState vAr57$plaid;
											vAr57$plaid = plaid.runtime.Util.newState();
											@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
											final plaid.runtime.PlaidObject vAr61$plaid;
											vAr61$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
												public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr62$plaid) {
													final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
													final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
													final plaid.runtime.PlaidObject vAr60$plaid;
													vAr60$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaidPathEntries", local$c0pe);
													return vAr60$plaid;
													
												}
												
											}
											);
											final plaid.runtime.PlaidMemberDef vAr63$plaid;
											vAr63$plaid = plaid.runtime.Util.memberDef("fst", vAr59$plaid, false, false);
											vAr57$plaid.addMember(vAr63$plaid,vAr61$plaid);
											@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
											final plaid.runtime.PlaidObject vAr65$plaid;
											vAr65$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
												public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr66$plaid) {
													final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
													final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
													final plaid.runtime.PlaidObject vAr64$plaid;
													vAr64$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
													return vAr64$plaid;
													
												}
												
											}
											);
											final plaid.runtime.PlaidMemberDef vAr67$plaid;
											vAr67$plaid = plaid.runtime.Util.memberDef("snd", vAr59$plaid, false, false);
											vAr57$plaid.addMember(vAr67$plaid,vAr65$plaid);
											vAr53$plaid = vAr58$plaid.initState(vAr57$plaid);
											vAr50$plaid = vAr53$plaid.instantiate();
											return vAr50$plaid;
											
										}
										
									}
									);
									final plaid.runtime.PlaidMemberDef vAr68$plaid;
									vAr68$plaid = plaid.runtime.Util.memberDef("snd", vAr45$plaid, false, false);
									vAr43$plaid.addMember(vAr68$plaid,vAr51$plaid);
									vAr39$plaid = vAr44$plaid.initState(vAr43$plaid);
									vAr36$plaid = vAr39$plaid.instantiate();
									return vAr36$plaid;
									
								}
								
							}
							);
							final plaid.runtime.PlaidMemberDef vAr69$plaid;
							vAr69$plaid = plaid.runtime.Util.memberDef("snd", vAr31$plaid, false, false);
							vAr29$plaid.addMember(vAr69$plaid,vAr37$plaid);
							vAr25$plaid = vAr30$plaid.initState(vAr29$plaid);
							vAr24$plaid = vAr25$plaid.instantiate();
							final plaid.runtime.PlaidObject vAr70$plaid;
							vAr1$plaid = plaid.runtime.Util.tailCall(vAr23$plaid,vAr24$plaid);
							
						}
						
					}
					
				}
				return vAr1$plaid;
				
			}
			
		}
		);
		
	}
	
}