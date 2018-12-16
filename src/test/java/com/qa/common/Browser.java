package com.qa.common;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class Browser extends TestBase {

	public static String URL;
	private static Logger log = Logger.getLogger(Browser.class);

	public Browser() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : Browser Constructor Called");
	}

	public static void openbrowser() {
		log.info(" : OpenBrowser Method Called");
		// Config.initConstants();
		if (driver == null) {
			// Gets URL of the Test Environment from Property File
			URL = Config.ApplicationURL;

			try {
				// Gets the Browser from the Property file, Launches Browser &
				// navigate to Login URL
				if (Config.Browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", Config.FirefoxDriverPath);
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					driver = new FirefoxDriver();
				}

				else if (Config.Browser.equalsIgnoreCase("ie")) {
					System.setProperty("webdriver.ie.driver", Config.IEDriverPath);
					driver = new InternetExplorerDriver();
				}

				else if (Config.Browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", Config.ChromeDriverPath);
					driver = new ChromeDriver();
				}

				eDriver = new EventFiringWebDriver(driver);
				EventCapture handler = new EventCapture();
				eDriver.register(handler);
				eDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				eDriver.manage().window().maximize();
				eDriver.get(URL);
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
	}
}
