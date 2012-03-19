package plaid.demo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.Util;
import plaid.runtime.models.map.PlaidJavaObjectMap;

public class PlaidCellProxy implements Cell<PlaidCellProxy> {

	private final PlaidObject rep;
	
	public PlaidCellProxy(PlaidObject rep) {
		this.rep = rep;
	}
	
	@Override
	public void toggle() {
		PlaidMethod toggleMethod = (PlaidMethod)rep.getMember("toggle").getValue();
		Util.call(toggleMethod, Util.unit());
	}

	@Override
	public void explore(List<PlaidCellProxy> neighbors) {
		PlaidMemberDef exploreMember = rep.getMember("explore");
		if(exploreMember == null) {
			System.out.println("Uh oh ... this object is broken " + rep.toString());
			throw new RuntimeException("Explore method is undefined");			
		}
		PlaidMethod nextMethod = (PlaidMethod) exploreMember.getValue();
		ArrayList<PlaidObject> neighborPlaidObjects = new ArrayList<PlaidObject>();
		for(PlaidCellProxy neighbor : neighbors) {
			neighborPlaidObjects.add(neighbor.rep);
		}
		Util.call(nextMethod, new PlaidJavaObjectMap(neighborPlaidObjects));
	}
	
	@Override
	public void change() {
		PlaidMethod nextMethod = (PlaidMethod)rep.getMember("change").getValue();
		Util.call(nextMethod, Util.unit());
	}

	@Override
	public Color getColor() {
		PlaidMethod isAliveMethod = (PlaidMethod)rep.getMember("getColor").getValue();
		PlaidJavaObjectMap color = (PlaidJavaObjectMap)Util.call(isAliveMethod, plaid.runtime.Util.unit());
		return (Color)color.getJavaObject();
	}


}
