package plaid.tests.runtime;

import junit.framework.Assert;

import org.junit.Test;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.reference.SimplePlaidObject;
import plaid.generated.Iname$0$plaid;
import plaid.generated.Igreeting$0$plaid;

public class H_elloP_ersonT_est {
	
    public static plaid.fastruntime.PlaidObject greeting(plaid.fastruntime.PlaidObject this$plaid) {
        plaid.fastruntime.PlaidObject vaR0$plaid;
        plaid.fastruntime.PlaidObject vaR1$plaid;
        plaid.fastruntime.PlaidObject vaR2$plaid;
        vaR2$plaid = plaid.fastruntime.Util.string("hello ");
        plaid.fastruntime.PlaidObject vaR3$plaid;
        plaid.fastruntime.PlaidObject vaR4$plaid;
        vaR4$plaid = this$plaid;
        vaR3$plaid = ((Iname$0$plaid)vaR4$plaid.getDispatch()).name(vaR4$plaid);
        vaR1$plaid = ((plaid.generated.Iplus$plaid$1$plaid)vaR2$plaid.getDispatch()).plus$plaid(vaR2$plaid,vaR3$plaid);
        plaid.fastruntime.PlaidObject vaR5$plaid;
        vaR5$plaid = plaid.fastruntime.Util.string("!");
        vaR0$plaid = ((plaid.generated.Iplus$plaid$1$plaid)vaR1$plaid.getDispatch()).plus$plaid(vaR1$plaid,vaR5$plaid);
        return vaR0$plaid;
    }
    
    public static plaid.fastruntime.PlaidObject name(plaid.fastruntime.PlaidObject this$plaid) {
        plaid.fastruntime.PlaidObject vaR0$plaid;
        vaR0$plaid = plaid.fastruntime.Util.string("Jane");
        return vaR0$plaid;
    }
    
    public static final plaid.fastruntime.PlaidDispatch theState$plaid;
    static {
        plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("81ff3c7e-2f12-4433-904c-ecd2af212512");
        ovf.mthd("greeting",0,"plaid.tests.runtime.HelloPersonTest");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        theState$plaid = plaid.fastruntime.Util.DISPATCH_GEN.createStateInstance(ov);
    }
    
    @Test
    public void test() {
    	plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("plaid/fastexamples/hellocaseof/Jane");
        ovf.parent(theState$plaid.getObjectValue());
        ovf.mthd("name",0,"plaid.tests.runtime.HelloPersonTest");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        PlaidDispatch janeDispatch = plaid.fastruntime.Util.DISPATCH_GEN.createStateInstance(ov);
        PlaidObject jane = new SimplePlaidObject(janeDispatch, new PlaidObject[0]);
        PlaidJavaObject myGreeting = (PlaidJavaObject)((Igreeting$0$plaid)jane.getDispatch()).greeting(jane);
        Assert.assertEquals("hello Jane!", myGreeting.getJavaObject().toString());
    }

}
