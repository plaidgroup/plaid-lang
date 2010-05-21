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
@plaid.runtime.annotations.RepresentsState(name = "Nil", toplevel = true)
public class Nil{
	public static final java.util.List<plaid.runtime.utils.Import> vAr99$plaid;
	static {
		vAr99$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr99$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Nil", toplevel = false)
	public static final plaid.runtime.PlaidObject Nil;
	static {
		final plaid.runtime.PlaidState vAr100$plaid;
		vAr100$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "append", toplevel = false)
		final plaid.runtime.PlaidObject vAr101$plaid;
		vAr101$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject cell) {
				plaid.runtime.PlaidScope vAr103$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr103$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr102$plaid;
				final plaid.runtime.PlaidObject vAr104$plaid;
				final plaid.runtime.PlaidObject vAr105$plaid;
				final plaid.runtime.PlaidObject vAr106$plaid;
				vAr106$plaid = cell;
				vAr104$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("copy", vAr106$plaid);
				vAr105$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				vAr102$plaid = plaid.runtime.Util.call(vAr104$plaid, vAr105$plaid);
				return vAr102$plaid;
			}
		}
		);
		vAr100$plaid.addMember("append",vAr101$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "copy", toplevel = false)
		final plaid.runtime.PlaidObject vAr107$plaid;
		vAr107$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr109$plaid) {
				plaid.runtime.PlaidScope vAr110$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr110$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr108$plaid;
				final plaid.runtime.PlaidState vAr111$plaid;
				final plaid.runtime.PlaidObject vAr112$plaid;
				vAr112$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", current$c0pe);
				vAr111$plaid = plaid.runtime.Util.toPlaidState(vAr112$plaid);
				vAr108$plaid = vAr111$plaid.instantiate();
				return vAr108$plaid;
			}
		}
		);
		vAr100$plaid.addMember("copy",vAr107$plaid);
		Nil = vAr100$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Nil")
	public static final plaid.runtime.PlaidTag Nil$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr113$plaid;
		final plaid.runtime.PlaidObject vAr114$plaid;
		vAr114$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ListCell", current$c0pe);
		vAr113$plaid = plaid.runtime.Util.toPlaidState(vAr114$plaid);
		Nil$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Nil", vAr113$plaid);
	}
}
