package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MemberDefInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.Ord;
import fj.data.List;
import fj.data.Set;

public final class EmptySingleValue extends SingleValue {
	
	@Override
	public List<MethodInfo> getMethods() {
		return List.nil();
	}

	@Override
	public List<FieldInfo> getFields() {
		return List.nil();
	}
	
	@Override
	public List<MemberDefInfo> getMemberDefs() {
		return List.nil();
	}

	@Override
	public ObjectValue remove(String member) {
		return this;
	}

	@Override
	public Set<String> getTags() {
		return Set.empty(Ord.stringOrd);
	}

	@Override
	public Set<String> getOuterTags() {
		return Set.empty(Ord.stringOrd);
	}

	@Override
	public Set<String> getInnerTags() {
		return Set.empty(Ord.stringOrd);
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
	public String getCanonicalRep() {
		return "";
	}

	@Override
	protected String constructCanonicalRep() {
		return "";
	}

}
