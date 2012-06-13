package plaid.lang;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.Util;

public class printL_ine{
    public static plaid.fastruntime.PlaidObject invoke$plaid(final plaid.fastruntime.PlaidObject msg) {
        java.lang.System.out.println(((java.lang.String) ((PlaidJavaObject) msg).getJavaObject()));
        return Util.unit();
    }
}
