package processesSchedularImprovement;

public class priorityQueue<T> {
	
	private listNode head;
	private listNode tail;
	public int length;
	
	public priorityQueue()
	{
		this.head = null;
		this.length = 0;
	}
	
	public boolean isEmpty()
	{
		return length == 0;
	}
	
	public void add(process data)
	{
		boolean sort = false;
		listNode currentNode = new listNode(data);
		
		//If the queue is empty, add the process to the top
		if(isEmpty())
		{
			head = currentNode;
			tail = currentNode;
		}
		
		else
		{
			listNode temp = head;
			
			if(currentNode.data.priority <= head.data.priority && sort != true)
			{
				currentNode.next = head;
				head.previous = currentNode;
				currentNode.previous = null;
				head = currentNode;
				sort = true; //End add method		
			}
			//Add currentNode to the end of the queue if there's only one process in the queue
			if(currentNode.data.priority > head.data.priority && length == 1 && sort != true) 
			{
				currentNode.previous = tail;
				tail.next = currentNode;
				currentNode.next = null;
				tail = currentNode;
				sort = true; //End add method
			}

			if (length > 1 && sort != true) 
			{       	  
				while(currentNode.data.priority > temp.data.priority && temp.next != null) 
				{
					temp = temp.next; //Use a temporary value to iterate through the queue
				}
				//Keep iterating through the queue until the currentNode's priority is >= the temp's prioirity
				if (currentNode.data.priority <= temp.data.priority) 
				{
					listNode storeNext = temp;
					temp.previous.next = currentNode;
					currentNode.previous = temp.previous;
					temp.previous = currentNode;
					currentNode.next = storeNext;
					sort = true; //End add method
				}
				//If the currentNode's priority is greater than the tail, make it the new tail
				if (currentNode.data.priority > temp.data.priority && sort != true)
				{
					tail.next = currentNode;
					currentNode.previous = tail;
					currentNode.next = null;
					tail = currentNode;
					sort = true; //End add method  	  
				}
			}		
		}
		length++;
	}
	
	//Iterate through the queue and print the processes one by one until it's empty
	public void printQueue()
	{	
		if (head == null)
		{
			return;
		}
		
		listNode temp = head;
		
		while(temp != null)
		{
			System.out.println(temp.data);
			temp = temp.next;
		}		
		length--;
	}

}
