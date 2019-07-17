package com.FirstMaven.TestCases;


import java.io.File;
//import java.io.FileUtils

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import com.FirstMaven.Utilities.*;
import com.aventstack.extentreports.utils.FileUtil;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger Logger;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser)
	{
		Logger = Logger.getLogger("MyFirstMavenProject");
		PropertyConfigurator.configure("log4j.properties");
		
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath());
			driver = new FirefoxDriver();	
		}
		else
		{
			System.setProperty("webdriver.ie.driver",readconfig.getiepath());
			driver = new InternetExplorerDriver();	
		}
		
		driver.get(baseURL);
		Logger.info("------------------------Test case execution started-----------------------");
		Logger.info("Opened the URL");
		
		
	}
	
	@AfterClass
	public void teardown()
	{
		Logger.info("--------------------End of the test case execution------------------------");
		driver.quit();
		
	}
	
	public void captureScreen(WebDriver driver, String tname ) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".jpeg");
		FileUtils.copyFile(source,target);
		System.out.println("Screenshot taken");
		
		
	}
}
