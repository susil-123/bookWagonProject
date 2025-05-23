package com.booksWagon.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.booksWagon.utils.DriverManager;

public class PersonalSettings {
	WebDriver driver;
	
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
	By inpMobilNumber = By.xpath("//input[@name='ctl00$phBody$AccountSetting$fvCustomer$txtMobile']");
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
		
		
		driver.findElement(inpFirstName).clear();
		driver.findElement(inpLastName).clear();
		driver.findElement(inpEmail).clear();
		driver.findElement(inpFax).clear();
		driver.findElement(inpProfileName).clear();
		driver.findElement(inpMobilNumber).clear();
		
		driver.findElement(inpFirstName).sendKeys(firstName);
        driver.findElement(inpLastName).sendKeys(lastName);
        driver.findElement(inpEmail).sendKeys(email);
        driver.findElement(inpFax).sendKeys(fax);
        driver.findElement(inpProfileName).sendKeys(profileName);
        driver.findElement(inpMobilNumber).sendKeys(mobile);
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

        // Wait for the OTP overlay to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(overlayOTP));

        // Enter the OTP
        WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(inpOTP));
//        otpField.sendKeys("123453");
        solveCaptcha();
        // Click the verify button
        WebElement verifyBtn = driver.findElement(btnOTP);
        verifyBtn.click();

        // Wait for overlay to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayOTP));
        
        Actions action = new Actions(driver);
        action.click().perform();
        driver.findElement(btnUpdate).click();
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
//		    System.out.println("Child element is visible inside the dropdown.");
//			actions.click().perform();
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
