package processesSchedularImprovement;

/*
 * Class to hold the behaviour and states of a priority queue
 */
public class pQueue<T> {
	
	private ThisProcess head;
	private ThisProcess tail;
	public int length;
	
	/*
	 * Add processes to their respective queues
	 */
	public void add(Process data)
	{
		ThisProcess currentProcess = new ThisProcess(data);
		
		if(isEmpty())
		{
			head = currentProcess;
			tail = currentProcess;
		}
		
		else {

		ThisProcess temp = head;
				
		  while(temp.next != null) 
		  { 
			  temp = temp.next;
		  }
		  
		  if(temp.next == null) 
		  { 
			  tail.next = currentProcess;
			  tail = currentProcess;
			  currentProcess.next = null;		  
		  }	 
		}
		// Increase the length of the queues each time a process is added
		  length++;
	}

	/*
	 * If called, check to see if any of the queues are empty
	 */
	public boolean isEmpty() 
	{
		return length == 0; 
	}
	
	/*
	 * Iterate through a queue and print its' contents
	 */
	public void printQueue()
	{
		if(isEmpty())
		{
			return;
		}
		
		ThisProcess temp = head;
		
		while(temp != null)
		{
			System.out.printf("%9s %15s %15s %13s %n", temp.data.processID, temp.data.arrivalTime, temp.data.burstTime, temp.data.priority);
			temp = temp.next;
		}
		length--;
	}
	
}