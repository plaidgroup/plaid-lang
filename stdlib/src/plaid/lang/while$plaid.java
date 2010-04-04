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
	public static final java.util.List<plaid.runtime.utils.Import> vAr48$plaid;
	static {
		vAr48$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr48$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "while$plaid", toplevel = false)
	public static final plaid.runtime.PlaidObject while$plaid;
	static {
		while$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject cond) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr49$plaid;
				vAr49$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject body) throws plaid.runtime.PlaidException {
						final plaid.runtime.PlaidObject vAr50$plaid;
						final plaid.runtime.PlaidObject vAr51$plaid;
						final plaid.runtime.PlaidObject vAr52$plaid;
						final plaid.runtime.PlaidObject vAr53$plaid;
						final plaid.runtime.PlaidState vAr54$plaid;
						final plaid.runtime.PlaidState vAr55$plaid;
						final plaid.runtime.PlaidState vAr56$plaid;
						final plaid.runtime.PlaidObject vAr57$plaid;
						vAr57$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("WhileState", current$c0pe);
						vAr55$plaid = plaid.runtime.Util.toPlaidState(vAr57$plaid);
						vAr56$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
						final plaid.runtime.PlaidObject vAr59$plaid;
						vAr59$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr60$plaid) {
								plaid.runtime.PlaidScope vAr61$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr61$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr58$plaid;
								vAr58$plaid = cond;
								return vAr58$plaid;
							}
						}
						);
						vAr56$plaid.addMember("condition",vAr59$plaid);
						@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
						final plaid.runtime.PlaidObject vAr63$plaid;
						vAr63$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr64$plaid) {
								plaid.runtime.PlaidScope vAr65$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr65$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr62$plaid;
								vAr62$plaid = body;
								return vAr62$plaid;
							}
						}
						);
						vAr56$plaid.addMember("whileBody",vAr63$plaid);
						vAr54$plaid = vAr55$plaid.with(vAr56$plaid);
						vAr53$plaid = vAr54$plaid.instantiate();
						vAr51$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", vAr53$plaid);
						vAr52$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr50$plaid = plaid.runtime.Util.call(vAr51$plaid, vAr52$plaid);
						return vAr50$plaid;
					}
				}
				);
				return vAr49$plaid;
			}
		}
		);
	}
}
