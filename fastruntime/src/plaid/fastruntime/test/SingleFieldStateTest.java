package plaid.fastruntime.test;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.reference.SimplePlaidObject;
import plaid.generated.Igetx$plaid$0$plaid;


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
        ovf.field("x","plaid.fastruntime.test.SingleFieldStateTest");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        PlaidState ps = Util.DISPATCH_GEN.createStateInstance(ov);
        PlaidObject[] storage = ps.getStorage();
        PlaidObject o = new SimplePlaidObject(ps, storage);
        PlaidJavaObject returnedValue = (PlaidJavaObject) ((Igetx$plaid$0$plaid)o.getDispatch()).getx$plaid(o);
		Assert.assertEquals("5", returnedValue.getJavaObject().toString());
	}
    
    

}
