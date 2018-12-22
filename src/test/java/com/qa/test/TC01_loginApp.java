package com.qa.test;


import com.qa.common.TestBase;
import com.qa.pages.FlightFinder;
import com.qa.pages.LoginPage;
import com.qa.pages.PassengerDetails;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.common.DataProviders;
import com.qa.common.Keywords;


public class TC01_loginApp extends TestBase {

	@Test(priority = 1,dataProvider = "Login", dataProviderClass = DataProviders.class)
	
	public void LoginApp(String username,String password){
		
		LoginPage.loginToApp(username, password);
		String title = LoginPage.getPageTitle();
		Assert.assertEquals("Find a Flight: Mercury Tours:", title);
	}
	
	@Test(priority = 2,dataProvider = "SearchFlight", dataProviderClass = DataProviders.class)
	public void SearchFlight(String type , 
			 String passengerCount , 
			 String departure , 
			 String fromMonth , 
			 String fromDay , 
			 String arrival , 
			 String toMonth , 
			 String toDay , 
			 String serviceClass , 
			 String preference,
			 String firstName,
			 String lastName,
			 String creditCardNo
			 ) {
		
		FlightFinder.searchFight(type, passengerCount, departure, fromMonth, fromDay, arrival, toMonth, toDay, serviceClass, preference);
		PassengerDetails.inputPassengerDetailBookFlight(firstName, lastName, creditCardNo);
		Assert.assertTrue(Keywords.doesPageContainsText("Your itinerary has been booked!"));
		PassengerDetails.clickFlights();
	}
	
	@Test(priority = 3)
	public void LogOut(){
		LoginPage.signOut();
		Assert.assertTrue(Keywords.doesPageContainsText("Welcome back to Mercury Tours!"));
	}
	
}
	