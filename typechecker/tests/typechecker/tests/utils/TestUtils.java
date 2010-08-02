package typechecker.tests.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;
import plaid.runtime.models.map.PlaidJavaObjectMap;
import plaid.runtime.models.map.PlaidStateMap;
import plaid.runtime.utils.Delegate;
import plaid.typechecker.AST.FieldTypeDecl;
import plaid.typechecker.AST.FullPermission;
import plaid.typechecker.AST.ID;
import plaid.typechecker.AST.ImmutablePermission;
import plaid.typechecker.AST.IntLiteral;
import plaid.typechecker.AST.PermType;
import plaid.typechecker.AST.PurePermission;
import plaid.typechecker.AST.SharedPermission;
import plaid.typechecker.AST.Type;
import plaid.typechecker.AST.UnannotatedLetBinding;
import plaid.typechecker.AST.UniquePermission;
import plaid.typechecker.AST.visitor.TypecheckerVisitor;
import plaid.typechecker.AST.visitor.helper.Context;

/**
 * TODO: We may want to merge all of these constructors into the actual Plaid 
 * code generation.  This could potentially speed up execution a lot by not 
 * requiring reloading the class every time.
 * 
 * @author mhahnenberg
 *
 */
public class TestUtils {
	/**
	 * Constructs a new UnannotatedLetBinding using the given variable, 
	 * expression, and body.
	 * 
	 * @param x The ID of the variable in the let expression.
	 * @param exp The expression to bind to the variable in the body of the let 
	 * 	expression.
	 * @param body The body of the let expression.
	 * @return The newly created UnannotatedLetBinding AST node.
	 */
	public static PlaidObject let(final PlaidObject x, final PlaidObject exp, final PlaidObject body) {	
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("x", false, false), 
				protoField(x));
		newState.addMember(Util.anonymousMemberDef("exp", false, false), 
				protoField(exp));
		newState.addMember(Util.anonymousMemberDef("body", false, false), 
				protoField(body));
		newState.addMember(Util.anonymousMemberDef("mutable", false, false), 
				protoField(Util.falseObject()));
		
