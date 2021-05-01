package TestClasses;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import Links.URLs;
import org.junit.Test;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;
import Links.FilesPaths;
import Utils.JSONUtils;
import org.json.simple.JSONObject;


//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.json.*;  
//import org.json.simple.JSONStreamAware;
//import org.json.simple.JSONValue;
//import org.json.simple.parser.ParseException;
//import org.junit.Ignore;
//import java.util.Iterator;
//import org.json.simple.JSONArray;
//import requestHandling.HandleRequestReponse;

public class TestPostRestAPI {
	
	public HttpURLConnection TestGetRestAPI(String bookingId) throws IOException {
		// 1. connect to server and open connection (get HttpURLConnection object)

		HttpURLConnection connection = RestClientHandler.connectServer(URLs.API + bookingId, HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
		connection.addRequestProperty("Content-Type", "application/json");
		connection.addRequestProperty("Content-Length", "<calculated when request is sent>");
		connection.addRequestProperty("Host", "<calculated when request is sent>");
		connection.addRequestProperty("User-Agent", "PostmanRuntime/7.26.8");
		connection.addRequestProperty("Accept", "*/*");
		connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
		connection.addRequestProperty("Connection", "keep-alive");
		
		return connection;
		
	}

	//check post rest api by add new jsonObject not exist in the data 
	@Test
    public void TestPostCorrectJsonObject() throws Exception {
        // 1. Open Connection --- HttpURLConnection
        HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST,HTTPRequestsContentTypes.JSON);
        // 2. Prepare Json Object
        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewCorrectObjectJSONFile);
        // 3. Post Request
        RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
        // 4. Reading Response
		assertTrue("unable to connect to webservice in POST", connection.getResponseCode() == 200);
        String response = RestClientHandler.readResponse(connection);
        System.out.println(response);
        // 5. convert String to JSON
        JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
        String booking =  jsonObject.get("booking").toString();
        System.out.println(booking);
        //6. validation data resquestJSONObject==response
        assertEquals("The request not equal to the responce ",resquestJSONObject , booking );
        //7. validation the resquestJSONObject added to the data of API 
        
         String bookingID =  jsonObject.get("bookingid").toString();
         HttpURLConnection connectionGet = TestGetRestAPI(bookingID);
		 assertTrue("unable to connect to webservice in GET", connectionGet.getResponseCode() == 200);
		 String responseGet = RestClientHandler.readResponse(connectionGet);
		 System.out.println(responseGet);
		 
