package com.testSelenium.pages;

import org.openqa.selenium.By;
import com.relevantcodes.extentreports.LogStatus;
import com.testSelenium.utilities.InstanceHolder;

public class HomePage extends PageBase{

  public HomePage(InstanceHolder ih) {
    super(ih);              
  }

  final static By Users = By.xpath("//a[contains(@href,'users')]");
  final static By Dashboard = By.xpath("//h2[(text()='Dashboard')]");
  final static By UsersHeaders = By.xpath("//h2[(text()='Users')]");
  final static By NewUser = By.xpath("//a[contains(@href,'users/new')]");

  public void open() {
    try{
      driver.manage().deleteAllCookies();
      driver.get(testdata.get("EnvUrl").toString().trim());
      if(driver.getTitle().equalsIgnoreCase("Dashboard | Active Admin Depot") && wh.isElementPresent(Dashboard)){
        test.log(LogStatus.PASS, "HomePage Validation", "HomePage loaded successfully"); 
      }
      else {
        throw new Exception();
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "HomePage Validation", "HomePage failed to load successfully");
    }
  }
  
  public void clickUsers() {
    try {
      wh.clickElement(Users);
      waitForUserPageToLoad();
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "Clicking Users Link", "Exception occured in the function - clickUsers");
    }
  }
  
  public void waitForUserPageToLoad() {
    try{
      if(driver.getTitle().equalsIgnoreCase("Users | Active Admin Depot") && wh.isElementPresent(NewUser)){
        test.log(LogStatus.PASS, "User Page Validation", "User page loaded successfully"); 
      }
      else {
        throw new Exception();
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "User Page Validation", "User Page failed to load successfully");
    }
  }
  
  
}
