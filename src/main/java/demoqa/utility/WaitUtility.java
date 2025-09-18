package demoqa.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Provides utility methods for explicit waits in test automation.
 * Wraps Selenium's WebDriverWait with additional convenience methods.
 */
public class WaitUtility {
    private static final int TIMEOUT_INTERVAL_UNIT = 30;
    private WebDriver driver;
    private JavascriptExecutor js;

    public WaitUtility(WebDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        js = ((JavascriptExecutor) driver);
    }

    /**
     * Waits until the element is invisible. Note that this function is not doing what its name is saying.
     * It is actually waiting for the element to be visible, not invisible.
     *
     * @param by the locator of the element
     * @return the element
     */
    public WebElement waitForInvisibilityOf(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_INTERVAL_UNIT)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits until the element is clickable
     *
     * @param by the locator of the element
     * @return the element
     */
    public WebElement waitUntilToBeClickAble(By by) {
        return waitUntilToBeClickAble(by, TIMEOUT_INTERVAL_UNIT);
    }

    /**
     * Waits until the element is clickable
     *
     * @param by       the locator of the element
     * @param timeOutSecond Maximum time to wait in seconds
     * @return the element
     */
    public WebElement waitUntilToBeClickAble(By by, int timeOutSecond) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutSecond)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits until the element is present
     *
     * @param by the By object
     * @return the element
     */
    public WebElement waitForPresentOf(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_INTERVAL_UNIT)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for the page to fully load.
     * This method uses the default timeout interval.
     */
    public void waitForPageLoad() {
        waitForPageLoad(TIMEOUT_INTERVAL_UNIT);
    }

    /**
     * Waits for the page to fully load.
     *
     * @param timeout Maximum time to wait in seconds
     * @throws JavascriptException if there is an error executing JavaScript
     */
    public void waitForPageLoad(int timeout) throws JavascriptException {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until((ExpectedCondition<Boolean>) d -> js
                .executeScript("return document.readyState").toString().equals("complete"));
    }

    /**
     * Waits until the element located by the given By locator is visible
     *
     * @param by The locator of the element to wait for
     * @throws TimeoutException if the timeout is reached before the element becomes visible
     */
    public WebElement waitUntilVisibilityOf(By by) throws TimeoutException {
        return waitUntilVisibilityOf(by, TIMEOUT_INTERVAL_UNIT);
    }

    /**
     * Waits for an element to be visible on the page.
     *
     * @param by The locator of the element to wait for
     * @param timeoutInSeconds Maximum time to wait in seconds
     * @return The WebElement once it is visible
     * @throws TimeoutException if the element is not visible within the timeout
     */
    public WebElement waitUntilVisibilityOf(By by, int timeoutInSeconds) throws TimeoutException {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Pauses the execution for a specified time.
     *
     * @param time the time in milliseconds to sleep
     */
    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
