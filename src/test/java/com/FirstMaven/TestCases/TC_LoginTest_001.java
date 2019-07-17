package com.FirstMaven.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.FirstMaven.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	
	@Test
	public void LoginTest() throws InterruptedException, IOException
	{
	
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		Logger.info("Entered username");
		
		Thread.sleep(5000);
		lp.setPassword(password);
		Logger.info("Entered password");
		
		lp.clickSubmit();
		Logger.info("Clicked on submit button");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			Logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver,"LoginTest");
			Assert.assertTrue(false);
			Logger.info("Login test failed");
		}
	}
}
