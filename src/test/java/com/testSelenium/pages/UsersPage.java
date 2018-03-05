package com.testSelenium.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.LogStatus;
import com.testSelenium.utilities.CommonUtilities;
import com.testSelenium.utilities.InstanceHolder;

public class UsersPage extends PageBase{


  public UsersPage(InstanceHolder ih) {
    super(ih);              
  }

  final static By Users = By.xpath("//a[contains(@href,'users')]");
  final static By UsersHeaders = By.xpath("//h2[(text()='Users')]");
  final static By NewUser = By.xpath("//a[contains(@href,'users/new')]");
  final static By Filter = By.name("commit");
  final static By ClearFilters = By.xpath("//a[(text()='Users')]");
  final static By TextFieldUserName = By.id("q_username");
  final static By TextFieldEmailID = By.id("q_email");
  final static By TextFieldFromDate= By.id("q_created_at_gteq_datetime");
  final static By TextFieldToDate = By.id("q_created_at_lteq_datetime");
  final static By SelectorUsername = By.xpath("//*[@id=\"q_username_input\"]/select");
  final static By SelectorEmail = By.xpath("//*[@id=\"q_email_input\"]/select");
  final static By NewUserHeaders = By.xpath("//h2[(text()='New User')]");
  final static By DeleteLink = By.xpath("//a[contains(@data-method,'delete')]");
  CommonUtilities utilities = new CommonUtilities();

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

