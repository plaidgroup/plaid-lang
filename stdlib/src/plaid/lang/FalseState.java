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
@plaid.runtime.annotations.RepresentsState(name = "FalseState", toplevel = true)
public class FalseState{
	public static final java.util.List<plaid.runtime.utils.Import> vAr6$plaid;
	static {
		vAr6$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr6$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr6$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "FalseState", toplevel = false)
	public static final plaid.runtime.PlaidObject FalseState;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr7$plaid;
		vAr7$plaid = plaid.runtime.Util.newState();
		FalseState = vAr7$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.FalseState")
	public static final plaid.runtime.PlaidTag FalseState$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr8$plaid;
		final plaid.runtime.PlaidObject vAr9$plaid;
		vAr9$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Boolean", global$c0pe);
		vAr8$plaid = plaid.runtime.Util.toPlaidState(vAr9$plaid);
		FalseState$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.FalseState", vAr8$plaid);
	}
}
