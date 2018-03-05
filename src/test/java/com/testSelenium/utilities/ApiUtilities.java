package com.testSelenium.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.LogStatus;
import com.testSelenium.pages.PageBase;


public class ApiUtilities extends PageBase {  

  JsonObject jSONObject = new JsonObject(); 
  int responseCode,randomID,randomPostID,randomSuiteNbr;
  Random rand = new Random();

  public ApiUtilities(InstanceHolder ih) {
    super(ih);
  }

  public JsonObject postEntity(String url,String sXML,HashMap<String,String> mapSetReqProp) throws Exception {

    try {       
      URL obj = new URL(url);
      test.log(LogStatus.PASS, "Get Url ",url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      con.setDoOutput(true);
      con.setDoInput(true);

      if(!mapSetReqProp.isEmpty()){
        for (Entry<String, String> entry : mapSetReqProp.entrySet()) {
          con.setRequestProperty(entry.getKey(),entry.getValue());                   
        }   
      }                           

      con.setRequestMethod("POST");           
      OutputStream out = con.getOutputStream();
      if(!sXML.isEmpty())
        out.write(sXML.getBytes());
      out.flush();
      out.close();

      responseCode = con.getResponseCode();
      BufferedReader in;
      if (responseCode != 200 && responseCode != 201) {
        in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        return null;
      } else {
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      String strResponse = response.toString();
      test.log(LogStatus.PASS, "Response", strResponse);
      jSONObject = new JsonParser().parse(strResponse).getAsJsonObject();
      return jSONObject;

    } catch (Exception e) {

      System.out.println("Error in POST method" + e.getMessage());
      return null;
    }
  }

  public  JsonObject putEntity(String url,String sXML,HashMap<String,String> mapSetReqProp) throws Exception {

    try {       
      URL obj = new URL(url);
      test.log(LogStatus.PASS, "Get Url ",url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      con.setDoOutput(true);
      con.setDoInput(true);

      if(!mapSetReqProp.isEmpty()){
        for (Entry<String, String> entry : mapSetReqProp.entrySet()) {
          con.setRequestProperty(entry.getKey(),entry.getValue());                   
        }   
      }                           

      con.setRequestMethod("PUT");           
      OutputStream out = con.getOutputStream();
      if(!sXML.isEmpty())
        out.write(sXML.getBytes());
      out.flush();
      out.close();

      responseCode = con.getResponseCode();
      BufferedReader in;
      if (responseCode != 200) {
        in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        return null;
      } else {
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      String strResponse = response.toString();
      test.log(LogStatus.PASS, "Response", strResponse);
      jSONObject = new JsonParser().parse(strResponse).getAsJsonObject();
      return jSONObject;

    } catch (Exception e) {

      System.out.println("Error in Put method" + e.getMessage());
      return null;
    }
  }


  public JsonObject getEntity(String url,HashMap<String,String> mapSetReqProp) {

    try {   

      URL obj = new URL(url);
      test.log(LogStatus.PASS, "Get Url ",url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      if(!mapSetReqProp.isEmpty()){
        for (Entry<String, String> entry : mapSetReqProp.entrySet()) {
          con.setRequestProperty(entry.getKey(),entry.getValue());                   
        }   
      }                           

      con.setRequestMethod("GET");        
      responseCode = con.getResponseCode();
      BufferedReader in;

      if (responseCode != 200) {
        in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        return null;
      } else {

        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      }

      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      String sResponse = response.toString();
      test.log(LogStatus.PASS, "Response", sResponse);
      jSONObject = new JsonParser().parse(sResponse).getAsJsonObject();
      return jSONObject;

    } catch (Exception e) {
      System.out.println("Error in GET method" + e.getMessage());
      return null;
    }
  }

  public void validateGetService(String jsonTag, String valuetobeValidated, JsonObject jsonResponse) {
    try {
      if(jsonResponse.get(jsonTag).toString().equalsIgnoreCase(valuetobeValidated) && responseCode == 200 ) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }

  public void validatePostServicePosts(JsonObject jsonResponse) {
    try {
      if(jsonResponse.get("id").getAsInt() == randomID && responseCode == 201) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }

  public void validatePutServicePosts(JsonObject jsonResponse) {
    try {
      if(jsonResponse.get("id").getAsInt() == 456459 && responseCode == 200 && jsonResponse.get("postId").getAsInt() == randomPostID ) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }

  public void validatePostServiceComments(JsonObject jsonResponse) {
    try {
      if(jsonResponse.get("id").getAsInt() == randomID && responseCode == 201) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }

  public void validatePutServiceComments(JsonObject jsonResponse) {
    try {
      if(jsonResponse.get("id").getAsInt() == 12 && responseCode == 200 && jsonResponse.get("postId").getAsInt() == randomPostID ) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }

  public void validatePostServiceUsers(JsonObject jsonResponse) {
    try {
      if(jsonResponse.get("id").getAsInt() == randomID && responseCode == 201 && jsonResponse.get("name").getAsString().equalsIgnoreCase("Automation Tester")) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }

  public void validatePutServiceUsers(JsonObject jsonResponse) {
    try {
      JsonObject subJson = jsonResponse.getAsJsonObject("address"); 
      if(jsonResponse.get("id").getAsInt() == 1811 && responseCode == 200 && subJson.get("suite").getAsInt() == randomSuiteNbr) {
        test.log(LogStatus.PASS, "Validation", "Passed");
      }else
        test.log(LogStatus.FAIL, "Validation", "Failed");    
    }
    catch(Exception e){
      test.log(LogStatus.FAIL, "Validation", "Failed with exception");
    }
  }




  public String createPostRequestForPosts() {
    randomID = rand.nextInt(90000) + 10000;
    String payload  = "{\n \"postId\": 456456,\n \"id\": "+randomID+",\n \"name\": \"id labore ex et quam laborum\",\n \"email\": \"Eliseo@gardner.biz\",\n \"body\": \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"\n}";
    return payload;
  }

  public String createPutRequestForPosts() {
    randomPostID = rand.nextInt(90000) + 10000;
    String payload  ="{\n \"postId\": "+randomPostID+",\n \"id\": 456459,\n \"name\": \"test put method\",\n \"email\": \"Eliseo@gardner.biz\",\n \"body\": \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"\n}";
    return payload;
  }

  public String createPostRequestForComments() {
    randomID = rand.nextInt(90000) + 10000;
    String payload  ="{\n \"postId\": 12,\n \"id\": "+randomID+",\n \"name\": \"test post method\",\n \"email\": \"Eliseo@gardner.biz\",\n \"body\": \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"\n}";
    return payload;
  }

  public String createPutRequestForComments() {
    randomPostID = rand.nextInt(90000) + 10000;
    String payload  ="{\n \"postId\": "+randomPostID+",\n \"id\": 12,\n \"name\": \"test put method\",\n \"email\": \"Eliseo@gardner.biz\",\n \"body\": \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"\n}";
    return payload;
  }

  public String createPostRequestForUsers() {
    randomID = rand.nextInt(900) + 1000;
    String payload  ="{\n\"id\": "+randomID+",\n\"name\": \"Automation Tester\",\n\"username\": \"Moriah.Stanton\",\n\"email\": \"Rey.Padberg@karina.biz\",\n\"address\": {\n\"street\": \"Kattie Turnpike\",\n\"suite\": \"Suite 198\",\n\"city\": \"Lebsackbury\",\n\"zipcode\": \"31428-2261\",\n\"geo\": {\n\"lat\": \"-38.2386\",\n\"lng\": \"57.2232\"\n}\n},\n\"phone\": \"024-648-3804\",\n\"website\": \"ambrose.net\",\n\"company\": {\n\"name\": \"Hoeger LLC\",\n\"catchPhrase\": \"Centralized empowering task-force\",\n\"bs\": \"target end-to-end models\"\n}\n}";
    return payload;
  }

  public String createPutRequestForUsers() {
    randomSuiteNbr = rand.nextInt(90) + 100;
    String payload  ="{\n\"id\": 1811,\n\"name\": \"Automation Tester\",\n\"username\": \"Moriah.Stanton\",\n\"email\": \"Rey.Padberg@karina.biz\",\n\"address\": {\n\"street\": \"Kattie Turnpike\",\n\"suite\": \""+randomSuiteNbr+"\",\n\"city\": \"Lebsackbury\",\n\"zipcode\": \"31428-2262\",\n\"geo\": {\n\"lat\": \"-38.2386\",\n\"lng\": \"57.2232\" }},\n\"phone\": \"4047356789\",\n\"website\": \"ambrose.net\",\n\"company\": {\n\"name\": \"Hoeger LLC\",\n\"catchPhrase\": \"Centralized empowering task-force\",\n\"bs\": \"target end-to-end models\"}}";
    return payload;
  }







}
