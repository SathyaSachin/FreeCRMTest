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
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	
	String sheetName="contacts";
	
	public ContactsPageTest() {
	super();	
	}

	@BeforeMethod
	public void SetUp() throws Exception {
		initialization();
		testutil=new TestUtil();
		loginpage=new LoginPage();
		contactspage =new ContactsPage();
		homepage=loginpage.login("username","password");
		testutil.switchToFrame();
		contactspage=homepage.clickOnContactsLink(); 
	}
	
	@Test(priority=2)
	public void verifyContactsPageLabel() {
		
		Assert.assertTrue(contactspage.verifyContactsLabel(),"contacts label is missing");
	}
	@Test(priority=1)
	public void validateCreateNewContact() {
		homepage.clickOnNewContactLink();
		contactspage.createNewContact("Mr.", "Andy", "Perry", "Amazon");
	}
	
	@DataProvider
	public Object[][] getCrmTestData() throws Exception {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
		
	}
	@Test(priority=3,dataProvider="getCrmTestData")
	public void validateCreateNewContacts(String title,String firstname,String lastname,String company) {
		homepage.clickOnNewContactLink();
		contactspage.createNewContact(title, firstname, lastname, company);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
}
