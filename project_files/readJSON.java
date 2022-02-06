package SR.venueSR;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readJSON {
	
	public static int ageInt;
	
	public static String theAge;
	
	public static JSONObject ClientDataArray;
	
	public static String theMailString;
	
	public static void readFromJson() throws ParseException {
		
		
		

		JSONParser jsonParser = new JSONParser();
			try ( FileReader fileReader = new FileReader(".\\Databases\\clientInfo.json") ) {
				
				Object parseJson = jsonParser.parse(fileReader);
//				JSONObject ClientDataArray = ( (JSONObject) parseJson );
				JSONObject ClientDataArray = ( (JSONObject) parseJson );
					System.out.println(ClientDataArray);
					
					theMailString = ClientDataArray.toString();
				
				JSONObject getAge = (JSONObject) ClientDataArray.get("Client");
				String theAge = (String) getAge.get("clientAge");
				
					ageInt = Integer.parseInt(theAge);
				
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException " );
			} catch (IOException e) {
				System.out.println("IOException " );
			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException " );
				e.printStackTrace();
			} catch (ParseException e) {
				System.out.println("ParseException " );
				e.printStackTrace();
			}
	
		
		
		
		Timer theTimer = new Timer();
		
		theTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try {
					Panel3.startAlchogolDrinks();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}, 200);
		
	try {
		Panel3.sendMail(theMailString);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
				
	}
	
	public  void Delay(String theAge) {
		ageInt = Integer.parseInt(theAge);
		
	}

	public static void main(String[] args) throws ParseException {
	//	readFromJson();
	}

}
