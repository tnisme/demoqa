package demoqa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // region locators
    @FindBy(how = How.XPATH, using = "//h5[text()='Book Store Application']/ancestor::div[@class[contains(., 'top-card')]]")
    private WebElement weBookStoreApplication;
    // endregion

    // region methods
    public BookStoreApplicationPage goToBookStoreApplication() {
        getWaitUtility().waitUntilToBeClickAble(weBookStoreApplication);
        getActionUtility().scrollToElement(weBookStoreApplication);
        weBookStoreApplication.click();
        getWaitUtility().waitForPageLoad();
        return new BookStoreApplicationPage(driver);
    }
    // endregion
}
