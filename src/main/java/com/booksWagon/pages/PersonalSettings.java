package com.booksWagon.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.booksWagon.utils.DriverManager;
import com.booksWagon.utils.ExcelUtility;

public class PersonalSettings {
	WebDriver driver;
	ExcelUtility objExcel = new ExcelUtility();
	
//	variables
	By btnMyAccountDropDown = By.xpath("//li[@class='list-inline-item text-center loginpopupwrapper']");
	By btnLogin = By.xpath("(//a[@href='https://www.bookswagon.com/login'])[1]");
	By btnLogout = By.xpath("//a[@id='ctl00_lnkbtnLogout']");
	By inpNumber = By.xpath("//input[@name='ctl00$phBody$SignIn$txtEmail']");
	By inpPassword = By.xpath("//input[@name='ctl00$phBody$SignIn$txtPassword']");
	By btnSignin = By.xpath("//a[@id='ctl00_phBody_SignIn_btnLogin']");
	By btnPersonalSettings = By.xpath("(//div[@class='accountbox']//a)[1]");
//	form input variables
	By inpFirstName = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtfname']");
	By inpLastName = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtLName']");
	By inpEmail = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtemail']");
	By inpFax = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtFax']");
	By inpProfileName = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtProfileName']");
	By inpMobileNumber = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtMobile']");
	By inpWishlist = By.xpath("//select[@name='ctl00$phBody$AccountSetting$fvCustomer$ddlWishlist']");
	By inpNewsletter = By.xpath("//select[@name='ctl00$phBody$AccountSetting$fvCustomer$chkNewsletter']");
	By inpTransmail = By.xpath("//select[@name='ctl00$phBody$AccountSetting$fvCustomer$chkTransUnsubscribe']");
	By inpPromomail = By.xpath("//select[@name='ctl00$phBody$AccountSetting$fvCustomer$chkPromoUnsubscribe']");
	By inpCountryCode = By.xpath("//select[@name='ctl00$phBody$AccountSetting$fvCustomer$ddlCountryCode']");
	By btnUpdate = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$imgUpdate']");
	
	By overlayOTP = By.xpath("//div[@id='divOTP']");
	By inpOTP = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtEmailOTP']");
	By btnOTP = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$btnOTP']");

	
//	constructor for initializing
	public PersonalSettings() {
		this.driver = DriverManager.getDriver();
	}
	
//	methods
	public void gotoHomePage() {
		driver.get("https://www.bookswagon.com/");
	}
	
	public String getCurrentURL() {
		String currentURL="NULL";
		currentURL = driver.getCurrentUrl();
		return currentURL;
	}
	
	public void gotoPersonalSettings(){
		driver.findElement(btnMyAccountDropDown).click();
		driver.findElement(btnPersonalSettings).click();
	}
	
