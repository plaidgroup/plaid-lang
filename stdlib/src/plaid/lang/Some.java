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
	public static final java.util.List<plaid.runtime.utils.Import> vAr84$plaid;
	static {
		vAr84$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr84$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Some", toplevel = false)
	public static final plaid.runtime.PlaidObject Some;
	static {
		Some = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr86$plaid;
		vAr86$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr87$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				final plaid.runtime.PlaidObject vAr85$plaid;
				vAr85$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr85$plaid;
			}
		}
		);
		Some.addMember("value",vAr86$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "getValue", toplevel = false)
		final plaid.runtime.PlaidObject vAr88$plaid;
		vAr88$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr90$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				final plaid.runtime.PlaidObject vAr89$plaid;
				vAr89$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				return vAr89$plaid;
			}
		}
		);
		Some.addMember("getValue",vAr88$plaid);
	}
}
