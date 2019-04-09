package processesSchedularImprovement;

import java.util.Comparator;

public class Process implements Comparator<Process> {

	int processID;
	int arrivalTime;
    int burstTime;
	int priority;
	
	int remainingBurstTime;  
    String progressBar;      // --> Additional variables added to the process
    
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

	// Implement comparator in order to sort the processes in the SJF list
	@Override
	public int compare(Process arg0, Process arg1) {
		if (arg0.getBurstTime() < arg1.getBurstTime()) {		
			return 1;
		}
		else if ( arg0.getBurstTime() > arg1.getBurstTime()) {
			return -1;
		}
		return 0;
	}
}