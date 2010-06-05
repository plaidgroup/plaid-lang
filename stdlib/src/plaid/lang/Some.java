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
	public static final java.util.List<plaid.runtime.utils.Import> vAr7$plaid;
	static {
		vAr7$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr7$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr7$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Some", toplevel = false)
	public static final plaid.runtime.PlaidObject Some;
	static {
		final plaid.runtime.PlaidState vAr8$plaid;
		vAr8$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		final plaid.runtime.PlaidObject vAr10$plaid;
		vAr10$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr11$plaid) {
				plaid.runtime.PlaidScope vAr12$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr12$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr9$plaid;
				vAr9$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr9$plaid;
			}
		}
		);
		vAr8$plaid.addMember("value",vAr10$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "getValue", toplevel = false)
		final plaid.runtime.PlaidObject vAr13$plaid;
		vAr13$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr0$plaid) {
				plaid.runtime.PlaidScope vAr15$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr15$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr14$plaid;
				vAr14$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				return vAr14$plaid;
			}
		}
		);
		vAr8$plaid.addMember("getValue",vAr13$plaid);
		Some = vAr8$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Some")
	public static final plaid.runtime.PlaidTag Some$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr16$plaid;
		final plaid.runtime.PlaidObject vAr17$plaid;
		vAr17$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Option", current$c0pe);
		vAr16$plaid = plaid.runtime.Util.toPlaidState(vAr17$plaid);
		Some$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Some", vAr16$plaid);
	}
}
