package plaid.fastruntime.util.interfacegen;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.util.interfacegen.CodeObjects.ClassObject;
import plaid.fastruntime.util.interfacegen.CodeObjects.CodeGenerator;
import plaid.fastruntime.util.interfacegen.CodeObjects.CodeObject;
import plaid.fastruntime.util.interfacegen.CodeObjects.PlaidMethodInfo;

//Must be top-level
public class JavaPlaidFile implements CodeGenerator {
	private ArrayList<String> dependencies = new ArrayList<String>();
	private ClassObject root;
	
	private String generatedDestDir = "./";
	
    private static String generateInterfaceForMethod(PlaidMethodInfo method, String outputDir, String outputNamespace) {
        StringBuilder sb = new StringBuilder();
        sb.append("package plaid.generated;\n\n");
        sb.append("import plaid.fastruntime.PlaidObject;\n");
        
        sb.append("public interface "+
                NamingConventions.getGeneratedInterfaceSimpleName(method.getName(), method.getNArgs())+"{\n\n");
        sb.append("\tPlaidObject "+method.getName());
        sb.append("(");
        sb.append("PlaidObject receiver");
        for (int i = 0; i < method.getNArgs(); i++) {
            sb.append(", PlaidObject arg"+i);
        }
        sb.append(");\n");
        sb.append("}");
		
		String generatedInterface = sb.toString();
		File outputFile = new File(new File(outputDir), 
				NamingConventions.getGeneratedInterfaceSimpleName(method.getName(), 
						method.getNArgs())+".java");
		if (!(outputFile.exists())) {
			try {
				FileOutputStream output = new FileOutputStream(outputFile);
				output.write(generatedInterface.getBytes("UTF-8"));
				output.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		return NamingConventions.getGeneratedInterfaceName(method.getName(), 
				method.getNArgs());
    }
	
    public String addPlaidMethodInterface(PlaidMethodInfo info) {
    	return generateInterfaceForMethod(info, generatedDestDir, root.getNamespace());
    }
	
	public JavaPlaidFile(ClassObject root, String generatedDestDir) { 
		this.root = root; 
		this.generatedDestDir = generatedDestDir;
	}
	
	public String getCode() {
		StringBuilder output = new StringBuilder();
		
		String code = root.getGeneratedCode(this);
		
		output.append("package "+root.getNamespace()+";\n");
		
		for (String dependency : dependencies) 
			output.append("import "+dependency+";\n");
		
		output.append(code);
		
		return output.toString();
	}
	
	public CodeObject getElement() {
		return root;
	}

	@Override
	public void addDependency(String dependencyName) {
		if (!dependencies.contains(dependencyName))
			dependencies.add(dependencyName);
		
	}

	@Override
	public String getIndentString() {
		return "";
	}
}
