package plaid.lang;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.Util;

public class printLine{
    public static plaid.fastruntime.PlaidObject invoke$plaid(final plaid.fastruntime.PlaidObject msg) {
        java.lang.System.out.println(((java.lang.String) ((PlaidJavaObject) msg).getJavaObject()));
        return Util.unit();
    }
}
