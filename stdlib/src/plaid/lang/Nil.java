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
	public static final java.util.List<plaid.runtime.utils.Import> vAr33$plaid;
	static {
		vAr33$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr33$plaid.add(new plaid.runtime.utils.Import("plaid.lang.*"));
	}
	public static final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().packageScope("plaid.lang",vAr33$plaid);
	@plaid.runtime.annotations.RepresentsState(name = "Nil", toplevel = false)
	public static final plaid.runtime.PlaidObject Nil;
	static {
		final plaid.runtime.PlaidState vAr34$plaid;
		vAr34$plaid = plaid.runtime.Util.newState();
		@plaid.runtime.annotations.RepresentsMethod(name = "append", toplevel = false)
		final plaid.runtime.PlaidObject vAr35$plaid;
		vAr35$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject cell) {
				plaid.runtime.PlaidScope vAr37$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr37$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr36$plaid;
				final plaid.runtime.PlaidObject vAr38$plaid;
				final plaid.runtime.PlaidObject vAr39$plaid;
				final plaid.runtime.PlaidObject vAr40$plaid;
				vAr40$plaid = cell;
				vAr38$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("copy", vAr40$plaid);
				vAr39$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().unit();
				vAr36$plaid = plaid.runtime.Util.call(vAr38$plaid, vAr39$plaid);
				return vAr36$plaid;
			}
		}
		);
		vAr34$plaid.addMember("append",vAr35$plaid);
		@plaid.runtime.annotations.RepresentsMethod(name = "copy", toplevel = false)
		final plaid.runtime.PlaidObject vAr41$plaid;
		vAr41$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoMethod(new plaid.runtime.utils.Delegate () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr28$plaid) {
				plaid.runtime.PlaidScope vAr43$plaid = current$c0pe;
				final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(vAr43$plaid, this$plaid);
				final plaid.runtime.PlaidObject vAr42$plaid;
				final plaid.runtime.PlaidState vAr44$plaid;
				final plaid.runtime.PlaidObject vAr45$plaid;
				vAr45$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Nil", current$c0pe);
				vAr44$plaid = plaid.runtime.Util.toPlaidState(vAr45$plaid);
				vAr42$plaid = vAr44$plaid.instantiate();
				return vAr42$plaid;
			}
		}
		);
		vAr34$plaid.addMember("copy",vAr41$plaid);
		Nil = vAr34$plaid.getPrototype();
	}
	@plaid.runtime.annotations.RepresentsTag(name = "plaid.lang.Nil")
	public static final plaid.runtime.PlaidTag Nil$Tag$plaid;
	static {
		final plaid.runtime.PlaidState vAr46$plaid;
		final plaid.runtime.PlaidObject vAr47$plaid;
		vAr47$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("ListCell", current$c0pe);
		vAr46$plaid = plaid.runtime.Util.toPlaidState(vAr47$plaid);
		Nil$Tag$plaid = plaid.runtime.Util.tag("plaid.lang.Nil", vAr46$plaid);
	}
}
