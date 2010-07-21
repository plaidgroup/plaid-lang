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
	public static final java.util.List<plaid.runtime.utils.Import> vAr300$plaid;
	static {
		vAr300$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr300$plaid",vAr300$plaid);
		vAr300$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
		vAr300$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr300$plaid);
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
				plaid.runtime.PlaidObject vAr299$plaid=null;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",22,17);
				{
					final plaid.runtime.PlaidObject f;
					final plaid.runtime.PlaidObject vAr301$plaid;
					final plaid.runtime.PlaidObject vAr302$plaid;
					final plaid.runtime.PlaidObject vAr303$plaid;
					vAr303$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr303$plaid",vAr303$plaid);
					vAr301$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr303$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr301$plaid",vAr301$plaid);
					vAr302$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr302$plaid",vAr302$plaid);
					f = plaid.runtime.Util.call(vAr301$plaid, vAr302$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("f",f);
					if (f instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("f", f, true);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",22,19);
					{
						final plaid.runtime.PlaidObject s;
						final plaid.runtime.PlaidObject vAr304$plaid;
						final plaid.runtime.PlaidObject vAr305$plaid;
						final plaid.runtime.PlaidObject vAr306$plaid;
						final plaid.runtime.PlaidObject vAr307$plaid;
						final plaid.runtime.PlaidObject vAr308$plaid;
						final plaid.runtime.PlaidObject vAr309$plaid;
						vAr309$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr309$plaid",vAr309$plaid);
						vAr307$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr309$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr307$plaid",vAr307$plaid);
						vAr308$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr308$plaid",vAr308$plaid);
						vAr306$plaid = plaid.runtime.Util.call(vAr307$plaid, vAr308$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr306$plaid",vAr306$plaid);
						vAr304$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr306$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr304$plaid",vAr304$plaid);
						vAr305$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr305$plaid",vAr305$plaid);
						s = plaid.runtime.Util.call(vAr304$plaid, vAr305$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("s",s);
						if (s instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("s", s, true);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",23,9);
						final plaid.runtime.PlaidState vAr310$plaid;
						final plaid.runtime.PlaidObject vAr311$plaid;
						vAr311$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", global$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr311$plaid",vAr311$plaid);
						final plaid.runtime.PlaidState vAr313$plaid;
						vAr313$plaid = plaid.runtime.Util.toPlaidState(vAr311$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr313$plaid",vAr313$plaid);
						final plaid.runtime.PlaidState vAr312$plaid;
						vAr312$plaid = plaid.runtime.Util.newState();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr312$plaid",vAr312$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",23,20);
						@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
						final plaid.runtime.PlaidObject vAr315$plaid;
						vAr315$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr316$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr314$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",23,26);
								vAr314$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("f", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr314$plaid",vAr314$plaid);
								return vAr314$plaid;
							}
						}
						);
						final plaid.runtime.PlaidMemberDef vAr317$plaid;
						vAr317$plaid = plaid.runtime.Util.anonymousMemberDef("fst", false, false);
						vAr312$plaid.addMember(vAr317$plaid,vAr315$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",23,29);
						@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
						final plaid.runtime.PlaidObject vAr319$plaid;
						vAr319$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr320$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr318$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/kbn/Documents/EclipseEnv/workspace/plaid-lang/stdlib/plaid/lang/makePair.plaid",23,35);
								vAr318$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("s", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr318$plaid",vAr318$plaid);
								return vAr318$plaid;
							}
						}
						);
						final plaid.runtime.PlaidMemberDef vAr321$plaid;
						vAr321$plaid = plaid.runtime.Util.anonymousMemberDef("snd", false, false);
						vAr312$plaid.addMember(vAr321$plaid,vAr319$plaid);
						vAr310$plaid = vAr313$plaid.initState(vAr312$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr310$plaid",vAr310$plaid);
						vAr299$plaid = vAr310$plaid.instantiate();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr299$plaid",vAr299$plaid);
					}
				}
				return vAr299$plaid;
			}
		}
		);
	}
}
