package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MemberDefInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class MemberDefValue extends AtomicValue implements MemberDefInfo {
	
	private final String memberDefId;
	private final Object memberDefInstance;
	
	public  MemberDefValue(String memberDefId, Object memberDefInstance){
		this.memberDefId = memberDefId;
		this.memberDefInstance = memberDefInstance;
		this.init();
	}
	

	@Override
	protected final List<MethodInfo> constructMethods() {
		return NIL_METHOD_INFO;
	}

	@Override
	protected final List<FieldInfo> constructFields() {
		return NIL_FIELD_INFO;
	}

	@Override
	protected final List<MemberDefInfo> constructMemberDefs() {
		List<MemberDefInfo> memberDefList =  List.single((MemberDefInfo)this);
		return memberDefList;
	}

	@Override
	public ObjectValue remove(String member) {
		return this;
	}

	@Override
	public ObjectValue rename(String currentName, String newName) {
		return this;
	}

	@Override
	protected String constructCanonicalRep() {
		return "MemberDef: " + this.memberDefId.toString();
	}

	@Override
	public AbstractObjectValue add(MemberValue mv) {
		return new ListValue(this, mv);
	}

	@Override
	public Object getMemberDefInstance() {
		return this.memberDefInstance;
	}
	
	@Override
	public String getMemberDefId() {
		return this.memberDefId;
	}


	@Override
	public int compareTo(MemberDefInfo o) {
		return  this.memberDefId.compareTo(o.getMemberDefId());
	}
}
