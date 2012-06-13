package plaid.util;

public final class JavaBenchmarkTimer {
	private long start;
	private long lastMark;
	
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
		System.out.println(msg + " : " + lastLap + "s");
	}
	
	public void end(String msg) {
		final long mark = System.nanoTime();
		final double lastLap = (mark - this.start) / (1000*1000*1000.0);
		System.out.println(msg + " : " + lastLap + "s");
		reset();
	}
}
