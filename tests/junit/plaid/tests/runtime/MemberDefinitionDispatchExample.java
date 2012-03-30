package plaid.tests.runtime;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidDispatch;
import plaid.generated.Iadd$1$plaid;

public class MemberDefinitionDispatchExample extends AbstractPlaidDispatch implements Iadd$1$plaid{

	public MemberDefinitionDispatchExample(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject add(PlaidObject x, PlaidObject y) {
		int mdIndex = x.getDispatch().getObjectValue().getMemberDefinitionIndex("XXX");
		return ((Iadd$1$plaid)x.getMemberDefs()[mdIndex]).add(x,y);
	}

}
