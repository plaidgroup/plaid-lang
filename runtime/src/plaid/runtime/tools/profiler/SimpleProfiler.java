package plaid.runtime.tools.profiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimePlugin;
import plaid.runtime.event.PlaidRuntimeCallEnterEvent;
import plaid.runtime.event.PlaidRuntimeCallLeaveEvent;
import plaid.runtime.event.PlaidRuntimeEventListenerAdapter;

public class SimpleProfiler extends PlaidRuntimeEventListenerAdapter implements	PlaidRuntimePlugin {
	private PlaidRuntime rt;
	private ConcurrentHashMap<String, MethodEntry> map;
	private ThreadLocal<CallStack> callstack = new ThreadLocal<CallStack>() {
		@Override
		protected CallStack initialValue() {
			return new CallStack();
		}		
	};
	

	@SuppressWarnings("unused")
	private class MethodEntry {
		public String stateName;
		public String methodName;
		public String filename;
		public Integer line;
		public Integer column;
		public AtomicLong totalRuntime     = new AtomicLong(0);
		public AtomicLong totalInvokations = new AtomicLong(0);
	}
	
	private class MethodInvokation {
		public MethodEntry me;
		public long enter;
		public long subcalls;
	}
	
	private class CallStack {
		private Stack<MethodInvokation> stack = new Stack<MethodInvokation>();
		
		public MethodInvokation peek() {
			if ( !stack.isEmpty() ) {
				stack.peek();
			}
			return null;
		}
		
		public void push(MethodInvokation me) {
			stack.push(me);
		}
		
		public MethodInvokation pop() {
			return stack.pop();
		}
	}
	
	public SimpleProfiler() {
		System.out.println("Loaded simple profiler.");
	}
	
	@Override
	public boolean activate(PlaidRuntime rt) {
		this.rt  = rt;
		this.map = new ConcurrentHashMap<String, MethodEntry>(); 
		rt.addRuntimeEventListener(this);
		return true;
	}

	@Override
	public void shutdown() {
		rt.removeRuntimeEventListener(this);
		System.out.println("Method name                                        | Total Time (us) | Method Calls |");
		System.out.println("-------------------------------------------------------------------------------------");
		List<Map.Entry<String, MethodEntry>> list = new ArrayList<Map.Entry<String, MethodEntry>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, MethodEntry>>() {
			@Override
			public int compare(Entry<String, MethodEntry> o1, Entry<String, MethodEntry> o2) {
				if (o1.getValue().totalRuntime.get() < o2.getValue().totalRuntime.get()) {
					return 1;
				} else if (o2.getValue().totalRuntime.get() < o1.getValue().totalRuntime.get()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		for (Map.Entry<String, MethodEntry> entry : list ) {
			MethodEntry me = entry.getValue();
			String name = entry.getKey();
			long totalTime = (long) (me.totalRuntime.get() / (1000));
			System.out.println(String.format("%-50s | %15d | %12d | ", name, totalTime, me.totalInvokations.get()));
		}
		this.map = null;
	}

	@Override
	public void handleCallEnter(PlaidRuntimeCallEnterEvent ev) {
		super.handleCallEnter(ev);
				
		CallStack cs = callstack.get();
		MethodInvokation mi = new MethodInvokation();
		mi.me = getEntry(ev);
		mi.enter = System.nanoTime();
		cs.push(mi);
	}

	@Override
	public void handleCallLeave(PlaidRuntimeCallLeaveEvent ev) {
		super.handleCallLeave(ev);
		MethodInvokation mi = callstack.get().pop();
		mi.me.totalInvokations.incrementAndGet();
		mi.me.totalRuntime.addAndGet(System.nanoTime() - mi.enter - mi.subcalls );
		if ( callstack.get().peek() != null ) {
			callstack.get().peek().subcalls += System.nanoTime() - mi.enter;
		}
	}
	
	protected String createKey(PlaidRuntimeCallEnterEvent ev) {
		if (ev.getReceiver().toString().equals("State<plaid.lang.Unit>") ) {
			return ev.getMethodName();
		} else if ( ev.getReceiver() instanceof PlaidJavaObject ) {
			return ((PlaidJavaObject)ev.getReceiver()).getJavaObject().getClass().getCanonicalName() + "." + ev.getMethodName();
		} else {
			return ev.getReceiver().toString() + "." + ev.getMethodName();
		}
	}
	
	protected MethodEntry getEntry(PlaidRuntimeCallEnterEvent ev) {
		MethodEntry me = null;
		String key = createKey(ev);
		me = map.get(key);
		if ( me == null ) {
			MethodEntry newMe = new MethodEntry();
			newMe.stateName = ev.getReceiver().toString();
			newMe.methodName= ev.getMethodName().toString();
			newMe.filename  = ev.getCallerFilename();
			newMe.line      = ev.getCallerLine();
			newMe.column    = ev.getCallerColumn();
			map.putIfAbsent(key, newMe);
			me = map.get(key);
		}
				
		return me;
	}
}
