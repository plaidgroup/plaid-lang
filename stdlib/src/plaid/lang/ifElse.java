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
	public static final java.util.List<plaid.runtime.utils.Import> vAr33$plaid;
	static {
		vAr33$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr33$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr33$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = false)
	public static plaid.runtime.PlaidObject ifElse = plaid.runtime.Util.unit();
	static {
		final plaid.runtime.PlaidLocalScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		ifElse = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
				final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
				local$c0pe.insert("bool", bool, false);
				plaid.runtime.PlaidObject vAr34$plaid = plaid.runtime.Util.unit();
				final plaid.runtime.PlaidObject vAr35$plaid;
				vAr35$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("bool", local$c0pe);
				final plaid.runtime.PlaidState vAr36$plaid;
				final plaid.runtime.PlaidObject vAr38$plaid;
				vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
				final plaid.runtime.PlaidObject vAr39$plaid;
				vAr39$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr38$plaid);
				final plaid.runtime.PlaidObject vAr40$plaid;
				vAr40$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("TrueState", vAr39$plaid);
				vAr36$plaid = plaid.runtime.Util.toPlaidState(vAr40$plaid);
				final String vAr37$plaid;
				vAr37$plaid = plaid.runtime.Util.getQualifiedIdString(vAr36$plaid);
				if (vAr35$plaid.matchesTag(vAr37$plaid)) {
					vAr34$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
							final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
							final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
							local$c0pe.insert("trueClause", trueClause, false);
							plaid.runtime.PlaidObject vAr41$plaid = plaid.runtime.Util.unit();
							vAr41$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
									final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
									local$c0pe.insert("falseClause", falseClause, false);
									plaid.runtime.PlaidObject vAr42$plaid = plaid.runtime.Util.unit();
									final plaid.runtime.PlaidObject vAr43$plaid;
									final plaid.runtime.PlaidObject vAr44$plaid;
									vAr43$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("trueClause", local$c0pe);
									vAr44$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									vAr42$plaid = plaid.runtime.Util.call(vAr43$plaid, vAr44$plaid);
									return vAr42$plaid;
								}
							}
							);
							return vAr41$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidState vAr45$plaid;
					final plaid.runtime.PlaidObject vAr47$plaid;
					vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", global$c0pe);
					final plaid.runtime.PlaidObject vAr48$plaid;
					vAr48$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr47$plaid);
					final plaid.runtime.PlaidObject vAr49$plaid;
					vAr49$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("FalseState", vAr48$plaid);
					vAr45$plaid = plaid.runtime.Util.toPlaidState(vAr49$plaid);
					final String vAr46$plaid;
					vAr46$plaid = plaid.runtime.Util.getQualifiedIdString(vAr45$plaid);
					if (vAr35$plaid.matchesTag(vAr46$plaid)) {
						vAr34$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
								final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
								local$c0pe.insert("trueClause", trueClause, false);
								plaid.runtime.PlaidObject vAr50$plaid = plaid.runtime.Util.unit();
								vAr50$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
										final plaid.runtime.PlaidScope temp$c0pe = local$c0pe;
										final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);
										local$c0pe.insert("falseClause", falseClause, false);
										plaid.runtime.PlaidObject vAr51$plaid = plaid.runtime.Util.unit();
										final plaid.runtime.PlaidObject vAr52$plaid;
										final plaid.runtime.PlaidObject vAr53$plaid;
										vAr52$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("falseClause", local$c0pe);
										vAr53$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr51$plaid = plaid.runtime.Util.call(vAr52$plaid, vAr53$plaid);
										return vAr51$plaid;
									}
								}
								);
								return vAr50$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr34$plaid;
			}
		}
		);
		global$c0pe.insert("ifElse", ifElse, false);
	}
}
