package com.qa.common;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.qa.common.Config;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class TestBase {

	public static WebDriver driver;
	public static EventFiringWebDriver eDriver;
	protected static ExtentReports extent;
	protected static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ExtentTest TestLogger;
	protected Util util;
	protected static String objName;
	public static JiraUtil jira = new JiraUtil();

	protected static String seperator = ",";
	protected static DataSourceOperations dataSourceOperations = new DataSourceOperations();
	protected static SoftAssert softAssert = new SoftAssert();
	static Logger log = Logger.getLogger(TestBase.class);

	public TestBase() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : TestBase - Constructor called");
	}
	@BeforeSuite
	protected void BeforeSuite() throws IOException {
		log.info("-----------------EXECUTION START----------------------");
		log.info(" : TestBase - BeforeSuite called");
		extent = ExtentManager.getInstance();
		Util.isFolderExistAtPath(Config.ScreenShotsPath);
		Util.isFolderExistAtPath(Config.ZipPath);
		FileUtils.cleanDirectory(new File(Config.ScreenShotsPath));
		}

	@BeforeClass
	protected void BeforeClass() {
		log.info(" : TesTBase - BeforeTest called");
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
	}


	@BeforeTest
	protected void BeforeTest() {
		log.info(" : TesTBase - BeforeTest called");
	}


	@BeforeMethod
	protected void BeforeMethod(Method method) throws InterruptedException {
		log.info(" : TestBase - BeforeMethod called");
		Browser.openbrowser();
		TestLogger = parentTest.get().createNode(method.getName());
		test.set(TestLogger);
	}

	@AfterMethod
	protected void AfterMethod(ITestResult result) {
		log.info(" : TestBase - AfterMethod called");
		try {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.get().fail(result.getThrowable());
			test.get().fail(MarkupHelper.createLabel(result.getName() + "Test case FAILED", ExtentColor.RED));
			if (Config.screenshotOnFailure.equalsIgnoreCase("true")){
				String screenShotPath = Util.captureScreenshot(eDriver, "Failure");
				if (Config.logDefectOnFailure.equalsIgnoreCase("true")){
					String summary = result.getName() + "Test case Failed";
					String description = result.getName() + "Test case Failed";
					String defectId = jira.createDefect(summary, description);
					jira.attachScreenshot(defectId, screenShotPath);
				}
				
				test.get().fail("Snapshot below: " + test.get().addScreenCaptureFromPath(screenShotPath));
			}
		} else 
			if (result.getStatus() == ITestResult.SKIP){
				test.get().skip(result.getThrowable());
				test.get().skip(MarkupHelper.createLabel(result.getName() + "Test case SKIPPED", ExtentColor.AMBER));
				if (Config.screenshotOnSkip.equalsIgnoreCase("true")){
					String screenShotPath = Util.captureScreenshot(eDriver, "Skipped");
					test.get().skip("Snapshot below: " + test.get().addScreenCaptureFromPath(screenShotPath));
					}
			}
		else{
			test.get().pass(MarkupHelper.createLabel(result.getName() + "Test case PASSED", ExtentColor.GREEN));
		}
		}
		catch (IOException e){
			System.err.format("No Element Found to enter text" + e);
		}
	}


	@AfterTest
	protected void AfterTest() {
		log.info(" : TestBase - AfterTest called");
	}


	@AfterSuite
	protected void afterSuite() throws Exception {
		log.info(" : TestBase - AfterSuite called");
		if (eDriver != null)
			eDriver.quit();
		driver = null;
		extent.flush();
		Util.setScreenshotRelativePath();
		String zipFilePath = Config.ZipPath + Util.generateUniqueName() + ".zip";
		Util.zipFolder(Paths.get(Config.ScreenShotsPath), Paths.get(zipFilePath));
		if (Config.setReportEmail.equalsIgnoreCase("True")) {
			Util.SendMail(zipFilePath);
		}
		log.info("---------------EXECUTION COMPLETED--------------------");
	}
}
