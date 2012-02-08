package plaid.fastruntime.dcg.test;

import static fj.P.p;
import static fj.data.List.list;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidStorage;
import plaid.fastruntime.dcg.StorageGenerator;
import fj.P2;
import fj.data.Set;
import fj.Ord;


public class StorageGeneratorTests {
	@Test
	public void tryXY() {
		StorageGenerator sg = new StorageGenerator();
		P2<Boolean,String> fieldX = p(true, "x");
		P2<Boolean,String> fieldY = p(false, "y");
		PlaidStorage storage = sg.createStorage(list(fieldX,fieldY));
		
		Method[] methods = storage.getClass().getMethods();
		for(Method method : methods) {
			if(method.getDeclaringClass().equals(storage.getClass())) {
				System.out.println(method.getName());
				Assert.assertTrue("unexpected method name", method.getName().equals("getx") || method.getName().equals("gety") ||
				method.getName().equals("setx"));
			}
		}
	}
	
	
	@Test
	public void hasInterfacesXY() {
		StorageGenerator sg = new StorageGenerator();
		P2<Boolean,String> fieldX = p(true, "x");
		P2<Boolean,String> fieldY = p(false, "y");
		PlaidStorage storage = sg.createStorage(list(fieldX,fieldY));
		
		Class<?>[] ifaces = storage.getClass().getInterfaces();
		ArrayList<String> actualIfaceStrings = new ArrayList<String>();
		ArrayList<String> goalIfaceStrings = new ArrayList<String>();
		Collections.addAll(
				goalIfaceStrings,
				"plaid.fastruntime.PlaidStorage",
				"plaid.generated.Igetx$0$plaid",
				"plaid.generated.Isetx$1$plaid",
				"plaid.generated.Igety$0$plaid");
		Set<String> goal = Set.iterableSet(Ord.stringOrd, goalIfaceStrings);
		for(Class<?> iface : ifaces) {
			actualIfaceStrings.add(iface.getName());
		}
		Set<String> actual = Set.iterableSet(Ord.stringOrd, actualIfaceStrings);
		Assert.assertTrue("goal set is not a subset of actual set", goal.subsetOf(actual));
		Assert.assertTrue("actual set is not a subset of goal set", actual.subsetOf(goal));
	}
	
	
}
