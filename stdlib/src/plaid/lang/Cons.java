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
@plaid.runtime.annotations.RepresentsState(name = "Cons", toplevel = true)
public class Cons{
	public static final java.util.List<plaid.runtime.utils.Import> vAr48$plaid;
	static {
		vAr48$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr48$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr48$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Cons", toplevel = false)
	public static final plaid.runtime.PlaidObject Cons;
	static {
		final plaid.runtime.PlaidState vAr49$plaid;
		vAr49$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr51$plaid;
		vAr51$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr52$plaid) {
				plaid.runtime.PlaidScope vAr53$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr53$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr50$plaid;
				vAr50$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr50$plaid;
			}
		}
		);
		vAr49$plaid.addMember("value",vAr51$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
		final plaid.runtime.PlaidObject vAr55$plaid;
		vAr55$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr56$plaid) {
				plaid.runtime.PlaidScope vAr57$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr57$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr54$plaid;
				vAr54$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr54$plaid;
			}
		}
		);
		vAr49$plaid.addMember("next",vAr55$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "append", toplevel = false)
		final plaid.runtime.PlaidObject vAr58$plaid;
		vAr58$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject cell) {
				plaid.runtime.PlaidScope vAr60$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr60$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr59$plaid;
				{
					final plaid.runtime.PlaidObject vAr29$plaid;
					final plaid.runtime.PlaidObject vAr61$plaid;
					final plaid.runtime.PlaidObject vAr62$plaid;
					final plaid.runtime.PlaidObject vAr63$plaid;
					final plaid.runtime.PlaidObject vAr64$plaid;
					vAr64$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", current$c0pe);
					vAr62$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("append", vAr64$plaid);
					vAr63$plaid = cell;
					vAr61$plaid = plaid.runtime.Util.call(vAr62$plaid, vAr63$plaid);
					if (this$plaid.getMembers().containsKey("next")) this$plaid.addMember("next",vAr61$plaid);
					else throw new plaid.runtime.PlaidException("Object does not have member next.  Assignment failed.");
					vAr29$plaid = plaid.runtime.Util.unit();
					vAr59$plaid = this$plaid;
				}
				return vAr59$plaid;
			}
		}
		);
		vAr49$plaid.addMember("append",vAr58$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "copy", toplevel = false)
		final plaid.runtime.PlaidObject vAr65$plaid;
		vAr65$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr30$plaid) {
				plaid.runtime.PlaidScope vAr67$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr67$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr66$plaid;
				final plaid.runtime.PlaidState vAr68$plaid;
				final plaid.runtime.PlaidState vAr69$plaid;
				final plaid.runtime.PlaidState vAr70$plaid;
				final plaid.runtime.PlaidObject vAr71$plaid;
				vAr71$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
				vAr69$plaid = plaid.runtime.Util.toPlaidState(vAr71$plaid);
				vAr70$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
				final plaid.runtime.PlaidObject vAr73$plaid;
				vAr73$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr74$plaid) {
						plaid.runtime.PlaidScope vAr75$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr75$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr72$plaid;
						vAr72$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
						return vAr72$plaid;
					}
				}
				);
				vAr70$plaid.addMember("value",vAr73$plaid);
				@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
				final plaid.runtime.PlaidObject vAr77$plaid;
				vAr77$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr78$plaid) {
						plaid.runtime.PlaidScope vAr79$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr79$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr76$plaid;
						final plaid.runtime.PlaidObject vAr80$plaid;
						final plaid.runtime.PlaidObject vAr81$plaid;
						final plaid.runtime.PlaidObject vAr82$plaid;
						vAr82$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", current$c0pe);
						vAr80$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("copy", vAr82$plaid);
						vAr81$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr76$plaid = plaid.runtime.Util.call(vAr80$plaid, vAr81$plaid);
						return vAr76$plaid;
					}
				}
				);
				vAr70$plaid.addMember("next",vAr77$plaid);
				vAr68$plaid = vAr69$plaid.with(vAr70$plaid);
				vAr66$plaid = vAr68$plaid.instantiate();
				return vAr66$plaid;
			}
		}
		);
		vAr49$plaid.addMember("copy",vAr65$plaid);
		Cons = vAr49$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Cons")
	public static final plaid.runtime.PlaidTag Cons$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr83$plaid;
		final plaid.runtime.PlaidObject vAr84$plaid;
		vAr84$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ListCell", current$c0pe);
		vAr83$plaid = plaid.runtime.Util.toPlaidState(vAr84$plaid);
		Cons$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Cons", vAr83$plaid);
	}
}
