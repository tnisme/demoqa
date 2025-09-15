package demoqa.page;

import demoqa.factories.ElementFactory;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

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
