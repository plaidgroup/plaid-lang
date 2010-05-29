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
@plaid.runtime.annotations.RepresentsState(name = "Some", toplevel = true)
public class Some{
	public static final java.util.List<plaid.runtime.utils.Import> vAr303$plaid;
	static {
		vAr303$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr303$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Some", toplevel = false)
	public static final plaid.runtime.PlaidObject Some;
	static {
		final plaid.runtime.PlaidState vAr304$plaid;
		vAr304$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr306$plaid;
		vAr306$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr307$plaid) {
				plaid.runtime.PlaidScope vAr308$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr308$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr305$plaid;
				vAr305$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr305$plaid;
			}
		}
		);
		vAr304$plaid.addMember("value",vAr306$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "getValue", toplevel = false)
		final plaid.runtime.PlaidObject vAr309$plaid;
		vAr309$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr311$plaid) {
				plaid.runtime.PlaidScope vAr312$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr312$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr310$plaid;
				vAr310$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				return vAr310$plaid;
			}
		}
		);
		vAr304$plaid.addMember("getValue",vAr309$plaid);
		Some = vAr304$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Some")
	public static final plaid.runtime.PlaidTag Some$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr313$plaid;
		final plaid.runtime.PlaidObject vAr314$plaid;
		vAr314$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Option", current$c0pe);
		vAr313$plaid = plaid.runtime.Util.toPlaidState(vAr314$plaid);
		Some$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Some", vAr313$plaid);
	}
}
