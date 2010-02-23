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
@plaid.runtime.annotations.RepresentsField(name = "while$plaid", toplevel = true)
public class while$plaid{
	public static java.util.List<plaid.runtime.utils.Import> vAr36$plaid;
	static {
		vAr36$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr36$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "while$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject while$plaid;
	static {
		while$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject cond) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr37$plaid;
				vAr37$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject body) throws plaid.runtime.PlaidException {
						plaid.runtime.PlaidObject vAr38$plaid;
						plaid.runtime.PlaidObject vAr39$plaid;
						plaid.runtime.PlaidObject vAr40$plaid;
						plaid.runtime.PlaidObject vAr41$plaid;
						plaid.runtime.PlaidState vAr42$plaid;
						plaid.runtime.PlaidState vAr43$plaid;
						plaid.runtime.PlaidState vAr44$plaid;
						plaid.runtime.PlaidObject vAr45$plaid;
						vAr45$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("WhileState", current$c0pe);
						vAr43$plaid = plaid.runtime.Util.toPlaidState(vAr45$plaid);
						vAr44$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
						plaid.runtime.PlaidObject vAr47$plaid;
						vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr48$plaid) {
								plaid.runtime.PlaidScope the$c0pe = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
								plaid.runtime.PlaidObject vAr46$plaid;
								vAr46$plaid = cond;
								return vAr46$plaid;
							}
						}
						);
						vAr44$plaid.addMember("condition",vAr47$plaid);
						@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
						plaid.runtime.PlaidObject vAr50$plaid;
						vAr50$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr51$plaid) {
								plaid.runtime.PlaidScope the$c0pe = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
								plaid.runtime.PlaidObject vAr49$plaid;
								vAr49$plaid = body;
								return vAr49$plaid;
							}
						}
						);
						vAr44$plaid.addMember("whileBody",vAr50$plaid);
						vAr42$plaid = vAr43$plaid.with(vAr44$plaid);
						vAr41$plaid = vAr42$plaid.instantiate();
						vAr39$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", vAr41$plaid);
						vAr40$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr38$plaid = plaid.runtime.Util.call(vAr39$plaid, vAr40$plaid);
						return vAr38$plaid;
					}
				}
				);
				return vAr37$plaid;
			}
		}
		);
	}
}
