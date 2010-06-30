/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.compilerjava.util;

import java.io.IOException;
import java.util.*;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.AST.ID;
import plaid.compilerjava.coreparser.Token;
import plaid.runtime.PlaidConstants;

/**
 * @author kbn
 *
 */
public class CodeGen {
	private CompilerConfiguration cc = null; 
	
	
	//store the output
	private final StringBuilder output = new StringBuilder();
	
	/*----------------------------
	 * various constant strings used below
	 *----------------------------
	 */
	public static final Map<String,String> opNames = new HashMap<String,String>(15);
	static {
		opNames.put("=","eq");
		opNames.put("<","lt");
		opNames.put(">","gt");
		opNames.put("!","bang");
		opNames.put("~","tilde");
		opNames.put("?","quest");
		opNames.put(":","colon");
		opNames.put("&","amp");
		opNames.put("|","pipe");
		opNames.put("+","plus");
		opNames.put("-","sub");
		opNames.put("*","mult");
		opNames.put("/","div");
		opNames.put("^","carat");
		opNames.put("%","mod");
	}
	
	public static final String convertOpNames(String oldName) {
		String newName = "";
		if (CodeGen.opNames.containsKey(oldName.substring(0, 1))) {
			for(char op : oldName.toCharArray()) {
				newName += CodeGen.opNames.get(Character.toString(op));
			}
			newName += PlaidConstants.ID_SUFFIX;
			return newName;
		} else {
			return oldName;
		}
	}
	
	public static final String runtimePackage = "plaid.runtime";
	public static final String classLoader = runtimePackage + ".PlaidRuntime.getRuntime().getClassLoader()";
	public static final String rt = runtimePackage + ".PlaidRuntime.getRuntime()";
	public static final String globalScope = "global$c0pe";
	public static final String localScope = "local$c0pe";
	public static final String utilClass = runtimePackage + ".Util";
	public static final String utilsPackage = runtimePackage + ".utils";
	public static final String newPlaidObject = utilClass + ".newObject()";
	public static final String newPlaidState = utilClass + ".newState()";
	public static final String thisVar = "this" + PlaidConstants.ID_SUFFIX;
	//public static final String theScope = "the$c0pe";
	public static final String copyright = 
		"/**" + System.getProperty("line.separator") + 
		" * Copyright (c) 2010 The Plaid Group (see AUTHORS file)" + System.getProperty("line.separator") +
		" *" + System.getProperty("line.separator") +
		" * This file is part of Plaid Programming Language." + System.getProperty("line.separator") +
		" *" + System.getProperty("line.separator") +
		" * Plaid Programming Language is free software: you can redistribute it and/or modify" + System.getProperty("line.separator") +
		" * it under the terms of the GNU General Public License as published by" + System.getProperty("line.separator") +
		" * the Free Software Foundation, either version 3 of the License, or" + System.getProperty("line.separator") +
		" * (at your option) any later version." + System.getProperty("line.separator") +
		" *" + System.getProperty("line.separator") +
		" *  Plaid Programming Language is distributed in the hope that it will be useful," + System.getProperty("line.separator") +
		" *  but WITHOUT ANY WARRANTY; without even the implied warranty of" + System.getProperty("line.separator") +
		" *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the" + System.getProperty("line.separator") +
		" *  GNU General Public License for more details." + System.getProperty("line.separator") +
		" *" + System.getProperty("line.separator") +
		" *  You should have received a copy of the GNU General Public License " + System.getProperty("line.separator") +
		" *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>." + System.getProperty("line.separator") +
		" */"  + System.getProperty("line.separator") + System.getProperty("line.separator");

