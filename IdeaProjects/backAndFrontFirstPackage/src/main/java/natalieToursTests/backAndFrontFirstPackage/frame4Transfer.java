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

public class frame4Transfer implements ActionListener,Runnable{
	 JFrame frame;
	 JPanel buttonPane, fieldsPanel;
	 JLabel fromL,toL,firstDateL,secondDateL,time1L,time2L,time3L,flight1L,flight2L,terminalFromL,terminalToL,adultQuantityL,childQuantityL,childAge1L,childAge2L,childAge3L;
	 static JTextField from,to,firstDate,secondDate,time1,time2,time3,flight1,flight2,terminalFrom,terminalTo,adultQuantity,childQuantity,childAge1,childAge2,childAge3;
	 JButton ok,cancel;
	 static JCheckBox thereAndBack;
	 
	 frame4Transfer(){
		 frame = new JFrame("TRANSFER TEST");
		 buttonPane = new JPanel();
	     fieldsPanel = new JPanel();
	     
	     fromL = new JLabel("Терминал");
	     from = new JTextField("SIP");
	     toL = new JLabel("Отель");
	     to = new JTextField("1001 НОЧЬ");
	     thereAndBack = new JCheckBox("Туда и обратно");
	     firstDateL = new JLabel("Дата первой встречи ddMMyyyy");
	     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
	     LocalDate localDate1 = LocalDate.now().plusMonths(1).plusDays(2);
	     firstDate = new JTextField(dtf.format(localDate1));
	     secondDateL = new JLabel("Дата второй встречи ddmmyyyy");
	     LocalDate localDate2 = LocalDate.now().plusMonths(1).plusDays(3);
	     secondDate = new JTextField(dtf.format(localDate2));
	     time1L = new JLabel("Время прибытия рейса");
	     time1 = new JTextField("12:00");
	     time2L = new JLabel("Время встречи обратно");
	     time2 = new JTextField("12:00");
	     time3L = new JLabel("Время вылета");
	     time3 = new JTextField("15:00");
	     flight1L = new JLabel("Номер рейса прибытия");
	     flight1 = new JTextField("SU1234");
	     flight2L = new JLabel("Номер рейса отлёта");
	     flight2 = new JTextField("SU1234");
	     terminalFromL = new JLabel("Прибытие из терминала");
	     terminalFrom = new JTextField("DME");
	     terminalToL = new JLabel("Направление в терминал");
	     terminalTo = new JTextField("DME");
	     adultQuantityL = new JLabel("Количество взрослых(<=50)");
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

	     fieldsPanel.add(firstDateL);
	     fieldsPanel.add(firstDate);
	     fieldsPanel.add(thereAndBack);
	     fieldsPanel.add(secondDateL);
	     fieldsPanel.add(secondDate);
	     fieldsPanel.add(time1L);
	     fieldsPanel.add(time1);
	     fieldsPanel.add(time2L);
	     fieldsPanel.add(time2);
	     fieldsPanel.add(time3L);
	     fieldsPanel.add(time3);
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
	             System.out.println("Button pressed! Preparing Transfer book and cancel test ...");
	             new JUnitCore();
	             Result result = JUnitCore.runClasses(Transfer.class);
	             System.out.println(result.getFailures());
	            }
	            if (action.equals("abort")){
	            	frame.dispose();
	            }
	        }
	     public static void main(String args[]) {
	         new frame4Transfer();
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
