package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS extends Scheduler {

	// Multiple queues depending on the arrival time and remaining burst time of each process
	public static ArrayList<Process> tempQueue     = new ArrayList<Process>();
	public static ArrayList<Process> waitingQueue  = new ArrayList<Process>();
	public static ArrayList<Process> readyQueue    = new ArrayList<Process>();
	public static ArrayList<Process> finishedQueue = new ArrayList<Process>();


	// Sort all processes entered into this queue from the CSV file by arrival time
	public FCFS(List<Process> process) {

		tempQueue              = new ArrayList<Process>(process);

		// Sort processes based on their arrival time into the system
		tempQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}

	// Print out First-Come-First-Served list
	public void print() throws InterruptedException	{
		
		// If the process is completed, move it to the finished queue
		if (readyQueue.size() == 1 && readyQueue.get(0).burstTime == 0) {
			//System.out.print(readyQueue.get(0).progressBar + " Done\n");
			//System.out.println("\nPROCESS COMPLETED!");
			finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue
			openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
			avgTurnaroundTime += (openCSV.currentTime - finishedQueue.get(0).arrivalTime)/openCSV.numberOfProcesses;
			openCSV.currentTime++;
			
		}
		
		// First print out all the processes with no remaining burst time
		for (Process p: finishedQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print(p.progressBar + " Done\n");
		}

		// Then print out the process currently executing
		for (Process p: readyQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");

			if (p.burstTime != 0) {
				System.out.print(p.progressBar);
				p.progressBar += "|";
				openCSV.currentTime++;
				p.burstTime--;
				//openCSV.printQueues();					    		    
			}
			
			cpuUtilization += 100;
		}
		//Print out some of the averages for the FCFS algorithm
		//super.print("FCFS");	
	}
}