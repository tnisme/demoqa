package demoqa.factories;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
        // private constructor to prevent instantiation
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            throw new IllegalStateException("WebDriver has not been set for the current thread.");
        }
        return DRIVER.get();
    }

    public static void removeDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
