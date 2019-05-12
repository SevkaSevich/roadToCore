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

public class frame4Cruise implements ActionListener,Runnable{
	 JFrame frame;
	 JPanel buttonPane, fieldsPanel;
	 JLabel whenToL,whenBackL,shipL,roomQuantityL,adultQuantityL,childQuantityL,childAge1L,childAge2L,childAge3L,regionL;
	 static JTextField whenTo, whenBack,ship,roomQuantity, adultQuantity, childQuantity,childAge1,childAge2,childAge3;
	 JButton ok,cancel;
     static JComboBox<String> region;
	 String[] regions = {"Атлантический океан ","Восточное Средиземноморье ","Греческие острова ","Западное Средиземноморье ","Индийский океан ","Карибский бассейн ","Круизы из Санкт-Петербурга ","Круизы по Средиземноморью и Канарским островам ","Персидский залив, ОАЭ ","Северная Европа "};
	 
	 
	 frame4Cruise(){
		 frame = new JFrame("CRUISE TEST");
		 buttonPane = new JPanel();
	     fieldsPanel = new JPanel();
	     
	     whenToL = new JLabel("Дата начала ddMMyyyy");
	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
	     LocalDate localDate1 = LocalDate.now().plusMonths(1);
	     whenTo = new JTextField(dtf.format(localDate1));
	     whenBackL = new JLabel("Дата конца ddMMyyyy");
	     LocalDate localDate2 = LocalDate.now().plusMonths(1).plusDays(8);
	     whenBack = new JTextField(dtf.format(localDate2));
	     shipL = new JLabel("Название корабля");
	     ship= new JTextField("");
	     roomQuantityL = new JLabel("Количество кают");
	     roomQuantity= new JTextField("1");
	     adultQuantityL = new JLabel("Количество взрослых на каюту");
	     adultQuantity = new JTextField("2");
	     childQuantityL = new JLabel("Количество детей на каюту");
	     childQuantity = new JTextField("2");
	     childAge1L = new JLabel("Возраст 1го ебёнка");
	     childAge1 = new JTextField("5");
	     childAge2L = new JLabel("Возраст 2го ребёнка");
	     childAge2 = new JTextField("1");
	     childAge3L = new JLabel("Возраст 3го ребёнка");
	     childAge3 = new JTextField("10");
	     ok = new JButton("Вперёд!");
	     cancel = new JButton("Отмена");
	     region = new JComboBox<String>(regions);
	     region.setSelectedIndex(2);
	     regionL = new JLabel("Выберете регион");
	     
	     ok.addActionListener(this);
	     ok.setActionCommand("go");
	     cancel.addActionListener(this);
	     cancel.setActionCommand("abort");
	     
	     fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
	     buttonPane.setLayout(new FlowLayout());
	     
	     fieldsPanel.add(whenToL);
	     fieldsPanel.add(whenTo);
	     fieldsPanel.add(whenBackL);
	     fieldsPanel.add(whenBack);
	     fieldsPanel.add(regionL);
	     fieldsPanel.add(region);
	     fieldsPanel.add(shipL);
	     fieldsPanel.add(ship);
	     fieldsPanel.add(roomQuantityL);
	     fieldsPanel.add(roomQuantity);
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
	     		System.out.println("Button pressed! Preparing Cruise book and cancel test ...");
	            new JUnitCore();
	    		Result result = JUnitCore.runClasses(Cruise.class);
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

	}

}
