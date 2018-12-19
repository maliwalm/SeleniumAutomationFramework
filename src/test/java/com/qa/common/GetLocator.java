package com.qa.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class GetLocator extends TestBase {

	private static String xmlPath;
	private static List<String> listLocator;
	private static By locator;
	private static Logger log = Logger.getLogger(GetLocator.class);
	
	public static By GetElement(String locatorValue, String locatorType) throws Exception {
		log.info(" : GetElement Method Called");
		switch (locatorType.toLowerCase()) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
			return By.tagName(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("DOM FINDER : did not find the correct dom finder type in the file for locator value: "
					+ locatorValue);
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
			System.err.format("Failed to read data from XML file" + ex);
		}
		return locator;
	}

	
	public static void Sleep(int millsecs) {
		log.info(" : Sleep Method Called");
		try {
			Thread.sleep(millsecs);
		} catch (Exception ex) {
			System.err.format("exception Occured" + ex);
		}
	}

}
