package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RR extends Scheduler {

	// Multiple queues depending on the arrival time and remaining burst time of each process
	public static ArrayList<Process> jobQueue      = new ArrayList<Process>();
	public static ArrayList<Process> waitingQueue  = new ArrayList<Process>();
	public static ArrayList<Process> readyQueue    = new ArrayList<Process>();
	public static ArrayList<Process> finishedQueue = new ArrayList<Process>();

	// The time slice/quantum that Round Robin processes should execute for in the CPU
	static final int QUANTUM = 4;

	// Temporary variable for the quantum.
	static int quantumCounter = QUANTUM;


	public RR(List<Process> process) {

		jobQueue = new ArrayList<Process>(process);

		// Sort processes based on their arrival time
		jobQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}

	public void swapProcesses() throws InterruptedException {

		readyQueue.add(readyQueue.remove(0));

		System.out.print("\n\nQUANTUM ELAPSED. SWAPPING EXECUTING PROCESSES.");
	}

	// Print out Round Robin list
	public void print() throws InterruptedException {   

		if(readyQueue.size() != 0 && readyQueue.get(0).burstTime == 0) {	
			finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue	
			openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
			avgTurnaroundTime += (openCSV.currentTime - finishedQueue.get(0).arrivalTime)/openCSV.numberOfProcesses; 	
			quantumCounter = QUANTUM;                  
		}

		if(quantumCounter == 0)
		{
			quantumCounter = QUANTUM;
		}

		// Print out all processes with no remaining burst time
		for (Process p: finishedQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print("Done\n");
		}

		// Don't execute any processes here unless both ready queues from FCFS and SJF are empty
		if(FCFS.readyQueue.size() == 0 && SJF.readyQueue.size() == 0  && readyQueue.size() != 0) {

			Process p = readyQueue.get(0);
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority);
			System.out.print("          ");

			while(p.burstTime != 0) {

				//If a higher priority arriving process would occur before the quantum is up, increment as much of the quantum as you can
				if(p.burstTime > 1 && openCSV.collectiveQueue.size() > 1 && openCSV.collectiveQueue.get(1).getArrivalTime() == openCSV.currentTime +1 && openCSV.collectiveQueue.get(1).getPriority() < 3) {
					p.progressBar += "|";
					System.out.print(p.progressBar);
					System.out.print("\n\nHIGHER PRIORITY PROCESS DETECTED. SWAPPING EXECUTING PROCESS.");
					p.burstTime--;
					waitingQueue.add(readyQueue.remove(0));
					openCSV.currentTime++;
					cpuUtilization += 100;
					openCSV.printQueues();
				}

				if (p.burstTime > QUANTUM) {
					System.out.print(p.progressBar);
					p.progressBar += "|";

					if(p.progressBar.length() == 1) {
						avgResponseTime += openCSV.currentTime/openCSV.numberOfProcesses;
					}

					openCSV.currentTime++;
					p.burstTime--;
					quantumCounter--;
					cpuUtilization += 100;

					//If there's a process in the ready queue, swap it with the current process
					if(readyQueue.size() > 1 && quantumCounter == 0) {     
						swapProcesses();
						quantumCounter = QUANTUM;
					}
					openCSV.printQueues();	
				}

				if (p.burstTime == QUANTUM) {
					System.out.print(p.progressBar);				
					p.progressBar += "|";

					if(p.progressBar.length() == 1) {
						avgResponseTime += openCSV.currentTime/openCSV.numberOfProcesses;
					}

					openCSV.currentTime++;
					p.burstTime--;
					quantumCounter--;
					cpuUtilization += 100;

					//If there's a process in the ready queue, swap it with the current process
					if(readyQueue.size() > 1 && quantumCounter == 0) {     
						swapProcesses();
						quantumCounter = QUANTUM;
					}
					openCSV.printQueues();
				}

				if (p.burstTime < QUANTUM) {
					System.out.print(p.progressBar);				
					p.progressBar += "|";

					if(p.progressBar.length() == 1) {
						avgResponseTime += openCSV.currentTime/openCSV.numberOfProcesses;
					}

					openCSV.currentTime++;
					p.burstTime -= p.burstTime;
					quantumCounter--;
					cpuUtilization += 100;
				}
			}
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
					SJF.readyQueue.add(readyQueue.remove(i));
					System.out.println("\n\nWAITING TIME EXCEEDED. RR PROCESS'S PRIORITY INCREASED.");

				}
			}
		}
		
		if(finishedQueue.size() == 0 && FCFS.readyQueue.size() != 0 || SJF.readyQueue.size() != 0) {
			System.out.println("\nRR readyQueue on hold.");
		}
	}	
}