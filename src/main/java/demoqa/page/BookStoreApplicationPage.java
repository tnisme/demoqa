package demoqa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BookStoreApplicationPage extends BasePage {

    public BookStoreApplicationPage(WebDriver driver) {
        super(driver);
    }

    // region locators
    @FindBy(how = How.XPATH, using = "//span[text()='Login']/ancestor::li")
    protected WebElement weLogin;
    // endregion

    // region methods
    public LoginPage goToLoginPage() {
        getWaitUtility().waitUntilToBeClickAble(weLogin);
        weLogin.click();
        return new LoginPage(driver);
    }
    // endregion
}
