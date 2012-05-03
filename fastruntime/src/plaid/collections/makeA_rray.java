package plaid.collections;

import plaid.fastruntime.PlaidJavaObject;

public class makeA_rray {
	public static plaid.fastruntime.PlaidObject invoke$plaid(plaid.fastruntime.PlaidObject size) {
		int intSize = ((Integer)((PlaidJavaObject)size).getJavaObject()).intValue();
		return A_rray.plaidArray(intSize);
    }
}
