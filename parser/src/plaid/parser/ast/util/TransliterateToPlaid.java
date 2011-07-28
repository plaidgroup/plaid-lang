package plaid.parser.ast.util;

import static plaid.parser.ast.util.ClassDiscovery.DiscoverClasses;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import plaid.parser.ast.ASTNode;

public class TransliterateToPlaid<T> {
	private static Class<? extends ASTNode>[] getASTClasses() {
		return DiscoverClasses(ASTNode.class,null,ASTNode.class);
	}
	
	/**
	 * Only converts fields and sets all of the fields to immutable. Only supports fields whose types are 
	 * in the same package as the class being converted.
	 * @param clazz The Java class to transliterate
	 * @param plaidPackage The string package name of the Plaid file.
	 * @return Plaid code as a String.
	 */
	private static String plaidCodeFromJavaClass(Class<?> clazz, String plaidPackage) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + plaidPackage+ ";\n\n");
		sb.append("state " + clazz.getSimpleName());
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != Object.class) {
			sb.append(" case of " + superClass.getSimpleName());
		}
		sb.append(" {\n\n");
		Field[] fs = clazz.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			sb.append("\tval immutable " + fs[i].getType().getSimpleName() + " " + fs[i].getName() + ";\n");
		}
		sb.append("\n}");
		return sb.toString();
	}
	
	private static void writePlaidFile(File outputDirectory, String code, String stateName) throws IOException{
		File outputFile = new File(outputDirectory, stateName+".plaid");
		outputFile.createNewFile();
		FileWriter fw = new FileWriter(outputFile);
		fw.write(code);
		fw.close();
	}
	
	private static String matchCase(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("case " + clazz.getName() + "{ \n");
		sb.append("\tnew " + clazz.getName() + " {\n");
		List<String> allFields = getAllFields(clazz);
		for (String field : allFields) {
			sb.append("\t\t" + field + " = this.translateAST(root.get" + 
					field.substring(0,1).toUpperCase() + 
					field.substring(1,field.length()) + "());\n");
		}
		sb.append("\t}\n");
		sb.append("}\n");
		return sb.toString();
	}
	
	private static List<String> getAllFields(Class<?> clazz) {
		List<String> fields = new ArrayList<String>();
		Class<?> supr = clazz;
		while (supr != Object.class) {
			Field[] fs = supr.getDeclaredFields();
			for (Field field : fs) {
				if (!Modifier.isStatic(field.getModifiers())) {
					fields.add(field.getName());
				}
			}
			supr = supr.getSuperclass();
		}
		return fields;
	}
	
//	private static <T> T[] concatAll(T[][] arrays) {
//		int totalLength = 0;
//		for (T[] array : arrays) {
//			totalLength += array.length;
//		}
//		T[] result = Arrays.copyOf(arrays[0], totalLength);
//		int offset = arrays[0].length;
//		for (int i=1; i<arrays.length; i++) {
//			T[] array = arrays[i];
//			System.arraycopy(array, 0, result, offset, array.length);
//			offset += array.length;
//		}
//		return result;
//	}
	
	public static void main(String[] args) throws IOException {
		Class<? extends ASTNode>[] classes = getASTClasses();
		File outputDir = new File("../ast/pld/plaid/ast/parsed");
		outputDir.mkdir();
		for (Class<?> clazz : classes) {
			String code = plaidCodeFromJavaClass(clazz, "plaid.ast.parsed");
			writePlaidFile(outputDir, code, clazz.getSimpleName());
			if(!Modifier.isAbstract(clazz.getModifiers())) {
				System.out.println(matchCase(clazz));
			}
		}
		
	}
}
