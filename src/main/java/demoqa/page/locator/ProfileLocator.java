package demoqa.page.locator;

import demoqa.factory.SeleniumFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfileLocator extends SeleniumFactory {
    public ProfileLocator(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "userName-value")
    protected WebElement weUserName;
    @FindBy(how = How.XPATH, using = "//button[text()='Log out']")
    protected WebElement weLogout;
}
