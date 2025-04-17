package demoqa.page.method;

import demoqa.page.locator.LoginLocator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends LoginLocator {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillInUsername(String username) {
        weUsername.clear();
        weUsername.sendKeys(username);
    }

    public void fillInPassword(String password) {
        wePassword.clear();
        wePassword.sendKeys(password);
    }

    public void clickLogin() {
        getWaitUtility().waitUntilToBeClickAble(weLogin);
        getActionUtility().scrollToElement(weLogin);
        weLogin.click();
    }

    public void performLogin(String username, String password) {
        fillInUsername(username);
        fillInPassword(password);
        clickLogin();
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
