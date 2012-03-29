package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.generated.Iprintln$1$plaid;
import plaid.generated.IunitTest$1$plaid;
import plaid.lang.True;
import plaid.lang.Unit;


public class JavaInteropTest {

	PlaidJavaObject test = (PlaidJavaObject) Util.javaToPlaid(new JavaInterop());
	
	@Test
	public void unit() {
		try {
			PlaidObject result = ((IunitTest$1$plaid) test.getDispatch()).unitTest(test, Util.unit());
			Assert.assertTrue(result == True.TRUE_VALUE);
			PlaidObject result2 = ((Iprintln$1$plaid) test.getDispatch()).println(test, Util.string("testing"));
			Assert.assertTrue(result2 == Unit.UNIT_VALUE);
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void nullAndUnit() {
		Assert.assertTrue(Unit.UNIT_VALUE.getJavaObject() == null);
	}
	
	public class JavaInterop{
		public boolean unitTest(Object o) {
			return o == null;
		}
		
		public void println(String s) {
			System.out.println(s);
		}
	}
	
}
