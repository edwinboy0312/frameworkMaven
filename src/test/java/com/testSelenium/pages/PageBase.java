package com.testSelenium.pages;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.testSelenium.utilities.InstanceHolder;



public class PageBase {

  public WebDriver driver;
  public ExtentReports report;
  public ExtentTest test;
  public com.testSelenium.utilities.WebDriverHelper wh;
  public HashMap<String,String> testdata; 

  public PageBase() {
  }


  public PageBase(InstanceHolder ih) {

    driver = ih.driver;
    wh = ih.wh;
    test = ih.test;
    report = ih.report;
    testdata = ih.testdata;
  }

}
