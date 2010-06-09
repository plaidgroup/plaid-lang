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
@plaid.runtime.annotations.RepresentsState(name = "TrueState", toplevel = true)
public class TrueState{
	public static final java.util.List<plaid.runtime.utils.Import> vAr2$plaid;
	static {
		vAr2$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr2$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope global$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr2$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "TrueState", toplevel = false)
	public static final plaid.runtime.PlaidObject TrueState;
	static {
		final plaid.runtime.PlaidScope local$c0pe = new plaid.runtime.PlaidLocalScope(global$c0pe);
		final plaid.runtime.PlaidState vAr3$plaid;
		vAr3$plaid = plaid.runtime.Util.newState();
		TrueState = vAr3$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.TrueState")
	public static final plaid.runtime.PlaidTag TrueState$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr4$plaid;
		final plaid.runtime.PlaidObject vAr5$plaid;
		vAr5$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Boolean", global$c0pe);
		vAr4$plaid = plaid.runtime.Util.toPlaidState(vAr5$plaid);
		TrueState$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.TrueState", vAr4$plaid);
	}
}
