package processesSchedularImprovement;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class progressBars {

	static JFrame f;
	static JProgressBar b1, b2, b3, b4, b5, b6, b7, b8, b9;
	//static JProgressBar[] bars = {b1, b2, b3, b4, b5, b6, b7, b8, b9}; // Array of progress bars that doesn't work (See line 81)

	public void showBars()
	{
		// create a frame
		f = new JFrame("Process's Progress");

		// create a panel
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBorder(new EmptyBorder(new Insets(20, 100, 20, 20)));
		

		// create progress bars
		b1 = new JProgressBar();
		b2 = new JProgressBar();
		b3 = new JProgressBar();
		b4 = new JProgressBar();
		b5 = new JProgressBar();
		b6 = new JProgressBar();
		b7 = new JProgressBar();
		b8 = new JProgressBar();
		b9 = new JProgressBar();

		// add title and centre it
		JLabel jlabel = new JLabel("Process Progress Bars", SwingConstants.CENTER);
		jlabel.setFont(new Font("Verdana",2,30));
		p.add(jlabel);	
		
		// add process titles
		JLabel jlabel2 = new JLabel("Process 1", SwingConstants.LEFT);

		// set initial values for progress bars 
		b1.setValue(0); b1.setStringPainted(true); b1.setForeground(Color.black);
		b2.setValue(0); b2.setStringPainted(true); b2.setForeground(Color.black);	
		b3.setValue(0); b3.setStringPainted(true); b3.setForeground(Color.black);
		b4.setValue(0); b4.setStringPainted(true); b4.setForeground(Color.black);
		b5.setValue(0); b5.setStringPainted(true); b5.setForeground(Color.black);
		b6.setValue(0); b6.setStringPainted(true); b6.setForeground(Color.black);	
		b7.setValue(0); b7.setStringPainted(true); b7.setForeground(Color.black);		
		b8.setValue(0); b8.setStringPainted(true); b8.setForeground(Color.black);	
		b9.setValue(0); b9.setStringPainted(true); b9.setForeground(Color.black);

		// add progress bars
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b1); 
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b2);	
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b3);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b4);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b5);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b6);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b7);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b8);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(b9);

		// add panel
		f.add(p);

		// set the size of the frame
		f.setSize(500, 1000);
		f.setVisible(true);

		fill1();	
		fill2(); 
		fill3(); 
		fill4(); 
		fill5();
		fill6(); 
		fill7(); 
		fill8(); 
		fill9();

	}
	// function to increase progress of the bar
	public static void fill1()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b1.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 33;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b1.setValue(100);
			b1.setForeground(Color.green);
		}
	}

	// function to increase progress of the bar
	public static void fill2()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b2.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 20;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b2.setValue(100);
			b2.setForeground(Color.green);
		}
	}

	// function to increase progress of the bar
	public static void fill3()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b3.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 11;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b3.setValue(100);
			b3.setForeground(Color.green);
		}
	}

	// function to increase progress of the bar
	public static void fill4()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b4.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 7;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b4.setValue(100);
			b4.setForeground(Color.green);
		}
	}
	// function to increase progress of the bar
	public static void fill5()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b5.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 20;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b5.setValue(100);
			b5.setForeground(Color.green);
		}
	}
	// function to increase progress
	// function to increase progress of the bar
	public static void fill6()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b6.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 100;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b6.setValue(100);
			b6.setForeground(Color.green);
		}
	}

	// function to increase progress
	// function to increase progress of the bar
	public static void fill7()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b7.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 15;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b7.setValue(100);
			b7.setForeground(Color.green);
		}
	}
	// function to increase progress
	// function to increase progress of the bar
	public static void fill8()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b8.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 25;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b8.setValue(100);
			b8.setForeground(Color.green);
		}
	}
	
	// function to increase progress
	// function to increase progress of the bar
	public static void fill9()
	{
		//set value to count increments in the progress bar
		int i = 0;
		try {
			while (i <= 100)
			{				
				// fill the menu bar
				b9.setValue(i);

				// delay the thread
				Thread.sleep(1000);
				i += 8;
			}
		}
		catch (Exception e) {}

		// set bar colour to green when it's full
		if (i >= 100) {
			b9.setValue(100);
			b9.setForeground(Color.green);
		}
	}
}