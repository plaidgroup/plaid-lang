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
 
package plaid.runtime.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import plaid.runtime.utils.QualifiedIdentifier;

public class QualifiedIdentifierTest extends BaseTest {
	@Test
	public void QIFromString() {
		QualifiedIdentifier qi = QualifiedIdentifier.getQI("foo.bar.baz");
		assertTrue( qi.getSuffix().equals("baz"));
		
		QualifiedIdentifier prefix1 = qi.getPrefix();
		assertTrue( prefix1.isEmpty() == false );
		assertTrue( prefix1.getSuffix().equals("bar"));
		
		QualifiedIdentifier prefix2 = prefix1.getPrefix();
		assertTrue( prefix2.isEmpty() == false );
		assertTrue( prefix2.getSuffix().equals("foo"));
		
		QualifiedIdentifier prefix3 = prefix2.getPrefix();
		assertTrue( prefix3.isEmpty() == true );
	}

}
