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
	public static final java.util.List<plaid.runtime.utils.Import> vAr27$plaid;
	static {
		vAr27$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr27$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = false)
	public static final plaid.runtime.PlaidObject ifElse;
	static {
		ifElse = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr28$plaid;
				final plaid.runtime.PlaidObject vAr29$plaid;
				vAr29$plaid = bool;
				final plaid.runtime.PlaidState vAr30$plaid;
				final plaid.runtime.PlaidObject vAr32$plaid;
				vAr32$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				final plaid.runtime.PlaidObject vAr33$plaid;
				vAr33$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr32$plaid);
				final plaid.runtime.PlaidObject vAr34$plaid;
				vAr34$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr33$plaid);
				vAr30$plaid = plaid.runtime.Util.toPlaidState(vAr34$plaid);
				final String vAr31$plaid;
				vAr31$plaid = plaid.runtime.Util.getQualifiedIdString(vAr30$plaid);
				if (vAr29$plaid.matchesTag(vAr31$plaid)) {
					vAr28$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
							final plaid.runtime.PlaidObject vAr35$plaid;
							vAr35$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidObject vAr36$plaid;
									final plaid.runtime.PlaidObject vAr37$plaid;
									final plaid.runtime.PlaidObject vAr38$plaid;
									vAr37$plaid = trueClause;
									vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									vAr36$plaid = plaid.runtime.Util.call(vAr37$plaid, vAr38$plaid);
									return vAr36$plaid;
								}
							}
							);
							return vAr35$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidState vAr39$plaid;
					final plaid.runtime.PlaidObject vAr41$plaid;
					vAr41$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr42$plaid;
					vAr42$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr41$plaid);
					final plaid.runtime.PlaidObject vAr43$plaid;
					vAr43$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr42$plaid);
					vAr39$plaid = plaid.runtime.Util.toPlaidState(vAr43$plaid);
					final String vAr40$plaid;
					vAr40$plaid = plaid.runtime.Util.getQualifiedIdString(vAr39$plaid);
					if (vAr29$plaid.matchesTag(vAr40$plaid)) {
						vAr28$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidObject vAr44$plaid;
								vAr44$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
										final plaid.runtime.PlaidObject vAr45$plaid;
										final plaid.runtime.PlaidObject vAr46$plaid;
										final plaid.runtime.PlaidObject vAr47$plaid;
										vAr46$plaid = falseClause;
										vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr45$plaid = plaid.runtime.Util.call(vAr46$plaid, vAr47$plaid);
										return vAr45$plaid;
									}
								}
								);
								return vAr44$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr28$plaid;
			}
		}
		);
	}
}
