package plaid.parser.test.astfactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import plaid.parser.ast.*;

public class ASTFactory {
	public static ArgSpec ArgSpec(Type preType) {
		return new ArgSpec(null, preType, Type.EMPTY);
	}
	public static ArgSpec ArgSpec(Type preType, Type postType) {
		return new ArgSpec(null, preType, postType);
	}
	public static NominalObjectType NominalObjectType(QualifiedIdentifier qi) {
		NominalObjectType type = new NominalObjectType(null, Permission.EMPTY, 
				qi,
				new ArrayList<MetaType>());
		return type;
	}
	
	public static QualifiedIdentifier QualifiedIdentifier(String... s) {
		List<Identifier> ids = new ArrayList<Identifier>();
		for (int i = 0; i < s.length; i++) {
			ids.add(new Identifier(null, s[i]));
		}
		return new QualifiedIdentifier(null, ids);
	}
	
	public static LambdaType LambdaType(Type returnType) {
		return LambdaType(new ArrayList<ArgSpec>(), returnType);
	}
	public static LambdaType LambdaType(List<ArgSpec>argsSpec, Type returnType) {
		return new LambdaType(null,new ArrayList<MetaType>(),argsSpec,
				new ArrayList<Arg>(), returnType);
	}
}
