package SR.venueSR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.WebDriver;


public class DialogWindowOnOpening extends JFrame {
	
	private int frameWdth = 300;
	private int frameHgth = 300;
	
public DialogWindowOnOpening() {
  super("Please allow emailing your order...");
  this.setVisible(true);
  this.setSize(frameWdth, frameHgth);
  this.setLayout(new BorderLayout(2, 1));
  this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
  this.setLocationRelativeTo(null);
  
  WindowMessage_ WindowMessage_ = new WindowMessage_();
  WindowMessage_.setAlignmentX(CENTER_ALIGNMENT);;
  WindowMessage_.setAlignmentY(TOP_ALIGNMENT);
  WindowMessage_.setPreferredSize(new Dimension(280,200));
  WindowMessage_.setText(WindowMessage_.theText);
  
  YES_Button_ wButton = new YES_Button_();
  wButton.setText("Double-click to stop the test");
  
  WaitPanel wPanel = new WaitPanel();
  wPanel.add(WindowMessage_, BorderLayout.NORTH);
  wPanel.add(wButton, BorderLayout.SOUTH);
  
  this.getContentPane().add(wPanel);
}//eoconstructor


public static void main(String args[]) {
	  final DialogWindowOnOpening wWindowSettings = new DialogWindowOnOpening();
	  if (!wWindowSettings.isVisible()) {
		  wWindowSettings.setVisible(true);
	  }//eoif
	  
	  	wWindowSettings.addWindowFocusListener(new WindowAdapter() {
	    	@Override
			public void windowLostFocus(WindowEvent e) {
				wWindowSettings.setVisible(true);
				wWindowSettings.toFront();
				wWindowSettings.requestFocus();
	    	}
		});// eo addWindowFocusListener
} //eomain

}//eoclass

class WindowMessage_ extends JTextArea {

	public static String labelText;
	public  String theText;

	
	public WindowMessage_() {
		
//		theText = "Please allow the app send your order\'s copy to your mail.\n"
//				+ "To do it visit : <a href=\"https:\\www.google.com\\accounts\\DisplayUnlockCaptcha\" target=\"_blank\">"
//				+ "https://www.google.com/accounts/DisplayUnlockCaptcha</a>";
//		labelText = "<html><body>"
//				+ "<font size='20px' family='Helvetica, sans-serif'>"
//				+ theText + "</font>"
//				+ "</body></html>";
		
		theText = "Please allow the app send your order\'s copy to your mail.\n";
		
	  this.setText(theText);
	  this.setWrapStyleWord(true);
	  this.setLineWrap(true);
	  this.setOpaque(false);
	  this.setEditable(false);
	  this.setFocusable(false);
	  this.setOpaque(true);
	  this.setBackground(Color.ORANGE);
	  this.setBorder(new EmptyBorder(0, 10, 0, 10));
	  this.setText(labelText);
	}//eometh

}//eoclass


class YES_Button_ extends JButton {
	
	public YES_Button_() {

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DialogWindowOnOpening winInstance = new DialogWindowOnOpening();
					winInstance.dispose();
					BrowserOpener.main(null);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					System.out.println("Sorry, cannot connect to the browser.\n"
							+ "The system is shut down.");
				}
			}
		}); //eo addMouseListener
		
		this.setPreferredSize(new Dimension(200, 50));
	} //eoconstructor

}//eoclass YES_Button_


class WaitPanel extends JPanel {
	public WaitPanel() {
		this.setSize(DialogWindowOnOpening.WIDTH, DialogWindowOnOpening.HEIGHT);
	    this.setOpaque(true);
	    this.setBackground(Color.ORANGE);
	}//eoconstructor
} //eoclass WaitPanel

