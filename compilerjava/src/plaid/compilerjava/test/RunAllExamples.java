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
 
package plaid.compilerjava.test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import plaid.runtime.PlaidRuntime;
import uk.ac.lkl.common.util.testing.LabelledParameterized;

@RunWith(LabelledParameterized.class)
public class RunAllExamples {
	
	private Method method;
	private String label;
	public RunAllExamples(String label, Method method) {
		this.method = method;
		this.label = label;
	}
	
	@Test
	public void run() {
		System.out.println("---- " + label + " ------");
		Object[] o = new Object[1];
		o[0] = new String[0];
		try {
			method.invoke(null, o);
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			StringBuilder sb = new StringBuilder("\n" + cause.toString());
			for(StackTraceElement ste : cause.getStackTrace()) {
				sb.append("\n");
				sb.append(ste.toString());
			}
			System.out.println("**********EXCEPTION**********");
			Assert.fail(sb.toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Assert.fail(e.toString());
		} finally {
			PlaidRuntime.getRuntime().shutdown();
		}
	}
	
	@Parameters
	public static Collection<Object[]> mainMethods() {
		return getAll(new File("bin/coreExamples"), "coreExamples");
	}
	
	public static Collection<Object[]> getAll(File parentFile, String pkg) {
		File[] files = parentFile.listFiles();
		Collection<Object[]> methods = new ArrayList<Object[]>();
		for (File file : files) {
			if (file.isDirectory()) {
				if (pkg == "")  {
					methods.addAll(getAll(file, file.getName()));
				} else {
					methods.addAll(getAll(file, pkg + "." + file.getName()));
				}
			} else if (file.isFile()) {
				if (file.getName().contains(".class") && !file.getName().contains("$")) {
					plaid.runtime.PlaidRuntime.getRuntime().shutdown();
					ClassLoader cl = ClassLoader.getSystemClassLoader();
					try {
						String className = file.getName().substring(0, file.getName().length()-6);
						Class<?> mainClass = cl.loadClass(pkg+"."+className);
						for(Method method : mainClass.getMethods()) {
							if (method.getName().equals("main")) {
								methods.add(new Object[] {pkg + "." + className, method} );
							}
						}
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(e);
					}
				}
			} else {
				throw new RuntimeException("File is not file or directory");
			}
		}
		return methods;
	}
}
