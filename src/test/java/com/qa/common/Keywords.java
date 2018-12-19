package com.qa.common;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.Status;


public class Keywords extends TestBase{

	private static WebDriverWait wait;
	private static Logger log = Logger.getLogger(Keywords.class);

	public static void moveToElement(By element){
		log.info(" : moveToElement Method Called");
		try{
			Actions a=new Actions(eDriver);
			a.moveToElement(eDriver.findElement(element)).perform();
			//TestLogger.log(Status.PASS, "\"" + "Scrolled to " + "\"" + objName + "\"");
		}
		catch(NoSuchElementException e){
			System.err.format("No Element Found to enter text" + e);
		}
	}


	public static void waitForElementVisibility(By element , int secs){
		log.info(" : waitForElementVisibility Method Called");
		try
		{
			wait = new WebDriverWait(eDriver, secs);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
	}


	public static void waitForElementInVisibility(By element , int secs){
		log.info(" : waitForElementInVisibility Method Called");
		try
		{
			wait = new WebDriverWait(eDriver, secs);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
	}

	public static void setText(By textBox, String value) {
		log.info(" : setText Method Called");
		try{
			Keywords.moveToElement(textBox);
			wait = new WebDriverWait(eDriver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			eDriver.findElement(textBox).sendKeys(value);
			TestLogger.log(Status.PASS, "\"" + value + "\"" + " is entered in text box " + "\"" + objName + "\"");
			if (Config.screenshotOnPass.equalsIgnoreCase("true")){
				String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
					test.get().addScreenCaptureFromPath(screenShotPath);
			}
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
		catch (IOException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
	}
	

	public static void setUniqueText(By textBox) {
		log.info(" : setUniqueText Method Called");
		try{
			String uniqueText = Util.generateUniqueName();
			wait = new WebDriverWait(eDriver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			eDriver.findElement(textBox).sendKeys(uniqueText);
			TestLogger.log(Status.PASS, "Unique text is entered in the text box "  + "\"" + objName + "\"");
			if (Config.screenshotOnPass.equalsIgnoreCase("true")){
				String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
					test.get().addScreenCaptureFromPath(screenShotPath);
			}
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
		catch (IOException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
	}

	public static void click(By button) {
		log.info(" : click Method Called");
		try{
			Keywords.moveToElement(button);
			wait = new WebDriverWait(eDriver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(button));
			eDriver.findElement(button).click();
			TestLogger.log(Status.PASS, "\"" +" Clicked " + "\"" + objName + "\"");
			if (Config.screenshotOnPass.equalsIgnoreCase("true")){
				String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
					test.get().addScreenCaptureFromPath(screenShotPath);
			}
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
		catch (IOException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
	}
	

	public static void selectDropdownValue(By dropdown, String value) {
		log.info(" : selectDropdownValue Method Called");
		try{
			Select drpdown = new Select(eDriver.findElement(dropdown));
			drpdown.selectByVisibleText(value);
			TestLogger.log(Status.PASS, "\"" +" Selected " + "\"" + value + "\"" + "from Dropdown");
			if (Config.screenshotOnPass.equalsIgnoreCase("true")){
				String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
					test.get().addScreenCaptureFromPath(screenShotPath);
			}
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
		catch (IOException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
	}


	public static String getPageTitle()
	{
		log.info(" : selectDropdownValue Method Called");
		return eDriver.getTitle();
	} 

}
