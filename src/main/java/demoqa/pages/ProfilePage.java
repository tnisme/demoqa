package demoqa.pages;

import demoqa.factories.ElementFactory;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityFactory;
import org.openqa.selenium.By;

public class ProfilePage extends BasePage {

    // region locators
    private By username = By.id("userName-value");
    private By logout = By.xpath("//button[text()='Log out']");
    // endregion

    // region methods
    public ProfilePage checkUsername(String usernameExpected) {
        UtilityFactory.assertUtil().assertEquals(usernameExpected, ElementFactory.label(username).getText(),"Check username");
        return this;
    }

    public LoginPage logout() {
        ElementFactory.button(logout).clickButton();
        return PageFactory.loginPage();
    }
    // endregion
}
