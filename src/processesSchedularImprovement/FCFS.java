package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS extends Scheduler {

	// Multiple queues depending on the arrival time and remaining burst time of each process
	public static ArrayList<Process> jobQueue      = new ArrayList<Process>();
	public static ArrayList<Process> waitingQueue  = new ArrayList<Process>();
	public static ArrayList<Process> readyQueue    = new ArrayList<Process>();
	public static ArrayList<Process> finishedQueue = new ArrayList<Process>();


	// Sort all processes entered into this queue from the CSV file by arrival time
	public FCFS(List<Process> process) {

		jobQueue = new ArrayList<Process>(process);

		// Sort processes based on their arrival time into the system
		jobQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}

	// Print out First-Come-First-Served list
	public void print() throws InterruptedException	{

		// If the process is completed, move it to the finished queue
		if (readyQueue.size() != 0 && readyQueue.get(0).burstTime == 0) {
			
			avgTurnaroundTime += openCSV.currentTime - readyQueue.get(0).arrivalTime; 	
			avgWaitingTime += readyQueue.get(0).waitingTime;
			finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue
			openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
		}

		// Print out all the processes with no remaining burst time
		for (Process p: finishedQueue) {
			
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print(p.progressBar + " Done\n");
		}

		// Then print out the process currently executing
		if (readyQueue.size() != 0) {
			
			Process p = readyQueue.get(0);
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");

			if (p.burstTime != 0) {
				
				System.out.print(p.progressBar);
				p.progressBar += "|";

				if(p.progressBar.length() == 1) {
					avgResponseTime += openCSV.currentTime - p.arrivalTime;
				}

				openCSV.currentTime++;
				p.burstTime--;					    		    
			}		
			cpuUtilization += 100;
		}
		
        //------------------------------
		//        AGING SOLUTION
		//------------------------------
		
		
		// If there's more than one process in the ready queue, increment the waiting
		//  times of all the processes not currently being executed
		
		// If a process is waiting for more than the average burst time, increase its' priority
		if(readyQueue.size() > 1) {
			
			for (int i = 1; i < readyQueue.size(); i++) {
				readyQueue.get(i).waitingTime++;
				avgWaitingTime++;
			}

			for(int i = 1; i < readyQueue.size(); i++) {
				
				if(readyQueue.get(i).waitingTime >= avgBurstTime) {
					
					readyQueue.get(i).waitingTime = 0;
					System.out.println("\n\nWARNING: WAITING TIME EXCEEDED. FCFS PROCESS (" + readyQueue.get(i).processID + ") IN THIS QUEUE PUSHED TO FRONT OF QUEUE." );
					readyQueue.add(readyQueue.remove(i));
				}
			}
		}
	}
}