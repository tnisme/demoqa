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

public class ReportUtility {
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String SCREENSHOT_DIR = "screenshots";
    private static final String REPORT_PATH = USER_DIR + File.separator + "test-output" + File.separator + "report";
    private ExtentReports report;
    private ExtentTest logger;

    private ReportUtility(String suiteName) {
        String reportName = suiteName + ".html";
        this.report = new ExtentReports(REPORT_PATH + File.separator + reportName);
    }

    public static ReportUtility create(String suiteName) {
        return new ReportUtility(suiteName);
    }

    public void startTest(String testName) {
        logger = report.startTest(testName);
    }

    public void log(LogStatus status, String content) {
        logger.log(status, encodingContent(content));
    }

    public void addScreenCapture(LogStatus status, String ... paths) {
        String capture = Arrays.stream(paths)
                .map(path ->logger.addScreenCapture(path))
                .collect(Collectors.joining());
        logger.log(status, capture);
    }

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

    public void flush() {
        report.endTest(logger);
        report.flush();
    }

    public void close() {
        report.close();
    }

    private String encodingContent(String content) {
        byte[] ptext = content.getBytes(StandardCharsets.UTF_8);
        return new String(ptext);
    }
}
