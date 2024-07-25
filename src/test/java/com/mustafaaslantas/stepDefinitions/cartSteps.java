package com.mustafaaslantas.stepDefinitions;

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
    String selectedBookTitle;
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait;


    public cartSteps() {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Given("Go to homepage")
    public void go_to_homepage() {
        driver.get("https://www.kitapyurdu.com");
        WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cookiescript_accept']")));
        acceptCookiesButton.click();
    }

    @When("Navigate to literature category")
    public void navigate_to_literature_category() {
        WebElement categoriesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tüm Kategoriler')]")));
        categoriesLink.click();

        WebElement edebiyat = driver.findElement(By.xpath("//h2[.='Edebiyat']"));
        edebiyat.click();
    }

    @When("Add a random book to the cart")
    public void add_a_random_book_to_the_cart() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("product-cr")));
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        WebElement selectedBook = elements.get(randomIndex);

        this.selectedBookTitle = selectedBook.getText();
        selectedBook.click();
        WebElement addToCartButton = driver.findElement(By.xpath("//span[text()='Sepete Ekle']"));
        addToCartButton.click();
        driver.navigate().refresh();

    }

    @Then("The book in the cart must match the selected book")
    public void the_book_in_the_cart_must_match_the_selected_book() {
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
    @And("Remove the item from the cart")
    public void remove_the_item_from_the_cart() {
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-times red-icon' and @title='Kaldır']")));
        removeButton.click();
    }

    @Then("The cart must be empty")
    public void the_cart_must_be_empty() {
        WebElement empty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sepetinizden çıkarılıyor!')]")));
        if (empty.isDisplayed()) {
            System.out.println("Sepet boş.");
        } else System.out.println("Sepet Dolu");
        driver.quit();
    }
}

