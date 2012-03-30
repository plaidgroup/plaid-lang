package plaid.tests.runtime.personmatches;

import plaid.fastruntime.Util;

public class Jane{
    public static plaid.fastruntime.PlaidObject name(plaid.fastruntime.PlaidObject this$plaid) {
        plaid.fastruntime.PlaidObject vaR1$plaid;
        vaR1$plaid = plaid.fastruntime.Util.string("Jane");
        return vaR1$plaid;
    }
    public static final plaid.fastruntime.PlaidState theState$plaid;
    static {
        plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("0568f3de-0760-476d-8250-2adff5d812b9");
        plaid.fastruntime.ObjectValue vaR0$plaid = Person.theState$plaid.getObjectValue();
        ovf.parent(vaR0$plaid);
        ovf.mthd("name",0,"plaid.fastexamples.hellocaseof.Jane");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        theState$plaid = Util.makeState(ov);
    }
}
