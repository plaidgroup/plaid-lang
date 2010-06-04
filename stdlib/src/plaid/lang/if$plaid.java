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
	public static final java.util.List<plaid.runtime.utils.Import> vAr245$plaid;
	static {
		vAr245$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr245$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr245$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "if$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject if$plaid;
	static {
		if$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr246$plaid;
				final plaid.runtime.PlaidObject vAr247$plaid;
				vAr247$plaid = bool;
				final plaid.runtime.PlaidState vAr248$plaid;
				final plaid.runtime.PlaidObject vAr250$plaid;
				vAr250$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				final plaid.runtime.PlaidObject vAr251$plaid;
				vAr251$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr250$plaid);
				final plaid.runtime.PlaidObject vAr252$plaid;
				vAr252$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr251$plaid);
				vAr248$plaid = plaid.runtime.Util.toPlaidState(vAr252$plaid);
				final String vAr249$plaid;
				vAr249$plaid = plaid.runtime.Util.getQualifiedIdString(vAr248$plaid);
				if (vAr247$plaid.matchesTag(vAr249$plaid)) {
					vAr246$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
							final plaid.runtime.PlaidObject vAr253$plaid;
							final plaid.runtime.PlaidObject vAr254$plaid;
							final plaid.runtime.PlaidObject vAr255$plaid;
							vAr254$plaid = func;
							vAr255$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							vAr253$plaid = plaid.runtime.Util.call(vAr254$plaid, vAr255$plaid);
							return vAr253$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidState vAr256$plaid;
					final plaid.runtime.PlaidObject vAr258$plaid;
					vAr258$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr259$plaid;
					vAr259$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr258$plaid);
					final plaid.runtime.PlaidObject vAr260$plaid;
					vAr260$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr259$plaid);
					vAr256$plaid = plaid.runtime.Util.toPlaidState(vAr260$plaid);
					final String vAr257$plaid;
					vAr257$plaid = plaid.runtime.Util.getQualifiedIdString(vAr256$plaid);
					if (vAr247$plaid.matchesTag(vAr257$plaid)) {
						vAr246$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject func) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidObject vAr261$plaid;
								vAr261$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
								return vAr261$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr246$plaid;
			}
		}
		);
	}
}
