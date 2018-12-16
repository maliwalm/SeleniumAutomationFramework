package com.qa.common;

import org.testng.annotations.DataProvider;

import com.qa.common.Config;
import com.qa.common.DataSourceOperations;

public class DataProviders {
	 static String seperator = ",";
	 

	 @DataProvider(name="Login")
     public static Object[][] getDataFromDataprovider(){
		 String [][] dataSet = DataSourceOperations.CSVDataProvider("LoginApp", seperator ,Config.inputFile);
         return dataSet;
	 }
}
