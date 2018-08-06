package com.exercise.Setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.exercise.TestUtils.ExcelReader;

//All test setup activities like WebDriver, excel reader, property initialisation done here
public class TestSetup {
	public static WebDriver driver;
	public static Properties configProperty;
	
	public ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\main\\resources\\testData\\VR.xlsx");
	
	@Before
	public void beforeSuit() {
		// excel reader class instantiation
		// property file instantiation
		
		try {
			FileInputStream fi = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\propertyFIles\\config.properties"));
			configProperty = new Properties();
			try {
				configProperty.load(fi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// Initialise based on the provide config and open the url
		System.out.println("Driver Status:" + driver);
		if (driver == null) {
			if (configProperty.getProperty("browser").equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if (configProperty.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if (configProperty.getProperty("browser").equalsIgnoreCase("ie")) {
				// todo for IE browser
			}
		}
		
		driver.navigate().to(configProperty.getProperty("url"));
	
	}
	
	@After
	public void afterSuite() {
		// browser close/quit
		driver.quit();		// Close all open instances of webdrivers
	}
}
