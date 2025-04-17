package demoqa.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility {
    private static final int TIMEOUT_INTERVAL_UNIT = 30;
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;


    public WaitUtility(WebDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_INTERVAL_UNIT));
        js = ((JavascriptExecutor) driver);
    }

    /**
     * Waits until the element is invisible. Note that this function is not doing what its name is saying.
     * It is actually waiting for the element to be visible, not invisible.
     *
     * @param element the element
     * @return the element
     */
    public WebElement waitForInvisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element is clickable
     *
     * @param element the element
     * @return the element
     */
    public WebElement waitUntilToBeClickAble(WebElement element) {
        return waitUntilToBeClickAble(element, TIMEOUT_INTERVAL_UNIT);
    }

    public WebElement waitUntilToBeClickAble(WebElement element, int timeOutSecond) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutSecond)).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element is present
     *
     * @param by the By object
     * @return the element
     */
    public WebElement waitForPresentOf(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for the page to fully load.
     * This method uses the default timeout interval.
     */

    public void waitForPageLoad() {
        waitForPageLoad(TIMEOUT_INTERVAL_UNIT);
    }

    public void waitForPageLoad(int timeout) throws JavascriptException {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_INTERVAL_UNIT)).until((ExpectedCondition<Boolean>) d -> js
                .executeScript("return document.readyState").toString().equals("complete"));
    }

    /**
     * Waits until the element is visible
     *
     * @param element the element to wait for
     * @throws TimeoutException if the timeout is reached before the element becomes visible
     */

    public void waitUntilVisibilityOf(WebElement element) throws TimeoutException {
        waitUntilVisibilityOf(element, TIMEOUT_INTERVAL_UNIT);
    }

    public WebElement waitUntilVisibilityOf(WebElement element, int timeOutSecond) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeOutSecond)).until(ExpectedConditions.visibilityOf(element));
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
