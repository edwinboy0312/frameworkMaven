package com.testSelenium.pages;

import org.openqa.selenium.By;
import com.relevantcodes.extentreports.LogStatus;
import com.testSelenium.utilities.InstanceHolder;

public class NewUserPage extends PageBase{


  public NewUserPage(InstanceHolder ih) {
    super(ih);              
  }

  final static By Users = By.xpath("//a[contains(@href,'users')]");
  final static By NewUserHeaders = By.xpath("//h2[(text()='New User')]");
  final static By CreateUser = By.name("commit");
  final static By Cancel = By.xpath("//a[(text()='Cancel')]");
  final static By TextFieldUserName = By.id("user_username");
  final static By TextFieldPassword = By.id("user_password");
  final static By TextFieldEmail = By.id("user_email");
  final static By NewUser = By.xpath("//a[contains(@href,'users/new')]");
  final static By UserNameFieldValidation = By.xpath("//p[(text()='has already been taken')]");
  final static By PasswordFieldValidation = By.xpath("//*[@id=\"user_password_input\"]/p");
  final static By EmailFieldValidation = By.xpath("//p[(text()='is invalid')]");
  final static By UserTestValidation = By.xpath("//div[(text()='User was successfully created.')]");

  public void waitForNewUserPageToLoad() {
    try{
      if(driver.getTitle().equalsIgnoreCase("Users | Active Admin Depot") && wh.isElementPresent(NewUserHeaders)){
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

  public void waitForUserPageToLoad() {
    try{
      if(driver.getTitle().equalsIgnoreCase("Users | Active Admin Depot") && wh.isElementPresent(NewUser)){
      }
      else {
        throw new Exception();
      }
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "User Page Validation", "User Page failed to load successfully");
    }
  }

  public void clickCancelButton() {
    try {
      wh.clickElement(Cancel);
      waitForUserPageToLoad();
      test.log(LogStatus.PASS, "Cancel Button validation", "Cancel Button validation successfully");
    }

    catch(Exception e) {
      test.log(LogStatus.FAIL, "Cancel Button validation", "Cancel Button validation failed");
    }
  }

  public void fieldValidations() {
    try {
      wh.sendKeys(TextFieldUserName, "edwinboy");
      wh.clickElement(CreateUser);
      if(wh.isElementPresent(UserNameFieldValidation, 2) && wh.isElementPresent(PasswordFieldValidation,2) && wh.isElementPresent(EmailFieldValidation,2)) {
        test.log(LogStatus.PASS, "Field validation for UserName", "Field validation for UserName completed successfully");
      }
      else {
        test.log(LogStatus.FAIL, "Field validation for UserName", "Field validation for UserName failed");
      }

      wh.sendKeys(TextFieldUserName, "testuser123");
      wh.clickElement(CreateUser);
      if(wh.isElementPresent(PasswordFieldValidation,2) && wh.isElementPresent(EmailFieldValidation,2)) {
        test.log(LogStatus.PASS, "Field validation for Password", "Field validation for Password completed successfully");
      }
      else {
        test.log(LogStatus.FAIL, "Field validation for Password", "Field validation for Password failed");
      }

      wh.sendKeys(TextFieldUserName, "testuser123");
      wh.sendKeys(TextFieldPassword, "testuser123");
      wh.clickElement(CreateUser);
      if(wh.isElementPresent(EmailFieldValidation,2)) {
        test.log(LogStatus.PASS, "Field validation for blank email", "Field validation for blank email completed successfully");
      }
      else {
        test.log(LogStatus.FAIL, "Field validation for blank email", "Field validation for blank email failed");
      }

      wh.sendKeys(TextFieldUserName, "testuser123");
      wh.sendKeys(TextFieldPassword, "testuser123");
      wh.sendKeys(TextFieldEmail, "testuser123");
      wh.clickElement(CreateUser);
      if(wh.isElementPresent(EmailFieldValidation,2)) {
        test.log(LogStatus.PASS, "Field validation for invalid email", "Field validation for invalid email completed successfully");
      }
      else {
        test.log(LogStatus.FAIL, "Field validation for invalid email", "Field validation for invalid email failed");
      }

    }

    catch(Exception e) {
      test.log(LogStatus.FAIL, "Field validations", "Field validations failed");
    }

  }

  public void createNewUser() {
    try {
      wh.sendKeys(TextFieldUserName, testdata.get("TD.userName"));
      wh.sendKeys(TextFieldPassword, testdata.get("TD.password"));
      wh.sendKeys(TextFieldEmail, testdata.get("TD.email"));
      wh.clickElement(CreateUser);
      if(wh.isElementPresent(UserTestValidation)){
        test.log(LogStatus.PASS, "Create User Button validation", "Create User Button validation completed successfully");
      }

    }

    catch(Exception e) {
      test.log(LogStatus.FAIL, "Create User button validation", "Create User Button validation failed");
    }
  }
  
  public void clickUsersTab() {
    try {
      wh.clickElement(Users);
    }
    catch(Exception e) {
      test.log(LogStatus.FAIL, "Click Users tab", "Click Users tab failed");
    }
  }

}







