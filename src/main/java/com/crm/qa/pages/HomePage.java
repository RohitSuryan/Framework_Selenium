package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="//span[contains(text(),'Rohit Suryan')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//*[@id=\"main-nav\"]/div[3]/a")
	WebElement contactsLink;
	
	@FindBy(xpath="//*[@id=\"main-nav\"]/div[5]/a")
	WebElement dealsLink;
	
	@FindBy(xpath="//*[@id=\"main-nav\"]/div[6]/a")
	WebElement tasksLink;
	
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[1]")
	WebElement contactsVerify;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click(); 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		contactsLink.click(); 
		
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		contactsLink.click(); 
		return new TasksPage();
	}
	
	public boolean  verifyContactsPage() {
		return contactsVerify.isDisplayed();
	}
}
