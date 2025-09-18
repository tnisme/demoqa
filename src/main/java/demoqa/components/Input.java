package demoqa.components;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.factories.UtilityFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Input extends BaseElement {

    private boolean shouldClear = false;
    private final StringBuilder textBuilder = new StringBuilder();

    public Input(By locator) {
        super(locator);
    }

    public Input clearBefore() {
        this.shouldClear = true;
        return this;
    }

    public Input type(String text) {
        textBuilder.append(text);
        return this;
    }

    public Input tab() {
        textBuilder.append(Keys.TAB);
        return this;
    }

    public Input pressEnter() {
        textBuilder.append(Keys.ENTER);
        return this;
    }

    public void perform() {
        WebElement element = find();
        if (shouldClear) {
            element.clear();
            UtilityFactory.reportUtil().log(LogStatus.INFO, "Cleared input field" + locator);
        }
        element.sendKeys(textBuilder.toString());
        UtilityFactory.reportUtil().log(LogStatus.INFO, "Typed text: " + textBuilder);
    }
}
