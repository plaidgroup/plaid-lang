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
@plaid.runtime.annotations.RepresentsState(name = "WhileState", toplevel = true)
public class WhileState{
	public static java.util.List<plaid.runtime.utils.Import> vAr52$plaid;
	static {
		vAr52$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr52$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "WhileState", toplevel = false)
	public static plaid.runtime.PlaidObject WhileState;
	static {
		WhileState = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
		plaid.runtime.PlaidObject vAr54$plaid;
		vAr54$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr55$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr53$plaid;
				vAr53$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr53$plaid;
			}
		}
		);
		WhileState.addMember("condition",vAr54$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
		plaid.runtime.PlaidObject vAr57$plaid;
		vAr57$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr58$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr56$plaid;
				vAr56$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr56$plaid;
			}
		}
		);
		WhileState.addMember("whileBody",vAr57$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "whileLoop", toplevel = false)
		plaid.runtime.PlaidObject vAr59$plaid;
		vAr59$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject result) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr60$plaid;
				{
					final plaid.runtime.PlaidObject test;
					plaid.runtime.PlaidObject vAr61$plaid;
					plaid.runtime.PlaidObject vAr62$plaid;
					vAr61$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("condition", current$c0pe);
					vAr62$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					test = plaid.runtime.Util.call(vAr61$plaid, vAr62$plaid);
					plaid.runtime.PlaidObject vAr63$plaid;
					vAr63$plaid = test;
					plaid.runtime.PlaidObject vAr64$plaid;
					plaid.runtime.PlaidObject vAr65$plaid;
					vAr65$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					plaid.runtime.PlaidObject vAr66$plaid;
					vAr66$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr65$plaid);
					plaid.runtime.PlaidObject vAr67$plaid;
					vAr67$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr66$plaid);
					vAr64$plaid = plaid.runtime.Util.toPlaidState(vAr67$plaid);
					if (vAr63$plaid.getStates().contains(vAr64$plaid)) {
						final plaid.runtime.PlaidObject t;
						t = vAr63$plaid;
						{
							final plaid.runtime.PlaidObject newResult;
							plaid.runtime.PlaidObject vAr68$plaid;
							plaid.runtime.PlaidObject vAr69$plaid;
							vAr68$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileBody", current$c0pe);
							vAr69$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							newResult = plaid.runtime.Util.call(vAr68$plaid, vAr69$plaid);
							plaid.runtime.PlaidObject vAr70$plaid;
							plaid.runtime.PlaidObject vAr71$plaid;
							vAr70$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", current$c0pe);
							vAr71$plaid = newResult;
							vAr60$plaid = plaid.runtime.Util.call(vAr70$plaid, vAr71$plaid);
						}
					}
					else {
						plaid.runtime.PlaidObject vAr72$plaid;
						plaid.runtime.PlaidObject vAr73$plaid;
						vAr73$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
						plaid.runtime.PlaidObject vAr74$plaid;
						vAr74$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr73$plaid);
						plaid.runtime.PlaidObject vAr75$plaid;
						vAr75$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr74$plaid);
						vAr72$plaid = plaid.runtime.Util.toPlaidState(vAr75$plaid);
						if (vAr63$plaid.getStates().contains(vAr72$plaid)) {
							final plaid.runtime.PlaidObject f;
							f = vAr63$plaid;
							vAr60$plaid = result;
						}
						else {
							throw new RuntimeException("Pattern match exhausted.");
						}
					}
				}
				return vAr60$plaid;
			}
		}
		);
		WhileState.addMember("whileLoop",vAr59$plaid);
	}
}
