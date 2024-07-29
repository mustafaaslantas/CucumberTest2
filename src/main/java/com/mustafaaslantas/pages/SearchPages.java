package com.mustafaaslantas.pages;

import com.mustafaaslantas.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPages {
    static WebDriver driver;
    static ElementHelper elementHelper;
    static WebDriverWait wait;
    public SearchPages(WebDriver driver) {

        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void GoHomePage() {
        driver.get("https://www.kitapyurdu.com");
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cookiescript_accept']")));
        acceptCookiesButton.click();
    }

    public static void OrhanPamuk() {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-input")));
        searchBox.sendKeys("Orhan Pamuk");
        searchBox.sendKeys(Keys.RETURN);
    }

    public static void Results() {
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
