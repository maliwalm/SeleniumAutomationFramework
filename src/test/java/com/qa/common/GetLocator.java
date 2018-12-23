package com.qa.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class GetLocator extends TestBase {

	private static String xmlPath;
	private static List<String> listLocator;
	private static By locator;
	private static Logger log = Logger.getLogger(GetLocator.class);
	
	public static By GetElement(String locatorValue, String locatorType) {
		log.info(" : GetElement Method Called");
		try {
			if(locatorType.equalsIgnoreCase("id"))
				return By.id(locatorValue);
			else if(locatorType.equalsIgnoreCase("name"))
				return By.name(locatorValue);
			else if(locatorType.equalsIgnoreCase("class"))
				return By.className(locatorValue);
			else if(locatorType.equalsIgnoreCase("tagname"))
				return By.tagName(locatorValue);
			else if(locatorType.equalsIgnoreCase("linktext"))
				return By.linkText(locatorValue);
			else if(locatorType.equalsIgnoreCase("partiallinktext"))
				return By.partialLinkText(locatorValue);
			else if(locatorType.equalsIgnoreCase("cssselector"))
				return By.cssSelector(locatorValue);
			else if(locatorType.equalsIgnoreCase("xpath"))
				return By.xpath(locatorValue);
			else 
				throw new Exception("DOM FINDER : did not find the correct dom finder type in the file for locator value: "
						+ locatorValue);
		}
		catch(Exception ex) {
			System.err.format("Exception" +ex);
			log.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	public static By findElement(String PageName, String ObjectName) {
		log.info(" : findElement Method Called");
		try {
			objName = ObjectName;
			xmlPath = Config.locatorsFile;
			listLocator = dataSourceOperations.GetXmlValue(ObjectName, xmlPath, PageName);
			locator = GetElement(listLocator.get(0), listLocator.get(1));
		} catch (Exception ex) {
			System.err.format("Unable to find element " + ObjectName +  "in XML file");
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
		return locator;
	}

	
	public static void Sleep(int millsecs) {
		log.info(" : Sleep Method Called");
		try {
			Thread.sleep(millsecs);
		} catch (Exception ex) {
			System.err.format("exception Occured" + ex);
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
