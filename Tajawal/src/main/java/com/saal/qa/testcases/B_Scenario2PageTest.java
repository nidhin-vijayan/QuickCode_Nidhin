package com.saal.qa.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import com.saal.qa.base.TestBase;
import com.saal.qa.pages.A_Scenario1Page;
import com.saal.qa.pages.B_Scenario2Page;
import com.saal.qa.util.TestUtil;



public class B_Scenario2PageTest extends TestBase{


	TestUtil testUtil;
	B_Scenario2Page scenario1Page;
	String sheetName = "Sheet1";
//	WebDriver driver;
	   
	public B_Scenario2PageTest(){
			super();
			
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		testUtil = new TestUtil();
		scenario1Page = new B_Scenario2Page();
	}
			
	@Test(priority=0, dataProvider="getBookingTestData", description="Validate Scenario 2")
	public void validateScenarioTwo(String from_, String to_, String date_, String passenger_) throws InterruptedException, IOException{
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
