package processesSchedularImprovement;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class openCSV extends Scheduler {

	// To iterate through all the processes
	public static List<Process> collectiveQueue = new ArrayList<Process>(); 

	// Separate queues for each priority of process
	public static List<Process> fcfsQueue = new ArrayList<Process>();
	public static List<Process> sjfQueue  = new ArrayList<Process>();
	public static List<Process> rrQueue   = new ArrayList<Process>();

	// To calculate average waiting time
	static int totalBurstTime;
	// To keep track of how much time has passed in the system
	static int currentTime = 0;
	// To calculate averages at the end of program execution
	static int numberOfProcesses;


	public static void openCSV() throws InterruptedException {

		String fileName = "processesFile.csv";
		File file = new File(fileName); // Read in the file
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNext() ) {
				String data = inputStream.nextLine(); // Read in the entire line
				String[] details = data.split(",");   // Split each piece of data at the comma so they can be assigned

				// Assign each piece of split data on the current line to it's relevant variable
				int processID      = Integer.parseInt(details[0]);
				int arrivalTime    = Integer.parseInt(details[1]); 
				int burstTime      = Integer.parseInt(details[2]);
				int priority       = Integer.parseInt(details[3]);
				String progressBar = ""; // Dynamic variable to hold the progress of the process's execution in the CPU
				int waitingTime = 0;

				if (priority == 1) {
					fcfsQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				}

				else if (priority == 2) {
					sjfQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				}
				else {
					rrQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				}

				collectiveQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				numberOfProcesses++;
				totalBurstTime += burstTime;
			}

			// Set up the waiting time for the aging algorithm
			avgBurstTime = totalBurstTime/numberOfProcesses;
			inputStream.close(); // Prevent resource leak
			
			// Sort each individual queue
			collectiveQueue.sort(Comparator.comparing(Process::getArrivalTime));	
			fcfsQueue.sort(Comparator.comparing(Process::getArrivalTime));
			sjfQueue.sort(Comparator.comparing(Process::getArrivalTime));
			rrQueue.sort(Comparator.comparing(Process::getArrivalTime));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void printQueues() throws InterruptedException {

		FCFS fcfs = new FCFS(fcfsQueue);
		SJF  sjf  = new SJF(sjfQueue);
		RR   rr   = new RR(rrQueue);

		if (collectiveQueue.size() != 0) {

			Thread.sleep(50);
			System.out.println("\n\n-----------------------\nSeconds Elapsed: " + currentTime);
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("|  Process ID  |  Arrival Time  |  Burst Time  |  Priority  |  Progress  \n");	

			for (Process p: collectiveQueue) {

				/* For a process to enter the waitingQueue, there must be something in the jobQueue and 
				 * the arrivalTime of the process must equal currentTime.
				 */		
				if (p.arrivalTime == currentTime) {

					if (p.priority == 1) {
						FCFS.readyQueue.add(FCFS.jobQueue.remove(0));
						fcfsQueue.remove(0);  //Remove the top process from this queue to prevent it being added twice
					}

					else if (p.priority == 2) {			
						SJF.readyQueue.add(SJF.jobQueue.remove(0));
						sjfQueue.remove(0);  //Remove the top process from this queue to prevent it being added twice

						// If the new process's burst time < currently executing process's, swap their positions and reorder the waiting queue
						if (SJF.readyQueue.size() > 1 && SJF.readyQueue.get(1).burstTime < SJF.readyQueue.get(0).burstTime) {
							SJF.sortBySJF();
						}
					}
					else {
						RR.readyQueue.add(RR.jobQueue.remove(0));
						rrQueue.remove(0);  //Remove the top process from this queue to prevent it being added twice
					}
				}
			}

			if(FCFS.waitingQueue.size() == 0 && SJF.waitingQueue.size() == 0 && RR.waitingQueue.size() == 0 &&
					FCFS.readyQueue.size() == 0 && SJF.readyQueue.size() == 0 && RR.readyQueue.size() == 0) {

				currentTime++;
				System.out.printf("\n%40s", "CPU IDLE");
				printQueues();
			}
		}

		// End the execution of the program
		if(collectiveQueue.size() == 0) {
			System.out.printf("\n%55s","ALL PROCESSES COMPLETED!");
			System.out.printf("%16s %.1f %1s", "\n\nCPU Utilization: ", cpuUtilization/currentTime, "%");
			System.out.printf("\n%23s %.1f %12s", "Average Turnaround Time: ", avgTurnaroundTime/(double)numberOfProcesses, "milliseconds");
			System.out.printf("\n%20s %.1f %12s", "Average Waiting Time: ", avgWaitingTime/(double)numberOfProcesses, "milliseconds");	
			System.out.printf("\n%20s %.1f %12s", "Average Response Time: ", avgResponseTime/(double)numberOfProcesses, "milliseconds\n");
			System.exit(0);
		}

		// For a process to enter the readyQueue, there must be something in the waitingQueue and the readyQueue must be empty	
		if(FCFS.waitingQueue.size() != 0) {
			FCFS.readyQueue.add(FCFS.waitingQueue.remove(0));
		}

		if (SJF.waitingQueue.size() != 0) {
			SJF.readyQueue.add(SJF.waitingQueue.remove(0));
		}

		if(RR.waitingQueue.size() != 0) {
			RR.readyQueue.add(RR.waitingQueue.remove(0));
		}

		if (FCFS.readyQueue.size() != 0 || FCFS.finishedQueue.size() != 0 || 
				SJF.readyQueue.size() != 0 || SJF.finishedQueue.size() != 0  || 
				RR.readyQueue.size()  != 0 || RR.finishedQueue.size() != 0) {

			fcfs.print(); System.out.println();
			sjf.print();  System.out.println();
			rr.print();
			printQueues();
		}
	}
}