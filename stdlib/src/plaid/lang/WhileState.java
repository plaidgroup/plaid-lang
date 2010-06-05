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
	public static final java.util.List<plaid.runtime.utils.Import> vAr301$plaid;
	static {
		vAr301$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr301$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr301$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "WhileState", toplevel = false)
	public static final plaid.runtime.PlaidObject WhileState;
	static {
		final plaid.runtime.PlaidState vAr302$plaid;
		vAr302$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
		final plaid.runtime.PlaidObject vAr304$plaid;
		vAr304$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr305$plaid) {
				plaid.runtime.PlaidScope vAr306$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr306$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr303$plaid;
				vAr303$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr303$plaid;
			}
		}
		);
		vAr302$plaid.addMember("condition",vAr304$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
		final plaid.runtime.PlaidObject vAr308$plaid;
		vAr308$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr309$plaid) {
				plaid.runtime.PlaidScope vAr310$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr310$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr307$plaid;
				vAr307$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr307$plaid;
			}
		}
		);
		vAr302$plaid.addMember("whileBody",vAr308$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "whileLoop", toplevel = false)
		final plaid.runtime.PlaidObject vAr311$plaid;
		vAr311$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject result) {
				plaid.runtime.PlaidScope vAr313$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr313$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr312$plaid;
				{
					final plaid.runtime.PlaidObject test;
					final plaid.runtime.PlaidObject vAr314$plaid;
					final plaid.runtime.PlaidObject vAr315$plaid;
					vAr314$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("condition", current$c0pe);
					vAr315$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					test = plaid.runtime.Util.call(vAr314$plaid, vAr315$plaid);
					final plaid.runtime.PlaidObject vAr316$plaid;
					vAr316$plaid = test;
					final plaid.runtime.PlaidState vAr317$plaid;
					final plaid.runtime.PlaidObject vAr319$plaid;
					vAr319$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr320$plaid;
					vAr320$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr319$plaid);
					final plaid.runtime.PlaidObject vAr321$plaid;
					vAr321$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr320$plaid);
					vAr317$plaid = plaid.runtime.Util.toPlaidState(vAr321$plaid);
					final String vAr318$plaid;
					vAr318$plaid = plaid.runtime.Util.getQualifiedIdString(vAr317$plaid);
					if (vAr316$plaid.matchesTag(vAr318$plaid)) {
						{
							final plaid.runtime.PlaidObject newResult;
							final plaid.runtime.PlaidObject vAr322$plaid;
							final plaid.runtime.PlaidObject vAr323$plaid;
							vAr322$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileBody", current$c0pe);
							vAr323$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							newResult = plaid.runtime.Util.call(vAr322$plaid, vAr323$plaid);
							final plaid.runtime.PlaidObject vAr324$plaid;
							final plaid.runtime.PlaidObject vAr325$plaid;
							vAr324$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", current$c0pe);
							vAr325$plaid = newResult;
							vAr312$plaid = plaid.runtime.Util.call(vAr324$plaid, vAr325$plaid);
						}
					}
					else {
						final plaid.runtime.PlaidState vAr326$plaid;
						final plaid.runtime.PlaidObject vAr328$plaid;
						vAr328$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
						final plaid.runtime.PlaidObject vAr329$plaid;
						vAr329$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr328$plaid);
						final plaid.runtime.PlaidObject vAr330$plaid;
						vAr330$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr329$plaid);
						vAr326$plaid = plaid.runtime.Util.toPlaidState(vAr330$plaid);
						final String vAr327$plaid;
						vAr327$plaid = plaid.runtime.Util.getQualifiedIdString(vAr326$plaid);
						if (vAr316$plaid.matchesTag(vAr327$plaid)) {
							vAr312$plaid = result;
						}
						else {
							throw new RuntimeException("Pattern match exhausted.");
						}
					}
				}
				return vAr312$plaid;
			}
		}
		);
		vAr302$plaid.addMember("whileLoop",vAr311$plaid);
		WhileState = vAr302$plaid.getPrototype();
	}
}
