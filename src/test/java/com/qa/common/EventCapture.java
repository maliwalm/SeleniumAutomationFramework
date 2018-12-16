package com.qa.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;


public class EventCapture implements WebDriverEventListener {

	static Logger log;

	public EventCapture() {
		log = Logger.getLogger(EventCapture.class);
		PropertyConfigurator.configure("log4j.properties");
	}


	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		log.debug(": afterChangeValueOf :: Webelement :" + arg0);
	}


	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		log.debug(": afterClickOn :: After clicking on the element :" + arg0);
	}

	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		log.debug(": afterFindBy :: After finding the element : " + arg0);
	}

	public void afterNavigateBack(WebDriver arg0) {
		log.debug(": afterNavigateBack :: After navigating back");
	}


	public void afterNavigateForward(WebDriver arg0) {
		log.debug(": afterNavigateForward :: After navigating forward");
	}


	public void afterNavigateRefresh(WebDriver arg0) {
		log.debug(": afterNavigateRefresh :: After navigating refresh");
	}


	public void afterNavigateTo(String arg0, WebDriver arg1) {
		log.debug(": afterNavigateTo :: " + arg0);
	}

	public void afterScript(String arg0, WebDriver arg1) {
		log.debug(": afterScript ::");
	}


	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		log.debug(": beforeChangeValueOf :: Before changing value of " + arg0);
	}


	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		log.debug(": beforeClickOn :: " + arg0);
	}


	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		log.debug(": beforeFindBy :: element : " + arg0);
	}

	public void beforeNavigateBack(WebDriver arg0) {
		log.debug(": beforeNavigateBack ::");
	}


	public void beforeNavigateForward(WebDriver arg0) {
		log.debug(": beforeNavigateForward ::");
	}

	public void beforeNavigateRefresh(WebDriver arg0) {
		log.debug(": beforeNavigateRefresh ::");
	}

	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		log.debug(": beforeNavigateTo ::" + arg0);
	}

	public void beforeScript(String arg0, WebDriver arg1) {
		log.debug(": beforeScript ::");
	}

	public void onException(Throwable arg0, WebDriver arg1) {
		log.info(": onException :: " + arg0.getMessage());
	}


	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}

}
