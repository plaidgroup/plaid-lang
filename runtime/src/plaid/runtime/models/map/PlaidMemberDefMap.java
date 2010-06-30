package plaid.runtime.models.map;

import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidTag;

public class PlaidMemberDefMap implements PlaidMemberDef {

	private String memberName;
	private PlaidTag definedIn;
	private boolean anonymous;
	private boolean mutable;
	
	public PlaidMemberDefMap(String memberName, PlaidTag definedIn, boolean mutable) {
		this.memberName = memberName;
		this.definedIn = definedIn;
		this.mutable = mutable;
		anonymous = (definedIn == null);
	}
	
	public PlaidMemberDefMap(String memberName, PlaidTag definedIn) {
		this(memberName, definedIn, false);
	}
	
	public PlaidMemberDefMap(String memberName) {
		this(memberName, null, false);
	}
	
	public PlaidMemberDefMap(String memberName, boolean mutable) {
		this(memberName, null, mutable);
	}

	public String getMemberName() {
		return memberName;
	}

	public PlaidTag definedIn() {
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
			ret.append("," + definedIn.getName() + ")");
		
		
		return ret.toString();
	}
	
	
}
