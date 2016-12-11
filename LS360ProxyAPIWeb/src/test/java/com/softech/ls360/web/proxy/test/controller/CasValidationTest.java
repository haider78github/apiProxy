package com.softech.ls360.web.proxy.test.controller;

import java.net.HttpURLConnection;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.softech.ls360.proxy.api.util.network.NetworkUtil;
import com.softech.ls360.proxy.api.util.xml.XmlUtil;
import com.softech.ls360.web.proxy.test.TestUtil;
import com.softech.ls360.web.proxy.test.WebProxyAbstractTest;

public class CasValidationTest extends WebProxyAbstractTest {

	@Test
	public void test() {
		
	}
	
	//@Test
	public void test1() {
		
		String serviceUrl = "https://www.google.com.pk";
		String ticket = "ST-3fXxiG4oKH";
		String requestParameters = String.format("?ticket=%s", ticket);
		String casController = "http://localhost:8080/LS360ProxyAPIWeb/restful/sso/cas/lf?ticket=asdasd";
		//String casController = "https://api.360training.com/LS360ProxyAPIWeb/restful/sso/cas/lf";
		
		String casControllerUrl = casController + requestParameters;
		try {
			HttpURLConnection conn = TestUtil.getUrlConnection(casControllerUrl);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");
			
			boolean redirect = false;
			 
			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM 
						|| status == HttpURLConnection.HTTP_SEE_OTHER) {
					redirect = true;
				}
			}
			
			System.out.println("Response Code ... " + status);
			if (redirect) {
				
			}
			//setConnectionProperties(conn, "GET", userPass, apiKey);
		} catch (Exception e) {
			
		}
		
	}
	
	public static void setConnectionProperties(HttpURLConnection conn, String method, String userPass, String key) {
		
		String charset = "UTF-8";
		try {
			String encodedAuth = TestUtil.encodeBase64(userPass);
			String basicAuth = "Basic " + new String(encodedAuth);
			if ("POST".equalsIgnoreCase(method)) {        
				conn.setDoOutput(true);   // set request method to POST
			} else if ("GET".equalsIgnoreCase(method)) {  
				conn.setDoOutput(false);  // set request method to GET
			}
			conn.setRequestProperty("Content-Type", "application/json");
			//conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setRequestProperty("Accept-Charset", charset);
	        conn.setRequestProperty("key", key);
	        conn.setInstanceFollowRedirects(true);
		} catch (Exception e) {
			
		}
	}
	
	private void validateCasUser(String serviceUrl, String ticket) {
		
		String casValidatingUrl = "https://identity.linuxfoundation.org/cas/serviceValidate";
		String requestParameters = String.format("?service=%s&ticket=%s", serviceUrl, ticket);
		String url = casValidatingUrl + requestParameters;
		
		try {
			String xmlResponse = validateTicket(serviceUrl, ticket);
			Document xmlDocument = XmlUtil.getXmlDocument(xmlResponse);
			if (isValidUser(xmlDocument)) {
				Node userNode = getNode(xmlDocument, "cas:user"); 
				String userName = getNodeValue(userNode);
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String validateTicket(String serviceUrl, String ticket) throws Exception {
		
		String casValidatingUrl = "https://identity.linuxfoundation.org/cas/serviceValidate";
		String requestParameters = String.format("?service=%s&ticket=%s", serviceUrl, ticket);
		String lfUrl = casValidatingUrl + requestParameters;
		String certFilePath = "D:\\LinuxFoundationCert.crt";
		String xmlResponse = NetworkUtil.getResponseAsString(certFilePath, lfUrl);
		return xmlResponse;
	}
	
	private boolean isValidUser(Document xmlDocument) {
		
		Node authenticationFailureNode = getNode(xmlDocument, "cas:authenticationFailure");
		if (authenticationFailureNode != null) {
			return false;
		}
		return true;
	}
	
	private Node getNode(Document xmlDocument, String nodeName) {
		
		Node node = null;
		NodeList nodeList = xmlDocument.getElementsByTagName(nodeName);
		int nodeListLength = nodeList.getLength();
		if (nodeListLength > 0) {
			node = nodeList.item(0);
		}
		return node;
	}
	
	private String getNodeValue(Node node) {
		Node textNode = node.getFirstChild();
		return textNode.getNodeValue();
	}
	
}
