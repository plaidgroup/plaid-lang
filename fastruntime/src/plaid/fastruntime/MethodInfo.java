package plaid.fastruntime;

public interface MethodInfo extends MemberInfo {
	String getMethodDescriptor();
	int numArgs();
}
