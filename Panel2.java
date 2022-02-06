package SR.venueSR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.transform.OutputKeys;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Panel2 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String ageWarnString = "";
	public static String mailWarnString = "Please enter your mail into field above.";
	public static String clientDayString;
	
	public static JPanel panel2 = new JPanel(new BorderLayout());
	public static JTextPane ageField = new JTextPane();
	static JTextField ageFieldLabel = new JTextField("Please enter your age");	
	static JTextField ageWarning = new JTextField(ageWarnString);	
	public static JTextField mailWarning = new JTextField( mailWarnString );	
	
	public static JTextField chooseDayLabel = new JTextField("Testing Testing Testing Testing Testing Testing Testing ");
	
	public static JTextField mailFieldLabel = new JTextField("Your mail, please1");
	
	public static JTextPane mailField = new JTextPane();
	
	public static String monthName [] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	public static JComboBox <Integer>  chooseDay;	
	public static Integer[] comboDays;
	public static Calendar theCalendar = Calendar.getInstance();
	public static int monthNmb = theCalendar.get(Calendar.MONTH);
	public static int theYear = theCalendar.get(Calendar.YEAR);
	public static int dayNmb2 = theCalendar.get(Calendar.DAY_OF_MONTH) ;
	public static int daysTotal2 = theCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	public static int cn = dayNmb2;
	public static String currentMonth;
	public static int limit;
	
	public static String clientAge;
	public static String clientMail;
	public static String theMail;
	public static Boolean ageCorrect;
	public static Boolean dateSelected;
	public static String theAge;
	
	public static int maxInComboArr;
	public static int bigNmbIndx;
	public static int selIndx;
	

	
	public static JComboBox<Integer> comboD ( ) {
		comboDays =  new Integer[daysTotal2-dayNmb2];
		currentMonth = monthName[monthNmb];
		chooseDayLabel = new JTextField("Now is " + currentMonth + ". Please choose a day of your visit.");
		
		 limit = daysTotal2-dayNmb2;
		
			if ( limit < 10 ) {
				limit = daysTotal2-20;
				comboDays =  new Integer[limit];
			}
		
			for (int i = 0; i < limit; i++) {
				comboDays[i] = cn;
				cn++;
					if (cn > daysTotal2) {
						cn = 1;
					}
				
			}
			
		maxInComboArr = comboDays[0];
			
			for (int i = 0; i < comboDays.length; i++) {
				if (maxInComboArr < comboDays[i]) {
					maxInComboArr = comboDays[i];
				}
			}
			
			for (int i = 0; i < comboDays.length; i++) {
				if (comboDays[i] == maxInComboArr) {
					List<Integer> arr = Arrays.asList(comboDays);
					 bigNmbIndx = arr.indexOf(maxInComboArr);
				}
			}
		
		chooseDay = new JComboBox<> (comboDays); // works only inside rhe function
		
		chooseDay.insertItemAt(null, 0);
		
	return chooseDay;}
	
	public Panel2() { // Constructor for the panel
		
		System.out.println("//////////////// Begins Panel2 //////////////////");
				
		ageField.setBounds(300,150,150, 25);	
		ageField.setFont(new Font("Calibri", Font.BOLD, 18));
		ageField.setForeground(Color.RED);
		ageField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ageField.setText("");
			}
		});
		
		ageFieldLabel.setBounds(100,150,450,25);	
		ageFieldLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		ageFieldLabel.setForeground(Color.RED);
		ageFieldLabel.setBorder(BorderFactory.createEmptyBorder());
		ageFieldLabel.setEditable(false);
		ageFieldLabel.setOpaque(false);
		
		ageWarning.setBounds(100,180,450,25);
		ageWarning.setFont(new Font("sans-serif", Font.CENTER_BASELINE, 11));
		ageWarning.setForeground(Color.WHITE);
		ageWarning.setBackground(new Color(0));
		ageWarning.setOpaque(true);
		ageWarning.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		
		mailWarning.setBounds(100,330,450,25);
		mailWarning.setFont(new Font("sans-serif", Font.CENTER_BASELINE, 11));
		mailWarning.setForeground(Color.WHITE);
		mailWarning.setBackground(new Color(0));
		mailWarning.setOpaque(true);
		mailWarning.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
			
		mailField.setBounds(100,300,450,25);
		mailField.setFont(new Font("sans-serif", Font.LAYOUT_LEFT_TO_RIGHT, 11));
		mailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				clientMail(e);
			}
			
		});
		
