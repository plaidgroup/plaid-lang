package plaid.fastruntime;

public interface MethodInfo extends MemberInfo {
	String getName();
	String getStaticClassInternalName();
	String getMethodDescriptor();
	int numArgs();
}
