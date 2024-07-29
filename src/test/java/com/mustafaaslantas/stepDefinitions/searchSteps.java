package com.mustafaaslantas.stepDefinitions;

import com.mustafaaslantas.pages.SearchPages;
import com.mustafaaslantas.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class searchSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait;
    SearchPages searchPages = new SearchPages(driver);
    public searchSteps() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("Go to home page")
    public void go_to_home_page() {
        SearchPages.GoHomePage();

    }
    @When("Search for Orhan Pamuk")
    public void search_for_Orhan_Pamuk() {
        SearchPages.OrhanPamuk();

    }
    @Then("The results must be relevant to Orhan Pamuk")
    public void the_results_must_be_relevant_to_Orhan_Pamuk() {
        SearchPages.Results();

    }

}
