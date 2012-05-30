package plaid.ForkJoinBenchmark;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.aeminium.Task;

public class RunAeminium {
	
	public static int START_LEVEL = 27;
	
	public static void main(String[] args) {
		int startLevel = START_LEVEL;
		if( args.length > 0 ) {
			startLevel = Integer.valueOf(args[0]);
		}
		
		System.out.println("start level   : " + startLevel);
		long start = System.nanoTime();
		
		final PlaidObject fjb    = plaid.ForkJoinBenchmark.makeForkJoinBenchmark.invoke$plaid();
		final PlaidObject value  = plaid.fastruntime.Util.integer(startLevel);
		
		
		Task task = new Task(0) {
			@Override
			protected void compute() {
				PlaidObject result = ((plaid.generated.IforkJoin$1$plaid)fjb.getDispatch()).forkJoin(fjb, value);				
			}
		};
		plaid.fastruntime.aeminium.Util.POOL.invoke(task);
		
		long end = System.nanoTime();
		System.out.println("execution time : " + ((end - start)/(1000*1000*1000.0))+ "s" );
	}
}