  public void validateUsernameFilter() {
    try{

      boolean userNameFilterflag = false;

      wh.selectValueFromDropDown(SelectorUsername, "Contains");
      wh.sendKeys(TextFieldUserName, "test");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list1 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-username"));
      for(WebElement el: list1)  {
        if(!(el.getText().contains("test"))) {
          userNameFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);

      wh.selectValueFromDropDown(SelectorUsername, "Equals");
      wh.sendKeys(TextFieldUserName, "testuser");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list2 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-username"));
      for(WebElement el: list2)  {
        if(!(el.getText().equalsIgnoreCase("testuser"))) {
          userNameFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);

      wh.selectValueFromDropDown(SelectorUsername, "Starts with");
      wh.sendKeys(TextFieldUserName, "amira");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list3 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-username"));
      for(WebElement el: list3)  {
        if(!(el.getText().startsWith("amira"))) {
          userNameFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);

      wh.selectValueFromDropDown(SelectorUsername, "Ends with");
      wh.sendKeys(TextFieldUserName, "user");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list4 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-username"));
      for(WebElement el: list4)  {
        if(!(el.getText().endsWith("user"))) {
          userNameFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);
      if(!userNameFilterflag) {
        test.log(LogStatus.PASS, "Username Filter Validation - Contains,Equals,Ends with & Start with", "Validation is successful");
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "User page  - 'username' filter validation", "Validation failed");
    }
  }


  public void validateEmailFilter() {
    try{

      boolean emailFilterflag = false;

      wh.selectValueFromDropDown(SelectorEmail, "Contains");
      wh.sendKeys(TextFieldEmailID, "@test");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list1 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-email"));
      for(WebElement el: list1)  {
        if(!(el.getText().contains("@test"))) {
          emailFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);

      wh.selectValueFromDropDown(SelectorEmail, "Equals");
      wh.sendKeys(TextFieldEmailID, "testuser@test.com");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list2 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-email"));
      for(WebElement el: list2)  {
        if(!(el.getText().equalsIgnoreCase("testuser@test.com"))) {
          emailFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);

      wh.selectValueFromDropDown(SelectorEmail, "Starts with");
      wh.sendKeys(TextFieldEmailID, "jacquelyn99");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list3 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-email"));
      for(WebElement el: list3)  {
        if(!(el.getText().startsWith("jacquelyn99"))) {
          emailFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);

      wh.selectValueFromDropDown(SelectorEmail, "Ends with");
      wh.sendKeys(TextFieldEmailID, "@ritchie.info");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      List<WebElement> list4 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-email"));
      for(WebElement el: list4)  {
        if(!(el.getText().endsWith("@ritchie.info"))) {
          emailFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);
      if(!emailFilterflag) {
        test.log(LogStatus.PASS, "Email Filter Validation - Contains,Equals,Ends with & Start with", "Validation is successful");
      }

    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "User page  - 'email' filter validation", "Validation failed");
    }
  }


  public void validateDateFilter() {
    try{
      boolean dateFilterflag = false;
      wh.sendKeys(TextFieldFromDate, "2018-03-01");
      wh.sendKeys(TextFieldToDate, "2018-03-03");
      wh.clickElement(Filter);


      List<WebElement> list = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-created_at"));
      for(WebElement el: list)  {
        if(!(utilities.dateConverter1(el.getText()).after(utilities.dateConverter2("2018-03-01")) && utilities.dateConverter1(el.getText()).before(utilities.dateConverter2("2018-03-03")))) {
          dateFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);
      if(!dateFilterflag) {
        test.log(LogStatus.PASS, "Date Filter Validation  - From & To", "Validation is successful");
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "User page  - date filter validation", "Validation failed");
    }
  }


  public void validateComboFilter() {
    try{
      boolean comboFilterflag = false;
      wh.selectValueFromDropDown(SelectorUsername, "Contains");
      wh.sendKeys(TextFieldUserName, "edwin");
      wh.selectValueFromDropDown(SelectorEmail, "Starts with");
      wh.sendKeys(TextFieldEmailID, "edwin");
      wh.sendKeys(TextFieldFromDate, "2018-03-01");
      wh.sendKeys(TextFieldToDate, "2018-03-03");
      wh.clickElement(Filter);

      Thread.sleep(2000);
      List<WebElement> list1 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-username"));
      for(WebElement el: list1)  {
        if(!(el.getText().contains("edwin"))) {
          comboFilterflag=true;
        }
      }
      List<WebElement> list3 = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-email"));
      for(WebElement el: list3)  {
        if(!(el.getText().startsWith("edwin"))) {
          comboFilterflag=true;
        }
      }

      List<WebElement> list = driver.findElements(By.cssSelector("table[id='index_table_users'] tr td.col.col-created_at"));
      for(WebElement el: list)  {
        if(!(utilities.dateConverter1(el.getText()).after(utilities.dateConverter2("2018-03-01")) && utilities.dateConverter1(el.getText()).before(utilities.dateConverter2("2018-03-03")))) {
          comboFilterflag=true;
        }
      }
      wh.clickElement(ClearFilters);
      if(!comboFilterflag) {
        test.log(LogStatus.PASS, "Combination Filter Validation ", "Validation is successful");
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "User page  - Combination filter validation", "User Page failed to load successfully");
    }
  }

  public void clickNewUser() {
    try {
      wh.clickElement(NewUser);
      test.log(LogStatus.PASS, "Click New User Button", "New User button clicked successfully"); 
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "Click New User Button", "Failed to click no New User Button");
    }
  }

  public void waitForNewUserPageToLoad() {
    try{
      if(driver.getTitle().equalsIgnoreCase("New User | Active Admin Depot") && wh.isElementPresent(NewUserHeaders)){
        test.log(LogStatus.PASS, "New User Page Validation", "New User page loaded successfully"); 
      }
      else {
        throw new Exception();
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "New User Page Validation", "New User Page failed to load successfully");
    }


  }

  public void deleteTestData() {
    try {

      wh.selectValueFromDropDown(SelectorUsername, "Contains");
      wh.sendKeys(TextFieldUserName, "usertest");
      wh.clickElement(Filter);
      Thread.sleep(2000);
      wh.clickElement(DeleteLink);
      wh.handleAlert();
      test.log(LogStatus.PASS, "Test Data deletion", "Test Data deletion successful");
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "Test Data deletion", "Test Data deletion failed");
    }
  }
}
