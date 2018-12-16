package com.qa.common;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {

	private static ExtentReports extent;
	private static Logger log = Logger.getLogger(ExtentManager.class);

	public static ExtentReports getInstance() {
		log.info(" : ExtentReport getInstance Called");
		Config.initConstants();
		if (extent == null)
			createInstance(Config.ExtentReportsPath);
		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		log.info(" : ExtentReport createInstance Called");
		Config.initConstants();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(Config.ReportTitle);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(Config.ReportName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
}