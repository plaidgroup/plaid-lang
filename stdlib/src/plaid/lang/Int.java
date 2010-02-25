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
@plaid.runtime.annotations.RepresentsState(name = "Int", toplevel = true)
public class Int{
	public static java.util.List<plaid.runtime.utils.Import> vAr88$plaid;
	static {
		vAr88$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr88$plaid.add(new plaid.runtime.utils.Import("java.math.BigInteger"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr88$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Int", toplevel = false)
	public static plaid.runtime.PlaidObject Int;
	static {
		Int = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
		plaid.runtime.PlaidObject vAr90$plaid;
		vAr90$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr91$plaid) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr89$plaid;
				vAr89$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				return vAr89$plaid;
			}
		}
		);
		Int.addMember("value",vAr90$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "plus", toplevel = false)
		plaid.runtime.PlaidObject vAr92$plaid;
		vAr92$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject x) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr93$plaid;
				plaid.runtime.PlaidObject vAr94$plaid;
				plaid.runtime.PlaidObject vAr95$plaid;
				plaid.runtime.PlaidObject vAr96$plaid;
				vAr96$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				vAr94$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("add", vAr96$plaid);
				plaid.runtime.PlaidObject vAr97$plaid;
				vAr97$plaid = x;
				vAr95$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", vAr97$plaid);
				vAr93$plaid = plaid.runtime.Util.call(vAr94$plaid, vAr95$plaid);
				return vAr93$plaid;
			}
		}
		);
		Int.addMember("plus",vAr92$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "multiply", toplevel = false)
		plaid.runtime.PlaidObject vAr98$plaid;
		vAr98$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject x) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr99$plaid;
				plaid.runtime.PlaidObject vAr100$plaid;
				plaid.runtime.PlaidObject vAr101$plaid;
				plaid.runtime.PlaidObject vAr102$plaid;
				vAr102$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				vAr100$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("multiply", vAr102$plaid);
				plaid.runtime.PlaidObject vAr103$plaid;
				vAr103$plaid = x;
				vAr101$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", vAr103$plaid);
				vAr99$plaid = plaid.runtime.Util.call(vAr100$plaid, vAr101$plaid);
				return vAr99$plaid;
			}
		}
		);
		Int.addMember("multiply",vAr98$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "minus", toplevel = false)
		plaid.runtime.PlaidObject vAr104$plaid;
		vAr104$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject x) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr105$plaid;
				plaid.runtime.PlaidObject vAr106$plaid;
				plaid.runtime.PlaidObject vAr107$plaid;
				plaid.runtime.PlaidObject vAr108$plaid;
				vAr108$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				vAr106$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("subtract", vAr108$plaid);
				plaid.runtime.PlaidObject vAr109$plaid;
				vAr109$plaid = x;
				vAr107$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", vAr109$plaid);
				vAr105$plaid = plaid.runtime.Util.call(vAr106$plaid, vAr107$plaid);
				return vAr105$plaid;
			}
		}
		);
		Int.addMember("minus",vAr104$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "divide", toplevel = false)
		plaid.runtime.PlaidObject vAr110$plaid;
		vAr110$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject x) {
				plaid.runtime.PlaidScope the$c0pe = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
				plaid.runtime.PlaidObject vAr111$plaid;
				plaid.runtime.PlaidObject vAr112$plaid;
				plaid.runtime.PlaidObject vAr113$plaid;
				plaid.runtime.PlaidObject vAr114$plaid;
				vAr114$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", current$c0pe);
				vAr112$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("divide", vAr114$plaid);
				plaid.runtime.PlaidObject vAr115$plaid;
				vAr115$plaid = x;
				vAr113$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("value", vAr115$plaid);
				vAr111$plaid = plaid.runtime.Util.call(vAr112$plaid, vAr113$plaid);
				return vAr111$plaid;
			}
		}
		);
		Int.addMember("divide",vAr110$plaid);
	}
}
