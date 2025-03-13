package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;

public class AppiumDriverSetup {
    private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();

    public static AppiumDriver getDriver(String platform) {
        if (appiumDriver.get() == null) {
            try {
                if (platform.equalsIgnoreCase("android")) {
                    UiAutomator2Options options = new UiAutomator2Options()
                            .setDeviceName(ConfigManager.getProperty("android.deviceName"))
                            .setPlatformVersion(ConfigManager.getProperty("android.platformVersion"))
                            .setApp(System.getProperty("user.dir") + ConfigManager.getProperty("android.appPath"))
                            .setPlatformName(ConfigManager.getProperty("android.platformName"))
                            .setAutomationName(ConfigManager.getProperty("android.automationName"));
                    appiumDriver.set(new AndroidDriver(new URL("http://127.0.0.1:4723"), options));
                    Logger.info("Android driver initialized: " + appiumDriver.get());

                } else if (platform.equalsIgnoreCase("ios")) {
                    XCUITestOptions options = new XCUITestOptions()
                            .setDeviceName(ConfigManager.getProperty("ios.deviceName"))
                            .setApp(System.getProperty("user.dir") + ConfigManager.getProperty("ios.appPath"))
                            .setPlatformName(ConfigManager.getProperty("ios.platformName"))
                            .setAutomationName(ConfigManager.getProperty("ios.automationName"))
                            .setUdid(ConfigManager.getProperty("ios.udid"));
                    appiumDriver.set(new IOSDriver(new URL("http://127.0.0.1:4723"), options));
                    Logger.info("iOS driver initialized: " + appiumDriver.get());
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize Appium driver for platform: " + platform, e);
            }
        }
        if (appiumDriver.get() == null) {
            throw new IllegalStateException("Appium driver is null after initialization attempt for platform: " + platform);
        }
        return appiumDriver.get();
    }

    public static void quitDriver() {
        if (appiumDriver.get() != null) {
            appiumDriver.get().quit();
            appiumDriver.remove();
            Logger.info("Driver quit and removed from ThreadLocal");
        }
    }
}