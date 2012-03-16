package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MemberDefInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class EmptySingleValue extends AtomicValue {
	
	public EmptySingleValue() {
		this.init();
	}
	
	@Override
	protected List<MethodInfo> constructMethods() {
		return List.nil();
	}

	@Override
	protected List<FieldInfo> constructFields() {
		return List.nil();
	}
	
	@Override
	protected List<MemberDefInfo> constructMemberDefs() {
		return List.nil();
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
	public AbstractObjectValue add(MemberValue mv) {
		return mv;
	}

	@Override
	protected String constructCanonicalRep() {
		return "";
	}

}
