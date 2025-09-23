package demoqa.factories;

import demoqa.pages.BookStoreApplicationPage;
import demoqa.pages.DashboardPage;
import demoqa.pages.LoginPage;
import demoqa.pages.ProfilePage;

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
