package com.qa.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount;
	static Logger log = Logger.getLogger(Retry.class);


	public Retry() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : Retry Constructor called");
	}

	public boolean retry(ITestResult result) {
		log.info(" : retry Method called");
		maxRetryCount = Integer.parseInt(Config.MaxRetryCountOnTestFailure);
		if (retryCount < maxRetryCount) {
			log.info(" : Retry Test onFailure called - Retrying test " + result.getName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			retryCount++;
			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		log.info(" : getResultStatusName Method called");
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
}
