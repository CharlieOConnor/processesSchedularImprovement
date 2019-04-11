package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RR extends Scheduler {

	// Multiple queues depending on the arrival time and remaining burst time of each process
	public static ArrayList<Process> tempQueue     = new ArrayList<Process>();
	public static ArrayList<Process> waitingQueue  = new ArrayList<Process>();
	public static ArrayList<Process> readyQueue    = new ArrayList<Process>();
	public static ArrayList<Process> finishedQueue = new ArrayList<Process>();

	// The time slice/quantum that Round Robin processes should execute for in the CPU
	static final int QUANTUM = 2;


	public RR(List<Process> process) {

		tempQueue          = new ArrayList<Process>(process);

		// Sort processes based on their arrival time
		tempQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}
	
	public void swapProcesses() throws InterruptedException {
		
		Process temp = waitingQueue.get(0);
		waitingQueue.remove(0);
		waitingQueue.add(readyQueue.remove(0));
		readyQueue.add(temp);
		
		System.out.print("\nQuantum elapsed. Swapping executing processes");
	}

	// Print out Round Robin list
	public void print() throws InterruptedException {   
		
		if(openCSV.quantumCounter == 0)
		{
			openCSV.quantumCounter = QUANTUM;
		}
		
		// Print out all processes with no remaining burst time
		for (Process p: finishedQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print(p.progressBar + " Done\n");
		}

		// Don't execute any processes here unless both ready queues from FCFS and SJF are empty
		if(FCFS.readyQueue.size() == 0 && SJF.readyQueue.size() == 0) {

			for (Process p: readyQueue) {
				System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority);
				System.out.print("          ");

				while(p.burstTime != 0) {

					//					if (p.burstTime > 1) {
					//
					//						//for (Process p1: openCSV.collectiveQueue) {
					//
					//							//If a higher priority arriving process would occur before the quantum is up, increment as much of the quantum as you can
					//							if(p.burstTime > 1 && openCSV.collectiveQueue.get(0).getArrivalTime() == openCSV.currentTime +1 && openCSV.collectiveQueue.get(0).getPriority() < 3) {
					//								p.progressBar += "|";
					//								System.out.print(p.progressBar);
					//								p.burstTime--;
					//								openCSV.currentTime++;
					//								openCSV.printQueues();
					//							}
					//						//  }
					//						}

					if (p.burstTime > QUANTUM) {
						System.out.print(p.progressBar);
					    openCSV.currentTime++;
						p.progressBar += "|";
						p.burstTime--;
						openCSV.quantumCounter--;
						System.out.println(openCSV.quantumCounter);
						
						//If there's a process in the waiting queue, swap it with the current process
						if(waitingQueue.size() != 0 && openCSV.quantumCounter == 0) {     
							swapProcesses();
							openCSV.quantumCounter = QUANTUM;
						}
						openCSV.printQueues();	
					}

					if (p.burstTime == QUANTUM) {
						System.out.print(p.progressBar);
							openCSV.currentTime++;
						p.progressBar += "|";
						p.burstTime--;
						openCSV.quantumCounter--;
						System.out.println(openCSV.quantumCounter);
						
						//If there's a process in the waiting queue, swap it with the current process
						if(waitingQueue.size() != 0 && openCSV.quantumCounter == 0) {     
							swapProcesses();
							openCSV.quantumCounter = QUANTUM;
						}
						openCSV.printQueues();
					}

					// If the remaining burst time is less than the quantum, reduce the burst time to 0
					if (p.burstTime < QUANTUM  && p.burstTime != 0) {
						System.out.print(p.progressBar);
						openCSV.currentTime += p.burstTime;
						p.progressBar += "|";
						p.burstTime -= p.burstTime;
						openCSV.quantumCounter--;
						System.out.println(openCSV.quantumCounter);
						openCSV.printQueues();
					}
				}

				if(readyQueue.size() == 1 && readyQueue.get(0).burstTime == 0) {	
					System.out.print(readyQueue.get(0).progressBar + " Done\n");
					finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue	
					openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
					openCSV.currentTime++;		
					openCSV.quantumCounter = QUANTUM;
					System.out.println("\nProcess completed!");
					openCSV.printQueues();                    
				}
			}
		}
	}	
}