package demoqa.page.method;

import demoqa.page.locator.LoginLocator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends LoginLocator {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

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

    public boolean isErrorMsgDisplayed() {
        try {
            getWaitUtility().waitUntilVisibilityOf(weErrorMsg, 5);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
