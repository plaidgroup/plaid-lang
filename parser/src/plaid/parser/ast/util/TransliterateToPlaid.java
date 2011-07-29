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
	private static String plaidCodeFromJavaClass(Class<?> clazz, String plaidPackage, boolean isConcrete) {
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
			if(!Modifier.isStatic(fs[i].getModifiers())) {
				sb.append("\tval immutable " + fs[i].getType().getSimpleName() + " " + fs[i].getName() + ";\n");
			}
		}
		
		
		//toString()
		sb.append("\n");
		sb.append("\tmethod immutable String toString()");
		if (isConcrete) {
			sb.append(" {\n\t\t");
			sb.append("\"" +clazz.getSimpleName() + "\\n\" + ");
			for(Field field:getAllFields(clazz)) {
				sb.append("\n\t\t");
				sb.append("\"" + field.getName() + ":\" + " + field.getName() + ".toString()");
				sb.append(" + \"\\n\" +");
			}
			sb.replace(sb.length()-1, sb.length(), ";"); //remove last plus
			sb.append("\n\t}\n");
		} else {
			sb.append(";\n");
		}
		

		//accept(v)
		sb.append("\n");
		sb.append("\tmethod void accept(unique ASTVisitor v)");
		if(isConcrete){
			sb.append("{\n");
			sb.append("\t\tv.visit"+clazz.getSimpleName() +"(this);\n");
			sb.append("\t}\n");
		} else {
			sb.append(";\n");
		}
		

		
		sb.append("\n}");
		return sb.toString();
	}
	
	private static void writePlaidFile(File outputDirectory, String code, 
			String stateName) throws IOException{
		File outputFile = new File(outputDirectory, stateName+".plaid");
		outputFile.delete();
		outputFile.createNewFile();
		FileWriter fw = new FileWriter(outputFile);
		fw.write(code);
		fw.close();
		outputFile.setReadOnly();
	}
	
	private static String matchCase(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t\t\tcase " + clazz.getName() + "{ \n");
		sb.append("\t\t\t\tnew " + clazz.getSimpleName() + " {\n");
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
				sb.append("makeTokenFromJavaToken(root." + getter(field.getName()) + "());");
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
	
	private static String visitMethod(Class<?> clazz, boolean includeBody) {
		String visitMethodCode =  "\tmethod void visit" + clazz.getSimpleName() 
			+ "(immutable " + clazz.getSimpleName() + " node)";
		if(includeBody){
			visitMethodCode += " {\n";
			for(Field field:getAllFields(clazz)) {
				visitMethodCode += "\t\tnode."+field.getName()+".accept(this);\n";
			}
			visitMethodCode += "\t}\n";
		}else {
			visitMethodCode +=";\n";
		}
		return visitMethodCode;
	}
	
	public static void main(String[] args) throws IOException {
		Class<? extends ASTNode>[] classes = getASTClasses();
		
		
		File outputDir = new File("../ast/pld/plaid/ast/parsed");
		outputDir.mkdir();
		File translateDir =  new File("../ast/pld/plaid/ast/translator");
		translateDir.mkdir();
		
		StringBuilder sbTranslator = new StringBuilder();
		sbTranslator.append("package plaid.ast.translator;\n\n");
		sbTranslator.append("import plaid.ast.parsed.*;\n");
		sbTranslator.append("import plaid.ast.util.makeListFromJavaCollection;\n");
		sbTranslator.append("import plaid.ast.util.makeTokenFromJavaToken;\n\n");
		sbTranslator.append("state ASTTranslator {\n");
		sbTranslator.append("\t method immutable ASTNode translateAST(" +
				"/* immutable " + ASTNode.class.getName() + "*/ root){\n");
		sbTranslator.append("\t\t match(root){\n");
		
		StringBuilder sbVisitor = new StringBuilder();
		sbVisitor.append("package plaid.ast.parsed;\n\n");
		sbVisitor.append("state ASTVisitor {\n");
		
		StringBuilder sbLeafVisitor = new StringBuilder();
		sbLeafVisitor.append("package plaid.ast.parsed;\n\n");
		sbLeafVisitor.append("state LeafVisitor {\n");
		
		for (Class<?> clazz : classes) {
			boolean isConcrete = !Modifier.isAbstract(clazz.getModifiers());
			String code = plaidCodeFromJavaClass(clazz, "plaid.ast.parsed", isConcrete);
			writePlaidFile(outputDir, code, clazz.getSimpleName());
			if(isConcrete) {
				sbTranslator.append(matchCase(clazz));
				sbVisitor.append(visitMethod(clazz,false));
				sbLeafVisitor.append(visitMethod(clazz,true));
			}
		}
		sbTranslator.append("\t\t}\n");
		sbTranslator.append("\t}\n");
		sbTranslator.append("}\n");
		
		writePlaidFile(translateDir, 
				sbTranslator.toString(), "ASTTranslator");
		
		sbVisitor.append("}\n");
		writePlaidFile(outputDir, sbVisitor.toString(), "ASTVisitor");
		sbLeafVisitor.append("}\n");
		writePlaidFile(outputDir, sbLeafVisitor.toString(), "LeafVisitor");
	}
}
