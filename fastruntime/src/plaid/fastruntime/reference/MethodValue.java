package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class MethodValue extends MemberValue implements MethodInfo {

	private final int numArgs;
	
	public static MethodValue createMethodWithStaticDefinition(String name, int numArgs, String classInternalName) {
		return new MethodValue(name, numArgs, classInternalName, true);
	}
	
	public static MethodValue createMethodWithDynamicDefinition(String name, int numArgs) {
		return new MethodValue(name, numArgs, null, false);
	}
	
	
	
	private MethodValue(String name, int numArgs, String classInternalName,
					   boolean staticallyDefined) {
		super(name, classInternalName, staticallyDefined);
		this.numArgs = numArgs;
		this.init();
	}

	@Override
	public List<MethodInfo> constructMethods() {
		//the fact that Java requires the MethodInfo cast is annoying
		return List.single((MethodInfo)this);
	}
	
	@Override
	public List<FieldInfo> constructFields() {
		return NIL_FIELD_INFO;
	}

	@Override
	public String getMethodDescriptor() {
		return NamingConventions.getMethodDescriptor(numArgs + 1);
	}

	@Override
	public int numArgs() {
		return this.numArgs;
	}
	
	@Override
	public ObjectValue rename(String currentName, String newName) {
		if(this.getName().equals(currentName)) {
			return new MethodValue(newName, this.numArgs, this.getStaticClassInternalName(),
					this.isStaticallyDefined());
		} else {
			return this;
		}
	}

	@Override
	protected String constructCanonicalRep() {
		String methodDefinition = this.isStaticallyDefined() ?
				this.getStaticClassInternalName() 
				: "";
		String result =  "method:" + this.getName() + this.numArgs + this.isStaticallyDefined() + methodDefinition;	
		return result;//.intern();
	}

}
