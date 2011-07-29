package plaid.parser.test.astfactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import plaid.parser.ast.ASTNode;
import plaid.parser.ast.AbstractFieldDecl;
import plaid.parser.ast.AbstractMethodDecl;
import plaid.parser.ast.AbstractStateDecl;
import plaid.parser.ast.AbstractStateValDecl;
import plaid.parser.ast.Application;
import plaid.parser.ast.Arg;
import plaid.parser.ast.ArgSpec;
import plaid.parser.ast.ArgumentExpression;
import plaid.parser.ast.Assignment;
import plaid.parser.ast.AtomicBlock;
import plaid.parser.ast.BlockExpr;
import plaid.parser.ast.Case;
import plaid.parser.ast.Cast;
import plaid.parser.ast.CompilationUnit;
import plaid.parser.ast.ConcreteFieldDecl;
import plaid.parser.ast.ConcreteMethodDecl;
import plaid.parser.ast.ConcreteStateDecl;
import plaid.parser.ast.ConcreteStateValDecl;
import plaid.parser.ast.Decl;
import plaid.parser.ast.DeclList;
import plaid.parser.ast.DeclOrStateOp;
import plaid.parser.ast.DefaultCase;
import plaid.parser.ast.Dereference;
import plaid.parser.ast.DestructiveDereference;
import plaid.parser.ast.DoubleLiteral;
import plaid.parser.ast.Expression;
import plaid.parser.ast.FieldDecl;
import plaid.parser.ast.Freeze;
import plaid.parser.ast.GroupArg;
import plaid.parser.ast.GroupDecl;
import plaid.parser.ast.GroupPermission;
import plaid.parser.ast.GroupPermission.GroupPermissionKind;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.Import;
import plaid.parser.ast.InfixOperator;
import plaid.parser.ast.IntLiteral;
import plaid.parser.ast.Lambda;
import plaid.parser.ast.LambdaType;
import plaid.parser.ast.Match;
import plaid.parser.ast.MetaArg;
import plaid.parser.ast.MetaType;
import plaid.parser.ast.MethodDecl;
import plaid.parser.ast.Modifier;
import plaid.parser.ast.NewInstance;
import plaid.parser.ast.NominalObjectType;
import plaid.parser.ast.PatternCase;
import plaid.parser.ast.Permission;
import plaid.parser.ast.Permission.PermissionKind;
import plaid.parser.ast.QualifiedIdentifier;
import plaid.parser.ast.Replace;
import plaid.parser.ast.Specifier;
import plaid.parser.ast.SplitBlock;
import plaid.parser.ast.StateChange;
import plaid.parser.ast.StateDecl;
import plaid.parser.ast.StateExpression;
import plaid.parser.ast.StateOpRemove;
import plaid.parser.ast.StateOpRename;
import plaid.parser.ast.StatePrim;
import plaid.parser.ast.StateRef;
import plaid.parser.ast.StateValDecl;
import plaid.parser.ast.Stmt;
import plaid.parser.ast.StringLiteral;
import plaid.parser.ast.Type;
import plaid.parser.ast.TypeArg;
import plaid.parser.ast.UnaryOperator;
import plaid.parser.ast.UnpackInnerGroups;
import plaid.parser.ast.VarDecl;
import plaid.parser.ast.With;

public class ASTFactory {
	// expression
	public static IntLiteral IntLiteral(int value) {
		return new IntLiteral(ASTNode.DEFAULT_TOKEN, value);
	}
	
	public static StringLiteral StringLiteral(String value) {
		return new StringLiteral(ASTNode.DEFAULT_TOKEN, value);
	}

	public static DoubleLiteral DoubleLiteral(Double value) {
		return new DoubleLiteral(ASTNode.DEFAULT_TOKEN, value);
	}
	
	public static Identifier Identifier(String value) {
		return new Identifier(ASTNode.DEFAULT_TOKEN, value);
	}
	
	public static QualifiedIdentifier QualifiedIdentifier(String... s) {
		List<Identifier> ids = new ArrayList<Identifier>();
		for (int i = 0; i < s.length; i++) {
			ids.add(new Identifier(null, s[i]));
		}
		return new QualifiedIdentifier(null, ids);
	}
	
	public static ArgumentExpression ArgumentExpression(Expression ...es) {
		return new ArgumentExpression(ASTNode.DEFAULT_TOKEN, Arrays.asList(es));
	}
	
	public static Dereference Dereference(Expression left, Identifier right) {
		return new Dereference(ASTNode.DEFAULT_TOKEN, left, right);
	}
	
	public static DestructiveDereference DestructiveDereference(Expression left, Identifier right) {
		return new DestructiveDereference(ASTNode.DEFAULT_TOKEN, left, right);
	}

	public static Application Application(Expression left, Expression right) {
		return new Application(ASTNode.DEFAULT_TOKEN, left, null, right);
	}
	
	public static Application Application(Expression left, List<Expression> metaArgs, Expression right) {
		return new Application(ASTNode.DEFAULT_TOKEN, left, metaArgs, right);
	}
	
	public static List<Expression> MetaArgs(Expression ...args) {
		return Arrays.asList(args);
	}
	
