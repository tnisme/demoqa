package demoqa.page.method;

import demoqa.page.locator.DashboardLocator;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends DashboardLocator {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public BookStoreApplicationPage goToBookStoreApplication() {
        getWaitUtility().waitUntilToBeClickAble(weBookStoreApplication);
        weBookStoreApplication.click();
        getWaitUtility().waitForPageLoad();
        return new BookStoreApplicationPage(driver);
    }
}
