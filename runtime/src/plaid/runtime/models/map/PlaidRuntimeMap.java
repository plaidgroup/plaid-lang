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
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.runtime.models.map;

import java.util.Map;

import plaid.runtime.PlaidClassLoader;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidTailCall;
import plaid.runtime.Util;

public final class PlaidRuntimeMap extends PlaidRuntime {

	@Override
	public PlaidObject call(PlaidObject func, PlaidObject args) {
		PlaidObject this$plaid = null;
		String name = "";
		if ( enableEvent ) {
			if (func instanceof PlaidMethodMap) {
				PlaidMethodMap pom = (PlaidMethodMap)func;
				this$plaid = pom.varthis;
				for (Map.Entry<String, PlaidMemberDef> entry : this$plaid.getMembers().entrySet() ) {
					if ( entry.getValue() == func ) {
						name = entry.getValue().getMemberName();
						enterCall(this$plaid, name);
					}
				}
			} else if ( func instanceof PlaidJavaMethodMap ) {
				PlaidJavaMethodMap pjom = (PlaidJavaMethodMap)func;
				this$plaid = PlaidClassLoaderMap.getClassLoader().packJavaObject(pjom.instance);
				name = pjom.name;
				enterCall(this$plaid, pjom.name);

			} else if ( func instanceof PlaidFunctionMap ) {
				PlaidFunctionMap pfm = (PlaidFunctionMap)func;
				this$plaid = PlaidClassLoaderMap.getClassLoader().unit();
				name = pfm.getName();
				enterCall(this$plaid, name);
			}
		}
		PlaidObject result;
		try {
			result = Util.toPlaidMethod(func).invoke(args);
			
			//deal with tail calls
			while (result instanceof PlaidTailCall) {
				PlaidTailCall tc = (PlaidTailCall) result;
				result = (tc.getMethod()).invoke(tc.getArgs());
			}
		} catch (ClassCastException exn) {
			throw new PlaidInvalidArgumentException("Attempt to call " + this$plaid + "." + name + "() on " + args + " failed.");
		}
		
		if ( enableEvent ) {
			leaveCall(this$plaid, name);
		}

		return result;
	}

	@Override
	public PlaidClassLoader getClassLoader() {
		return PlaidClassLoaderMap.getClassLoader();
	}
}
