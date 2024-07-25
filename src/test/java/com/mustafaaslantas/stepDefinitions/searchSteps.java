package com.mustafaaslantas.stepDefinitions;

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

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait;
    public searchSteps() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("Go to home page")
    public void go_to_home_page() {
        driver.get("https://www.kitapyurdu.com");
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cookiescript_accept']")));
        acceptCookiesButton.click();

    }
    @When("Search for Orhan Pamuk")
    public void search_for_Orhan_Pamuk() {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-input")));
        searchBox.sendKeys("Orhan Pamuk");
        searchBox.sendKeys(Keys.RETURN);
    }
    @Then("The results must be relevant to Orhan Pamuk")
    public void the_results_must_be_relevant_to_Orhan_Pamuk() {
        String searchTerm = "Orhan Pamuk";
        List<WebElement> productNames = driver.findElements(By.cssSelector("mg-b-10"));

        boolean allProductsRelated = true;
        for (WebElement productName : productNames) {
            String nameText = productName.getText();
            System.out.println("Ürün İsmi: " + nameText);
            if (!nameText.toLowerCase().contains(searchTerm.toLowerCase())) {
                allProductsRelated = false;
                break;
            }
        }
        if (allProductsRelated) {
            System.out.println("Tüm ürünler arama terimi ile ilgili.");
        } else {
            System.out.println("Bazı ürünler arama terimi ile ilgili değil.");
        }
        driver.quit();
    }

}
