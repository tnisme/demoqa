package demoqa.page.method;

import demoqa.page.locator.BookStoreApplicationLocator;
import org.openqa.selenium.WebDriver;

public class BookStoreApplicationPage extends BookStoreApplicationLocator {
    public BookStoreApplicationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        getWaitUtility().waitUntilToBeClickAble(weLogin);
        weLogin.click();
        return new LoginPage(driver);
    }
}
