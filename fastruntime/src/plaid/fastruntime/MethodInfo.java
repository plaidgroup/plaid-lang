package plaid.fastruntime;

public interface MethodInfo {
	String getName();
	String getStaticClassInternalName();
	String getMethodDescriptor();
	int numArgs();
}
