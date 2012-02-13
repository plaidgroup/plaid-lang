package plaid.runtime.models.map;

import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidTag;

public final class PlaidMemberDefMap implements PlaidMemberDef {
	protected String memberName;
	protected PlaidTag definedIn;
	protected boolean anonymous;
	protected boolean mutable;
	protected boolean overrides;
	protected boolean overrideIsBound = false;
	protected String overridenDef;
	protected PlaidObject value;
	
	public static final PlaidMemberDefMap anonymousMember(String memberName, boolean mutable, boolean overrides) {
		return new PlaidMemberDefMap(memberName, true, null, mutable, overrides);
	}
	
	public PlaidMemberDefMap(String memberName, boolean anonymous, PlaidTag definedIn, boolean mutable, boolean overrides) {
		this.memberName = memberName;
		this.definedIn = definedIn;
		this.mutable = mutable;
		this.anonymous = anonymous;
		this.overrides = overrides;
	}

	public PlaidMemberDefMap(PlaidMemberDefMap other) {
		this.memberName = other.memberName;
		this.definedIn  = other.definedIn;
		this.mutable    = other.mutable;
		this.anonymous  = other.anonymous;
		this.overrides  = other.overrides;
		this.value      = other.value;
	}
	
	public PlaidMemberDefMap(String memberName, PlaidTag definedIn) {
		this(memberName, false, definedIn, false, false);
	}
	
	public PlaidMemberDefMap(String memberName) {
		this(memberName, true, null, false, false);
	}
	
	public PlaidMemberDefMap(String memberName, boolean mutable) {
		this(memberName, true, null, mutable, false);
	}
	
	public PlaidMemberDef copy() {
		return new PlaidMemberDefMap(this);
	}

	@Override
	public String getMemberName() {
		return memberName;
	}

	@Override
	public PlaidTag definedIn() {
		return definedIn;
	}

	@Override
	public boolean isAnonymous() {
		return anonymous;
	}
	
	@Override
	public boolean isMutable() {
		return mutable;
	}
	
	@Override
	public String overridenDef() {
		return overridenDef;
	}

	@Override
	public boolean overrides() {
		return overrides;
	}
	
	@Override
	public boolean overrideIsBound() {
		return overrideIsBound;
	}
	
	@Override
	public void bindOverride(String overridenDef) throws PlaidRuntimeException {
		if (!overrideIsBound) {
			this.overridenDef = overridenDef;
			overrideIsBound = true;
		}
		else throw new PlaidRuntimeException("Cannot re-bind an overriden member");
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append(memberName);
		
		if (mutable) 
			ret.append("(mutable");
		else
			ret.append("(immutable");
		
		if (anonymous) {
			ret.append(",anonymous");
		} else {
			if ( definedIn != null ) {
				ret.append("," + definedIn.toString());
			}
		}
		if (overrides) {
			ret.append(", overrides " + (overrideIsBound ? overridenDef : "?") );
		}
		
		ret.append(")");
		return ret.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof PlaidMemberDefMap))
			return false;
		
		PlaidMemberDefMap o = (PlaidMemberDefMap) other;
		return this.toString().equals(o.toString());
	}

	@Override
	public PlaidObject getValue() {
		return value;
	}

	@Override
	public void setValue(PlaidObject value) {
		this.value = value;
	}


}
