package plaid.lang.globals;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsField(name = "makePair", inPackage = "plaid.lang.globals", toplevel = true)
public class makePair{
	public static final java.util.List<plaid.runtime.utils.Import> vAr202$plaid;
	static {
		vAr202$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr202$plaid",vAr202$plaid);
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Exception"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr202$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang.globals",vAr202$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "makePair", toplevel = false)
	public static plaid.runtime.PlaidObject makePair=null;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,16);
		makePair = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject pA1R$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				if (pA1R$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("pA1R$plaid", pA1R$plaid, false);
				plaid.runtime.PlaidObject vAr203$plaid=null;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,20);
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr204$plaid;
					final plaid.runtime.PlaidObject vAr205$plaid;
					final plaid.runtime.PlaidObject vAr206$plaid;
					vAr206$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr206$plaid",vAr206$plaid);
					vAr204$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr206$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr204$plaid",vAr204$plaid);
					vAr205$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr205$plaid",vAr205$plaid);
					f = plaid.runtime.Util.call(vAr204$plaid, vAr205$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("f",f);
					if (f instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("f", f, true);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,22);
					{
						final plaid.runtime.PlaidObject s;
						final plaid.runtime.PlaidObject vAr207$plaid;
						final plaid.runtime.PlaidObject vAr208$plaid;
						final plaid.runtime.PlaidObject vAr209$plaid;
						final plaid.runtime.PlaidObject vAr210$plaid;
						final plaid.runtime.PlaidObject vAr211$plaid;
						final plaid.runtime.PlaidObject vAr212$plaid;
						vAr212$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr212$plaid",vAr212$plaid);
						vAr210$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr212$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr210$plaid",vAr210$plaid);
						vAr211$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr211$plaid",vAr211$plaid);
						vAr209$plaid = plaid.runtime.Util.call(vAr210$plaid, vAr211$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr209$plaid",vAr209$plaid);
						vAr207$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr209$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr207$plaid",vAr207$plaid);
						vAr208$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr208$plaid",vAr208$plaid);
						s = plaid.runtime.Util.call(vAr207$plaid, vAr208$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("s",s);
						if (s instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("s", s, true);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,28);
						final plaid.runtime.PlaidState vAr213$plaid;
						final plaid.runtime.PlaidObject vAr214$plaid;
						vAr214$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", global$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr214$plaid",vAr214$plaid);
						final plaid.runtime.PlaidState vAr216$plaid;
						vAr216$plaid = plaid.runtime.Util.toPlaidState(vAr214$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr216$plaid",vAr216$plaid);
						final plaid.runtime.PlaidState vAr215$plaid;
						vAr215$plaid = plaid.runtime.Util.newState();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr215$plaid",vAr215$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,39);
						@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
						final plaid.runtime.PlaidObject vAr218$plaid;
						vAr218$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr219$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr217$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,45);
								vAr217$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("f", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr217$plaid",vAr217$plaid);
								return vAr217$plaid;
								
							}
							
						}
						);
						final plaid.runtime.PlaidMemberDef vAr220$plaid;
						vAr220$plaid = plaid.runtime.Util.anonymousMemberDef("fst", false, false);
						vAr215$plaid.addMember(vAr220$plaid,vAr218$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,48);
						@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
						final plaid.runtime.PlaidObject vAr222$plaid;
						vAr222$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr223$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr221$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",59,54);
								vAr221$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("s", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr221$plaid",vAr221$plaid);
								return vAr221$plaid;
								
							}
							
						}
						);
						final plaid.runtime.PlaidMemberDef vAr224$plaid;
						vAr224$plaid = plaid.runtime.Util.anonymousMemberDef("snd", false, false);
						vAr215$plaid.addMember(vAr224$plaid,vAr222$plaid);
						vAr213$plaid = vAr216$plaid.initState(vAr215$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr213$plaid",vAr213$plaid);
						vAr203$plaid = vAr213$plaid.instantiate();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr203$plaid",vAr203$plaid);
						
					}
					
				}
				return vAr203$plaid;
				
			}
			
		}
		);
		if (makePair instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
		else global$c0pe.insert("makePair", makePair, true);
		
	}
	
}