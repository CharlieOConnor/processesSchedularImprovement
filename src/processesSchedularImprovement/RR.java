package processesSchedularImprovement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RR extends Scheduler {

	public  static ArrayList<Process> tempQueue     = new ArrayList<Process>();
	public  static ArrayList<Process> waitingQueue  = new ArrayList<Process>();
	public  static ArrayList<Process> readyQueue    = new ArrayList<Process>();
	public  static ArrayList<Process> finishedQueue = new ArrayList<Process>();

	private static final int QUANTUM = 2;

	public RR(List<Process> process) {

		tempQueue          = new ArrayList<Process>(process);

		// Sort processes based on their arrival time into the system
		tempQueue.sort(Comparator.comparing(Process::getArrivalTime));
	}

	// Print out Round Robin list, taking account of the quantum
	public void print() throws InterruptedException {   

		for (Process p: readyQueue) {
			System.out.printf("%9s %15s %15s %13s", p.processID, p.arrivalTime, p.burstTime, p.priority);
			System.out.print("          ");

			while(p.burstTime != 0) {

				if (p.burstTime >= 2) {

					for (Process p1: openCSV.collectiveQueue) {

						if(p1.getArrivalTime() == openCSV.currentTime +1 && p1.getPriority() < 3) {
							p.progressBar += "|";
							System.out.print(p.progressBar);
							p.burstTime--;
							openCSV.currentTime++;
							openCSV.printQueues();
						}
					}
				}

				if (p.burstTime % 2 == 0 || p.burstTime > 2)
				{
					System.out.print(p.progressBar);
					openCSV.currentTime += QUANTUM;
					p.progressBar += "||";
					p.burstTime -= QUANTUM;
					openCSV.printQueues();	
				}

				else {
					System.out.print(p.progressBar);
					openCSV.currentTime++;
					p.progressBar += "|";
					p.burstTime--;
				}
			}

			if(readyQueue.get(0).burstTime == 0) {			
				finishedQueue.add(readyQueue.remove(0));	
                openCSV.rrQueue.remove(0);
			}

			//System.out.print(p.progressBar + " Done\n");
			openCSV.printQueues();
		}
	}		
}
