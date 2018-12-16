package com.qa.test;


import com.qa.common.TestBase;
import com.qa.pages.LoginPage;

import org.testng.annotations.Test;

import com.qa.common.DataProviders;


public class TC01_loginApp extends TestBase {

	@Test(dataProvider = "Login", dataProviderClass = DataProviders.class)
	
	public void LoginApp1(String username,String password){
		
		LoginPage.loginToApp(username, password);
		
	}
}