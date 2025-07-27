package base;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.enums.BrowserType;
import demoqa.page.DashboardPage;
import demoqa.utility.DataTest;
import demoqa.utility.ReportUtility;
import demoqa.utility.WaitUtility;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static final ThreadLocal<String> className = new ThreadLocal<>();
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final static String USER_DIR = System.getProperty("user.dir");

    public synchronized static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeTest
    @Parameters("browser")
    public void setUp(ITestContext ctx, @Optional("CHROME") BrowserType browser) {
        cleanReportDirectory();
        ReportUtility.init(ctx.getCurrentXmlTest().getName());
        className.set(getClass().getSimpleName());
        ReportUtility.getInstance().startTest(className.get());
        ReportUtility.getInstance().log(LogStatus.INFO, "<b>Browser: " + browser + "<b>");

        DataTest.init();
        initBrowser(browser);
    }

    @BeforeClass
    public void beforeClass() {
        preCondition();
    }

    @BeforeMethod
    public void setUpBeforeMethod(Method method) {
        ReportUtility.getInstance().log(LogStatus.INFO, "<b>Start method: " + method.getName() + "</b>");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        String methodName = method.getName();
        if (result.getStatus() == ITestResult.FAILURE) {
            ReportUtility.getInstance().log(LogStatus.FAIL, "Test case: " + methodName + " failed");
            ReportUtility.getInstance().log(LogStatus.FAIL, result.getThrowable().toString());
            captureScreenshot(methodName);
        } else if (result.getStatus() == ITestResult.SKIP) {
            ReportUtility.getInstance().log(LogStatus.SKIP, "Test case: " + methodName + " skipped");
        } else {
            ReportUtility.getInstance().log(LogStatus.PASS, "Test case: " + methodName + " passed");
        }
    }

    @AfterClass
    public void afterClass() throws TimeoutException {
        ReportUtility.getInstance().flush();
    }

    @AfterTest
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    private void initBrowser(BrowserType browser) {
        WaitUtility waitU;
        WebDriver localDriver;
        JSONObject jsonObject = null;
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "environment.json");
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            jsonObject = new JSONObject(content);
        } catch (IOException e) {
            ReportUtility.getInstance().log(LogStatus.ERROR, e.toString());
            ReportUtility.getInstance().log(LogStatus.ERROR, e.getMessage());
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
            driver.set(localDriver);
        } catch (MalformedURLException e) {
            ReportUtility.getInstance().log(LogStatus.ERROR, e.toString());
            ReportUtility.getInstance().log(LogStatus.ERROR, e.getMessage());
            e.printStackTrace();
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(jsonObject.getInt("object_wait"), TimeUnit.SECONDS);
        waitU = new WaitUtility(getDriver());
        getDriver().get(DataTest.getHomeURL());
        waitU.waitForPageLoad();
    }

    protected void preCondition() {
        // Implement pre-condition
    }

    protected DashboardPage getDashboardPage() {
        return new DashboardPage(getDriver());
    }

    private void captureScreenshot(String methodName) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = methodName + "_" + timestamp + ".png";
        String screenshotPath = "screenshots" + File.separator + fileName;
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotPath));
        ReportUtility.getInstance().addScreenCapture(LogStatus.FAIL, screenshotPath);
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