	//types for the runtime
	public static final String plaidScopeType = runtimePackage + ".PlaidScope";
	public static final String plaidObjectType = runtimePackage + ".PlaidObject";
	public static final String plaidStateType = runtimePackage + ".PlaidState";
	public static final String plaidMethodType = runtimePackage + ".PlaidMethod";
	public static final String plaidExceptionType = runtimePackage + ".PlaidException";
	public static final String plaidTagType = runtimePackage + ".PlaidTag";
	public static final String delegateType = utilsPackage + ".Delegate";
	public static final String lambdaType = utilsPackage + ".Lambda";

	
	public CodeGen(CompilerConfiguration cc) {
		this.cc = cc;
		if ( cc.addCopyrightHeader() ) {
			output.append(copyright);
		}
	}

	
	/*----------------------------
	 * boolean tests (return strings instead of just appending)
	 *----------------------------
	 */
	public static final String matchesState(String target, String match) {
		return target + ".getStates().contains(" + match + ")";
	}
	
	public static final String containsMember(String target, String member) {
		return target + ".getMembers().containsKey(\"" + member + "\")";
	}
	
	public static final String stateHasTag(String target) {
		return target + ".hasTag()";
	}
	
	public static final String objectMatchesTag(String target, String tag) {
		return target + ".matchesTag(" + tag + ")";
	}
	
	/*----------------------------
	 * generic append function
	 *----------------------------
	 */
	public final void append(String s) {
		output.append(s);
	}
	
	/*----------------------------
	 * generic and specialized assignment functions.  Form "target = ...;"
	 *----------------------------
	 */
	public final void assign(String target) {
		output.append(target + " = ");
	}
	
	public final void assignToCall(String target, String function, String arg) {
		assign(target);
		call(function,arg);
		append(";");
		updateVar(target);
	}
	
	public final void assignToID(String target, String val) {
		assign(target);
		append(val);
		append(";");
		updateVar(target);
	}
	
	public final void assignToChangedState(String target, String object, String newState) {
		
		assign(target);
		changeState(object,newState);
		updateVar(target);
	}

	public final void assignToLookup(String target, String name, String scope) {
		assign(target);
		lookup(name, scope);
		updateVar(target);
	}
	
	public final void assignToNewJavaObject(String target, String type) {
		assign(target);
		newJavaObject(type);
		output.append(";");
		updateVar(target);
	}
	
	public final void assignToNewJavaObject(String target, String type, String arg) {
		assign(target);
		newJavaObject(type,arg);
		output.append(";");
		updateVar(target);
	}
	
	//need to implement multiple argument Java constructors if needed 
	
	//Complicated generation of code to add a field to an uninitialized state
	//after body, follow with closeAnonymousDeclaration() function
	public final void assignToProtoField(String target, String name) {
		assign(target);
		output.append(classLoader + ".protoField(new " + delegateType + " () {" +
			"public " + plaidObjectType + " invoke" + "(final " + plaidObjectType + " " + thisVar + ", final " + plaidObjectType + " " + name + ") {" + 
				"final " + plaidScopeType + " temp$c0pe = " + CodeGen.localScope + ";" + 
				"final " + plaidScopeType + " local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);");
	}
	
	
	//Complicated generation of code to add a method to an uninitialized state
	//after body, follow with closeAnonymousDeclaration() function
	public final void assignToProtoMethod(String target, String name) {
		assign(target);
		// TODO: target + name is a temporary fix.  we should put the fully qualified name here
		output.append(classLoader + ".protoMethod(\"" + target + name + "\", new " + delegateType + " () {" +
			"public " + plaidObjectType + " invoke(final " + plaidObjectType + " " + thisVar + ", final " + plaidObjectType + " " + name + ") {" +
				"final " + plaidScopeType + " " + CodeGen.localScope + " = " + classLoader + ".localScope(" + CodeGen.globalScope + ");" +
				"local$c0pe.insert(\"" + name + "\", " + name + ", false" + ");" + 
				"local$c0pe.insert(\"" + CodeGen.thisVar +"\", " + CodeGen.thisVar + ", true" + ");");
	}
	
	//Complicated generation of code to wrap a first class function declaration
	//after body, follow with closeAnonymousDeclaration() function
	public final void assignToNewLambda(String target, String varName) {
		assign(target);
		append(classLoader + ".lambda(new " + lambdaType + " () {" + 
			"public " + plaidObjectType + " invoke(final " + plaidObjectType + " " + varName + ") throws " + plaidExceptionType + " {" + 
				"final " + plaidScopeType + " temp$c0pe = " + CodeGen.localScope + ";" + 
				"final " + plaidScopeType + " local$c0pe = new plaid.runtime.PlaidLocalScope(temp$c0pe);" + 
				"local$c0pe.insert(\"" + varName + "\", " + varName + ", false" + ");");
	}
	
