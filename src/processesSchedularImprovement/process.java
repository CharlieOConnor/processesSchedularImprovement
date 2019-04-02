package processesSchedularImprovement;

import java.util.Comparator;

public class Process implements Comparator<Process> {

	int processID;
	int arrivalTime;
    int burstTime;
	int priority;
	
	int remainingBurstTime;
    String progressBar; // A special variable needed to hold the current progress of each process
	
	/*
	 * Create instance of the process class to hold data on the individual processes
	 */
	public Process(int processID, int arrivalTime, int burstTime, int priority, String progressBar)
	{
		this.processID   = processID;
		this.arrivalTime = arrivalTime;
		this.burstTime   = burstTime;
		this.priority    = priority;
		this.progressBar = progressBar;
	}
	
	// Not sure if needed
//	public String toString()
//	{
//		return processID + " " + arrivalTime + " " + burstTime + " " + priority;
//	}
	
	public int getProcessID()
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
	public int compare(Process arg0, Process arg1) {
		if (arg0.getBurstTime() < arg1.getBurstTime()) 
		{		
			return 1;
		}
		else if ( arg0.getBurstTime() > arg1.getBurstTime())
		{
			return -1;
		}
		// TODO Auto-generated method stub
		return 0;
	}
}
