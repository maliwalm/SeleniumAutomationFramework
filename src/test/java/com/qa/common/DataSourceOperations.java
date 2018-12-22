package com.qa.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class DataSourceOperations {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private List<String> valueOfElement = null;

	private static Logger log = Logger.getLogger(DataSourceOperations.class);

	public DataSourceOperations() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : FileOperation Constructor Called");
	}

	
	public List<String> GetXmlValue(String variablename, String XMLFile, String module) {
		log.info(" : GetXMLValue Method Called");
		try {
		valueOfElement = new ArrayList<String>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(XMLFile));
		doc.getDocumentElement().normalize();
		if (doc.hasChildNodes()) {
			NodeList nodeList = doc.getChildNodes();
			for (int count = 0; count < nodeList.getLength(); count++) {
				Node tempNode = nodeList.item(count);
				if (tempNode.hasChildNodes()) {
					NodeList moduleList = tempNode.getChildNodes();
					for (int j = 0; j < moduleList.getLength(); j++) {
						Node moduleNode = moduleList.item(j);
						if (moduleNode.getNodeType() == Node.ELEMENT_NODE && moduleNode.getNodeName() == module) {
							if (moduleNode.hasChildNodes()) {
								NodeList childList = moduleNode.getChildNodes();
								for (int i = 0; i < childList.getLength(); i++) {
									Node childNode = childList.item(i);
									if (childNode.getNodeType() == Node.ELEMENT_NODE
											&& childNode.getNodeName().toString() == "ElementProperty") {
										if (childNode.hasAttributes()) {
											// get attributes names and values
											NamedNodeMap nodeMap = childNode.getAttributes();
											for (int k = 0; k < nodeMap.getLength(); k++) {

												Node node = nodeMap.item(k);
												if (node.getNodeName() == "NameOfElement") {
													if (node.getNodeValue().equals(variablename)) {
														valueOfElement.add(childNode.getTextContent());
														Node propertyType = nodeMap.getNamedItem("Type");
														valueOfElement.add(propertyType.getNodeValue());
														break;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return valueOfElement;
		}
		catch (Exception ex) {
			System.err.format("Exception" +ex);
			log.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
	}
	
	
	public String[] ReadCSV(String testCaseId, String separator, String filename) {
		log.info(" : ReadCSV Method Called");
		BufferedReader br = null;
		String[] values = null;
		String line = "";

		try {
			File file = new File(filename);
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith(testCaseId.trim())) {
					values = line.split(separator);
					break;
				}
			}
		} catch (FileNotFoundException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					log.error(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		return values;
	}


	public static String[][] CSVDataProvider(String testCaseId, String separator, String filename) {
		log.info(" : CSVDataProvider Method Called");
		List<String[]> dataArr = new ArrayList<String[]>();
		BufferedReader br = null;
		String[] values = null;
		String line = "";
		String[][] strArray = null;
		try {
			File file = new File(filename);
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith(testCaseId.trim())) {
					line = line.substring(line.indexOf(separator) + 1);
					values = line.split(separator);
					dataArr.add(values);
					strArray = dataArr.toArray(new String[0][0]);
				}
			}
		} catch (FileNotFoundException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ex) {
					log.error(ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		return strArray;
	}
}
