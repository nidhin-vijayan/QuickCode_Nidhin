package com.saal.qa.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.saal.qa.base.TestBase;
import com.saal.qa.util.TestUtil;

public class A_Scenario1Page extends TestBase {

	@FindBy(xpath = "//*[text()='One-way']")
	WebElement oneway;

	@FindBy(xpath = "//div[@class='wt4csw-5 kwiqCh']/div[@class='AutoComplete__Wrapper phbroq-0 iCLoB' and 1]/div[1]/div[1]")
	WebElement originbefore;

	@FindBy(xpath = "//input[@data-testid='FlightSearchBox__FromAirportInput']")
	WebElement origin;

	@FindBy(xpath = "//input[@data-testid='FlightSearchBox__ToAirportInput']")
	WebElement destination;

	String dest_list = "//*[@class='AutoComplete__ListItem AutoComplete__ListItem--highlighted phbroq-5 dbvRBC']";

	@FindBy(xpath = "//div[@class='sc-5uo3xu-2 sc-5uo3xu-3 jHXzET']")
	WebElement fromdate;

	String months = "//*[@class='DayPicker-Months']";

	@FindBy(xpath = "//*[@class='sc-1sn5k4t-1 kuIFqb']")
	WebElement classtype;

	@FindBy(xpath = "//*[@class='sc-1o8lb20-2 faFRbH']")
	WebElement passenger_drpdwn;

	@FindBy(xpath = "//*[@class='FlightSearchBox__PaxCounterMenu show sc-1o8lb20-3 hNCtCe']")
	WebElement passenger_menu;

	@FindBy(xpath = "//*[@class='FlightSearchBox__PaxCounterMenu show sc-1o8lb20-3 hNCtCe']/div[1]")
	WebElement adultsfield;

	@FindBy(xpath = "//*[@data-testid='FlightSearchPAXSelection__AdultsCountLabel']")
	WebElement adultscountlabel;

	@FindBy(xpath = "//*[@data-testid='FlightSearchPAXSelection__AdultsPlusButton']")
	WebElement adultsplus;

	@FindBy(xpath = "//*[@class='FlightSearchBox__PaxCounterToggle sc-1o8lb20-1 gRoUzx']")
	WebElement passengersettle;

	@FindBy(xpath = "//div[@class='d-none d-md-block me7zgm-9 iqXula']/button[1]")
	WebElement searchflight;

	@FindBy(xpath = "//*[contains(@class,'sc-cBrjTV hYinSI sc-ifAKCX fagsrJ btn btn-empty')]")
	WebElement notifybutton;

	@FindBy(xpath = "//div[@data-testid='FlightSearchResults__OriginAirportLabel']")
	WebElement sum_from;

	@FindBy(xpath = "//div[@data-testid='FlightSearchResults__DestinationAirportLabel']")
	WebElement sum_to;

	@FindBy(xpath = "//*[text()='2']")
	WebElement sum_passenger;

	@FindBy(xpath = "//*[text()='Mon, 01/07']")
	WebElement sum_date;
	// ****************************************************************//
	@FindBy(linkText = "Show all")
	WebElement showairlines;   

	@FindBy(xpath = "//div[6]/div[@class='sc-sdtwF btTEnt' and 2]/div[2]//label[contains(@type,'checkbox')]")
	List<WebElement> airlineslist;

	@FindBy(xpath = "//*[text()='EK: Emirates']")
	WebElement check_emirates;

	@FindBy(xpath = "//div[@data-testid='FlightSearchResult__Itinerary1__PriceLabel']")
	WebElement rflightprice;

	@FindBy(xpath = "//*[contains(@data-testid,'SelectFlightButton')]")
	WebElement rflight;
	
	@FindBy(xpath = "//*[contains(@data-testid,'__Itinerary1__SelectFlightButton')]")
	WebElement rflight1;

	@FindBy(xpath = "//*[@class='sc-dTLGrV gXeKbT']/div[2]")
	WebElement cflightpricebefore;

	@FindBy(xpath = "//span[@data-testid='FlightPAX__FareDetails__Adult1__TotalPriceLabel']")
	WebElement cflightprice;
	
