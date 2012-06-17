package plaid.util;

import java.util.HashSet;
import java.util.Set;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.lang.Float64;

public class TimeIntegralMainThreads {
	public static PlaidObject getSquareIntegral() {
        PlaidState state = (PlaidState)plaid.examples.integral.plaid.SquareIntegral.theState$plaid;
        return state.instantiate();
	}
	
	public static void main(String[] args)  {
		Thread[] threads = new Thread[Runtime.getRuntime().availableProcessors()];
		final PlaidObject integral = getSquareIntegral();
		final Set<PlaidObject> results = new HashSet<PlaidObject>();
		
		for ( int i = 0 ; i < threads.length ; i++ ) {
			final int index = i;
			final double interval = 1.0/Runtime.getRuntime().availableProcessors();
			System.out.println("create thread " + i + " from " + (interval * index) + " to " + (interval * index + interval));
			threads[i] = new Thread() {
				@Override
				public void run() {
					
					PlaidObject begin = plaid.fastruntime.Util.float64(interval * index);
					PlaidObject end = plaid.fastruntime.Util.float64(interval * index+interval );
					results.add(((plaid.generated.Icompute$2$plaid)integral.getDispatch()).compute(integral,begin,end));
				}
			};
		}
		
		final long startTime = System.nanoTime();
		
		for (Thread t : threads) { t.start(); }
		for (Thread t : threads) { 
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		double result = 0;
		for ( PlaidObject o : results ) {
			double value = ((Float64.Float64PlaidJavaObject)o).doubleValue;
			System.out.println("add " + value);
			result += value;
		}
		
		System.out.println("result " + result);
		
		final long duration = System.nanoTime() -startTime;
		final double durSeconds = duration / 1000000000.0;
		System.out.println("Ran for : " + durSeconds  + "s");
	}
}
