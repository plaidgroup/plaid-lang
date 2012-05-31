package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.SimplePlaidObject;
import plaid.generated.Igetx$plaid$0$plaid;
import plaid.generated.Isetx$plaid$1$plaid;


public class SingleFieldStateTest {
	
    public static plaid.fastruntime.PlaidFieldInitializer x;
    static {
        x = new plaid.fastruntime.PlaidFieldInitializer() { 
            @Override
            public plaid.fastruntime.PlaidObject invoke$plaid() {
                return plaid.fastruntime.Util.integer(5);
            }
        };
    }
    
	@Test 
	public void test() {
		plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("8bc23ee5-bcb7-4b8f-b7de-afcda749e09d");
        ovf.field(false, "x","plaid.tests.runtime.SingleFieldStateTest");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        PlaidState ps = Util.makeState(ov);
        PlaidObject[] storage = ps.getObjectValue().getDefaultStorage(null);
        PlaidObject o = new SimplePlaidObject(ps.getDispatch(), storage);
        PlaidJavaObject returnedValue = (PlaidJavaObject) ((Igetx$plaid$0$plaid)o.getDispatch()).getx$plaid(o);
		Assert.assertEquals("5", returnedValue.getJavaObject().toString());
	}
	
	@Test 
	public void test2() {
		plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("8bc23ee5-bcb7-4b8f-b7de-afcda749e09d");
        ovf.field(true, "x","plaid.tests.runtime.SingleFieldStateTest");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        PlaidState ps = Util.makeState(ov);
        PlaidObject[] storage = ps.getObjectValue().getDefaultStorage(null);
        PlaidObject o = new SimplePlaidObject(ps.getDispatch(), storage);
        ((Isetx$plaid$1$plaid)o.getDispatch()).setx$plaid(o,Util.integer(3));
        PlaidJavaObject returnedValue = (PlaidJavaObject) ((Igetx$plaid$0$plaid)o.getDispatch()).getx$plaid(o);
		Assert.assertEquals("3", returnedValue.getJavaObject().toString());
	}
    
    

}
