package plaid.fastruntime;

/*
 * Because Plaid allows the capture of local variables in inner scopes,
 * members may need to be constructed with information known only at 
 * runtime.  The needsMemberDefinition() method will return 'true' if this
 * is the case, which means that the getMemberDefinitionName() method will
 * provide the name needed to get the memberDefinition instance needed
 * to use this member.  Otherwise, the member will be entirely defined
 * in the static class given by the method getStaticClassInternalName().
 */
public interface MemberInfo extends NameInfo{
	String getName();
	boolean isStaticallyDefined();
	String getStaticClassInternalName();
}
