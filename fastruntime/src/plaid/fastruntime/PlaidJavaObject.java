package plaid.fastruntime;

import plaid.fastruntime.errors.PlaidIllegalOperationException;

public interface PlaidJavaObject extends PlaidObject {
	public enum JavaPrimitive {
		BOOLEAN("boolean","BOOLEAN"),
		INT("int","INT"),
		LONG("long","LONG"),
		SHORT("short","SHORT"),
		BYTE("byte","BYTE"),
		CHAR("char","CHAR"),
		DOUBLE("double","DOUBLE"),
		FLOAT("float","FLOAT");
		
		public String name;
		public String field;
		JavaPrimitive(String name, String field) {
			this.name = name;
			this.field = field;
		}
		
		public static JavaPrimitive fromClass(Class<?> c) {
			if (c.equals(boolean.class)) return BOOLEAN;
			else if (c.equals(int.class)) return INT;
			else if (c.equals(long.class)) return LONG;
			else if (c.equals(short.class)) return SHORT;
			else if (c.equals(byte.class)) return BYTE;
			else if (c.equals(char.class)) return CHAR;
			else if (c.equals(double.class)) return DOUBLE;
			else if (c.equals(float.class)) return FLOAT;
			else throw new PlaidIllegalOperationException("Java class " + c.getSimpleName() + " is not a primitive");
			 
		}
		
	}
	
	public Object getJavaObject();
	
	public boolean canBePrimitive(JavaPrimitive p);
	
	//returns boxed version which java can then convert to true primitive
	public Object asPrimitive(JavaPrimitive p);
}
