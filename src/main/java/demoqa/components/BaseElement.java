package demoqa.components;

import demoqa.factories.UtilityManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Abstract base class for all web elements in the framework.
 * Provides common functionality and locator-based element interaction.
 */
public class BaseElement {
    /**
     * The locator strategy used to find this element
     */
    protected By locator;

    protected BaseElement(By locator) {
        this.locator = locator;
    }

    protected WebElement find() {
        return UtilityManager.waitUtil().waitUntilVisibilityOf(locator);
    }

    public void click() {
        find().click();
    }

    public boolean isDisplayed() {
        return find().isDisplayed();
    }

    public boolean isDisplayed(int timeoutInSeconds) {
        try {
            UtilityManager.waitUtil().waitUntilVisibilityOf(locator, timeoutInSeconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
