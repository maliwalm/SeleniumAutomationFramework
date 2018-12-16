package com.qa.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class FileOperations {

	private static final String PROP_FILE = Config.appConfig;
	private static Logger log = Logger.getLogger(FileOperations.class);

	public FileOperations() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : FileOperation Constructor Called");
		// Config.initConstants();
	}

	public static String getPropertyValue(String key) {
		log.info(" : GetPropertyValue Method Called");
		Properties props = new Properties();
		try {
			InputStream input = new FileInputStream(PROP_FILE);
			props.load(input);
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
		String value = "";
		if (key != null) {
			value = props.getProperty(key);
		}
		return value;
	}


	public static void setPropertyValue(String key, String data) {
		log.info(" : SetPropertyValue Method Called");
		InputStream fileIn = null;
		OutputStream fileOut = null;
		try {
			Properties configProperty = new Properties();
			fileIn = new FileInputStream(PROP_FILE);
			configProperty.load(fileIn);
			configProperty.setProperty(key, data);
			fileOut = new FileOutputStream(PROP_FILE);
			configProperty.store(fileOut, "Test properties");

		} catch (Exception ex) {
			log.error(ex.getMessage());
		} finally {
			try {
				fileOut.close();
			} catch (IOException ex) {
				log.error(ex.getMessage());
			}
		}
	}
}
