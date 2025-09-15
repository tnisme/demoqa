package demoqa.components;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.factories.UtilityFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends BaseElement {

    public Button(WebDriver driver, By locator) {
        super(driver, locator);
    }

    public void clickButton() {
        UtilityFactory.actionUtil().scrollToElement(find());
        find().click();
        UtilityFactory.reportUtil().log(LogStatus.INFO, "Clicked on button: " + locator);
    }
}
