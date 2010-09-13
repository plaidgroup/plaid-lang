package aeminium.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Permission {
	UNIQUE,
	IMMUTABLE,
	NONE;
	
	static Permission fromString(String s) {
		if (s.equals("U"))
			return UNIQUE;
		else if (s.equals("I"))
			return IMMUTABLE;
		else if (s.equals("N"))
			return NONE;
		else
			return null;
	}
	
	public String toString() {
		switch (this) {
		case UNIQUE:
			return "U.";
		case IMMUTABLE:
			return "I.";
		case NONE:
			return "N.";
		}
		
		return null;
	}
}

class Variable {	
	private final String name;
	private final Permission perm;
	
	public Variable(String s) {
		String[] p = s.split("\\.");
		name = p[1];
		perm = Permission.fromString(p[0]);
	}
	
	public Variable(String name, Permission perm) {
		this.name = name;
		this.perm = perm;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Permission getPermission() {
		return this.perm;
	}
	
	@Override
	public String toString() {
		return this.perm.toString() + this.name;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Variable))
			return false;
		
		Variable ov = (Variable) o;
		return this.name.equals(ov.getName());
	}
}

abstract class Node {
	private int num;
	public Node(int num) {
		this.num = num;
	}
	
	public abstract List<Variable> getReads();
	public abstract List<Variable> getWrites();
	
	public int getNum() {
		return this.num;
	}
}

class Assignment extends Node {
	public static final Pattern pattern = Pattern.compile("Let\\(([IUN]\\.\\w)=([IUN]\\.\\w)\\)");
	
	private final Variable to;
	private final Variable from;
	
	public Assignment(int num, Variable to, Variable from) {
		super(num);
		this.to = to;
		this.from = from;
	}
	
	public List<Variable> getReads() {
		return Arrays.asList(from);
	}
	
	public List<Variable> getWrites() {
		return Arrays.asList(to);
	}
	
	@Override
	public String toString() {
		return "Let " + this.to + " = " + this.from;
	}
}

class Call extends Node {
	public static final Pattern pattern = Pattern.compile("Call\\(([IUN]\\.\\w)(,[IUN]\\.\\w)*\\)");
	
	private final List<Variable> args;
	private final List<Variable> uniArgs;
	private final List<Variable> immArgs;
	
	public Call(int num, List<Variable> args) {
		super(num);
		uniArgs = new ArrayList<Variable>();
		immArgs = new ArrayList<Variable>();
		this.args = args;
		
		for (Variable v : args) {
			if (v.getPermission() == Permission.UNIQUE)
				uniArgs.add(v);
			else
				immArgs.add(v);
		}
	}
	
	public List<Variable> getReads() {
		return immArgs;
	}
	
	public List<Variable> getWrites() {
		return uniArgs;
	}

	@Override
	public String toString() {
		String out = "";
		for (Variable v : this.args)
			out += v + ", ";
		return "Call(" + out.substring(0, out.length() - 2) + ")";
	}
}

class Split extends Node {
	public static final Pattern pattern = Pattern.compile("Split\\(([IUN]\\.\\w)=>([IUN]\\.\\w)/([IUN]\\.\\w)\\)");
	
	private final Variable in;
	private final Variable out1, out2;
	
	public Split(int num, Variable in, Variable out1, Variable out2) {
		super(num);
		this.in = in;
		this.out1 = out1;
		this.out2 = out2;
	}

	public List<Variable> getReads() {
		return new ArrayList<Variable>();
	}
	
	public List<Variable> getWrites() {
		return Arrays.asList(in, out1, out2);
	}

	@Override
	public String toString() {
		return "Split " + this.in + " => " + this.out1 + "/" + this.out2;
	}
}

class Join extends Node {
	public static final Pattern pattern = Pattern.compile("Join\\(([IUN]\\.\\w)/([IUN]\\.\\w)=>([IUN]\\.\\w)/([IUN]\\.\\w)\\)");
	
	private final Variable in1, in2;
	private final Variable out1, out2;

	public Join(int num, Variable in1, Variable in2, Variable out1, Variable out2) {
		super(num);
		this.in1 = in1;
		this.in2 = in2;
		this.out1 = out1;
		this.out2 = out2;
	}
	
	public List<Variable> getReads() {
		return new ArrayList<Variable>();
	}
	
