package demoqa.page.locator;

import demoqa.factory.SeleniumFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginLocator extends SeleniumFactory {

    @FindBy(how = How.ID, using = "userName")
    protected WebElement weUsername;
    @FindBy(how = How.ID, using = "password")
    protected WebElement wePassword;
    @FindBy(how = How.ID, using = "login")
    protected WebElement weLogin;
    @FindBy(how = How.XPATH, using = "//p[text()='Invalid username or password!']")
    protected WebElement weErrorMsg;

    public LoginLocator(WebDriver driver) {
        super(driver);
    }
}
