package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.URL;

public class Hooks {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    @Before
    public static AppiumDriver getDriver(String platform) {
        if (driver.get() == null) {
            try {
                if (platform.equalsIgnoreCase("android")) {
                    UiAutomator2Options options = new UiAutomator2Options()
                            .setDeviceName(ConfigManager.getProperty("android.deviceName"))
                            .setApp(ConfigManager.getProperty("android.appPath"))
                            .setPlatformName(ConfigManager.getProperty("android.platformName"))
                            .setAutomationName(ConfigManager.getProperty("android.automationName"));
                    driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723"), options));

                } else if (platform.equalsIgnoreCase("ios")) {
                    XCUITestOptions options = new XCUITestOptions()
                            .setDeviceName(ConfigManager.getProperty("ios.deviceName"))
                            .setApp(ConfigManager.getProperty("ios.appPath"))
                            .setPlatformName(ConfigManager.getProperty("ios.platformName"))
                            .setAutomationName(ConfigManager.getProperty("ios.automationName"))
                            .setUdid(ConfigManager.getProperty("ios.udid"));
                    driver.set(new IOSDriver(new URL("http://127.0.0.1:4723"), options));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    @After
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}