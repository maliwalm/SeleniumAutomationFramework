package com.qa.pages;

import com.qa.common.GetLocator;
import com.qa.common.Keywords;

public class PassengerDetails {
	private static String pageName = "PassengerDetails";
    
	public static void setFirstName(String firstName) {
    	Keywords.setText(GetLocator.findElement(pageName,"textboxFirstName") ,firstName);
    }
	
	public static void setLastName(String lastName) {
    	Keywords.setText(GetLocator.findElement(pageName,"textboxLastName") ,lastName);
    }
	
	public static void setCreditCard(String creditcardNo) {
    	Keywords.setText(GetLocator.findElement(pageName,"textboxCardNumber") ,creditcardNo);
    }
	
	public static void clickSecureFlight() {
    	Keywords.click(GetLocator.findElement(pageName,"buttonSecureFlight"));
    }
	
	public static void clickFlights() {
    	Keywords.click(GetLocator.findElement(pageName,"buttonHome"));
    	Keywords.waitForElementVisibility(GetLocator.findElement("Login","buttonLogout"), 30);
    }
	
	public static void inputPassengerDetailBookFlight(String firstName,String lastName , String creditcardNo) {
		setFirstName(firstName);
		setLastName(lastName);
		setCreditCard(creditcardNo);
		clickSecureFlight();
		
	}
	
}
