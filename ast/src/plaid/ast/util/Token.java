package plaid.ast.util;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsState(name = "Token", inPackage = "plaid.ast.util", toplevel = true, jsonRep = "{\"member_type\":\"state\",\"name\":\"Token\",\"members\":[{\"member_type\":\"field\",\"name\":\"beginLine\"},{\"member_type\":\"field\",\"name\":\"beginColumn\"},{\"member_type\":\"field\",\"name\":\"endLine\"},{\"member_type\":\"field\",\"name\":\"endColumn\"},{\"member_type\":\"field\",\"name\":\"image\"},{\"member_type\":\"method\",\"name\":\"errorOutput\"}]}")
public class Token{
	public static final java.util.List<plaid.runtime.utils.Import> vAr2861$plaid;
	static {
		vAr2861$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Double"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Set"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.ast.util.makeListFromJavaCollection"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.ast.util.Token"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr2861$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.ast.util",vAr2861$plaid);
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.ast.util.Token")
	public static final plaid.runtime.PlaidTag Token$Tag$plaid;
	static {
		final plaid.runtime.PlaidTag vAr2863$plaid;
		vAr2863$plaid = null;
		Token$Tag$plaid = plaid.runtime.Util.tag("Token", "plaid.ast.util", vAr2863$plaid);
		
	}
	@plaid.runtime.annotations.RepresentsState(name = "Token", toplevel = false)
	public static final plaid.runtime.PlaidObject Token;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		final plaid.runtime.PlaidState vAr2862$plaid;
		vAr2862$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "beginLine", toplevel = false)
		final plaid.runtime.PlaidObject vAr2865$plaid;
		vAr2865$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr2867$plaid;
		vAr2867$plaid = plaid.runtime.Util.memberDef("beginLine", Token$Tag$plaid, false, false);
		vAr2862$plaid.addMember(vAr2867$plaid,vAr2865$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "beginColumn", toplevel = false)
		final plaid.runtime.PlaidObject vAr2869$plaid;
		vAr2869$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr2871$plaid;
		vAr2871$plaid = plaid.runtime.Util.memberDef("beginColumn", Token$Tag$plaid, false, false);
		vAr2862$plaid.addMember(vAr2871$plaid,vAr2869$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "endLine", toplevel = false)
		final plaid.runtime.PlaidObject vAr2873$plaid;
		vAr2873$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr2875$plaid;
		vAr2875$plaid = plaid.runtime.Util.memberDef("endLine", Token$Tag$plaid, false, false);
		vAr2862$plaid.addMember(vAr2875$plaid,vAr2873$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "endColumn", toplevel = false)
		final plaid.runtime.PlaidObject vAr2877$plaid;
		vAr2877$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr2879$plaid;
		vAr2879$plaid = plaid.runtime.Util.memberDef("endColumn", Token$Tag$plaid, false, false);
		vAr2862$plaid.addMember(vAr2879$plaid,vAr2877$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "image", toplevel = false)
		final plaid.runtime.PlaidObject vAr2881$plaid;
		vAr2881$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr2883$plaid;
		vAr2883$plaid = plaid.runtime.Util.memberDef("image", Token$Tag$plaid, false, false);
		vAr2862$plaid.addMember(vAr2883$plaid,vAr2881$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "errorOutput", toplevel = false)
		final plaid.runtime.PlaidObject vAr2884$plaid;
		vAr2884$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(Token$Tag$plaid.getPath()+ "." + "errorOutput", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr2885$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr2886$plaid;
				final plaid.runtime.PlaidObject vAr2887$plaid;
				final plaid.runtime.PlaidObject vAr2888$plaid;
				final plaid.runtime.PlaidObject vAr2889$plaid;
				vAr2889$plaid = plaid.runtime.Util.string("(line: ");
				final plaid.runtime.PlaidObject vAr2890$plaid;
				vAr2890$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plus$plaid", vAr2889$plaid);
				final plaid.runtime.PlaidObject vAr2891$plaid;
				final plaid.runtime.PlaidObject vAr2892$plaid;
				vAr2892$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr2891$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("beginLine", vAr2892$plaid);
				final plaid.runtime.PlaidObject vAr2893$plaid;
				vAr2888$plaid = plaid.runtime.Util.call(vAr2890$plaid, vAr2891$plaid);
				final plaid.runtime.PlaidObject vAr2894$plaid;
				vAr2894$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plus$plaid", vAr2888$plaid);
				final plaid.runtime.PlaidObject vAr2895$plaid;
				vAr2895$plaid = plaid.runtime.Util.string(",Column: ");
				final plaid.runtime.PlaidObject vAr2896$plaid;
				vAr2887$plaid = plaid.runtime.Util.call(vAr2894$plaid, vAr2895$plaid);
				final plaid.runtime.PlaidObject vAr2897$plaid;
				vAr2897$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plus$plaid", vAr2887$plaid);
				final plaid.runtime.PlaidObject vAr2898$plaid;
				final plaid.runtime.PlaidObject vAr2899$plaid;
				vAr2899$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr2898$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("beginColumn", vAr2899$plaid);
				final plaid.runtime.PlaidObject vAr2900$plaid;
				vAr2886$plaid = plaid.runtime.Util.call(vAr2897$plaid, vAr2898$plaid);
				final plaid.runtime.PlaidObject vAr2901$plaid;
				vAr2901$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plus$plaid", vAr2886$plaid);
				final plaid.runtime.PlaidObject vAr2902$plaid;
				vAr2902$plaid = plaid.runtime.Util.string(")");
				final plaid.runtime.PlaidObject vAr2903$plaid;
				vAr2885$plaid = plaid.runtime.Util.tailCall(vAr2901$plaid,vAr2902$plaid);
				return vAr2885$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr2904$plaid;
		vAr2904$plaid = plaid.runtime.Util.memberDef("errorOutput", Token$Tag$plaid, false, false);
		vAr2862$plaid.addMember(vAr2904$plaid,vAr2884$plaid);
		Token$Tag$plaid.nest(vAr2862$plaid);
		Token = vAr2862$plaid.getPrototype();
		
	}
	
}