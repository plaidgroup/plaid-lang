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

package plaid.gui;

import java.awt.*;
import plaid.runtime.*;
import plaid.runtime.models.map.PlaidJavaObjectMap;

// TODO: replace this with pure Plaid code when Plaid/Java interop improves
public class ExtendedJavaCanvas extends java.awt.Canvas {
	PlaidMethod plaidFunction;
	public void setFunction(PlaidMethod f) {
		System.out.println("instance of " + f.getClass());
		plaidFunction = f;
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("invoking paint");
		plaidFunction.invoke(new PlaidJavaObjectMap(g));
		System.out.println("invoked paint");
	}
}