//		mailField.setText("sergey.rubinshtein@gmail.com"); // TESTING
		
		chooseDayLabel.setBounds(100,375,450,25);
		chooseDayLabel.setFont(new Font("sans-serif", Font.LAYOUT_LEFT_TO_RIGHT, 11));
		chooseDayLabel.setForeground(Color.WHITE);
		chooseDayLabel.setBackground(new Color(0));
		chooseDayLabel.setOpaque(true);
		chooseDayLabel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		
		chooseDay.setBounds(100,400,450,25);
		chooseDay.setSelectedIndex(0);
		chooseDay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseDay.getSelectedItem();
				clientDayString = chooseDay.getSelectedItem().toString() + " " + monthNmb + " " + theYear;
				selIndx = chooseDay.getSelectedIndex();
					if ( selIndx > bigNmbIndx  && monthNmb < 11 ) {
						monthNmb = monthNmb + 1;
						System.out.println("monthNmb " + monthNmb);
					}
			//	clientDay();
			}
		});
	} // EO Constructor
//	
	public static void insertPanel2(  /////////////////////////////////////////////////////////////////////
			JPanel panel2, 
			JTextPane ageField, 
			JTextField ageWarning, 
			String ageWarnString, 
			String mailWarnString, 
			JComboBox<Integer> chooseDay, 
			JTextPane mailField,
			JTextField mailWarning
			) {
		
		new Panel2();

		try {
			panel2.add(chooseDay);
			panel2.add(ageWarning);
			panel2.add(ageField);
			panel2.add(ageFieldLabel);
			panel2.add(chooseDayLabel);
			panel2.add(mailField);
			panel2.add(mailWarning);
			
			panel2.setBounds(440, 0, 660, 700);			
//			panel2.setPreferredSize(new Dimension(1100,700));
			panel2.setMaximumSize(new Dimension(660,700));
			panel2.setMinimumSize(new Dimension(660,700));
//			panel2.setLayout(null);
			
			panel2.setBackground(Color.DARK_GRAY);
			panel2.setLayout(null);
			panel2.setOpaque(false);
			panel2.setVisible(true);
			
			panel2.revalidate();
			panel2.repaint();
			panel2.setVisible(true);
			
			venueGUI.jFrame.add(panel2);
			venueGUI.jFrame.validate();
			venueGUI.jFrame.repaint();
			venueGUI.jFrame.setLayout(null);
			venueGUI.jFrame.pack();
			venueGUI.jFrame.setVisible(true);

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		System.out.println("//////////// Ends Insert Pane /////////////");

	}
	
	public static void clientAge(MouseEvent m) {
		
		theAge = ageField.getText();
		
			if ( theAge.matches("\\d+") ) {
				int parseAge = Integer.parseInt(theAge);
					if ( parseAge > 9 && parseAge < 121 ) {
						System.out.println("parseAge = " + parseAge);
						clientAge = theAge;
						ageCorrect = true;
						sendClientInfo.clientInfo();
						flWriter();
						ageWarning.setText("");
						return;
					} else {
						ageWarning.setText("");
						ageWarning.setText("Sorry, the age premitted to make orders is between 10 and 120. \nBlame the Government!");
						ageField.setText("");
						return;
					} // if 2
			} else {
				ageWarning.setText("");
				ageWarning.setText("Please use numerical values only");
				ageField.setText("");
				return;
			} // if 1
	}
	
	public static void clientMail(MouseEvent m) {
		theMail = mailField.getText();
			if ( theMail.matches("^(.+)@(.+)$") ) {
				System.out.println("Matches------------------------");
				mailWarning.setText("Thanks! A copy of your order will be send to this mail.");
				clientMail = theMail;
				sendClientInfo.clientInfo();
			} else if ( theMail.equals("") ) {
				mailWarning.setText("Please enter your mail into field above.");
			} else {
				mailWarning.setText("The mail probably needs correction. Please inspect it.");
				System.out.println("Wrong mail");
			}
	}
	static boolean ccc = true;
	
	public static boolean Controller() throws IOException, ParseException {
		
		if (  theAge.matches("\\d+") &&  theMail.matches("^(.+)@(.+)$") && chooseDay.getSelectedItem() != null ) {
			
		//	Panel3.startAlchogolDrinks();
			JSONclientInfo.writeToJSON();
			
			return false;
		} else {
			JOptionPane.showMessageDialog(panel2, "Please fill up all fields.");
			venueGUI.mouseCount = 1;
			return false;
		}
	}
	

	
	
	public void mouseEvent() {
	
	ageField.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseExited(MouseEvent m) {
			clientAge(m);
			ageField.removeMouseListener(this);
		}
	});
	
	mailField.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseExited(MouseEvent m) {
			clientMail(m);
			System.out.println("*******************************************");
		}
	});
	
	chooseDay.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseExited(MouseEvent m) {
		}
	});
	
	} // mouseEvent()
	
	private static void flWriter() {
		try {
			FileWriter fileWriter = new FileWriter(".\\logs\\log.txt");
			fileWriter.write(ageField.getText());
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
	//	venueGUI.GUIbuilder(); //Testing
	//	comboD();
	}

}
