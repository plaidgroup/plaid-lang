package plaid.fastruntime.util.interfacegen.CodeObjects;

public class StaticBlock implements CodeObject {
	private String contents;
	
	public StaticBlock(String contents) {
		this.contents = contents;
	}
	@Override
	public String getGeneratedCode(CodeGenerator generator) {
		String indent = generator.getIndentString();
		return indent+"static {\n\t"+indent+
				contents.replace("\n", "\n"+indent+"\t")+"\n"+indent+"}\n";
	}

}
