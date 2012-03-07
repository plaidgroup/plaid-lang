package plaid.examples.common.plist;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsState(name = "DataItem", inPackage = "plaid.examples.common.plist", toplevel = true, jsonRep = "{\"member_type\":\"state\",\"name\":\"DataItem\",\"members\":[{\"member_type\":\"field\",\"name\":\"value\"}]}")
public class DataItem{
	public static final java.util.List<plaid.runtime.utils.Import> vAr21$plaid;
	static {
		vAr21$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.IntegerTest"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int64"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.JavaInteropTest"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.CharacterTest"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Hashable"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeSome"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Comparable"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdLessThan"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Decimal"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Float64"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int8Test"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.BooleanTest"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.PListElement"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Number"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.init"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int32"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.DataItem"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OptionTest"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int64Test"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int32Test"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Printable"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdGreaterThan"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.refEq"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.PList"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.PListDataElement"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int8"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.PListData"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Orderable"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Rational"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Ordering"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.NativeNumber"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrdEqual"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Character"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.main"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.examples.common.plist.PListNilElement"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int16"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.OrderProvider"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Float32"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Int16Test"));
		vAr21$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.examples.common.plist",vAr21$plaid);
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.examples.common.plist.DataItem")
	public static final plaid.runtime.PlaidTag DataItem$Tag$plaid;
	static {
		final plaid.runtime.PlaidTag vAr28$plaid;
		final plaid.runtime.PlaidState vAr30$plaid;
		final plaid.runtime.PlaidObject vAr33$plaid;
		vAr33$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("PListData", global$c0pe);
		vAr30$plaid = plaid.runtime.Util.toPlaidState(vAr33$plaid);
		vAr28$plaid = vAr30$plaid.getTag();
		DataItem$Tag$plaid = plaid.runtime.Util.tag("DataItem", "plaid.examples.common.plist", vAr28$plaid);
		
	}
	@plaid.runtime.annotations.RepresentsState(name = "DataItem", toplevel = false)
	public static final plaid.runtime.PlaidObject DataItem;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		final plaid.runtime.PlaidState vAr23$plaid;
		final plaid.runtime.PlaidState vAr34$plaid;
		final plaid.runtime.PlaidObject vAr36$plaid;
		vAr36$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("PListData", global$c0pe);
		vAr34$plaid = plaid.runtime.Util.toPlaidState(vAr36$plaid);
		final plaid.runtime.PlaidState vAr35$plaid;
		vAr35$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr38$plaid;
		vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr39$plaid) {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				final plaid.runtime.PlaidObject vAr37$plaid;
				vAr37$plaid = plaid.runtime.Util.integer(new java.math.BigInteger("0"));
				return vAr37$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr40$plaid;
		vAr40$plaid = plaid.runtime.Util.memberDef("value", DataItem$Tag$plaid, true, false);
		vAr35$plaid.addMember(vAr40$plaid,vAr38$plaid);
		vAr23$plaid = vAr34$plaid.with(vAr35$plaid);
		DataItem$Tag$plaid.nest(vAr23$plaid);
		DataItem = vAr23$plaid.getPrototype();
		
	}
	
}