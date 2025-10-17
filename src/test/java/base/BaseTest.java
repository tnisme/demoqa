package base;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.enums.BrowserType;
import demoqa.factories.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    private static final ThreadLocal<String> className = new ThreadLocal<>();
    private final static String USER_DIR = System.getProperty("user.dir");

    @BeforeTest
    @Parameters("browser")
    public void setUp(ITestContext ctx, @Optional("CHROME") BrowserType browserType) {
        cleanReportDirectory();
        className.set(getClass().getSimpleName());
        UtilityManager.initReport(ctx.getCurrentXmlTest().getName());
        UtilityManager.reportUtil().startTest(className.get());
        UtilityManager.reportUtil().log(LogStatus.INFO, "<b>Browser: " + browserType + "<b>");

        WebDriver driver = DriverFactory.createInstance(browserType);
        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
    }

    @BeforeMethod
    public void setUpBeforeMethod(Method method) {
        UtilityManager.reportUtil().log(LogStatus.INFO, "<b>Start method: " + method.getName() + "</b>");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) {
        String methodName = method.getName();
        if (result.getStatus() == ITestResult.FAILURE) {
            UtilityManager.reportUtil().log(LogStatus.FAIL, "Test case: " + methodName + " failed");
            UtilityManager.reportUtil().addScreenCapture(LogStatus.FAIL);
            UtilityManager.reportUtil().log(LogStatus.FAIL, String.valueOf(result.getThrowable()));
        } else if (result.getStatus() == ITestResult.SKIP) {
            UtilityManager.reportUtil().log(LogStatus.SKIP, "Test case: " + methodName + " skipped");
        } else {
            UtilityManager.reportUtil().log(LogStatus.PASS, "Test case: " + methodName + " passed");
        }
    }

    @AfterClass
    public void afterClass() {
        UtilityManager.reportUtil().flush();
    }

    @AfterTest
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            UtilityManager.clear();
            DriverManager.removeDriver();
        }
    }

    protected void preCondition() {
        // Implement pre-condition
    }

    private void cleanReportDirectory() {
        // Clean report directory before running tests
        try {
            File reportDir = new File(USER_DIR + File.separator + "test-output" + File.separator +"report");
            if (reportDir.exists()) {
                FileUtils.cleanDirectory(reportDir);
                System.out.println("Cleaned report directory: " + reportDir.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Failed to clean report directory: " + e.getMessage());
        }
    }
}