	public static BlockExpr BlockExpression(Stmt ...args) {
		return new BlockExpr(ASTNode.DEFAULT_TOKEN, Arrays.asList(args));
	}
	
	public static InfixOperator InfixOperator(Expression left, Identifier op, Expression right) {
		return new InfixOperator(ASTNode.DEFAULT_TOKEN, left, op, right);
	}
	
	public static DeclList DeclList(Decl ...decls) {
		return new DeclList(ASTNode.DEFAULT_TOKEN, Arrays.asList(decls));
	}
	
	public static NewInstance NewInstance(StateExpression state) {
		return new NewInstance(ASTNode.DEFAULT_TOKEN, state);
	}

	public static Cast Cast(Expression e, Type type) {
		return new Cast(ASTNode.DEFAULT_TOKEN, e, type);
	}
	
	public static StateRef StateRef(Expression e) {
		return new StateRef(ASTNode.DEFAULT_TOKEN, e, new ArrayList<DeclOrStateOp>());
	}
	
	public static StateRef StateRef(Expression e, DeclOrStateOp ...dops) {
		return new StateRef(ASTNode.DEFAULT_TOKEN, e, Arrays.asList(dops));
	}
	
	public static With With(StatePrim p1, StatePrim p2) {
		return new With(ASTNode.DEFAULT_TOKEN, p1, p2);
	}
	
	public static UnaryOperator UnaryOperator(Identifier op, Expression e) {
		return new UnaryOperator(ASTNode.DEFAULT_TOKEN, op, e);
	}
	
	public static Assignment Assignment(Expression target, Identifier field, Expression value) {
		return new Assignment(ASTNode.DEFAULT_TOKEN, target, field, value);
	}
	
	public static StateChange StateChange(Expression e, StateExpression state) {
		return new StateChange(ASTNode.DEFAULT_TOKEN, e, state);
	}
	
	public static Replace Replace(Expression e, StateExpression state) {
		return new Replace(ASTNode.DEFAULT_TOKEN, e, state);
	}
	
	public static Case PatternCase(QualifiedIdentifier qi, Expression e) {
		return new PatternCase(ASTNode.DEFAULT_TOKEN, qi, e);
	}
	
	public static Case DefaultCase(Expression e) {
		return new DefaultCase(ASTNode.DEFAULT_TOKEN, e);
	}
	
	public static StateOpRemove StateOpRemove(Identifier id) {
		return new StateOpRemove(ASTNode.DEFAULT_TOKEN, id);
	}
	
	public static StateOpRename StateOpRename(Identifier from, Identifier to) {
		return new StateOpRename(ASTNode.DEFAULT_TOKEN, from, to );
	}
	
	public static Freeze Freeze(Expression e) {
		return new Freeze(ASTNode.DEFAULT_TOKEN, e);
	}
	
	public static Match Match(Expression e, Case ...cases) {
		return new Match(ASTNode.DEFAULT_TOKEN,  e, Arrays.asList(cases));
	}
	
	public static AtomicBlock AtomicBlock(Expression e, Expression ...datagroups) {
		return new AtomicBlock(ASTNode.DEFAULT_TOKEN,  Arrays.asList(datagroups), e);
	}
	
	public static SplitBlock SplitBlock(Expression e, Expression ...datagroups) {
		return new SplitBlock(ASTNode.DEFAULT_TOKEN,  Arrays.asList(datagroups), e);
	}
	
	public static UnpackInnerGroups UnpackInnerGroups(Expression e) {
		return new UnpackInnerGroups(ASTNode.DEFAULT_TOKEN, e);
	}
	
	public static VarDecl VarDecl(Specifier specifier, Type type, Identifier field, Expression value) {
		return new VarDecl(ASTNode.DEFAULT_TOKEN, specifier, type, field, value);
	}

	public static Specifier Val() {
		return new Specifier(ASTNode.DEFAULT_TOKEN, Specifier.SpecifierKind.VAL);
	}
	
	public static Specifier Var() {
		return new Specifier(ASTNode.DEFAULT_TOKEN, Specifier.SpecifierKind.VAR);
	}

	public static Lambda Lambda(List<MetaArg> metaArgsSpec, List<Arg> args, List<Arg> env, Expression body) {
		return new Lambda(ASTNode.DEFAULT_TOKEN, metaArgsSpec, args, env, body);
	}
	
	public static Arg Arg(ArgSpec argSpec, Identifier id) {
		return new Arg(ASTNode.DEFAULT_TOKEN, argSpec, id);
	}

	public static Permission  Shared(Expression datagroup) {
		return new Permission(ASTNode.DEFAULT_TOKEN, PermissionKind.SHARED, datagroup);
	}

	public static Permission  Immutable() {
		return new Permission(ASTNode.DEFAULT_TOKEN, PermissionKind.IMMUTABLE, Expression.EMPTY);
	}

	public static Permission  Unique() {
		return new Permission(ASTNode.DEFAULT_TOKEN, PermissionKind.UNIQUE, Expression.EMPTY);
	}
	
	public static Permission  None() {
		return new Permission(ASTNode.DEFAULT_TOKEN, PermissionKind.NONE, Expression.EMPTY);
	}
	
