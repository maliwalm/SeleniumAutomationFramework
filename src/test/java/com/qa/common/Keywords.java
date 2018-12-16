package com.qa.common;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import com.qa.common.GetLocator;;

public class Keywords extends TestBase{

	private static WebDriverWait wait;

	public static boolean isElementPresent(By element){
		try{
			wait = new WebDriverWait(eDriver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			eDriver.findElement(element);                                                             
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}


	public static void moveToElement(By element){
		try{
			Actions a=new Actions(eDriver);
			a.moveToElement(eDriver.findElement(element)).perform();
			//TestLogger.log(Status.PASS, "\"" + "Scrolled to " + "\"" + objName + "\"");
		}
		catch(NoSuchElementException e){
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public static String getText(By element){
		wait = new WebDriverWait(eDriver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		return(eDriver.findElement(element).getText());	
	}

	public static String getAttribute(By element, String attribute){
		wait = new WebDriverWait(eDriver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
		return (eDriver.findElement(element).getAttribute(attribute));	
	}

	public static void clear(By textBox) {
		try{
			wait = new WebDriverWait(eDriver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(textBox));
			eDriver.findElement(textBox).clear();
			TestLogger.log(Status.PASS, "\"" +" Text Box Cleared ---> " + "\"" + objName + "\"");	
			if (Config.screenshotOnPass.equalsIgnoreCase("true")){
				String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
					test.get().addScreenCaptureFromPath(screenShotPath);
			}
		}
		catch (NoSuchElementException e){
			System.err.format("No Element Found to enter text" + e);
		}
		catch (IOException e){
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public static void tickCheckbox(By checkbox) {
		try{
			eDriver.findElement(checkbox).click();
			TestLogger.log(Status.PASS, "\"" +"Ticked CheckBox " + "\"" + objName + "\"");		
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


	public static String getModalPopMessage(By modal , By message){
		eDriver.switchTo().activeElement();
		wait = new WebDriverWait(eDriver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(modal));	
		return eDriver.findElement(message).getText();
	}

	public static void clickModalPopButton(By modal , String buttonName){
		try{
		eDriver.switchTo().activeElement();
		WebElement modalbuttons = eDriver.findElement(modal);
		List<WebElement> options = modalbuttons.findElements(By.className("btn"));
		for (WebElement option : options) {
			if (buttonName.equals(option.getText())){
				option.click();
				TestLogger.log(Status.PASS, "Clicked on the modal pop up button"  + "\"" + objName + "\"");	
				if (Config.screenshotOnPass.equalsIgnoreCase("true")){
					String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
						test.get().addScreenCaptureFromPath(screenShotPath);
				}
				break;
			}
		  }
		}
			catch (NoSuchElementException ex){
				System.err.format("No Element Found to enter text" + ex);
			}
			catch (IOException ex){
				System.err.format("No Element Found to enter text" + ex);
			}
	}


	public static void jsClick(){
		try{
		((JavascriptExecutor) eDriver).executeScript(" $('.modal-dialog').filter(':visible').find('.btn').last().click()");
		TestLogger.log(Status.PASS, "Button Clicked");
		if (Config.screenshotOnPass.equalsIgnoreCase("true")){
			String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
				test.get().addScreenCaptureFromPath(screenShotPath);
		}
		}
		catch (IOException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
		
	}

	public static void waitForElementVisibility(By element , int secs){
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
	

	public static void focusSetText(By textBox, String value) {
		try{
			wait = new WebDriverWait(eDriver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			Actions action = new Actions(eDriver); 
			action.sendKeys(Keys.TAB).build().perform();
			action.sendKeys(value).build().perform(); 
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

	public static void searchAndSelect(By textBox , By list , String value){
		try {
			wait = new WebDriverWait(eDriver, 10);
			GetLocator.Sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBox));
			eDriver.findElement(textBox).sendKeys(value);
			wait.until(ExpectedConditions.elementToBeClickable(list));
			eDriver.findElement(textBox).sendKeys(Keys.TAB);
			TestLogger.log(Status.PASS, "\"" + value + "\"" + " searched & selected from " + "\"" + objName + "\"");
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
		try{
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
	
	public static void clickUsingJs(By element){
		try {
			
		System.out.println("clicking sort using JS");
		JavascriptExecutor executor = (JavascriptExecutor)eDriver;
		executor.executeScript("arguments[0].click();", eDriver.findElement(element));
		if (Config.screenshotOnPass.equalsIgnoreCase("true")){
			String screenShotPath = Util.captureScreenshot(eDriver, "Passed");
				test.get().addScreenCaptureFromPath(screenShotPath);
		}
	}
	catch (IOException ex){
		System.err.format("No Element Found to enter text" + ex);
	}
	}

	public static void clickLinkText(By link, String linkText) {
		try{
			wait = new WebDriverWait(eDriver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(link));
			eDriver.findElement(By.linkText(linkText)).click();
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

	public static void scrollAndClick(By button) {
		try{
			Actions a=new Actions(eDriver);
			a.moveToElement(eDriver.findElement(button)).click().build().perform();
			TestLogger.log(Status.PASS, "\"" + "Scrolled & Clicked " + "\"" + objName + "\"");
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

	public static void keyPressEnter(By textBox){
		eDriver.findElement(textBox).sendKeys(Keys.ENTER);
	}

	public static void selectDropdownValue(By dropdown, String value) {
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

	public static boolean verifyElement(By element) {
		try{
			wait = new WebDriverWait(eDriver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			if (eDriver.findElement(element).isDisplayed()){
				return true;
			}
		}
		catch (NoSuchElementException ex){
			System.err.format("No Element Found to enter text" + ex);
		}
		return false;
	} 


	public static String getPageTitle()
	{
		return eDriver.getTitle();
	} 


	public static void waitForAjax() {
		while (true) {
			Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) eDriver)
					.executeScript("return jQuery.active == 0");
			if (ajaxIsComplete) {
				break;
			}
			GetLocator.Sleep(500);
		}
	}


	public static void verifyNotification(By element) {
		try{
		    WebDriverWait wait = new WebDriverWait(driver,20);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}catch(NoSuchElementException ex){
		    System.err.println("Error while waiting for the notification to appear: "+ ex.getMessage());
		}
	}
}
