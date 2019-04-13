package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF extends Scheduler {

	// Multiple queues depending on the arrival time and remaining burst time of each process
	public static ArrayList<Process> jobQueue       = new ArrayList<Process>();
	public static ArrayList<Process> waitingQueue   = new ArrayList<Process>();
	public static ArrayList<Process> readyQueue     = new ArrayList<Process>();
	public static ArrayList<Process> finishedQueue  = new ArrayList<Process>();


	public SJF(List<Process> process) {

		jobQueue = new ArrayList<Process>(process);		

		// Sort list by arrival time
		jobQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}

	// Swap the new process (that has a lower burst time) with the one currently executing
	public static void sortBySJF() throws InterruptedException {

		waitingQueue.add(readyQueue.remove(0));

		waitingQueue.sort(Comparator.comparing(Process::getBurstTime));
		System.out.println("Currently executing process swapped via SJF");
	}

	// Print out Shortest-Job-First list
	public void print() throws InterruptedException	{

		// If the process is completed, move it to the finished queue
		if(readyQueue.size() != 0 && readyQueue.get(0).burstTime == 0) {	
			finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue	
			openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining	
			avgTurnaroundTime += (openCSV.currentTime - finishedQueue.get(0).arrivalTime)/openCSV.numberOfProcesses;
		}

		// Print out all the processes with no remaining burst time
		for (Process p: finishedQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print("Done\n");
		}

		// Don't execute any processes in this ready queue unless the FCFS ready queue is empty
		if(FCFS.readyQueue.size() == 0 && readyQueue.size() != 0) {
			Process p = readyQueue.get(0);
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");

			if (p.burstTime != 0) {
				System.out.print(p.progressBar);
				p.progressBar += "|";

				if(p.progressBar.length() == 1) {
					avgResponseTime += openCSV.currentTime/openCSV.numberOfProcesses;
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
		// If a process is waiting for 10 seconds to be executed, increase its' priority
		if(readyQueue.size() > 1) {
			for (int i = 1; i < readyQueue.size(); i++) {
				readyQueue.get(i).waitingTime++;
			}

			for(int i = 1; i < readyQueue.size(); i++) {
				if(readyQueue.get(i).waitingTime == 10) {
					avgWaitingTime += readyQueue.get(i).waitingTime;
					readyQueue.get(i).priority--;
					openCSV.collectiveQueue.get(i).priority--;
					readyQueue.get(i).waitingTime = 0;
					FCFS.readyQueue.add(readyQueue.remove(i));
					System.out.println("\n\nWAITING TIME EXCEEDED. RR PROCESS'S PRIORITY INCREASED.");

				}
			}
		}

		if(finishedQueue.size() == 0 && FCFS.readyQueue.size() != 0) {
			System.out.println("\nSJF readyQueue on hold.");
		}
	}
}