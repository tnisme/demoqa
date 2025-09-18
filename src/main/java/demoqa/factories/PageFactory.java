package demoqa.factories;

import demoqa.page.BookStoreApplicationPage;
import demoqa.page.DashboardPage;
import demoqa.page.LoginPage;
import demoqa.page.ProfilePage;

public class PageFactory {

    private PageFactory() {
        // private constructor to prevent instantiation
    }

    public static ProfilePage profilePage() {
        return new ProfilePage();
    }

    public static LoginPage loginPage() {
        return new LoginPage();
    }

    public static DashboardPage dashboardPage() {
        return new DashboardPage();
    }

    public static BookStoreApplicationPage bookStoreApplicationPage() {
        return new BookStoreApplicationPage();
    }
}
