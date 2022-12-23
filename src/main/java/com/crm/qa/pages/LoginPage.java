package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement signUp;
	
	@FindBy(xpath = "//a[contains(text(),'Got an account? Log in here')]")
	WebElement login;
	
	@FindBy(name = "email")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//div[contains(text(),'Login')]")
	WebElement login_button;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement crmLogo;
	
	
	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un , String pwd) {
		
		signUp.click();
		login.click();
		username.sendKeys(un);
		password.sendKeys(pwd);
		login_button.click();
		
		return new HomePage();
	}
	
	
	
	
	
	
	
}
