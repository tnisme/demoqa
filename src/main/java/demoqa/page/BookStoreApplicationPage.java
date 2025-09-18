package demoqa.page;

import demoqa.factories.ElementFactory;
import demoqa.factories.PageFactory;
import org.openqa.selenium.By;

public class BookStoreApplicationPage extends BasePage {

    // region locators
    private final By login = By.xpath("//span[text()='Login']/ancestor::li");
    // endregion

    // region methods
    public LoginPage goToLoginPage() {
        ElementFactory.button(login).clickButton();
        return PageFactory.loginPage();
    }
    // endregion
}
