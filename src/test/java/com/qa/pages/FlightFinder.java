package com.qa.pages;

import com.qa.common.GetLocator;
import com.qa.common.Keywords;

public class FlightFinder {

	private static String pageName = "FlightFinder";
	
	 public static void selectType(String type) {
		 if (type.equals("Round Trip")) {
	    	Keywords.click(GetLocator.findElement(pageName, "radioRoundTrip"));
		 }
		 else {
			 Keywords.click(GetLocator.findElement(pageName, "radioOneWay"));
		 }
	    }
	 
	 public static void selectPassengerCount(String passengerCount) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listPassengers"), passengerCount);
	    }
	 
	 
	 public static void selectDeparture(String departure) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listDepartureFrom"), departure);
	    }
	 
	 public static void selectFromMonth(String fromMonth) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listFromMonth"), fromMonth);
	    }
	 
	 public static void selectFromDay(String fromDay) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listFromDay"), fromDay);
	    }
	 
	 public static void selectArrival(String arrival) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listArrivingIn"), arrival);
	    }
	 
	 public static void selectToMonth(String toMonth) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listToMonth"), toMonth);
	    }
	 
	 public static void selectToDay(String toDay) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listToDay"), toDay);
	    }
	 
	 public static void selectPreference(String preference) {
	    	Keywords.selectDropdownValue(GetLocator.findElement(pageName, "listPreference"), preference);
	    }
	 
	 public static void selectServiceClass(String serviceClass) {
		 if (serviceClass.equals("Business Class")) {
	    	Keywords.click(GetLocator.findElement(pageName, "radioBusinessClass"));
		 }
		 else if (serviceClass.equals("First Class"))
			 Keywords.click(GetLocator.findElement(pageName, "radioFirstClass"));
	    }
	 
	 public static void clickContinue() {
		 Keywords.click(GetLocator.findElement(pageName, "buttonContinue"));
		 Keywords.waitForElementVisibility(GetLocator.findElement(pageName, "buttonReserveFlight"), 30);
	 }
	 
	 public static void clickReserveFlight() {
		 Keywords.click(GetLocator.findElement(pageName, "buttonReserveFlight"));
		 Keywords.waitForElementVisibility(GetLocator.findElement("PassengerDetails", "buttonSecureFlight"), 30);
	 }
	 
	  
	 
	 public static void searchFight(
			 String type , 
			 String passengerCount , 
			 String departure , 
			 String fromMonth , 
			 String fromDay , 
			 String arrival , 
			 String toMonth , 
			 String toDay , 
			 String serviceClass , 
			 String preference ) {
		 
		 selectType(type);
		 selectPassengerCount(passengerCount);
		 selectDeparture(departure);
		 selectFromMonth(fromMonth);
		 selectFromDay(fromDay);
		 selectArrival(arrival);
		 selectToMonth(toMonth);
		 selectToDay(toDay);
		 selectServiceClass(serviceClass);
		 selectPreference(preference);
		 clickContinue();
		 clickReserveFlight();
	 }
	 
}