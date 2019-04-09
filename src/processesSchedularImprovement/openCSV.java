package processesSchedularImprovement;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class openCSV {

	// To iterate through all the processes and check if any new arrival times match the current time
	public static List<Process> collectiveQueue = new ArrayList<Process>(); 

	// Separate queues for each priority of process
	public static List<Process> fcfsQueue = new ArrayList<Process>();
	public static List<Process> sjfQueue  = new ArrayList<Process>();
	public static List<Process> rrQueue   = new ArrayList<Process>();

	// To hold how much time has passed in the system
	static int currentTime = 0;

	// Collective variables to hold the value of currentTime at which the system should stop executing
	static int totalBurstTime;
	static int totalWaitingTime;

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

				totalBurstTime += burstTime;

				if (priority == 1) {
					fcfsQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar));
				}

				else if (priority == 2) {
					sjfQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar));
				}
				else {
					rrQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar));
				}

				collectiveQueue.add(new Process(processID, arrivalTime, burstTime, priority, progressBar));
			}
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

		// Loop printing of output every second
		while(currentTime < totalBurstTime + totalWaitingTime) {

			Thread.sleep(1000);
			System.out.println("\n\n-----------------------\nSeconds Elapsed: " + currentTime);
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("|  Process ID  |  Arrival Time  |  Burst Time  |  Priority  |  Progress  \n");	

			if (currentTime > 0 ) {

				for (Process p: collectiveQueue) {

					/* For a process to enter the waitingQueue, there must be something in the tempQueue and 
					 * the arrivalTime of the process must equal currentTime, to prevent overlapping
					 */		
					if (p.arrivalTime == currentTime) {

						if (p.priority == 1) {
							FCFS.waitingQueue.add(FCFS.tempQueue.remove(0));
							fcfsQueue.remove(0);  //Remove the top process from this queue to prevent it being executed twice
						}

						else if (p.priority == 2) {			
							SJF.waitingQueue.add(SJF.tempQueue.remove(0));
							sjfQueue.remove(0);  //Remove the top process from this queue to prevent it being executed twice
							
							// If the new process's burst time < currently executing process's, swap their positions and reorder the waiting queue
							if (SJF.waitingQueue.size() != 0 && SJF.readyQueue.size() != 0 && SJF.waitingQueue.get(0).burstTime < SJF.readyQueue.get(0).burstTime) {
							SJF.sortBySJF();
							}
						}
						else {
							RR.waitingQueue.add(RR.tempQueue.remove(0));
							rrQueue.remove(0);  //Remove the top process from this queue to prevent it being executed twice
						}
					}

					// For a process to enter the readyQueue, there must be something in the waitingQueue and the readyQueue must be empty	
					if(FCFS.waitingQueue.size() != 0 && FCFS.readyQueue.size() == 0) {
						FCFS.readyQueue.add(FCFS.waitingQueue.remove(0));
					}
					
					if (SJF.waitingQueue.size() != 0 && SJF.readyQueue.size() == 0) {
						SJF.readyQueue.add(SJF.waitingQueue.remove(0));
					}
					
				    if(RR.waitingQueue.size() != 0 && RR.readyQueue.size() == 0) {
						RR.readyQueue.add(RR.waitingQueue.remove(0));
					}
				}

				try {
					if (FCFS.readyQueue.size() != 0 || FCFS.finishedQueue.size() != 0 || 
							SJF.readyQueue.size() != 0 || SJF.finishedQueue.size() != 0  || 
							RR.readyQueue.size()  != 0 || RR.finishedQueue.size() != 0) {

						fcfs.print();
						sjf.print();
						rr.print();
					}
				}
				catch (ConcurrentModificationException e) {
					System.out.print("");
				}
			}
			currentTime++;
		}
	}
}