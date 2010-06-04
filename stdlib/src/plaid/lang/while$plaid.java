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
	public static final java.util.List<plaid.runtime.utils.Import> vAr283$plaid;
	static {
		vAr283$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr283$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr283$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "while$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject while$plaid;
	static {
		while$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject cond) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr284$plaid;
				vAr284$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject body) throws plaid.runtime.PlaidException {
						final plaid.runtime.PlaidObject vAr285$plaid;
						final plaid.runtime.PlaidObject vAr286$plaid;
						final plaid.runtime.PlaidObject vAr287$plaid;
						final plaid.runtime.PlaidObject vAr288$plaid;
						final plaid.runtime.PlaidState vAr289$plaid;
						final plaid.runtime.PlaidState vAr290$plaid;
						final plaid.runtime.PlaidState vAr291$plaid;
						final plaid.runtime.PlaidObject vAr292$plaid;
						vAr292$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("WhileState", current$c0pe);
						vAr290$plaid = plaid.runtime.Util.toPlaidState(vAr292$plaid);
						vAr291$plaid = plaid.runtime.Util.newState();
						@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
						final plaid.runtime.PlaidObject vAr294$plaid;
						vAr294$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr295$plaid) {
								plaid.runtime.PlaidScope vAr296$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr296$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr293$plaid;
								vAr293$plaid = cond;
								return vAr293$plaid;
							}
						}
						);
						vAr291$plaid.addMember("condition",vAr294$plaid);
						@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
						final plaid.runtime.PlaidObject vAr298$plaid;
						vAr298$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr299$plaid) {
								plaid.runtime.PlaidScope vAr300$plaid = current$c0pe;
								final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr300$plaid, this$plaid);
								final plaid.runtime.PlaidObject vAr297$plaid;
								vAr297$plaid = body;
								return vAr297$plaid;
							}
						}
						);
						vAr291$plaid.addMember("whileBody",vAr298$plaid);
						vAr289$plaid = vAr290$plaid.with(vAr291$plaid);
						vAr288$plaid = vAr289$plaid.instantiate();
						vAr286$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", vAr288$plaid);
						vAr287$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr285$plaid = plaid.runtime.Util.call(vAr286$plaid, vAr287$plaid);
						return vAr285$plaid;
					}
				}
				);
				return vAr284$plaid;
			}
		}
		);
	}
}
