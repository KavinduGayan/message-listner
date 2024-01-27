package org.example.rabbitmqproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MonitoringComponent {

    private final Runtime runtime;

    @Autowired
    public MonitoringComponent() {
        this.runtime = Runtime.getRuntime();
    }

    @Scheduled(fixedRate = 2000) // Run every 2 seconds
    public void logMetrics() {
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        double cpuUsage = getCpuUsage();

        // Log the metrics to different log files or wherever you prefer
        System.out.println("Time: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        System.out.println("Used Memory: " + usedMemory / (1024 * 1024) + " MB");
        System.out.println("CPU Usage: " + cpuUsage + " %");
    }

    /**
     * Returns the current CPU usage as a percentage.
     *
     * @return the current CPU usage as a percentage, or -1 if monitoring is not supported on the current platform
     */
    private double getCpuUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        if (operatingSystemMXBean instanceof com.sun.management.OperatingSystemMXBean sunOsBean) {
            return sunOsBean.getCpuLoad() * 100;
        }

        return -1; // CPU usage monitoring not supported on this platform
    }

}

