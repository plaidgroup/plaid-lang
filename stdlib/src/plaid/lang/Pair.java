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
	public static final java.util.List<plaid.runtime.utils.Import> vAr315$plaid;
	static {
		vAr315$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr315$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Pair", toplevel = false)
	public static final plaid.runtime.PlaidObject Pair;
	static {
		final plaid.runtime.PlaidState vAr316$plaid;
		vAr316$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "first", toplevel = false)
		final plaid.runtime.PlaidObject vAr317$plaid;
		vAr317$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr319$plaid) {
				plaid.runtime.PlaidScope vAr320$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr320$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr318$plaid;
				vAr318$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("fst", current$c0pe);
				return vAr318$plaid;
			}
		}
		);
		vAr316$plaid.addMember("first",vAr317$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "second", toplevel = false)
		final plaid.runtime.PlaidObject vAr321$plaid;
		vAr321$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr323$plaid) {
				plaid.runtime.PlaidScope vAr324$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr324$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr322$plaid;
				vAr322$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("snd", current$c0pe);
				return vAr322$plaid;
			}
		}
		);
		vAr316$plaid.addMember("second",vAr321$plaid);
		Pair = vAr316$plaid.getPrototype();
	}
}
