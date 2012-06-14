package plaid.util;

import java.text.DecimalFormat;

public final class JavaBenchmarkTimer {
	private static final DecimalFormat formatter = new DecimalFormat("#.###");
	private long start;
	private long lastMark;
	
	public JavaBenchmarkTimer() {}
	
	public static final JavaBenchmarkTimer make() {
		return new JavaBenchmarkTimer();
	}
	
	public void start() {
		reset();
	}
	
	public void reset() {
		this.start = System.nanoTime();
		this.lastMark = this.start;
	}
	
	public void mark(String msg) {
		final long mark = System.nanoTime();
		final double lastLap = (mark - this.lastMark) / (1000*1000*1000.0);
		System.out.println(msg + " : " + formatter.format(lastLap) + "s");
		this.lastMark = System.nanoTime();
	}
	
	public void end(String msg) {
		mark(msg);
		final double lastLap = (this.lastMark - this.start) / (1000*1000*1000.0);
		System.out.println("total time : " + formatter.format(lastLap) + "s");
		reset();
	}
}
