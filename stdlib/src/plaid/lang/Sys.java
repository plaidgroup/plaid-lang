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

package plaid.lang;

import java.util.ArrayList;
import java.util.List;

public class Sys {
	static List<String> commandArgs;
	
	public static void setArgs(String args[], int firstIndex) {
		List<String> list = new ArrayList<String>(args.length-firstIndex);
		for (int i = firstIndex; i < args.length; ++i)
			list.add(args[i]);
		commandArgs = list; //Collections.unmodifiableList(list);
	}
	
	public static List<String> getCommandLineArguments() {
		return commandArgs;
	}
}
