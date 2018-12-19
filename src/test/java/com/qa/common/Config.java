package com.qa.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	public static FileInputStream ofileInputStream;
	public static String ExtentReportsPath;
	public static String ReportTitle;
	public static String ReportName;
	public static String ScreenShotsPath;
	public static String ChromeDriverPath;
	public static String FirefoxDriverPath;
	public static String IEDriverPath;
	public static String ApplicationURL;
	public static String Browser;
	public static String MaxRetryCountOnTestFailure;
	public static String ObjectRepository;
	public static String inputFile;
	public static String locatorsFile;
	public static String ZipPath;
	public static String mailFrom;
	public static String mailTo;
	public static String mailUserName;
	public static String mailPassword;
	public static String mailHost;
	public static String setReportEmail;
	public static String testData;
	public static String screenshotOnFailure;
	public static String screenshotOnSkip;
	public static String screenshotOnPass;
	public static String appConfig;
	public static String jiraUsername;
	public static String jiraPassword;
	public static String jiraURL;
	public static String jiraReporter;
	public static String logDefectOnFailure;
	

	public Config() {
	}


	public static void initConstants() {

		String path = System.getProperty("user.dir") + "\\src\\test\\resource\\app.properties";
		Properties prop = new Properties();

		try {
			ofileInputStream = new FileInputStream(path);
			if (ofileInputStream != null) {
				prop.load(ofileInputStream);
				inputFile = System.getProperty("user.dir") + prop.getProperty("CSVFile");
				locatorsFile = System.getProperty("user.dir") + prop.getProperty("XMLFile");
				Browser = prop.getProperty("browser");
				ExtentReportsPath = System.getProperty("user.dir") + prop.getProperty("ExtentReportsPath");
				ReportTitle = prop.getProperty("ReportTitle");
				ReportName = prop.getProperty("ReportName");
				MaxRetryCountOnTestFailure = prop.getProperty("MaxRetryCountOnTestFailure");
				ScreenShotsPath = System.getProperty("user.dir") + prop.getProperty("ScreenShotsPath");
				ChromeDriverPath = System.getProperty("user.dir") + prop.getProperty("ChromeDriverPath");
				FirefoxDriverPath = System.getProperty("user.dir") + prop.getProperty("FirefoxDriverPath");
				IEDriverPath = System.getProperty("user.dir") + prop.getProperty("IEDriverPath");
				ObjectRepository = System.getProperty("user.dir") + prop.getProperty("ObjectRepository");
				ZipPath = System.getProperty("user.dir") + prop.getProperty("ZipPath");
				mailFrom = prop.getProperty("mailFrom");
				mailTo = prop.getProperty("mailTo");
				mailUserName = prop.getProperty("mailUserName");
				mailPassword = prop.getProperty("mailPassword");
				mailHost = prop.getProperty("mailHost");
				setReportEmail = prop.getProperty("SetReportEmail");
				testData = System.getProperty("user.dir") + prop.getProperty("TestData");
				screenshotOnFailure = prop.getProperty("ScreenshotOnFailure");
				screenshotOnSkip = prop.getProperty("ScreenshotOnSkip");
				screenshotOnPass = prop.getProperty("ScreenshotOnPass");
				ApplicationURL = prop.getProperty("AppUrl");
				appConfig = prop.getProperty("AppConfig");
				logDefectOnFailure=prop.getProperty("logDefectOnFailure");
				jiraURL=prop.getProperty("JiraURL");
				jiraUsername=prop.getProperty("JiraUserID");
				jiraPassword=prop.getProperty("JiraPassword");
				jiraURL=prop.getProperty("JiraURL");
				jiraReporter=prop.getProperty("JiraDefectReporter");
			}

		} catch (IOException e) {
			System.err.println("Cannot find the app.properties file at " + path);
		} finally {
			if (ofileInputStream != null) {
				try {
					ofileInputStream.close();
				} catch (IOException e) {
					System.err.println("Cannot close the app.properties file instance.");
				}
			}
		}

	}

}