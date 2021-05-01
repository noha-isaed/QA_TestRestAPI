package requestHandling;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;

public class RestClientHandler {

	
	 /**
     *  
     * @param Url: specifies the URL we are sending the request to.
     * @param method: specifies the HTTP request method we are using to send the request
     * @return: returns the connection we create to send the request to be used to send any data or get the response later.
     * @throws IOException: for connection lost.
     * Description: the function is used to send the whole request by the method specified in the params
     */
    public static HttpURLConnection connectServer(String Url, HTTPMethod method, HTTPRequestsContentTypes contentType) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
//        connection.setRequestMethod(method.toString());
//        connection.setRequestProperty("Content-Type", contentType.toString() );
//
//        connection.setAllowUserInteraction(true);
//        connection.setDoOutput(true);
//        
        
        return connection;
    }
	
	 /**
     *  .
     * @param con: specifies the connection that was used to send the request , which is going to be used toget the response.
     * @return returns the response as String
     * @throws IOException: for connection lost.
     * Description: the function is used to read the response of the opened connection.
     */
    public static String readResponse(HttpURLConnection connection) throws IOException {

        InputStream in = connection.getInputStream();
        InputStreamReader isReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(isReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while((str = bufferedReader.readLine())!= null){
            stringBuffer.append(str);
        }
        connection.disconnect();
        bufferedReader.close();
        ///System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }
    
   
    
    /**
     *  .
     * @param con: specifies the connection that was used to send the request , which is going to be used to send data.
     * @param data: specifies the data we want to send through the HTTP connection.
     * @throws IOException for connection not found.
     * Description: the function is used to send the data on the previous opened connection.
     */
    public static void sendRequest(HttpURLConnection connection,String data) throws IOException {
    	connection.setDoInput(true);
        OutputStream outStream= connection.getOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(outStream);
        out.write(data.getBytes());
        out.close();
    }
    
    
    public static void sendPost(HttpURLConnection conn, String json, HTTPRequestsContentTypes contentType) throws Exception {
        try {
          System.out.println("POST Operation Started..");
          conn.setDoInput(true);
          conn.setDoOutput(true);
          conn.setDefaultUseCaches(false);
          conn.setUseCaches(false);
          conn.setRequestMethod("POST");
          conn.setRequestProperty("Content-Type", contentType.toString());
          conn.setRequestProperty("Accept", "*/*");
          conn.addRequestProperty("User-Agent", "PostmanRuntime/7.26.8");
          conn.setRequestProperty("Content-Length", "<calculated when request is sent>");
          conn.setRequestProperty("Host", "<calculated when request is sent>");
          conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");   
          conn.setRequestProperty("Connection", "keep-alive");   
         
          conn.connect();

          if (null != json && !json.isEmpty()) {
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(json);
            osw.flush();
            osw.close();
          } 

          System.out.println("Response code from server while doing POST Operation : " + conn.getResponseCode());
          if (conn.getResponseCode() == 200) {
            System.out.println("POST Operation successfully completed..");
          }
        } catch (Exception ex) {
          System.out.println("Got Exception while POST operation : " + ex.getMessage());
          throw new Exception(ex.getMessage(), ex);
        } 
      }

      
    public static void sendGet(HttpURLConnection conn, String json, HTTPRequestsContentTypes contentType) throws Exception {
        try {
          System.out.println("GET Operation Started..");
          
          conn.addRequestProperty("Cookie", "__cfduid=d91d912a21fb99821fed85f7eb8267d821619292329; _ga=GA1.2.1046101046.1619292332; _gid=GA1.2.2026596592.1619292332; __stripe_mid=94af3bd8-6994-45fb-bfef-6fe3a98dc940753751");
          
          conn.setDoInput(true);
          conn.setDoOutput(true);
          conn.setDefaultUseCaches(false);
          conn.setUseCaches(false);
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Content-Type", contentType.toString());
          conn.setRequestProperty("Accept", "application/json");
          
          
          //conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

          conn.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
          conn.addRequestProperty("User-Agent", 
        		  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
          
          conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
          conn.connect();
          
          System.out.println("Response code from server while doing GET Operation : " + conn.getResponseCode());
          if (conn.getResponseCode() == 200) {
            System.out.println("GET Operation successfully completed..");
          }
        } catch (Exception ex) {
          System.out.println("Got Exception while GET operation : " + ex.getMessage());
          throw new Exception(ex.getMessage(), ex);
        } 
      }
      
    public static void sendPut(HttpURLConnection conn, String json, HTTPRequestsContentTypes contentType) throws Exception {
        try {
          System.out.println("PUT Operation Started..");
          conn.setDoInput(true);
          conn.setDoOutput(true);
          conn.setDefaultUseCaches(false);
          conn.setUseCaches(false);
          conn.setRequestMethod("PUT");
          conn.setRequestProperty("Content-Type", contentType.toString());
          conn.setRequestProperty("Accept", " application/json");
          conn.addRequestProperty("User-Agent", 
        		  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
          conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
          conn.connect();
          
          if (null != json && !json.isEmpty()) {
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(json);
            osw.flush();
            osw.close();
          } 
          //System.out.println("Response code from server while doing PUT Operation : " + conn.getResponseCode());
          if (conn.getResponseCode() == 200) {
            System.out.println("PUT Operation successfully completed..");
          }
        } catch (Exception ex) {
          System.out.println("Got Exception while PUT operation : " + ex.getMessage());
          throw new Exception(ex.getMessage(), ex);
        } 
      }
      
    public static void sendDelete(HttpURLConnection conn, String json, HTTPRequestsContentTypes contentType) throws Exception {
        try {
          System.out.println("DELETE Operation Started..");
          conn.setDoInput(true);
          conn.setDoOutput(true);
          conn.setDefaultUseCaches(false);
          conn.setUseCaches(false);
          conn.setRequestMethod("DELETE");
          conn.setRequestProperty("Content-Type", contentType.toString());
          conn.setRequestProperty("Accept", "application/json");
          conn.addRequestProperty("User-Agent", 
        		  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
          conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
          conn.connect();
          
          if (null != json && !json.isEmpty()) {
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(json);
            osw.flush();
            osw.close();
          } 
          
          System.out.println("Response code from server while doing DELETE Operation : " + conn.getResponseCode());
          if (conn.getResponseCode() == 201) {
            System.out.println("DELETE Operation successfully completed..");
          }
        }
        catch (Exception ex) {
          System.out.println("Got Exception while DELETE operation : " + ex.getMessage());
          throw new Exception(ex.getMessage(), ex);
        } 
      }
}
