package plaid.examples.baseline.plaid;
public class runGlobalForkJoin{
	public static void main(String[] args)  {
		long begin = System.nanoTime();
        plaid.fastruntime.PlaidObject VaR42$plaid = plaid.fastruntime.Util.integer(28);
        plaid.examples.baseline.plaid.forkJoin.invoke$plaid(VaR42$plaid);
        long end = System.nanoTime();
		long delta = end - begin;
		double divider = 1000*1000*1000.0;
		double result = delta / divider;
		System.out.printf("%.3f\n", result);
    }
}
