package plaid.fastruntime;

public interface FieldInfo extends MemberInfo, Comparable<FieldInfo> {
	String getName();
	String getStaticClassInternalName();
	boolean isSettable();
}
