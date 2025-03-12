package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    private static AppiumDriver appiumDriver;

    public AppiumDriver getAppiumDriver(){
        return appiumDriver;
    }

    public static void android_SetUp() throws MalformedURLException {
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","9.0");
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("app",System.getProperty("user.dir")+"/apps/ToDo_1.24_Apkpure.apk");

        appiumDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }

    public static void ios_SetUp() throws MalformedURLException {
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("automationName","XCUITest");
        caps.setCapability("platformName","ios");
        caps.setCapability("platformVersion","14.2");
        caps.setCapability("deviceName","iPhone 12 mini");
        caps.setCapability("app",System.getProperty("user.dir")+"/apps/app.zip");

        appiumDriver = new IOSDriver(new URL("http://localhost:4723/wd/hub"),caps);
    }

    public void teardown(){
        if (appiumDriver!=null){
            appiumDriver.quit();
        }
    }
}
