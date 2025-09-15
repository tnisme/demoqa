package demoqa.factories;

import demoqa.page.BookStoreApplicationPage;
import demoqa.page.DashboardPage;
import demoqa.page.LoginPage;
import demoqa.page.ProfilePage;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    private PageFactory() {
        // private constructor to prevent instantiation
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void init(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static ProfilePage profilePage() {
        return new ProfilePage(driver.get());
    }

    public static LoginPage loginPage() {
        return new LoginPage(driver.get());
    }

    public static DashboardPage dashboardPage() {
        return new DashboardPage(driver.get());
    }

    public static BookStoreApplicationPage bookStoreApplicationPage() {
        return new BookStoreApplicationPage(driver.get());
    }

    public static void clear() {
        driver.remove();
    }
}
