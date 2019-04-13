package processesSchedularImprovement;

public class Main extends openCSV {
	
	public static void main(String args[]) throws InterruptedException {
		
	   System.out.printf("\n%60s", "-------------------------------");	
	   System.out.printf("\n%60s", "Processes Scheduler Improvement");
	   System.out.printf("\n%60s", "-------------------------------");	
	   
	   openCSV.openCSV();
	   openCSV.printQueues();
	}
}