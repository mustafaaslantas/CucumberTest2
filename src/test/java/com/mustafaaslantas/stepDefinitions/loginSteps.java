package com.mustafaaslantas.stepDefinitions;

import com.mustafaaslantas.pages.LoginPages;
import com.mustafaaslantas.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginSteps {
    WebDriver driver = DriverFactory.getDriver();
    LoginPages loginPages = new LoginPages(driver);
    WebDriverWait wait;
    public loginSteps() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Given("Go to login page")
    public void go_to_login_page() {
        LoginPages.GoToLoginPage();

    }
    @When("Enter invalid email and password")
    public void enter_invalid_email_and_password() {
        LoginPages.EnterInvalidEmailandPassword();



    }
    @Then("An error message must be displayed")
    public void an_error_message_must_be_displayed() {
        LoginPages.ErrorMessageVerify();

    }

    @When("Enter valid email and password")
    public void enter_valid_email_and_password() {
        LoginPages.EnterValidEmailandPassword();

    }

    @Then("Verify the login was succesful")
    public void verifyTheLoginWasSuccesful() {
        LoginPages.SuccessfulLoginVerify();


    }
}
