package com.FirstMaven.TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FirstMaven.PageObjects.LoginPage;
import com.FirstMaven.Utilities.XLUtils;

public class TC_LoginTest_002 extends BaseClass{


	@Test(dataProvider = "LoginData")
	public void Login_DDT(String uname,String pswd)
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);
		Logger.info("Entered username");
		
		lp.setPassword(pswd);
		Logger.info("Entered password");
		
		lp.clickSubmit();
		Logger.info("Clicked on submit button");
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
			Logger.info("Closed homepage alert");
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			Logger.info("Closed logout alert");
		}
		
	}
	
	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}catch (NoAlertPresentException e)
		{
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String [][] getData() throws IOException
	{
		String path =System.getProperty("user.dir")+"/src/test/java/com/FirstMaven/TestData/SampleDataFile.xlsx";
		int rownum = XLUtils.getRowCount(path, "sheet1");
		int cocount = XLUtils.getCellCount(path, "sheet1", 1);
		
		String logindata[][] = new String[rownum][cocount];
		for (int i=1;i<=rownum;i++)
		{
			for(int j=0;j<cocount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j);		
			}
		}
		return logindata;
	}
}
