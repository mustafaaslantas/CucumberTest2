package com.mustafaaslantas.pages;

import com.mustafaaslantas.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CartPages {
    static WebDriver driver;
    static ElementHelper elementHelper;
    static WebDriverWait wait;
    static String selectedBookTitle;
    public CartPages(WebDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void GoHomePage() {
        driver.get("https://www.kitapyurdu.com");
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cookiescript_accept']")));
        acceptCookiesButton.click();
    }

    public static void LiteratureCategory() {
        WebElement categoriesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tüm Kategoriler')]")));
        categoriesLink.click();

        WebElement edebiyat = driver.findElement(By.xpath("//h2[.='Edebiyat']"));
        edebiyat.click();
    }

    public static void RandomBook() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("product-cr")));
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        WebElement selectedBook = elements.get(randomIndex);

        selectedBookTitle = selectedBook.getText();
        selectedBook.click();
        WebElement addToCartButton = driver.findElement(By.xpath("//span[text()='Sepete Ekle']"));
        addToCartButton.click();
        driver.navigate().refresh();
    }

    public static void VerifyTheBook() {
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='cart-items']")));
        cartButton.click();
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("js-cart")));
        cartLink.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement productNameCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='name']//a[@class='alt']")));
        String cartProductName = productNameCell.getText().trim();

        if (selectedBookTitle.contains(cartProductName)) {
            System.out.println("Sepetteki ürünle seçilen ürün aynı.");
        } else {
            System.out.println("Sepetteki ürünle seçilen ürün aynı değil.");
        }
    }

    public static void RemoveItem() {
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-times red-icon' and @title='Kaldır']")));
        removeButton.click();
    }

    public static void IsEmpty() {
        WebElement empty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sepetinizden çıkarılıyor!')]")));
        if (empty.isDisplayed()) {
            System.out.println("Sepet boş.");
        } else System.out.println("Sepet Dolu");
        driver.quit();
    }
}
