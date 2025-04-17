package demoqa.page.locator;

import demoqa.factory.SeleniumFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BookStoreApplicationLocator extends SeleniumFactory {
    public BookStoreApplicationLocator(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "//span[text()='Login']/ancestor::li")
    protected WebElement weLogin;
}
