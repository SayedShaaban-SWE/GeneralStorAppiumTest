package screens;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductsScreen {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    // Locators
    private final By firstProduct = By.xpath("(//*[@resource-id='com.androidsample.generalstore:id/productAddCart'])[1]");
    private final By secondProduct = By.xpath("(//*[@resource-id='com.androidsample.generalstore:id/productAddCart'])[2]");
    private final By cartButton = By.id("com.androidsample.generalstore:id/appbar_btn_cart");

    public ProductsScreen(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30)); // Initialize WebDriverWait
    }

    public void addProductsToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        wait.until(ExpectedConditions.elementToBeClickable(secondProduct)).click();
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }
}