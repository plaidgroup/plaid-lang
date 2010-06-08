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
@plaid.runtime.annotations.RepresentsMethod(name = "createInt", toplevel = true)
public class createInt{
	public static java.util.List<plaid.runtime.utils.Import> vAr77$plaid;
	static {
		vAr77$plaid = new java.util.ArrayList<plaid.runtime.utils.Import>();
		vAr77$plaid.add(new plaid.runtime.utils.Import("java.math.BigInteger"));
	}
	public static final plaid.runtime.PlaidScope globalScope = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().globalScope("plaid.lang",vAr77$plaid);
	@plaid.runtime.annotations.RepresentsMethod(name = "createInt", toplevel = false)
	public static plaid.runtime.PlaidMethod createInt_func;
	static {
		createInt_func = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambda(new plaid.runtime.utils.Lambda () {
			public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject intStr) throws plaid.runtime.PlaidException {
				plaid.runtime.PlaidObject vAr76$plaid;
				plaid.runtime.PlaidState vAr78$plaid;
				plaid.runtime.PlaidState vAr79$plaid;
				plaid.runtime.PlaidState vAr80$plaid;
				plaid.runtime.PlaidObject vAr81$plaid;
				vAr81$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("Int", globalScope);
				vAr79$plaid = plaid.runtime.Util.toPlaidState(vAr81$plaid);
				vAr80$plaid = plaid.runtime.Util.newState();
				@plaid.runtime.annotations.RepresentsField(name = "value", toplevel = false)
				plaid.runtime.PlaidObject vAr83$plaid;
				vAr83$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().protoField(new plaid.runtime.utils.Delegate () {
					public plaid.runtime.PlaidObject invoke(final plaid.runtime.PlaidObject this$plaid, final plaid.runtime.PlaidObject vAr84$plaid) {
						plaid.runtime.PlaidScope the$c0pe = globalScope;
						final plaid.runtime.PlaidScope current$c0pe = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lambdaScope(the$c0pe, this$plaid);
						plaid.runtime.PlaidObject vAr82$plaid;
						plaid.runtime.PlaidObject vAr85$plaid;
						plaid.runtime.PlaidObject vAr86$plaid;
						plaid.runtime.PlaidObject vAr87$plaid;
						vAr87$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("BigInteger", globalScope);
						vAr85$plaid = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("create", vAr87$plaid);
						vAr86$plaid = intStr;
						vAr82$plaid = plaid.runtime.Util.call(vAr85$plaid, vAr86$plaid);
						return vAr82$plaid;
					}
				}
				);
				vAr80$plaid.addMember("value",vAr83$plaid);
				vAr78$plaid = vAr79$plaid.with(vAr80$plaid);
				vAr76$plaid = vAr78$plaid.instantiate();
				return vAr76$plaid;
			}
		}
		);
	}
}
