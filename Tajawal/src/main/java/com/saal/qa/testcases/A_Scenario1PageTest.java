package com.saal.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import com.saal.qa.base.TestBase;
import com.saal.qa.pages.A_Scenario1Page;
import com.saal.qa.util.TestUtil;



public class A_Scenario1PageTest extends TestBase{


	TestUtil testUtil;
	A_Scenario1Page scenario1Page;
	String sheetName = "Sheet1";
	
//	WebDriver driver;
	   
	public A_Scenario1PageTest(){
			super();
			
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		testUtil = new TestUtil();
		scenario1Page = new A_Scenario1Page();
	}
			
	@Test(priority=0, dataProvider="getBookingTestData", description="Validate Scenario 1")
	public void validateCreateNewContact(String from_, String to_, String date_, String passenger_) throws InterruptedException, IOException{
		try {
		scenario1Page.bookingticket(from_, to_,date_,passenger_);
		TestUtil.takeScreenshotAtEndOfTest();
		}
		catch (AssertionError ae) {
  			TestUtil.runTimeInfo("error", "***CAPTURED SCREENSHOT****");
  			TestUtil.takeScreenshotAtEndOfTest();
			ae.printStackTrace();
		}
	}
	
	@DataProvider
	public Object[][] getBookingTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
