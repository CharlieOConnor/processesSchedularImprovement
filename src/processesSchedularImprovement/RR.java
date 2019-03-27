package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RR extends Scheduler {
	
	private static final int QUANTUM = 4;
	private static List<Process> RRList = new ArrayList<Process>();
	private static List<Integer> arrivalTime = new ArrayList<Integer>();
    private static Map<Integer, Integer> responseTime = new HashMap<Integer, Integer>();
	
	private void prepareList(List<Process> process, int returnAux)
	{
		int min = 0;
		
		for (Process p1: process)
		{
			if (!arrivalTime.contains(p1.getArrivalTime()) && p1.getArrivalTime() <= returnAux)
			{
				if (!RRList.contains(p1))
				{
					min = p1.getArrivalTime();
					arrivalTime.add(min);
					for (Process p2: process)
					{
						if (p2.getArrivalTime() == min)
						{
							RRList.add(p2);
						}
					}
				}
			}
		}
	}
	
	private int responseTimeTotal()
	{
		int sumResponse = 0;
	    for (int key : responseTime.keySet())
	    {
	    	sumResponse += responseTime.get(key);
	    }
	    return sumResponse;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public RR(List<Process> process)
	{
		int returnTime     = 0;
		int waitingTime    = 0;
		int totalProcesses = super.getAmountOfProcesses(process);
		int arrivalProcess = arrivalMin(process);
		
		prepareList(process, arrivalProcess);
		
		//As long as there are processes in the list
		while (!RRList.isEmpty())
		{
			Process p = RRList.remove(0);
			
			//key: process
			//value: time it was attended to
			if(!responseTime.containsKey(p.getProcessID()))
			{
				// Not currently working because processID isn't an integer
				//responseTime.put(p.getProcessID(), arrivalProcess - p.getArrivalTime());
			}
			
			if(p.getRemainingBurstTime() > QUANTUM)
			{
				p.setRemainingBurstTime(p.getRemainingBurstTime() - QUANTUM);
				arrivalProcess += QUANTUM;
				prepareList(process, arrivalProcess);
				
				RRList.add(p);
			}
			
			else
			{
				arrivalProcess += p.getRemainingBurstTime();
			}
			
			if(!RRList.contains(p))
			{
				returnTime  += (arrivalProcess - p.getArrivalTime());
				waitingTime += (arrivalProcess - p.getArrivalTime() - p.getBurstTime());
			}	
		}
		
		super.setAvgTurnaroundTime((float) returnTime / totalProcesses);
		super.setAvgResponseTime((float) responseTimeTotal() / totalProcesses);
		super.setAvgWaitingTime((float) waitingTime / totalProcesses);
	}
	
	public void print()
	{
		for (Process p: RRList)
		 {
		 System.out.printf("%9s %15s %15s %13s %n", p.processID, p.arrivalTime, p.burstTime, p.priority);
		 }
		//Print out some of the averages for the RR algorithm
		//super.print("RR");
	}

}