package demoqa.utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import demoqa.factories.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utility class for generating test reports using ExtentReports.
 * Provides methods to log test steps, capture screenshots.
 */
public class ReportUtility {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SCREENSHOT_DIR = "screenshots";
    private static final String REPORT_PATH = USER_DIR + File.separator + "test-output" + File.separator + "report";
    private ExtentReports report;
    private ExtentTest logger;

    /**
     * Private constructor to initialize the ExtentReports instance.
     * @param suiteName Name of the test suite
     */
    private ReportUtility(String suiteName) {
        String reportName = suiteName + ".html";
        this.report = new ExtentReports(REPORT_PATH + File.separator + reportName);
    }

    /**
     * Factory method to create a ReportUtility instance.
     * @param suiteName Name of the test suite
     * @return ReportUtility instance
     */
    public static ReportUtility create(String suiteName) {
        return new ReportUtility(suiteName);
    }

    /**
     * Starts a new test in the report.
     * @param testName Name of the test
     */
    public void startTest(String testName) {
        logger = report.startTest(testName);
    }

    /**
     * Logs a message with the specified status.
     * @param status
     * @param content
     */
    public void log(LogStatus status, String content) {
        logger.log(status, encodingContent(content));
    }

    /**
     * Adds multiple screenshots to the report with the specified status.
     * @param status LogStatus for the screenshots
     * @param paths Array of screenshot file paths
     */
    public void addScreenCapture(LogStatus status, String ... paths) {
        String capture = Arrays.stream(paths)
                .map(path ->logger.addScreenCapture(path))
                .collect(Collectors.joining());
        logger.log(status, capture);
    }

    /**
     * Captures a screenshot and adds it to the report with the specified status.
     * @param status LogStatus for the screenshot
     */
    public void addScreenCapture(LogStatus status) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName =  timestamp + ".png";
        String screenshotPath = SCREENSHOT_DIR + File.separator + fileName;
        File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(REPORT_PATH + File.separator + screenshotPath));
        } catch (Exception e) {
            log(LogStatus.ERROR, "Failed to capture screenshot: " + e.getMessage());
        }
        logger.log(status, logger.addScreenCapture(screenshotPath));
    }

    /**
     * Ends the current test and flushes the report to the file.
     */
    public void flush() {
        report.endTest(logger);
        report.flush();
    }

    /**
     * Closes the ExtentReports instance.
     */
    public void close() {
        report.close();
    }

    private String encodingContent(String content) {
        byte[] ptext = content.getBytes(StandardCharsets.UTF_8);
        return new String(ptext);
    }
}
