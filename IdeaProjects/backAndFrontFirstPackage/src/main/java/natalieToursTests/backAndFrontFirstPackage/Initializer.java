package natalieToursTests.backAndFrontFirstPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Initializer implements ActionListener{
    JFrame frame;
    JPanel buttonPane, fieldsPanel;
    JLabel portalL,login, pass,agencyPortalL,agencyL,systemToGoToL;
    static JTextField loginField;
	static JTextField passwordField;
	public static JTextField agency,systemToGoTo;
    JButton ok, cancel;
    JCheckBox toursCheckBox, hotelCheckBox, additionalServicesCheckBox, cruisesCheckBox, aviaCheckBox;
    public static JComboBox<String> portal;
    public static JComboBox<String> portalOfAgency;
    String[] portalsOfAgency = {"DAN","DANP","DEMO","MICE B2B (BEL)","MICE B2B (KZ)","MICE B2B (RU)","MICE B2B (UKR)","MICE B2B","Natalie Tours","Natalie Tours B2C","Natalie Tours (BEL)",
    		"Natalie Tours (KAZ)","Natalie Tours (KAZ) B2C","Natalie Tours (META)","NATALIE TOURS (P)","Natalie Tours (RUS)","Natalie Tours (UKR)","Natalie Tours (UKR) B2C","Tourix",
    		"VHotel","VProveedor"};
    String[] portals = {"empty-dp","preproductiondevelopment","global"};
    
    Initializer() {
        frame = new JFrame("Info4Test");
        //frame.setBounds(100, 100, 500, 500);
        buttonPane = new JPanel();
        fieldsPanel = new JPanel();
        login = new JLabel("Login");
        pass = new JLabel("Password");
        loginField = new JTextField("savin.v");
        passwordField = new JPasswordField("blablabla");
        ok = new JButton("Proceed");
        cancel = new JButton("Cancel");
        toursCheckBox = new JCheckBox("Tours");
        hotelCheckBox = new JCheckBox("Hotels");
        additionalServicesCheckBox = new JCheckBox("Add. services");
        cruisesCheckBox = new JCheckBox("Cruises");
        aviaCheckBox = new JCheckBox("Flights");
        portal = new JComboBox<String>(portals);
        portal.setSelectedIndex(0);                     //DEFAULT PORTAL
        systemToGoToL = new JLabel("OR type in adress of natecnia");
        systemToGoTo = new JTextField("");
        portalL = new JLabel("Choose natecnia platform");
        agencyL = new JLabel("Choose agency");
        agency = new JTextField("test qa");
        portalOfAgency = new JComboBox<String>(portalsOfAgency);
        portalOfAgency.setSelectedIndex(14);
        agencyPortalL = new JLabel("Choose agency portal");
        
        ok.addActionListener(this);
        ok.setActionCommand("go");
        cancel.addActionListener(this);
        cancel.setActionCommand("abort");

        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPane.setLayout(new FlowLayout());

        fieldsPanel.add(toursCheckBox);
        fieldsPanel.add(hotelCheckBox);
        fieldsPanel.add(additionalServicesCheckBox);
        fieldsPanel.add(cruisesCheckBox);
        fieldsPanel.add(aviaCheckBox);
        fieldsPanel.add(login);
        fieldsPanel.add(loginField);
        fieldsPanel.add(pass);
        fieldsPanel.add(passwordField);
        fieldsPanel.add(portalL);
        fieldsPanel.add(portal);
        fieldsPanel.add(systemToGoToL);
        fieldsPanel.add(systemToGoTo);
        fieldsPanel.add(agencyL);
        fieldsPanel.add(agency);
        fieldsPanel.add(agencyPortalL);
        fieldsPanel.add(portalOfAgency);
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
                //System.out.println("Button pressed! Preparing tests...");
                if (aviaCheckBox.isSelected()){               	
                	Thread aviaThread = new Thread(new frame4Avia(),"aviaThread");
                	aviaThread.start();
                }
                if (toursCheckBox.isSelected()){               	
                	Thread toursThread = new Thread(new frame4Tours(),"toursThread");
                	toursThread.start();
                }
                if (hotelCheckBox.isSelected()){
                	Thread hotelThread = new Thread(new frame4Hotel(),"hotelThread");
                	hotelThread.start();
                }
                if (cruisesCheckBox.isSelected()){
                	Thread cruiseThread = new Thread(new frame4Cruise(),"cruiseThread");
               	 	cruiseThread.start();
               }
                if (additionalServicesCheckBox.isSelected()){
                	Thread transferThread = new Thread(new frame4Transfer(),"transferThread");
               	 	transferThread.start();
               }
            }
            if (action.equals("abort")){
            	frame.dispose();
            }
        }
        
        public static String getLogin(){
        	return loginField.getText();
        }
        
        public static String getPassword(){
        	return passwordField.getText();
        }

    public static void main(String args[]) {
        new Initializer();
    }
}