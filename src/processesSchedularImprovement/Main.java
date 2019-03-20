package processesSchedularImprovement;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** Proposed solution with a priority queue scheduling program*/

public class Main {

	public static void main(String args[])
	{
		progressBars bars = new progressBars();
		openCSV();
		bars.showBars();
	}

	public static void openCSV()
	{
        priorityQueue<process> pq = new priorityQueue<process>();
		System.out.println("\n|  Process ID  |  Arrival Time  |  Burst Time  |  Priority  |\n");
		/*
		 * Open CSV file
		 */
		String fileName = "processesFile.csv";
		File file = new File(fileName); //Read in the file
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNext())
			{
				String data = inputStream.nextLine(); //Currently reads in the entire line
				String[] details = data.split(",");
				  
				  String processID = details[0]; 
				  int arrivalTime  = Integer.parseInt(details[1]); 
				  int burstTime    = Integer.parseInt(details[2]);
				  int priority     = Integer.parseInt(details[3]);
				  
				  pq.add(new process(processID, arrivalTime, burstTime, priority));
				  
				 
				System.out.printf("%9s %15s %15s %13s %n", processID, arrivalTime, burstTime, priority); 

			}
			inputStream.close();
			System.out.println("\nProcesses ordered by priority\n");
			pq.printQueue();

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	public static void processInformation(String processID[], int n,
			int burstTime[], int priority[])
	{
		/*
		 * Make a copy of burstTimes[] to store remaining burst times
		 */
		int rem_burstTime[] = new int[n];
		for (int i = 0; i < n; i++)
		{
			rem_burstTime[i] = burstTime[i];
		}		    
	}

}
