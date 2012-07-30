package plaid.demo;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsState(name = "PlaidCell", inPackage = "plaid.demo", toplevel = true, jsonRep = "{\"member_type\":\"state\",\"name\":\"PlaidCell\",\"members\":[{\"member_type\":\"method\",\"name\":\"toggle\"},{\"member_type\":\"method\",\"name\":\"isAlive\"}]}")
public class PlaidCell{
	public static final java.util.List<plaid.runtime.utils.Import> vAr14$plaid;
	static {
		vAr14$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.IntegerTest"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int64"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.JavaInteropTest"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Unknown"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.CharacterTest"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Hashable"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeSome"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Comparable"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Empty"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdLessThan"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Decimal"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Float64"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Alive"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int8Test"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.BooleanTest"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Number"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int32"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OptionTest"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int64Test"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int32Test"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Bhava"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Lonely"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Printable"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Liveness"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdGreaterThan"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Happy"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.refEq"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Dead"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int8"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Orderable"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Known"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Rational"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Ordering"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.NativeNumber"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Fertile"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.PlaidCell"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdEqual"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Character"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int16"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrderProvider"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.makeStartCell"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Float32"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int16Test"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		vAr14$plaid.add(new plaid.runtime.utils.Import("plaid.demo.Crowded"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.demo",vAr14$plaid);
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.demo.PlaidCell")
	public static final plaid.runtime.PlaidTag PlaidCell$Tag$plaid;
	static {
		final plaid.runtime.PlaidTag vAr16$plaid;
		vAr16$plaid = null;
		PlaidCell$Tag$plaid = plaid.runtime.Util.tag("PlaidCell", "plaid.demo", vAr16$plaid);
		
	}
	@plaid.runtime.annotations.RepresentsState(name = "PlaidCell", toplevel = false)
	public static final plaid.runtime.PlaidObject PlaidCell;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		final plaid.runtime.PlaidState vAr15$plaid;
		final plaid.runtime.PlaidState vAr17$plaid;
		final plaid.runtime.PlaidState vAr18$plaid;
		vAr17$plaid = plaid.runtime.Util.newState();
		final plaid.runtime.PlaidState vAr19$plaid;
		final plaid.runtime.PlaidState vAr20$plaid;
		final plaid.runtime.PlaidObject vAr21$plaid;
		vAr21$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Liveness", global$c0pe);
		vAr19$plaid = plaid.runtime.Util.toPlaidState(vAr21$plaid);
		final plaid.runtime.PlaidObject vAr22$plaid;
		vAr22$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Bhava", global$c0pe);
		vAr20$plaid = plaid.runtime.Util.toPlaidState(vAr22$plaid);
		vAr18$plaid = vAr19$plaid.with(vAr20$plaid);
		vAr15$plaid = vAr17$plaid.with(vAr18$plaid);
		PlaidCell$Tag$plaid.nest(vAr15$plaid);
		PlaidCell = vAr15$plaid.getPrototype();
		
	}
	
}