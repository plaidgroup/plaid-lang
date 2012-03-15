package plaid.fastruntime.reference;

import java.util.UUID;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MemberDefInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.Ord;
import fj.data.List;
import fj.data.Set;

public class MemberDefValue extends SingleValue implements MemberDefInfo {
	
	private final UUID memberDefId;
	private final Object memberDefInstance;
	private final String canonicalRep;
	
	public  MemberDefValue(UUID memberDefId, Object memberDefInstance){
		this.memberDefId = memberDefId;
		this.memberDefInstance = memberDefInstance;
		this.canonicalRep = this.constructCanonicalRep();
	}
	

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
	public String getCanonicalRep() {
		return this.canonicalRep;
	}

	@Override
	protected String constructCanonicalRep() {
		return "MemberDef: " + this.memberDefId.toString();
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
	public AbstractObjectValue add(MemberValue mv) {
		return new ListValue(this, mv);
	}

	@Override
	public Object getMemberDefInstance() {
		return this.memberDefInstance;
	}
	
	@Override
	public UUID getMemberDefId() {
		return this.memberDefId;
	}	
}
