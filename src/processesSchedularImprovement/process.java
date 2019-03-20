package processesSchedularImprovement;

public class process {

	String processID;
	int arrivalTime;
	int burstTime;
	int priority;
	
	/*
	 * Create instance of the process class to hold data on the individual processes
	 */
	public process(String processID, int arrivalTime, int burstTime, int priority)
	{
		this.processID = processID;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
	}
	
	public String toString()
	{
		return processID + " "+ arrivalTime + " " + burstTime + " " + priority;
	}
}
