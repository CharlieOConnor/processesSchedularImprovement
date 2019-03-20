package processesSchedularImprovement;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class progressBars {
	
	static JFrame f;
	static JProgressBar b1, b2, b3, b4, b5, b6, b7, b8, b9;
	
	public static void showBars()
	{
		// create a frame
		f = new JFrame("Process's Progress");
		
		// create a panel
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(10, 1, 30, 20));
		
		// create a progressbar
		b1 = new JProgressBar();
		b2 = new JProgressBar();
		b3 = new JProgressBar();
		b4 = new JProgressBar();
		b5 = new JProgressBar();
		b6 = new JProgressBar();
		b7 = new JProgressBar();
		b8 = new JProgressBar();
		b9 = new JProgressBar();
		
		/*
		 * for(int i = 1; i <= 9; i++) { }
		 */
		
		JLabel jlabel = new JLabel("Process 1");
		jlabel.setFont(new Font("Verdana",1,10));
		p.add(jlabel);	
		
		// set initial value 
		b1.setValue(0);
		b1.setStringPainted(true);
		b1.setForeground(Color.black);
		
		b2.setValue(0);
		b2.setStringPainted(true);
		b2.setForeground(Color.black);
		
		b3.setValue(0);
		b3.setStringPainted(true);
		b3.setForeground(Color.black);
		
		b4.setValue(0);
		b4.setStringPainted(true);
		b4.setForeground(Color.black);
		
		b5.setValue(0);
		b5.setStringPainted(true);
		b5.setForeground(Color.black);
		
		b6.setValue(0);
		b6.setStringPainted(true);
		b6.setForeground(Color.black);
		
		b7.setValue(0);
		b7.setStringPainted(true);
		b7.setForeground(Color.black);
		
		b8.setValue(0);
		b8.setStringPainted(true);
		b8.setForeground(Color.black);
		
		b9.setValue(0);
		b9.setStringPainted(true);
		b9.setForeground(Color.black);

		
		// add progressbar
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.add(b7);
		p.add(b8);
		p.add(b9);
		
		// add panel
		f.add(p);
		
		// set the size of the frame
		f.setSize(500, 500);
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
		
		// function to increase progress
		public static void fill1()
		{
			int i = 0;
			try {
				while (i <= 100)
				{
					// set text according to the level to which the bar is filled
					if ( i < 30)
						b1.setString("Loading started");
				    else if (i > 30 && i < 70)
						b1.setString("In progress");
					else if (i > 70 && i <100)
						b1.setString("Almost finished loading");
					else if (i == 100)
						b1.setString("Finished");
					
					// fill the menu bar
					b1.setValue(i);
					
					// delay the thread
					Thread.sleep(500);
					i += 10;
				}
			}
			catch (Exception e) {}
		}
		
		// function to increase progress
				public static void fill2()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b2.setString("Loading started");
						    else if (i > 30 && i < 70)
								b2.setString("In progress");
							else if (i > 70 && i <100)
								b2.setString("Almost finished loading");
							else if (i == 100)
								b2.setString("Finished");
							
							// fill the menu bar
							b2.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill3()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b3.setString("Loading started");
						    else if (i > 30 && i < 70)
								b3.setString("In progress");
							else if (i > 70 && i <100)
								b3.setString("Almost finished loading");
							else if (i == 100)
								b3.setString("Finished");
							
							// fill the menu bar
							b3.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill4()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b4.setString("Loading started");
						    else if (i > 30 && i < 70)
								b4.setString("In progress");
							else if (i > 70 && i <100)
								b4.setString("Almost finished loading");
							else if (i == 100)
								b4.setString("Finished");
							
							// fill the menu bar
							b4.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill5()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b5.setString("Loading started");
						    else if (i > 30 && i < 70)
								b5.setString("In progress");
							else if (i > 70 && i <100)
								b5.setString("Almost finished loading");
							else if (i == 100)
								b5.setString("Finished");
							
							// fill the menu bar
							b5.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill6()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b6.setString("Loading started");
						    else if (i > 30 && i < 70)
								b6.setString("In progress");
							else if (i > 70 && i <100)
								b6.setString("Almost finished loading");
							else if (i == 100)
								b6.setString("Finished");
							
							// fill the menu bar
							b6.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill7()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b7.setString("Loading started");
						    else if (i > 30 && i < 70)
								b7.setString("In progress");
							else if (i > 70 && i <100)
								b7.setString("Almost finished loading");
							else if (i == 100)
								b7.setString("Finished");
							
							// fill the menu bar
							b7.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill8()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b8.setString("Loading started");
						    else if (i > 30 && i < 70)
								b8.setString("In progress");
							else if (i > 70 && i <100)
								b8.setString("Almost finished loading");
							else if (i == 100)
								b8.setString("Finished");
							
							// fill the menu bar
							b8.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
				
				// function to increase progress
				public static void fill9()
				{
					int i = 0;
					try {
						while (i <= 100)
						{
							// set text according to the level to which the bar is filled
							if ( i < 30)
								b9.setString("Loading started");
						    else if (i > 30 && i < 70)
								b9.setString("In progress");
							else if (i > 70 && i <100)
								b9.setString("Almost finished loading");
							else if (i == 100)
								b9.setString("Finished");
							
							// fill the menu bar
							b9.setValue(i);
							
							// delay the thread
							Thread.sleep(500);
							i += 10;
						}
					}
					catch (Exception e) {}
				}
	}