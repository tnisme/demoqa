package demoqa.factories;

import demoqa.components.Button;
import demoqa.components.Input;
import demoqa.components.Label;
import org.openqa.selenium.By;

/**
 * Factory class for creating web element instances.
 * Implements the Factory Method pattern to create different types of web elements.
 */
public class ElementFactory {

    private ElementFactory() {
        // private constructor to prevent instantiation
    }

    public static Input input(By locator) {
        return new Input(locator);
    }

    public static Button button(By locator) {
        return new Button(locator);
    }

    public static Label label(By locator) {
        return new Label(locator);
    }
}
