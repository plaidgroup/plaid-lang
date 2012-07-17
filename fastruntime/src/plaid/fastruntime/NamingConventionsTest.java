package plaid.fastruntime;

public class NamingConventionsTest {
	public static void main(String[] args) {
		System.out.println(NamingConventions.getGeneratedIdentifier("Fo_o+"));
		System.out.println(NamingConventions.getGeneratedFQN("PLAID.LANG.FOO"));
		System.out.println(NamingConventions.getInternalFQN("PLAID/LANG/FOO"));
	}
}
