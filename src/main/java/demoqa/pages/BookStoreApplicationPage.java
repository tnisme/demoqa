package demoqa.pages;

import demoqa.components.Button;
import demoqa.factories.PageFactory;
import org.openqa.selenium.By;

public class BookStoreApplicationPage extends BasePage {

    // region locators
    private final By login = By.xpath("//span[text()='Login']/ancestor::li");
    // endregion

    // region methods
    public LoginPage goToLoginPage() {
        Button.of(login).clickButton();
        return PageFactory.create(LoginPage.class);
    }
    // endregion
}
