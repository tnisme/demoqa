package function;

import base.BaseTest;
import demoqa.entity.Account;
import demoqa.page.method.BookStoreApplicationPage;
import demoqa.page.method.LoginPage;
import demoqa.page.method.ProfilePage;
import demoqa.utility.AssertUtility;
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
        profilePage = loginPage.login(account.getUsername(), account.getPassword());
        AssertUtility.assertTrue(profilePage.checkUsername(account.getUsername()), "Check username");
    }

    @Test(dependsOnMethods = "loginWithValidAccount")
    public void loginValidAccountTest() {
        loginPage = profilePage.logout();
        loginPage.performLogin(randomAccount.getUsername(), randomAccount.getPassword());
        AssertUtility.assertTrue(loginPage.isErrorMsgDisplayed(), "Check error message");
    }
}
