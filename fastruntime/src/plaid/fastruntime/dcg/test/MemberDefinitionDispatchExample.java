package plaid.fastruntime.dcg.test;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.generated.Iadd$1$plaid;

public class MemberDefinitionDispatchExample extends AbstractPlaidState implements Iadd$1$plaid{

	public MemberDefinitionDispatchExample(ObjectValue metadata) {
		super(metadata);
	}

	@Override
	public PlaidObject add(PlaidObject x, PlaidObject y) {
		int mdIndex = x.getDispatch().getObjectValue().getMemberDefinitionIndex("XXX");
		return ((Iadd$1$plaid)x.getMemberDefs()[mdIndex]).add(x,y);
	}

}