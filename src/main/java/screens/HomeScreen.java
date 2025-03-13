package screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScrollHelper;

import java.time.Duration;

public class HomeScreen {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    // Locators
    private final By countryDropdown = By.id("com.androidsample.generalstore:id/spinnerCountry");
    private final By egyptCountry = By.xpath("//android.widget.TextView[@text='Egypt']");
    private final By nameField = By.id("com.androidsample.generalstore:id/nameField");
    private final By maleGender = By.id("com.androidsample.generalstore:id/radioMale");
    private final By letsShopButton = By.id("com.androidsample.generalstore:id/btnLetsShop");

    public HomeScreen(AppiumDriver appiumDriver) {
        if (appiumDriver == null) {
            throw new IllegalArgumentException("AppiumDriver cannot be null");
        }
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(30)); // Initialize WebDriverWait
    }

    public void selectCountry(String country) {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();
        ScrollHelper.scrollToElement(appiumDriver, egyptCountry);
        wait.until(ExpectedConditions.elementToBeClickable(egyptCountry)).click();
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).sendKeys(name);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            wait.until(ExpectedConditions.elementToBeClickable(maleGender)).click();
        }
    }

    public void clickLetsShop() {
        wait.until(ExpectedConditions.elementToBeClickable(letsShopButton)).click();
    }
}