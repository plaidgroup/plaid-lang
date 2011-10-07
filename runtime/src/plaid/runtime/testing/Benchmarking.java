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

import org.junit.Test;

import plaid.runtime.PlaidClassLoader;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;

public class Benchmarking extends BaseTest {
	protected int N = 100;
	
	@Test
	public void TimeToLoadPlaidState() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		long start = System.nanoTime();
		cl.lookup("plaid.lang.Pair", cl.unit());
		long end = System.nanoTime();
		System.out.println("Time to load Pair state for 1st time : " + (end-start) + " ns");
		
		start = System.nanoTime();
		for (int i = 0; i < N; i++) {
			cl.lookup("plaid.lang.Pair", cl.unit());			
		}
		end = System.nanoTime();
		System.out.println("Time to load Pair state for Nst time : " + ((end-start)/N) + " ns");

	}
	
	@Test
	public void TimeToCreateInstance() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidState ps;
		ps = (PlaidState) cl.lookup("plaid.lang.Pair", cl.unit());
		
		long start = System.nanoTime();
		for (int i = 0; i < N ; i++) {
			ps.instantiate();
		}
		long end = System.nanoTime();
		
		System.out.println("Time to create instance of Pair: " + ((end-start)/N) + " ns");
	}

	@Test
	public void TimeForPlaidMethodInvokation() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject po;
		PlaidState ps;
		ps = (PlaidState) cl.lookup("plaid.lang.Pair", cl.unit());
		po = ps.instantiate();
		PlaidMethod getFirst = (PlaidMethod) Util.lookup("first", po);
		
		long start = System.nanoTime();
		for (int i = 0; i < N ; i++) {
			Util.call(getFirst, cl.unit());
		}
		long end = System.nanoTime();
		
		System.out.println("Time for PlaidMethod invocation: " + ((end-start)/N) + " ns");
	}
	
	@Test
	public void TimeForPlaidJavaMethodInvokation() {
		PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
		PlaidObject dot = Util.string(".");
		PlaidState javaSystem = (PlaidState)cl.lookup("java.lang.System", cl.unit());
		PlaidObject sysOut = cl.lookup("out", javaSystem);
		PlaidMethod print = (PlaidMethod) cl.lookup("print", sysOut);
				
		long start = System.nanoTime();
		for (int i = 0; i < N ; i++) {
			Util.call(print, dot);
		}
		long end = System.nanoTime();
		
		System.out.println("\nTime for PlaidJavaMethod invocation: " + ((end-start)/N) + " ns");
	}
}
