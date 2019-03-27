package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collections;
import java.util.List;

public class SJF extends Scheduler {

	private static List<Process> SJFList = new ArrayList<Process>();
	
	private void prepareList(List<Process> process)
	{
		List<Process> p = new ArrayList<Process>(process);
		int returnSum = 0;
		int min = 0;
		int pivo = 0;
		
		Collections.sort(p);
		SJFList.add(p.remove(0));
		returnSum = SJFList.get(0).getBurstTime();
		
		while (p.size() > 1)
		{
			for (int i = 1; i < p.size(); i++)
			{
				if (p.get(pivo).getBurstTime() <= p.get(i).getBurstTime()
						&& p.get(pivo).getArrivalTime() < returnSum)
				{
					min = pivo;
				}
				else if (p.get(i).getArrivalTime() < returnSum)
				{
					pivo = i;
					min = i;
				}
			}
			
			returnSum += p.get(min).getBurstTime();
			SJFList.add(p.remove(0));
		}
		
		if (p.size() == 1)
		{
			SJFList.add(p.remove(0));
		}
	}
	
	public SJF(List<Process> process)
	{
		int returnTime        = 0;
		int responseTime      = 0;
		int waitingTime       = 0;
		int totalProcesses    = super.getAmountOfProcesses(process);
		int arrivalProcess    = arrivalMin(process);
		prepareList(process);
		
		//While there are processes
		for (Process p: SJFList)
		{
			arrivalProcess += p.getBurstTime();
			returnTime += (arrivalProcess - p.getArrivalTime());
			waitingTime   += (arrivalProcess - p.getArrivalTime() - p.getBurstTime());
		}
		
		responseTime = waitingTime;
		
		super.setAvgTurnaroundTime((double) returnTime / totalProcesses);
		super.setAvgResponseTime((double) responseTime / totalProcesses);
		super.setAvgWaitingTime((double) waitingTime / totalProcesses);
	}
	
	public void print()
	{
		for (Process p: SJFList)
		 {
		 System.out.printf("%9s %15s %15s %13s %n", p.processID, p.arrivalTime, p.burstTime, p.priority);
		 }
		//Print out some of the averages for the SJF algorithm
		//super.print("SJF");
	}
}
