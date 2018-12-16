package com.qa.pages;

import org.openqa.selenium.By;

import com.qa.common.GetLocator;
import com.qa.common.Keywords;
public class LoginPage {
	
	   
	    private static String pageName = "Login";
	    private static By textBoxUsername = GetLocator.findElement(pageName,"TextBoxUsername");
	    private static By textBoxPassword = GetLocator.findElement(pageName,"TextBoxPassword");
	    private static By btnlogin = GetLocator.findElement(pageName,"ButtonLogin");
	    
	    public static void setUserName(String strUserName) {
	    	Keywords.setText(textBoxUsername ,strUserName);
	    }
	      
	    public static void setPassword(String strPassword){
	    	Keywords.setText(textBoxPassword ,strPassword);
	    }
		 
	    public static void clickLogin(){
	    	Keywords.click(btnlogin);
	    }
	    
	    public static void clickRemberMe(){
	    	Keywords.click(GetLocator.findElement(pageName, "RememberMeCheckBox"));
	    }
        public static void loginToApp(String strUserName,String strPassword){
	        //Fill user name
	        setUserName(strUserName);
	        //Fill password
	        setPassword(strPassword);
	        clickRemberMe();
	        //Click Login button
	        clickLogin();
	   } 
        
        public static String getPageTitle() {
			return Keywords.getPageTitle();
		}
                	
}  
        

	
