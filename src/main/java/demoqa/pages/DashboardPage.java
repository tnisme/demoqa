package demoqa.pages;

import demoqa.components.Button;
import demoqa.exceptions.NotOpenDashBoardPageException;
import demoqa.factories.*;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

    private static final String URL = DataFactory.getHomeURL();

    // region locators
    private final By bookStoreApplication = By.xpath("//h5[text()='Book Store Application']/ancestor::div[@class[contains(., 'top-card')]]");
    // endregion

    // region methods

    /**
     * Opens the Dashboard page.
     * Must be called before any other methods in this class.
     *
     * @return the current DashboardPage instance for method chaining
     */
    public DashboardPage open() {
        DriverManager.getDriver().get(URL);
        return this;
    }


    public BookStoreApplicationPage goToBookStoreApplication() {
        if (!DriverManager.getDriver().getCurrentUrl().equals(URL)) {
            throw new NotOpenDashBoardPageException("LoginPage is not opened. Call open() before using goToBookStoreApplication().");
        }
        Button.of(bookStoreApplication).clickButton();
        return PageFactory.create(BookStoreApplicationPage.class);
    }
    // endregion
}
