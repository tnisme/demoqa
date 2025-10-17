package demoqa.components;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.factories.UtilityManager;
import org.openqa.selenium.By;

public class Button extends BaseElement {

    public Button(By locator) {
        super(locator);
    }

    public static Button of(By locator) {
        return new Button(locator);
    }

    public void clickButton() {
        UtilityManager.actionUtil().scrollToElement(find());
        find().click();
        UtilityManager.reportUtil().log(LogStatus.INFO, "Clicked on button: " + locator);
    }
}
