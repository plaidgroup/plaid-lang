package plaid.compilerjava.AST;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.QualifiedID;
import plaid.compilerjava.util.StateRep;
import plaid.runtime.PlaidConstants;
import plaid.runtime.annotations.RepresentsState;

public class StateDeclHelper {
	public StateDeclHelper() {
	}
	
	public List<ID> generateStateVars(QualifiedID qid, List<QualifiedID> imports, DeclList stateDef) {
		Set<ID> s = genStateVars(qid, imports, stateDef, null);
		List<ID> l = new ArrayList<ID>();
		l.addAll(s);
		return l;
	}

	public Set<ID> genStateVars(QualifiedID qid, List<QualifiedID> imports, State stateDef, QI caseOf) {
		Set<ID> stateVars = new HashSet<ID>();
		Stack<State> workList = new Stack<State>();
		workList.push(stateDef);
		workList.push(caseOf);
		
		//This seems the wrong way to do this...
		List<QualifiedID> statepath = new ArrayList<QualifiedID>();
		List<String> qidList = qid.getQidList();
		List<String> newQidList = new ArrayList<String>();
		newQidList.addAll(qidList);
		newQidList.add("*");
		QualifiedID declPackageQid = new QualifiedID(newQidList);
		statepath.add(declPackageQid);
		statepath.addAll(imports);
		
		State s;
		while(!workList.isEmpty()) {
			s = workList.pop();
			if (s instanceof DeclList) {
				List<Decl> decls = ((DeclList) s).getDecls();
				for (Decl decl : decls) {
					//System.out.println("Adding to state vars: " + decl.getName());
					stateVars.add(new ID(decl.getName()));
				}
			} else if (s instanceof With) {
				With w = (With) s;
				workList.push(w.getR1());
				workList.push(w.getR2());
			} else if (s instanceof QI) {
				
				loadStateMembers(((QI) s).toString(), statepath, stateVars);
			}
		}
		
		return stateVars;
	}

	@SuppressWarnings("unchecked")
	private void loadStateMembers(String stateName, List<QualifiedID> statepath, Set<ID> stateVars) {
		ClassLoader cl = this.getClass().getClassLoader();
		
		List<String> toLookup = new ArrayList<String>();
		toLookup.add(stateName);
		for (QualifiedID i : statepath) {
			String theImport = i.toString();
			if (theImport.endsWith(stateName)) toLookup.add(theImport);
			else if (theImport.endsWith("*")) toLookup.add(theImport.substring(0,theImport.length()-1) + stateName);
		}
		
		Class<Object> obj = null;
		for (String lkup : toLookup) {
		
			String names[] = {lkup + PlaidConstants.ID_SUFFIX, lkup};
			for ( String current : names) {
				try {
					obj = (Class<Object>) cl.loadClass(current);
					for (Annotation a : obj.getAnnotations()) {
						if (a instanceof RepresentsState) {
							String memberString = ((RepresentsState) a).jsonRep();
							StateRep rep = StateRep.parseJSONObject((JSONObject)JSONValue.parse(memberString));
							for (MemberRep member : rep.getMembers()) {
								stateVars.add(new ID(member.getName()));
							}
						}
					}
					return; //once we've found the class, we're done
				} catch (ClassNotFoundException e) {
					// If there is no classfile then we need to keep searching
				}
			}
		}
		
		
	}
}
