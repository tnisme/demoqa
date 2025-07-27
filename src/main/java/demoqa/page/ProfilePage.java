package demoqa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import demoqa.utility.AssertUtility;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // region locators
    @FindBy(how = How.ID, using = "userName-value")
    private WebElement weUserName;
    @FindBy(how = How.XPATH, using = "//button[text()='Log out']")
    private WebElement weLogout;
    // endregion

    // region methods
    public ProfilePage checkUsername(String username) {
        AssertUtility.assertEquals(username, weUserName.getText(),"Check username");
        return this;
    }

    public LoginPage logout() {
        getWaitUtility().waitUntilToBeClickAble(weLogout);
        weLogout.click();
        return new LoginPage(driver);
    }
    // endregion
}
