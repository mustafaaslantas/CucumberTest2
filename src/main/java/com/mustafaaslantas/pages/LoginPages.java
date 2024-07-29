package com.mustafaaslantas.pages;

import com.mustafaaslantas.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPages {

    static WebDriver driver;
    static ElementHelper elementHelper;
    static WebDriverWait wait;
    private static By errorMessage = By.className("ky-form-messages");
    public LoginPages(WebDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void GoToLoginPage() {
        driver.get("https://www.kitapyurdu.com");
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cookiescript_accept']")));
        acceptCookiesButton.click();
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Giriş Yap')]")));
        loginLink.click();
    }

    public static void EnterInvalidEmailandPassword() {
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-email")));
        WebElement passwordField = driver.findElement(By.id("login-password"));

        usernameField.sendKeys("email");
        passwordField.sendKeys("password");
        passwordField.sendKeys(Keys.RETURN);
    }

    public static void ErrorMessageVerify() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        if (driver.findElements(errorMessage).size() > 0) {
            System.out.println("Hata mesajı başarıyla gösterildi");
        }
        else System.out.println("Hata mesajı gösterilemedi");
        driver.quit();
    }

    public static void EnterValidEmailandPassword() {

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-email")));
        WebElement passwordField = driver.findElement(By.id("login-password"));

        usernameField.sendKeys("realEMail");
        passwordField.sendKeys("realPassword");
        passwordField.sendKeys(Keys.RETURN);
    }

    public static void SuccessfulLoginVerify() {
        WebElement accountText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='section']")));
        if (accountText.isDisplayed()) {
            System.out.println("Başarıyla giriş yapıldı.");
        } else {
            System.out.println("Giriş başarısız.");
        }
        driver.quit();
    }
}
