package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization(); 
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
		homePage.clickOnContactsLink();
	}
	
	@Test
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing on Page");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	
	@Test(dataProvider="getCRMTestData")
	public void createContact(String firstName , String lastName , String company , String category) {
//		contactsPage.createContact("Rohit", "Suryan", "Nagarro" ,"Lead");
		contactsPage.createContact(firstName, lastName, company, category);
	}
	
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
