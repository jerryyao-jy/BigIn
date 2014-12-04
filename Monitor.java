import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;

import javax.management.MBeanServer;

import com.sun.management.OperatingSystemMXBean;


public class Monitor {

	public static void main(String[] args) {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		try {
			final RuntimeMXBean rmxb = ManagementFactory.newPlatformMXBeanProxy(server, "java.lang:type=Runtime", RuntimeMXBean.class);
			final MemoryMXBean mmxb = ManagementFactory.newPlatformMXBeanProxy(server, "java.lang:type=Memory", MemoryMXBean.class);
			final ThreadMXBean tmxb = ManagementFactory.newPlatformMXBeanProxy(server, "java.lang:type=Threading", ThreadMXBean.class);
			final OperatingSystemMXBean omxb = ManagementFactory.newPlatformMXBeanProxy(server, "java.lang:type=OperatingSystem", OperatingSystemMXBean.class);

			new Thread() {
				public void run() {
					while (true) {
//						System.out.println("Uptime: " + rmxb.getUptime());
						MemoryUsage mu = mmxb.getHeapMemoryUsage();
						System.out.println("Max: " + mu.getMax() + "Used: " + mu.getUsed());
						System.out.println("Thread count: " + tmxb.getThreadCount());
//						System.out.println("CPU time: " + omxb.getProcessCpuTime());


						int availableProcessors = omxb.getAvailableProcessors();
						long prevUpTime = rmxb.getUptime();
						long prevProcessCpuTime = omxb.getProcessCpuTime();
						double cpuUsage;
						try	{
						    Thread.sleep(1000);
						}
						catch (Exception ignored) { }

						long upTime = rmxb.getUptime();
						long processCpuTime = omxb.getProcessCpuTime();
						long elapsedCpu = processCpuTime - prevProcessCpuTime;
						long elapsedTime = upTime - prevUpTime;

						cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
						 DecimalFormat df = new DecimalFormat("#.00");

						System.out.println("Java CPU usage: " + df.format(cpuUsage));
						System.out.println("----------------\r\n");

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
					}
				}
			}.start();

			new Thread() {
				public void run() {
					int i = 0;
					while (true) {
						i++;
//						try {
////							sleep(1);
//						} catch (InterruptedException e) {
//						}
					}
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
