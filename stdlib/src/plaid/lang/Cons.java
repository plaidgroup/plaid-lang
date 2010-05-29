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
	public static final java.util.List<plaid.runtime.utils.Import> vAr115$plaid;
	static {
		vAr115$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr115$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Cons", toplevel = false)
	public static final plaid.runtime.PlaidObject Cons;
	static {
		final plaid.runtime.PlaidState vAr116$plaid;
		vAr116$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr118$plaid;
		vAr118$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr119$plaid) {
				plaid.runtime.PlaidScope vAr120$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr120$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr117$plaid;
				vAr117$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr117$plaid;
			}
		}
		);
		vAr116$plaid.addMember("value",vAr118$plaid);
		@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
		final plaid.runtime.PlaidObject vAr122$plaid;
		vAr122$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr123$plaid) {
				plaid.runtime.PlaidScope vAr124$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr124$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr121$plaid;
				vAr121$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr121$plaid;
			}
		}
		);
		vAr116$plaid.addMember("next",vAr122$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "append", toplevel = false)
		final plaid.runtime.PlaidObject vAr125$plaid;
		vAr125$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject cell) {
				plaid.runtime.PlaidScope vAr127$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr127$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr126$plaid;
				{
					final plaid.runtime.PlaidObject vAr96$plaid;
					final plaid.runtime.PlaidObject vAr128$plaid;
					final plaid.runtime.PlaidObject vAr129$plaid;
					final plaid.runtime.PlaidObject vAr130$plaid;
					final plaid.runtime.PlaidObject vAr131$plaid;
					vAr131$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", current$c0pe);
					vAr129$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("append", vAr131$plaid);
					vAr130$plaid = cell;
					vAr128$plaid = plaid.runtime.Util.call(vAr129$plaid, vAr130$plaid);
					if (this$plaid.getMembers().containsKey("next")) this$plaid.addMember("next",vAr128$plaid);
					else throw new plaid.runtime.PlaidException("Object does not have member next.  Assignment failed.");
					vAr96$plaid = plaid.runtime.Util.unit();
					vAr126$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("this", current$c0pe);
				}
				return vAr126$plaid;
			}
		}
		);
		vAr116$plaid.addMember("append",vAr125$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "copy", toplevel = false)
		final plaid.runtime.PlaidObject vAr132$plaid;
		vAr132$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr134$plaid) {
				plaid.runtime.PlaidScope vAr135$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr135$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr133$plaid;
				final plaid.runtime.PlaidState vAr136$plaid;
				final plaid.runtime.PlaidState vAr137$plaid;
				final plaid.runtime.PlaidState vAr138$plaid;
				final plaid.runtime.PlaidObject vAr139$plaid;
				vAr139$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Cons", current$c0pe);
				vAr137$plaid = plaid.runtime.Util.toPlaidState(vAr139$plaid);
				vAr138$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
				final plaid.runtime.PlaidObject vAr141$plaid;
				vAr141$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr142$plaid) {
						plaid.runtime.PlaidScope vAr143$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr143$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr140$plaid;
						vAr140$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
						return vAr140$plaid;
					}
				}
				);
				vAr138$plaid.addMember("value",vAr141$plaid);
				@plaid.runtime.annotations.RepresentsField(name = "next", toplevel = false)
				final plaid.runtime.PlaidObject vAr145$plaid;
				vAr145$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr146$plaid) {
						plaid.runtime.PlaidScope vAr147$plaid = current$c0pe;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr147$plaid, this$plaid);
						final plaid.runtime.PlaidObject vAr144$plaid;
						final plaid.runtime.PlaidObject vAr148$plaid;
						final plaid.runtime.PlaidObject vAr149$plaid;
						final plaid.runtime.PlaidObject vAr150$plaid;
						vAr150$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("next", current$c0pe);
						vAr148$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("copy", vAr150$plaid);
						vAr149$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
						vAr144$plaid = plaid.runtime.Util.call(vAr148$plaid, vAr149$plaid);
						return vAr144$plaid;
					}
				}
				);
				vAr138$plaid.addMember("next",vAr145$plaid);
				vAr136$plaid = vAr137$plaid.with(vAr138$plaid);
				vAr133$plaid = vAr136$plaid.instantiate();
				return vAr133$plaid;
			}
		}
		);
		vAr116$plaid.addMember("copy",vAr132$plaid);
		Cons = vAr116$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Cons")
	public static final plaid.runtime.PlaidTag Cons$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr151$plaid;
		final plaid.runtime.PlaidObject vAr152$plaid;
		vAr152$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ListCell", current$c0pe);
		vAr151$plaid = plaid.runtime.Util.toPlaidState(vAr152$plaid);
		Cons$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Cons", vAr151$plaid);
	}
}
