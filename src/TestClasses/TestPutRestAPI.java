package TestClasses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;

public class TestPutRestAPI {
	
	public HttpURLConnection TestGetRestAPI(String Url) throws IOException {
		// 1. connect to server and open connection (get HttpURLConnection object)

		HttpURLConnection connection = RestClientHandler.connectServer(Url, HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		connection.addRequestProperty("Content-Type", "application/json");
		connection.addRequestProperty("Content-Length", "<calculated when request is sent>");
		connection.addRequestProperty("Host", "<calculated when request is sent>");
		connection.addRequestProperty("User-Agent", "PostmanRuntime/7.26.8");
		connection.addRequestProperty("Accept", "*/*");
		connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
		connection.addRequestProperty("Connection", "keep-alive");
		//connection.addRequestProperty("Cookie", CreateToken());
		
		return connection;
		
	}
	public String CreateToken() throws Exception{
		
        // 1. Open Connection --- HttpURLConnection
		String URL = "https://restful-booker.herokuapp.com/auth" ;		
		HttpURLConnection connection = RestClientHandler.connectServer(URL, HTTPMethod.POST,HTTPRequestsContentTypes.JSON);
        // 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCreateToken);
        // 3. Post Request
        RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
        // 4. Reading Response
        String response = RestClientHandler.readResponse(connection);
        JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
        String tokenValue =  jsonObject.get("token").toString();
        String Cookie = "token="+tokenValue;
		return Cookie;
		
	}
	@Test
	public void TestEditInExistBookingId() throws Exception {
		
		// 1. Open Connection --- HttpURLConnection
		String Url = URLs.API + "11";
        HttpURLConnection connection = RestClientHandler.connectServer(Url ,HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
        // 2. Prepare Json Object
        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCopyCorrectObjectJSONFile);
        // 3. Put Request
        connection.setRequestProperty("Cookie", CreateToken());
        RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
        System.out.print(connection.getResponseCode());
		assertTrue("unable to connect to webservice in PUT", connection.getResponseCode() == 200);
        // 4. Reading Response
        String response = RestClientHandler.readResponse(connection);
        JSONObject ResponsePUTJSONObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		JSONObject ResquestPUTJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		System.out.println(ResponsePUTJSONObject);
		System.out.print(ResquestPUTJSONObject);
        //5. validation data resquestJSONObject==response
        assertEquals("The request not equal to the responce ",ResponsePUTJSONObject , ResquestPUTJSONObject );
        //6. validation the the edit is done in the current id 
         HttpURLConnection connectionGet = TestGetRestAPI(Url);
		 assertTrue("unable to connect to webservice in GET", connectionGet.getResponseCode() == 200);
		 String responseGet = RestClientHandler.readResponse(connectionGet);
		 System.out.println(responseGet);
		 
		 JSONObject ResponseGetJSONObject = (JSONObject) JSONUtils.convertStringToJSON(responseGet);
		 JSONObject ResquestJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		 
		 assertEquals("The request data not added to the API data " , ResponseGetJSONObject , ResquestJSONObject );

	}
	
   @Test
	public void TestEditInNotExistBookingId() throws Exception {
		
		// 1. Open Connection --- HttpURLConnection
		String Url = URLs.API + "111";
        HttpURLConnection connection = RestClientHandler.connectServer(Url ,HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
        // 2. Prepare Json Object
        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCopyCorrectObjectJSONFile);
        // 3. Put Request
        connection.setRequestProperty("Cookie", CreateToken());
        RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
        System.out.print(connection.getResponseCode() );
		assertTrue("unable to connect to webservice in PUT", connection.getResponseCode() == 405); //Method Not Allowed
	}
	
	@Test
	public void TestEditWithEmptyJSONObject() throws Exception {
		
		// 1. Open Connection --- HttpURLConnection
		String Url = URLs.API + "10";
		HttpURLConnection connection = RestClientHandler.connectServer(Url ,HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCopyEmptyObjectJSONFile);
		// 3. Put Request
		connection.setRequestProperty("Cookie", CreateToken());
		RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		System.out.print(connection.getResponseCode());
		assertTrue("unable to connect to webservice in PUT", connection.getResponseCode() == 400); //Bad Request

	}
	
	@Test
	public void TestEditWithIncompleteRequiredDataJSONObject() throws Exception {
		
		// 1. Open Connection --- HttpURLConnection
		String Url = URLs.API + "9";
		HttpURLConnection connection = RestClientHandler.connectServer(Url ,HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCopyIncompleteRequiredDataObjectJSONFile);
		// 3. Put Request
		connection.setRequestProperty("Cookie", CreateToken());
		RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		System.out.print(connection.getResponseCode());
		assertTrue("unable to connect to webservice in PUT", connection.getResponseCode() == 400);

	}
			
	
	  @Test
		public void TestEditWithIncompleteOptionalDataJSONObjectIfExist() throws Exception {
			
			// 1. Open Connection --- HttpURLConnection
			String Url = URLs.API + "12";
	        HttpURLConnection connection = RestClientHandler.connectServer(Url ,HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
	        // 2. Prepare Json Object
	        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCopyIncompleteOptionalDataJSONObjectIfExist);
	        // 3. Put Request
	        connection.setRequestProperty("Cookie", CreateToken());
	        RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
			assertTrue("unable to connect to webservice in PUT", connection.getResponseCode() == 200);
	        // 4. Reading Response
	        String response = RestClientHandler.readResponse(connection);
	        JSONObject ResponsePUTJSONObject = (JSONObject) JSONUtils.convertStringToJSON(response);
			JSONObject ResquestPUTJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
			
			System.out.println(ResponsePUTJSONObject);
			System.out.print(ResquestPUTJSONObject);
	        //5. validation data resquestJSONObject==response
	        assertEquals("The request not equal to the responce ",ResponsePUTJSONObject , ResquestPUTJSONObject );
	        //6. validation the the edit is done in the current id 
	         HttpURLConnection connectionGet = TestGetRestAPI(Url);
			 assertTrue("unable to connect to webservice in GET", connectionGet.getResponseCode() == 200);
			 String responseGet = RestClientHandler.readResponse(connectionGet);
			 System.out.println(responseGet);
			 
			 JSONObject ResponseGetJSONObject = (JSONObject) JSONUtils.convertStringToJSON(responseGet);
			 JSONObject ResquestJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
			 
			 assertEquals("The request data not added to the API data " , ResponseGetJSONObject , ResquestJSONObject );

		}
	  
		@Test
		public void TestEditWithIncompleteOptionalDataJSONObjectIfNotExist() throws Exception {
					
					// 1. Open Connection --- HttpURLConnection
					String Url = URLs.API + "6";
			        HttpURLConnection connection = RestClientHandler.connectServer(Url ,HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
			        // 2. Prepare Json Object
			        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCopyIncompleteOptionalDataJSONObjectIfNotExist);
			        // 3. Put Request
			        connection.setRequestProperty("Cookie", CreateToken());
			        RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
					assertTrue("unable to connect to webservice in PUT", connection.getResponseCode() == 200);
			        // 4. Reading Response
			        String response = RestClientHandler.readResponse(connection);
			        JSONObject ResponsePUTJSONObject = (JSONObject) JSONUtils.convertStringToJSON(response);
					JSONObject ResquestPUTJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
					
					System.out.println(ResponsePUTJSONObject);
					System.out.print(ResquestPUTJSONObject);
			        //5. validation data resquestJSONObject==response
			        assertEquals("The request not equal to the responce ",ResponsePUTJSONObject , ResquestPUTJSONObject );
			        //6. validation the the edit is done in the current id 
			         HttpURLConnection connectionGet = TestGetRestAPI(Url);
					 assertTrue("unable to connect to webservice in GET", connectionGet.getResponseCode() == 200);
					 String responseGet = RestClientHandler.readResponse(connectionGet);
					 System.out.println(responseGet);
					 
					 JSONObject ResponseGetJSONObject = (JSONObject) JSONUtils.convertStringToJSON(responseGet);
					 JSONObject ResquestJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
					 
					 assertEquals("The request data not added to the API data " , ResponseGetJSONObject , ResquestJSONObject );

				}

}
