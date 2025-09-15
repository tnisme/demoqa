package base;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.enums.BrowserType;
import demoqa.factories.*;
import demoqa.utility.ReportUtility;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class BaseTest {
    private static final ThreadLocal<String> className = new ThreadLocal<>();
    private final static String USER_DIR = System.getProperty("user.dir");
    private static final String CONFIG_PATH = Paths.get("src", "main", "resources", "data").toString();

    @BeforeTest
    @Parameters("browser")
    public void setUp(ITestContext ctx, @Optional("CHROME") BrowserType browser) {
        cleanReportDirectory();
        className.set(getClass().getSimpleName());
        UtilityFactory.initReport(ctx.getCurrentXmlTest().getName());
        UtilityFactory.reportUtil().startTest(className.get());
        UtilityFactory.reportUtil().log(LogStatus.INFO, "<b>Browser: " + browser + "<b>");

        initBrowser(browser);
        PageFactory.init(DriverManager.getDriver());
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
    }

    @BeforeMethod
    public void setUpBeforeMethod(Method method) {
        UtilityFactory.reportUtil().log(LogStatus.INFO, "<b>Start method: " + method.getName() + "</b>");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        String methodName = method.getName();
        if (result.getStatus() == ITestResult.FAILURE) {
            UtilityFactory.reportUtil().log(LogStatus.FAIL, "Test case: " + methodName + " failed");
            UtilityFactory.reportUtil().addScreenCapture(LogStatus.FAIL);
        } else if (result.getStatus() == ITestResult.SKIP) {
            UtilityFactory.reportUtil().log(LogStatus.SKIP, "Test case: " + methodName + " skipped");
        } else {
            UtilityFactory.reportUtil().log(LogStatus.PASS, "Test case: " + methodName + " passed");
        }
    }

    @AfterClass
    public void afterClass() throws TimeoutException {
        UtilityFactory.reportUtil().flush();
    }

    @AfterTest
    public void tearDown() {
        if (DriverManager.getDriver() == null) {
            return;
        }

        try {
            // Clear utility
            UtilityFactory.clear();

            // Clear report
            ReportUtility report = UtilityFactory.reportUtil();
            if (report != null) {
               report.close();
            }

            // Clear page & element factories
            PageFactory.clear();
            ElementFactory.clear();

            // Quit driver
            try {
                DriverManager.getDriver().quit();
            } catch (Exception e) {
                UtilityFactory.reportUtil().log(LogStatus.ERROR, e.toString());
                e.printStackTrace();
            }
        } finally {
            // Always remove driver to avoid memory leaks
            DriverManager.removeDriver();
        }
    }

    private void initBrowser(BrowserType browser) {
        WebDriver localDriver;
        JSONObject jsonObject = null;
        File file = new File(CONFIG_PATH + File.separator + "environment.json");
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            jsonObject = new JSONObject(content);
        } catch (IOException e) {
            UtilityFactory.reportUtil().log(LogStatus.ERROR, e.toString());
            UtilityFactory.reportUtil().log(LogStatus.ERROR, e.getMessage());
            e.printStackTrace();
        }
        try {
            switch (browser) {
                case CHROME:
                    localDriver = new RemoteWebDriver(new URL(jsonObject.getString("hub_url")), new ChromeOptions());
                    break;
                case FIREFOX:
                    localDriver = new RemoteWebDriver(new URL(jsonObject.getString("hub_url")), new FirefoxOptions());
                    break;
                case EDGE:
                default:
                    throw new IllegalArgumentException("Browser type not supported");
            }
            DriverManager.setDriver(localDriver);
        } catch (MalformedURLException e) {
            UtilityFactory.reportUtil().log(LogStatus.ERROR, e.toString());
            UtilityFactory.reportUtil().log(LogStatus.ERROR, e.getMessage());
            throw new RuntimeException("Failed to initialize browser: " + e.getMessage(), e);
        }

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(DataFactory.getHomeURL());
        UtilityFactory.waitUtil().waitForPageLoad();
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
