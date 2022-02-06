package SR.venueSR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Panel3 extends JPanel {
	
	public static JPanel panel3;
	
	public static int ageFromJson = readJSON.ageInt;
	
	public static JComboBox<String> mealsJBox;
	public static JComboBox<String> nonAlchoJBox;
	public static JComboBox<String> alchoBox;
	
	public static String [] mealString ;
	public static String [] nonAlchoString ;
	public static String [] alchoString ;
	
	public static String mealSelected;
	public static String nonAlchoSelected;
	public static String alchoSelected;
	public static JSONObject clientChoise;
	public static JSONObject clientsChoise;
	public static JSONObject clientMeal;
	public static JSONObject clientNonAlcho;
	public static JSONObject clientAlcho;
	public static JSONObject clientsOrder;
		
	public static boolean checkIfExists;
	public static File checkFile = new File(".\\Databases\\clientInfo.json");
	
	public static JLabel explanatoLabel = new JLabel();
	public static String makeChoise = "Please make your choise.";
	public static String finish = "When ready, just click the GO button.";
	/**
	 * @throws IOException 
	 * 
	 */
	
	public static void startAlchogolDrinks() throws IOException {
		
		System.out.println("startAlchogolDrinks >> ageInt = " + readJSON.ageInt );
		
		try {
			
			MealsList.readExcelMenu();

			nonAlchogolDrinks.listFromExcel();
			
			alchogolDrinks.alchogolList();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;
		
	
	public Panel3() {
		
		venueGUI.jFrame.remove(Panel2.panel2);

		panel3 = new JPanel(new BorderLayout());

		panel3.setBounds(440, 0, 660, 700);			
		panel3.setMaximumSize(new Dimension(660,700));
		panel3.setMinimumSize(new Dimension(660,700));
		
		panel3.setBackground(Color.ORANGE);
		panel3.setLayout(null);
		panel3.setOpaque(false);
		panel3.setVisible(true);
		
		panel3.revalidate();
		panel3.repaint();
		panel3.setVisible(true);
		
		explanatoLabel.setBounds(50,10,450,200);
		explanatoLabel.setText("<html><body><br/><br/><br/><span style=\"text-align:center;font-family:Verdana,Helvetica, sans-serif;font-size:24pt;font-style:normal;font-weight:700;text-align:left;color:#f5c449;word-wrap:normal;\">" 
		+ makeChoise + "</span>" + 
		"<br/><span style=\"text-align:center;font-family:Verdana,Helvetica, sans-serif;font-size:18pt;font-style:italic;font-weight:normal;text-align:left;color:#ffffff;word-wrap:normal;\"><br />" 
		+ finish + "</span><body></html>");

		
		
		venueGUI.jFrame.revalidate();			
		venueGUI.jFrame.repaint();
		venueGUI.jFrame.setLayout(null);
	}
	
	public static boolean check1 = false;
	public static boolean check2 = false;
	public static boolean check3 = false;
	
	public static void  getComboArrays( String a ) {
		if ( a.equals("meals") ) {
			mealString = MealsList.mealsArrayString;
			check1 = true;
		}
		if ( a.equals("nonAlcho") ) {
			nonAlchoString = nonAlchogolDrinks.nonAlchoArray;
			check2 = true;
		}
		if ( a.equals("alcho") ) {
			alchoString = alchogolDrinks.alchoString;
			check3 = true;
		}	
	}
	
	
	
	public static boolean mealCont = false;
	public static boolean NonAlchoCont = false;
	public static boolean AlchoCont = false;
	static String clientMail = "";
	
	public static void createCombos( String [] a, String [] b, String [] c ) {
		
		new Panel3();
		
		mealsJBox = new JComboBox<String>(a);
			mealsJBox.setBounds(50,200,550,25);
	//		mealsJBox.setSelectedIndex(0);
		//	mealsJBox.insertItemAt("None", 0);
			mealsJBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
						mealsJBox.getSelectedItem();
						mealSelected = mealsJBox.getSelectedItem().toString();
					}
		}) ;	
			
			
		nonAlchoJBox = new JComboBox<String>(b);
			nonAlchoJBox.setBounds(100,300,450,25);
			nonAlchoJBox.setSelectedIndex(0);
//			
			nonAlchoJBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					nonAlchoJBox.getSelectedItem();
					nonAlchoSelected = nonAlchoJBox.getSelectedItem().toString();
					}
		}) ;	

		alchoBox = new JComboBox<String>(c);
			alchoBox.setBounds(100,400,450,25);
			alchoBox.setSelectedIndex(0);
			
			alchoBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					alchoBox.getSelectedItem();
					alchoSelected = alchoBox.getSelectedItem().toString();
		
				}});
			
		insertPanel3( mealsJBox,nonAlchoJBox,alchoBox );
	}
	
	
		
	public static boolean checkMeal  = false;
	public static String mealOrderString;
	
	
