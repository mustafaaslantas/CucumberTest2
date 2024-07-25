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

public class loginSteps {

    private By errorMessage = By.className("ky-form-messages");
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait;
    public loginSteps() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Given("Go to login page")
    public void go_to_login_page() {
        driver.get("https://www.kitapyurdu.com");
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cookiescript_accept']")));
        acceptCookiesButton.click();
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Giriş Yap')]")));
        loginLink.click();
    }
    @When("Enter invalid email and password")
    public void enter_invalid_email_and_password() {
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-email")));
        WebElement passwordField = driver.findElement(By.id("login-password"));

        usernameField.sendKeys("email");
        passwordField.sendKeys("password");
        passwordField.sendKeys(Keys.RETURN);


    }
    @Then("An error message must be displayed")
    public void an_error_message_must_be_displayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        if (driver.findElements(errorMessage).size() > 0) {
            System.out.println("Hata mesajı başarıyla gösterildi");
        }
        else System.out.println("Hata mesajı gösterilemedi");
        driver.quit();
    }

    @When("Enter valid email and password")
    public void enter_valid_email_and_password() {

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-email")));
        WebElement passwordField = driver.findElement(By.id("login-password"));

        usernameField.sendKeys("realEMail");
        passwordField.sendKeys("realPassword");
        passwordField.sendKeys(Keys.RETURN);
    }

    @Then("Verify the login was succesful")
    public void verifyTheLoginWasSuccesful() {
        WebElement accountText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='section']")));
        if (accountText.isDisplayed()) {
            System.out.println("Başarıyla giriş yapıldı.");
        } else {
            System.out.println("Giriş başarısız.");
        }
        driver.quit();

    }
}
