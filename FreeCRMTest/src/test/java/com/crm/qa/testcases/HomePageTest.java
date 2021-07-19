package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	
	public HomePageTest() {
	super();	
	}
	
	@BeforeMethod
	public void SetUp() throws Exception {
		initialization();
		testutil=new TestUtil();
		loginpage=new LoginPage();
		homepage=loginpage.login("username","password");
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle=homepage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home page title not matching");//msg will be printed when testcase fails
	
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testutil.switchToFrame();
		 boolean flag=homepage.verifyCorrectUserName();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testutil.switchToFrame();
		contactspage=homepage.clickOnContactsLink();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
