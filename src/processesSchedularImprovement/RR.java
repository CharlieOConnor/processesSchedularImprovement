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
	static final int QUANTUM = 5;

	// Temporary variable for the quantum
	static int quantumCounter = QUANTUM;

	// Sort all processes entered into this queue from the CSV file by arrival time
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

		// Add finished processes to the finished queue, and increment the average turnaround time
		if(readyQueue.size() != 0 && readyQueue.get(0).burstTime == 0) {
			
			avgTurnaroundTime += openCSV.currentTime - readyQueue.get(0).arrivalTime; 	
			avgWaitingTime += readyQueue.get(0).waitingTime;
			finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue	
			openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
			quantumCounter = QUANTUM;                  
		}

		// Reset the quantum counter when it hits 0
		if(quantumCounter == 0)	{
			
			quantumCounter = QUANTUM;
		}

		// Print out all processes with no remaining burst time
		for (Process p: finishedQueue) {
			
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print(p.progressBar + " Done\n");
		}

		// Don't execute any processes here unless both ready queues from FCFS and SJF are empty
		if (FCFS.readyQueue.size() == 0 && SJF.readyQueue.size() == 0  && readyQueue.size() != 0) {

			Process p = readyQueue.get(0);
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority);
			System.out.print("          ");

			if (p.burstTime != 0) {

				//If a higher priority arriving process would occur before the quantum is up, start executing that process
				if(p.burstTime > 1 && openCSV.collectiveQueue.size() > 1 && openCSV.collectiveQueue.get(1).getArrivalTime() == openCSV.currentTime +1 && openCSV.collectiveQueue.get(1).getPriority() < 3) {
					p.progressBar += "|";
					System.out.print(p.progressBar);
					System.out.print("\n\nWARNING: HIGHER PRIORITY PROCESS DETECTED DURING QUANTUM. INTERRUPTING EXECUTING PROCESS.");
					p.burstTime--;
					waitingQueue.add(readyQueue.remove(0));
					openCSV.currentTime++;
					cpuUtilization += 100;
					openCSV.printQueues();
				}

				if (p.burstTime > QUANTUM) {
					
					System.out.print(p.progressBar);
					p.progressBar += "|";

					// Initial response time
					if(p.progressBar.length() == 1) {
						avgResponseTime += openCSV.currentTime - p.arrivalTime;
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

				// If the burst time is <= the quantum
				System.out.print(p.progressBar);				
				p.progressBar += "|";

				// Initial response time
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
		}

		//------------------------------
		//        AGING SOLUTION
		//------------------------------


		// If there's more than one process in the ready queue, increment the waiting
		//  times of all the processes not currently being executed
		
		// If a process is waiting for more than the average burst time, increase its' priority
		if(readyQueue.size() > 1 || FCFS.readyQueue.size() != 0 || readyQueue.size() > 1 && SJF.readyQueue.size() != 0) {
			
			for (int i = 1; i < readyQueue.size(); i++) {
				readyQueue.get(i).waitingTime++;
				avgWaitingTime++;
			}

			for(int i = 1; i < readyQueue.size(); i++) {
				
				if(readyQueue.get(i).waitingTime >= avgBurstTime) {
					
					readyQueue.get(i).waitingTime = 0;
					readyQueue.get(i).priority--;
					openCSV.collectiveQueue.get(i).priority--;
					System.out.println("\n\nWARNING: WAITING TIME EXCEEDED. PROCESS (" + readyQueue.get(i).processID + ") PRIORITY INCREASED FROM 3 -> 2.");
					SJF.readyQueue.add(readyQueue.remove(i));
				}
			}
		}
	}	
}