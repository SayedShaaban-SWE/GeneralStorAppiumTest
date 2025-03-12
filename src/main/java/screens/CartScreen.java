package screens;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartScreen {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    // Locators
    private final By productNames = By.id("com.androidsample.generalstore:id/productName");
    private final By productPrices = By.id("com.androidsample.generalstore:id/productPrice");
    private final By totalAmount = By.id("com.androidsample.generalstore:id/totalAmountLbl");

    public CartScreen(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30)); // Initialize WebDriverWait
    }

    public boolean areProductsDisplayed() {
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
        return products.size() == 2;
    }

    public double getTotalAmount() {
        WebElement totalAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount));
        return Double.parseDouble(totalAmountElement.getText().replace("$", ""));
    }

    public double getSumOfProductPrices() {
        List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices));
        return prices.stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().replace("$", "")))
                .sum();
    }
}
