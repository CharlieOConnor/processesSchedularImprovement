package processesSchedularImprovement;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

/** Proposed solution with a multi-level queue scheduling program */

public class Main  {

	public static List<Process> fcfsList = new ArrayList<Process>();
	public static List<Process> sjfList  = new ArrayList<Process>();
	public static List<Process> rrList   = new ArrayList<Process>();
	
	static int i = 0;
	
	// The maximum value for the seconds counter
	static int totalBurstTime;

	public static void main(String args[]) throws InterruptedException {
		
	 	openCSV();

		//progressBars bars = new progressBars();	
		//bars.showBars();
	}

	public static void openCSV() throws InterruptedException {
		//        pQueue<Process> high = new pQueue<Process>();
		//        pQueue<Process> medium = new pQueue<Process>();
		//        pQueue<Process> low = new pQueue<Process>();
		
		/*
		 * Open CSV file
		 */
		String fileName = "processesFile.csv";
		File file = new File(fileName); //Read in the file
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNext()) {
				String data = inputStream.nextLine(); //Currently reads in the entire line
				String[] details = data.split(",");

				//String processID = details[0]; 
				int processID    = Integer.parseInt(details[0]);
				int arrivalTime  = Integer.parseInt(details[1]); 
				int burstTime    = Integer.parseInt(details[2]);
				int priority     = Integer.parseInt(details[3]);
				String progressBar = "";
				int waitingTime = 0;
				
				totalBurstTime += burstTime;

				if (priority == 1) {
					fcfsList.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				}

				else if (priority == 2) {
					sjfList.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				}
				else {
					rrList.add(new Process(processID, arrivalTime, burstTime, priority, progressBar, waitingTime));
				}
			}
			
			inputStream.close();


//			// Loop printing of output every second
//			for(int i = 0; i<totalBurstTime-1; i++) {
//				Thread.sleep(1000);
//				System.out.println("\n-----------------------\nSeconds Elapsed: " + i);
//				System.out.println("--------------------------------------------------------------------------");
//				System.out.println("|  Process ID  |  Arrival Time  |  Burst Time  |  Priority  |  Progress  \n");
//				fcfs.print();
//				//sjf.print();
//				//rr.print();
//			}
//			
			//fcfs.print();
			//sjf.print();
			//rr.print();
			
			printQueues();
			
			// Side project to check if it's possible to print progress bars alongside an executing process in the console
			//			for (int i = 0; i< high.length; i++)
			//			{
			//				for( int j = 0; j< Process.data.getBurstTime(); j++)
			//				{
			//					
			//				}
			//			}
			//			high.printQueue();
			//			medium.printQueue();
			//			low.printQueue();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Print out the contents of all the queues
	public static void printQueues() throws InterruptedException {
		
		FCFS fcfs = new FCFS(fcfsList);
		SJF  sjf  = new SJF(sjfList);
		RR   rr   = new RR(rrList);

		// Loop printing of output every second
		while(i < totalBurstTime +1) {
			Thread.sleep(1000);
			System.out.println("\n-----------------------\nSeconds Elapsed: " + i);
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("|  Process ID  |  Arrival Time  |  Burst Time  |  Priority  |  Progress  \n");
			fcfs.print();
			sjf.print();
			rr.print();	
		}
	}
}