package com.booksWagon.stepDefinition;

import java.io.IOException;

import com.booksWagon.pages.PersonalSettings;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class PersonalSettingsStepDefinition {
	PersonalSettings obj = new PersonalSettings();
	

	@Given("I was in the home page")
	public void i_was_in_the_home_page() {
	    System.out.println("i_was_in_the_home_page");
	    obj.gotoHomePage();
	    String expectedURL = "https://www.bookswagon.com/";
	    String actualURL = obj.getCurrentURL();
	    Assert.assertEquals("Not in the home page...",expectedURL, actualURL);
	}

	@When("I navigate to personal settings")
	public void i_navigate_to_personal_settings() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("i_navigate_to_personal_settings");
		obj.gotoPersonalSettings();
		String expectedURL = "https://www.bookswagon.com/accountsetting.aspx";
	    String actualURL = obj.getCurrentURL();
	    Assert.assertEquals("Not in the personal settings page...",expectedURL, actualURL);
	}

	@And("I fill valid {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string} credentials")
	public void i_fill_valid_credentials(String firstName, String lastName, String email, String fax, String profileName,
			String wishList, String newsLetter, String transMail, String promoMail, String countryCode, String mobile) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("i_fill_valid_credentials");
		obj.fillDetailsTables( firstName,  lastName,  email,  fax,  profileName,  wishList,  newsLetter,  transMail,  promoMail,  countryCode,  mobile);
		
	}

	@And("I solve captcha and i click on update")
	public void i_solve_captcha_and_i_click_on_update() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("i_solve_captcha_and_i_click_on_update");
	}

	@Then("the details should be updated")
	public void the_details_should_be_updated() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("the_details_should_be_updated");
	}

	@Before
	public void homePage() {
		System.out.println("before tag");
		obj.gotoHomePage();
	}
	
	@BeforeStep
	public void before() {
		System.out.println("before step tag");
		if(obj.checkLogin())System.out.println("logout found");
		else obj.dummyLogin();
	}
}