	public final void closeAnonymousDeclaration() {
		output.append("}});");
	}
	
	public final void assignToNewStateObject(String target) {
		assign(target);
		output.append(newPlaidState + ";");
		updateVar(target);
	}
	
	public final void assignToNewObject(String target) {
		assign(target);
		output.append(newPlaidObject + ";");
		updateVar(target);
	}
	
	public final void assignToInstantiation(String target, String toInstantiate) {
		assign(target);
		output.append(toInstantiate + ".instantiate();");
		updateVar(target);
	}
	
	public final void assignCastedtoState(String target, String toCast) {
		assign(target);
		castToState(toCast);
		append(";");
		updateVar(target);
	}
	
	public final void assignToWith(String target, String s1, String s2) {
		assign(target);
		with(s1,s2);
		updateVar(target);
	}
	
	public final void assignToPrototype(String target, String state) {
		assign(target);
		append(state + ".getPrototype();");
		updateVar(target);
	}
	
	public final void assignToUnit(String target) {
		assign(target);
		append(utilClass + ".unit();");
		updateVar(target);
	}
	
	public final void assignToNewTag(String target, String tag, String caseOf) {
		assign(target);
		append(utilClass + ".tag(\"" + tag + "\", " + caseOf + ");");
		updateVar(target);
	}
	
	public final void assignToSuperTag(String target, String tag) {
		assign(target);
		append(tag + ".superTag();");
		updateVar(target);
	}
	
	public final void assignToStateTag(String target, String state) {
		assign(target);
		append(state + ".getTag();");
		updateVar(target);
	}
	
	public final void assignToQIDString(String target, String stateString) {
		assign(target);
		append("plaid.runtime.Util.getQualifiedIdString(" + stateString + ");" );
	}
	
	/*----------------------------
	 * Declarations of variables/fields/packages/classes
	 *----------------------------
	 */
	public final void declareVar(String type, String varName) {
		if ( type.startsWith("final") ) {
			output.append(type + " " + varName + ";");
		} else {
			output.append(type + " " + varName + ((cc.isDebugMode())?"=null;":" = plaid.runtime.Util.unit();"));
		}
	}
	
	public final void declareStaticVar(String type, String varName) {
		output.append("static ");
		declareVar(type, varName);
	}
	
	public final void declarePublicStaticVar(String type, String varName) {
		output.append("public ");
		declareStaticVar(type, varName);
	}
	
	public final void declarePublicStaticFinalVar(String type, String varName) {
		output.append("public static ");
		declareFinalVar(type, varName);
	}
	
	public final void declareFinalVar(String type, String varName) {
		//output.append("final ");
		declareVar("final " + type, varName);
	}
	
	public final void declarePackage(String packageName) {
		output.append("package " + packageName + ";");
	}
	
	public final void declareClass(String className) {
		output.append("class " + className);
	}
	
	public final void declarePublicClass(String className) {
		output.append("public ");
		declareClass(className);
	}
	
	public final void declareVarAssign(String type, String varName, String value) {
		output.append(type + " " + varName + " = " + value + ";");
	}
	
	public final void declareLambdaScope() {
		declareLambdaScope(thisVar);
	}
	
	public final void declareLambdaScope(String var) {
//		String theScope = IdGen.getId().getName();
//		declareVarAssign(plaidScopeType, theScope, globalScope);
		// create the new local scope
//		output.append("final " + plaidScopeType + " " + CodeGen.localScope + 
//				" = " + classLoader + ".localScope(" + CodeGen.globalScope + ");");
		// add "this" to the new local scope
//		output.append(CodeGen.localScope + ".insert(\"this$plaid\", this$plaid);");
		
	}
	
	public final void declareTopScope(String qid, String imports) {
		output.append("public static final " + plaidScopeType + " " + globalScope + " = " + 
							classLoader + ".globalScope(\"" + qid + "\"," + imports + ");");
	}
	
