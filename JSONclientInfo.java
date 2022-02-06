package SR.venueSR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JSONclientInfo {
	
	public static JSONObject clientDetails;
	public static JSONObject ClientInfo;
	public static JSONArray clientsArray;
	public static boolean checkIfExists;
	public static File checkFile = new File(".\\Databases\\clientInfo.json");
	
	
	public static void writeToJSON() throws IOException, ParseException {
		
		clientDetails = new JSONObject();
			clientDetails.put("clientAge", Panel2.clientAge);
			clientDetails.put("clientMail", Panel2.clientMail);		
			clientDetails.put("clientDate", Panel2.clientDayString);
			
		
		ClientInfo = new JSONObject();
			ClientInfo.put("Client", clientDetails);
			
		
			try ( FileWriter getJsonFile = new FileWriter(".\\Databases\\clientInfo.json"); )
			{
					getJsonFile.write(ClientInfo.toJSONString());	

					getJsonFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		Timer thisTimer = new Timer();
			thisTimer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					try {
						readJSON.readFromJson();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}, 200);
	
			
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		//writeToJSON();

	}

}
