package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF extends Scheduler {

	public  static ArrayList<Process> tempQueue      = new ArrayList<Process>();
	public  static ArrayList<Process> waitingQueue   = new ArrayList<Process>();
	public  static ArrayList<Process> readyQueue     = new ArrayList<Process>();
	public  static ArrayList<Process> finishedQueue  = new ArrayList<Process>();
	

	
	public SJF(List<Process> process)
	{
		tempQueue = new ArrayList<Process>(process);		

		// Sort list by arrival time
		tempQueue.sort(Comparator.comparing(Process::getArrivalTime));

	}

	// Swap the new process (that has a lower burst time) with the one currently executing
	public static void sortBySJF() throws InterruptedException {

		Process temp = waitingQueue.get(0);	
		waitingQueue.remove(0);
		waitingQueue.add(readyQueue.remove(0));
		waitingQueue.sort(Comparator.comparing(Process::getBurstTime));
		readyQueue.add(temp);
		openCSV.totalWaitingTime++;
		System.out.println("Current process swapped via SJF\n");
	}

	// Print out Shortest-Job-First list
	public void print() throws InterruptedException	{
		
		// First print out all the processes with no remaining burst time
		for (Process p: finishedQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			System.out.print(p.progressBar + " Done\n");
		}
		
		// If the CPU doesn't have anything to execute at the moment, increment totalWaitingTime
		if(waitingQueue.size() == 0 && readyQueue.size() == 0) {
			openCSV.totalWaitingTime++;
		}

		for (Process p: readyQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			
			while(p.burstTime != 0) {
				System.out.print(p.progressBar);
				p.progressBar += "|";
				openCSV.currentTime++;
				p.burstTime--;
			    openCSV.printQueues();	
			}
			
			if(readyQueue.size() == 1 && readyQueue.get(0).burstTime == 0) {	
				System.out.print(readyQueue.get(0).progressBar + " Done\n");
				finishedQueue.add(readyQueue.remove(0)); // Move the finished process to a new queue	
				openCSV.collectiveQueue.remove(0);       // Improve efficiency of searches by removing processes with no burst time remaining
    			openCSV.currentTime++;		                
    			openCSV.printQueues();                    
			}
		}

	}
}