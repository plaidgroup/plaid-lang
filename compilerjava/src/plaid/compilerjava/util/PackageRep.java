package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackageRep {

	private String packageSuffix = "";
	private List<PackageRep> nestedPackages = new ArrayList<PackageRep>();
	private List<MemberRep> packageMembers = new ArrayList<MemberRep>();
	
	//simple cache for members
	private String cachedMemberPath = null;
	private MemberRep cachedMemberValue = null;
	
	//simple cache for packages
	private String cachedPackage = null;
	private PackageRep cachedPackageValue = null;
	
	
	public PackageRep(String suffix) {
		packageSuffix = suffix;
	}
	
	public String getSuffix() {
		return packageSuffix;
	}
	
	public List<MemberRep> getPackageMembers() {
		return Collections.unmodifiableList(packageMembers);
	}
	
	public List<PackageRep> getNestedPackages() {
		return Collections.unmodifiableList(nestedPackages);
	}
	
	public boolean packageExists(String path) {
		PackageRep p = findPackage(path);
		if (p == null) return false;
		else return true;
	}
	
	public boolean memberExists(String path) {
		MemberRep toRet = lookupMember(path);
		if (toRet == null) return false;
		else return true;
	}
	
	public boolean memberExists(String thePackage, String member) {
		return memberExists(thePackage + "." + member);
	}
	
	public MemberRep lookupMember(String path) {
		if (path.equals(cachedMemberPath)) return cachedMemberValue;
		MemberRep toRet = lookupHelper(path);
		if (toRet != null) {
			cachedMemberPath = path;
			cachedMemberValue = toRet;
		}
		return toRet;
	}
	
	public MemberRep lookupMember(String thePackage, String memberName) {
		return lookupMember(thePackage + "." + memberName);
	}
	
	private MemberRep lookupHelper(String path) {
		int nextDot = path.indexOf(".");
		if (nextDot >= 0) {  //need to recurse further
			String next = path.substring(0,nextDot);
			for (PackageRep p : nestedPackages) 
				if (next.equals(p.getSuffix())) 
					return p.lookupHelper(path.substring(nextDot + 1));
		} else { //find a member
			for (MemberRep m : packageMembers) {
				if (path.equals(m.getName())) return m;  //TODO : is this right? (recompilation stuff)
			}
		}
		return null; //lookup failed
	}
	
	public List<MemberRep> getPackageMembers(String thePackage) {
		PackageRep p = findPackage(thePackage);
		if (p != null) return p.getPackageMembers();
		else return null; //package not found
	}
	
//	public void addMember(String path) {
//		int lastDot = path.lastIndexOf(".");
//		addMember(path.substring(0,lastDot), path.substring(lastDot + 1));
//	}
	
	public void addMember(String thePackage, MemberRep member) {
		if (thePackage == null || thePackage.length() == 0) { //add member here
			MemberRep existing = null;
			for (MemberRep r : packageMembers) if (r.getName().equals(member.getName())) existing = r;
			if (existing != null) {
				if (existing.isRecompiling()) {
					packageMembers.remove(existing);
					packageMembers.add(member);
				}
				else throw new RuntimeException("Attempt to overwrite member " + member.getName());  //TODO : cannot currently give good error message - don't have package
			} else {
				packageMembers.add(member);
			}
		} else {
		
			//what package are we currently looking for and what is coming next
			int nextDot = thePackage.indexOf(".");
			String next;
			String rest;
			if (nextDot >= 0) {
				next = thePackage.substring(0,nextDot);
				rest = thePackage.substring(nextDot + 1);
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
				addTo = new PackageRep(next);
				nestedPackages.add(addTo);
			}
			
			addTo.addMember(rest, member); //recursively add member to rest package				
		}
	}
	
//	public boolean removeMember(String thePackage, String member) {
//		PackageRep p = findPackage(thePackage);
//		MemberRep remove = null;
//		for (MemberRep m : p.packageMembers) {
//			if (m.getName().equals(member)) remove = m;
//		}
//		if (member == null) return false;
//		else {
//			packageMembers.remove(remove);
//			return true;
//		}
//		
//	}
	
	private PackageRep findPackage(String thePackage) {
		if (thePackage.equals(cachedPackage)) return cachedPackageValue;
		else {
			PackageRep toRet = findPackageHelper(thePackage);
			if (toRet != null) {
				cachedPackage = thePackage;
				cachedPackageValue = toRet;
			}
			return toRet;	
		}
	}
	
	private PackageRep findPackageHelper(String thePackage) {		
		int nextDot = thePackage.indexOf(".");
		if (nextDot >= 0) {  //need to recurse further
			String next = thePackage.substring(0,nextDot);
			for (PackageRep p : nestedPackages) 
				if (next.equals(p.getSuffix())) 
					return p.findPackageHelper(thePackage.substring(nextDot + 1));
		} else { // find the package and return its members
			for (PackageRep p : nestedPackages)
				if (thePackage.equals(p.getSuffix()))
					return p;
		}
		return null;  //package not found
	}
	
	public String toString() {
		return packageSuffix;
	}
	
}
