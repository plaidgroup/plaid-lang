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
	public static final java.util.List<plaid.runtime.utils.Import> vAr368$plaid;
	static {
		vAr368$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr368$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Pair", toplevel = false)
	public static final plaid.runtime.PlaidObject Pair;
	static {
		final plaid.runtime.PlaidState vAr369$plaid;
		vAr369$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "first", toplevel = false)
		final plaid.runtime.PlaidObject vAr370$plaid;
		vAr370$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr372$plaid) {
				plaid.runtime.PlaidScope vAr373$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr373$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr371$plaid;
				vAr371$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("fst", current$c0pe);
				return vAr371$plaid;
			}
		}
		);
		vAr369$plaid.addMember("first",vAr370$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "second", toplevel = false)
		final plaid.runtime.PlaidObject vAr374$plaid;
		vAr374$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr376$plaid) {
				plaid.runtime.PlaidScope vAr377$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr377$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr375$plaid;
				vAr375$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("snd", current$c0pe);
				return vAr375$plaid;
			}
		}
		);
		vAr369$plaid.addMember("second",vAr374$plaid);
		Pair = vAr369$plaid.getPrototype();
	}
}
