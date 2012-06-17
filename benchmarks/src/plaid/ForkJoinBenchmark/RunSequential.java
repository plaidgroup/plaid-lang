package plaid.ForkJoinBenchmark;

import plaid.fastruntime.PlaidObject;

public class RunSequential {
	
	public static int START_LEVEL = 29;
	
	public static void main(String[] args) {
		int startLevel = START_LEVEL;
		if( args.length > 0 ) {
			startLevel = Integer.valueOf(args[0]);
		}
		
		System.out.println("start level   : " + startLevel);
		long start = System.nanoTime();
		
		PlaidObject fjb    = plaid.ForkJoinBenchmark.makeForkJoinBenchmark.invoke$plaid();
		PlaidObject value  = plaid.fastruntime.Util.integer(startLevel);
		PlaidObject result = ((plaid.generated.IforkJoin$1$plaid)fjb.getDispatch()).forkJoin(fjb, value);
		
		long end = System.nanoTime();
		System.out.println("execution time : " + ((end - start)/(1000*1000*1000.0))+ "s" );
	}
}
