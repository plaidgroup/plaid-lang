package plaid.ast.util;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsMethod(name = "makeListFromJavaCollection", inPackage = "plaid.ast.util", toplevel = true)
public class makeListFromJavaCollection{
	public static final java.util.List<plaid.runtime.utils.Import> vAr2823$plaid;
	static {
		vAr2823$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Double"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Set"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.ast.util.makeListFromJavaCollection"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.ast.util.Token"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr2823$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.ast.util",vAr2823$plaid);
	@plaid.runtime.annotations.RepresentsMethod(name = "makeListFromJavaCollection", toplevel = false)
	public static final plaid.runtime.PlaidMethod makeListFromJavaCollection_func;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		makeListFromJavaCollection_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject javaCollection) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				if (javaCollection instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("javaCollection", javaCollection, false);
				plaid.runtime.PlaidObject vAr2822$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject iter;
					final plaid.runtime.PlaidObject vAr2824$plaid;
					vAr2824$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("javaCollection", local$c0pe);
					final plaid.runtime.PlaidObject vAr2825$plaid;
					vAr2825$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iterator", vAr2824$plaid);
					final plaid.runtime.PlaidObject vAr2826$plaid;
					vAr2826$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					final plaid.runtime.PlaidObject vAr2827$plaid;
					iter = plaid.runtime.Util.call(vAr2825$plaid, vAr2826$plaid);
					if (iter instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("iter", iter, true);
					{
						final plaid.runtime.PlaidObject newList;
						final plaid.runtime.PlaidState vAr2828$plaid;
						final plaid.runtime.PlaidObject vAr2829$plaid;
						vAr2829$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
						final plaid.runtime.PlaidObject vAr2830$plaid;
						vAr2830$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr2829$plaid);
						final plaid.runtime.PlaidObject vAr2831$plaid;
						vAr2831$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("List", vAr2830$plaid);
						final plaid.runtime.PlaidState vAr2833$plaid;
						vAr2833$plaid = plaid.runtime.Util.toPlaidState(vAr2831$plaid);
						final plaid.runtime.PlaidTag vAr2834$plaid;
						vAr2834$plaid = vAr2833$plaid.getTag();
						final plaid.runtime.PlaidState vAr2832$plaid;
						vAr2832$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "head", toplevel = false)
						final plaid.runtime.PlaidObject vAr2836$plaid;
						vAr2836$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr2837$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr2835$plaid;
								final plaid.runtime.PlaidState vAr2838$plaid;
								final plaid.runtime.PlaidObject vAr2839$plaid;
								vAr2839$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
								final plaid.runtime.PlaidObject vAr2840$plaid;
								vAr2840$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr2839$plaid);
								final plaid.runtime.PlaidObject vAr2841$plaid;
								vAr2841$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", vAr2840$plaid);
								vAr2838$plaid = plaid.runtime.Util.toPlaidState(vAr2841$plaid);
								vAr2835$plaid = vAr2838$plaid.instantiate();
								return vAr2835$plaid;
								
							}
							
						}
						);
						final plaid.runtime.PlaidMemberDef vAr2842$plaid;
						vAr2842$plaid = plaid.runtime.Util.memberDef("head", vAr2834$plaid, true, false);
						vAr2832$plaid.addMember(vAr2842$plaid,vAr2836$plaid);
						vAr2828$plaid = vAr2833$plaid.initState(vAr2832$plaid);
						newList = vAr2828$plaid.instantiate();
						if (newList instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("newList", newList, true);
						{
							final plaid.runtime.PlaidObject vAr2$plaid;
							final plaid.runtime.PlaidObject vAr2843$plaid;
							final plaid.runtime.PlaidObject vAr2844$plaid;
							final plaid.runtime.PlaidObject vAr2845$plaid;
							final plaid.runtime.PlaidObject vAr2846$plaid;
							vAr2845$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("while$plaid", local$c0pe);
							vAr2846$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr0$plaid) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									if (vAr0$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
									else local$c0pe.insert("vAr0$plaid", vAr0$plaid, false);
									plaid.runtime.PlaidObject vAr2847$plaid = plaid.runtime.Util.unit();
									final plaid.runtime.PlaidObject vAr2848$plaid;
									vAr2848$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iter", local$c0pe);
									final plaid.runtime.PlaidObject vAr2849$plaid;
									vAr2849$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("hasNext", vAr2848$plaid);
									final plaid.runtime.PlaidObject vAr2850$plaid;
									vAr2850$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									final plaid.runtime.PlaidObject vAr2851$plaid;
									vAr2847$plaid = plaid.runtime.Util.tailCall(vAr2849$plaid,vAr2850$plaid);
									return vAr2847$plaid;
									
								}
								
							}
							);
							vAr2843$plaid = plaid.runtime.Util.call(vAr2845$plaid, vAr2846$plaid);
							vAr2844$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject vAr1$plaid) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									if (vAr1$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
									else local$c0pe.insert("vAr1$plaid", vAr1$plaid, false);
									plaid.runtime.PlaidObject vAr2852$plaid = plaid.runtime.Util.unit();
									final plaid.runtime.PlaidObject vAr2853$plaid;
									vAr2853$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("newList", local$c0pe);
									final plaid.runtime.PlaidObject vAr2854$plaid;
									vAr2854$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("add", vAr2853$plaid);
									final plaid.runtime.PlaidObject vAr2855$plaid;
									final plaid.runtime.PlaidObject vAr2856$plaid;
									vAr2856$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("iter", local$c0pe);
									final plaid.runtime.PlaidObject vAr2857$plaid;
									vAr2857$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", vAr2856$plaid);
									final plaid.runtime.PlaidObject vAr2858$plaid;
									vAr2858$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									final plaid.runtime.PlaidObject vAr2859$plaid;
									vAr2855$plaid = plaid.runtime.Util.call(vAr2857$plaid, vAr2858$plaid);
									final plaid.runtime.PlaidObject vAr2860$plaid;
									vAr2852$plaid = plaid.runtime.Util.tailCall(vAr2854$plaid,vAr2855$plaid);
									return vAr2852$plaid;
									
								}
								
							}
							);
							vAr2$plaid = plaid.runtime.Util.call(vAr2843$plaid, vAr2844$plaid);
							vAr2822$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("newList", local$c0pe);
							
						}
						
					}
					
				}
				return vAr2822$plaid;
				
			}
			
		}
		);
		
	}
	
}