		 JSONObject ResponseGetJSONObject = (JSONObject) JSONUtils.convertStringToJSON(responseGet);
		 JSONObject ResquestJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		 
		 assertEquals("The request data not added to the API data " , ResponseGetJSONObject , ResquestJSONObject );
   
    }

	//check post rest api by add new jsonObject exist in the data 
	@Test
	public void TestPostExisttJSONObject() throws Exception {
		// 1. Open Connection --- HttpURLConnection
        HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST,HTTPRequestsContentTypes.JSON);
        // 2. Prepare Json Object
        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewExistObjectJSONFile);
        // 3. Post Request
        RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
        // 4. Reading Response
		assertTrue("unable to connect to webservice in POST", connection.getResponseCode() == 200);
        String response = RestClientHandler.readResponse(connection);
        System.out.println(response);
        // 5. convert String to JSON
        JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
        String booking =  jsonObject.get("booking").toString();
        System.out.println(booking);
        //6. validation data resquestJSONObject==response
        assertEquals("The request not equal to the responce ",resquestJSONObject , booking );
        //7. validation the resquestJSONObject added to the data of API 
        
         String bookingID =  jsonObject.get("bookingid").toString();
         HttpURLConnection connectionGet = TestGetRestAPI(bookingID);
		 assertTrue("unable to connect to webservice in GET", connectionGet.getResponseCode() == 200);
		 String responseGet = RestClientHandler.readResponse(connectionGet);
		 System.out.println(responseGet);
		 
		 JSONObject ResponseGetJSONObject = (JSONObject) JSONUtils.convertStringToJSON(responseGet);
		 JSONObject ResquestJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		 
		 assertEquals("The request data not added to the API data " , ResponseGetJSONObject , ResquestJSONObject );
	}
	
	//check post rest api by add new jsonObject exist in the data 
	@Test
	public void TestPostIncompleteOptionalDataJSONObject() throws Exception {
		// 1. Open Connection --- HttpURLConnection
        HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST,HTTPRequestsContentTypes.JSON);
        // 2. Prepare Json Object
        String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewIncompleteOptionalDataObjectJSONFile);
        // 3. Post Request
        RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
        // 4. Reading Response
		assertTrue("unable to connect to webservice in POST", connection.getResponseCode() == 200);
        String response = RestClientHandler.readResponse(connection);
        System.out.println(response);
        // 5. convert String to JSON
        JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
        String booking =  jsonObject.get("booking").toString();
        System.out.println(booking);
        //6. validation data resquestJSONObject==response
        assertEquals("The request not equal to the responce ",resquestJSONObject , booking );
        //7. validation the resquestJSONObject added to the data of API 
        
         String bookingID =  jsonObject.get("bookingid").toString();
         HttpURLConnection connectionGet = TestGetRestAPI(bookingID);
		 assertTrue("unable to connect to webservice in GET", connectionGet.getResponseCode() == 200);
		 String responseGet = RestClientHandler.readResponse(connectionGet);
		 System.out.println(responseGet);
		 
		 JSONObject ResponseGetJSONObject = (JSONObject) JSONUtils.convertStringToJSON(responseGet);
		 JSONObject ResquestJSONObject = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		 
		 assertEquals("The request data not added to the API data " , ResponseGetJSONObject , ResquestJSONObject );
	}
	
	//check post rest api by add incomplete Json object
	@Test 
	public void TestPostIncompleteRequiredDataJSONObject() throws Exception {
			
		 // 1. Open Connection --- HttpURLConnection
		 HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST, HTTPRequestsContentTypes.JSON);
	        // 2. Prepare Json Object
		 String resquestJSONObject= JSONUtils.readJSONObjectFromFile(FilesPaths.NewIncompleteRequiredDataObjectJSONFile);
		 System.out.print(resquestJSONObject);
	     // 3. Post Request
		 RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		 // 4. validation connection response code 
		 assertTrue("unable to connect to webservice", connection.getResponseCode() == 500);
		}
		
	   //check post rest api by add correct array of object
		@Test
		public void TestPostCorrectJSONArray() throws Exception {
				
			 // 1. Open Connection --- HttpURLConnection
			 HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST, HTTPRequestsContentTypes.JSON);
		     // 2. Prepare Json Array
			 String resquestJSONArray = JSONUtils.readJSONArrayFromFile(FilesPaths.NewCorrectArrayJSONFile);
			 System.out.print(resquestJSONArray);
		     // 3. Post Request
			 RestClientHandler.sendPost(connection, resquestJSONArray, HTTPRequestsContentTypes.JSON);
			 // 4. validation connection response code 
			 assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
			 
		}
		
		//check post rest api by add empty object
		@Test
		public void TestPostEmptyJSONObject() throws Exception {
			
			 // 1. Open Connection --- HttpURLConnection
			 HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST, HTTPRequestsContentTypes.JSON);
		        // 2. Prepare Json Object
			 String resquestJSONOblect = JSONUtils.readJSONObjectFromFile(FilesPaths.NewEmptyObjectJSONFile);
			 System.out.print(resquestJSONOblect);
		     // 3. Post Request
			 RestClientHandler.sendPost(connection, resquestJSONOblect, HTTPRequestsContentTypes.JSON);
			 // 4. validation connection response code 
			 assertTrue("unable to connect to webservice", connection.getResponseCode() == 500);

		}
			
		//check post rest api by add object with new attribute 
		@Test
		public void TestPostSONObjectWithNewAttribute() throws Exception {	
			 // 1. Open Connection --- HttpURLConnection
			 HttpURLConnection connection = RestClientHandler.connectServer(URLs.API, HTTPMethod.POST, HTTPRequestsContentTypes.JSON);
		     // 2. Prepare Json Object	
			 String resquestJSONOblect = JSONUtils.readJSONObjectFromFile(FilesPaths.NewObjectJSONWithNewAttributeFile);
			 System.out.print(resquestJSONOblect);
		     // 3. Post Request
			 RestClientHandler.sendPost(connection, resquestJSONOblect, HTTPRequestsContentTypes.JSON);
			 // 4. validation connection response code 
			 assertTrue("unable to connect to webservice", connection.getResponseCode() == 500);

		}
		
}