	/*----------------------------
	 * Java control and code structure
	 *----------------------------
	 */
	public final void ifCondition(String condition) {
		output.append("if (" + condition + ") ");
	}
	
	public final void elseCase() {
		output.append("else ");
	}
	
	public final void openBlock() {
		output.append("{");
	}
	
	public final void openStaticBlock() {
		output.append("static {");
	}
	
	public final void closeBlock() {
		output.append("}");
	}
	
	public final void ret(String toReturn) {
		output.append("return " + toReturn + ";");
	}
	
	/*----------------------------
	 * Various Plaid functions
	 *----------------------------
	 */
	public final void addMember(String target, String memberName, String genName) {
		output.append(target + ".addMember(\"" + memberName + "\"," + genName + ");");
	}
	
	public final void addMember(String target, String memberName, String genName, boolean immutable) {
		output.append(target + ".addMember(\"" + memberName + "\"," + genName + ", " + Boolean.toString(immutable) + ");");
	}
	
	public final void updateMember(String target, String memberName, String genName) {
		output.append(target + ".updateMember(\"" + memberName + "\"," + genName + ");");
	}
	
	public final void with(String target, String param) {
		output.append(target + ".with(" + param + ");");
	}
	
	public final void call(String function, String arg) {
		output.append(utilClass + ".call(" + function + ", " + arg + ")"); //TODO deal with removal of ;
	}
	
	public final void changeState(String target, String newState) {
		output.append(target + ".changeState(" + newState + ");");
	}
	
	public final void lookup(String name, String scope) {
		output.append(classLoader + ".lookup(\"" + name + "\", " + scope+ ");");
	}
	
	// TODO: I don't think this is right
	public final void lookupInCurrentScope(String name) {
		lookup(name, localScope);
	}
	
	/*----------------------------
	 * methods to create objects of different types
	 *----------------------------
	 */
	public final void newJavaObject(String type) {
		output.append("new " + type + "()");
	}
	
	public final void newJavaObject(String type,String arg) {
		output.append("new " + type + "(" + arg + ")");
	}

	public final void unit() {
		output.append( classLoader + ".unit()");
	}
	
	public final void plaidString(String s) {
		output.append(utilClass + ".string(\"" + s + "\")"); 
	}
	
	public final void plaidInt(int i) {
		output.append(utilClass + ".integer(" + i + ")");
	}
	
	public final void castToState(String s) {
		output.append(utilClass + ".toPlaidState(" + s + ")");
	}
	
	public final void throwNewPlaidException(String text) {
		output.append("throw new " + plaidExceptionType + "(\"" + text + "\");"); 
	}
	
	public final void throwNewPlaidException(String data, String text) {
		output.append("throw new " + plaidExceptionType + "("  + data + " + \"" + text + "\");"); 
	}
	
	/*----------------------------
	 * create a main function for top-level declarations
	 *----------------------------
	 */
	public final void topLevelMain(String functionName) {
		append("public static void main(String[] args) {");
		append("plaid.lang.Sys.setArgs(args,0);"); 
		append(rt + ".init();");
		if ( cc.isDebugMode() ) { 
			String name = functionName.substring(0, functionName.length()-"_func".length());
			append(rt + ".enterCall(" + classLoader + ".unit(),\"" + name +"\");");
		}
		append(functionName + ".invoke(" + classLoader + ".unit());");
		if ( cc.isDebugMode() ) { 
			String name = functionName.substring(0, functionName.length()-"_func".length());
			append(rt + ".leaveCall(" + classLoader + ".unit(),\"" + name +"\");");
		}
		append(rt + ".shutdown();");
		append("}");
	}
	
