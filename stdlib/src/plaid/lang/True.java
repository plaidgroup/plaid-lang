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
@plaid.runtime.annotations.RepresentsState(name = "True", toplevel = true)
public class True{
	public static final java.util.List<plaid.runtime.utils.Import> vAr231$plaid;
	static {
		vAr231$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr231$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr231$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "True", toplevel = false)
	public static final plaid.runtime.PlaidObject True;
	static {
		final plaid.runtime.PlaidState vAr232$plaid;
		vAr232$plaid = plaid.runtime.Util.newState();
		True = vAr232$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.True")
	public static final plaid.runtime.PlaidTag True$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr233$plaid;
		final plaid.runtime.PlaidObject vAr234$plaid;
		vAr234$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Boolean", current$c0pe);
		vAr233$plaid = plaid.runtime.Util.toPlaidState(vAr234$plaid);
		True$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.True", vAr233$plaid);
	}
}