		// instantiate the new prototype
		return initAndInstantiateState(UnannotatedLetBinding.UnannotatedLetBinding, 
				newState);
	}
	
	/**
	 * Constructs a new ID using the given name.
	 * 
	 * @param name The name of the ID.
	 * @return The newly created ID AST node.
	 */
	public static PlaidObject id(final String name) {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("name", false, false), 
				protoField(Util.string(name)));
		
		// instantiate the new prototype
		return initAndInstantiateState(ID.ID, newState);
	}
	
	/**
	 * Constructs a new IntLiteral given an integer value.
	 * 
	 * @param num The value of the IntLiteral AST node.
	 * @return The newly created IntLiteral AST node.
	 */
	public static PlaidObject intLiteral(final int num) {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("integer", false, false), 
				protoField(Util.integer(num)));
		
		// instantiate the new prototype
		return initAndInstantiateState(IntLiteral.IntLiteral, newState);
	}
	
	/**
	 * Constructs a new TypecheckerVisitor to use for typechecking tests.
	 * 
	 * @return The newly created TypecheckerVisitor.
	 */
	public static PlaidObject typechecker() {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("context", false, false), protoField(context()));
		
		// instantiate the new prototype
		return initAndInstantiateState(TypecheckerVisitor.TypecheckerVisitor, newState);
	}
	
	/**
	 * Constructs a new Context object to use for typechecking tests.
	 * 
	 * @return The newly created Context object.
	 */
	public static PlaidObject context() {
		PlaidState newState = Util.newState();
		
		return initAndInstantiateState(Context.Context, newState);
	}
	
	/**
	 * Constructs a new PermType based on the given Permission and Type.
	 * 
	 * @param perm
	 * @param type
	 * @return
	 */
	public static PlaidObject permtype(PlaidObject perm, PlaidObject type) {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("perm", false, false), 
				protoField(perm));
		newState.addMember(Util.anonymousMemberDef("type", false, false), 
				protoField(type));
		
		// instantiate the new prototype
		return initAndInstantiateState(PermType.PermType, newState);
	}
	
	/**
	 * Constructs a new UniquePermission object.
	 * 
	 * @return The new UniquePermission object;
	 */
	public static PlaidObject unique() {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// instantiate the new prototype
		return initAndInstantiateState(UniquePermission.UniquePermission, newState);
	}
	
	/**
	 * Constructs a new FullPermission object.
	 * 
	 * @return The new FullPermission object;
	 */
	public static PlaidObject full() {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// instantiate the new prototype
		return initAndInstantiateState(FullPermission.FullPermission, newState);
	}
	
	/**
	 * Constructs a new SharedPermission object.
	 * 
	 * @return The new SharedPermission object;
	 */
	public static PlaidObject shared() {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// instantiate the new prototype
		return initAndInstantiateState(SharedPermission.SharedPermission, newState);
	}
	
	/**
	 * Constructs a new ImmutablePermission object.
	 * 
	 * @return The new ImmutablePermission object;
	 */
	public static PlaidObject immutable() {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// instantiate the new prototype
		return initAndInstantiateState(ImmutablePermission.ImmutablePermission, newState);
	}
	
	/**
	 * Constructs a new PurePermission object.
	 * 
	 * @return The new PurePermission object;
	 */
	public static PlaidObject pure() {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// instantiate the new prototype
		return initAndInstantiateState(PurePermission.PurePermission, newState);
	}
	
	/**
	 * Constructs a new Type object based upon the specified type abbreviations 
	 * and declarations.
	 * 
	 * @param abbrevs Array of ID objects for the abbreviations in the PermType.
	 * @param decls Array of TypeDecl objects (MethodTypeDecl or FieldTypeDecl).
	 * @return
	 */
	public static PlaidObject type(PlaidObject[] abbrevs, PlaidObject[] decls) {
		// fill in the sets
		Set<PlaidObject> typeAbbrevs = new HashSet<PlaidObject>();
		for (PlaidObject abbrev : abbrevs) {
			typeAbbrevs.add(abbrev);
		}
		Set<PlaidObject> typeDecls = new HashSet<PlaidObject>();
		for (PlaidObject decl : decls) {
			typeDecls.add(decl);
		}
	
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("typeAbbrevs", false, false), 
				protoField(new PlaidJavaObjectMap(typeAbbrevs)));
		newState.addMember(Util.anonymousMemberDef("typeDecls", false, false), 
				protoField(new PlaidJavaObjectMap(typeDecls)));
		
		// instantiate the new prototype
		return initAndInstantiateState(Type.Type, newState);
	}
	
	/**
	 * TODO
	 * @param name
	 * @param retPermType
	 * @param argTypes
	 * @param recvPermTypeBefore
	 * @param recvPermTypeAfter
	 * @param varTypeTrans
	 * @return
	 */
	public static PlaidObject methodType(PlaidObject name, PlaidObject retPermType, 
			PlaidObject argTypes, PlaidObject recvPermTypeBefore, 
			PlaidObject recvPermTypeAfter, PlaidObject varTypeTrans) {
		return null;
	}
	
	/**
	 * Constructs a new FieldTypeDecl AST Node given a PermType for the field.
	 * @param name ID of the field.
	 * @param permType PermType of the field.
	 * @return
	 */
	public static PlaidObject fieldType(PlaidObject name, PlaidObject permType) {
		// create a new blank prototype
		PlaidState newState = Util.newState();
		
		// add the necessary members to the new prototype
		newState.addMember(Util.anonymousMemberDef("name", false, false), 
				protoField(name));
		newState.addMember(Util.anonymousMemberDef("permType", false, false), 
				protoField(permType));
		
		// instantiate the new prototype
		return initAndInstantiateState(FieldTypeDecl.FieldTypeDecl, newState);
	}
	
	/**
	 * Fetches the specified field from within the given PlaidObject.
	 * 
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static PlaidObject getField(String fieldName, PlaidObject obj) {
		Map<PlaidMemberDef, PlaidObject> members = obj.getMembers();
		for (Entry<PlaidMemberDef, PlaidObject> member : members.entrySet()) {
			if (member.getKey().getMemberName().equals(fieldName)) {
				return member.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Creates a new ProtoField that simply initializes the field with whatever
	 * object is passed to the function.
	 * 
	 * @param obj The object with which the field will be initialized.
	 * @return The new ProtoField.
	 */
	private static PlaidMethod protoField(final PlaidObject obj) {
		return Util.protoField(new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args) throws PlaidException {
				return obj;
			}
		});
	}
	
	/**
	 * Initializes and instantiates a new state based upon the prototype object
	 * stored in the generated Java class (usually called <state name>.<state name>)
	 * 
	 * @param prototypeObj
	 * @param newState
	 * @return
	 */
	private static PlaidObject initAndInstantiateState(PlaidObject prototypeObj, PlaidState newState) {
		// get the prototype for the state
		PlaidStateMap prototype = new PlaidStateMap();
		prototype.setPrototype(prototypeObj);
		
		// initialize the rest of the new object with the old prototype
		PlaidState initializedState = prototype.initState(newState);
		
		// instantiate the new prototype
		return initializedState.instantiate();
	}
}
