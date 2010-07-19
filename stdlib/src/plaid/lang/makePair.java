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
	public static final java.util.List<plaid.runtime.utils.Import> vAr397$plaid;
	static {
		vAr397$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr397$plaid",vAr397$plaid);
		vAr397$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
		vAr397$plaid.add(new plaid.runtime.utils.Import("plaid.lang.globals.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr397$plaid);
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
				plaid.runtime.PlaidObject vAr396$plaid=null;
				plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",34,19);
				{
					final plaid.runtime.PlaidObject s;
					final plaid.runtime.PlaidObject vAr398$plaid;
					final plaid.runtime.PlaidObject vAr399$plaid;
					final plaid.runtime.PlaidObject vAr400$plaid;
					vAr400$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr400$plaid",vAr400$plaid);
					vAr398$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("first", vAr400$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr398$plaid",vAr398$plaid);
					vAr399$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr399$plaid",vAr399$plaid);
					s = plaid.runtime.Util.call(vAr398$plaid, vAr399$plaid);
					plaid.runtime.PlaidRuntime.getRuntime().updateVar("s",s);
					if (s instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
					else local$c0pe.insert("s", s, false);
					plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",34,17);
					{
						final plaid.runtime.PlaidObject f;
						final plaid.runtime.PlaidObject vAr401$plaid;
						final plaid.runtime.PlaidObject vAr402$plaid;
						final plaid.runtime.PlaidObject vAr403$plaid;
						vAr403$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("pA1R$plaid", local$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr403$plaid",vAr403$plaid);
						vAr401$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("second", vAr403$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr401$plaid",vAr401$plaid);
						vAr402$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr402$plaid",vAr402$plaid);
						f = plaid.runtime.Util.call(vAr401$plaid, vAr402$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("f",f);
						if (f instanceof plaid.runtime.models.map.PlaidLookupMap) throw new plaid.runtime.PlaidException("No object found to insert into scope");
						else local$c0pe.insert("f", f, false);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,9);
						final plaid.runtime.PlaidState vAr404$plaid;
						final plaid.runtime.PlaidObject vAr405$plaid;
						vAr405$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Pair", global$c0pe);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr405$plaid",vAr405$plaid);
						final plaid.runtime.PlaidState vAr407$plaid;
						vAr407$plaid = plaid.runtime.Util.toPlaidState(vAr405$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr407$plaid",vAr407$plaid);
						final plaid.runtime.PlaidState vAr406$plaid;
						vAr406$plaid = plaid.runtime.Util.newState();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr406$plaid",vAr406$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,20);
						@plaid.runtime.annotations.RepresentsField(name = "fst", toplevel = false)
						final plaid.runtime.PlaidObject vAr409$plaid;
						vAr409$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr410$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr408$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,26);
								vAr408$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("f", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr408$plaid",vAr408$plaid);
								return vAr408$plaid;
							}
						}
						);
						final plaid.runtime.PlaidMemberDef vAr411$plaid;
						vAr411$plaid = plaid.runtime.Util.anonymousMemberDef("fst", false, false);
						vAr406$plaid.addMember(vAr411$plaid,vAr409$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,29);
						@plaid.runtime.annotations.RepresentsField(name = "snd", toplevel = false)
						final plaid.runtime.PlaidObject vAr413$plaid;
						vAr413$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr414$plaid) {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().localScope(temp$c0pe);
								final plaid.runtime.PlaidObject vAr412$plaid;
								plaid.runtime.PlaidRuntime.getRuntime().updateLocation("/Users/mhahnenberg/Desktop/plaid-lang/stdlib/src/plaid/lang/Pair.plaid",35,35);
								vAr412$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("s", local$c0pe);
								plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr412$plaid",vAr412$plaid);
								return vAr412$plaid;
							}
						}
						);
						final plaid.runtime.PlaidMemberDef vAr415$plaid;
						vAr415$plaid = plaid.runtime.Util.anonymousMemberDef("snd", false, false);
						vAr406$plaid.addMember(vAr415$plaid,vAr413$plaid);
						vAr404$plaid = vAr407$plaid.initState(vAr406$plaid);
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr404$plaid",vAr404$plaid);
						vAr396$plaid = vAr404$plaid.instantiate();
						plaid.runtime.PlaidRuntime.getRuntime().updateVar("vAr396$plaid",vAr396$plaid);
					}
				}
				return vAr396$plaid;
			}
		}
		);
	}
}
