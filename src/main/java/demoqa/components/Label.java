package demoqa.components;

import org.openqa.selenium.By;

public class Label extends BaseElement{

    public Label(By locator) {
        super(locator);
    }

    public String getText() {
        return find().getText();
    }
}
