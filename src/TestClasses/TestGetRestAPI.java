package TestClasses;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import Links.URLs;
import org.junit.Test;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;
import java.io.FileNotFoundException;


//import java.util.Iterator;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONStreamAware;
//import org.json.simple.JSONValue;
//import org.json.simple.parser.ParseException;
//import org.junit.Ignore;
//import Links.FilesPaths;
//import Utils.JSONUtils;
//import requestHandling.HandleRequestReponse;

public class TestGetRestAPI {

	//check get rest api by get all data 
    @Test
	public void TestGetAllData() throws IOException {
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.API , HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));

	}
	
	//check get rest api by get only one id exist in the data 
	@Test
	public void TestGetExistIDBooking() throws IOException {
		// 1. connect to server and open connection (get HttpURLConnection object)
		String Url = URLs.API + "2";
		System.out.println(Url);
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.API , HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		connection.addRequestProperty("Content-Type", "application/json");
		connection.addRequestProperty("Content-Length", "<calculated when request is sent>");
		connection.addRequestProperty("Host", "<calculated when request is sent>");
		connection.addRequestProperty("User-Agent", "PostmanRuntime/7.26.8");
		connection.addRequestProperty("Accept", "*/*");
		connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
		connection.addRequestProperty("Connection", "keep-alive");
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));

	}
	
	//check get rest api by get only one id not exist in the data 
    @Test(expected= FileNotFoundException.class)
	public void TestGetNotExistIDBooking() throws IOException  {
		// 1. connect to server and open connection (get HttpURLConnection object)
		String Url = URLs.API + "200" ;
		HttpURLConnection connection = RestClientHandler.connectServer(Url, HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("able to connect to webservice", connection.getResponseCode() == 404);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is not empty and you request id not exist", response.equals("Not Found"));
	}
	


//	//@Test
//	public void testUpdateUser() throws Exception {
//		// 1. Open Connection --- HttpURLConnection
//		String url = URLs.ReqResAPI+"/2";
//		//url = url.replace("userID", "2");
//		System.out.println(url);
//		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
//				HTTPRequestsContentTypes.JSON);
//		// 2. Prepare Json Object
//		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.UpdateUserJSONFile);
//		System.out.println(resquestJSONObject);
//		// 3. Post Request
//		RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
//		// 4. Reading Response
//		System.out.println(connection.getResponseCode());
//		String response = RestClientHandler.readResponse(connection);
//		System.out.println(response);
//	}

//	@Test
//	public void TestPostData() throws Exception
//	{
//		//1. Get HttpURLConnection
//		HttpURLConnection connection= RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST , HTTPRequestsContentTypes.JSON);
//		
//		//2. Read JSON file		
//		String json=JSONUtils.readJSONObjectFromFile(FilesPaths.CreatBookingJSONFile);
//		
//		//3.sent post request
//		RestClientHandler.sendPost(connection, json, HTTPRequestsContentTypes.JSON);
//		
//		//4. Read Response
//		String response=RestClientHandler.readResponse(connection);
//		System.out.println(response);
//		//5. convert string to json object 
//		JSONObject obj= (JSONObject) JSONUtils.convertStringToJSON(response);
//		System.out.println(obj.get("bookingid"));
//	}

	
//	@Ignore
//	@Test
//	public void TestReqRes()  {
//		try
//		{
//		// 1. connect to server and open connection (get HttpURLConnection object)
//		HttpURLConnection connection = RestClientHandler.connectServer(URLs.ReqResAPI, HTTPMethod.GET,
//				HTTPRequestsContentTypes.JSON);
//		
//		//2. Send Post Request
//		RestClientHandler.sendGet(connection, "", HTTPRequestsContentTypes.JSON);
//		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
//		// 3. reading response using input stream
//		String response = RestClientHandler.readResponse(connection);
//		System.out.println(response);
//		assertTrue("Data is empty", !response.equals(""));
//		}catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//
//	}
}
