package stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import utils.AppiumDriverSetup;
import utils.ConfigManager;
import utils.Logger;

public class Hooks {

    private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();

    @BeforeAll
    public static void beforeAll() {
        // Setup logic that runs once before all scenarios
        Logger.info("Before All: Initializing test suite...");
    }

    @Before
    public void setupDriver(Scenario scenario) {
        // Setup logic that runs before each scenario
        Logger.info("Before Scenario: " + scenario.getName());
        String platform = System.getProperty("platform", ConfigManager.getProperty("android.platformName")); // Default to Android
        appiumDriver.set(AppiumDriverSetup.getDriver(platform));
    }

    public static AppiumDriver getAppiumDriver() {
        return appiumDriver.get();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Teardown logic that runs after each scenario
        Logger.info("After Scenario: " + scenario.getName());
        if (appiumDriver.get() != null) {
            AppiumDriverSetup.quitDriver();
            appiumDriver.remove(); // Clean up the ThreadLocal instance
        }
    }

    @AfterAll
    public static void afterAll() {
        // Teardown logic that runs once after all scenarios
        Logger.info("After All: Cleaning up test suite...");
        if (appiumDriver.get() != null) {
            AppiumDriverSetup.quitDriver();
            appiumDriver.remove(); // Clean up the ThreadLocal instance
        }
    }
}