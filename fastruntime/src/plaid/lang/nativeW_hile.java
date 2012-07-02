package plaid.lang;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidLambda$0;

public class nativeW_hile {
	public static final plaid.fastruntime.PlaidObject theField$plaid;
    static {
        theField$plaid = new plaid.fastruntime.PlaidLambda$1() {
            public plaid.fastruntime.PlaidObject invoke$plaid(final plaid.fastruntime.PlaidObject cond) {
                return new plaid.fastruntime.PlaidLambda$1() {
                    public plaid.fastruntime.PlaidObject invoke$plaid(final plaid.fastruntime.PlaidObject body) {
                    	PlaidLambda$0 condition = (PlaidLambda$0)cond;
                    	boolean conditionBool = (Boolean)((PlaidJavaObject)condition.invoke$plaid()).getJavaObject();
                    	PlaidObject result = plaid.fastruntime.Util.unit();
                    	while(conditionBool) {
                    		result = ((PlaidLambda$0)body).invoke$plaid();
                    		conditionBool = (Boolean)((PlaidJavaObject)condition.invoke$plaid()).getJavaObject();
                    	}
                    	return result;
                    }
                };
            }
        };
    }
}
