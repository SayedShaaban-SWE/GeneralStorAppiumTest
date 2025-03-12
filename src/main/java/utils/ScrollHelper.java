package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;

public final class ScrollHelper {

    private static final int MAX_SCROLL_ATTEMPTS = 6;
    private static final double SCROLL_START_RATIO = 0.8; // Start scroll from 80% of the screen height
    private static final double SCROLL_END_RATIO = 0.3;   // Scroll to 30% of the screen height
    private static final Duration SCROLL_DURATION = Duration.ofMillis(500); // Scroll duration

    private ScrollHelper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Scrolls to the specified element until it is displayed or the maximum scroll attempts are reached.
     *
     * @param appiumDriver  The AppiumDriver instance.
     * @param locator The locator of the element to scroll to.
     */
    public static void scrollToElement(AppiumDriver appiumDriver, By locator) {
        int scrollAttempts = 0;
        while (scrollAttempts < MAX_SCROLL_ATTEMPTS) {
            if (isElementDisplayed(appiumDriver, locator)) {
                Logger.debug("Element found after {0} scroll attempts.", String.valueOf(scrollAttempts));
                return;
            }
            performScroll(appiumDriver);
            scrollAttempts++;
        }
        Logger.debug("Element not found after {0} scroll attempts.", String.valueOf(MAX_SCROLL_ATTEMPTS));
    }

    /**
     * Checks if the specified element is displayed.
     *
     * @param driver  The AppiumDriver instance.
     * @param locator The locator of the element to check.
     * @return True if the element is displayed, false otherwise.
     */
    private static boolean isElementDisplayed(AppiumDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            Logger.debug("Element not displayed: {0}", String.valueOf(locator));
            return false;
        }
    }

    /**
     * Performs a scroll action on the screen.
     *
     * @param appiumDriver The AppiumDriver instance.
     */
    private static void performScroll(AppiumDriver appiumDriver) {
        Dimension dimension = appiumDriver.manage().window().getSize();
        int startX = dimension.getWidth() / 2; // Center of the screen horizontally
        int startY = (int) (dimension.getHeight() * SCROLL_START_RATIO);
        int endY = (int) (dimension.getHeight() * SCROLL_END_RATIO);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);

        // Move finger to starting position
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        // Finger comes into contact with the screen
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Finger moves to end position
        scroll.addAction(finger.createPointerMove(SCROLL_DURATION, PointerInput.Origin.viewport(), startX, endY));
        // Finger lifts up from the screen
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the scroll action
        appiumDriver.perform(Collections.singletonList(scroll));
    }
}