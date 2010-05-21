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
	public static final java.util.List<plaid.runtime.utils.Import> vAr356$plaid;
	static {
		vAr356$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr356$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Some", toplevel = false)
	public static final plaid.runtime.PlaidObject Some;
	static {
		final plaid.runtime.PlaidState vAr357$plaid;
		vAr357$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr359$plaid;
		vAr359$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr360$plaid) {
				plaid.runtime.PlaidScope vAr361$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr361$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr358$plaid;
				vAr358$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr358$plaid;
			}
		}
		);
		vAr357$plaid.addMember("value",vAr359$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "getValue", toplevel = false)
		final plaid.runtime.PlaidObject vAr362$plaid;
		vAr362$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr364$plaid) {
				plaid.runtime.PlaidScope vAr365$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr365$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr363$plaid;
				vAr363$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				return vAr363$plaid;
			}
		}
		);
		vAr357$plaid.addMember("getValue",vAr362$plaid);
		Some = vAr357$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Some")
	public static final plaid.runtime.PlaidTag Some$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr366$plaid;
		final plaid.runtime.PlaidObject vAr367$plaid;
		vAr367$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Option", current$c0pe);
		vAr366$plaid = plaid.runtime.Util.toPlaidState(vAr367$plaid);
		Some$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Some", vAr366$plaid);
	}
}
