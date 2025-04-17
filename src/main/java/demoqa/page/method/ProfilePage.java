package demoqa.page.method;

import demoqa.page.locator.ProfileLocator;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends ProfileLocator {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean checkUsername(String username) {
        return weUserName.getText().equals(username);
    }

    public LoginPage logout() {
        getWaitUtility().waitUntilToBeClickAble(weLogout);
        weLogout.click();
        return new LoginPage(driver);
    }
}
