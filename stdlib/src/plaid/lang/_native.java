package plaid.lang;

public final class _native {
	private _native() {}
	
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static boolean refEq(Object o1, Object o2) {
		return o1 == o2;
	}
}
