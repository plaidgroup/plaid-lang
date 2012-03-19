package plaid.fastruntime.reference;

import plaid.fastruntime.MemberInfo;
import plaid.fastruntime.ObjectValue;

public abstract class MemberValue extends AtomicValue implements MemberInfo{
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
		this.init();
	}
	
	

	public final String getName() {
		return name;
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
