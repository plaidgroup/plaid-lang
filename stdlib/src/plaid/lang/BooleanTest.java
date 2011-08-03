package plaid.lang;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsState(name = "BooleanTest", inPackage = "plaid.lang", toplevel = true, jsonRep = "{\"member_type\":\"state\",\"name\":\"BooleanTest\",\"members\":[{\"member_type\":\"method\",\"name\":\"createTests\"},{\"member_type\":\"method\",\"name\":\"createTests\"},{\"member_type\":\"method\",\"name\":\"mktest\"}]}")
public class BooleanTest{
	public static final java.util.List<plaid.runtime.utils.Import> vAr248$plaid;
	static {
		vAr248$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Hashable"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Comparable"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdLessThan"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Orderable"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Ordering"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.BooleanTest"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Number"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdEqual"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OptionTest"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.testing.TestFactory"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Real"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrderProvider"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		vAr248$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdGreaterThan"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr248$plaid);
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.BooleanTest")
	public static final plaid.runtime.PlaidTag BooleanTest$Tag$plaid;
	static {
		final plaid.runtime.PlaidTag vAr250$plaid;
		final plaid.runtime.PlaidState vAr251$plaid;
		final plaid.runtime.PlaidObject vAr252$plaid;
		vAr252$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("TestFactory", global$c0pe);
		vAr251$plaid = plaid.runtime.Util.toPlaidState(vAr252$plaid);
		vAr250$plaid = vAr251$plaid.getTag();
		BooleanTest$Tag$plaid = plaid.runtime.Util.tag("BooleanTest", "plaid.lang", vAr250$plaid);
		
	}
	@plaid.runtime.annotations.RepresentsState(name = "BooleanTest", toplevel = false)
	public static final plaid.runtime.PlaidObject BooleanTest;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		final plaid.runtime.PlaidState vAr249$plaid;
		final plaid.runtime.PlaidState vAr253$plaid;
		final plaid.runtime.PlaidObject vAr255$plaid;
		vAr255$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("TestFactory", global$c0pe);
		vAr253$plaid = plaid.runtime.Util.toPlaidState(vAr255$plaid);
		final plaid.runtime.PlaidState vAr254$plaid;
		vAr254$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "createTests", toplevel = false)
		final plaid.runtime.PlaidObject vAr256$plaid;
		vAr256$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(BooleanTest$Tag$plaid.getPath()+ "." + "createTests", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr257$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject tests;
					final plaid.runtime.PlaidObject vAr258$plaid;
					final plaid.runtime.PlaidObject vAr259$plaid;
					vAr258$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("makeEmptyList", local$c0pe);
					vAr259$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					tests = plaid.runtime.Util.call(vAr258$plaid, vAr259$plaid);
					if (tests instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("tests", tests, true);
					{
						final plaid.runtime.PlaidObject test;
						test = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject pA1R$plaid) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								if (pA1R$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
								else local$c0pe.insert("pA1R$plaid", pA1R$plaid, false);
								plaid.runtime.PlaidObject vAr262$plaid = plaid.runtime.Util.unit();
								{
									final plaid.runtime.PlaidObject name;
									final plaid.runtime.PlaidObject vAr321$plaid;
									final plaid.runtime.PlaidObject vAr322$plaid;
									final plaid.runtime.PlaidObject vAr323$plaid;
									vAr323$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
									vAr321$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr323$plaid);
									vAr322$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									name = plaid.runtime.Util.call(vAr321$plaid, vAr322$plaid);
									if (name instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
									else local$c0pe.insert("name", name, true);
									{
										final plaid.runtime.PlaidObject body;
										final plaid.runtime.PlaidObject vAr324$plaid;
										final plaid.runtime.PlaidObject vAr325$plaid;
										final plaid.runtime.PlaidObject vAr326$plaid;
										final plaid.runtime.PlaidObject vAr327$plaid;
										final plaid.runtime.PlaidObject vAr328$plaid;
										final plaid.runtime.PlaidObject vAr329$plaid;
										vAr329$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
										vAr327$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr329$plaid);
										vAr328$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr326$plaid = plaid.runtime.Util.call(vAr327$plaid, vAr328$plaid);
										vAr324$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr326$plaid);
										vAr325$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										body = plaid.runtime.Util.call(vAr324$plaid, vAr325$plaid);
										if (body instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
										else local$c0pe.insert("body", body, true);
										final plaid.runtime.PlaidObject vAr330$plaid;
										vAr330$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("tests", local$c0pe);
										final plaid.runtime.PlaidObject vAr331$plaid;
										vAr331$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("add", vAr330$plaid);
										final plaid.runtime.PlaidObject vAr332$plaid;
										final plaid.runtime.PlaidObject vAr333$plaid;
										final plaid.runtime.PlaidObject vAr334$plaid;
										final plaid.runtime.PlaidObject vAr335$plaid;
										vAr335$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
										vAr333$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mktest", vAr335$plaid);
										final plaid.runtime.PlaidState vAr336$plaid;
										final plaid.runtime.PlaidObject vAr337$plaid;
										vAr337$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
										final plaid.runtime.PlaidObject vAr338$plaid;
										vAr338$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr337$plaid);
										final plaid.runtime.PlaidObject vAr339$plaid;
										vAr339$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr338$plaid);
										final plaid.runtime.PlaidState vAr341$plaid;
										vAr341$plaid = plaid.runtime.Util.toPlaidState(vAr339$plaid);
										final plaid.runtime.PlaidTag vAr342$plaid;
										vAr342$plaid = vAr341$plaid.getTag();
										final plaid.runtime.PlaidState vAr340$plaid;
										vAr340$plaid = plaid.runtime.Util.newState();
										@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
										final plaid.runtime.PlaidObject vAr344$plaid;
										vAr344$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
											public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr345$plaid) {
												final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
												final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
												final plaid.runtime.PlaidObject vAr343$plaid;
												vAr343$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("name", local$c0pe);
												return vAr343$plaid;
												
											}
											
										}
										);
										final plaid.runtime.PlaidMemberDef vAr346$plaid;
										vAr346$plaid = plaid.runtime.Util.memberDef("fst", vAr342$plaid, false, false);
										vAr340$plaid.addMember(vAr346$plaid,vAr344$plaid);
										@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
										final plaid.runtime.PlaidObject vAr348$plaid;
										vAr348$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
											public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr349$plaid) {
												final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
												final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
												final plaid.runtime.PlaidObject vAr347$plaid;
												final plaid.runtime.PlaidState vAr350$plaid;
												final plaid.runtime.PlaidObject vAr351$plaid;
												vAr351$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
												final plaid.runtime.PlaidObject vAr352$plaid;
												vAr352$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr351$plaid);
												final plaid.runtime.PlaidObject vAr353$plaid;
												vAr353$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr352$plaid);
												final plaid.runtime.PlaidState vAr355$plaid;
												vAr355$plaid = plaid.runtime.Util.toPlaidState(vAr353$plaid);
												final plaid.runtime.PlaidTag vAr356$plaid;
												vAr356$plaid = vAr355$plaid.getTag();
												final plaid.runtime.PlaidState vAr354$plaid;
												vAr354$plaid = plaid.runtime.Util.newState();
												@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
												final plaid.runtime.PlaidObject vAr358$plaid;
												vAr358$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
													public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr359$plaid) {
														final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
														final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
														final plaid.runtime.PlaidObject vAr357$plaid;
														vAr357$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("body", local$c0pe);
														return vAr357$plaid;
														
													}
													
												}
												);
												final plaid.runtime.PlaidMemberDef vAr360$plaid;
												vAr360$plaid = plaid.runtime.Util.memberDef("fst", vAr356$plaid, false, false);
												vAr354$plaid.addMember(vAr360$plaid,vAr358$plaid);
												@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
												final plaid.runtime.PlaidObject vAr362$plaid;
												vAr362$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
													public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr363$plaid) {
														final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
														final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
														final plaid.runtime.PlaidObject vAr361$plaid;
														vAr361$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
														return vAr361$plaid;
														
													}
													
												}
												);
												final plaid.runtime.PlaidMemberDef vAr364$plaid;
												vAr364$plaid = plaid.runtime.Util.memberDef("snd", vAr356$plaid, false, false);
												vAr354$plaid.addMember(vAr364$plaid,vAr362$plaid);
												vAr350$plaid = vAr355$plaid.initState(vAr354$plaid);
												vAr347$plaid = vAr350$plaid.instantiate();
												return vAr347$plaid;
												
											}
											
										}
										);
										final plaid.runtime.PlaidMemberDef vAr365$plaid;
										vAr365$plaid = plaid.runtime.Util.memberDef("snd", vAr342$plaid, false, false);
										vAr340$plaid.addMember(vAr365$plaid,vAr348$plaid);
										vAr336$plaid = vAr341$plaid.initState(vAr340$plaid);
										vAr334$plaid = vAr336$plaid.instantiate();
										vAr332$plaid = plaid.runtime.Util.call(vAr333$plaid, vAr334$plaid);
										final plaid.runtime.PlaidObject vAr366$plaid;
										vAr262$plaid = plaid.runtime.Util.tailCall(vAr331$plaid,vAr332$plaid);
										
									}
									
								}
								return vAr262$plaid;
								
							}
							
						}
						);
						if (test instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("test", test, true);
						{
							final plaid.runtime.PlaidObject vAr162$plaid;
							final plaid.runtime.PlaidObject vAr367$plaid;
							final plaid.runtime.PlaidObject vAr368$plaid;
							vAr367$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("test", local$c0pe);
							final plaid.runtime.PlaidState vAr369$plaid;
							final plaid.runtime.PlaidObject vAr370$plaid;
							vAr370$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
							final plaid.runtime.PlaidObject vAr371$plaid;
							vAr371$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr370$plaid);
							final plaid.runtime.PlaidObject vAr372$plaid;
							vAr372$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr371$plaid);
							final plaid.runtime.PlaidState vAr374$plaid;
							vAr374$plaid = plaid.runtime.Util.toPlaidState(vAr372$plaid);
							final plaid.runtime.PlaidTag vAr375$plaid;
							vAr375$plaid = vAr374$plaid.getTag();
							final plaid.runtime.PlaidState vAr373$plaid;
							vAr373$plaid = plaid.runtime.Util.newState();
							@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
							final plaid.runtime.PlaidObject vAr377$plaid;
							vAr377$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr378$plaid) {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									final plaid.runtime.PlaidObject vAr376$plaid;
									vAr376$plaid = plaid.runtime.Util.string("equality of Booleans");
									return vAr376$plaid;
									
								}
								
							}
							);
							final plaid.runtime.PlaidMemberDef vAr379$plaid;
							vAr379$plaid = plaid.runtime.Util.memberDef("fst", vAr375$plaid, false, false);
							vAr373$plaid.addMember(vAr379$plaid,vAr377$plaid);
							@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
							final plaid.runtime.PlaidObject vAr381$plaid;
							vAr381$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr382$plaid) {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									final plaid.runtime.PlaidObject vAr380$plaid;
									final plaid.runtime.PlaidState vAr383$plaid;
									final plaid.runtime.PlaidObject vAr384$plaid;
									vAr384$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
									final plaid.runtime.PlaidObject vAr385$plaid;
									vAr385$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr384$plaid);
									final plaid.runtime.PlaidObject vAr386$plaid;
									vAr386$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr385$plaid);
									final plaid.runtime.PlaidState vAr388$plaid;
									vAr388$plaid = plaid.runtime.Util.toPlaidState(vAr386$plaid);
									final plaid.runtime.PlaidTag vAr389$plaid;
									vAr389$plaid = vAr388$plaid.getTag();
									final plaid.runtime.PlaidState vAr387$plaid;
									vAr387$plaid = plaid.runtime.Util.newState();
									@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
									final plaid.runtime.PlaidObject vAr391$plaid;
									vAr391$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr392$plaid) {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											final plaid.runtime.PlaidObject vAr390$plaid;
											vAr390$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
												public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject ctx) throws plaid.runtime.PlaidException {
													final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
													final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
													if (ctx instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
													else local$c0pe.insert("ctx", ctx, false);
													plaid.runtime.PlaidObject vAr393$plaid = plaid.runtime.Util.unit();
													{
														final plaid.runtime.PlaidObject vAr156$plaid;
														final plaid.runtime.PlaidObject vAr394$plaid;
														vAr394$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
														final plaid.runtime.PlaidObject vAr395$plaid;
														vAr395$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertTrue", vAr394$plaid);
														final plaid.runtime.PlaidObject vAr396$plaid;
														final plaid.runtime.PlaidObject vAr397$plaid;
														vAr397$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
														final plaid.runtime.PlaidObject vAr398$plaid;
														vAr398$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("eqeq$plaid", vAr397$plaid);
														final plaid.runtime.PlaidObject vAr399$plaid;
														vAr399$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
														final plaid.runtime.PlaidObject vAr400$plaid;
														vAr396$plaid = plaid.runtime.Util.call(vAr398$plaid, vAr399$plaid);
														final plaid.runtime.PlaidObject vAr401$plaid;
														vAr156$plaid = plaid.runtime.Util.call(vAr395$plaid, vAr396$plaid);
														{
															final plaid.runtime.PlaidObject vAr155$plaid;
															final plaid.runtime.PlaidObject vAr402$plaid;
															vAr402$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
															final plaid.runtime.PlaidObject vAr403$plaid;
															vAr403$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertTrue", vAr402$plaid);
															final plaid.runtime.PlaidObject vAr404$plaid;
															final plaid.runtime.PlaidObject vAr405$plaid;
															vAr405$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
															final plaid.runtime.PlaidObject vAr406$plaid;
															vAr406$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("eqeq$plaid", vAr405$plaid);
															final plaid.runtime.PlaidObject vAr407$plaid;
															final plaid.runtime.PlaidState vAr408$plaid;
															final plaid.runtime.PlaidObject vAr409$plaid;
															vAr409$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", global$c0pe);
															vAr408$plaid = plaid.runtime.Util.toPlaidState(vAr409$plaid);
															vAr407$plaid = vAr408$plaid.instantiate();
															final plaid.runtime.PlaidObject vAr410$plaid;
															vAr404$plaid = plaid.runtime.Util.call(vAr406$plaid, vAr407$plaid);
															final plaid.runtime.PlaidObject vAr411$plaid;
															vAr155$plaid = plaid.runtime.Util.call(vAr403$plaid, vAr404$plaid);
															{
																final plaid.runtime.PlaidObject vAr154$plaid;
																final plaid.runtime.PlaidObject vAr412$plaid;
																vAr412$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																final plaid.runtime.PlaidObject vAr413$plaid;
																vAr413$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertFalse", vAr412$plaid);
																final plaid.runtime.PlaidObject vAr414$plaid;
																final plaid.runtime.PlaidObject vAr415$plaid;
																vAr415$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
																final plaid.runtime.PlaidObject vAr416$plaid;
																vAr416$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("eqeq$plaid", vAr415$plaid);
																final plaid.runtime.PlaidObject vAr417$plaid;
																vAr417$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																final plaid.runtime.PlaidObject vAr418$plaid;
																vAr414$plaid = plaid.runtime.Util.call(vAr416$plaid, vAr417$plaid);
																final plaid.runtime.PlaidObject vAr419$plaid;
																vAr154$plaid = plaid.runtime.Util.call(vAr413$plaid, vAr414$plaid);
																{
																	final plaid.runtime.PlaidObject vAr153$plaid;
																	final plaid.runtime.PlaidObject vAr420$plaid;
																	vAr420$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																	final plaid.runtime.PlaidObject vAr421$plaid;
																	vAr421$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertTrue", vAr420$plaid);
																	final plaid.runtime.PlaidObject vAr422$plaid;
																	final plaid.runtime.PlaidObject vAr423$plaid;
																	vAr423$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																	final plaid.runtime.PlaidObject vAr424$plaid;
																	vAr424$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("eqeq$plaid", vAr423$plaid);
																	final plaid.runtime.PlaidObject vAr425$plaid;
																	vAr425$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																	final plaid.runtime.PlaidObject vAr426$plaid;
																	vAr422$plaid = plaid.runtime.Util.call(vAr424$plaid, vAr425$plaid);
																	final plaid.runtime.PlaidObject vAr427$plaid;
																	vAr153$plaid = plaid.runtime.Util.call(vAr421$plaid, vAr422$plaid);
																	final plaid.runtime.PlaidObject vAr428$plaid;
																	vAr428$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																	final plaid.runtime.PlaidObject vAr429$plaid;
																	vAr429$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertTrue", vAr428$plaid);
																	final plaid.runtime.PlaidObject vAr430$plaid;
																	final plaid.runtime.PlaidObject vAr431$plaid;
																	vAr431$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																	final plaid.runtime.PlaidObject vAr432$plaid;
																	vAr432$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("eqeq$plaid", vAr431$plaid);
																	final plaid.runtime.PlaidObject vAr433$plaid;
																	final plaid.runtime.PlaidState vAr434$plaid;
																	final plaid.runtime.PlaidObject vAr435$plaid;
																	vAr435$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", global$c0pe);
																	vAr434$plaid = plaid.runtime.Util.toPlaidState(vAr435$plaid);
																	vAr433$plaid = vAr434$plaid.instantiate();
																	final plaid.runtime.PlaidObject vAr436$plaid;
																	vAr430$plaid = plaid.runtime.Util.call(vAr432$plaid, vAr433$plaid);
																	final plaid.runtime.PlaidObject vAr437$plaid;
																	vAr393$plaid = plaid.runtime.Util.tailCall(vAr429$plaid,vAr430$plaid);
																	
																}
																
															}
															
														}
														
													}
													return vAr393$plaid;
													
												}
												
											}
											);
											return vAr390$plaid;
											
										}
										
									}
									);
									final plaid.runtime.PlaidMemberDef vAr438$plaid;
									vAr438$plaid = plaid.runtime.Util.memberDef("fst", vAr389$plaid, false, false);
									vAr387$plaid.addMember(vAr438$plaid,vAr391$plaid);
									@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
									final plaid.runtime.PlaidObject vAr440$plaid;
									vAr440$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr441$plaid) {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											final plaid.runtime.PlaidObject vAr439$plaid;
											vAr439$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
											return vAr439$plaid;
											
										}
										
									}
									);
									final plaid.runtime.PlaidMemberDef vAr442$plaid;
									vAr442$plaid = plaid.runtime.Util.memberDef("snd", vAr389$plaid, false, false);
									vAr387$plaid.addMember(vAr442$plaid,vAr440$plaid);
									vAr383$plaid = vAr388$plaid.initState(vAr387$plaid);
									vAr380$plaid = vAr383$plaid.instantiate();
									return vAr380$plaid;
									
								}
								
							}
							);
							final plaid.runtime.PlaidMemberDef vAr443$plaid;
							vAr443$plaid = plaid.runtime.Util.memberDef("snd", vAr375$plaid, false, false);
							vAr373$plaid.addMember(vAr443$plaid,vAr381$plaid);
							vAr369$plaid = vAr374$plaid.initState(vAr373$plaid);
							vAr368$plaid = vAr369$plaid.instantiate();
							vAr162$plaid = plaid.runtime.Util.call(vAr367$plaid, vAr368$plaid);
							final plaid.runtime.PlaidObject vAr444$plaid;
							final plaid.runtime.PlaidObject vAr445$plaid;
							vAr444$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("test", local$c0pe);
							final plaid.runtime.PlaidState vAr446$plaid;
							final plaid.runtime.PlaidObject vAr447$plaid;
							vAr447$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
							final plaid.runtime.PlaidObject vAr448$plaid;
							vAr448$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr447$plaid);
							final plaid.runtime.PlaidObject vAr449$plaid;
							vAr449$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr448$plaid);
							final plaid.runtime.PlaidState vAr451$plaid;
							vAr451$plaid = plaid.runtime.Util.toPlaidState(vAr449$plaid);
							final plaid.runtime.PlaidTag vAr452$plaid;
							vAr452$plaid = vAr451$plaid.getTag();
							final plaid.runtime.PlaidState vAr450$plaid;
							vAr450$plaid = plaid.runtime.Util.newState();
							@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
							final plaid.runtime.PlaidObject vAr454$plaid;
							vAr454$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr455$plaid) {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									final plaid.runtime.PlaidObject vAr453$plaid;
									vAr453$plaid = plaid.runtime.Util.string("ordering of Booleans");
									return vAr453$plaid;
									
								}
								
							}
							);
							final plaid.runtime.PlaidMemberDef vAr456$plaid;
							vAr456$plaid = plaid.runtime.Util.memberDef("fst", vAr452$plaid, false, false);
							vAr450$plaid.addMember(vAr456$plaid,vAr454$plaid);
							@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
							final plaid.runtime.PlaidObject vAr458$plaid;
							vAr458$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr459$plaid) {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
									final plaid.runtime.PlaidObject vAr457$plaid;
									final plaid.runtime.PlaidState vAr460$plaid;
									final plaid.runtime.PlaidObject vAr461$plaid;
									vAr461$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
									final plaid.runtime.PlaidObject vAr462$plaid;
									vAr462$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr461$plaid);
									final plaid.runtime.PlaidObject vAr463$plaid;
									vAr463$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", vAr462$plaid);
									final plaid.runtime.PlaidState vAr465$plaid;
									vAr465$plaid = plaid.runtime.Util.toPlaidState(vAr463$plaid);
									final plaid.runtime.PlaidTag vAr466$plaid;
									vAr466$plaid = vAr465$plaid.getTag();
									final plaid.runtime.PlaidState vAr464$plaid;
									vAr464$plaid = plaid.runtime.Util.newState();
									@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
									final plaid.runtime.PlaidObject vAr468$plaid;
									vAr468$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr469$plaid) {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											final plaid.runtime.PlaidObject vAr467$plaid;
											vAr467$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
												public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject ctx) throws plaid.runtime.PlaidException {
													final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
													final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
													if (ctx instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
													else local$c0pe.insert("ctx", ctx, false);
													plaid.runtime.PlaidObject vAr470$plaid = plaid.runtime.Util.unit();
													{
														final plaid.runtime.PlaidObject vAr161$plaid;
														final plaid.runtime.PlaidObject vAr471$plaid;
														vAr471$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
														final plaid.runtime.PlaidObject vAr472$plaid;
														vAr472$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertTrue", vAr471$plaid);
														final plaid.runtime.PlaidObject vAr473$plaid;
														final plaid.runtime.PlaidObject vAr474$plaid;
														vAr474$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
														final plaid.runtime.PlaidObject vAr475$plaid;
														vAr475$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lt$plaid", vAr474$plaid);
														final plaid.runtime.PlaidObject vAr476$plaid;
														vAr476$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
														final plaid.runtime.PlaidObject vAr477$plaid;
														vAr473$plaid = plaid.runtime.Util.call(vAr475$plaid, vAr476$plaid);
														final plaid.runtime.PlaidObject vAr478$plaid;
														vAr161$plaid = plaid.runtime.Util.call(vAr472$plaid, vAr473$plaid);
														{
															final plaid.runtime.PlaidObject vAr160$plaid;
															final plaid.runtime.PlaidObject vAr479$plaid;
															vAr479$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
															final plaid.runtime.PlaidObject vAr480$plaid;
															vAr480$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertTrue", vAr479$plaid);
															final plaid.runtime.PlaidObject vAr481$plaid;
															final plaid.runtime.PlaidObject vAr482$plaid;
															vAr482$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
															final plaid.runtime.PlaidObject vAr483$plaid;
															vAr483$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("gt$plaid", vAr482$plaid);
															final plaid.runtime.PlaidObject vAr484$plaid;
															vAr484$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
															final plaid.runtime.PlaidObject vAr485$plaid;
															vAr481$plaid = plaid.runtime.Util.call(vAr483$plaid, vAr484$plaid);
															final plaid.runtime.PlaidObject vAr486$plaid;
															vAr160$plaid = plaid.runtime.Util.call(vAr480$plaid, vAr481$plaid);
															{
																final plaid.runtime.PlaidObject vAr159$plaid;
																final plaid.runtime.PlaidObject vAr487$plaid;
																vAr487$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																final plaid.runtime.PlaidObject vAr488$plaid;
																vAr488$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertFalse", vAr487$plaid);
																final plaid.runtime.PlaidObject vAr489$plaid;
																final plaid.runtime.PlaidObject vAr490$plaid;
																vAr490$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
																final plaid.runtime.PlaidObject vAr491$plaid;
																vAr491$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lt$plaid", vAr490$plaid);
																final plaid.runtime.PlaidObject vAr492$plaid;
																vAr492$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																final plaid.runtime.PlaidObject vAr493$plaid;
																vAr489$plaid = plaid.runtime.Util.call(vAr491$plaid, vAr492$plaid);
																final plaid.runtime.PlaidObject vAr494$plaid;
																vAr159$plaid = plaid.runtime.Util.call(vAr488$plaid, vAr489$plaid);
																{
																	final plaid.runtime.PlaidObject vAr158$plaid;
																	final plaid.runtime.PlaidObject vAr495$plaid;
																	vAr495$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																	final plaid.runtime.PlaidObject vAr496$plaid;
																	vAr496$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertFalse", vAr495$plaid);
																	final plaid.runtime.PlaidObject vAr497$plaid;
																	final plaid.runtime.PlaidObject vAr498$plaid;
																	vAr498$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																	final plaid.runtime.PlaidObject vAr499$plaid;
																	vAr499$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("gt$plaid", vAr498$plaid);
																	final plaid.runtime.PlaidObject vAr500$plaid;
																	vAr500$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
																	final plaid.runtime.PlaidObject vAr501$plaid;
																	vAr497$plaid = plaid.runtime.Util.call(vAr499$plaid, vAr500$plaid);
																	final plaid.runtime.PlaidObject vAr502$plaid;
																	vAr158$plaid = plaid.runtime.Util.call(vAr496$plaid, vAr497$plaid);
																	{
																		final plaid.runtime.PlaidObject vAr157$plaid;
																		final plaid.runtime.PlaidObject vAr503$plaid;
																		vAr503$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																		final plaid.runtime.PlaidObject vAr504$plaid;
																		vAr504$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertFalse", vAr503$plaid);
																		final plaid.runtime.PlaidObject vAr505$plaid;
																		final plaid.runtime.PlaidObject vAr506$plaid;
																		vAr506$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																		final plaid.runtime.PlaidObject vAr507$plaid;
																		vAr507$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lt$plaid", vAr506$plaid);
																		final plaid.runtime.PlaidObject vAr508$plaid;
																		vAr508$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("false$plaid", local$c0pe);
																		final plaid.runtime.PlaidObject vAr509$plaid;
																		vAr505$plaid = plaid.runtime.Util.call(vAr507$plaid, vAr508$plaid);
																		final plaid.runtime.PlaidObject vAr510$plaid;
																		vAr157$plaid = plaid.runtime.Util.call(vAr504$plaid, vAr505$plaid);
																		final plaid.runtime.PlaidObject vAr511$plaid;
																		vAr511$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ctx", local$c0pe);
																		final plaid.runtime.PlaidObject vAr512$plaid;
																		vAr512$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("assertFalse", vAr511$plaid);
																		final plaid.runtime.PlaidObject vAr513$plaid;
																		final plaid.runtime.PlaidObject vAr514$plaid;
																		vAr514$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
																		final plaid.runtime.PlaidObject vAr515$plaid;
																		vAr515$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lt$plaid", vAr514$plaid);
																		final plaid.runtime.PlaidObject vAr516$plaid;
																		vAr516$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
																		final plaid.runtime.PlaidObject vAr517$plaid;
																		vAr513$plaid = plaid.runtime.Util.call(vAr515$plaid, vAr516$plaid);
																		final plaid.runtime.PlaidObject vAr518$plaid;
																		vAr470$plaid = plaid.runtime.Util.tailCall(vAr512$plaid,vAr513$plaid);
																		
																	}
																	
																}
																
															}
															
														}
														
													}
													return vAr470$plaid;
													
												}
												
											}
											);
											return vAr467$plaid;
											
										}
										
									}
									);
									final plaid.runtime.PlaidMemberDef vAr519$plaid;
									vAr519$plaid = plaid.runtime.Util.memberDef("fst", vAr466$plaid, false, false);
									vAr464$plaid.addMember(vAr519$plaid,vAr468$plaid);
									@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
									final plaid.runtime.PlaidObject vAr521$plaid;
									vAr521$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
										public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr522$plaid) {
											final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
											final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
											final plaid.runtime.PlaidObject vAr520$plaid;
											vAr520$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
											return vAr520$plaid;
											
										}
										
									}
									);
									final plaid.runtime.PlaidMemberDef vAr523$plaid;
									vAr523$plaid = plaid.runtime.Util.memberDef("snd", vAr466$plaid, false, false);
									vAr464$plaid.addMember(vAr523$plaid,vAr521$plaid);
									vAr460$plaid = vAr465$plaid.initState(vAr464$plaid);
									vAr457$plaid = vAr460$plaid.instantiate();
									return vAr457$plaid;
									
								}
								
							}
							);
							final plaid.runtime.PlaidMemberDef vAr524$plaid;
							vAr524$plaid = plaid.runtime.Util.memberDef("snd", vAr452$plaid, false, false);
							vAr450$plaid.addMember(vAr524$plaid,vAr458$plaid);
							vAr446$plaid = vAr451$plaid.initState(vAr450$plaid);
							vAr445$plaid = vAr446$plaid.instantiate();
							vAr257$plaid = plaid.runtime.Util.tailCall(vAr444$plaid,vAr445$plaid);
							
						}
						
					}
					
				}
				return vAr257$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr525$plaid;
		vAr525$plaid = plaid.runtime.Util.memberDef("createTests", BooleanTest$Tag$plaid, false, false);
		vAr254$plaid.addMember(vAr525$plaid,vAr256$plaid);
		vAr249$plaid = vAr253$plaid.with(vAr254$plaid);
		BooleanTest$Tag$plaid.nest(vAr249$plaid);
		BooleanTest = vAr249$plaid.getPrototype();
		
	}
	
}