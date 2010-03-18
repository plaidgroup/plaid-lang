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
@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = true)
public class ifElse{
	public static java.util.List<plaid.runtime.utils.Import> vAr17$plaid;
	static {
		vAr17$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr17$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = false)
	public static plaid.runtime.PlaidObject ifElse;
	static {
		ifElse = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr18$plaid;
				plaid.runtime.PlaidObject vAr19$plaid;
				vAr19$plaid = bool;
				final plaid.runtime.PlaidObject vAr20$plaid;
				plaid.runtime.PlaidObject vAr21$plaid;
				vAr21$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				plaid.runtime.PlaidObject vAr22$plaid;
				vAr22$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr21$plaid);
				plaid.runtime.PlaidObject vAr23$plaid;
				vAr23$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr22$plaid);
				vAr20$plaid = plaid.runtime.Util.toPlaidState(vAr23$plaid);
				if (vAr19$plaid.getStates().contains(vAr20$plaid)) {
					vAr18$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
							plaid.runtime.PlaidObject vAr24$plaid;
							vAr24$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
									plaid.runtime.PlaidObject vAr25$plaid;
									plaid.runtime.PlaidObject vAr26$plaid;
									plaid.runtime.PlaidObject vAr27$plaid;
									vAr26$plaid = trueClause;
									vAr27$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									vAr25$plaid = plaid.runtime.Util.call(vAr26$plaid, vAr27$plaid);
									return vAr25$plaid;
								}
							}
							);
							return vAr24$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidObject vAr28$plaid;
					plaid.runtime.PlaidObject vAr29$plaid;
					vAr29$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					plaid.runtime.PlaidObject vAr30$plaid;
					vAr30$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr29$plaid);
					plaid.runtime.PlaidObject vAr31$plaid;
					vAr31$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr30$plaid);
					vAr28$plaid = plaid.runtime.Util.toPlaidState(vAr31$plaid);
					if (vAr19$plaid.getStates().contains(vAr28$plaid)) {
						vAr18$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
								plaid.runtime.PlaidObject vAr32$plaid;
								vAr32$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
										plaid.runtime.PlaidObject vAr33$plaid;
										plaid.runtime.PlaidObject vAr34$plaid;
										plaid.runtime.PlaidObject vAr35$plaid;
										vAr34$plaid = falseClause;
										vAr35$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr33$plaid = plaid.runtime.Util.call(vAr34$plaid, vAr35$plaid);
										return vAr33$plaid;
									}
								}
								);
								return vAr32$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr18$plaid;
			}
		}
		);
	}
}
