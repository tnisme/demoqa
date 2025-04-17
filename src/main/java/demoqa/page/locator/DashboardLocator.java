package demoqa.page.locator;

import demoqa.factory.SeleniumFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardLocator extends SeleniumFactory {
    public DashboardLocator(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//h5[text()='Book Store Application']/ancestor::div[@class[contains(., 'top-card')]]")
    protected WebElement weBookStoreApplication;
}
