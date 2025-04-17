package demoqa.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtility {
    private final WebDriver driver;
    private Actions actions;
    private JavascriptExecutor js;

    public ActionUtility(WebDriver webDriver) {
        this.driver = webDriver;
        init();
    }

    private void init() {
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public void mouseMove(WebElement webElement) {
        actions.moveToElement(webElement).perform();
    }

    public void scrollToElement(WebElement ele) {
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }
}
