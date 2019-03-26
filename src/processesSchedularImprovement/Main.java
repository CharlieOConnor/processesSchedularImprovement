package processesSchedularImprovement;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** Proposed solution with a multi-level queue scheduling program*/

public class Main {

	public static void main(String args[])
	{
		openCSV();
		
		//progressBars bars = new progressBars();	
		//bars.showBars();
	}

	 /*-------------------------------------------------------
	  * To Do: remove other queues and use a placeholder queue to hold the variables 
	  * and allocate them accordingly to other queues based on the chosen sorting algorithms
	  */

	public static void openCSV()
	{
        pQueue<Process> high = new pQueue<Process>();
        pQueue<Process> medium = new pQueue<Process>();
        pQueue<Process> low = new pQueue<Process>();
        
        
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
				  
				  /*
				   * 
				   * 
				   * 
				   * Change the queue names to correspond  to the algorithms in the SchedulingAlgorithms class
				   * 
				   * 
				   * 
				   */
				  if (priority == 1)
				  {
				    high.add(new Process(processID, arrivalTime, burstTime, priority));
				  }
				  else if (priority == 2)
				  {
				    medium.add(new Process(processID, arrivalTime, burstTime, priority));
				  }
				  else 
				  {
					low.add(new Process(processID, arrivalTime, burstTime, priority));
				  }
			}
			inputStream.close();
			
			// Side project to check if it's possible to print progress bars alongside an executing process in the console
			/*
			 * for (int i = 0; i< high.length; i++) { for( int j = 0; j<
			 * Process.data.getBurstTime(); j++) {
			 * 
			 * } }
			 */
			high.printQueue();
			medium.printQueue();
			low.printQueue();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}