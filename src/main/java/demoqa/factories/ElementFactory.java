package demoqa.factories;

import demoqa.components.Button;
import demoqa.components.Input;
import demoqa.components.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementFactory {

    private ElementFactory() {
        // private constructor to prevent instantiation
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void init(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static Input input(By locator) {
        return new Input(driver.get(), locator);
    }

    public static Button button(By locator) {
        return new Button(driver.get(), locator);
    }

    public static Label label(By locator) {
        return new Label(driver.get(), locator);
    }

    public static void clear() {
        driver.remove();
    }
}
