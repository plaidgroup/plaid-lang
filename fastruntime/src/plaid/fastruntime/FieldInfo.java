package plaid.fastruntime;

public interface FieldInfo extends MemberInfo, Comparable<FieldInfo> {
	boolean isSettable();
}
