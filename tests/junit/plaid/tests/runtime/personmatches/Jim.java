package plaid.tests.runtime.personmatches;

import plaid.fastruntime.Util;

public class Jim{
    public static plaid.fastruntime.PlaidObject name(plaid.fastruntime.PlaidObject this$plaid) {
        plaid.fastruntime.PlaidObject vaR1$plaid;
        vaR1$plaid = plaid.fastruntime.Util.string("Jim");
        return vaR1$plaid;
    }
    public static final plaid.fastruntime.PlaidState theState$plaid;
    static {
        plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("576ecb9c-6669-4a21-98e4-a91ad85499a5");
        plaid.fastruntime.ObjectValue vaR0$plaid = Person.theState$plaid.getObjectValue();
        ovf.parent(vaR0$plaid);
        ovf.mthd("name",0,"plaid.fastexamples.hellocaseof.Jim");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        theState$plaid = Util.makeState(ov);
    }
}
