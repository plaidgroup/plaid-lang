package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.DimensionValue;
import plaid.fastruntime.reference.MethodValue;
import plaid.generated.IdoAddFive$1$plaid;

public class SingleMethodState {

	@Test
	public void test() {
		MethodValue mv = MethodValue.createMethodWithStaticDefinition("doAddFive",1,"plaid/tests/runtime/SingleMethodState");
		DimensionValue dv = new DimensionValue("plaid/tests/runtime/SingleMethodState", mv, null);
		PlaidDispatch ps = Util.DISPATCH_GEN.createStateInstance(dv);
		PlaidJavaObject returnedValue = (PlaidJavaObject) ((IdoAddFive$1$plaid)ps).doAddFive(Util.unit(), Util.integer(7));
		//System.out.println(returnedValue.getJavaObject().toString());
		Assert.assertEquals("12", returnedValue.getJavaObject().toString());
	}
	
	@Test 
	public void test2() {
		plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("8bc23ee5-bcb7-4b8f-b7de-afcda749e09d");
        ovf.mthd("doAddFive",1,"plaid.tests.runtime.SingleMethodState");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        PlaidDispatch ps = plaid.fastruntime.Util.DISPATCH_GEN.createStateInstance(ov);
        PlaidJavaObject returnedValue = (PlaidJavaObject) ((IdoAddFive$1$plaid)ps).doAddFive(Util.unit(), Util.integer(7));
		//System.out.println(returnedValue.getJavaObject().toString());
		Assert.assertEquals("12", returnedValue.getJavaObject().toString());
	}
	
	public static plaid.fastruntime.PlaidObject doAddFive(plaid.fastruntime.PlaidObject this$plaid, plaid.fastruntime.PlaidObject y) {
		plaid.fastruntime.PlaidObject vaR0$plaid;
		plaid.fastruntime.PlaidObject vaR1$plaid;
		vaR1$plaid = plaid.fastruntime.Util.integer(5);
		final plaid.fastruntime.PlaidObject x = vaR1$plaid;
		plaid.fastruntime.PlaidObject vaR2$plaid;
		plaid.fastruntime.PlaidObject vaR3$plaid;
		vaR3$plaid = x;
		plaid.fastruntime.PlaidObject vaR4$plaid;
		vaR4$plaid = y;
		vaR2$plaid = ((plaid.generated.Iplus$plaid$1$plaid)vaR3$plaid.getDispatch()).plus$plaid(vaR3$plaid,vaR4$plaid);
		final plaid.fastruntime.PlaidObject z = vaR2$plaid;
		vaR0$plaid = z;
		return vaR0$plaid;
	}

}
