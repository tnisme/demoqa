package function;

import base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import demoqa.entities.Account;
import demoqa.factories.DataFactory;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityManager;
import demoqa.pages.DashboardPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private Account account, randomAccount;

    @Override
    public void preCondition() {
        account = DataFactory.getAdminAccount();
        UtilityManager.reportUtil().log(LogStatus.INFO, "Test valid data: " + account);
        randomAccount = DataFactory.getRandomAccount();
        UtilityManager.reportUtil().log(LogStatus.INFO, "Test invalid data: " + randomAccount);
    }

    @Test
    public void loginWithValidAccount() {
        PageFactory.create(DashboardPage.class)
                .open()
                .goToBookStoreApplication()
                .goToLoginPage()
                .login(account.getUsername(), account.getPassword())
                .checkUsername(account.getUsername())
                .logout();
    }

    @Test
    public void loginInvalidAccount() {
        PageFactory.create(DashboardPage.class)
                .open()
                .goToBookStoreApplication()
                .goToLoginPage()
                .performLogin(randomAccount.getUsername(), randomAccount.getPassword())
                .checkErrorMsgDisplayed();
    }
}
