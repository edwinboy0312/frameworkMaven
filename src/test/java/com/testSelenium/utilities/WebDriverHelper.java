package com.testSelenium.utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class WebDriverHelper {

  public WebDriver driver;
  public ExtentReports report;
  public ExtentTest test;

  public WebDriverHelper(WebDriver driver, ExtentReports report,
      ExtentTest test) {

    this.driver = driver;
    this.report = report;
    this.test = test;
  }

  public boolean isElementPresent(By elementBy) throws Exception {
    return isElementPresent(elementBy,5);
  }


  public boolean isElementPresent(By elementBy, int waitForSeconds)throws Exception {     

    boolean elementPresent = true;
    int count = 0;
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    while(elementPresent){
      try{
        if (driver.findElement(elementBy).isDisplayed()){
          break;
        }else{
          if(count==waitForSeconds){
            elementPresent = false;
            break;}
          Thread.sleep(1000);
          count++;}
      }catch(Exception ex){
        if(count==waitForSeconds){
          elementPresent = false;
          break;}
        Thread.sleep(1000);
        count++; }}

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    return elementPresent;     
  }

  public void clickElement(By elementBy) throws Exception {

    try {
      if (isElementPresent(elementBy, 2)) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
          driver.findElement(elementBy).click();
        }
        catch (Exception e) {
          System.out.println("Generic Exception occured in clickElement");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      }
      else
        throw new Exception ();}

    catch(Exception e){
      System.out.println("Generic Exception occured in clickElement");
    }
  }

  public void selectValueFromDropDown(By elementBy, String label) {
    new Select(driver.findElement(elementBy)).selectByVisibleText(label);
  }

  public void sendKeys(By elementBy, String typeValue) throws Exception {
    try {
      if(isElementPresent(elementBy, 1)) {
        driver.findElement(elementBy).clear();
        driver.findElement(elementBy).sendKeys(typeValue);
      }           
    } catch(Exception ex) {
      System.out.println("Generic Exception occured in sendKeys");
    }
  }
  
  
  public void handleAlert() {
    try {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    } catch (Exception ex) {
      System.out.println("Generic Exception occured in handleAlert");
    }
}
}
