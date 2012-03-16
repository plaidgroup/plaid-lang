package plaid.fastruntime.reference;

import plaid.fastruntime.MemberInfo;
import plaid.fastruntime.ObjectValue;
import fj.Ord;
import fj.data.Set;

public abstract class MemberValue extends SingleValue implements MemberInfo{
	private final String name;
	private String classInternalName;
	private final boolean staticallyDefined;
	private final String memberDefinitionName;
	
	protected MemberValue(String name, String classInternalName, 
			boolean staticallyDefined, String memberDefinitionName) {
		this.name = name;
		this.classInternalName = classInternalName;
		this.staticallyDefined = staticallyDefined;
		this.memberDefinitionName = memberDefinitionName;
	}
	
	@Override
	public final Set<String> getTags() {
		return Set.empty(Ord.stringOrd);
	}

	public final String getName() {
		return name;
	}
	
	@Override
	public final boolean uniqueTags() {
		return true;
	}
	
	@Override
	public final Set<String> getOuterTags() {
		return Set.empty(Ord.stringOrd);
	}
	
	@Override
	public final Set<String> getInnerTags() {
		return Set.empty(Ord.stringOrd);
	}
	
	@Override
	public final String getStaticClassInternalName() {
		return this.classInternalName;
	}
	
	@Override
	public final ObjectValue remove(String member) {
		if(this.getName().equals(member)) {
			return new EmptySingleValue();
		} else {
			return this;
		}
	}
	
	@Override
	public final AbstractObjectValue add(MemberValue mv) {
		return new ListValue(this, mv);
	}

	@Override
	public final boolean isStaticallyDefined() {
		return this.staticallyDefined;
	}

	@Override
	public final String getMemberDefinitionName() {
		return this.memberDefinitionName;
	}
	
}
