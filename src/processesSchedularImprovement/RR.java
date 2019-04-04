package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RR extends Scheduler {
	
	private static final int QUANTUM = 2;
	private static List<Process> RRList = new ArrayList<Process>();
	private static List<Integer> arrivalTime = new ArrayList<Integer>();
    private static Map<Integer, Integer> responseTime = new HashMap<Integer, Integer>();
	
//	private void prepareList(List<Process> process, int returnAux) {
//		List<Process> p = new ArrayList<Process>(process);
//		int min = 0;
//		
//		for (Process p1: process) {
//			if (!arrivalTime.contains(p1.getArrivalTime()) && p1.getArrivalTime() <= returnAux)	{
//				if (!RRList.contains(p1)) {
//					min = p1.getArrivalTime();
//					arrivalTime.add(min);
//					
//					for (Process p2: process) {
//						if (p2.getArrivalTime() == min) {
//							//RRList.add(p2);
//						}
//					}
//				}
//			}
//			RRList.add(p.remove(0));
//		}
//	}
	
	private int responseTimeTotal() {
		int sumResponse = 0;
	    for (int key : responseTime.keySet()) {
	    	sumResponse += responseTime.get(key);
	    }
	    return sumResponse;
	}
	
	public RR(List<Process> process) {
		
		RRList             = new ArrayList<Process>(process);
		int returnTime     = 0;
		int waitingTime    = 0;
		int totalProcesses = super.getAmountOfProcesses(process);
		int arrivalProcess = arrivalMin(process);
		
		//prepareList(process, arrivalProcess);
		
		//As long as there are processes in the list
//		while (!RRList.isEmpty()) {
//			Process p = RRList.remove(0);
//			
//			//key: process
//			//value: time it was attended to
//			if(!responseTime.containsKey(p.getProcessID())) {
//				// Not currently working because processID isn't an integer                  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				// Temporarily changed processID to an integer
//				responseTime.put(p.getProcessID(), arrivalProcess - p.getArrivalTime());
//			}
//			
//			if(p.getRemainingBurstTime() > QUANTUM) {
//				p.setRemainingBurstTime(p.getRemainingBurstTime() - QUANTUM);
//				arrivalProcess += QUANTUM;
//				//prepareList(process, arrivalProcess);
//				
//				RRList.add(p);
//			}
//			
//			else {
//				arrivalProcess += p.getRemainingBurstTime();
//			}
//			
//			if(!RRList.contains(p)) {
//				returnTime  += (arrivalProcess - p.getArrivalTime());
//				waitingTime += (arrivalProcess - p.getArrivalTime() - p.getBurstTime());
//			}	
//		}
		
		super.setAvgTurnaroundTime((float) returnTime / totalProcesses);
		super.setAvgResponseTime((float) responseTimeTotal() / totalProcesses);
		super.setAvgWaitingTime((float) waitingTime / totalProcesses);
	}
	
	// Print out Round Robin list, taking account of the quantum
	public void print() throws InterruptedException {
		
		for (Process p: RRList) {
		 System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority);
	     System.out.print("          ");
	     
			if (Main.i == 0)
			{
				if (p.burstTime % 2 == 0 || p.burstTime > 1) {
					Main.i += 2;
					p.progressBar += "||";
					p.burstTime -= QUANTUM;
				}
				else {
					Main.i++;
					p.progressBar += "|";
					p.burstTime --;
				}
				
				Main.rrList.add(Main.rrList.remove(0));
				Main.printQueues();
			}
			
			while(p.burstTime != 0) {
				if (p.burstTime % 2 == 0 || p.burstTime > 1) {
					System.out.print(p.progressBar);
					p.progressBar += "||";
					Main.i += 2;
					p.burstTime -= QUANTUM;
				}
				else {
					System.out.print(p.progressBar);
					p.progressBar += "|";
					Main.i++;
					p.burstTime--;
				}
				
				// As long as the process has burst time left, add it to the back of the queue
				//  after it's time quantum has elapsed
                if (p.burstTime != 0) {
				Main.rrList.add(Main.rrList.remove(0));
                }
				Main.printQueues();
			}
			System.out.print(p.progressBar + " Done\n");
		 }		
		//Print out some of the averages for the RR algorithm
		//super.print("RR");
	}
}