	@FindBy(xpath = "//*[@class='sc-fHxwqH dvaYEJ sc-bYSBpT dTBkYG row']")
	List<WebElement> flightlist;
	
	@FindBy(xpath = "//div[@data-testid='FlightSearchResult__Itinerary1__PriceLabel")
	WebElement cheapprice;
	
	@FindBy(xpath = "//a[@class='poj0qx-2 ciwQeg']")
	WebElement scrollcontact;
	
	String scrollwait = "//a[@class='poj0qx-2 ciwQeg']";
	
	@FindBy(xpath = "//*[@name='travellers.0.lastName']")
	WebElement t1_lastname;
	

	// Initializing the Page Objects:
	public A_Scenario1Page() {
		PageFactory.initElements(driver, this);
	}

	public void bookingticket(String from, String to, String date, String passenger) {
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,15);
		oneway.click();
		originbefore.click();
		origin.sendKeys(from);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		origin.sendKeys(Keys.ENTER);
		destination.sendKeys(to);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dest_list)));
		destination.sendKeys(Keys.ENTER);
		fromdate.click();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(months)));
		String script = "//span[@data-testid='FlightSearchCalendar__" + date + "']";
		// System.out.println("my xpath is :"+script1);
		driver.findElement(By.xpath(script)).click();
		passenger_drpdwn.click();
		System.out.println("The Passenger list is:" + passenger);
		System.out.println("The adultscountlabel list is:" + adultscountlabel.getText());
		String adultsvalue = adultscountlabel.getText(); // TAKE TO TOP
		if (passenger.equalsIgnoreCase(adultsvalue)) {
			System.out.println("FINALLY FOUND");
			passengersettle.click();
		} else {
			adultsfield.click();
			adultsplus.click();
			System.out.println("FINALLY FOUND 1");
			passengersettle.click();
		}
		searchflight.click();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,
		// TimeUnit.SECONDS);
		// notifybutton.click();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].click();", notifybutton);
		//
		// //• Assert that your search query data you provide is aligned with
		// search summary module
		String summ_from = sum_from.getText();
		String summ_to = sum_to.getText();
		String summ_passenger = sum_passenger.getText();
		Assert.assertEquals(from, summ_from);
		System.out.println("Assertion 1 : MATCHED");
		Assert.assertEquals(to, summ_to);
		System.out.println("Assertion 2 : MATCHED");
		Assert.assertEquals(passenger, summ_passenger);
		System.out.println("Assertion 3 : MATCHED");
		// *********************************************//
		showairlines.click();
		int i = 0;
		do {
			if (airlineslist.get(i).getText().equalsIgnoreCase("EK: Emirates")) {
				airlineslist.get(i).click();
				System.out.println("The Emirates Airlines found");			
				break;
				
			}
			i++;
		} while (i < airlineslist.size()-1);
		{

			if (airlineslist.get(i).getText().equalsIgnoreCase("EK: Emirates") != true) {
				// check_emirates.click();
				airlineslist.get(0).click();
				System.out.println("The Emirates Airlines NOT found");

			}
		}
		js.executeScript("arguments[0].scrollIntoView(true);", scrollcontact);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(scrollwait)));
		System.out.println(flightlist.size());
		
		for (int j = 1; j < flightlist.size(); j++) {
			WebElement flightlisted = driver.findElement(By.xpath(
					"//div[contains(@data-testid,'FlightSearchResult__Itinerary" + j + "__Leg1__AirlineLabel')]"));
			WebElement actualflight = driver.findElement(By.xpath(
					"//div[contains(@data-testid,'FlightSearchResult__Itinerary1__Leg1__AirlineLabel')]"));
			String flightname = flightlisted.getText();
			String firstflight = actualflight.getText();
			Assert.assertEquals(firstflight, flightname);
			System.out.println("Assertion 4 : MATCHED");
		}
		js.executeScript("scroll(0,-350);");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='FlightSearchResult__Itinerary1__SelectFlightButton']")));		
		rflight.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		t1_lastname.sendKeys("Nidhin");
		//CHECK
	}

}
