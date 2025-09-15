package demoqa.components;

import demoqa.factories.UtilityFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    protected WebDriver driver;
    protected By locator;

    protected BaseElement(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    protected WebElement find() {
        return UtilityFactory.waitUtil().waitUntilVisibilityOf(locator);
    }

    public void click() {
        find().click();
    }

    public boolean isDisplayed() {
        return find().isDisplayed();
    }

    public boolean isDisplayed(int timeoutInSeconds) {
        try {
            UtilityFactory.waitUtil().waitUntilVisibilityOf(locator, timeoutInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