	public List<Variable> getWrites() {
		return Arrays.asList(in1, in2, out1, out2);
	}

	@Override
	public String toString() {
		return "Join " + this.in1 + "/" + this.in2 + " => " + this.out1 + "/" + this.out2;
	}
}

class Dependency {
	private final Node from;
	private final Node to;
	private final Variable variable;
	
	public Dependency(Node from, Node to, Variable variable) {
		this.from = from;
		this.to = to;
		this.variable = variable;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	public Variable getVariable() {
		return variable;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Dependency))
			return false;
		
		Dependency od = (Dependency) o;
		return this.from.equals(od.getFrom())
		       && this.to.equals(od.getTo())
		       && this.variable.equals(od.getVariable());
	}
}

public class DependencyTest {
	private static int nodeCounter = 0;
	
	public static Node createNode(final String s) {
		if (s.startsWith("Split")) {
			Matcher matcher = Split.pattern.matcher(s);
			if (matcher.find())
				return new Split(nodeCounter++, new Variable(matcher.group(1)), new Variable(matcher.group(2)), new Variable(matcher.group(3)));
		}
		else if (s.startsWith("Join")) {
			Matcher matcher = Join.pattern.matcher(s);
			if (matcher.find())
				return new Join(nodeCounter++, new Variable(matcher.group(1)), new Variable(matcher.group(2)), new Variable(matcher.group(3)), new Variable(matcher.group(4)));
		}
		else if (s.startsWith("Call")) {
			Matcher matcher = Call.pattern.matcher(s);
			if (matcher.find()) {
				List<Variable> args = new ArrayList<Variable>();
				args.add(new Variable(matcher.group(1)));
				for (int i = 2; i <= matcher.groupCount(); ++i)
					args.add(new Variable(matcher.group(i).substring(1)));
				return new Call(nodeCounter++, args);
			}
		}
		else if (s.startsWith("Let")) {
			Matcher matcher = Assignment.pattern.matcher(s);
			if (matcher.find())
				return new Assignment(nodeCounter++, new Variable(matcher.group(1)), new Variable(matcher.group(2)));
		}

		System.out.println("createNode: \"" + s + "\" doesn't match anything.");
		return null;
	}
	
	public static List<Node> buildExample() {
		final String example =
				"Split(U.x => I.x/I.x);"
			+	"Call(I.x,U.y);"
			+	"Split(U.y => I.y/I.y);"
			+	"Let(I.x=I.y);"
			+	"Let(I.z=I.y);";
		
		List<Node> nodes = new ArrayList<Node>();
		
		for (String n : example.replace(" ", "").split(";")) {
			Node x = createNode(n);
			System.out.println(x);
			nodes.add(x);
		}
		
		return nodes;
	}
	
	public static List<Dependency> extractDependencies(List<Node> program) {
		Map<Variable, Set<Node>> readers = new HashMap<Variable, Set<Node>>();
		Map<Variable, Node> writer = new HashMap<Variable, Node>();
		List<Dependency> deps = new ArrayList<Dependency>();
		
		for (Node n : program) {
			for (Variable readVar : n.getReads()) {
				Node prevWriter = writer.get(readVar);
				// Depend on writer (if there is one).
				if (prevWriter != null)
					deps.add(new Dependency(n, writer.get(readVar), readVar));
				
				// Add as reader.
				if (readers.get(readVar) == null)
					readers.put(readVar, new HashSet<Node>());
				readers.get(readVar).add(n);
			}
			
			for (Variable writeVar : n.getWrites()) {
				Set<Node> prevReaders = readers.get(writeVar);
				
				// Depend on previous readers (if there are any).
				if (prevReaders != null) {
					for (Node pr : prevReaders)
						deps.add(new Dependency(n, pr, writeVar));
				}
				else {
					Node prevWriter = writer.get(writeVar);
					if (prevWriter != null)
						deps.add(new Dependency(n, prevWriter, writeVar));
				}
				
				// Set as writer and clear list of readers.
				writer.put(writeVar, n);
				readers.put(writeVar, null);
			}
		}
		
		return deps;
	}
	
	public static void main(String[] args) {
		List<Node> example = buildExample();
		System.out.println();
		
		for (Dependency dep : extractDependencies(example)) {
			System.out.println(dep.getFrom().getNum() + " -> " + dep.getTo().getNum() + " with " + dep.getVariable());
		}
	}
}
