package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackageRep {

	private String packageSuffix = "";
	private List<PackageRep> nestedPackages = new ArrayList<PackageRep>();
	private List<MemberRep> packageMembers = new ArrayList<MemberRep>();
	
	public PackageRep(String suffix) {
		packageSuffix = suffix;
	}
	
	public String getSuffix() {
		return packageSuffix;
	}
	
	public List<MemberRep> getPackageMembers() {
		return Collections.unmodifiableList(packageMembers);
	}
	
	public MemberRep lookup(String path) {
		int nextDot = path.indexOf(".");
		if (nextDot >= 0) {  //need to recurse further
			String next = path.substring(0,nextDot);
			for (PackageRep p : nestedPackages) 
				if (next.equals(p.getSuffix())) 
					return p.lookup(path.substring(nextDot));
		} else { //find a member
			for (MemberRep m : packageMembers) {
				if (path.equals(m.getName())) return m;
			}
		}
		return null; //lookup failed
	}
	
	public List<MemberRep> getPackageMembers(String thePackage) {
		int nextDot = thePackage.indexOf(".");
		if (nextDot >= 0) {  //need to recurse further
			String next = thePackage.substring(0,nextDot);
			for (PackageRep p : nestedPackages) 
				if (next.equals(p.getSuffix())) 
					return p.getPackageMembers(thePackage.substring(nextDot));
		} else { // find the package and return its members
			for (PackageRep p : nestedPackages)
				if (thePackage.equals(p.getSuffix()))
					return getPackageMembers();
		}
		return null;  //package not found
	}
	
	public void addMember(String thePackage, MemberRep member) {
		if (thePackage == null) { //add member here
			packageMembers.add(member);
		} else {
		
			//what package are we currently looking for and what is coming next
			int nextDot = thePackage.indexOf(".");
			String next;
			String rest;
			if (nextDot >= 0) {
				next = thePackage.substring(0,nextDot);
				rest = thePackage.substring(nextDot);
			} else {
				next = thePackage;
				rest = null;
			}
			
			//What package are we adding to (existing or a new one)
			PackageRep addTo = null;
			for (PackageRep p : nestedPackages) {
				if (next.equals(p.getSuffix())) addTo = p; //found an existing package	
			}
			if (addTo == null) { //package not found - create it 
				nestedPackages.add(addTo);
			}
			
			addTo.addMember(rest, member); //recursively add member to rest package				
		}
	}
	
	
}
