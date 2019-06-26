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

public class B_Scenario2Page extends TestBase {

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
	
	@FindBy(xpath = "//*[@class='sc-fHxwqH dvaYEJ sc-bYSBpT dTBkYG row']")
	List<WebElement> flightlist;
	
	@FindBy(xpath = "//div[@data-testid='FlightSearchResult__Itinerary1__PriceLabel")
	WebElement cheapprice;
	
	@FindBy(xpath = "//a[@class='poj0qx-2 ciwQeg']")
	WebElement scrollcontact;
	
	String scrollwait = "//a[@class='poj0qx-2 ciwQeg']";

	// Initializing the Page Objects:
	public B_Scenario2Page() {
		PageFactory.initElements(driver, this);
	}

	public void bookingticket(String from, String to, String date, String passenger) {
		driver.get(prop.getProperty("urla"));
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
		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].click();", notifybutton);
		js.executeScript("arguments[0].scrollIntoView(true);", scrollcontact);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(scrollwait)));
		System.out.println(flightlist.size());
		//*******************************************************************************************
		String price1 = "//div[@data-testid='FlightSearchResult__Itinerary1__PriceLabel']";         
		WebElement Pricelist = driver.findElement(By.xpath(price1));
		System.out.println("The price list is:"+Pricelist.getText());
		String ex= Pricelist.getText();
		System.out.println("The value of a:" +ex);
		String finalprice1 = ex.replaceAll("[^\\d]", "" );
		System.out.println("The value of Pricelist1:"+finalprice1);
		int tfinalprice1 = Integer.parseInt(finalprice1);	
		
		for(int i=1;i<flightlist.size();i++){
			if(i==3)
				i++;
		String price2 = "//div[@data-testid='FlightSearchResult__Itinerary"+i+"__PriceLabel']"; 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement Pricelist2 = driver.findElement(By.xpath(price2));
		System.out.println("The price list is:"+Pricelist2.getText());
		String exe= Pricelist.getText();
		System.out.println("The value of Pricelist2:" +ex);
		String finalprice2 = exe.replaceAll("[^\\d]", "" );
		System.out.println("The value of a:"+finalprice2);
		int tfinalprice2 = Integer.parseInt(finalprice2);
		if(tfinalprice1>=tfinalprice2){
			System.out.println("Assertion 5 : Passed ,first price INTs are the lowest comparing to other prices ");
		}else{
			System.out.println("Assertion 5 : Failed");
		}
		}
		//*******************************************************************************************
//		for(int i=1;i<(flightlist.size());i++){
//			String price = "//div[@data-testid='FlightSearchResult__Itinerary"+i+"__PriceLabel']";         
//			List<WebElement> Pricelist = driver.findElements(By.xpath(price));
//			System.out.println("The price list is:"+Pricelist.get(0).getText());
//			String ex= Pricelist.get(0).getText();
//			System.out.println("The value of a:"+ex);
//			String part1 = ex.replaceAll("\\\\n"," ");
//			System.out.println("The value of a:"+part1);
////			int resulta=Integer.parseInt(a);
////			System.out.println("The value of resulta:"+resulta);
//			String b= Pricelist.get(i+1).getText();
//			System.out.println("The value of b:"+b);
////			if((Pricelist.get(0).getText())>(Pricelist.get(i).getText()))
//		}
		
	}

}
