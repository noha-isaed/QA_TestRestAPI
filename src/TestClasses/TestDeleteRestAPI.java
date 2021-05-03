package TestClasses;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;

public class TestDeleteRestAPI {

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
	
		
		//check delte id exist in the data 
		@Test
		public void TestDeleteExistIDBooking() throws Exception {
			// 1. connect to server and open connection (get HttpURLConnection object)
			String Url = URLs.API + "17";
			System.out.println(Url);
			HttpURLConnection connection = RestClientHandler.connectServer(Url, HTTPMethod.DELETE,HTTPRequestsContentTypes.JSON);
			connection.addRequestProperty("Cookie", CreateToken());
			// 2. Send DELETE request
			RestClientHandler.sendDelete(connection,"", HTTPRequestsContentTypes.JSON);
			// 3. validate if the connection is successfully openned
			System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
			assertTrue("unable to connect to webservice DELETE", connection.getResponseCode() == 201);
			// 4. reading response using input stream
			String response = RestClientHandler.readResponse(connection);
			System.out.println(response);
			assertTrue("Data is not deletd", response.equals("Created"));
			// 5. validation by get the id another time 
			HttpURLConnection connectionGET = RestClientHandler.connectServer(Url, HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
			assertTrue("unable to connect to webservice GET", connectionGET.getResponseCode() == 404);

		}
		
		//check delete id not exist in the data 
	    @Test 
		public void TestDeleteNotExistIDBooking() throws Exception  {
			// 1. connect to server and open connection (get HttpURLConnection object)
			String Url = URLs.API + "177";
			System.out.println(Url);
			HttpURLConnection connection = RestClientHandler.connectServer(Url, HTTPMethod.DELETE,HTTPRequestsContentTypes.JSON);
			connection.addRequestProperty("Cookie", CreateToken());
			// 2.  Send DELETE request
			RestClientHandler.sendDelete(connection,"", HTTPRequestsContentTypes.JSON);
			// 3. validate if the connection is successfully openned
			System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
			assertTrue("unable to connect to webservice DELETE", connection.getResponseCode() == 405);


		}
		

	}


