package demoqa.page;

import demoqa.factories.ElementFactory;
import demoqa.factories.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookStoreApplicationPage extends BasePage {

    public BookStoreApplicationPage(WebDriver driver) {
        super(driver);
    }

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
