package processesSchedularImprovement;

import java.util.List;

public class Scheduler {

	private double cpuUtilization    = 0;
	private double throughput        = 0;
	private double avgTurnaroundTime = 0;
	private double avgWaitingTime    = 0;
	private double avgResponseTime   = 0;
	/*
	 * private double turnaroundTime = 0; 
	 * private double waitingTime = 0; 
	 * private double responseTime = 0;
	 */
	
	public double getCpuUtilization()
	{
		return cpuUtilization;
	}
	
	public void setCpuUtilization(double cpuUtilization)
	{
		this.cpuUtilization = cpuUtilization;
	}
	
	public double getThroughput()
	{
		return throughput;
	}
	
	public void setThroughput(double throughput)
	{
		this.throughput = throughput;
	}
	
	public double getAvgTurnaroundTime()
	{
		return avgTurnaroundTime;
	}
	
	public void setAvgTurnaroundTime(double turnaroundTime)
	{
		this.avgTurnaroundTime = avgTurnaroundTime;
	}	
	
	public double getAvgWaitingTime()
	{
		return avgWaitingTime;
	}
	
	public void setAvgWaitingTime(double waitingTime)
	{
		this.avgWaitingTime = avgWaitingTime;
	}
	
	public double getAvgResponseTime()
	{
		return avgResponseTime;
	}
	
	public void setAvgResponseTime(double responsetime)
	{
		this.avgResponseTime = avgResponseTime;
	}
	
	public int getNumberOfProcesses(List<Process> process)
	{
		return process.size();
	}
	
	public int firstArrival(List<Process> process)
	{
		int first = process.get(0).getArrivalTime();
		for (Process p : process)
		{
			if(p.getArrivalTime() < first)
			{
				first = p.getArrivalTime();
			}
		}
		return first;
	}
	
	public void print(String schedulerType)
	{
		System.out.println(schedulerType + getAvgTurnaroundTime() + getAvgWaitingTime() + getAvgResponseTime());
	}
	
	/*
	 * public double getTurnaroundTime() { return turnaroundTime; }
	 * 
	 * public double getWaitingTime() { return waitingTime; }
	 * 
	 * public double getResponseTime() { return responseTime; }
	 */
}
