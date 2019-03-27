package processesSchedularImprovement;

public class Process implements Comparable<Process> {

	String processID;
	int arrivalTime;
    int burstTime;
	int priority;
	
	int remainingBurstTime;
	
	/*
	 * Create instance of the process class to hold data on the individual processes
	 */
	public Process(String processID, int arrivalTime, int burstTime, int priority)
	{
		this.processID   = processID;
		this.arrivalTime = arrivalTime;
		this.burstTime   = burstTime;
		this.priority    = priority;
	}
	
	// Not sure if needed
//	public String toString()
//	{
//		return processID + " " + arrivalTime + " " + burstTime + " " + priority;
//	}
	
	public String getProcessID()
	{
		return processID;
	}
	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	public int getBurstTime()
	{
		return burstTime;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public int getRemainingBurstTime()
	{
		return remainingBurstTime;
	}
	
	public void setRemainingBurstTime(int burstTime)
	{
		this.remainingBurstTime = remainingBurstTime;
	}

	@Override
	public int compareTo(Process o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
