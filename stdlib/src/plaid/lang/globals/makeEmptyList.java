package plaid.lang.globals;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsField(name = "makeEmptyList", inPackage = "plaid.lang.globals", toplevel = true)
public class makeEmptyList{
	public static final java.util.List<plaid.runtime.utils.Import> vAr190$plaid;
	static {
		vAr190$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr190$plaid",vAr190$plaid);
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Exception"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr190$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang.globals",vAr190$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "makeEmptyList", toplevel = false)
	public static plaid.runtime.PlaidObject makeEmptyList=null;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",56,21);
		makeEmptyList = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject _) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				plaid.runtime.PlaidObject vAr191$plaid=null;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",56,29);
				final plaid.runtime.PlaidState vAr192$plaid;
				final plaid.runtime.PlaidObject vAr193$plaid;
				vAr193$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", global$c0pe);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr193$plaid",vAr193$plaid);
				final plaid.runtime.PlaidState vAr195$plaid;
				vAr195$plaid = plaid.runtime.Util.toPlaidState(vAr193$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr195$plaid",vAr195$plaid);
				final plaid.runtime.PlaidState vAr194$plaid;
				vAr194$plaid = plaid.runtime.Util.newState();
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr194$plaid",vAr194$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",56,44);
				@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
				final plaid.runtime.PlaidObject vAr197$plaid;
				vAr197$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr198$plaid) {
						final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
						final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
						final plaid.runtime.PlaidObject vAr196$plaid;
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/plaid/lang/globals/package.plaid",56,51);
						final plaid.runtime.PlaidState vAr199$plaid;
						final plaid.runtime.PlaidObject vAr200$plaid;
						vAr200$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", global$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr200$plaid",vAr200$plaid);
						vAr199$plaid = plaid.runtime.Util.toPlaidState(vAr200$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr199$plaid",vAr199$plaid);
						vAr196$plaid = vAr199$plaid.instantiate();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr196$plaid",vAr196$plaid);
						return vAr196$plaid;
						
					}
					
				}
				);
				final plaid.runtime.PlaidMemberDef vAr201$plaid;
				vAr201$plaid = plaid.runtime.Util.anonymousMemberDef("head", true, false);
				vAr194$plaid.addMember(vAr201$plaid,vAr197$plaid);
				vAr192$plaid = vAr195$plaid.initState(vAr194$plaid);
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr192$plaid",vAr192$plaid);
				vAr191$plaid = vAr192$plaid.instantiate();
				plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr191$plaid",vAr191$plaid);
				return vAr191$plaid;
				
			}
			
		}
		);
		if (makeEmptyList instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
		else global$c0pe.insert("makeEmptyList", makeEmptyList, true);
		
	}
	
}