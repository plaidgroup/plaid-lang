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
	public static final java.util.List<plaid.runtime.utils.Import> vAr24$plaid;
	static {
		vAr24$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr24$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = false)
	public static final plaid.runtime.PlaidObject ifElse;
	static {
		ifElse = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr25$plaid;
				final plaid.runtime.PlaidObject vAr26$plaid;
				vAr26$plaid = bool;
				final plaid.runtime.PlaidObject vAr27$plaid;
				final plaid.runtime.PlaidObject vAr28$plaid;
				vAr28$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				final plaid.runtime.PlaidObject vAr29$plaid;
				vAr29$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr28$plaid);
				final plaid.runtime.PlaidObject vAr30$plaid;
				vAr30$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr29$plaid);
				vAr27$plaid = plaid.runtime.Util.toPlaidState(vAr30$plaid);
				if (vAr26$plaid.getStates().contains(vAr27$plaid)) {
					vAr25$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
							final plaid.runtime.PlaidObject vAr31$plaid;
							vAr31$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidObject vAr32$plaid;
									final plaid.runtime.PlaidObject vAr33$plaid;
									final plaid.runtime.PlaidObject vAr34$plaid;
									vAr33$plaid = trueClause;
									vAr34$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									vAr32$plaid = plaid.runtime.Util.call(vAr33$plaid, vAr34$plaid);
									return vAr32$plaid;
								}
							}
							);
							return vAr31$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidObject vAr35$plaid;
					final plaid.runtime.PlaidObject vAr36$plaid;
					vAr36$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr37$plaid;
					vAr37$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr36$plaid);
					final plaid.runtime.PlaidObject vAr38$plaid;
					vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr37$plaid);
					vAr35$plaid = plaid.runtime.Util.toPlaidState(vAr38$plaid);
					if (vAr26$plaid.getStates().contains(vAr35$plaid)) {
						vAr25$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidObject vAr39$plaid;
								vAr39$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
										final plaid.runtime.PlaidObject vAr40$plaid;
										final plaid.runtime.PlaidObject vAr41$plaid;
										final plaid.runtime.PlaidObject vAr42$plaid;
										vAr41$plaid = falseClause;
										vAr42$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr40$plaid = plaid.runtime.Util.call(vAr41$plaid, vAr42$plaid);
										return vAr40$plaid;
									}
								}
								);
								return vAr39$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr25$plaid;
			}
		}
		);
	}
}
