package plaid.tests.runtime.personmatches;
public class Person{
    public static plaid.fastruntime.PlaidObject greeting(plaid.fastruntime.PlaidObject this$plaid) {
        plaid.fastruntime.PlaidObject vaR0$plaid;
        plaid.fastruntime.PlaidObject vaR1$plaid;
        plaid.fastruntime.PlaidObject vaR2$plaid;
        vaR2$plaid = plaid.fastruntime.Util.string("hello ");
        plaid.fastruntime.PlaidObject vaR3$plaid;
        plaid.fastruntime.PlaidObject vaR4$plaid;
        vaR4$plaid = this$plaid;
        vaR3$plaid = ((plaid.generated.Iname$0$plaid)vaR4$plaid.getDispatch()).name(vaR4$plaid);
        vaR1$plaid = ((plaid.generated.Iplus$plaid$1$plaid)vaR2$plaid.getDispatch()).plus$plaid(vaR2$plaid,vaR3$plaid);
        plaid.fastruntime.PlaidObject vaR5$plaid;
        vaR5$plaid = plaid.fastruntime.Util.string("!");
        vaR0$plaid = ((plaid.generated.Iplus$plaid$1$plaid)vaR1$plaid.getDispatch()).plus$plaid(vaR1$plaid,vaR5$plaid);
        return vaR0$plaid;
    }
    public static final plaid.fastruntime.PlaidDispatch theState$plaid;
    static {
        plaid.fastruntime.reference.ObjectValueFactory ovf = new plaid.fastruntime.reference.ObjectValueFactory();
        ovf.tag("3eda0de5-7115-4b69-9bdc-372e47da56be");
        ovf.mthd("greeting",0,"plaid.fastexamples.hellocaseof.Person");
        plaid.fastruntime.ObjectValue ov = ovf.value();
        theState$plaid = plaid.fastruntime.Util.DISPATCH_GEN.createStateInstance(ov);
    }
}
