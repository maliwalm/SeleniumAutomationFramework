package com.qa.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;

import org.testng.annotations.ITestAnnotation;


public class TestNGListeners implements IAnnotationTransformer {

	protected Util util;
	static Logger log = Logger.getLogger(TestNGListeners.class);


	public TestNGListeners() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : TestNGListeners Constructor called");
	}


	@Override
	public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

		if (retry == null) {
			testannotation.setRetryAnalyzer(Retry.class);
		}
	}

}
