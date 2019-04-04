package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF extends Scheduler {

	private static List<Process> SJFList = new ArrayList<Process>();
	
//	private void prepareList(List<Process> process)
//	{
//		List<Process> p = new ArrayList<Process>(process);
//		int burstTimeSum = 0;
//		int shortest = 0;
//		int temp = 0;
//		
//		// Sort processes based on their arrival time into the system
//		SJFList.add(p.remove(0));
//		burstTimeSum = SJFList.get(0).getBurstTime();
//		
//		while (p.size() > 1)
//		{
//			for (int i = 1; i < p.size(); i++)
//			{
//				if (p.get(temp).getBurstTime() <= p.get(i).getBurstTime()
//						&& p.get(temp).getArrivalTime() < burstTimeSum)
//				{
//					shortest = temp;
//				}
//				else if (p.get(i).getArrivalTime() < burstTimeSum)
//				{
//					temp = i;
//					shortest = i;
//				}
//			}
//			
//			burstTimeSum += p.get(shortest).getBurstTime();
//			SJFList.add(p.remove(0));
//		}
//		
//		if (p.size() == 1)
//		{
//			SJFList.add(p.remove(0));
//		}
//	}
	
	public SJF(List<Process> process)
	{
		SJFList               = new ArrayList<Process>(process);		
		int returnTime        = 0;
		int responseTime      = 0;
		int waitingTime       = 0;
		
		int totalProcesses    = super.getAmountOfProcesses(process);
		int arrivalProcess    = arrivalMin(process);
		
		//prepareList(process);
		// Sort list by burst time (from low to high)
		SJFList.sort(Comparator.comparing(Process::getBurstTime));
		
		//While there are processes
		for (Process p: SJFList)
		{
			arrivalProcess += p.getBurstTime();
			returnTime     += (arrivalProcess - p.getArrivalTime());
			waitingTime    += (arrivalProcess - p.getArrivalTime() - p.getBurstTime());
		}
		
		responseTime = waitingTime;
		
		super.setAvgTurnaroundTime((double) returnTime / totalProcesses);
		super.setAvgResponseTime((double) responseTime / totalProcesses);
		super.setAvgWaitingTime((double) waitingTime / totalProcesses);
	}
	
	// Print out Shortest Job First list
	public void print() throws InterruptedException	{
		
		for (Process p: SJFList) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority, p.progressBar);
			System.out.print("          ");
			
			if (Main.i == 0) {
				Main.i++;
				p.progressBar += "|";
				p.burstTime--;
				Main.printQueues();
			}
			
			while(p.burstTime != 0) {
				System.out.print(p.progressBar);
				p.progressBar += "|";
				Main.i++;
				p.burstTime--;
			    Main.printQueues();	
			}
			System.out.print(p.progressBar + " Done\n");
		}
		//Print out some of the averages for the FCFS algorithm
		//super.print("FCFS");
	}
}