package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	//Page Factory - OR
	@FindBy(name="username")
	 WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[@href='https://www.crmpro.com/register/']")
	WebElement signUp;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement crmLogo;
	
	public LoginPage() {
		PageFactory.initElements(driver, this); //this to init current class obj
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) throws Exception {
		username.sendKeys(prop.getProperty(un));
		password.sendKeys(prop.getProperty(pwd));
		Thread.sleep(2000);
		loginBtn.click();
		
		return new HomePage();
		
	}
}
