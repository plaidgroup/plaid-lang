package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.generated.Iadd$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.ItoString$0$plaid;

public class JavaDispatchGenTest {

	@Test
	public void dispatchWorks() {
		TestJavaDispatchGen one = new TestJavaDispatchGen(1);
		TestJavaDispatchGen two = new TestJavaDispatchGen(2);
		
		PlaidObject onePlaid = Util.javaToPlaid(one);
		PlaidObject twoPlaid = Util.javaToPlaid(two);
		PlaidObject oneString = ((ItoString$0$plaid) onePlaid.getDispatch()).toString(onePlaid);
		PlaidObject twoString = ((ItoString$0$plaid) twoPlaid.getDispatch()).toString(twoPlaid);
		
		Assert.assertTrue(((PlaidJavaObject) oneString).getJavaObject().equals("1"));
		Assert.assertTrue(((PlaidJavaObject) twoString).getJavaObject().equals("2"));
	}
	
	@Test
	public void primitiveError() {
		TestJavaDispatchGen one = new TestJavaDispatchGen(1);
		TestJavaDispatchGen two = new TestJavaDispatchGen(2);
		PlaidObject onePlaid = Util.javaToPlaid(one);
		PlaidObject twoPlaid = Util.javaToPlaid(two);
		
		try {
		
		PlaidObject result = ((Iadd$1$plaid) onePlaid.getDispatch()).add(onePlaid,twoPlaid);
		Assert.fail();
		
		} catch (PlaidIllegalOperationException e) {
			if (!e.getMessage().contains("primitive."))
				Assert.fail();
		}
		
		
	}
	
	@Test
	public void argumentError1() {
		TestJavaDispatchGen one = new TestJavaDispatchGen(1);
		Object two = this;
		PlaidObject onePlaid = Util.javaToPlaid(one);
		PlaidObject twoPlaid = Util.javaToPlaid(two);
		
		try {
		
		PlaidObject result = ((Ieqeq$plaid$1$plaid) onePlaid.getDispatch()).eqeq$plaid(onePlaid,twoPlaid);
		Assert.fail();
		
		} catch (PlaidIllegalOperationException e) {
			if (!e.getMessage().startsWith("Invalid argument type passed to java method"))
				Assert.fail();
		}
		
		
	}

	
	public class TestJavaDispatchGen {
		
		private int i;
		
		public TestJavaDispatchGen(int i) {
			this.i = i;
		}
		
		public String toString() {
			return "" + this.i;
		}
		
		public void add(int i) {
			this.i += i;
		}
		
		public boolean eqeq$plaid(TestJavaDispatchGen other) {
			return this.i == other.i;
		}
	}

	
	
	
}
