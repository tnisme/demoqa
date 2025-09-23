package function;

import base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import demoqa.entities.Account;
import demoqa.factories.DataFactory;
import demoqa.factories.PageFactory;
import demoqa.factories.UtilityFactory;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private Account account, randomAccount;

    @Override
    public void preCondition() {
        account = DataFactory.getAdminAccount();
        UtilityFactory.reportUtil().log(LogStatus.INFO, "Test valid data: " + account);
        randomAccount = DataFactory.getRandomAccount();
        UtilityFactory.reportUtil().log(LogStatus.INFO, "Test invalid data: " + randomAccount);
    }

    @Test
    public void loginWithValidAccount() {
        PageFactory.dashboardPage()
                .open()
                .goToBookStoreApplication()
                .goToLoginPage()
                .login(account.getUsername(), account.getPassword())
                .checkUsername(account.getUsername())
                .logout();
    }

    @Test
    public void loginInvalidAccount() {
        PageFactory.dashboardPage()
                .open()
                .goToBookStoreApplication()
                .goToLoginPage()
                .performLogin(randomAccount.getUsername(), randomAccount.getPassword())
                .checkErrorMsgDisplayed();
    }
}
