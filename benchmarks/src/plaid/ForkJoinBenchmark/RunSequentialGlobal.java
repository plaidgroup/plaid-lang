package plaid.ForkJoinBenchmark;

import plaid.fastruntime.PlaidObject;

public class RunSequentialGlobal {
	
	public static int START_LEVEL = RunSequential.START_LEVEL;
	
	public static void main(String[] args) {
		int startLevel = START_LEVEL;
		if( args.length > 0 ) {
			startLevel = Integer.valueOf(args[0]);
		}
		
		System.out.println("start level   : " + startLevel);
		long start = System.nanoTime();
		
		PlaidObject value  = plaid.fastruntime.Util.integer(startLevel);
		PlaidObject result = plaid.ForkJoinBenchmark.forkJoinGlobal.invoke$plaid(value);
		
		long end = System.nanoTime();
		System.out.println("execution time : " + ((end - start)/(1000*1000*1000.0))+ "s" );
	}
}
