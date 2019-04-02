package processesSchedularImprovement;

public class countTime {

static Thread thread = new Thread();

public void counter() throws InterruptedException 
{
	for(int i = 0; i<10000; i++)
	{
		Thread.sleep(1000);
		System.out.println("Seconds Elapsed: " + i);
	}
}

}


