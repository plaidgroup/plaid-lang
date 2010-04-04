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
	public static final java.util.List<plaid.runtime.utils.Import> vAr102$plaid;
	static {
		vAr102$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr102$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Some", toplevel = false)
	public static final plaid.runtime.PlaidObject Some;
	static {
		final plaid.runtime.PlaidState vAr103$plaid;
		vAr103$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr105$plaid;
		vAr105$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr106$plaid) {
				plaid.runtime.PlaidScope vAr107$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr107$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr104$plaid;
				vAr104$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr104$plaid;
			}
		}
		);
		vAr103$plaid.addMember("value",vAr105$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "getValue", toplevel = false)
		final plaid.runtime.PlaidObject vAr108$plaid;
		vAr108$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr110$plaid) {
				plaid.runtime.PlaidScope vAr111$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr111$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr109$plaid;
				vAr109$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				return vAr109$plaid;
			}
		}
		);
		vAr103$plaid.addMember("getValue",vAr108$plaid);
		Some = vAr103$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Some")
	public static final plaid.runtime.PlaidTag Some$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr112$plaid;
		final plaid.runtime.PlaidObject vAr113$plaid;
		vAr113$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Option", current$c0pe);
		vAr112$plaid = plaid.runtime.Util.toPlaidState(vAr113$plaid);
		Some$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Some", vAr112$plaid);
	}
}
