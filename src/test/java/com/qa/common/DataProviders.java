package com.qa.common;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.qa.common.Config;
import com.qa.common.DataSourceOperations;

public class DataProviders {
	 static String seperator = ",";
	 private static Logger log = Logger.getLogger(DataProviders.class);

	 @DataProvider(name="Login")
     public static Object[][] getDataLoginData(){
		 log.info(" : getDataFromDataprovider Method Called");
		 String [][] dataSet = DataSourceOperations.CSVDataProvider("LoginApp", seperator ,Config.inputFile);
         return dataSet;
	 }
	 
	 @DataProvider(name="SearchFlight")
     public static Object[][] getFlightData(){
		 log.info(" : getDataFromDataprovider Method Called");
		 String [][] dataSet = DataSourceOperations.CSVDataProvider("SearchFlight", seperator ,Config.inputFile);
         return dataSet;
	 }
	 
}
