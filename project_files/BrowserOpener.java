//Gets a browser name selected by user to test the site on.
//Opens the site, deletes the cookies from the system
//to avoid test failure.

package SR.venueSR;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;



public abstract class BrowserOpener {
	
	public static WebDriver webDriver;
	public static String driverType;
	private static String driverPath = System.getProperty("user.dir") + "\\src\\main\\java\\SR\\venueSR\\web_drivers_\\";
	
	public static void startBrowser() throws InterruptedException {
		
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\n=+=+=+=+=+=+=+=+=+=+= BROWSER OPENER =+=+=+=+=+=+=+=+=+=+=\n");

		TimeUnit.SECONDS.sleep((long)0.5);
		
		System.out.println("########>>> listStringArray.length " + MakeBrowsersList_ver2_.listStringArray.length + " <<<########");
				
		for (int i = 0; i < MakeBrowsersList_ver2_.listStringArray.length; i++) {
			if (MakeBrowsersList_ver2_.listStringArray[0].matches("Firefox")) { // FIREFOX driver
				System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
						UnexpectedAlertBehaviour.IGNORE);
				firefoxOptions.setCapability("marionette", true);
				webDriver = new FirefoxDriver(firefoxOptions);
				break;
				
			} else if (MakeBrowsersList_ver2_.listStringArray[0].matches("Chrome")) { // CHROME driver
				System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
				webDriver = new ChromeDriver();
				break;

			} else if (MakeBrowsersList_ver2_.listStringArray[0].matches("MS Edge")) { // EDGE driver
				System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
				webDriver = new EdgeDriver();
				break;

			} else if (MakeBrowsersList_ver2_.listStringArray[0].matches("Internet Explorer")) { // EXPLORER driver
				System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer_x64_\\IEDriverServer.exe");
				InternetExplorerOptions ie_options = new InternetExplorerOptions().ignoreZoomSettings();
				webDriver = new InternetExplorerDriver(ie_options);
				break;

			} else if (MakeBrowsersList_ver2_.listStringArray[0].matches("Opera")) { // OPERA driver
				System.setProperty("webdriver.opera.driver", driverPath + "operadriver.exe");
				webDriver = new OperaDriver();
				break;

			} else if (MakeBrowsersList_ver2_.listStringArray[0].matches("Safari")) { // SAFARI driver. 
				//////////	Needs to be enabled in Safari browser. Do the following: ////////////
				//			Go to Safari -> Preferences-> Advanced
				//			Tick mark the Checkbox with the label – Show Develop menu in menu bar
				//////////	Once done, go to the Develop menu and click on the Allow Remote Automation option to enable it.
				webDriver = new SafariDriver();
				break;

			} else {
				System.out.println("\n---------------- Failure: Web driver was not found ------------------");
				System.exit(-1);
			} 
		}//eofor
		TimeUnit.SECONDS.sleep((long)0.5);
		webDriver.navigate().to("https://myaccount.google.com/lesssecureapps");// opens a browser			
	}	// eomethod

	public static void main(String [] args) throws InterruptedException {
		
		try {
			ListBrowsers_ver2_.main(args);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		MakeBrowsersList_ver2_.main(args);
		TimeUnit.SECONDS.sleep(5);
		startBrowser();
	}
}// eoclass
