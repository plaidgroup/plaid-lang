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
import java.util.HashMap;
import java.util.Map;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.coreparser.Token;
import plaid.runtime.PlaidConstants;

/**
 * @author kbn
 *
 */
public class CodeGen {
	private CompilerConfiguration cc = null; 
	private String[] indentTable = new String[128];
	
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
	public static final String aeminiumRt = "aeminium.runtime.implementations.Factory.getRuntime()";
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
	public static final String plaidJavaObjectType = runtimePackage + ".PlaidJavaObject";
	public static final String plaidStateType = runtimePackage + ".PlaidState";
	public static final String plaidMethodType = runtimePackage + ".PlaidMethod";
	public static final String plaidMemberDefType = runtimePackage + ".PlaidMemberDef";
	public static final String plaidExceptionType = runtimePackage + ".PlaidException";
	public static final String plaidTagType = runtimePackage + ".PlaidTag";
	public static final String delegateType = utilsPackage + ".Delegate";
	public static final String lambdaType = utilsPackage + ".Lambda";
	
	public static final String anonymousDeclaration = "<Anonymous>";

	
	public CodeGen(CompilerConfiguration cc) {
		this.cc = cc;
		if ( cc.addCopyrightHeader() ) {
			output.append(copyright);
		}
		// compute indent table
		indentTable[0] = "";
		for ( int i = 1 ; i < indentTable.length; i++ ) {
			indentTable[i] = indentTable[i-1] + "\t";
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
	
	public final void assignToNull(String target) {
		assign(target);
		append("null;");
		updateVarDebugInfo(target);
		
	}
	
	public final void assignToCall(String target, String function, String arg) {
		assign(target);
		call(function,arg);
		append(";");
		updateVarDebugInfo(target);
	}
	
	public final void assignToID(String target, String val) {
		assign(target);
		append(val);
		append(";");
		updateVarDebugInfo(target);
	}
	
	public final void assignToChangedState(String target, String object, String newState, String wipe) {
		
		assign(target);
		changeState(object,newState,wipe);
		updateVarDebugInfo(target);
	}

	public final void assignToLookup(String target, String name, String scope) {
		assign(target);
		lookup(name, scope);
		updateVarDebugInfo(target);
	}
	
	public final void assignToNewJavaObject(String target, String type) {
		assign(target);
		newJavaObject(type);
		output.append(";");
		updateVarDebugInfo(target);
	}
	
	public final void assignToNewJavaObject(String target, String type, String arg) {
		assign(target);
		newJavaObject(type,arg);
		output.append(";");
		updateVarDebugInfo(target);
	}
	
	//need to implement multiple argument Java constructors if needed 
	
	//Complicated generation of code to add a field to an uninitialized state
	//after body, follow with closeAnonymousDeclaration() function
	public final void assignToProtoField(String target, String name) {
		assign(target);
		output.append(classLoader + ".protoField(new " + delegateType + " () {" +
			"public " + plaidObjectType + " invoke" + "(final " + plaidObjectType + " " + thisVar + ", final " + plaidObjectType + " " + name + ") {" + 
				"final " + plaidScopeType + " temp$c0pe = " + localScope + ";" + 
				"final " + plaidScopeType + " " + localScope + " = " + classLoader + ".localScope(temp$c0pe);");
	}
	
	
	//Complicated generation of code to add a method to an uninitialized state
	//after body, follow with closeAnonymousDeclaration() function
	public final void assignToProtoMethod(String target, String name, String path) {
		assign(target);
		// TODO: target + name is a temporary fix.  we should put the fully qualified name here
		output.append(classLoader + ".protoMethod(" + path + ", new " + delegateType + " () {" +
			"public " + plaidObjectType + " invoke(final " + plaidObjectType + " " + thisVar + ", final " + plaidObjectType + " " + name + ") {" +
				"final " + plaidScopeType + " " + localScope + " = " + classLoader + ".localScope(" + CodeGen.globalScope + ");");
		insertIntoScope(localScope, name, false);
		insertIntoScope(localScope, CodeGen.thisVar, true);
	}
	
	//Complicated generation of code to wrap a first class function declaration
	//after body, follow with closeAnonymousDeclaration() function
	public final void assignToNewLambda(String target, String varName) {
		assign(target);
		append(classLoader + ".lambda(new " + lambdaType + " () {" + 
			"public " + plaidObjectType + " invoke(final " + plaidObjectType + " " + varName + ") throws " + plaidExceptionType + " {" + 
				"final " + plaidScopeType + " temp$c0pe = " + localScope + ";" + 
				"final " + plaidScopeType + " " + localScope + " = " + classLoader + ".localScope(temp$c0pe);");
		insertIntoScope(localScope, varName, false);
	}
	
	//Member Definition
	public final void assignToNewMemberDef(String target, String varName, String definedIn, boolean mutable, boolean overrides) {
		assign(target);
		append(utilClass + ".memberDef(\"" + varName + "\", " + definedIn + ", " + mutable + ", " + overrides + ");");
	}
	
	public final void assignToAnonymousMemberDef(String target, String varName, boolean mutable, boolean overrides) {
		assign(target);
		append(utilClass + ".anonymousMemberDef(\"" + varName + "\", " + mutable + ", " + overrides + ");");
	}
	
	public final void closeAnonymousDeclaration() {
		output.append("}});");
	}
	
	public final void assignToNewStateObject(String target) {
		assign(target);
		output.append(newPlaidState + ";");
		updateVarDebugInfo(target);
	}
	
	public final void assignToNewObject(String target) {
		assign(target);
		output.append(newPlaidObject + ";");
		updateVarDebugInfo(target);
	}
	
	public final void assignToInstantiation(String target, String toInstantiate) {
		assign(target);
		output.append(toInstantiate + ".instantiate();");
		updateVarDebugInfo(target);
	}
	
	public final void assignCastedtoState(String target, String toCast) {
		assign(target);
		castToState(toCast);
		append(";");
		updateVarDebugInfo(target);
	}
	
	public final void assignToWith(String target, String s1, String s2) {
		assign(target);
		with(s1,s2);
		updateVarDebugInfo(target);
	}

	public final void assignToFreeze(String target, String s1) {
		assign(target);
		freeze(s1);
		updateVarDebugInfo(target);
	}
	
	public final void assignToStateInitialization(String target, String toInit, String initState) {
		assign(target);
		output.append(toInit + ".initState(" + initState + ");");
		updateVarDebugInfo(target);
	}
	
	public final void assignToPrototype(String target, String state) {
		assign(target);
		append(state + ".getPrototype();");
		updateVarDebugInfo(target);
	}
	
	public final void assignToUnit(String target) {
		assign(target);
		append(utilClass + ".unit();");
		updateVarDebugInfo(target);
	}
	
	public final void assignToNewTag(String target, String tag) {
		assign(target);
		append(utilClass + ".tag(\"" + tag + "\");");
		updateVarDebugInfo(target);
	}
	
	public final void assignToNewTag(String target, String tag, String pkg, String superTag) {
		assign(target);
		append(utilClass + ".tag(\"" + tag + "\", \"" + pkg + "\", " + superTag + ");");
		updateVarDebugInfo(target);
	}
	
	public final void assignToSuperTag(String target, String tag) {
		assign(target);
		append(tag + ".superTag();");
		updateVarDebugInfo(target);
	}
	
	public final void assignToStateTag(String target, String state) {
		assign(target);
		append(state + ".getTag();");
		updateVarDebugInfo(target);
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
	
//	public final void declareLambdaScope() {
//		declareLambdaScope(thisVar);
//	}
//	
//	public final void declareLambdaScope(String var) {
////		String theScope = IdGen.getId().getName();
////		declareVarAssign(plaidScopeType, theScope, globalScope);
//		// create the new local scope
////		output.append("final " + plaidScopeType + " " + CodeGen.localScope + 
////				" = " + classLoader + ".localScope(" + CodeGen.globalScope + ");");
//		// add "this" to the new local scope
////		output.append(CodeGen.localScope + ".insert(\"this$plaid\", this$plaid);");
//		
//	}
	
	public final void declareGlobalScope(String qid, String imports) {
		output.append("public static final " + plaidScopeType + " " + globalScope + " = ");
		output.append(classLoader + ".globalScope(\"" + qid + "\"," + imports + ");");
	}
	
	public final void declareLocalScope(String parentScope) {
		output.append("final " + plaidScopeType + " " + localScope + " = ");
		output.append(classLoader + ".localScope(" + parentScope + ");");
	}
	
	/*----------------------------
	 * Scoping updates
	 *----------------------------
	 */
	
	public final void updateVarInScope(String destination, String source) {
		ifCondition(source + " instanceof plaid.runtime.models.map.PlaidLookupMap");  //TODO : this is not keeping encapsulation of plaid.runtime.map
		throwNewPlaidException("No object found to insert into scope. source = [\" + " + source + " + \"], destination = [" + destination + "] ");
		elseCase();
		output.append(localScope + ".update(\"" + destination + "\", " + source + ");");
	}
	
	public final void insertIntoScope(String scope, String varName, boolean isImmutable) {
		ifCondition(varName + " instanceof plaid.runtime.models.map.PlaidLookupMap");  //TODO : this is not keeping encapsulation of plaid.runtime.map
		throwNewPlaidException("No object found to insert into scope. varName = \" + " + varName + " + \"");
		elseCase();
		output.append(scope + ".insert(\"" + varName + "\", " + varName + ", "+ isImmutable + ");");
	}
	
	// TODO: use mutable to make sure that var/val is enforced
	public void makeImmutable(String field, boolean immutable) {
		output.append(field + ".setImmutable(" + Boolean.toString(immutable) + ");");
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
		output.append(target + ".addMember(" + memberName + "," + genName + ");");
	}
	
	public final void addTag(String target, String tagName, String enclosingTagName) {
		output.append(target + ".addTag(" + tagName + ", " + enclosingTagName + ");");
	}
	
	public final void addTopTag(String target, String tagName) {
		output.append(target + ".addTopTag(" + tagName + ");");
	}
	
	public final void updateMember(String target, String memberName, String genName) {
		output.append(target + ".updateMember(\"" + memberName + "\"," + genName + ");");
	}
	
	public final void with(String target, String param) {
		output.append(target + ".with(" + param + ");");
	}
	
	public final void nest(String tagTarget, String stateParam) {
		output.append(tagTarget + ".nest(" + stateParam + ");");
	}
	
	public final void freeze(String target) {
		output.append(target + ".freeze();");
	}
	
	public final void call(String function, String arg) {
		output.append(utilClass + ".call(" + function + ", " + arg + ")"); //TODO deal with removal of ;
	}
	
	public final void changeState(String target, String newState, String wipe) {
		output.append(target + ".changeState(" + newState + ", " + wipe + ");");
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
	
	// Exists so that this method can be called from Plaid code,
	// unit being a reserved word
	public final void unit2() {
		output.append (classLoader + ".unit()");
	}
	
	public final void plaidString(String s) {
		output.append(utilClass + ".string(\"" + s + "\")"); 
	}
	
	public final void plaidInt(java.math.BigInteger i) {
		output.append(utilClass + ".integer(new java.math.BigInteger(\"" + i.toString() + "\"))");
	}
	
	public final void plaidDouble(double d) {
		output.append(utilClass + ".floatingDouble(" + d + ")");
	}

	public final void plaidChar(String cImage) {
		output.append(utilClass + ".character(" + cImage + ")");
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
		if (cc.isAeminiumMode())
			append(aeminiumRt + ".init();");
		append(rt + ".init();");
		if ( cc.isDebugMode() ) { 
			String name = functionName.substring(0, functionName.length()-"_func".length());
			append(rt + ".enterCall(" + classLoader + ".unit(),\"" + name +"\");");
		}
		
		//append(functionName + ".invoke(" + classLoader + ".unit());");
		//get a arraylist<String>
		append("plaid.runtime.PlaidObject arg1$plaid$init = new plaid.runtime.models.map.PlaidJavaObjectMap(plaid.lang.Sys.getCommandLineArguments());");
		append("plaid.runtime.PlaidObject convert$plaid$init = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup(\"makeListFromJava\", global$c0pe);");
		append("plaid.runtime.PlaidObject arg2$plaid$init;");
		assignToCall("arg2$plaid$init", "convert$plaid$init", "arg1$plaid$init");
		
		call(functionName, "arg2$plaid$init");
		append(";");
		
		if ( cc.isDebugMode() ) { 
			String name = functionName.substring(0, functionName.length()-"_func".length());
			append(rt + ".leaveCall(" + classLoader + ".unit(),\"" + name +"\");");
		}
		append(rt + ".shutdown();");
		if (cc.isAeminiumMode())
			append(aeminiumRt + ".shutdown();");
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
	public final void topMethodAnnotation(String name, String thePackage) {
		output.append("@SuppressWarnings(\"unused\")");if (cc.prettyPrint()) output.append("@");
		output.append("@plaid.runtime.annotations.RepresentsMethod(name = \"" + name + "\", inPackage = \"" + thePackage + "\", toplevel = " + true + ")");
		if (cc.prettyPrint()) output.append("@");
	}
	
	public final void methodAnnotation(String name) {
		output.append("@plaid.runtime.annotations.RepresentsMethod(name = \"" + name + "\", toplevel = " + false + ")");
		if (cc.prettyPrint()) output.append("@");
	}
	
	/**
	 * @param name
	 * @param toplevel
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void topFieldAnnotation(String name, String thePackage) {
		output.append("@SuppressWarnings(\"unused\")");if (cc.prettyPrint()) output.append("@");
		output.append("@plaid.runtime.annotations.RepresentsField(name = \"" + name + "\", inPackage = \"" + thePackage + "\", toplevel = " + true + ")");
		if (cc.prettyPrint()) output.append("@");
	}
	
	public final void fieldAnnotation(String name) {
		output.append("@plaid.runtime.annotations.RepresentsField(name = \"" + name + "\", toplevel = " + false + ")");
		if (cc.prettyPrint()) output.append("@");
	}
	
	/**
	 * @param name
	 * @param toplevel
	 * @param members - comma separated list of member names
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void topStateAnnotation(String name, String thePackage, String jsonRep) {
		output.append("@SuppressWarnings(\"unused\")");if (cc.prettyPrint()) output.append("@");
		output.append("@plaid.runtime.annotations.RepresentsState(name = \"" + name + "\", inPackage = \"" + thePackage + "\", toplevel = " + true + ", jsonRep = \"" + jsonRep + "\")");
		if (cc.prettyPrint()) output.append("@");
	}
	
	public final void stateAnnotation(String name) {
		output.append("@plaid.runtime.annotations.RepresentsState(name = \"" + name + "\", toplevel = " + false + ")");
		if (cc.prettyPrint()) output.append("@");
	}
	
	/**
	 * @param name
	 * @return trailing @ sign to allow for correct indentation during pretty printing
	 */
	public final void tagAnnotation(String name) {
		output.append("@plaid.runtime.annotations.RepresentsTag(name = \"" + name + "\")");
		if (cc.prettyPrint()) output.append("@");
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
		} else {
			// add comments 			
			try {
				String filename = cc.getCurrentCompilationUnit().getSourceFile().getCanonicalPath();
				filename = filename.replaceAll("\\\\","\\\\\\\\");
				output.append("//" + filename + " (" + t.beginLine +","+ t.beginColumn + ");" );
			} catch (Exception e) {}
		}
	}
	
	public final void setLocation(String filename, int beginLine, int beginColumn) {
		output.append("// "+ filename + " (" + beginLine +","+ beginColumn + "\n" );
		if (cc.isDebugMode()) {
			output.append(rt + ".updateLocation("+ "\""+ filename + "\"" + "," + beginLine +","+ beginColumn + ");" );
		}
	}
	
	public final void updateVarDebugInfo(String var) {
		updateVarDebugInfo(var, var);
	}
	
	public final void updateVarDebugInfo(String target, String source) {
		if ( cc.isDebugMode() ) {
			output.append(rt + ".updateVar(\"" + target +"\"," + source + ");" );
		}
	}
	

	/*----------------------------
	 * Methods for transforming code into (almost) human-readable Java Code
	 *----------------------------
	 */
	//store the indent level
	private int indent = 0;

	public final String formatFile() {
		if (!cc.prettyPrint()) return toString();
		char current;
		int copiedToIndex = 0;
		boolean inAnnotation = false;
		boolean inString = false;
		StringBuilder formatted = new StringBuilder();
		
		
		for (int i = 0; i < output.length(); i++) {
			current = output.charAt(i);
			if (inAnnotation) { //Annotations are special
				if (current == '@') {
					copiedToIndex = copyUpTo(i, copiedToIndex, output, formatted); //grab all but the last @
					copiedToIndex++;  // skip the @ next time we copy
					formatted.append(getNewLine());
					formatted.append(getIndent());
					inAnnotation = false;
				}
				//else wait for next @
			} else if (inString) { //Strings are too
				if (current =='"' && output.charAt(i-1) != '\\') { //non-escaped "
					copiedToIndex = copyUpTo(i+1, copiedToIndex, output, formatted);
					inString = false;
				}
			} else if (current == ';') {
				copiedToIndex = copyUpTo(i+1, copiedToIndex, output, formatted);
				formatted.append(getNewLine()); // new line
				formatted.append(getIndent());
			} else if (current == '{') {
				copiedToIndex = copyUpTo(i+1, copiedToIndex, output, formatted);
				formatted.append(getNewLine());
				indent++;
				formatted.append(getIndent());
			} else if (current == '}') {
				indent--;
				formatted.append(getNewLine());
				formatted.append(getIndent());
				if (i < output.length() - 1 && output.charAt(i+1) == ';') { //}; should be atomic 
					copiedToIndex = copyUpTo(i+2,copiedToIndex, output, formatted);
					i++;
				} else {
					copiedToIndex = copyUpTo(i+1,copiedToIndex, output, formatted);
					if (copiedToIndex != output.length()){
						formatted.append(getNewLine());
						formatted.append(getIndent());
					}
				}
			} else if (current == '@') {
				inAnnotation = true;
			} else if (current =='\"') {
				inString = true;
			}
		}
		if (copiedToIndex != output.length()) { //there was a problem...
			String location = null;
			if (inString) location = "String never ended";
			else if (inAnnotation) location = "Annotation never ended";
			else location = "Mismatched Braces";
			
			throw new RuntimeException("Bad formatting: " + location);
		}
		
		return formatted.toString();
	}
	
	private final String getIndent() {
		if ( indent < indentTable.length) {
			return indentTable[indent]; 
		} else {
			String indentString = indentTable[indentTable.length-1];
			for (int i = indentTable.length-1; i < indent; i++) indentString += "\t";
			return indentString;
		}
	}
	
	private final String getNewLine() {
		 return "\n";
	}
	
	private static final int copyUpTo(int copyUpTo, int copyFrom, StringBuilder from, StringBuilder to) {
		to.append(from.substring(copyFrom, copyUpTo));
		return copyUpTo;
		
		
	}
	
	public final String toString() {
		return output.toString();
	}
	
	/*----------------------------
	 * Testing
	 *----------------------------
	 */
	public static void main(String args[]) {
		CompilerConfiguration cc = new CompilerConfiguration();
		cc.setPrettyPrint(true);
		CodeGen test = new CodeGen(cc);
		test.append("@annotation(test,true)@@annotation2(test,false)@Class A{int i;String z = \"te\\\"st\";{i = 2;}}");
		System.out.print(test.toString());
		System.out.println("=>");
		System.out.print(test.formatFile());
	}

	/*
	 * Following are functions generated optimized codes
	 * 
	 */
	
	public final void assignToTailCall(String retVal, String method, String arg) {
		assign(retVal);
		//output.append("new " + runtimePackage + ".PlaidTailCall" + "(" +  method + ", " + arg + ");");
		output.append( utilClass + ".tailCall(" + method +"," + arg + ");");
	}
}
