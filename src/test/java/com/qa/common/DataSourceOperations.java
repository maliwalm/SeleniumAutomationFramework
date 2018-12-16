package com.qa.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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

	// This method is used to parse a XML with below format to a HashMap
	/*
	 * <?xml version="1.0" encoding="UTF-8"?> <OR> <Page name="LoginPage">
	 * <Object name = 'UsernameTextbox'> <type>id</type> <value>Email</value>
	 * </Object> <Object name = 'PasswordTextBox'> <type>id</type>
	 * <value>Password</value> </Object> <Object name = 'LoginButton'>
	 * <type>xpath</type> <value>//*[@id='loginFormId']/div[4]/div/input</value>
	 * </Object> </Page> </OR>
	 */

	public Map<String, List<String>> ParseXml(String XMLFile)
			throws ParserConfigurationException, SAXException, IOException {
		log.info(" : Parse Object Locator XML - ParseXml Method Called");
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		File inputfile = new File(XMLFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docbuilder = dbFactory.newDocumentBuilder();
		Document doc = docbuilder.parse(inputfile);
		doc.getDocumentElement().normalize();
		NodeList pagelist = doc.getElementsByTagName("Page");
		for (int tmp = 0; tmp < pagelist.getLength(); tmp++) {
			Node pageNode = pagelist.item(tmp);
			NamedNodeMap nodeMap = pageNode.getAttributes();
			for (int j = 0; j < nodeMap.getLength(); j++) {
				for (int k = 0; k < nodeMap.getLength(); k++) {
					if (pageNode.getNodeType() == Node.ELEMENT_NODE) {
						NodeList objectList = pageNode.getChildNodes();
						for (int i = 0; i < objectList.getLength(); i++) {
							Node childNode = objectList.item(i);
							if (childNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) childNode;
								List<String> vals = new ArrayList<String>();
								map.put(eElement.getAttribute("name").toString(), vals);
								vals.add(nodeMap.getNamedItem("name").getNodeValue().toString());
								vals.add(eElement.getElementsByTagName("type").item(0).getTextContent().toString());
								vals.add(eElement.getElementsByTagName("value").item(0).getTextContent().toString());
							}
						}
					}
				}
			}
		}
		return map;
	}
	public List<String> GetXmlValue(String variablename, String XMLFile, String module)
			throws IOException, ParserConfigurationException, SAXException {
		log.info(" : GetXMLValue Method Called");
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
