package demoqa.pages;

import demoqa.components.Button;
import demoqa.components.Label;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityManager;
import org.openqa.selenium.By;

public class ProfilePage extends BasePage {

    // region locators
    private By username = By.id("userName-value");
    private By logout = By.xpath("//button[text()='Log out']");
    // endregion

    // region methods
    public ProfilePage checkUsername(String usernameExpected) {
        UtilityManager.assertUtil().assertEquals(usernameExpected, Label.of(username).getText(),"Check username");
        return this;
    }

    public LoginPage logout() {
        Button.of(logout).clickButton();
        return PageFactory.create(LoginPage.class);
    }
    // endregion
}
