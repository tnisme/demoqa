package demoqa.page;

import demoqa.utility.AssertUtility;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // region locators
    @FindBy(how = How.ID, using = "userName")
    private WebElement weUsername;
    @FindBy(how = How.ID, using = "password")
    private WebElement wePassword;
    @FindBy(how = How.ID, using = "login")
    private WebElement weLogin;
    @FindBy(how = How.XPATH, using = "//p[text()='Invalid username or password!']")
    private WebElement weErrorMsg;
    // endregion

    // region methods
    public LoginPage fillInUsername(String username) {
        weUsername.clear();
        weUsername.sendKeys(username);
        return this;
    }

    public LoginPage fillInPassword(String password) {
        wePassword.clear();
        wePassword.sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        getWaitUtility().waitUntilToBeClickAble(weLogin);
        getActionUtility().scrollToElement(weLogin);
        weLogin.click();
        return this;
    }

    public LoginPage performLogin(String username, String password) {
        fillInUsername(username).fillInPassword(password).clickLogin();
        return this;
    }

    public ProfilePage login(String username, String password) {
        performLogin(username, password);
        return new ProfilePage(driver);
    }

    public LoginPage checkErrorMsgDisplayed() {
        AssertUtility.assertTrue(isErrorMsgDisplayed(), "Check error message");
        return this;
    }

    private boolean isErrorMsgDisplayed() {
        try {
            getWaitUtility().waitUntilVisibilityOf(weErrorMsg, 5);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    // endregion
}
