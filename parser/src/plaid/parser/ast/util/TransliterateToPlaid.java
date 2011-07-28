package plaid.parser.ast.util;

import static plaid.parser.ast.util.ClassDiscovery.DiscoverClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import plaid.parser.Token;
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
		sb.append("\t\t\tcase " + clazz.getName() + "{ \n");
		sb.append("\t\t\t\tnew " + clazz.getName() + " {\n");
		List<Field> allFields = getAllFields(clazz);
		for (Field field : allFields) {
			sb.append("\t\t\t\t\t" + field.getName() + " = ");
			if(field.getType().getSimpleName().startsWith("List")) {
				sb.append("makeListFromJavaCollection(root." +
						getter(field.getName()) + "()).map(" +
								"fn(a) => this.translateAST(a));");
			} else if(field.getType().isPrimitive()
					|| field.getType() == String.class) {
				sb.append("root." + getter(field.getName()) + "();");
			} else if (field.getType() == Token.class) {
				sb.append("createToken(root." + getter(field.getName()) + "());");
			}
			else {
				sb.append("this.translateAST(root." + 
						getter(field.getName()) + "());");
			}
			sb.append("\n");
		}
		sb.append("\t\t\t\t}\n");
		sb.append("\t\t\t}\n");
		return sb.toString();
	}
	
	private static String getter(String field) {
		return "get" + field.substring(0,1).toUpperCase() + 
		field.substring(1,field.length());
	}
	
	private static List<Field> getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		Class<?> supr = clazz;
		while (supr != Object.class) {
			Field[] fs = supr.getDeclaredFields();
			for (Field field : fs) {
				if (!Modifier.isStatic(field.getModifiers())) {
					fields.add(field);
				}
			}
			supr = supr.getSuperclass();
		}
		return fields;
	}
	
	public static void main(String[] args) throws IOException {
		Class<? extends ASTNode>[] classes = getASTClasses();
		File outputDir = new File("../ast/pld/plaid/ast/parsed");
		outputDir.mkdir();
		StringBuilder sbTranslator = new StringBuilder();
		sbTranslator.append("package plaid.ast.translator;\n\n");
		sbTranslator.append("import plaid.ast.parsed.*;\n");
		sbTranslator.append("import plaid.ast.util.makeListFromJavaCollection;\n");
		sbTranslator.append("import plaid.ast.util.makeTokenFromJavaToken;\n\n");
		sbTranslator.append("state ASTTranslator {\n");
		sbTranslator.append("\t method immutable CompilationUnit translateAST(immutable ASTNode node){\n");
		sbTranslator.append("\t\t match(node){\n");
		for (Class<?> clazz : classes) {
			String code = plaidCodeFromJavaClass(clazz, "plaid.ast.parsed");
			writePlaidFile(outputDir, code, clazz.getSimpleName());
			if(!Modifier.isAbstract(clazz.getModifiers())) {
				sbTranslator.append(matchCase(clazz));
			}
		}
		sbTranslator.append("\t\t}\n");
		sbTranslator.append("\t}\n");
		sbTranslator.append("}\n");
		System.out.println(sbTranslator.toString());
		
	}
}
