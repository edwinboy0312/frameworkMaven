package com.testSelenium.utilities;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class InstanceHolder {

    public WebDriver driver;
    public ExtentReports report;
    public ExtentTest test;
    public WebDriverHelper wh;
    public HashMap<String,String> testdata; 
    
    public InstanceHolder(WebDriver driver,ExtentReports report, ExtentTest test,
                WebDriverHelper wh, HashMap<String,String> testdata) {
        
        this.report = report;
        this.driver = driver;
        this.wh = wh;
        this.test =test;
        this.testdata = testdata;
    }
    
  
}
