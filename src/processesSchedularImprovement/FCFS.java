package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/* FCFS inherits from the class *Main, so that the processes entered
 * can be alloted to the queue using this algorithm if it is called
 */
public class FCFS extends Scheduler {

	private static List<Process> FCFSList;       
	
	//An array of arrays to hold each progress bar
	//private static List<List<String>> arrayOfArrays = new ArrayList<>();
	//private static List<String> progressBar = new ArrayList<String>();
	
	//For getting rid of spaces and brackets between array elements
	//String result  = progressBar.toString();
	//String result2 = arrayOfArrays.toString();
	

	/* A constructor is used to initialise default values
	 * for other variables attached to the algorithm
	 */
	public FCFS(List<Process> process) {
		
		FCFSList               = new ArrayList<Process>(process);
		int returnTime         = 0;
		int responseTime       = 0;
		int waitingTime        = 0;

		int amountOfProcesses  = process.size(); 
		int arrivalProcess     = arrivalMin(process);
		
		// Sort processes based on their arrival time into the system
		FCFSList.sort(Comparator.comparing(Process::getArrivalTime));

		for (Process p: FCFSList) {
			arrivalProcess += p.getBurstTime();
			returnTime     += (arrivalProcess - p.getArrivalTime());
			waitingTime    += (arrivalProcess - p.getArrivalTime() - p.getBurstTime());
		}

		responseTime = waitingTime;

		super.setAvgTurnaroundTime((double) returnTime / amountOfProcesses);
		super.setAvgResponseTime((double) responseTime / amountOfProcesses);
		super.setAvgWaitingTime((double) waitingTime / amountOfProcesses);

	}

	public void print() throws InterruptedException	{
		
		for (Process p: FCFSList) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			while(p.burstTime != 0) {
				p.progressBar += "|";
				//if(arrayOfArrays.size() >= 1) {
				//System.out.print(result2.replace("[","").replace("]",""));
				//System.out.print(arrayOfArrays);
				//}
				//System.out.print(result.replaceAll(",","").replace("[","").replace("]",""));		
				System.out.print(p.progressBar);
				//System.out.print("|");
				p.burstTime--;
			    Main.printLoop();		
			}
			System.out.print(p.progressBar + " Done\n");
			//arrayOfArrays.add(progressBar);
			//System.out.print(arrayOfArrays + " Done\n");
			//System.out.print(result2.replaceAll(", ","").replace("[","").replace("]","") +" Done\n");
			//System.out.print(result.replaceAll(",","").replace("[","").replace("]","") + " Done\n");
			
			//Constantly being cleared with each new addition because
			// the first process's burst time = 0
		}
		//Print out some of the averages for the FCFS algorithm
		//super.print("FCFS");
	}
}