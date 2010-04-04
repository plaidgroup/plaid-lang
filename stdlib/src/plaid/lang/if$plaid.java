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
	public static final java.util.List<plaid.runtime.utils.Import> vAr10$plaid;
	static {
		vAr10$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr10$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "if$plaid", toplevel = false)
	public static final plaid.runtime.PlaidObject if$plaid;
	static {
		if$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr11$plaid;
				final plaid.runtime.PlaidObject vAr12$plaid;
				vAr12$plaid = bool;
				final plaid.runtime.PlaidState vAr13$plaid;
				final plaid.runtime.PlaidObject vAr15$plaid;
				vAr15$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				final plaid.runtime.PlaidObject vAr16$plaid;
				vAr16$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr15$plaid);
				final plaid.runtime.PlaidObject vAr17$plaid;
				vAr17$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr16$plaid);
				vAr13$plaid = plaid.runtime.Util.toPlaidState(vAr17$plaid);
				final plaid.runtime.PlaidTag vAr14$plaid;
				if (vAr13$plaid.hasTag()) {
					vAr14$plaid = vAr13$plaid.getTag();
				}
				else {
					throw new plaid.runtime.PlaidException(vAr13$plaid.toString() + " is not assocaiated with a tag");
				}
				if (vAr12$plaid.getTags().contains(vAr14$plaid)) {
					vAr11$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
							final plaid.runtime.PlaidObject vAr18$plaid;
							final plaid.runtime.PlaidObject vAr19$plaid;
							final plaid.runtime.PlaidObject vAr20$plaid;
							vAr19$plaid = func;
							vAr20$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							vAr18$plaid = plaid.runtime.Util.call(vAr19$plaid, vAr20$plaid);
							return vAr18$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidState vAr21$plaid;
					final plaid.runtime.PlaidObject vAr23$plaid;
					vAr23$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr24$plaid;
					vAr24$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr23$plaid);
					final plaid.runtime.PlaidObject vAr25$plaid;
					vAr25$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr24$plaid);
					vAr21$plaid = plaid.runtime.Util.toPlaidState(vAr25$plaid);
					final plaid.runtime.PlaidTag vAr22$plaid;
					if (vAr21$plaid.hasTag()) {
						vAr22$plaid = vAr21$plaid.getTag();
					}
					else {
						throw new plaid.runtime.PlaidException(vAr21$plaid.toString() + " is not assocaiated with a tag");
					}
					if (vAr12$plaid.getTags().contains(vAr22$plaid)) {
						vAr11$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidObject vAr26$plaid;
								vAr26$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
								return vAr26$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr11$plaid;
			}
		}
		);
	}
}
