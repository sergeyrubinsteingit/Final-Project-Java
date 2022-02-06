package SR.venueSR;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;  //  UI components
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;  //
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.simple.parser.ParseException;


public class venueGUI extends JFrame {
	
	public static String xlsMenuContents = "Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST Test Test Testtest TESTTEST ";
	
	private static String welcome = "Welcome to WEBistro online venue!";
	private static String pressButton = "Press the GO! button to make your order.";
	static JFrame jFrame = new JFrame("Java - final project");
	private static JLabel label1 = new JLabel();
	private static JLabel label2 = new JLabel();
	static JPanel panel0 = new JPanel();
	static JPanel panel1 = new JPanel();
	static JPanel panel3 = new JPanel();
	
	
	final static boolean flag = true;

	static JLabel goButton;
	static JScrollPane txtaScrollPane;
	
	public static int mouseCount = 0;

	public static Object txTextArea;
	static ImageIcon icon = new ImageIcon(".\\imgs\\goButton.png");


public static void GUIbuilder() {
	
try {
	menuListHomePage.menuList();
	
	menuListHomePage excelMenuValues = new menuListHomePage();
	
	xlsMenuContents = excelMenuValues.cellToString;
	
	final JTextArea txTextArea = new JTextArea(xlsMenuContents);
	
	jFrame.setPreferredSize(new Dimension(1100,700));
	jFrame.setMaximumSize(new Dimension(1100,700));
	jFrame.setMinimumSize(new Dimension(1100,700));
	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jFrame.setLocationRelativeTo(null);
	jFrame.setResizable(false);
	jFrame.setLayout(null);
	
	
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File(".\\\\imgs\\\\venueBackgr.gif"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Image dimg = img.getScaledInstance(1100, 700, Image.SCALE_SMOOTH);
	ImageIcon imageIcon = new ImageIcon(dimg);
	jFrame.setContentPane(new JLabel(imageIcon));

	
	goButton = new JLabel(icon);
	goButton.setBounds(120,30,200,150);
	
	label1.setBounds(50,133,422,200);
	label1.setText("<html><body><br/><br/><br/><span style=\"text-align:center;font-family:Verdana,Helvetica, sans-serif;font-size:24pt;font-style:normal;font-weight:700;text-align:left;color:#f5c449;word-wrap:normal;\">" 
	+ welcome + "</span>" + 
	"<br/><span style=\"text-align:center;font-family:Verdana,Helvetica, sans-serif;font-size:18pt;font-style:italic;font-weight:normal;text-align:left;color:#ffffff;word-wrap:normal;\"><br />" 
	+ pressButton + "</span><body></html>");


	panel0.add(goButton);
	panel0.add(label1);

	panel0.setLayout(null);
	
	panel0.setBounds(0,0,440,700);
	panel0.setMaximumSize(new Dimension(440,700));
	panel0.setMinimumSize(new Dimension(440,700));
	panel0.setLocation(0, 0);
	
	panel0.setBackground(Color.RED);
	panel0.setOpaque(false);
	
	
/*Settings for the Panel 1*/
	panel1.setBounds(440, 0, 660, 700);
	
	panel1.setBackground(Color.BLACK);
	panel1.setOpaque(false);
/*Settings for the Textarea/textScrollPane*/
	txTextArea.setEditable(false);
	txTextArea.setFont(new Font("sans-serif", Font.LAYOUT_LEFT_TO_RIGHT, 11));
	txTextArea.setForeground(Color.WHITE);
	txTextArea.setLineWrap(true);
	txTextArea.setWrapStyleWord(true);
	txTextArea.setOpaque(false);
	txTextArea.setBorder(BorderFactory.createEmptyBorder(20,0,20,20));
	txtaScrollPane = new JScrollPane(txTextArea);
	txtaScrollPane.getViewport().setOpaque(false);
	txtaScrollPane.setOpaque(false);
	txtaScrollPane.setBorder(BorderFactory.createEmptyBorder());
	txtaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	txtaScrollPane.setPreferredSize(new Dimension(600,650));
	txtaScrollPane.setBounds(440, 65, 600, 650);
	
	panel1.add(txtaScrollPane);
	jFrame.add(panel0);
	jFrame.add(panel1);

	
//	jFrame.getContentPane().add(panel1);

/*Launching JFrame*/



	jFrame.validate();
	jFrame.pack();
	jFrame.setVisible(true);

/* Mouse events */
	
	goButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent m) {
			goButton.setBounds(115,25,200,150);
			icon =  new ImageIcon(".\\imgs\\goButton2.png");
		}
		@Override
		public void mouseExited(MouseEvent m) {
			goButton.setBounds(120,30,200,150);
		}
		@Override
		public void mouseClicked(MouseEvent m) {
			mouseCount++;
			if (mouseCount == 1) {
				System.out.println("Gray Mouse!");
				
				jFrame.remove(panel1);
				jFrame.revalidate();			
				jFrame.repaint();
				jFrame.setLayout(null);
				
				Panel2.insertPanel2(Panel2.panel2, 
						Panel2.ageField, 
						Panel2.ageWarning, 
						Panel2.ageWarnString, 
						Panel2.mailWarnString, 
						Panel2.chooseDay, 
						Panel2.mailField,
						Panel2.mailWarning
						);
				
	
				}else if (mouseCount == 2) {
				System.out.println("White Mouse!");
				
				
				try {
					Panel2.Controller();
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}

				} else if (mouseCount == 3) {
				System.out.println("PINK Mouse!");
				
				Panel3.createCombos( Panel3.mealString, Panel3.nonAlchoString, Panel3.alchoString );
//				
//				try {
//					Panel3.control2();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				
//				try {
//					Panel3.writeOrderToJSON();
//				} catch (IOException | ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			//	mouseCount = 3;
			} else if (mouseCount == 4) {
				System.out.println("FAT GREEN Mouse!");
				try {
					Panel3.writeOrderToJSON(Panel3.mealSelected, Panel3.nonAlchoSelected, Panel3.alchoSelected);
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mouseCount = 4;
			}
		}
		
	});
	
	Panel2.ageField.addMouseListener(new MouseAdapter() {
		public void mouseExited(MouseEvent m) {
			Panel2.clientAge(null);
		}
	});
	} catch (Exception e) {
	System.out.println(e);
}

} // eo GUIbuilder()



public static void main(String[] args) {
//	GUIbuilder(); // Temporary - for excelMenuValues
//	Panel2.comboD();
	}




}//////////////////////////////////////////////////////////