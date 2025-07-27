package function;

import base.BaseTest;
import demoqa.entity.Account;
import demoqa.page.BookStoreApplicationPage;
import demoqa.page.LoginPage;
import demoqa.page.ProfilePage;
import demoqa.utility.DataTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private Account account, randomAccount;
    private LoginPage loginPage;
    private BookStoreApplicationPage bookStoreApplicationPage;
    private ProfilePage profilePage;

    @Override
    public void preCondition() {
        account = DataTest.getAdminAccount();
        randomAccount = DataTest.getRandomAccount();
    }

    @Test
    public void loginWithValidAccount() {
        bookStoreApplicationPage = getDashboardPage().goToBookStoreApplication();
        loginPage = bookStoreApplicationPage.goToLoginPage();
        profilePage = loginPage.login(account.getUsername(), account.getPassword())
                .checkUsername(account.getUsername());
    }

    @Test(dependsOnMethods = "loginWithValidAccount")
    public void loginValidAccountTest() {
        loginPage = profilePage.logout();
        loginPage.performLogin(randomAccount.getUsername(), randomAccount.getPassword())
                .checkErrorMsgDisplayed();
    }
}
