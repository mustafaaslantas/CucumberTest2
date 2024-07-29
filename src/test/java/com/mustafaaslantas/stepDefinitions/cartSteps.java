package com.mustafaaslantas.stepDefinitions;

import com.mustafaaslantas.pages.CartPages;
import com.mustafaaslantas.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class cartSteps {

    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait;
    CartPages cartPages = new CartPages(driver);

    public cartSteps() {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Given("Go to homepage")
    public void go_to_homepage() {
        CartPages.GoHomePage();

    }

    @When("Navigate to literature category")
    public void navigate_to_literature_category() {
        CartPages.LiteratureCategory();

    }

    @When("Add a random book to the cart")
    public void add_a_random_book_to_the_cart() {
        CartPages.RandomBook();


    }

    @Then("The book in the cart must match the selected book")
    public void the_book_in_the_cart_must_match_the_selected_book() {
        CartPages.VerifyTheBook();

    }
    @And("Remove the item from the cart")
    public void remove_the_item_from_the_cart() {
        CartPages.RemoveItem();

    }

    @Then("The cart must be empty")
    public void the_cart_must_be_empty() {
        CartPages.IsEmpty();

    }
}

