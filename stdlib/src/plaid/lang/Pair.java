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
@plaid.runtime.annotations.RepresentsState(name = "Pair", toplevel = true)
public class Pair{
	public static final java.util.List<plaid.runtime.utils.Import> vAr114$plaid;
	static {
		vAr114$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr114$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Pair", toplevel = false)
	public static final plaid.runtime.PlaidObject Pair;
	static {
		final plaid.runtime.PlaidState vAr115$plaid;
		vAr115$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "first", toplevel = false)
		final plaid.runtime.PlaidObject vAr116$plaid;
		vAr116$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr118$plaid) {
				plaid.runtime.PlaidScope vAr119$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr119$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr117$plaid;
				vAr117$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("fst", current$c0pe);
				return vAr117$plaid;
			}
		}
		);
		vAr115$plaid.addMember("first",vAr116$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "second", toplevel = false)
		final plaid.runtime.PlaidObject vAr120$plaid;
		vAr120$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr122$plaid) {
				plaid.runtime.PlaidScope vAr123$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr123$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr121$plaid;
				vAr121$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("snd", current$c0pe);
				return vAr121$plaid;
			}
		}
		);
		vAr115$plaid.addMember("second",vAr120$plaid);
		Pair = vAr115$plaid.getPrototype();
	}
}
