package com.testSelenium;

import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.testSelenium.pages.PageBase;
import com.testSelenium.utilities.ApiUtilities;
import com.testSelenium.utilities.CommonUtilities;
import com.testSelenium.utilities.InstanceHolder;
import com.testSelenium.utilities.WebDriverHelper;


public class ApiTest extends PageBase{

  WebDriver driver;
  ExtentReports report;
  ExtentTest test;
  WebDriverHelper wh;
  ApiUtilities apiUtilities;
  CommonUtilities excelUtilities = new CommonUtilities(); 
  InstanceHolder ih;
  public HashMap<String, String> testdata = new HashMap<String,String>();
  public HashMap<String,String> map = new HashMap<String,String>();
  String payload;
  JsonObject jsonResponse = new JsonObject();


  @BeforeClass
  void beforeClass(){
    report = new ExtentReports("./reports/ExecutionReport.html", false, DisplayOrder.OLDEST_FIRST);
    testdata = excelUtilities.loadData();
    driver = new HtmlUnitDriver();
    map.put("Content-Type" , "application/json");
  }

  @Test
  public void testGetServiceForPost() throws Exception {
    
    test = report.startTest("API - Get Method for Posts");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);
    
    jsonResponse = apiUtilities.getEntity(testdata.get("BaseUrl").toString().trim()+"/posts/2", map);
    apiUtilities.validateGetService("id","2",jsonResponse);
  }

  @Test
  public void testPostServiceForPost() throws Exception {

    test = report.startTest("API - Post Method for Posts");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);


    payload = apiUtilities.createPostRequestForPosts();
    jsonResponse = apiUtilities.postEntity(testdata.get("BaseUrl").toString().trim()+"/posts",payload, map);
    apiUtilities.validatePostServicePosts(jsonResponse);
  }

  @Test
  public void testPutServiceForPost() throws Exception {

    test = report.startTest("API - Put Method for Posts");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);

    payload = apiUtilities.createPutRequestForPosts();
    jsonResponse = apiUtilities.putEntity(testdata.get("BaseUrl").toString().trim()+"/posts/456459",payload, map);
    apiUtilities.validatePutServicePosts(jsonResponse);
  }

  @Test
  public void testGetServiceForComments() throws Exception {

    test = report.startTest("API - Get Method for Comments");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);

    jsonResponse = apiUtilities.getEntity(testdata.get("BaseUrl").toString().trim()+"/comments/2", map);
    apiUtilities.validateGetService("id","2",jsonResponse);
  }

  @Test
  public void testPostServiceForComments() throws Exception {

    test = report.startTest("API - Post Method for Comments");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);


    payload = apiUtilities.createPostRequestForComments();
    jsonResponse = apiUtilities.postEntity(testdata.get("BaseUrl").toString().trim()+"/comments",payload, map);
    apiUtilities.validatePostServiceComments(jsonResponse);
  }

  @Test
  public void testPutServiceForComment() throws Exception {

    test = report.startTest("API - Put Method for Comments");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);


    payload = apiUtilities.createPutRequestForComments();
    jsonResponse = apiUtilities.putEntity(testdata.get("BaseUrl").toString().trim()+"/comments/12",payload, map);
    apiUtilities.validatePutServiceComments(jsonResponse);
  }

  @Test
  public void testGetServiceForUsers() throws Exception {

    test = report.startTest("API - Get Method for Users");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);


    jsonResponse = apiUtilities.getEntity(testdata.get("BaseUrl").toString().trim()+"/users/10", map);
    apiUtilities.validateGetService("id","10",jsonResponse);
  }

  @Test
  public void testPostServiceForUsers() throws Exception {

    test = report.startTest("API - Post Method for User");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);

    payload = apiUtilities.createPostRequestForUsers();
    jsonResponse = apiUtilities.postEntity(testdata.get("BaseUrl").toString().trim()+"/users",payload, map);
    apiUtilities.validatePostServiceUsers(jsonResponse);
  }

  @Test
  public void testPutServiceForUsers() throws Exception {

    test = report.startTest("API - Put Method for Users");
    ih = new InstanceHolder(driver, report, test, wh, testdata);
    apiUtilities = new ApiUtilities(ih);


    payload = apiUtilities.createPutRequestForUsers();
    jsonResponse = apiUtilities.putEntity(testdata.get("BaseUrl").toString().trim()+"/users/1811",payload, map);
    apiUtilities.validatePutServiceUsers(jsonResponse);
  }

  @AfterMethod
  public void afterTest() {
    report.endTest(test);
  }

  @AfterSuite
  public void afterSuite() throws Exception{
    report.close();
    driver.quit();
    Desktop.getDesktop().open(new File("./reports/ExecutionReport.html"));
  }



}
