package natalieToursTests.backAndFrontFirstPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class frame4Hotel implements ActionListener,Runnable{
	 JFrame frame;
	 JPanel buttonPane, fieldsPanel;
	 JLabel countryL,toL,whenToL,whenBackL,hotelL,roomQuantityL,adultQuantityL,childQuantityL,childAge1L,childAge2L,childAge3L;
	 static JTextField country,to,whenTo, whenBack,hotel,roomQuantity, adultQuantity, childQuantity,childAge1,childAge2,childAge3;
	 JButton ok,cancel;
	 static JCheckBox cancelOrNot;
	 
	 frame4Hotel(){
		 frame = new JFrame("HOTEL TEST");
		 buttonPane = new JPanel();
	     fieldsPanel = new JPanel();
	     
	     countryL = new JLabel("Country");
	     country = new JTextField("spain");
	     toL = new JLabel("Region");
	     to = new JTextField("barcelona");
	     whenToL = new JLabel("Arrival date ddMMyyyy");
	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
	     LocalDate localDate1 = LocalDate.now().plusMonths(3).plusDays(1);
	     whenTo = new JTextField(dtf.format(localDate1));
	     whenBackL = new JLabel("Departure date ddMMyyyy");
	     LocalDate localDate2 = LocalDate.now().plusMonths(3).plusDays(8);
	     whenBack = new JTextField(dtf.format(localDate2));
	     hotelL = new JLabel("Hotel name");
	     hotel = new JTextField("hotel test");
	     roomQuantityL = new JLabel("Room quantity");
	     roomQuantity= new JTextField("1");
	     adultQuantityL = new JLabel("Adult quantity");
	     adultQuantity = new JTextField("2");
	     childQuantityL = new JLabel("Child quantity");
	     childQuantity = new JTextField("2");
	     childAge1L = new JLabel("1st child age");
	     childAge1 = new JTextField("5");
	     childAge2L = new JLabel("2nd shild age");
	     childAge2 = new JTextField("1");
	     childAge3L = new JLabel("3rd child age");
	     childAge3 = new JTextField("10");
	     ok = new JButton("Proceed");
	     cancel = new JButton("Cancel");
	     cancelOrNot = new JCheckBox("Cancel booking?");
	     
	     ok.addActionListener(this);
	     ok.setActionCommand("go");
	     cancel.addActionListener(this);
	     cancel.setActionCommand("abort");
	     
	     fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
	     buttonPane.setLayout(new FlowLayout());
	     
	     fieldsPanel.add(countryL);
	     fieldsPanel.add(country);
	     fieldsPanel.add(toL);
	     fieldsPanel.add(to);
	     fieldsPanel.add(whenToL);
	     fieldsPanel.add(whenTo);
	     fieldsPanel.add(whenBackL);
	     fieldsPanel.add(whenBack);
	     fieldsPanel.add(hotelL);
	     fieldsPanel.add(hotel);
	   //  fieldsPanel.add(roomQuantityL);
	    // fieldsPanel.add(roomQuantity);
	     fieldsPanel.add(adultQuantityL);
	     fieldsPanel.add(adultQuantity);
	     fieldsPanel.add(childQuantityL);
	     fieldsPanel.add(childQuantity);
	     fieldsPanel.add(childAge1L);
	     fieldsPanel.add(childAge1);
	     fieldsPanel.add(childAge2L);
	     fieldsPanel.add(childAge2);
	     fieldsPanel.add(childAge3L);
	     fieldsPanel.add(childAge3);
	     fieldsPanel.add(cancelOrNot);
	     buttonPane.add(ok);
	     buttonPane.add(cancel);
	     
	     frame.add(fieldsPanel, BorderLayout.PAGE_START);
	        frame.add(buttonPane, BorderLayout.PAGE_END);

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        
	 }
	     public void actionPerformed(ActionEvent ae) { 
	         String action = ae.getActionCommand();
	         if (action.equals("go")) {
	             System.out.println("Button pressed! Preparing Hotel book and cancel test ...");
	             new JUnitCore();
				Result result = JUnitCore.runClasses(Hotel.class);
				System.out.println(result.getFailures());
				
	            }
	            if (action.equals("abort")){
	            	frame.dispose();
	            }
	        }

	 public static ArrayList<String> getKinderAge(){
		 ArrayList<String> a = new ArrayList<String>();
		 a.add(childAge1.getText());
		 a.add(childAge2.getText());
		 a.add(childAge3.getText());
		 return a;
	 }
	public void run() {
		// TODO Auto-generated method stub
//		new frame4Hotel();
	}

}
