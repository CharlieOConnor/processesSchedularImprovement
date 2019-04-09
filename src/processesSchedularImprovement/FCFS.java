package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS {

	// Multiple queues depending on the arrival time and remaining burst time of each process
	public  static ArrayList<Process> tempQueue     = new ArrayList<Process>();
	public  static ArrayList<Process> waitingQueue  = new ArrayList<Process>();
	public  static ArrayList<Process> readyQueue    = new ArrayList<Process>();
	public  static ArrayList<Process> finishedQueue = new ArrayList<Process>();


	// Sort all processes entered into this queue from the CSV file by arrival time
	public FCFS(List<Process> process) {

		tempQueue              = new ArrayList<Process>(process);

		// Sort processes based on their arrival time into the system
		tempQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}

	// Print out First-Come-First-Served list
	public void print() throws InterruptedException	{

		// First print out all the processes with no remaining burst time
		for (Process p: finishedQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print(p.progressBar + " Done\n");
		}
		
		// If the CPU doesn't have anything to execute at the moment, increment totalWaitingTime
		if(waitingQueue.size() == 0 && readyQueue.size() == 0) {
			openCSV.totalWaitingTime++;
		}

		// Then print out the process currently executing
		for (Process p: readyQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");

			while (p.burstTime != 0) {
				openCSV.currentTime++;
				System.out.print(p.progressBar);
				p.progressBar += "|";
				p.burstTime--;
				openCSV.printQueues();					    		    
			}
		}

		if (readyQueue.size() == 1 && readyQueue.get(0).burstTime == 0) {
			System.out.print(readyQueue.get(0).progressBar + " Done\n");
			finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue
			openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
			openCSV.currentTime++;		
			openCSV.printQueues();
		}
		//Print out some of the averages for the FCFS algorithm
		//super.print("FCFS");	
	}
}