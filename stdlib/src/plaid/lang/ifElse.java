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
	public static final java.util.List<plaid.runtime.utils.Import> vAr262$plaid;
	static {
		vAr262$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr262$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr262$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "ifElse", toplevel = false)
	public static plaid.runtime.PlaidObject ifElse;
	static {
		ifElse = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject bool) throws plaid.runtime.PlaidException {
				final plaid.runtime.PlaidObject vAr263$plaid;
				final plaid.runtime.PlaidObject vAr264$plaid;
				vAr264$plaid = bool;
				final plaid.runtime.PlaidState vAr265$plaid;
				final plaid.runtime.PlaidObject vAr267$plaid;
				vAr267$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
				final plaid.runtime.PlaidObject vAr268$plaid;
				vAr268$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr267$plaid);
				final plaid.runtime.PlaidObject vAr269$plaid;
				vAr269$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr268$plaid);
				vAr265$plaid = plaid.runtime.Util.toPlaidState(vAr269$plaid);
				final String vAr266$plaid;
				vAr266$plaid = plaid.runtime.Util.getQualifiedIdString(vAr265$plaid);
				if (vAr264$plaid.matchesTag(vAr266$plaid)) {
					vAr263$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
						public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
							final plaid.runtime.PlaidObject vAr270$plaid;
							vAr270$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
								public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
									final plaid.runtime.PlaidObject vAr271$plaid;
									final plaid.runtime.PlaidObject vAr272$plaid;
									final plaid.runtime.PlaidObject vAr273$plaid;
									vAr272$plaid = trueClause;
									vAr273$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
									vAr271$plaid = plaid.runtime.Util.call(vAr272$plaid, vAr273$plaid);
									return vAr271$plaid;
								}
							}
							);
							return vAr270$plaid;
						}
					}
					);
				}
				else {
					final plaid.runtime.PlaidState vAr274$plaid;
					final plaid.runtime.PlaidObject vAr276$plaid;
					vAr276$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr277$plaid;
					vAr277$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr276$plaid);
					final plaid.runtime.PlaidObject vAr278$plaid;
					vAr278$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr277$plaid);
					vAr274$plaid = plaid.runtime.Util.toPlaidState(vAr278$plaid);
					final String vAr275$plaid;
					vAr275$plaid = plaid.runtime.Util.getQualifiedIdString(vAr274$plaid);
					if (vAr264$plaid.matchesTag(vAr275$plaid)) {
						vAr263$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
							public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject trueClause) throws plaid.runtime.PlaidException {
								final plaid.runtime.PlaidObject vAr279$plaid;
								vAr279$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
									public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject falseClause) throws plaid.runtime.PlaidException {
										final plaid.runtime.PlaidObject vAr280$plaid;
										final plaid.runtime.PlaidObject vAr281$plaid;
										final plaid.runtime.PlaidObject vAr282$plaid;
										vAr281$plaid = falseClause;
										vAr282$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
										vAr280$plaid = plaid.runtime.Util.call(vAr281$plaid, vAr282$plaid);
										return vAr280$plaid;
									}
								}
								);
								return vAr279$plaid;
							}
						}
						);
					}
					else {
						throw new RuntimeException("Pattern match exhausted.");
					}
				}
				return vAr263$plaid;
			}
		}
		);
	}
}
