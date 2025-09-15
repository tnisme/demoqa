package demoqa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Label extends BaseElement{

    public Label(WebDriver driver, By locator) {
        super(driver, locator);
    }

    public String getText() {
        return find().getText();
    }
}
