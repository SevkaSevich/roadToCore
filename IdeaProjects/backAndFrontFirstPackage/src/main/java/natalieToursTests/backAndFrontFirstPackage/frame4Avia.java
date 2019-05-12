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

public class frame4Avia implements ActionListener,Runnable{
	 JFrame frame;
	 JPanel buttonPane, fieldsPanel,additionalPanel;
	 JLabel fromL,toL,whenToL,whenBackL,adultQuantityL,childQuantityL,childAge1L,childAge2L,childAge3L,classL,stopsL,preferredAirlinesL,priceL,baggageL,supplierL;
	 static JTextField from , to,whenTo, whenBack, adultQuantity, childQuantity,childAge1,childAge2,childAge3;
	public static JTextField preferredAirlines;
	public static JTextField priceFrom;
	public static JTextField priceTo;
	static JTextField supplier;
	 JButton ok,cancel;
	static JCheckBox oneWay,hardWay,straightWay,cancelOrNot;
	public static JComboBox<String> classOfService;
	public static JComboBox<String> stop;
	public static JComboBox<String> baggage;
	 String[] classesOfService = {"All","Economy","Economy Premium","Business"};
	 String[] stops = {"All","No stops","1 stop","2 stops"};
	 String[] baggages = {"All","Included","Not included"};
	 
	 frame4Avia(){
		 frame = new JFrame("AVIA TEST");
		 buttonPane = new JPanel();
	     fieldsPanel = new JPanel();
	     additionalPanel = new JPanel();
	     
	     fromL = new JLabel("Departure");
	     from = new JTextField("MOW");
	     toL = new JLabel("Arrival");
	     to = new JTextField("saint petersburg");
	     whenToL = new JLabel("First flight date ddMMyyyy");
	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
	     LocalDate localDate1 = LocalDate.now().plusMonths(3);
	     whenTo = new JTextField(dtf.format(localDate1));
	     whenBackL = new JLabel("Return flight date ddmmyyyy");
	     LocalDate localDate2 = LocalDate.now().plusMonths(3).plusDays(8);
	     whenBack = new JTextField(dtf.format(localDate2));
	     oneWay = new JCheckBox("One way");
	     //hardWay = new JCheckBox("Сложный маршрут");
	     //straightWay = new JCheckBox("Без пересадок");
	     adultQuantityL = new JLabel("Adult quantity(<=5)");
	     adultQuantity = new JTextField("2");
	     childQuantityL = new JLabel("Child quantity(<=3)");
	     childQuantity = new JTextField("2");
	     childAge1L = new JLabel("1st child age");
	     childAge1 = new JTextField("5");
	     childAge2L = new JLabel("2nd child age");
	     childAge2 = new JTextField("1");
	     childAge3L = new JLabel("3rd child age");
	     childAge3 = new JTextField("10");
	     cancelOrNot = new JCheckBox("Cancel booking?");
	     
	     ok = new JButton("Proceed");
	     cancel = new JButton("Cancel");
	     
	     classL = new JLabel("Flight class");
	     classOfService = new JComboBox<String>(classesOfService);
	     classOfService.setSelectedIndex(0);  
	     stopsL = new JLabel("Stop overs");
	     stop = new JComboBox<String>(stops);
	     stop.setSelectedIndex(0);
	     baggageL = new JLabel("Baggage");
	     baggage = new JComboBox<String>(baggages);
	     baggage.setSelectedIndex(0);
	     priceL = new JLabel("Price from/to");
	     priceFrom = new JTextField("1");
	     priceTo = new JTextField("12343");
	     preferredAirlinesL = new JLabel("Airlines(separated by ,)");
	     preferredAirlines = new JTextField("");
	     supplierL = new JLabel("Suppliers(separated by ,)");
	     supplier = new JTextField("");
	     
	     ok.addActionListener(this);
	     ok.setActionCommand("go");
	     cancel.addActionListener(this);
	     cancel.setActionCommand("abort");
	     
	     frame.setLayout(new FlowLayout());
	     fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
	     additionalPanel.setLayout(new  BoxLayout(additionalPanel, BoxLayout.Y_AXIS));
	     buttonPane.setLayout(new FlowLayout());
	     
	     fieldsPanel.add(fromL);
	     fieldsPanel.add(from);
	     fieldsPanel.add(toL);
	     fieldsPanel.add(to);
	     fieldsPanel.add(whenToL);
	     fieldsPanel.add(whenTo);
	     fieldsPanel.add(whenBackL);
	     fieldsPanel.add(whenBack);
	     fieldsPanel.add(oneWay);
	     //fieldsPanel.add(hardWay);
	     //fieldsPanel.add(straightWay);
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
	     additionalPanel.add(classL);
	     additionalPanel.add(classOfService);
	     additionalPanel.add(stopsL);
	     additionalPanel.add(stop);
	     additionalPanel.add(baggageL);
	     additionalPanel.add(baggage);
	     additionalPanel.add(priceL);
	     additionalPanel.add(priceFrom);
	     additionalPanel.add(priceTo);
	     additionalPanel.add(preferredAirlinesL);
	     additionalPanel.add(preferredAirlines);
	     additionalPanel.add(supplierL);
	     additionalPanel.add(supplier);
	     additionalPanel.add(cancelOrNot);
	     buttonPane.add(ok);
	     buttonPane.add(cancel);
	     
	     additionalPanel.add(buttonPane);
	     frame.add(fieldsPanel, BorderLayout.PAGE_START);
	     frame.add(additionalPanel, BorderLayout.PAGE_START);
	     //frame.add(buttonPane, BorderLayout.PAGE_END);

	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.pack();
	     frame.setLocationRelativeTo(null);
	     frame.setVisible(true);
	        
	 }
	     public void actionPerformed(ActionEvent ae) { 
	         String action = ae.getActionCommand();
	         if (action.equals("go")) {
	             System.out.println("Button pressed! Preparing Avia book and cancel test ...");
	             new JUnitCore();
				Result result = JUnitCore.runClasses(Avia.class);
				System.out.println(result.getFailures());
				
	            }
	            if (action.equals("abort")){
	            	frame.dispose();
	            }
	        }
	     public static void main(String args[]) {
	         new frame4Avia();
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

	}

}
