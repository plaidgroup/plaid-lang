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
@plaid.runtime.annotations.RepresentsField(name = "if$plaid", toplevel = true)
public class if$plaid{
	public static java.util.List<plaid.runtime.utils.Import> vAr2$plaid;
	static {
		vAr2$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr2$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "if$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject if$plaid;
	static {
		if$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr3$plaid;
				plaid.runtime.PlaidObject vAr4$plaid;
				vAr4$plaid = bool;
				plaid.runtime.PlaidObject vAr5$plaid;
				plaid.runtime.PlaidObject vAr6$plaid;
				vAr6$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				plaid.runtime.PlaidObject vAr7$plaid;
				vAr7$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr6$plaid);
				plaid.runtime.PlaidObject vAr8$plaid;
				vAr8$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr7$plaid);
				vAr5$plaid = plaid.runtime.Util.toPlaidState(vAr8$plaid);
				if (vAr4$plaid.getStates().contains(vAr5$plaid)) {
					vAr3$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
							plaid.runtime.PlaidObject vAr9$plaid;
							plaid.runtime.PlaidObject vAr10$plaid;
							plaid.runtime.PlaidObject vAr11$plaid;
							vAr10$plaid = func;
							vAr11$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							vAr9$plaid = plaid.runtime.Util.call(vAr10$plaid, vAr11$plaid);
							return vAr9$plaid;
						}
					}
					);
				}
				else {
					plaid.runtime.PlaidObject vAr12$plaid;
					plaid.runtime.PlaidObject vAr13$plaid;
					vAr13$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					plaid.runtime.PlaidObject vAr14$plaid;
					vAr14$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr13$plaid);
					plaid.runtime.PlaidObject vAr15$plaid;
					vAr15$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr14$plaid);
					vAr12$plaid = plaid.runtime.Util.toPlaidState(vAr15$plaid);
					if (vAr4$plaid.getStates().contains(vAr12$plaid)) {
						vAr3$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
								plaid.runtime.PlaidObject vAr16$plaid;
								vAr16$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
								return vAr16$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr3$plaid;
			}
		}
		);
	}
}