//	public void mouseEvent() {
//		mealsJBox.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseExited(MouseEvent m) {
//				mealsJBox.getSelectedItem();
//				mealSelected = mealsJBox.getSelectedItem().toString();
//				System.out.println("EXIT MEALS >>> " + mealsJBox.getSelectedItem());
//			}
//		
//		});
//
//	}
	
	
	public static void insertPanel3(JComboBox<String> a, JComboBox<String> b, JComboBox<String> c) {
		
		new Panel3();

		panel3.add(mealsJBox);
		panel3.add(nonAlchoJBox);
		if ( ageFromJson > 18 ) {
			panel3.add(alchoBox);
		}
		
		panel3.add(explanatoLabel);
	
		venueGUI.jFrame.add(panel3);
		
		venueGUI.jFrame.validate();
		venueGUI.jFrame.repaint();
		venueGUI.jFrame.setLayout(null);
		venueGUI.jFrame.pack();
		venueGUI.jFrame.setVisible(true);

	}

	
	public static void control2(String mealSelected2) throws IOException, ParseException {
		if (mealsJBox.getItemListeners() != null) {
			System.out.println(" control2() control2() control2() control2()");
			
		//	writeOrderToJSON(mealSelected);
		}
		
	}

	@SuppressWarnings("unchecked")
	public static void writeOrderToJSON(String a, String b, String c) throws IOException, ParseException {
		
System.out.println(">>>>>>>>>>>>>>>>>>>>> writeOrderToJSON  >>> " + a);

		clientChoise = new JSONObject();
		clientChoise.put("Meal", a);
		clientChoise.put("Non-Alcho Drink", b);
		clientChoise.put("Alcho Drink", c);
		
			
		Timer thisTimer = new Timer();
			thisTimer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					
					clientsOrder = new JSONObject();
					clientsOrder.put("Client's order", clientChoise);
					
					try ( FileWriter getJsonFile = new FileWriter(".\\Databases\\clientOrder.json"); )
					{
						getJsonFile.write(clientsOrder.toJSONString());	

							getJsonFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}, 500);
//			
			
			Timer t2 = new Timer();
				t2.schedule(new TimerTask() {
					
					@Override
					public void run() {

						try {
							readSecondJSON.readSecondJson();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
					}
				}, 500);
			
	}
	
	
	public static void sendMail(String _get_msg_ ) throws AddressException, MessagingException {
				
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("sergrubQAtesting20.21@gmail.com", "sergrubQA202021");
		    }
		});
		
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("sergrubQAtesting20.21@gmail.com"));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse(Panel2.theMail));
		message.setSubject("Hi, here is weBistro!");
		 
		String msg = _get_msg_;
		 
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		 
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		 
		message.setContent(multipart);
		 
		Transport.send(message);
		
		
//		
//		Timer closeTimer = new Timer();
//			closeTimer.schedule(new TimerTask() {
//				
//				@Override
//				public void run() {
//					Finish();	
//				}
//			}, 150);
//		
	}
	
	public static void Finish() {
		JOptionPane.showMessageDialog(null, "Done! A copy of your order is sent to your mail. See you soon!");
		System.exit(0);
	}
	

	public static void main(String[] args) throws IOException, AddressException, MessagingException {
	//	insertPanel3( panel3, alchogolDrinks.alchoDrinksBox, alchogolDrinks.alchoString ); // Test
//	startAlchogolDrinks();
	
//	sendMail(String a);//Teasting
	}

}

