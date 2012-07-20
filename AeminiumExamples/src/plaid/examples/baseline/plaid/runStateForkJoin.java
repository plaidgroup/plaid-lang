package plaid.examples.baseline.plaid;
public class runStateForkJoin{
    public static void main(String[] args) {
        final plaid.fastruntime.PlaidObject VaR33$plaid;
        final plaid.fastruntime.PlaidObject VaR34$plaid;
        final plaid.fastruntime.PlaidObject VaR35$plaid;
        plaid.fastruntime.PlaidState VaR36$plaid;
        final plaid.fastruntime.PlaidObject VaR37$plaid;
        VaR37$plaid = plaid.examples.baseline.plaid.BaseLine.theState$plaid;
        plaid.fastruntime.PlaidState VaR38$plaid = (plaid.fastruntime.PlaidState)VaR37$plaid;
        VaR36$plaid = VaR38$plaid;
        VaR35$plaid = VaR36$plaid.instantiate();
        final plaid.fastruntime.PlaidObject bl;
        bl = VaR35$plaid;
        VaR34$plaid = plaid.fastruntime.Util.unit();
        final plaid.fastruntime.PlaidObject VaR39$plaid;
        VaR39$plaid = plaid.fastruntime.Util.integer(28);
        final plaid.fastruntime.PlaidObject VaR40$plaid;
        VaR40$plaid = bl;
        long begin = System.nanoTime();
        VaR33$plaid = ((plaid.generated.IforkJoin$1$plaid)VaR40$plaid.getDispatch()).forkJoin(VaR40$plaid,VaR39$plaid);
        long end = System.nanoTime();
		long delta = end - begin;
		double divider = 1000*1000*1000.0;
		double result = delta / divider;
		System.out.printf("%.3f\n", result);
    }
}
