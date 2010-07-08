package plaid.runtime.models.map;

import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidState;

public class PlaidMemberDefMap implements PlaidMemberDef {

	private String memberName;
	private String definedIn;
	private boolean anonymous;
	private boolean mutable;
	
	public PlaidMemberDefMap(String memberName, String definedIn, boolean mutable) {
		this.memberName = memberName;
		this.definedIn = definedIn;
		this.mutable = mutable;
		anonymous = (definedIn.equals("<Anonymous>"));  //TODO : This should be done better
	}
	
	public PlaidMemberDefMap(String memberName, String definedIn) {
		this(memberName, definedIn, false);
	}
	
	public PlaidMemberDefMap(String memberName) {
		this(memberName, "<Anonymous>", false);
	}
	
	public PlaidMemberDefMap(String memberName, boolean mutable) {
		this(memberName, null, mutable);
	}

	public String getMemberName() {
		return memberName;
	}

	public String definedIn() {
		return definedIn;
	}

	public boolean isAnonymous() {
		return anonymous;
	}
	
	public boolean isMutable() {
		return mutable;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(memberName);
		
		if (mutable) 
			ret.append("(mutable");
		else
			ret.append("(immutable");
		
		if (anonymous)
			ret.append(",anonymous)");
		else
			ret.append("," + definedIn.toString() + ")");
		
		
		return ret.toString();
	}
	
	
}
