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

public class frame4Tours implements ActionListener,Runnable{
	 JFrame frame;
	 JPanel buttonPane, fieldsPanel;
	 JLabel fromL,toL,regionL,firstDateL,secondDateL,nights1L,nights2L,roomsL,adultQuantityL,childQuantityL,tourNoL,childAge1L,childAge2L,childAge3L;
	 static JTextField from,to,region,firstDate,secondDate,nights1,nights2,rooms,adultQuantity,childQuantity,tourNo,childAge1,childAge2,childAge3;
	 JButton ok,cancel;
	 static JCheckBox straightWay;
	 
	 frame4Tours(){
		 frame = new JFrame("TOUR TEST");
		 buttonPane = new JPanel();
	     fieldsPanel = new JPanel();
	     
	     fromL = new JLabel("Откуда вылет");
	     from = new JTextField("Москва");
	     toL = new JLabel("Страна");
	     to = new JTextField("Испания");
	     regionL = new JLabel("Регион");
	     region = new JTextField("Коста Брава");
	     firstDateL = new JLabel("Дата вылета ddmmyyyy");
	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
	     LocalDate localDate1 = LocalDate.now().plusMonths(1).plusDays(2);
	     firstDate = new JTextField(dtf.format(localDate1));
	     secondDateL = new JLabel("Дата прилёта ddmmyyyy");
	     LocalDate localDate2 = LocalDate.now().plusMonths(1).plusDays(3);
	     secondDate = new JTextField(dtf.format(localDate2));
	     nights1L= new JLabel("Количество ночей 1");
	     nights1 = new JTextField("7");
	     nights2L= new JLabel("Количество ночей 2");
	     nights2 = new JTextField("7");
	     straightWay = new JCheckBox("Прямой перелёт");
	     tourNoL = new JLabel("№ турпакета");
	     tourNo = new JTextField("");
	     roomsL = new JLabel("Количество номеров");
	     rooms = new JTextField("1");
	     adultQuantityL = new JLabel("Количество взрослых(<=10)");
	     adultQuantity = new JTextField("2");
	     childQuantityL = new JLabel("Количество детей(<=3)");
	     childQuantity = new JTextField("2");
	     childAge1L = new JLabel("Возраст 1го ребёнка");
	     childAge1 = new JTextField("5");
	     childAge2L = new JLabel("Возраст 2го ребёнка");
	     childAge2 = new JTextField("1");
	     childAge3L = new JLabel("Возраст 3го ребёнка");
	     childAge3 = new JTextField("11");
	     
	     
	     ok = new JButton("Вперёд!");
	     cancel = new JButton("Отмена");
	     
	     ok.addActionListener(this);
	     ok.setActionCommand("go");
	     cancel.addActionListener(this);
	     cancel.setActionCommand("abort");
	     
	     fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
	     buttonPane.setLayout(new FlowLayout());
	     
	     fieldsPanel.add(fromL);
	     fieldsPanel.add(from);
	     fieldsPanel.add(toL);
	     fieldsPanel.add(to);
	     fieldsPanel.add(regionL);
	     fieldsPanel.add(region);
	     fieldsPanel.add(firstDateL);
	     fieldsPanel.add(firstDate);
	     fieldsPanel.add(secondDateL);
	     fieldsPanel.add(secondDate);
	     fieldsPanel.add(nights1L);
	     fieldsPanel.add(nights1);
	     fieldsPanel.add(nights2L);
	     fieldsPanel.add(nights2);
	     fieldsPanel.add(roomsL);
	     fieldsPanel.add(rooms);
	     fieldsPanel.add(adultQuantityL);
	     fieldsPanel.add(adultQuantity);
	     fieldsPanel.add(childQuantityL);
	     fieldsPanel.add(childQuantity);
	     fieldsPanel.add(tourNoL);
	     fieldsPanel.add(tourNo);
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
	             System.out.println("Button pressed! Preparing Tour book and cancel test ...");
	             new JUnitCore();
	             Result result = JUnitCore.runClasses(Tours.class);
	             System.out.println(result.getFailures());
	            }
	            if (action.equals("abort")){
	            	frame.dispose();
	            }
	        }
	     public static void main(String args[]) {
	         new frame4Tours();
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
