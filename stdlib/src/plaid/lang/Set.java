package plaid.lang;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsState(name = "Set", inPackage = "plaid.lang", toplevel = true, jsonRep = "{\"member_type\":\"state\",\"name\":\"Set\",\"members\":[{\"member_type\":\"field\",\"name\":\"data\"},{\"member_type\":\"method\",\"name\":\"add\"},{\"member_type\":\"method\",\"name\":\"remove\"},{\"member_type\":\"method\",\"name\":\"contains\"}]}")
public class Set{
	public static final java.util.List<plaid.runtime.utils.Import> vAr29$plaid;
	static {
		vAr29$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Double"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Set"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr29$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr29$plaid);
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Set")
	public static final plaid.runtime.PlaidTag Set$Tag$plaid;
	static {
		final plaid.runtime.PlaidTag vAr31$plaid;
		vAr31$plaid = null;
		Set$Tag$plaid = plaid.runtime.Util.tag("Set", "plaid.lang", vAr31$plaid);
		
	}
	@plaid.runtime.annotations.RepresentsState(name = "Set", toplevel = false)
	public static final plaid.runtime.PlaidObject Set;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		final plaid.runtime.PlaidState vAr30$plaid;
		vAr30$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "data", toplevel = false)
		final plaid.runtime.PlaidObject vAr33$plaid;
		vAr33$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr34$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr32$plaid;
				final plaid.runtime.PlaidObject vAr35$plaid;
				final plaid.runtime.PlaidObject vAr36$plaid;
				vAr35$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("makeEmptyList", local$c0pe);
				vAr36$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				vAr32$plaid = plaid.runtime.Util.call(vAr35$plaid, vAr36$plaid);
				return vAr32$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr37$plaid;
		vAr37$plaid = plaid.runtime.Util.memberDef("data", Set$Tag$plaid, false, false);
		vAr30$plaid.addMember(vAr37$plaid,vAr33$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "add", toplevel = false)
		final plaid.runtime.PlaidObject vAr38$plaid;
		vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(Set$Tag$plaid.getPath()+ "." + "add", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject o) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (o instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("o", o, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr39$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr40$plaid;
				final plaid.runtime.PlaidObject vAr41$plaid;
				final plaid.runtime.PlaidObject vAr42$plaid;
				final plaid.runtime.PlaidObject vAr43$plaid;
				vAr42$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("if$plaid", local$c0pe);
				final plaid.runtime.PlaidObject vAr44$plaid;
				final plaid.runtime.PlaidObject vAr45$plaid;
				final plaid.runtime.PlaidObject vAr46$plaid;
				final plaid.runtime.PlaidObject vAr47$plaid;
				vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr45$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("contains", vAr47$plaid);
				vAr46$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("o", local$c0pe);
				vAr44$plaid = plaid.runtime.Util.call(vAr45$plaid, vAr46$plaid);
				final plaid.runtime.PlaidObject vAr48$plaid;
				vAr48$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("bang$plaid", vAr44$plaid);
				final plaid.runtime.PlaidObject vAr49$plaid;
				vAr49$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr50$plaid;
				vAr43$plaid = plaid.runtime.Util.call(vAr48$plaid, vAr49$plaid);
				vAr40$plaid = plaid.runtime.Util.call(vAr42$plaid, vAr43$plaid);
				vAr41$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr1$plaid) throws plaid.runtime.PlaidException {
						final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
						final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
						if (vAr1$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("vAr1$plaid", vAr1$plaid, false);
						plaid.runtime.PlaidObject vAr51$plaid = plaid.runtime.Util.unit();
						final plaid.runtime.PlaidObject vAr52$plaid;
						final plaid.runtime.PlaidObject vAr53$plaid;
						vAr53$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
						vAr52$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("data", vAr53$plaid);
						final plaid.runtime.PlaidObject vAr54$plaid;
						vAr54$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("add", vAr52$plaid);
						final plaid.runtime.PlaidObject vAr55$plaid;
						vAr55$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("o", local$c0pe);
						final plaid.runtime.PlaidObject vAr56$plaid;
						vAr51$plaid = plaid.runtime.Util.tailCall(vAr54$plaid,vAr55$plaid);
						return vAr51$plaid;
						
					}
					
				}
				);
				vAr39$plaid = plaid.runtime.Util.tailCall(vAr40$plaid,vAr41$plaid);
				return vAr39$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr57$plaid;
		vAr57$plaid = plaid.runtime.Util.memberDef("add", Set$Tag$plaid, false, false);
		vAr30$plaid.addMember(vAr57$plaid,vAr38$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "remove", toplevel = false)
		final plaid.runtime.PlaidObject vAr58$plaid;
		vAr58$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(Set$Tag$plaid.getPath()+ "." + "remove", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject o) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (o instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("o", o, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr59$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr60$plaid;
				final plaid.runtime.PlaidObject vAr61$plaid;
				vAr61$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr60$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("data", vAr61$plaid);
				final plaid.runtime.PlaidObject vAr62$plaid;
				vAr62$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("filter", vAr60$plaid);
				final plaid.runtime.PlaidObject vAr63$plaid;
				vAr63$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject elem) throws plaid.runtime.PlaidException {
						final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
						final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
						if (elem instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("elem", elem, false);
						plaid.runtime.PlaidObject vAr64$plaid = plaid.runtime.Util.unit();
						final plaid.runtime.PlaidObject vAr65$plaid;
						vAr65$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("elem", local$c0pe);
						final plaid.runtime.PlaidObject vAr66$plaid;
						vAr66$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("bangeq$plaid", vAr65$plaid);
						final plaid.runtime.PlaidObject vAr67$plaid;
						vAr67$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("o", local$c0pe);
						final plaid.runtime.PlaidObject vAr68$plaid;
						vAr64$plaid = plaid.runtime.Util.tailCall(vAr66$plaid,vAr67$plaid);
						return vAr64$plaid;
						
					}
					
				}
				);
				final plaid.runtime.PlaidObject vAr69$plaid;
				vAr59$plaid = plaid.runtime.Util.tailCall(vAr62$plaid,vAr63$plaid);
				return vAr59$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr70$plaid;
		vAr70$plaid = plaid.runtime.Util.memberDef("remove", Set$Tag$plaid, false, false);
		vAr30$plaid.addMember(vAr70$plaid,vAr58$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "contains", toplevel = false)
		final plaid.runtime.PlaidObject vAr71$plaid;
		vAr71$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(Set$Tag$plaid.getPath()+ "." + "contains", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject o) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (o instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("o", o, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr72$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject result;
					result = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
					if (result instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("result", result, false);
					{
						final plaid.runtime.PlaidObject vAr4$plaid;
						final plaid.runtime.PlaidObject vAr73$plaid;
						final plaid.runtime.PlaidObject vAr74$plaid;
						vAr74$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
						vAr73$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("data", vAr74$plaid);
						final plaid.runtime.PlaidObject vAr75$plaid;
						vAr75$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("map", vAr73$plaid);
						final plaid.runtime.PlaidObject vAr76$plaid;
						vAr76$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject elem) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								if (elem instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
								else local$c0pe.insert("elem", elem, false);
								plaid.runtime.PlaidObject vAr77$plaid = plaid.runtime.Util.unit();
								{
									final plaid.runtime.PlaidObject vAr3$plaid;
									final plaid.runtime.PlaidObject vAr78$plaid;
									final plaid.runtime.PlaidObject vAr79$plaid;
									final plaid.runtime.PlaidObject vAr80$plaid;
									final plaid.runtime.PlaidObject vAr81$plaid;
									vAr80$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("if$plaid", local$c0pe);
									final plaid.runtime.PlaidObject vAr82$plaid;
									vAr82$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("elem", local$c0pe);
									final plaid.runtime.PlaidObject vAr83$plaid;
									vAr83$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("eqeq$plaid", vAr82$plaid);
									final plaid.runtime.PlaidObject vAr84$plaid;
									vAr84$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("o", local$c0pe);
									final plaid.runtime.PlaidObject vAr85$plaid;
									vAr81$plaid = plaid.runtime.Util.call(vAr83$plaid, vAr84$plaid);
									vAr78$plaid = plaid.runtime.Util.call(vAr80$plaid, vAr81$plaid);
									vAr79$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr2$plaid) throws plaid.runtime.PlaidException {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											if (vAr2$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
											else local$c0pe.insert("vAr2$plaid", vAr2$plaid, false);
											plaid.runtime.PlaidObject vAr86$plaid = plaid.runtime.Util.unit();
											final plaid.runtime.PlaidObject vAr87$plaid;
											vAr87$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
											plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("result", local$c0pe);
											if (vAr87$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
											else local$c0pe.update("result", vAr87$plaid);
											vAr86$plaid = vAr87$plaid;
											return vAr86$plaid;
											
										}
										
									}
									);
									vAr3$plaid = plaid.runtime.Util.call(vAr78$plaid, vAr79$plaid);
									vAr77$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("elem", local$c0pe);
									
								}
								return vAr77$plaid;
								
							}
							
						}
						);
						final plaid.runtime.PlaidObject vAr88$plaid;
						vAr4$plaid = plaid.runtime.Util.call(vAr75$plaid, vAr76$plaid);
						vAr72$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("result", local$c0pe);
						
					}
					
				}
				return vAr72$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr89$plaid;
		vAr89$plaid = plaid.runtime.Util.memberDef("contains", Set$Tag$plaid, false, false);
		vAr30$plaid.addMember(vAr89$plaid,vAr71$plaid);
		Set$Tag$plaid.nest(vAr30$plaid);
		Set = vAr30$plaid.getPrototype();
		
	}
	
}