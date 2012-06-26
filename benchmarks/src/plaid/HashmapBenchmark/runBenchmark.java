package plaid.HashmapBenchmark;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.aeminium.Task;


public abstract class runBenchmark {
	public static DecimalFormat formatter = new DecimalFormat("#.###");
	public static Random rnd = new Random(0xdeadbeef);
	
	public static List<List<PlaidObject>> createInputDataSets(int totalNumbers, int setCount) {
		List<List<PlaidObject>> results = new ArrayList<List<PlaidObject>>(setCount);
		
		// compute all numbers
		List<PlaidObject> allNumbers = new ArrayList<PlaidObject>(totalNumbers);
		for (int i = 0; i < totalNumbers; i++ ) {
			PlaidObject integer = plaid.fastruntime.Util.integer(i);
			PlaidObject num = plaid.examples.lib.hashmap.makeNumber.invoke$plaid(integer);
			allNumbers.add(num);
		}
		
		// mix them up
		Collections.shuffle(allNumbers, rnd);
		
		// partition into sets
		for ( int  i = 0; i < setCount; i++ ) {
			final int perSetCount = totalNumbers / setCount;
			List<PlaidObject> set = new ArrayList<PlaidObject>(perSetCount);
			for ( int n = 0; n < perSetCount; n++) {
				final int index = i*perSetCount + n;
				PlaidObject curNum = allNumbers.get(index);
				set.add(curNum);
			}
			results.add(set);
		}
		
		return results;
	}
	
	public static PlaidObject makeFineHashmap(int order) {
		PlaidObject porder= plaid.fastruntime.Util.integer(order);
		return plaid.examples.lib.hashmap.fine.makeFineHashmap.invoke$plaid(porder);
	}
	
	public static PlaidObject makeGlobalHashmap(int order) {
		PlaidObject porder= plaid.fastruntime.Util.integer(order);
		return plaid.examples.lib.hashmap.global.makeGlobalHashmap.invoke$plaid(porder);
	}
	
	public static void addNumberUnique(final PlaidObject ht, List<PlaidObject> nums) {
		for ( PlaidObject num : nums ) {
			((plaid.generated.IaddUnique$2$plaid)ht.getDispatch()).addUnique(ht, num, num);
		}
	}
	
	public static void addNumberShared(final PlaidObject ht, List<PlaidObject> nums) {
		for ( PlaidObject num : nums ) {
			((plaid.generated.IaddShared$2$plaid)ht.getDispatch()).addShared(ht, num, num);
		}
	}
	
	public static void checkNumberUnique(final PlaidObject ht, List<PlaidObject> nums) {
		for ( PlaidObject num : nums ) {
			PlaidObject result = ((plaid.generated.IcontainsUnique$1$plaid)ht.getDispatch()).containsUnique(ht, num);
			if ( result != plaid.lang.True.TRUE_VALUE ) {
				System.err.println("Cannot find number in ht" );
				System.exit(-1);
			}
		}
	}
	
	public static void checkNumberShared(final PlaidObject ht, List<PlaidObject> nums) {
		for ( PlaidObject num : nums ) {
			PlaidObject result = ((plaid.generated.IcontainsShared$1$plaid)ht.getDispatch()).containsShared(ht, num);
			if ( result != plaid.lang.True.TRUE_VALUE ) {
				System.err.println("Cannot find number in ht" );
				System.exit(-1);
			}
		}
	}
	
	public static void runUniqueBenchmark(final PlaidObject ht, int totalNumber, int threadCount) throws InterruptedException {
		final List<List<PlaidObject>> numberSets = createInputDataSets( totalNumber, threadCount);
		
		// create initialization tasks
		Thread initTask = new Thread() {
			
			@Override
			public void run() {
				for ( List<PlaidObject> set : numberSets) {
					addNumberUnique(ht, set);
				}
			}
		};
		long begin = System.nanoTime();
		initTask.start();
		initTask.join();
		long end = System.nanoTime();
		double initTime = (end - begin) / (1000*1000*1000.0);
		//System.out.println("initialization : " + formatter.format(initTime));
		
		// create check task
		Thread checkTask = new Thread() {
			@Override
			public void run() {
				for ( List<PlaidObject> set : numberSets) {
					checkNumberUnique(ht, set);
				}
			}
		};
		begin = System.nanoTime();
		checkTask.start();
		checkTask.join();
		end = System.nanoTime();
		double checkTime = (end - begin) / (1000*1000*1000.0);
		//System.out.println("check          : " + formatter.format(checkTime));
		

		System.out.println(String.format("%.3f	%.3f", initTime, checkTime));
	}
	
	public static void runSharedBenchmark(final PlaidObject ht, int totalNumber, int threadCount) throws InterruptedException {
		final List<List<PlaidObject>> numberSets = createInputDataSets( totalNumber, threadCount);
		
		// create initialization tasks
		final Collection<Thread> initTasks = new ArrayList<Thread>();
		for ( final List<PlaidObject> set : numberSets  ) {
			initTasks.add(new Thread() {
				@Override
				public void run() {
					addNumberShared(ht, set);
				}
			});
		}
		long begin = System.nanoTime();
		for (Thread t : initTasks) {
			t.start();
		}
		for (Thread t : initTasks ) {
			t.join();
		}
		long end = System.nanoTime();
		double initTime = (end - begin) / (1000*1000*1000.0);
		//System.out.println("initialization : " + formatter.format(initTime));
		
		// create checking tasks
		final Collection<Thread> checkTasks = new ArrayList<Thread>();
		for ( final List<PlaidObject> set : numberSets  ) {
			checkTasks.add(new Thread() {
				@Override
				public void run() {
					checkNumberShared(ht, set);
				}
			});
		}
		begin = System.nanoTime();
		for (Thread t : checkTasks) {
			t.start();
		}
		for (Thread t : checkTasks ) {
			t.join();
		}
		end = System.nanoTime();
		double checkTime = (end - begin) / (1000*1000*1000.0);
		//System.out.println("check          : " + formatter.format(checkTime));
		System.out.println(String.format("%.3f	%.3f", initTime, checkTime));

	}
	
	public static void usage(String msg) {
		System.out.println("ERROR: " + msg);
		System.out.println("usage: runBenchmark  [fine|global] [shared|unique] [totalNumbers] [bucketOrder] [threads]");
	}
	
	public static void main(String[] args) throws InterruptedException {
		if ( args.length != 5 ) {
			usage("Need 4 arguments but only " + args.length + " have been provided.");
		} else {
			int threadCount = 0;
			int totalNumbers = 0;
			int order      = 0;
			PlaidObject ht = null;
			if ( Integer.valueOf(args[4]) > 0 ) { threadCount = Integer.valueOf(args[4]); }
			if ( Integer.valueOf(args[3]) > 0 ) { order = Integer.valueOf(args[3]); }
			if ( Integer.valueOf(args[2]) > 0 ) { totalNumbers = Integer.valueOf(args[2]); }
			if ( args[0].toLowerCase().equals("fine") ) { ht = makeFineHashmap(order); }
			else if ( args[0].toLowerCase().equals("global") ) { ht = makeGlobalHashmap(order); }
			
			if  ( args[1].toLowerCase().equals("shared") ) {
				runSharedBenchmark(ht, totalNumbers, threadCount);
			} else if ( args[1].toLowerCase().equals("unique") ) {
				runUniqueBenchmark(ht, totalNumbers, threadCount);
			} else {
				usage("Second argument " + args[1] +  " not supported");
			}
		}
 	}
}
