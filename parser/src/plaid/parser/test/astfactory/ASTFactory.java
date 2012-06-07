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
import plaid.parser.ast.Annotation;
import plaid.parser.ast.Application;
import plaid.parser.ast.Arg;
import plaid.parser.ast.ArgSpec;
import plaid.parser.ast.ArgumentExpr;
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
import plaid.parser.ast.ConcreteType;
import plaid.parser.ast.Decl;
import plaid.parser.ast.DeclList;
import plaid.parser.ast.DeclOrStateOp;
import plaid.parser.ast.DefaultCase;
import plaid.parser.ast.Dereference;
import plaid.parser.ast.DestructiveDereference;
import plaid.parser.ast.DoubleLiteral;
import plaid.parser.ast.Expr;
import plaid.parser.ast.FieldDecl;
import plaid.parser.ast.Freeze;
import plaid.parser.ast.GroupArg;
import plaid.parser.ast.GroupDecl;
import plaid.parser.ast.GroupPermission;
import plaid.parser.ast.Identifier;
import plaid.parser.ast.ImmutableModifier;
import plaid.parser.ast.ImmutablePermission;
import plaid.parser.ast.Import;
import plaid.parser.ast.InfixOperatorExpr;
import plaid.parser.ast.IntLiteral;
import plaid.parser.ast.Lambda;
import plaid.parser.ast.LambdaStructure;
import plaid.parser.ast.Match;
import plaid.parser.ast.MethodDecl;
import plaid.parser.ast.Modifier;
import plaid.parser.ast.NewInstance;
import plaid.parser.ast.NominalStructure;
import plaid.parser.ast.NonePermission;
import plaid.parser.ast.OptionStructure;
import plaid.parser.ast.OverrideModifier;
import plaid.parser.ast.PatternCase;
import plaid.parser.ast.Permission;
import plaid.parser.ast.ProtectedGroupPermission;
import plaid.parser.ast.QualifiedIdentifier;
import plaid.parser.ast.Replace;
import plaid.parser.ast.RequiresModifier;
import plaid.parser.ast.SharedPermission;
import plaid.parser.ast.Specifier;
import plaid.parser.ast.SplitBlock;
import plaid.parser.ast.StateChange;
import plaid.parser.ast.StateDecl;
import plaid.parser.ast.StateExpr;
import plaid.parser.ast.StateOpRemove;
import plaid.parser.ast.StateOpRename;
import plaid.parser.ast.StateRef;
import plaid.parser.ast.StateValDecl;
import plaid.parser.ast.StaticArg;
import plaid.parser.ast.StaticType;
import plaid.parser.ast.Stmt;
import plaid.parser.ast.StringLiteral;
import plaid.parser.ast.Type;
import plaid.parser.ast.TypeArg;
import plaid.parser.ast.UnaryOperatorExpr;
import plaid.parser.ast.UniquePermission;
import plaid.parser.ast.UnpackInnerGroups;
import plaid.parser.ast.ValSpecifier;
import plaid.parser.ast.VarDecl;
import plaid.parser.ast.VarSpecifier;
import plaid.parser.ast.With;

