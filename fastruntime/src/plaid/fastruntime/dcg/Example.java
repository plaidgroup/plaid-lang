package plaid.fastruntime.dcg;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidDispatch;

public class Example extends AbstractPlaidDispatch {

	public Example(ObjectValue metadata) {
		super(metadata);
	}
	
	public PlaidObject getx(PlaidObject thisVar) {
		return thisVar.getStorage()[2];
	}
	
	public PlaidObject x(PlaidObject thisVar, PlaidObject arg1, PlaidObject arg2, PlaidObject arg3) {
		return ((plaid.fastruntime.PlaidLambda$4)getx(thisVar)).invoke$plaid(thisVar, arg1, arg2, arg3);
	}

}
