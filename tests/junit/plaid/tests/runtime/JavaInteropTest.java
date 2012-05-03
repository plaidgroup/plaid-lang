package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.generated.Iprintln$1$plaid;
import plaid.generated.IunitT_est$1$plaid;
import plaid.lang.T_rue;
import plaid.lang.U_nit;


public class JavaInteropTest {

	PlaidJavaObject test = (PlaidJavaObject) Util.javaToPlaid(new JavaInterop());
	
	@Test
	public void unit() {
		try {
			PlaidObject result = ((IunitT_est$1$plaid) test.getDispatch()).unitT_est(test, Util.unit());
			Assert.assertTrue(result == T_rue.TRUE_VALUE);
			PlaidObject result2 = ((Iprintln$1$plaid) test.getDispatch()).println(test, Util.string("testing"));
			Assert.assertTrue(result2 == U_nit.UNIT_VALUE);
		} catch (PlaidIllegalOperationException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void nullAndUnit() {
		Assert.assertTrue(U_nit.UNIT_VALUE.getJavaObject() == null);
	}
	
	public class JavaInterop{
		public boolean unitT_est(Object o) {
			return o == null;
		}
		
		public void println(String s) {
			System.out.println(s);
		}
	}
	
}