	/*----------------------------
	 * Annotations for fields, methods, and states (intermediate format to allow pretty printing)
	 *----------------------------
	 */
	/**
	 * @param name
	 * @param toplevel
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void methodAnnotation(String name, boolean toplevel) {
		output.append("@plaid.runtime.annotations.RepresentsMethod(name = \"" + name + "\", toplevel = " + toplevel + ")@");
	}
	
	/**
	 * @param name
	 * @param toplevel
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void fieldAnnotation(String name, boolean toplevel) {
		output.append("@plaid.runtime.annotations.RepresentsField(name = \"" + name + "\", toplevel = " + toplevel + ")@");
	}
	
	/**
	 * @param name
	 * @param toplevel
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void stateAnnotation(String name, boolean toplevel) {
		output.append("@plaid.runtime.annotations.RepresentsState(name = \"" + name + "\", toplevel = " + toplevel + ")@");
	}
	
	/**
	 * @param name
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void tagAnnotation(String name) {
		output.append("@plaid.runtime.annotations.RepresentsTag(name = \"" + name + "\")@");
	}
	
	public final void setLocation(Token t ) {
		if ( cc.isDebugMode() && t != null ) {
			try {
				String filename = cc.getCurrentCompilationUnit().getSourceFile().getCanonicalPath();
				filename = filename.replaceAll("\\\\","\\\\\\\\");
				output.append(rt + ".updateLocation("+ "\""+ filename + "\"" + "," + t.beginLine +","+ t.beginColumn + ");" );
			} catch (IOException e) {
				output.append(rt + ".updateLocation("+ "\""+ ">>UNKNOWN<<" + "\"" + "," + t.beginLine +","+ t.beginColumn + ");" );
			}
		}
	}
	
	public final void updateVar(String var) {
		updateVar(var, var);
	}
	
	public final void updateVar(String target, String source) {
		if ( cc.isDebugMode() ) {
			output.append(rt + ".updateVar(\"" + target +"\"," + source + ");" );
		}
	}
	
	public final void updateVar(ID destination, ID source) {
		output.append("local$c0pe.update(\"" + destination.getName() + "\", " + source.getName() + ");");
	}
	
	/*----------------------------
	 * Methods for transforming code into (almost) human-readable Java Code
	 *----------------------------
	 */
	//store the indent level
	private int indent = 0;

	public final String formatFile() {
		char current;
		
		for (int i = 0; i < output.length(); i++) {
			current = output.charAt(i);
			if (current == ';') {
				if (!(i < output.length() - 1 && output.charAt(i+1) == '}')) {
					i = i + newLine(i+1);
					i = i + indent(i+1);
				}
			} else if (current == '{') {
				i = i + newLine(i+1);
				indent++;
				i = i + indent(i+1);
			} else if (current == '}') {
				indent--;
				i = i + newLine(i);
				i = i + indent(i);
				if (i < output.length() - 1 && output.charAt(i+1) == ';') i++;  //}; should be atomic 
				if (!(i < output.length() - 1 && output.charAt(i+1) == '}')) {
					i = i + newLine(i+1);
					i = i + indent(i+1);
				}
			} else if (current == '@') {
				int search = i + 1;
				while (output.charAt(search) != '@') search++;
				i = search + indent(search + 1);
				i = search + newLine(search + 1);
				output.deleteCharAt(search);
			}
		}	
		return output.toString();
	}
	
	//create a newline at the current indent
	//returns the number of characters added
	private final int indent(int index) {
		for(int i = 0; i < indent; i++) output.insert(index,"\t");
		return indent;	
	}
	
	private final int newLine(int index) {
		output.insert(index, "\n");
		return 1;
	}
	
	public final String toString() {
		return output.toString();
	}
	
	/*----------------------------
	 * Testing
	 *----------------------------
	 */
	public static void main(String args[]) {
		CodeGen test = new CodeGen(new CompilerConfiguration());
		test.append("@annotation(test,true)@Class A{int i;String z = \"test\";{i = 2;}}");
		System.out.print(test.toString());
		test.formatFile();
		System.out.println("=>");
		System.out.print(test.toString());
	}

	// TODO: use mutable to make sure that var/val is enforced
	public void insertIntoScope(String scope, String varName, boolean immutable) {
		output.append(scope + ".insert(\"" + varName + "\", " + varName + ", " + Boolean.toString(immutable) + ");");
	}


	public void makeImmutable(ID field, boolean immutable) {
		output.append(field.getName() + ".setImmutable(" + Boolean.toString(immutable) + ");");
	}
}
