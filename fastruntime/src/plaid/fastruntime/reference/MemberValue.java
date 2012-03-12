package plaid.fastruntime.reference;

import plaid.fastruntime.MemberInfo;
import plaid.fastruntime.ObjectValue;
import fj.Ord;
import fj.data.Set;

public abstract class MemberValue extends SingleValue implements MemberInfo{
	private final String name;
	private String classInternalName;
	
	public MemberValue(String name, String classInternalName) {
		this.name = name;
		this.classInternalName = classInternalName;
	}
	
	@Override
	public Set<String> getTags() {
		return Set.empty(Ord.stringOrd);
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean uniqueTags() {
		return true;
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
	public String getStaticClassInternalName() {
		return this.classInternalName;
	}
	
	@Override
	public ObjectValue remove(String member) {
		if(this.getName().equals(member)) {
			return new EmptySingleValue();
		} else {
			return this;
		}
	}
	
	@Override
	public AbstractObjectValue add(MemberValue mv) {
		return new ListValue(this, mv);
	}

}