	public static GroupPermission Protected() {
		return new GroupPermission(ASTNode.DEFAULT_TOKEN, GroupPermissionKind.PROTECTED);
	}
	
	public static MetaArg GroupArg(GroupPermission gp, Identifier name) {
		return new GroupArg(ASTNode.DEFAULT_TOKEN, gp, name);
	}
	
	public static GroupDecl GroupDecl(Identifier name) {
		return new GroupDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, name);
	}
	
	public static FieldDecl AbstractFieldDecl(Specifier specifier, Type type, Identifier name) {
		return new AbstractFieldDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, specifier, type, name);
	}
	
	public static FieldDecl ConcreteFieldDecl(Specifier specifier, Type type, Identifier name, Expression body) {
		return new ConcreteFieldDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, specifier, type, name, body);
	}
	
	public static MethodDecl AbstractMethodDecl(Type type, Identifier name, List<MetaArg> metaArgs, List<Arg> args, List<Arg> env) {
		return new AbstractMethodDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, type, name, metaArgs, args, env);
	}

	public static MethodDecl AbstractMethodDecl(List<Modifier> modifiers, Type type, Identifier name, List<MetaArg> metaArgs, List<Arg> args, List<Arg> env) {
		return new AbstractMethodDecl(ASTNode.DEFAULT_TOKEN, modifiers, type, name, metaArgs, args, env);
	}
	
	public static MethodDecl ConcreteMethodDecl(Type type, Identifier name, List<MetaArg> metaArgs, List<Arg> args, List<Arg> env, Expression body) {
		return new ConcreteMethodDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, type, name, metaArgs, args, env, body);
	}
	
	public static Modifier OVERRIDE() {
		return new Modifier(ASTNode.DEFAULT_TOKEN, Modifier.ModifierKind.OVERRIDE);
	}
	
	public static Modifier REQUIRES() {
		return new Modifier(ASTNode.DEFAULT_TOKEN, Modifier.ModifierKind.REQUIRES);
	}
	
	public static StateValDecl AbstractStateValDecl(Identifier name, List<MetaArg> metaArgsSpec) {
		return new AbstractStateValDecl(ASTNode.DEFAULT_TOKEN, Modifier.EMPTY, name, metaArgsSpec);
	}

	public static StateValDecl ConcreteStateValDecl(Identifier name, List<MetaArg> metaArgsSpec, StateExpression stateBinding) {
		return new ConcreteStateValDecl(ASTNode.DEFAULT_TOKEN, Modifier.EMPTY, name, metaArgsSpec, stateBinding);
	}
	
	public static MetaArg TypeArg(Identifier id, QualifiedIdentifier caseof) {
		return new TypeArg(ASTNode.DEFAULT_TOKEN, id, caseof);
	}
	
	public static StateDecl AbstractStateDecl(Identifier name, List<MetaArg> metaArgsSpec, QualifiedIdentifier caseOf, List<Expression> metaCaseOfArgs) {
		return new AbstractStateDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, name, metaArgsSpec, caseOf, metaCaseOfArgs);
	}
	
	
	public static StateDecl ConcreteStateDecl(Identifier name, List<MetaArg> metaArgsSpec, QualifiedIdentifier caseOf, List<Expression> metaCaseOfArgs, StateExpression statebinding) {
		return new ConcreteStateDecl(ASTNode.DEFAULT_TOKEN, Collections.EMPTY_LIST, name, metaArgsSpec, caseOf, metaCaseOfArgs, statebinding);
	}
	
	public static Import Import(QualifiedIdentifier qi, boolean star) {
		return new Import(ASTNode.DEFAULT_TOKEN, qi, star);
	}
	
	public static QualifiedIdentifier Package(String ...names) {
		return QualifiedIdentifier(names);
	}
	
	public static CompilationUnit CompilationUnit(QualifiedIdentifier packageName, List<Import> imports, List<Decl> decls) {
		return new CompilationUnit(packageName, imports, decls);
	}
	
	// types 
	public static ArgSpec ArgSpec(Type preType) {
		return new ArgSpec(null, preType, Type.EMPTY);
	}

	public static ArgSpec ArgSpec(Type preType, Type postType) {
		return new ArgSpec(null, preType, postType);
	}

	public static NominalObjectType NominalObjectType(Permission perm, QualifiedIdentifier qi) {
		NominalObjectType type = new NominalObjectType(null, perm, 
				qi,
				new ArrayList<MetaType>());
		return type;
	}
	
	public static NominalObjectType NominalObjectType(QualifiedIdentifier qi) {
		NominalObjectType type = new NominalObjectType(null, Permission.EMPTY, 
				qi,
				new ArrayList<MetaType>());
		return type;
	}

	public static LambdaType LambdaType(Type returnType) {
		return LambdaType(new ArrayList<ArgSpec>(), returnType);
	}

	public static LambdaType LambdaType(List<ArgSpec>argsSpec, Type returnType) {
		return new LambdaType(null,new ArrayList<MetaType>(),argsSpec,
				new ArrayList<Arg>(), returnType);
	}
}
