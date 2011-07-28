package plaid.io;
@SuppressWarnings("unused")
@plaid.runtime.annotations.RepresentsState(name = "FileObj", inPackage = "plaid.io", toplevel = true, jsonRep = "{\"member_type\":\"state\",\"name\":\"FileObj\",\"members\":[{\"member_type\":\"field\",\"name\":\"file\"},{\"member_type\":\"field\",\"name\":\"out\"},{\"member_type\":\"method\",\"name\":\"setName\"},{\"member_type\":\"method\",\"name\":\"exists\"},{\"member_type\":\"method\",\"name\":\"delete\"},{\"member_type\":\"method\",\"name\":\"canWrite\"},{\"member_type\":\"method\",\"name\":\"mkdir\"},{\"member_type\":\"method\",\"name\":\"mkdirs\"},{\"member_type\":\"method\",\"name\":\"beginWrite\"},{\"member_type\":\"method\",\"name\":\"write\"},{\"member_type\":\"method\",\"name\":\"writeLine\"},{\"member_type\":\"method\",\"name\":\"endWrite\"}]}")
public class FileObj{
	public static final java.util.List<plaid.runtime.utils.Import> vAr75$plaid;
	static {
		vAr75$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Stack"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Double"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Some"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.ifElse"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Cons"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Pair"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Option"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.printLine"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.False"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Unit"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.io.FileObj"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.While"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeListFromJava"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.None"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Set"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.List"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Object"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Nil"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makePair"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.makeEmptyList"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.mapsEqual"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.createSome"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.io.File"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.plaidListToJavaList"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.true$plaid"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Boolean"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.while$plaid"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.NONE"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.print"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.if$plaid"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.false$plaid"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.ListCell"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.State"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.String"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.True"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("plaid.lang.Integer"));
		vAr75$plaid.add(new plaid.runtime.utils.Import("java.io.*"));
		
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.io",vAr75$plaid);
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.io.FileObj")
	public static final plaid.runtime.PlaidTag FileObj$Tag$plaid;
	static {
		final plaid.runtime.PlaidTag vAr77$plaid;
		vAr77$plaid = null;
		FileObj$Tag$plaid = plaid.runtime.Util.tag("FileObj", "plaid.io", vAr77$plaid);
		
	}
	@plaid.runtime.annotations.RepresentsState(name = "FileObj", toplevel = false)
	public static final plaid.runtime.PlaidObject FileObj;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		final plaid.runtime.PlaidState vAr76$plaid;
		vAr76$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "file", toplevel = false)
		final plaid.runtime.PlaidObject vAr79$plaid;
		vAr79$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr81$plaid;
		vAr81$plaid = plaid.runtime.Util.memberDef("file", FileObj$Tag$plaid, true, false);
		vAr76$plaid.addMember(vAr81$plaid,vAr79$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "out", toplevel = false)
		final plaid.runtime.PlaidObject vAr83$plaid;
		vAr83$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
		final plaid.runtime.PlaidMemberDef vAr85$plaid;
		vAr85$plaid = plaid.runtime.Util.memberDef("out", FileObj$Tag$plaid, true, false);
		vAr76$plaid.addMember(vAr85$plaid,vAr83$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "setName", toplevel = false)
		final plaid.runtime.PlaidObject vAr86$plaid;
		vAr86$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "setName", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject string) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (string instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("string", string, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr87$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject vAr44$plaid;
					final plaid.runtime.PlaidObject vAr88$plaid;
					final plaid.runtime.PlaidObject vAr89$plaid;
					final plaid.runtime.PlaidObject vAr90$plaid;
					final plaid.runtime.PlaidObject vAr91$plaid;
					vAr91$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("java", local$c0pe);
					vAr90$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("io", vAr91$plaid);
					vAr89$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("File", vAr90$plaid);
					final plaid.runtime.PlaidObject vAr92$plaid;
					vAr92$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr89$plaid);
					final plaid.runtime.PlaidObject vAr93$plaid;
					vAr93$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("string", local$c0pe);
					final plaid.runtime.PlaidObject vAr94$plaid;
					vAr88$plaid = plaid.runtime.Util.call(vAr92$plaid, vAr93$plaid);
					final plaid.runtime.PlaidObject vAr95$plaid;
					vAr95$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
					vAr95$plaid.updateMember("file",vAr88$plaid);
					vAr44$plaid = vAr88$plaid;
					vAr87$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("true$plaid", local$c0pe);
					
				}
				return vAr87$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr96$plaid;
		vAr96$plaid = plaid.runtime.Util.memberDef("setName", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr96$plaid,vAr86$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "exists", toplevel = false)
		final plaid.runtime.PlaidObject vAr97$plaid;
		vAr97$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "exists", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr98$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr99$plaid;
				final plaid.runtime.PlaidObject vAr100$plaid;
				vAr100$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr99$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("file", vAr100$plaid);
				final plaid.runtime.PlaidObject vAr101$plaid;
				vAr101$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("exists", vAr99$plaid);
				final plaid.runtime.PlaidObject vAr102$plaid;
				vAr102$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr103$plaid;
				vAr98$plaid = plaid.runtime.Util.tailCall(vAr101$plaid,vAr102$plaid);
				return vAr98$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr104$plaid;
		vAr104$plaid = plaid.runtime.Util.memberDef("exists", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr104$plaid,vAr97$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "delete", toplevel = false)
		final plaid.runtime.PlaidObject vAr105$plaid;
		vAr105$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "delete", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject string) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (string instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("string", string, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr106$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr107$plaid;
				final plaid.runtime.PlaidObject vAr108$plaid;
				vAr108$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr107$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("file", vAr108$plaid);
				final plaid.runtime.PlaidObject vAr109$plaid;
				vAr109$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("delete", vAr107$plaid);
				final plaid.runtime.PlaidObject vAr110$plaid;
				vAr110$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr111$plaid;
				vAr106$plaid = plaid.runtime.Util.tailCall(vAr109$plaid,vAr110$plaid);
				return vAr106$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr112$plaid;
		vAr112$plaid = plaid.runtime.Util.memberDef("delete", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr112$plaid,vAr105$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "canWrite", toplevel = false)
		final plaid.runtime.PlaidObject vAr113$plaid;
		vAr113$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "canWrite", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr114$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr115$plaid;
				final plaid.runtime.PlaidObject vAr116$plaid;
				vAr116$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr115$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("file", vAr116$plaid);
				final plaid.runtime.PlaidObject vAr117$plaid;
				vAr117$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("canWrite", vAr115$plaid);
				final plaid.runtime.PlaidObject vAr118$plaid;
				vAr118$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr119$plaid;
				vAr114$plaid = plaid.runtime.Util.tailCall(vAr117$plaid,vAr118$plaid);
				return vAr114$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr120$plaid;
		vAr120$plaid = plaid.runtime.Util.memberDef("canWrite", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr120$plaid,vAr113$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "mkdir", toplevel = false)
		final plaid.runtime.PlaidObject vAr121$plaid;
		vAr121$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "mkdir", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr122$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr123$plaid;
				final plaid.runtime.PlaidObject vAr124$plaid;
				vAr124$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr123$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("file", vAr124$plaid);
				final plaid.runtime.PlaidObject vAr125$plaid;
				vAr125$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mkdir", vAr123$plaid);
				final plaid.runtime.PlaidObject vAr126$plaid;
				vAr126$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr127$plaid;
				vAr122$plaid = plaid.runtime.Util.tailCall(vAr125$plaid,vAr126$plaid);
				return vAr122$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr128$plaid;
		vAr128$plaid = plaid.runtime.Util.memberDef("mkdir", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr128$plaid,vAr121$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "mkdirs", toplevel = false)
		final plaid.runtime.PlaidObject vAr129$plaid;
		vAr129$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "mkdirs", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr130$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr131$plaid;
				final plaid.runtime.PlaidObject vAr132$plaid;
				vAr132$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr131$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("file", vAr132$plaid);
				final plaid.runtime.PlaidObject vAr133$plaid;
				vAr133$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("mkdirs", vAr131$plaid);
				final plaid.runtime.PlaidObject vAr134$plaid;
				vAr134$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr135$plaid;
				vAr130$plaid = plaid.runtime.Util.tailCall(vAr133$plaid,vAr134$plaid);
				return vAr130$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr136$plaid;
		vAr136$plaid = plaid.runtime.Util.memberDef("mkdirs", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr136$plaid,vAr129$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "beginWrite", toplevel = false)
		final plaid.runtime.PlaidObject vAr137$plaid;
		vAr137$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "beginWrite", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr138$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr139$plaid;
				final plaid.runtime.PlaidObject vAr140$plaid;
				vAr140$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("BufferedWriter", local$c0pe);
				final plaid.runtime.PlaidObject vAr141$plaid;
				vAr141$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr140$plaid);
				final plaid.runtime.PlaidObject vAr142$plaid;
				final plaid.runtime.PlaidObject vAr143$plaid;
				vAr143$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("FileWriter", local$c0pe);
				final plaid.runtime.PlaidObject vAr144$plaid;
				vAr144$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("new$plaid", vAr143$plaid);
				final plaid.runtime.PlaidObject vAr145$plaid;
				final plaid.runtime.PlaidObject vAr146$plaid;
				vAr146$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr145$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("file", vAr146$plaid);
				final plaid.runtime.PlaidObject vAr147$plaid;
				vAr142$plaid = plaid.runtime.Util.call(vAr144$plaid, vAr145$plaid);
				final plaid.runtime.PlaidObject vAr148$plaid;
				vAr139$plaid = plaid.runtime.Util.call(vAr141$plaid, vAr142$plaid);
				final plaid.runtime.PlaidObject vAr149$plaid;
				vAr149$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr149$plaid.updateMember("out",vAr139$plaid);
				vAr138$plaid = vAr139$plaid;
				return vAr138$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr150$plaid;
		vAr150$plaid = plaid.runtime.Util.memberDef("beginWrite", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr150$plaid,vAr137$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "write", toplevel = false)
		final plaid.runtime.PlaidObject vAr151$plaid;
		vAr151$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "write", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject string) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (string instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("string", string, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr152$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr153$plaid;
				final plaid.runtime.PlaidObject vAr154$plaid;
				vAr154$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr153$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr154$plaid);
				final plaid.runtime.PlaidObject vAr155$plaid;
				vAr155$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("write", vAr153$plaid);
				final plaid.runtime.PlaidObject vAr156$plaid;
				vAr156$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("string", local$c0pe);
				final plaid.runtime.PlaidObject vAr157$plaid;
				vAr152$plaid = plaid.runtime.Util.tailCall(vAr155$plaid,vAr156$plaid);
				return vAr152$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr158$plaid;
		vAr158$plaid = plaid.runtime.Util.memberDef("write", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr158$plaid,vAr151$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "writeLine", toplevel = false)
		final plaid.runtime.PlaidObject vAr159$plaid;
		vAr159$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "writeLine", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject string) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (string instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("string", string, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr160$plaid = plaid.runtime.Util.unit();
				{
					final plaid.runtime.PlaidObject vAr45$plaid;
					final plaid.runtime.PlaidObject vAr161$plaid;
					final plaid.runtime.PlaidObject vAr162$plaid;
					vAr162$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
					vAr161$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr162$plaid);
					final plaid.runtime.PlaidObject vAr163$plaid;
					vAr163$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("write", vAr161$plaid);
					final plaid.runtime.PlaidObject vAr164$plaid;
					vAr164$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("string", local$c0pe);
					final plaid.runtime.PlaidObject vAr165$plaid;
					vAr45$plaid = plaid.runtime.Util.call(vAr163$plaid, vAr164$plaid);
					final plaid.runtime.PlaidObject vAr166$plaid;
					final plaid.runtime.PlaidObject vAr167$plaid;
					vAr167$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
					vAr166$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr167$plaid);
					final plaid.runtime.PlaidObject vAr168$plaid;
					vAr168$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("newLine", vAr166$plaid);
					final plaid.runtime.PlaidObject vAr169$plaid;
					vAr169$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					final plaid.runtime.PlaidObject vAr170$plaid;
					vAr160$plaid = plaid.runtime.Util.tailCall(vAr168$plaid,vAr169$plaid);
					
				}
				return vAr160$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr171$plaid;
		vAr171$plaid = plaid.runtime.Util.memberDef("writeLine", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr171$plaid,vAr159$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "endWrite", toplevel = false)
		final plaid.runtime.PlaidObject vAr172$plaid;
		vAr172$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(FileObj$Tag$plaid.getPath()+ "." + "endWrite", new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject _) {
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
				if (_ instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("_", _, false);
				if (this$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("this$plaid", this$plaid, true);
				plaid.runtime.PlaidObject vAr173$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr174$plaid;
				final plaid.runtime.PlaidObject vAr175$plaid;
				vAr175$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this$plaid", local$c0pe);
				vAr174$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("out", vAr175$plaid);
				final plaid.runtime.PlaidObject vAr176$plaid;
				vAr176$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("close", vAr174$plaid);
				final plaid.runtime.PlaidObject vAr177$plaid;
				vAr177$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				final plaid.runtime.PlaidObject vAr178$plaid;
				vAr173$plaid = plaid.runtime.Util.tailCall(vAr176$plaid,vAr177$plaid);
				return vAr173$plaid;
				
			}
			
		}
		);
		final plaid.runtime.PlaidMemberDef vAr179$plaid;
		vAr179$plaid = plaid.runtime.Util.memberDef("endWrite", FileObj$Tag$plaid, false, false);
		vAr76$plaid.addMember(vAr179$plaid,vAr172$plaid);
		FileObj$Tag$plaid.nest(vAr76$plaid);
		FileObj = vAr76$plaid.getPrototype();
		
	}
	
}