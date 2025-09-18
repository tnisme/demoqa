package demoqa.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Utility class for performing advanced user interactions using Selenium WebDriver.
 * Provides methods for mouse movements and scrolling to elements.
 */
public class ActionUtility {
    private final WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    public ActionUtility(WebDriver webDriver) {
        this.driver = webDriver;
        init();
    }

    /**
     * Initializes the Actions and JavascriptExecutor instances.
     */
    private void init() {
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    /**
     * Moves the mouse to the specified web element.
     *
     * @param webElement The target web element
     */
    public void mouseMove(WebElement webElement) {
        actions.moveToElement(webElement).perform();
    }

    /**
     * Scrolls the page to bring the specified web element into view.
     *
     * @param ele The target web element
     */
    public void scrollToElement(WebElement ele) {
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }
}
