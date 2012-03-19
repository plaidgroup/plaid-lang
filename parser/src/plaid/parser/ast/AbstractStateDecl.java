package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class AbstractStateDecl extends StateDecl {

	public AbstractStateDecl(Token t, List<Annotation> annotations,List<Modifier> modifiers,
			Identifier name, List<StaticArg> StaticArgsSpec,
			QualifiedIdentifier caseOf, List<Expr> metaCaseOfArgs) {
		super(t, annotations, modifiers, name, StaticArgsSpec, caseOf, metaCaseOfArgs);
	}

}
