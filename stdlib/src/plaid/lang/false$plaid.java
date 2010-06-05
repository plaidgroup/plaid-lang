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
@plaid.runtime.annotations.RepresentsField(name = "false$plaid", toplevel = true)
public class false$plaid{
	public static final java.util.List<plaid.runtime.utils.Import> vAr242$plaid;
	static {
		vAr242$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr242$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr242$plaid);
	@plaid.runtime.annotations.RepresentsField(name = "false$plaid", toplevel = false)
	public static plaid.runtime.PlaidObject false$plaid;
	static {
		final plaid.runtime.PlaidState vAr243$plaid;
		final plaid.runtime.PlaidObject vAr244$plaid;
		vAr244$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("False", current$c0pe);
		vAr243$plaid = plaid.runtime.Util.toPlaidState(vAr244$plaid);
		false$plaid = vAr243$plaid.instantiate();
	}
}
