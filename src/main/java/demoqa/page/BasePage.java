package demoqa.page;

import demoqa.factories.ElementFactory;
import org.openqa.selenium.WebDriver;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        initComponents();
    }

    private void initComponents() {
        ElementFactory.init(driver);
    }
}
