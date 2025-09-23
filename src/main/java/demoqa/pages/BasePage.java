package demoqa.pages;

import demoqa.factories.DriverManager;
import org.openqa.selenium.By;

/**
 * BasePage class providing common page-level methods and navigation utilities.
 * All page classes should extend this class to inherit these functionalities.
 */
public class BasePage {

    // ---- Navigation ----
    public void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    public void refresh() {
        DriverManager.getDriver().navigate().refresh();
    }

    public void goBack() {
        DriverManager.getDriver().navigate().back();
    }

    public void goForward() {
        DriverManager.getDriver().navigate().forward();
    }

    // ---- Page-level helpers ----
    public String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public String getCurrentUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public void switchToFrame(By locator) {
        DriverManager.getDriver().switchTo().frame(DriverManager.getDriver().findElement(locator));
    }

    public void switchToDefault() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void switchToWindow(String windowHandle) {
        DriverManager.getDriver().switchTo().window(windowHandle);
    }

}
