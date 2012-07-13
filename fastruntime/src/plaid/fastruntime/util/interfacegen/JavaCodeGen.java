package plaid.fastruntime.util.interfacegen;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidJavaObject.JavaPrimitive;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.util.interfacegen.CodeObjects.ClassObject;
import plaid.fastruntime.util.interfacegen.CodeObjects.CodeGenerator;
import plaid.fastruntime.util.interfacegen.CodeObjects.CodeObject;
import plaid.fastruntime.util.interfacegen.CodeObjects.FieldObject;
import plaid.fastruntime.util.interfacegen.CodeObjects.JavaMethod;
import plaid.fastruntime.util.interfacegen.CodeObjects.Newline;
import plaid.fastruntime.util.interfacegen.CodeObjects.PlaidClassObject;
import plaid.fastruntime.util.interfacegen.CodeObjects.PlaidOp;
import plaid.fastruntime.util.interfacegen.CodeObjects.SimplePlaidMethod;
import plaid.fastruntime.util.interfacegen.CodeObjects.SimplePlaidMethod.Return;
import plaid.fastruntime.util.interfacegen.CodeObjects.StaticBlock;
import plaid.lang.I_nt32;

public class JavaCodeGen {
	public static void main(String[] args) throws Exception {
		ArrayList<String> parsedArgs = new ArrayList<String>(Arrays.asList(args));

		boolean isPrimitive = false;
		
		String fullName = "";
		if (parsedArgs.contains("-n"))
			fullName = parsedArgs.get(parsedArgs.indexOf("-n")+1);
		
		Class javaClass = null;
		if (parsedArgs.contains("-s"))
			javaClass = Class.forName(parsedArgs.get(parsedArgs.indexOf("-s")+1));
		
		String[] generatedOperators = new String[0];
		if (parsedArgs.contains("-op"))
			generatedOperators = parsedArgs.get(parsedArgs.indexOf("-op")+1).split(",");
		
		String generatedDirectory = "./";
		if (parsedArgs.contains("-gd"))
			generatedDirectory =  parsedArgs.get(parsedArgs.indexOf("-gd")+1);
		
		String outputDirectory = "./";
		if (parsedArgs.contains("-od"))
			outputDirectory =  parsedArgs.get(parsedArgs.indexOf("-od")+1);
		
		
		String name = fullName.substring(fullName.lastIndexOf(".")+1);
		String namespace = fullName.substring(0, fullName.lastIndexOf("."));
		
		
		Class primitiveClass = null;
		try {
			primitiveClass = (Class) javaClass.getField("TYPE").get(null);
			isPrimitive = true;
		} catch (NoSuchFieldException e) {
			
		}
		String javaName = javaClass.getSimpleName();
		
		String plaidName = NamingConventions.getGeneratedIdentifier(name);
		
		String plaidJavaObject = "SimpleJavaPlaidObject";
		
		CodeObject runtimeClass = null;
		if (isPrimitive) {
			JavaPrimitive p;
			try {
				p = JavaPrimitive.fromClass(primitiveClass);
			} catch (PlaidIllegalOperationException e) {
				throw new RuntimeException("Given type is not a primitive");
			}

			String primitiveObjectName = javaClass.getSimpleName();
			
			plaidJavaObject = javaName+"PlaidJavaObject";
			plaidJavaObject = plaidJavaObject.substring(0,1).toUpperCase() + plaidJavaObject.substring(1);
			runtimeClass = new ClassObject(plaidJavaObject, null, new String[] {"PlaidJavaObject"}, Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL,
				new FieldObject(primitiveClass.getSimpleName(), "value", Modifier.PRIVATE | Modifier.FINAL),
				new FieldObject(primitiveObjectName, "javaObj", Modifier.PRIVATE | Modifier.FINAL),
				new JavaMethod(plaidJavaObject, new String[] {primitiveClass.getSimpleName()}, new String[] {"value"}, "", 
						"this.value = value;\nthis.javaObj = "+primitiveObjectName+".valueOf(value);", Modifier.PUBLIC),
				new JavaMethod("getDispatch", new String[] {}, new String[] {}, "PlaidDispatch", 
						"return "+plaidName+".theState$plaid.getDispatch();", Modifier.PUBLIC, new String[] {"@Override"}),
				new JavaMethod("getStorage", new String[] {}, new String[] {}, "PlaidObject[]", 
						"throw new PlaidIllegalOperationException(\"No storage object for a Java Object.\");", Modifier.PUBLIC, new String[] {"@Override"}),
				new JavaMethod("changeState", new String[] {}, new String[] {}, "PlaidState", "throw new PlaidIllegalOperationException(\"Cannot change the state of a Java Object.\");", Modifier.PUBLIC, new String[] {"@Override"}),
				new JavaMethod("getJavaObject", new String[] {}, new String[] {}, "Object", "if (this.javaObj == null) {\n\tthis.javaObj =  Integer.valueOf(value);\n}\nreturn this.javaObj;", Modifier.PUBLIC, new String[] {"@Override"}),
				new JavaMethod("canBePrimitive", new String[] {}, new String[] {}, "boolean", "switch (p) {\ncase "+p.field+": return true;\ndefault: return false;\n}", Modifier.PUBLIC, new String[] {"@Override"}),
				new JavaMethod("asPrimitive", new String[] {}, new String[] {}, "Object", "switch (p) {\ncase "+p.field+": return this.getJavaObject();\ndefault: throw new PlaidIllegalOperationException(\""+p.name+"s cannot be used as \" + p.name + \" primitives.\");\n}", Modifier.PUBLIC, new String[] {"@Override"})
			);
		
		}
		
		Class toGen = javaClass;
		
		JavaPlaidFile jpf = new JavaPlaidFile(
				new PlaidClassObject(plaidName, namespace,
					new JavaMethod(plaidName, new String[] {"ObjectValue"}, new String[] {"metadata"}, "", "super(metadata);", Modifier.PUBLIC),
					new JavaMethod("plaid"+name, new String[] {(isPrimitive)?primitiveClass.getSimpleName():javaName}, new String[] {"val"}, "PlaidObject", "return new "+plaidJavaObject+"(val)", Modifier.PUBLIC | Modifier.STATIC),
					new FieldObject("PlaidState", "theState$plaid", Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL),
					new StaticBlock("theState$plaid = new AbstractPlaidState(new "+plaidName+"(new DimensionValue(\""+fullName.replace(".", "/")+"\", null, null))) { \n\t@Override\n\tpublic PlaidObject instantiate() {\n\t\tthrow new PlaidIllegalOperationException(\"Cannot instantiate "+name+" state\");\n\t}\n};"),
					(isPrimitive)?runtimeClass:new Newline()
				), generatedDirectory
		);
		

		Method[] toGenMethods = toGen.getMethods();
		for (int i = 0; i < toGenMethods.length; i++)
			((ClassObject)jpf.getElement()).addCodeObject(new SimplePlaidMethod(toGenMethods[i], javaClass, plaidJavaObject));
		
		for (int i = 0; i < generatedOperators.length; i++)
			((ClassObject)jpf.getElement()).addCodeObject(new PlaidOp(generatedOperators[i], javaClass.getSimpleName(), plaidJavaObject));
		
		
		jpf.addDependency("plaid.fastruntime.ObjectValue");
		jpf.addDependency("plaid.fastruntime.PlaidJavaObject");
		jpf.addDependency("plaid.fastruntime.PlaidObject");
		
		
		File outputFile = new File(new File(outputDirectory), plaidName+".java");
		try {
			FileOutputStream output = new FileOutputStream(outputFile);
			output.write(jpf.getCode().getBytes("UTF-8"));
			output.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
