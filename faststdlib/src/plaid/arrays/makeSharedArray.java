package plaid.arrays;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.lang.Integer32.Integer32PlaidJavaObject;

public class makeSharedArray {
    public static plaid.fastruntime.PlaidObject invoke$plaid(final plaid.fastruntime.PlaidObject plaidOrder) {
       PlaidObject result = null;
       
       if ( plaidOrder instanceof Integer32PlaidJavaObject ) {
    	   int order = ((Integer32PlaidJavaObject)plaidOrder).integerValue;
    	   int bucketCount = (int)Math.pow(2, order);
    	   if ( bucketCount <= 0  || bucketCount >= Math.pow(2, 24)) {
    		   throw new PlaidIllegalArgumentException("Cannot allocate hashtable with " + bucketCount + " buckets.");
    	   }
       } else {
    	   throw new PlaidIllegalArgumentException("Parameter of 'makeSharedArray' needs to be an integer");
       }
       
       return result;
    }
}
