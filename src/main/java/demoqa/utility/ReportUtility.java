package demoqa.utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReportUtility {
    private final static String USER_DIR = System.getProperty("user.dir");
    private static ThreadLocal<ReportUtility> ourInstance = new ThreadLocal<>();
    private ExtentReports report;
    private ExtentTest logger;

    private ReportUtility(String suiteName) {
        String reportName = suiteName + ".html";
        report = new ExtentReports(USER_DIR + File.separator + "test-output" + File.separator + "report" + File.separator + reportName);
    }

    public static void init(String suiteName) {
        ReportUtility localOurInstance = new ReportUtility(suiteName);
        ourInstance.set(localOurInstance);
    }

    public static ReportUtility getInstance() {
        return ourInstance.get();
    }

    public void log(LogStatus status, String content) {
        logger.log(status, encodingContent(content));
    }

    public void addScreenCapture(LogStatus status, String ... paths) {
        String capture = Arrays.stream(paths)
                .map(path -> "<td>" + logger.addScreenCapture(path) + "</td>")
                .collect(Collectors.joining());;
        logger.log(status, capture);
    }

    public void startTest(String testName) {
        logger = report.startTest(testName);
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
