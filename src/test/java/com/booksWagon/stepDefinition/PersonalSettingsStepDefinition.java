package com.booksWagon.stepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;

import com.booksWagon.pages.PersonalSettings;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class PersonalSettingsStepDefinition {
	PersonalSettings obj = new PersonalSettings();
	

	@Given("I was in the home page")
	public void i_was_in_the_home_page() {
	    System.out.println("i_was_in_the_home_page");
	    
	    if(obj.checkLogin())System.out.println("logout found");
	    else obj.dummyLogin();
	    
	    obj.gotoHomePage();
	    
	    
	    String expectedURL = "https://www.bookswagon.com/";
	    String actualURL = obj.getCurrentURL();
	    Assert.assertEquals(actualURL,expectedURL);
	}

	@When("I navigate to personal settings")
	public void i_navigate_to_personal_settings() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("i_navigate_to_personal_settings");
		
		if(obj.checkLogin())System.out.println("logout found");
		else obj.dummyLogin();
		
		obj.gotoPersonalSettings();
	}

	@And("I fill valid {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} credentials and update")
	public void i_fill_valid_credentials(String firstName, String lastName, String email, String fax, String profileName,
			String wishList, String newsLetter, String transMail, String promoMail, String countryCode, String mobile) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("i_fill_valid_credentials");
		obj.fillDetailsTables( firstName,  lastName,  email,  fax,  profileName,  wishList,  newsLetter,  transMail,  promoMail,  countryCode,  mobile);
		
	}

	@And("I fill credentials and update")
	public void i_fill_credentials_and_update() throws IOException, InterruptedException {
//		ArrayList<String>expectedTestCase = new ArrayList(Arrays.asList("pass","fail","pass"));
//		ArrayList<String>actualTestCase = obj.fillDetailsExcel();
		obj.fillDetailsExcel();
//		ArrayList<String>expectedTestCase = obj.returnExpectedTestCases();
//		for(int i=0;i<expectedTestCase.size();i++) {
//			String expected = expectedTestCase.get(i);
//			String actual = actualTestCase.get(i);
//			System.out.println("expected: "+expected+" "+"actual: "+actual);
//			Assert.assertEquals(actual, expected);
//		}
	}
	
	@Then("the details should be updated")
	public void the_details_should_be_updated() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
//		System.out.println("the_details_should_be_updated");
//		String expectedURL = "https://www.bookswagon.com/myaccount.aspx";
//	    String actualURL = obj.getCurrentURL();
//	    Assert.assertEquals(actualURL,expectedURL);
		ArrayList<String>expectedTestCase = obj.returnColumn(11);
		ArrayList<String>actualTestCase = obj.returnColumn(12);
		for(int i=0;i<expectedTestCase.size();i++) {
			String expected = expectedTestCase.get(i);
			String actual = actualTestCase.get(i);
			System.out.println("expected: "+expected+" "+"actual: "+actual);
			Assert.assertEquals(actual, expected);
		}
	}

	@Before
	public void homePage() {
		System.out.println("before tag");
		obj.gotoHomePage();
	}
	
//	@BeforeStep
//	public void before() {
//		System.out.println("before step tag");
//		if(obj.checkLogin())System.out.println("logout found");
//		else obj.dummyLogin();
//	}
}