public class ASTFactory {
	public static FieldDecl AbstractFieldDecl(Specifier specifier, Type type, Identifier name) {
		return new AbstractFieldDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, specifier, type, name);
	}

	public static MethodDecl AbstractMethodDecl(List<Annotation> annotations, List<Modifier> modifiers, Type type, Identifier name, List<StaticArg> metaArgs, List<Arg> args, List<Arg> env) {
		return new AbstractMethodDecl(ASTNode.DEFAULT_TOKEN, annotations, modifiers, type, name, metaArgs, args, env);
	}
	
	
	public static MethodDecl AbstractMethodDecl(List<Modifier> modifiers, Type type, Identifier name, List<StaticArg> metaArgs, List<Arg> args, List<Arg> env) {
		return new AbstractMethodDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, modifiers, type, name, metaArgs, args, env);
	}

	public static MethodDecl AbstractMethodDecl(Type type, Identifier name, List<StaticArg> metaArgs, List<Arg> args, List<Arg> env) {
		return new AbstractMethodDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, type, name, metaArgs, args, env);
	}
	
	public static StateDecl AbstractStateDecl(Identifier name, List<StaticArg> metaArgsSpec, QualifiedIdentifier caseOf, List<Expr> metaCaseOfArgs) {
		return new AbstractStateDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, name, metaArgsSpec, caseOf, metaCaseOfArgs);
	}
	
	public static StateValDecl AbstractStateValDecl(Identifier name, List<StaticArg> metaArgsSpec) {
		return new AbstractStateValDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, name, metaArgsSpec);
	}
	
	public static StateValDecl AbstractStateValDecl(Identifier name, Modifier m, List<StaticArg> metaArgsSpec) {
		return new AbstractStateValDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Collections.singletonList(m), name, metaArgsSpec);
	}
	
	public static Application Application(Expr left, Expr right) {
		return new Application(ASTNode.DEFAULT_TOKEN, left, null, right);
	}
	
	public static Application Application(Expr left, List<Expr> metaArgs, Expr right) {
		return new Application(ASTNode.DEFAULT_TOKEN, left, metaArgs, right);
	}
	
	public static Arg Arg(ArgSpec argSpec, Identifier id) {
		return new Arg(ASTNode.DEFAULT_TOKEN, argSpec, id);
	}

	public static ArgSpec ArgSpec(Type preType) {
		return new ArgSpec(null, preType, Type.EMPTY);
	}
	
	public static ArgSpec ArgSpec(Type preType, Type postType) {
		return new ArgSpec(null, preType, postType);
	}
	
	public static ArgumentExpr ArgumentExpr(Expr ...es) {
		return new ArgumentExpr(ASTNode.DEFAULT_TOKEN, Arrays.asList(es));
	}
	
	public static Assignment Assignment(Expr target, Identifier field, Expr value) {
		return new Assignment(ASTNode.DEFAULT_TOKEN, target, field, value);
	}
	
	public static AtomicBlock AtomicBlock(BlockExpr e, Expr ...datagroups) {
		return new AtomicBlock(ASTNode.DEFAULT_TOKEN,  Arrays.asList(datagroups), e);
	}
	
	public static BlockExpr BlockExpr(Stmt ...args) {
		return new BlockExpr(ASTNode.DEFAULT_TOKEN, Arrays.asList(args));
	}
	
	public static Cast Cast(Expr e, Type type) {
		return new Cast(ASTNode.DEFAULT_TOKEN, e, type);
	}

	public static CompilationUnit CompilationUnit(QualifiedIdentifier packageName, List<Import> imports, List<Decl> decls) {
		return new CompilationUnit(packageName, imports, decls);
	}
	
	public static FieldDecl ConcreteFieldDecl(Specifier specifier, Type type, Identifier name, Expr body) {
		return new ConcreteFieldDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, specifier, type, name, body);
	}
	
	public static MethodDecl ConcreteMethodDecl(Type type, Identifier name, List<StaticArg> metaArgs, List<Arg> args, List<Arg> env, BlockExpr body) {
		return new ConcreteMethodDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, type, name, metaArgs, args, env, body);
	}
	
	public static StateDecl ConcreteStateDecl(Modifier m, Identifier name, List<StaticArg> metaArgsSpec, QualifiedIdentifier caseOf, List<Expr> metaCaseOfArgs, StateExpr statebinding) {
		return new ConcreteStateDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Collections.singletonList(m), name, metaArgsSpec, caseOf, metaCaseOfArgs, statebinding);
	}
	
	public static StateDecl ConcreteStateDecl(Identifier name, List<StaticArg> metaArgsSpec, QualifiedIdentifier caseOf, List<Expr> metaCaseOfArgs, StateExpr statebinding) {
		return new ConcreteStateDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, name, metaArgsSpec, caseOf, metaCaseOfArgs, statebinding);
	}
	
	public static StateValDecl ConcreteStateValDecl(Identifier name, List<StaticArg> metaArgsSpec, StateExpr stateBinding) {
		return new ConcreteStateValDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, name, metaArgsSpec, stateBinding);
	}
	
	public static DeclList DeclList(Decl ...decls) {
		return new DeclList(ASTNode.DEFAULT_TOKEN, Arrays.asList(decls));
	}
	
	public static Case DefaultCase(BlockExpr e) {
		return new DefaultCase(ASTNode.DEFAULT_TOKEN, e);
	}
	
	public static Dereference Dereference(Expr left, Identifier right) {
		return new Dereference(ASTNode.DEFAULT_TOKEN, left, right);
	}
	
	public static DestructiveDereference DestructiveDereference(Expr left, Identifier right) {
		return new DestructiveDereference(ASTNode.DEFAULT_TOKEN, left, right);
	}
	
	public static DoubleLiteral DoubleLiteral(Double value) {
		return new DoubleLiteral(ASTNode.DEFAULT_TOKEN, value);
	}
	
	public static Freeze Freeze(Expr e) {
		return new Freeze(ASTNode.DEFAULT_TOKEN, e);
	}
	
	public static StaticArg GroupArg(GroupPermission gp, Identifier name) {
		return new GroupArg(ASTNode.DEFAULT_TOKEN, gp, name);
	}
	
	public static GroupDecl GroupDecl(Identifier name) {
		return new GroupDecl(ASTNode.DEFAULT_TOKEN, Annotation.EMPTY, Modifier.EMPTY, name);
	}
	
	public static Identifier Identifier(String value) {
		return new Identifier(ASTNode.DEFAULT_TOKEN, value);
	}
	
	public static Permission  Immutable() {
		return new ImmutablePermission(ASTNode.DEFAULT_TOKEN);
	}
	
	public static Import Import(QualifiedIdentifier qi, boolean star) {
		return new Import(ASTNode.DEFAULT_TOKEN, qi, star);
	}
	
	public static InfixOperatorExpr InfixOperator(Expr left, Identifier op, Expr right) {
		return new InfixOperatorExpr(ASTNode.DEFAULT_TOKEN, left, op, right);
	}
	
	public static IntLiteral IntLiteral(int value) {
		return new IntLiteral(ASTNode.DEFAULT_TOKEN, value);
	}

	public static Lambda Lambda(List<StaticArg> metaArgsSpec, List<Arg> args, List<Arg> env, Expr body) {
		return new Lambda(ASTNode.DEFAULT_TOKEN, metaArgsSpec, args, env, body);
	}
	
	public static Type LambdaType(List<ArgSpec>argsSpec, Type returnType) {
		return new ConcreteType(ASTNode.DEFAULT_TOKEN, Permission.EMPTY,
				new LambdaStructure(null,new ArrayList<StaticType>(),argsSpec,
				new ArrayList<Arg>(), returnType));
	}

	public static Type LambdaType(Type returnType) {
		return LambdaType(new ArrayList<ArgSpec>(), returnType);
	}
	
	public static Match Match(Expr e, Case ...cases) {
		return new Match(ASTNode.DEFAULT_TOKEN,  e, Arrays.asList(cases));
	}

	public static List<Expr> MetaArgs(Expr ...args) {
		return Arrays.asList(args);
	}

	public static NewInstance NewInstance(StateExpr state) {
		return new NewInstance(ASTNode.DEFAULT_TOKEN, state);
	}

	public static Type NominalObjectType(Permission perm, QualifiedIdentifier qi) {
		NominalStructure struct = new NominalStructure(ASTNode.DEFAULT_TOKEN, 
				qi,
				new ArrayList<Expr>());
		return new ConcreteType(ASTNode.DEFAULT_TOKEN, perm, struct);
	}
	
	public static Type NominalObjectType(QualifiedIdentifier qi) {
		return NominalObjectType(Permission.EMPTY, qi);
	}
	
	public static Type OptionType(Permission perm, QualifiedIdentifier qi) {
		NominalStructure struct = new NominalStructure(ASTNode.DEFAULT_TOKEN, 
				qi,
				new ArrayList<Expr>());
		return new ConcreteType(ASTNode.DEFAULT_TOKEN, perm, 
								new OptionStructure(ASTNode.DEFAULT_TOKEN, struct));
	}
	
	public static Type OptionType(QualifiedIdentifier qi) {
		return OptionType(Permission.EMPTY, qi);	
	}
	
	public static Permission  None() {
		return new NonePermission(ASTNode.DEFAULT_TOKEN);
	}
	
	public static Modifier OVERRIDE() {
		return new OverrideModifier(ASTNode.DEFAULT_TOKEN);
	}
	
	public static QualifiedIdentifier Package(String ...names) {
		return QualifiedIdentifier(names);
	}
	
	public static Annotation Annotation(String name) {
		return new Annotation(ASTNode.DEFAULT_TOKEN, Identifier(name));
	}
	
	public static Case PatternCase(QualifiedIdentifier qi, BlockExpr e) {
		return new PatternCase(ASTNode.DEFAULT_TOKEN, qi, new ArrayList(), e);
	}
	
	public static GroupPermission Protected() {
		return new ProtectedGroupPermission(ASTNode.DEFAULT_TOKEN);
	}
	
	public static QualifiedIdentifier QualifiedIdentifier(String... s) {
		List<Identifier> ids = new ArrayList<Identifier>();
		for (int i = 0; i < s.length; i++) {
			ids.add(new Identifier(null, s[i]));
		}
		return new QualifiedIdentifier(null, ids);
	}

	public static Replace Replace(Expr e, StateExpr state) {
		return new Replace(ASTNode.DEFAULT_TOKEN, e, state);
	}
	
	public static Modifier REQUIRES() {
		return new RequiresModifier(ASTNode.DEFAULT_TOKEN);
	}
	
	public static Modifier IMMUTABLE() {
		return new ImmutableModifier(ASTNode.DEFAULT_TOKEN);
	}
	
	public static Permission  Shared(Expr datagroup) {
		return new SharedPermission(ASTNode.DEFAULT_TOKEN, datagroup);
	}
	
	public static SplitBlock SplitBlock(BlockExpr e, Expr ...datagroups) {
		return new SplitBlock(ASTNode.DEFAULT_TOKEN,  Arrays.asList(datagroups), e);
	}
	
	public static StateChange StateChange(Expr e, StateExpr state) {
		return new StateChange(ASTNode.DEFAULT_TOKEN, e, state);
	}

	public static StateOpRemove StateOpRemove(Identifier id) {
		return new StateOpRemove(ASTNode.DEFAULT_TOKEN, id);
	}
	
	public static StateOpRename StateOpRename(Identifier from, Identifier to) {
		return new StateOpRename(ASTNode.DEFAULT_TOKEN, from, to );
	}
	
	public static StateRef StateRef(Expr e) {
		return new StateRef(ASTNode.DEFAULT_TOKEN, e, new ArrayList<Expr>(), new ArrayList<DeclOrStateOp>());
	}
	
	
	public static StateRef StateRef(Expr e, DeclOrStateOp ...dops) {
		return new StateRef(ASTNode.DEFAULT_TOKEN, e, new ArrayList<Expr>(),  Arrays.asList(dops));
	}
	
	public static StringLiteral StringLiteral(String value) {
		return new StringLiteral(ASTNode.DEFAULT_TOKEN, value);
	}
	
	public static StaticArg TypeArg(Identifier id, QualifiedIdentifier caseof) {
		return new TypeArg(ASTNode.DEFAULT_TOKEN, id, caseof);
	}
	
	public static UnaryOperatorExpr UnaryOperator(Identifier op, Expr e) {
		return new UnaryOperatorExpr(ASTNode.DEFAULT_TOKEN, op, e);
	}
	
	public static Permission  Unique() {
		return new UniquePermission(ASTNode.DEFAULT_TOKEN);
	}

	public static UnpackInnerGroups UnpackInnerGroups(Expr e) {
		return new UnpackInnerGroups(ASTNode.DEFAULT_TOKEN, e);
	}

	public static Specifier Val() {
		return new ValSpecifier(ASTNode.DEFAULT_TOKEN);
	}
	
	public static Specifier Var() {
		return new VarSpecifier(ASTNode.DEFAULT_TOKEN);
	}

	public static VarDecl VarDecl(Specifier specifier, Type type, Identifier field, Expr value) {
		return new VarDecl(ASTNode.DEFAULT_TOKEN, specifier, type, field, value);
	}

	public static With With(StateExpr p1, StateExpr p2) {
		return new With(ASTNode.DEFAULT_TOKEN, p1, p2);
	}
}
