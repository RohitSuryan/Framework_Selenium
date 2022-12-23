package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[1]")
	@CacheLookup // For effiency and performance of the program .It stores the path of the element , there is no requirement for going to browser again and again 
	WebElement contactsLabel;
	
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[2]/div/a/button")
	WebElement createContact;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath = "//*[@id=\"main-content\"]/div/div[2]/form/div[2]/div[2]/div/div/input")
	WebElement company;
	
	@FindBy(xpath = "//*[@id=\"dashboard-toolbar\"]/div[2]/div/button[2]")
	WebElement save;
	
	@FindBy(name = "category")
	WebElement category;
	
	
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void createContact(String fn , String ln , String Com , String title) {
		
		createContact.click();
		firstName.sendKeys(fn);
		lastName.sendKeys(ln);
		company.sendKeys(Com);
		company.sendKeys(Keys.RETURN);
		category.click();
		selectOptionUsingCustomLoc(driver, title);
		
//		Select select = new Select(category);
//		select.selectByVisibleText(title);
		save.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void selectOptionUsingCustomLoc(WebDriver driver, String valueToBeSelected) {
		String customLoc = "//span[text()='" + valueToBeSelected + "']";
		driver.findElement(By.xpath(customLoc)).click();
	}
	
}
