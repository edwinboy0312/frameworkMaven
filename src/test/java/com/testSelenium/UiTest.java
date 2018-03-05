package com.testSelenium;

import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.testSelenium.pages.HomePage;
import com.testSelenium.pages.NewUserPage;
import com.testSelenium.pages.PageBase;
import com.testSelenium.pages.UsersPage;
import com.testSelenium.utilities.CommonUtilities;
import com.testSelenium.utilities.InstanceHolder;
import com.testSelenium.utilities.WebDriverHelper;


public class UiTest extends PageBase{

  WebDriver driver;
  ExtentReports report;
  ExtentTest test;
  WebDriverHelper wh;
  InstanceHolder ih;
  public HashMap<String, String> testdata = new HashMap<String,String>();
  CommonUtilities excelUtilities = new CommonUtilities();
  HomePage homePage; 
  UsersPage users;
  NewUserPage newUserPage;

  @BeforeSuite
  public void beforeSuite() throws IOException {
    report = new ExtentReports("./reports/ExecutionReport.html", true, DisplayOrder.OLDEST_FIRST);
  }
  
  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    driver = new ChromeDriver(options);
    testdata = excelUtilities.loadData();
    wh = new WebDriverHelper(driver, report, test);
  }


  @Test
  public void testFilterPage() throws Exception {
    test = report.startTest("UI - Validating Filter Page for Users");
    ih = new InstanceHolder(driver, report, test, wh, testdata);

    homePage = new HomePage(ih);
    users =new UsersPage(ih);
    homePage.open();
    homePage.clickUsers();
    users.validateUsernameFilter();
    users.validateEmailFilter();
    users.validateDateFilter();
    users.validateComboFilter();
  }

  @Test
  public void testNewCustomerPage() throws Exception {
    test = report.startTest("UI -Validating New Customer Screen");
    ih = new InstanceHolder(driver, report, test, wh, testdata);

    homePage = new HomePage(ih);
    users =new UsersPage(ih);
    newUserPage = new NewUserPage(ih);
    homePage.open();
    homePage.clickUsers();
    users.clickNewUser();
    newUserPage.clickCancelButton();
    users.clickNewUser();
    newUserPage.fieldValidations();
    newUserPage.createNewUser();
    newUserPage.clickUsersTab();
    users.deleteTestData();
  }

  @AfterMethod
  public void afterTest() {
    report.endTest(test);
  }

  @AfterClass
  public void afterClass() {
    report.flush();
    driver.quit();
  }

}
