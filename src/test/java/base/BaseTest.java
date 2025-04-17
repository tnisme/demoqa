package base;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.page.method.DashboardPage;
import demoqa.utility.DataTest;
import demoqa.utility.ReportUtility;
import demoqa.utility.WaitUtility;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
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

    @BeforeSuite
    public void setSuite() {
        String suiteName = org.testng.Reporter.getCurrentTestResult().getTestContext().getSuite().getXmlSuite().getName();
        ReportUtility.init(suiteName);
    }

    @BeforeTest
    public void setUp(ITestContext ctx) {
        ReportUtility.init(ctx.getCurrentXmlTest().getSuite().getName());
        DataTest.init();
        initBrowser();
    }

    @BeforeClass
    public void beforeClass() {
        className.set(getClass().getSimpleName());
        ReportUtility.getInstance().startTest(className.get());
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

    private void initBrowser() {
        WaitUtility waitU;
        WebDriver localDriver;
        File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "environment.json");
        try {
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jsonObject = new JSONObject(content);

            localDriver = new ChromeDriver();
            driver.set(localDriver);
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(jsonObject.getInt("object_wait"), TimeUnit.SECONDS);

            waitU = new WaitUtility(getDriver());
            getDriver().get(DataTest.getHomeURL());
            waitU.waitForPageLoad();
        } catch (IOException e) {
            ReportUtility.getInstance().log(LogStatus.ERROR, e.toString());
            ReportUtility.getInstance().log(LogStatus.ERROR, e.getMessage());
            e.printStackTrace();
        }

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
        String screenshotPath = USER_DIR + File.separator + "test-output" + File.separator + "report" + File.separator + "screenshots" + File.separator + fileName;
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotPath));
        ReportUtility.getInstance().addScreenCapture(LogStatus.FAIL, screenshotPath);
    }
}
