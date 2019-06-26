package com.saal.qa.base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.saal.qa.util.TestUtil;
import com.saal.qa.util.WebEventListener;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger log;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\saal"
					+ "\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// /PageObjectModel/src/main/resources/chromedriver.exe
	public static void initialization() throws IOException{
//		String browserName = prop.getProperty("browser");
//		  System.out.print("Enter your preferred browser for test 1.CHROME 2.FIREFOX: ");  
//	      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  	  
//	      String planName=br.readLine(); 
//	      System.out.println("The Browser you selected is: " +planName);
		String browserName = prop.getProperty("browser");
//	      String browserName = "planName";
		
		if(browserName.equals("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\main\\resources\\chromedriver.exe");
            driver =new ChromeDriver(options);
		}
		else if(browserName.equals("FF")){
			FirefoxOptions options =new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\src\\main\\resources\\geckodriver.exe");			
			driver = new FirefoxDriver(options);
		}
		
		
		log = Logger.getLogger(TestBase.class);
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		//*********************************//
//		driver.get(prop.getProperty("url"));
		//*********************************//
		
//		driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
//		driver=driverManager.getWebDriver();
//		driver.get(prop.getProperty("url"));
		//*********************************//
	}
	
	
	
	
	
	
	
	

}
