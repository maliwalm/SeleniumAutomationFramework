package com.qa.common;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class JiraUtil {
    
	public final String JIRA_URL=Config.jiraURL;
	public final String JIRA_USERNAME=Config.jiraUsername;
	public final String JIRA_PASSWORD=Config.jiraPassword;
	public final String JIRA_ISSUE_REPORTER= Config.jiraReporter;
	public final String JIRA_PROJECT = Config.jiraProject;
	private static Logger log = Logger.getLogger(JiraUtil.class);
	
	public String createDefect( String summary , String description) {
		log.info(" : createDefect Method Called");
		//intiate connection with JIRA tool
		Client oClient = Client.create();
		oClient.addFilter(new HTTPBasicAuthFilter(JIRA_USERNAME,JIRA_PASSWORD));
		WebResource oWebResource = oClient.resource(JIRA_URL+ "/rest/api/2/issue");

		//frame the message which we want to post to JIRA defect board
		String sInput = "{\'fields\':{\"project\":{\"key\": \""+JIRA_PROJECT+"\"},\"summary\":\""+summary +"\",\"description\":\""+description +"\",\"issuetype\":{\"name\":\"Defect\"},\"issuetype\":{\"name\":\""+JIRA_ISSUE_REPORTER+"\"}}}";
		//post the framed value and store the output to extract the issueKey. So that, we can attach the screenshot of the failed test case along with the defect
		ClientResponse oResponse = oWebResource.type("application/json").post(ClientResponse.class, sInput);
		String sOutput = oResponse.getEntity(String.class);
		String defectId = sOutput.split(":")[2].split(",")[0].replace("\"", "");
		return defectId;
	}
	
	public void attachScreenshot(String defectId ,String screenshotPath) throws IOException {
		log.info(" : attachScreenshot Method Called");
		//create connection to attach the screenshot to the defect
		String sAuthenticationConnString = new String(org.apache.commons.codec.binary.Base64.encodeBase64((JIRA_USERNAME + ":" +JIRA_PASSWORD).getBytes()));

		CloseableHttpClient oHttpclient = HttpClients.createSystem();
		HttpPost oHttppost = new HttpPost(JIRA_URL + "/rest/api/2/issue/"+ defectId + "/attachments");
		oHttppost.setHeader("X-Atlassian-Token", "no-check");
		oHttppost.setHeader("Authorization", "Basic "+ sAuthenticationConnString);

		//screenshot attached to the defect
		File fileToUpload = new File(screenshotPath);
		FileBody oFileBody = new FileBody(fileToUpload);
		HttpEntity oEntity = MultipartEntityBuilder.create().addPart("file", oFileBody).build();
		oHttppost.setEntity(oEntity);
		CloseableHttpResponse oResponse;
		oResponse= oHttpclient.execute(oHttppost);
		oHttpclient.close();

		if (oResponse.getStatusLine().getStatusCode() == 200) {
		       System.out.println("Attached successfully");
		} else {
		      System.out.println("Error while attaching the file" + oResponse.getStatusLine().getStatusCode() );
		}
		
	}
}
