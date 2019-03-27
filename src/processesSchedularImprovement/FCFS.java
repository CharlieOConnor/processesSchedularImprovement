package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.List;


/* FCFS inherits from the class *Main, so that the processes entered
 * can be alloted to the queue using this algorithm if it is called
 */
public class FCFS extends Scheduler
{

	private static List<Process> FCFSList;
	
	/* A constructor is used to initialise default values
	 * for other variables attached to the algorithm
	 */
	public FCFS(List<Process> process)
	{
		FCFSList               = new ArrayList<Process>(process);
		int returnTime         = 0;
		int responseTime       = 0;
		int waitingTime        = 0;
	
		int amountOfProcesses  = process.size(); 
		int arrivalProcess     = arrivalMin(process);
		
		for (Process p: FCFSList)
		{
			arrivalProcess += p.getBurstTime();
			returnTime     += (arrivalProcess - p.getArrivalTime());
			waitingTime    += (arrivalProcess - p.getArrivalTime() - p.getBurstTime());
		}
		
		responseTime = waitingTime;
		
		super.setAvgTurnaroundTime((double) returnTime / amountOfProcesses);
		super.setAvgResponseTime((double) responseTime / amountOfProcesses);
		super.setAvgWaitingTime((double) waitingTime / amountOfProcesses);
		
	}
	
	public void print()
	{
		 for (Process p: FCFSList)
		 {
		 System.out.printf("%9s %15s %15s %13s %n", p.processID, p.arrivalTime, p.burstTime, p.priority);
		 }
		//Print out some of the averages for the FCFS algorithm
		//super.print("FCFS");
	}
}
