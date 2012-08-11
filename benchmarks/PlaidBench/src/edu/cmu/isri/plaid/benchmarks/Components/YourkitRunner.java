package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;

import java.lang.reflect.Method;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Ben Chung
 * Date: 8/3/12
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class YourkitRunner {
    public static void run(Object main, Method invoke, HashMap<String, Object> properties) throws Exception{
        Object yk = null;
        boolean yke = false;
        Method startCPU = null;
        Method startMonitor = null;
        Method stopCPU = null;
        Method stopMonitor = null;
        Method startAllocation = null;
        Method stopAllocation = null;
        Method captureSnapshot = null;
        
        Method clearCPU = null, clearAllocation = null, clearMonitor = null;
        Long sampling = 0l;
        String default_filters = "";
        if (properties.containsKey("yourkit")
                && (Boolean)properties.get("yourkit")) {
            Class ykClass = Class.forName("com.yourkit.api.Controller");
            yk = ykClass.getConstructor(new Class[] {}).newInstance();


            startCPU = ykClass.getMethod("startCPUProfiling", long.class, String.class, String.class);
            stopCPU = ykClass.getMethod("stopCPUProfiling");
            startMonitor = ykClass.getMethod("startMonitorProfiling");
            stopMonitor = ykClass.getMethod("stopMonitorProfiling");
            startAllocation = ykClass.getMethod("startAllocationRecording",
                    boolean.class, int.class, boolean.class, int.class, boolean.class, boolean.class);
            stopAllocation = ykClass.getMethod("stopAllocationRecording");
            
            captureSnapshot = ykClass.getMethod("captureSnapshot", long.class);

            clearCPU = ykClass.getMethod("clearCPUData");
            clearMonitor = ykClass.getMethod("clearMonitorData");
            clearAllocation = ykClass.getMethod("clearAllocationData");

            sampling = (Long)Class.forName("com.yourkit.api.ProfilingModes").getDeclaredField("CPU_SAMPLING").get(null);
            default_filters = (String)Class.forName("com.yourkit.api.Controller").getDeclaredField("DEFAULT_FILTERS").get(null);
            yke = true;
        }
        invoke.invoke(null, main);
        if (yke) {
            startCPU.invoke(yk, sampling, default_filters, null);
            startAllocation.invoke(yk, true, 5, false, 0, true, true);
            startMonitor.invoke(yk);
        }
        invoke.invoke(null, main);
        if (yke) {
            stopCPU.invoke(yk);
            stopMonitor.invoke(yk);
            stopAllocation.invoke(yk);
            captureSnapshot.invoke(yk, 1L);
            clearAllocation.invoke(yk);
            clearCPU.invoke(yk);
            clearMonitor.invoke(yk);
        }
    }
}
