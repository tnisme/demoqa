package demoqa.page;

import demoqa.factories.ElementFactory;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // region locators
    private final By bookStoreApplication = By.xpath("//h5[text()='Book Store Application']/ancestor::div[@class[contains(., 'top-card')]]");
    // endregion

    // region methods
    public BookStoreApplicationPage goToBookStoreApplication() {
        ElementFactory.button(bookStoreApplication).clickButton();
        UtilityFactory.waitUtil().waitForPageLoad();
        return PageFactory.bookStoreApplicationPage();
    }
    // endregion
}
