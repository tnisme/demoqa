package demoqa.pages;

import demoqa.components.Button;
import demoqa.components.Input;
import demoqa.components.Label;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityManager;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // region locators
    private By usernameField = By.id("userName");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    private By errorMsg = By.xpath("//p[text()='Invalid username or password!']");
    // endregion

    // region methods
    /**
     * Fills in the username field
     *
     * @param username the username
     * @return the LoginPage object
     */
    public LoginPage fillInUsername(String username) {
        Input.of(usernameField).clearBefore().type(username).perform();
        return this;
    }

    /**
     * Fills in the password field
     *
     * @param password the password
     * @return the LoginPage object
     */
    public LoginPage fillInPassword(String password) {
        Input.of(passwordField).clearBefore().type(password).perform();
        return this;
    }

    /**
     * Clicks the login button
     *
     * @return the LoginPage object
     */
    public LoginPage clickLogin() {
        Button.of(loginButton).clickButton();
        return this;
    }

    /**
     * Performs the login action
     *
     * @param username the username
     * @param password the password
     * @return the LoginPage object
     */
    public LoginPage performLogin(String username, String password) {
        fillInUsername(username).fillInPassword(password).clickLogin();
        return this;
    }

    /**
     * Logs in and navigates to the ProfilePage
     *
     * @param username the username
     * @param password the password
     * @return the ProfilePage object
     */
    public ProfilePage login(String username, String password) {
        performLogin(username, password);
        return PageFactory.create(ProfilePage.class);
    }

    /**
     * Checks if the error message is displayed
     *
     * @return the LoginPage object
     */
    public LoginPage checkErrorMsgDisplayed() {
        UtilityManager.assertUtil().assertTrue(isErrorMsgDisplayed(), "Check error message");
        return this;
    }

    /**
     * Checks if the error message is displayed
     *
     * @return true if the error message is displayed, false otherwise
     */
    private boolean isErrorMsgDisplayed() {
        return Label.of(errorMsg).isDisplayed(5);
    }
    // endregion
}
