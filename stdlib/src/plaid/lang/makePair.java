/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 *
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY;
 without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License 
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */

package plaid.lang;
@plaid.runtime.annotations.RepresentsMethod(name = "makePair", toplevel = true)
public class makePair{
	public static final java.util.List<plaid.runtime.utils.Import> vAr361$plaid;
	static {
		vAr361$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr361$plaid",vAr361$plaid);
		vAr361$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
		vAr361$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr361$plaid);
	@plaid.runtime.annotations.RepresentsMethod(name = "makePair", toplevel = false)
	public static final plaid.runtime.PlaidMethod makePair_func;
	static {
		final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(global$c0pe);
		makePair_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject pA1R$plaid) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
				if (pA1R$plaid instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
				else local$c0pe.insert("pA1R$plaid", pA1R$plaid, false);
				plaid.runtime.PlaidObject vAr360$plaid=null;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",34,17);
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr362$plaid;
					final plaid.runtime.PlaidObject vAr363$plaid;
					final plaid.runtime.PlaidObject vAr364$plaid;
					vAr364$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr364$plaid",vAr364$plaid);
					vAr362$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr364$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr362$plaid",vAr362$plaid);
					vAr363$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr363$plaid",vAr363$plaid);
					f = plaid.runtime.Util.call(vAr362$plaid, vAr363$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("f",f);
					if (f instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("f", f, true);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",34,19);
					{
						final plaid.runtime.PlaidObject s;
						final plaid.runtime.PlaidObject vAr365$plaid;
						final plaid.runtime.PlaidObject vAr366$plaid;
						final plaid.runtime.PlaidObject vAr367$plaid;
						final plaid.runtime.PlaidObject vAr368$plaid;
						final plaid.runtime.PlaidObject vAr369$plaid;
						final plaid.runtime.PlaidObject vAr370$plaid;
						vAr370$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr370$plaid",vAr370$plaid);
						vAr368$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr370$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr368$plaid",vAr368$plaid);
						vAr369$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr369$plaid",vAr369$plaid);
						vAr367$plaid = plaid.runtime.Util.call(vAr368$plaid, vAr369$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr367$plaid",vAr367$plaid);
						vAr365$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr367$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr365$plaid",vAr365$plaid);
						vAr366$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr366$plaid",vAr366$plaid);
						s = plaid.runtime.Util.call(vAr365$plaid, vAr366$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("s",s);
						if (s instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("s", s, true);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,9);
						final plaid.runtime.PlaidState vAr371$plaid;
						final plaid.runtime.PlaidObject vAr372$plaid;
						vAr372$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", global$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr372$plaid",vAr372$plaid);
						final plaid.runtime.PlaidState vAr374$plaid;
						vAr374$plaid = plaid.runtime.Util.toPlaidState(vAr372$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr374$plaid",vAr374$plaid);
						final plaid.runtime.PlaidState vAr373$plaid;
						vAr373$plaid = plaid.runtime.Util.newState();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr373$plaid",vAr373$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,20);
						@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
						final plaid.runtime.PlaidObject vAr376$plaid;
						vAr376$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr377$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr375$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,26);
								vAr375$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("f", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr375$plaid",vAr375$plaid);
								return vAr375$plaid;
							}
						}
						);
						final plaid.runtime.PlaidMemberDef vAr378$plaid;
						vAr378$plaid = plaid.runtime.Util.anonymousMemberDef("fst", false, false);
						vAr373$plaid.addMember(vAr378$plaid,vAr376$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,29);
						@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
						final plaid.runtime.PlaidObject vAr380$plaid;
						vAr380$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr381$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr379$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/workspace/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,35);
								vAr379$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("s", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr379$plaid",vAr379$plaid);
								return vAr379$plaid;
							}
						}
						);
						final plaid.runtime.PlaidMemberDef vAr382$plaid;
						vAr382$plaid = plaid.runtime.Util.anonymousMemberDef("snd", false, false);
						vAr373$plaid.addMember(vAr382$plaid,vAr380$plaid);
						vAr371$plaid = vAr374$plaid.initState(vAr373$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr371$plaid",vAr371$plaid);
						vAr360$plaid = vAr371$plaid.instantiate();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr360$plaid",vAr360$plaid);
					}
				}
				return vAr360$plaid;
			}
		}
		);
	}
}
