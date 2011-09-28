package plaid.runtime.models.map;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidMethodNotFoundException;
import plaid.runtime.PlaidObject;
import plaid.runtime.Util;

public class PlaidJavaProxyMap implements InvocationHandler {
	private final PlaidObject obj;
	
	public static Object newInstance(PlaidObject obj, Class<?>[] klazzes) {
		return  java.lang.reflect.Proxy.newProxyInstance(
			    obj.getClass().getClassLoader(),
			    klazzes,
			    new PlaidJavaProxyMap(obj));
	}
	
	private PlaidJavaProxyMap(PlaidObject obj) {
		this.obj = obj;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		PlaidMemberDef mdef = obj.getMember(method.getName());
		if  ( mdef != null && (mdef.getValue() instanceof PlaidMethod )) {
			PlaidMethod pmethod = (PlaidMethod)mdef.getValue();
			if ( args == null ) {
				result = Util.call(pmethod, Util.unit());
			} else {
				result = Util.call(pmethod, Util.convertArrayToParams(args));
			}
		} else {
			throw new PlaidMethodNotFoundException(method.getName());
		}
		return result;
	}

}
