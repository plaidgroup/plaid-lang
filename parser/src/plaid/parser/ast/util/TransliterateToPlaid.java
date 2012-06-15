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
	
	private static final String PREFIX = "Parsed";
	
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
		sb.append("state " + PREFIX + clazz.getSimpleName());
		Class<?> superClass = clazz.getSuperclass();
		if(superClass != Object.class) {
			sb.append(" case of " + PREFIX + superClass.getSimpleName());
		}
		sb.append(" {\n\n");
		Field[] fs = clazz.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			if(!Modifier.isStatic(fs[i].getModifiers())) {
				String fieldType = fs[i].getType().getSimpleName();
				if (fieldType.equals("List")) fieldType = "LinkedList";
				else if (!fieldType.equals("String")) 
					fieldType = PREFIX + fieldType;
				sb.append("\tval immutable " + fieldType + " " + fs[i].getName() + ";\n");
			}
		}
		
		
		//toString()
		sb.append("\n");
		if ( clazz.getSimpleName().toString().equals("ASTNode")) {
			sb.append("\tmethod immutable String toString()");
		} else {
			sb.append("\toverride method immutable String toString()");
		}
		if (isConcrete) {
			sb.append(" {\n\t\t");
			sb.append("\"" +clazz.getSimpleName() + "\\n\" + ");
			for(Field field:getAllFields(clazz)) {
				sb.append("\n\t\t");
				sb.append("\"" + field.getName() + ":\" + this." + field.getName() + ".toString()");
				sb.append(" + \"\\n\" +");
			}
			sb.replace(sb.length()-1, sb.length(), ";"); //remove last plus
			sb.append("\n\t}\n");
		} else {
			sb.append(";\n");
		}
		
		//shortString() (name of ast, plus the name field if available
		sb.append("\n");
		if ( clazz.getSimpleName().toString().equals("ASTNode")) {
			sb.append("\tmethod immutable String shortString()");
		} else {
			sb.append("\toverride method immutable String shortString()");
		}
		if (isConcrete) {
			String clazzName = clazz.getSimpleName();
			sb.append(" {\n\t\t");
			sb.append("\"" + clazzName + "\" + ");
			if (clazzName.equals("QualifiedIdentifier")) {
				sb.append("\":\" + plaid.ast.util.makeStringFromQualifiedIdentifier(this) + ");	
			} else {
				for(Field field:getAllFields(clazz)) {
					if (field.getName().equals("name")) {
						if (clazzName.equals("Identifier"))
							sb.append("\":\" + this." + field.getName() + " + ");
						else
							sb.append("\":\" + this." + field.getName() + ".name + ");
					}
				}
			}
			sb.append(" this.token.toString();"); //remove last plus
			sb.append("\n\t}\n");
		} else {
			sb.append(";\n");
		}
		

		//accept(v)
		sb.append("\n");
		if ( clazz.getSimpleName().toString().equals("ASTNode")) {
			sb.append("\tmethod void accept(unique "+PREFIX+"ASTVisitor v)");
		} else {
			sb.append("\toverride method void accept(unique "+PREFIX+"ASTVisitor v)");
		}
		if(isConcrete){
			sb.append("{\n");
			sb.append("\t\tv.visit"+PREFIX+clazz.getSimpleName() +"(this);\n");
			sb.append("\t}\n");
		} else {
			sb.append(";\n");
		}
		
		//rewrite(v)
		sb.append("\n");
		if ( clazz.getSimpleName().toString().equals("ASTNode")) {
			sb.append("\tmethod immutable ASTNode rewrite(unique "+PREFIX+"ASTRewriteVisitor v)");
		} else {
			sb.append("\toverride method immutable "+PREFIX+clazz.getSimpleName()+" rewrite(unique "+PREFIX+"ASTVisitor v)");
		}
		if(isConcrete){
			sb.append("{\n");
			sb.append("\t\tv.rewrite"+PREFIX+clazz.getSimpleName() +"(this);\n");
			sb.append("\t}\n");
		} else {
			sb.append(";\n");
		}
		
		if ( clazz.getSimpleName().toString().equals("ASTNode")) {
			sb.append("\n\tmethod immutable String nodeName() {\"ASTNode\"}") ;
		} else {
			sb.append("\n\toverride method immutable string nodeName() { \""+ PREFIX+clazz.getSimpleName() +"\" }") ;
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
		sb.append("\t\t\t\tLOG(\"translate '"+clazz.getName()+"'\");\n");
		sb.append("\t\t\t\tnew " + PREFIX + clazz.getSimpleName() + " {\n");
		List<Field> allFields = getAllFields(clazz);
		for (Field field : allFields) {
			sb.append("\t\t\t\t\t" + field.getName() + " = ");
			if(field.getType().getSimpleName().startsWith("List")) {
				sb.append("makeListFromJavaCollection(root." +
						getter(field.getName()) + "()).map(" +
								"fn(a) => this.translateAST(a,fileName));");
			} else if(field.getType().isPrimitive()
					|| field.getType() == String.class) {
				sb.append("root." + getter(field.getName()) + "();");
			} else if (field.getType() == Token.class) {
				sb.append("makeTokenFromJavaToken(root." + getter(field.getName()) + "(),fileName);");
			}
			else {
				sb.append("this.translateAST(root." + 
						getter(field.getName()) + "(),fileName);");
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
		String visitMethodCode =  "\tmethod void visit" + PREFIX + clazz.getSimpleName() 
			+ "(immutable " + PREFIX + clazz.getSimpleName() + " node)";
		if(includeBody){
			visitMethodCode += " {\n";
			visitMethodCode += "\t\tLOG(\"visit "+PREFIX+clazz.getSimpleName()+"\");\n";
			for(Field field:getAllFields(clazz)) {
				if ( List.class.isAssignableFrom(field.getType())) {
					visitMethodCode += "\t\tnode."+field.getName()+".map(fn (item) => { item.accept(this); item });\n";
				} else if ( ASTNode.class.isAssignableFrom(field.getType()) ) {
					visitMethodCode += "\t\tnode."+field.getName()+".accept(this);\n";
				}
			}
			visitMethodCode += "\t}\n";
		}else {
			visitMethodCode +=";\n";
		}
		return visitMethodCode;
	}

	private static String rewriteMethod(Class<?> clazz, boolean includeBody) {
		String visitMethodCode =  "\tmethod immutable "+PREFIX+clazz.getSimpleName()+" rewrite" + PREFIX + clazz.getSimpleName()
			+ "(immutable " + PREFIX + clazz.getSimpleName() + " node)";
		if(includeBody){
			visitMethodCode += " {\n";
			visitMethodCode += "\t\tLOG(\"rewrite "+PREFIX+clazz.getSimpleName()+"\");\n";
			
			// create new object
			visitMethodCode += "\t\t// create new object\n";
			visitMethodCode += "\t\tnew " + PREFIX + clazz.getSimpleName() + "{\n";
			for ( Field field : getAllFields(clazz) ) {
				if (  List.class.isAssignableFrom(field.getType()) ) {
					visitMethodCode += "\t\t\tval " + field.getName() + "= node."+field.getName()+".map(fn (item) => { item.rewrite(this) });\n";
				} else if ( ASTNode.class.isAssignableFrom(field.getType()) ) {			
					visitMethodCode += "\t\t\tval " + field.getName() + " = node."+field.getName()+".rewrite(this);\n";
				} else {
					visitMethodCode += "\t\t\tval "+field.getName()+" = node."+field.getName()+";\n"; 				
				}
			}
			visitMethodCode += "\t\t}\n";
			
			visitMethodCode += "\t}\n";
		}else {
			visitMethodCode +=";\n";
		}
		return visitMethodCode;
	}

	private static String showMethod(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		String nodeName = "node" + clazz.getSimpleName(); 
		sb.append("\toverride method void visit" + PREFIX + clazz.getSimpleName() + "(immutable " + PREFIX+clazz.getSimpleName() + " node) {\n") ;
		sb.append("\t\tval " + nodeName + " = createNode(\"" + clazz.getSimpleName() + "\");\n");
		sb.append("\t\tthis.parent.add(" + nodeName + ");\n");
				
		for(Field field : getAllFields(clazz)) {
			sb.append("\t    \n");
			sb.append("\t    // add " + field.getName()+"\n");
			
			if ( List.class.isAssignableFrom(field.getType()) ) {
				String fieldNode = "node"+field.getName();
				sb.append("\t    val " + fieldNode + " = createNode(\"" + field.getName() + "\");\n" );
				sb.append("\t    "+nodeName+".add(" + fieldNode + ");\n");
				sb.append("\t    node." + field.getName() +".map( fn(item) => {  this.parent = " + fieldNode + "; item.accept(this); item } );\n");
			} else if ( ASTNode.class.isAssignableFrom(field.getType()) ) {
				String fieldNode = "node"+field.getName();
				sb.append("\t    val " + fieldNode + " = createNode(\"" + field.getName() + "\");\n" );
				sb.append("\t    this.parent = " + fieldNode + ";\n");
				sb.append("\t    node."+field.getName()+".accept(this);\n");
				sb.append("\t    " + nodeName + ".add(" + fieldNode + ");\n");
			} else if ( Token.class.isAssignableFrom(field.getType()) ) {
				sb.append("\t    "+ nodeName+".add(this.createTokenNode(node."+field.getName()+"));\n");
			}else {
				// handle base cases 
				String fieldNode = "node"+field.getName();
				String fieldNodeValue = "node"+field.getName()+"Value";
				sb.append("\t    val " + fieldNode + " = createNode(\"" + field.getName() + "\");\n" );
				sb.append("\t    val " + fieldNodeValue + " = createNode(node." + field.getName() + ".toString());\n" );
				sb.append("\t    "+fieldNode+".add("+fieldNodeValue+");\n");
				sb.append("\t    " + nodeName + ".add(" + fieldNode + ");\n");
			}
		}
	
		if ( clazz.getSimpleName().equals("CompilationUnit")) {
			// add call to create windows
			sb.append("\t // create window\n");
			sb.append("\tdraw(" +  nodeName + ")\n");
		}
		sb.append("\t}\n\n");
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Class<? extends ASTNode>[] classes = getASTClasses();
	
		
		
		File outputASTDir = new File("../ast/pld/plaid/ast/parsed");
		outputASTDir.mkdir();
		File translateDir =  new File("../ast/pld/plaid/ast/translator");
		translateDir.mkdir();
		
		StringBuilder sbTranslator = new StringBuilder();
		sbTranslator.append("package plaid.ast.translator;\n\n");
		sbTranslator.append("import plaid.ast.parsed.*;\n");
		sbTranslator.append("import plaid.ast.util.makeListFromJavaCollection;\n");
		sbTranslator.append("import plaid.ast.util.makeTokenFromJavaToken;\n\n");
		sbTranslator.append("state ASTTranslator {\n");
		sbTranslator.append("\tmethod void LOG(immutable String msg) {}\n");
		sbTranslator.append("\tmethod immutable ASTNode translateAST(" +
				"/* immutable " + ASTNode.class.getName() + "*/ root, fileName){\n");
		sbTranslator.append("\t\t match(root){\n");
		
		StringBuilder sbVisitor = new StringBuilder();
		sbVisitor.append("package plaid.ast.parsed;\n\n");
		sbVisitor.append("state "+PREFIX+"ASTVisitor {\n");
		
		StringBuilder sbLeafVisitor = new StringBuilder();
		sbLeafVisitor.append("package plaid.ast.parsed;\n\n");
		sbLeafVisitor.append("state "+PREFIX+"LeafVisitor {\n");
		sbLeafVisitor.append("\tmethod void LOG(immutable String msg);\n");
		
		StringBuilder sbRewriteVisitor = new StringBuilder();
		sbRewriteVisitor.append("package plaid.ast.parsed;\n\n");
		sbRewriteVisitor.append("state "+PREFIX+"ASTRewriteVisitor {\n");		
		
		StringBuilder sbRewriteLeafVisitor = new StringBuilder();
		sbRewriteLeafVisitor.append("package plaid.ast.parsed;\n\n");
		sbRewriteLeafVisitor.append("import plaid.collections.makeLinkedList;\n\n");
		sbRewriteLeafVisitor.append("state "+PREFIX+"RewriteLeafVisitor {\n");
		sbRewriteLeafVisitor.append("\tmethod void LOG(immutable String msg);\n");
		
		StringBuilder sbASTViewerVisitor = new StringBuilder(); 
		sbASTViewerVisitor.append("package plaid.ast.parsed;\n\n");
		sbASTViewerVisitor.append("import java.lang.Thread;\n");
		sbASTViewerVisitor.append("import java.awt.BorderLayout;\n");
		sbASTViewerVisitor.append("import java.awt.Dimension;\n");
		sbASTViewerVisitor.append("import javax.swing.JFrame;\n");
		sbASTViewerVisitor.append("import javax.swing.JTree;\n");
		sbASTViewerVisitor.append("import javax.swing.JScrollPane;\n");
		sbASTViewerVisitor.append("import javax.swing.tree.DefaultMutableTreeNode;\n");
		sbASTViewerVisitor.append("import javax.swing.WindowConstants;\n\n");
		sbASTViewerVisitor.append("state "+PREFIX+"ASTViewerVisitor case of "+PREFIX+"LeafVisitor {\n");
		sbASTViewerVisitor.append("\tvar /* (private) DefaultMutableTreeNode */ parent = DefaultMutableTreeNode.new(\"AST\");\n");
		sbASTViewerVisitor.append("\tval /* String */ title = \"ASTViewer\";\n\n");
		sbASTViewerVisitor.append("\tmethod createNode(name) {\n");
		sbASTViewerVisitor.append("\t    DefaultMutableTreeNode.new(name);\n");
		sbASTViewerVisitor.append("\t}\n\n");
		sbASTViewerVisitor.append("\tmethod draw(root) {\n");
		sbASTViewerVisitor.append("\t    var jtree  = JTree.new(root);\n");
		sbASTViewerVisitor.append("\t    jtree.setRootVisible(true);\n");
		sbASTViewerVisitor.append("\t    var scroll = JScrollPane.new(jtree);\n");
		sbASTViewerVisitor.append("\t    var jframe = JFrame.new(this.title);\n");
		sbASTViewerVisitor.append("\t    jframe.add(scroll);\n"); 
		sbASTViewerVisitor.append("\t    jframe.setMinimumSize(Dimension.new(500,500));\n");
		sbASTViewerVisitor.append("\t    jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);\n");
		sbASTViewerVisitor.append("\t    jframe.setVisible(true);\n");
		sbASTViewerVisitor.append("\t}\n\n");
		sbASTViewerVisitor.append("\tmethod createTokenNode(token) { \n");
		sbASTViewerVisitor.append("\t   var tokenNode = createNode(\"Token\");\n");
		sbASTViewerVisitor.append("\t   tokenNode.add(createNode(\"beginLine(\"+token.beginLine+\")\"));\n");
		sbASTViewerVisitor.append("\t   tokenNode.add(createNode(\"beginColumn(\"+token.beginColumn+\")\"));\n");
		sbASTViewerVisitor.append("\t   tokenNode.add(createNode(\"endLine(\"+token.endLine+\")\"));\n");
		sbASTViewerVisitor.append("\t   tokenNode.add(createNode(\"endColumn(\"+token.endColumn+\")\"));\n");
		sbASTViewerVisitor.append("\t   tokenNode.add(createNode(\"image(\\\"\"+token.image+\"\\\")\"));\n");
		sbASTViewerVisitor.append("\t   tokenNode\n");
		sbASTViewerVisitor.append("\t}\n\n");
		
		for (Class<?> clazz : classes) {
			boolean isConcrete = !Modifier.isAbstract(clazz.getModifiers());
			String code = plaidCodeFromJavaClass(clazz, "plaid.ast.parsed", isConcrete);
			writePlaidFile(outputASTDir, code, PREFIX + clazz.getSimpleName());
			if(isConcrete) {
				sbTranslator.append(matchCase(clazz));
				sbVisitor.append(visitMethod(clazz,false));
				sbLeafVisitor.append(visitMethod(clazz,true));
				sbRewriteVisitor.append(rewriteMethod(clazz, false));
				sbRewriteLeafVisitor.append(rewriteMethod(clazz, true));
				sbASTViewerVisitor.append(showMethod(clazz));
			}
		}
		sbTranslator.append("\t\t}\n");
		sbTranslator.append("\t}\n");
		sbTranslator.append("}\n");
		
		writePlaidFile(translateDir, 
				sbTranslator.toString(), "ASTTranslator");
		
		sbVisitor.append("}\n");
		writePlaidFile(outputASTDir, sbVisitor.toString(), PREFIX+"ASTVisitor");
		sbLeafVisitor.append("}\n");
		writePlaidFile(outputASTDir, sbLeafVisitor.toString(), PREFIX+"LeafVisitor");
		sbRewriteVisitor.append("}\n");
		writePlaidFile(outputASTDir, sbRewriteVisitor.toString(), PREFIX+"ASTRewriteVisitor");
		sbRewriteLeafVisitor.append("}\n");
		writePlaidFile(outputASTDir, sbRewriteLeafVisitor.toString(), PREFIX+"RewriteLeafVisitor");
		sbASTViewerVisitor.append("}\n");
		writePlaidFile(outputASTDir, sbASTViewerVisitor.toString(), PREFIX+"ASTViewerVisitor");
		
		System.out.println("Done.");
	}
}
