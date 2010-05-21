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
	public static final java.util.List<plaid.runtime.utils.Import> vAr66$plaid;
	static {
		vAr66$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr66$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "WhileState", toplevel = false)
	public static final plaid.runtime.PlaidObject WhileState;
	static {
		final plaid.runtime.PlaidState vAr67$plaid;
		vAr67$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "condition", toplevel = false)
		final plaid.runtime.PlaidObject vAr69$plaid;
		vAr69$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr70$plaid) {
				plaid.runtime.PlaidScope vAr71$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr71$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr68$plaid;
				vAr68$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr68$plaid;
			}
		}
		);
		vAr67$plaid.addMember("condition",vAr69$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "whileBody", toplevel = false)
		final plaid.runtime.PlaidObject vAr73$plaid;
		vAr73$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr74$plaid) {
				plaid.runtime.PlaidScope vAr75$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr75$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr72$plaid;
				vAr72$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr72$plaid;
			}
		}
		);
		vAr67$plaid.addMember("whileBody",vAr73$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "whileLoop", toplevel = false)
		final plaid.runtime.PlaidObject vAr76$plaid;
		vAr76$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject result) {
				plaid.runtime.PlaidScope vAr78$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr78$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr77$plaid;
				{
					final plaid.runtime.PlaidObject test;
					final plaid.runtime.PlaidObject vAr79$plaid;
					final plaid.runtime.PlaidObject vAr80$plaid;
					vAr79$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("condition", current$c0pe);
					vAr80$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
					test = plaid.runtime.Util.call(vAr79$plaid, vAr80$plaid);
					final plaid.runtime.PlaidObject vAr81$plaid;
					vAr81$plaid = test;
					final plaid.runtime.PlaidState vAr82$plaid;
					final plaid.runtime.PlaidObject vAr84$plaid;
					vAr84$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
					final plaid.runtime.PlaidObject vAr85$plaid;
					vAr85$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr84$plaid);
					final plaid.runtime.PlaidObject vAr86$plaid;
					vAr86$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("True", vAr85$plaid);
					vAr82$plaid = plaid.runtime.Util.toPlaidState(vAr86$plaid);
					final String vAr83$plaid;
					vAr83$plaid = plaid.runtime.Util.getQualifiedIdString(vAr82$plaid);
					if (vAr81$plaid.matchesTag(vAr83$plaid)) {
						{
							final plaid.runtime.PlaidObject newResult;
							final plaid.runtime.PlaidObject vAr87$plaid;
							final plaid.runtime.PlaidObject vAr88$plaid;
							vAr87$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileBody", current$c0pe);
							vAr88$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
							newResult = plaid.runtime.Util.call(vAr87$plaid, vAr88$plaid);
							final plaid.runtime.PlaidObject vAr89$plaid;
							final plaid.runtime.PlaidObject vAr90$plaid;
							vAr89$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("whileLoop", current$c0pe);
							vAr90$plaid = newResult;
							vAr77$plaid = plaid.runtime.Util.call(vAr89$plaid, vAr90$plaid);
						}
					}
					else {
						final plaid.runtime.PlaidState vAr91$plaid;
						final plaid.runtime.PlaidObject vAr93$plaid;
						vAr93$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("plaid", current$c0pe);
						final plaid.runtime.PlaidObject vAr94$plaid;
						vAr94$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("lang", vAr93$plaid);
						final plaid.runtime.PlaidObject vAr95$plaid;
						vAr95$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", vAr94$plaid);
						vAr91$plaid = plaid.runtime.Util.toPlaidState(vAr95$plaid);
						final String vAr92$plaid;
						vAr92$plaid = plaid.runtime.Util.getQualifiedIdString(vAr91$plaid);
						if (vAr81$plaid.matchesTag(vAr92$plaid)) {
							vAr77$plaid = result;
						}
						else {
							throw new RuntimeException("Pattern match exhausted.");
						}
					}
				}
				return vAr77$plaid;
			}
		}
		);
		vAr67$plaid.addMember("whileLoop",vAr76$plaid);
		WhileState = vAr67$plaid.getPrototype();
	}
}
