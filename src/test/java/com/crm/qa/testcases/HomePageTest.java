package com.crm.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	public ExtentReports extent;
	public ExtentTest extentTest;
	

	public HomePageTest() {
		super();
	}


	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir") + "\\Reports" + File.separator
				+ "ExtentPage.html", true);
		extent.addSystemInfo("Host Name", "Rohit ");
		extent.addSystemInfo("User Name", "Rohit Suryan");
		extent.addSystemInfo("Environment", "QA");
		
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	


	@BeforeMethod
	public void setUp() {
		initialization(); 
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
		
	}	
	

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		extentTest = extent.startTest("freeCrmTitleTest");
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title , "Cogmento CRM" , "HomePage title not matched");
	}

	@Test(priority = 2)
	public void verifyUserNameTest() {
		extentTest = extent.startTest("freeCrmTitleTest");
		boolean username = homePage.verifyCorrectUserName();
		Assert.assertTrue(username);
	}
	
	@Test(priority = 3)
	public void verifyContactsLinkTest() {
		extentTest = extent.startTest("freeCrmTitleTest");
		contactsPage = homePage.clickOnContactsLink();
		boolean us = homePage.verifyContactsPage();
		Assert.assertTrue(us);
	}





	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}


}
