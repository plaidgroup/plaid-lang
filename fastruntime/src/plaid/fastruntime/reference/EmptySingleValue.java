package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;

public final class EmptySingleValue extends AtomicValue {
	
	public EmptySingleValue() {
		this.init();
	}
	
	@Override
	protected UnmodifiableList<MethodInfo> constructMethods() {
		return NIL_METHOD_INFO;
	}

	@Override
	protected UnmodifiableList<FieldInfo> constructFields() {
		return NIL_FIELD_INFO;
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