	public void fillDetailsTables(String firstName, String lastName, String email, String fax, String profileName,
			String wishList, String newsLetter, String transMail, String promoMail, String countryCode, String mobile) throws IOException {
		
		String actualEmailBeforeClearing = driver.findElement(inpEmail).getAttribute("value");
		String actualMobileBeforeClearing = driver.findElement(inpMobileNumber).getAttribute("value");
		System.out.println(actualEmailBeforeClearing+" "+actualMobileBeforeClearing+" "+email+" "+mobile);
		driver.findElement(inpFirstName).clear();
		driver.findElement(inpLastName).clear();
		driver.findElement(inpEmail).clear();
		driver.findElement(inpFax).clear();
		driver.findElement(inpProfileName).clear();
		driver.findElement(inpMobileNumber).clear();
		
		driver.findElement(inpFirstName).sendKeys(firstName);
        driver.findElement(inpLastName).sendKeys(lastName);
        driver.findElement(inpEmail).sendKeys(email);
        driver.findElement(inpFax).sendKeys(fax);
        driver.findElement(inpProfileName).sendKeys(profileName);
        driver.findElement(inpMobileNumber).sendKeys(mobile);
//		select
        new Select(driver.findElement(inpWishlist)).selectByVisibleText(wishList);
        new Select(driver.findElement(inpNewsletter)).selectByVisibleText(newsLetter);
        new Select(driver.findElement(inpTransmail)).selectByVisibleText(transMail);
        new Select(driver.findElement(inpPromomail)).selectByVisibleText(promoMail);
        new Select(driver.findElement(inpCountryCode)).selectByVisibleText(countryCode);
        solveCaptcha();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnUpdate));
        btn.click();

        if(!(actualEmailBeforeClearing.equals(email)) || ! (actualMobileBeforeClearing.equals(mobile)) ) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(overlayOTP));
            solveCaptcha();
            WebElement verifyBtn = driver.findElement(By.xpath("//input[@id='ctl00_phBody_AccountSetting_fvCustomer_btnOTP']"));
            verifyBtn.click();
            solveCaptcha();
            driver.findElement(btnUpdate).click();
        }
	}

	public ArrayList<String> fillDetailsExcel() throws IOException, InterruptedException {
		ArrayList<String> testCase = new ArrayList();
		String excelPath = "C:\\Users\\susil.k\\eclipse-workspace\\BooksWagonProject\\src\\test\\resources\\testData\\personalSettingsData.xlsx";
		int rowCount =  objExcel.getRowCount(excelPath,"personalSettingsSheet");
		String actualEmailBeforeClearing = driver.findElement(inpEmail).getAttribute("value");
		String actualMobileBeforeClearing = driver.findElement(inpMobileNumber).getAttribute("value");
		
		for(int row=1;row<rowCount;row++) {
			int cellCount = objExcel.getCellCount(excelPath,"personalSettingsSheet",1);

			String firstName = objExcel.getCellData(excelPath,"personalSettingsSheet",row,0);
			String lastName = objExcel.getCellData(excelPath,"personalSettingsSheet",row,1);
			String email = objExcel.getCellData(excelPath,"personalSettingsSheet",row,2);
			String fax  = new BigDecimal(objExcel.getCellData(excelPath,"personalSettingsSheet",row,3)).toPlainString(); 
			String profileName = objExcel.getCellData(excelPath,"personalSettingsSheet",row,4);
			String wishlist = objExcel.getCellData(excelPath,"personalSettingsSheet",row,5);
			String newsLetter = objExcel.getCellData(excelPath,"personalSettingsSheet",row,6);
			String transMail = objExcel.getCellData(excelPath,"personalSettingsSheet",row,7);
			String promoMail = objExcel.getCellData(excelPath,"personalSettingsSheet",row,8);
			String countryCode = objExcel.getCellData(excelPath,"personalSettingsSheet",row,9);
			String mobileNumber = new BigDecimal(objExcel.getCellData(excelPath,"personalSettingsSheet",row,10)).toPlainString(); 

			driver.findElement(inpFirstName).clear();
			driver.findElement(inpLastName).clear();
			driver.findElement(inpEmail).clear();
			driver.findElement(inpFax).clear();
			driver.findElement(inpProfileName).clear();
			driver.findElement(inpMobileNumber).clear();
			
			driver.findElement(inpFirstName).sendKeys(firstName);
	        driver.findElement(inpLastName).sendKeys(lastName);
	        driver.findElement(inpEmail).sendKeys(email);
	        driver.findElement(inpFax).sendKeys(fax);
	        driver.findElement(inpProfileName).sendKeys(profileName);
	        driver.findElement(inpMobileNumber).sendKeys(mobileNumber);
//			select
	        new Select(driver.findElement(inpWishlist)).selectByVisibleText(wishlist);
	        new Select(driver.findElement(inpNewsletter)).selectByVisibleText(newsLetter);
	        new Select(driver.findElement(inpTransmail)).selectByVisibleText(transMail);
	        new Select(driver.findElement(inpPromomail)).selectByVisibleText(promoMail);
	        new Select(driver.findElement(inpCountryCode)).selectByVisibleText(countryCode);
	        
	        solveCaptcha();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnUpdate));
//	        btn.click();
	        driver.findElement(btnUpdate).click();

	        if(!(actualEmailBeforeClearing.equals(email)) || ! (actualMobileBeforeClearing.equals(mobileNumber)) ) {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(overlayOTP));
	            solveCaptcha();
	            WebElement verifyBtn = driver.findElement(By.xpath("//input[@id='ctl00_phBody_AccountSetting_fvCustomer_btnOTP']"));
	            verifyBtn.click();
	            solveCaptcha();
	            driver.findElement(btnUpdate).click();
	        }
	        
//	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        Thread.sleep(4000);
	        String actualURL = driver.getCurrentUrl();
	        String expectedURL = "https://www.bookswagon.com/myaccount.aspx";
	        if(expectedURL.equals(actualURL)) {
	        	testCase.add("pass");
	        	gotoPersonalSettings();
	        }
	        else {
	        	testCase.add("fail");
	        	driver.navigate().back();
	        }
		}
		driver.navigate().back();
		return testCase;
	}
	
	public void solveCaptcha() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 
	    // Prompt the user to bypass the CAPTCHA and type "continue" in the console
	    System.out.println("Please complete the CAPTCHA and type 'continue' to proceed...");
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    String userInput = "";
	    // Wait until the user types "continue"
	    while (!userInput.equalsIgnoreCase("continue")) {
	        userInput = reader.readLine();
	    }
	}
	
	public boolean checkLogin(){
		Actions actions = new Actions(driver);
		WebElement hoverDropdown = driver.findElement(btnMyAccountDropDown);
		actions.moveToElement(hoverDropdown).perform();
		WebElement btnLogoutElement = driver.findElement(btnLogout);
		if (btnLogoutElement.isDisplayed()) {
		    return true;
		}
		else return false;
	}
	
    public void dummyLogin() {
		driver.findElement(btnLogin).click();
		driver.findElement(inpNumber).sendKeys("7708210534");
		driver.findElement(inpPassword).sendKeys("susil@2002A");
		driver.findElement(btnSignin).click();
    }

}
