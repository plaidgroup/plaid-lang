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
	public static final java.util.List<plaid.runtime.utils.Import> vAr20$plaid;
	static {
		vAr20$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr20$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr20$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Pair", toplevel = false)
	public static final plaid.runtime.PlaidObject Pair;
	static {
		final plaid.runtime.PlaidState vAr21$plaid;
		vAr21$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "first", toplevel = false)
		final plaid.runtime.PlaidObject vAr22$plaid;
		vAr22$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr18$plaid) {
				plaid.runtime.PlaidScope vAr24$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr24$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr23$plaid;
				vAr23$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("fst", current$c0pe);
				return vAr23$plaid;
			}
		}
		);
		vAr21$plaid.addMember("first",vAr22$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "second", toplevel = false)
		final plaid.runtime.PlaidObject vAr25$plaid;
		vAr25$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr19$plaid) {
				plaid.runtime.PlaidScope vAr27$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr27$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr26$plaid;
				vAr26$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("snd", current$c0pe);
				return vAr26$plaid;
			}
		}
		);
		vAr21$plaid.addMember("second",vAr25$plaid);
		Pair = vAr21$plaid.getPrototype();
	}
}
