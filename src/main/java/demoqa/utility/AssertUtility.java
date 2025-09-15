package demoqa.utility;

import com.relevantcodes.extentreports.LogStatus;
import demoqa.factories.UtilityFactory;
import org.testng.ITestResult;
import org.testng.Reporter;

public class AssertUtility {
    
    private static final String FONT_COLOR_BLUE = "<font color=\"blue\">";
    private static final String FONT_COLOR_RED = "<font color=\"red\">";
    private static final String FONT_END = "</front>";

    public void assertTrue(boolean condition, String description) {
        assertWithBooleanExpectation(condition, description, true);
    }

    public void assertFalse(boolean condition, String description) {
        assertWithBooleanExpectation(condition, description, false);
    }

    public void assertEquals(String expected, String actual, String description) {
        assertWithBooleanExpectation(expected.equals(actual), description, true);
        UtilityFactory.reportUtil().log(LogStatus.INFO, "Expected: " + expected + ", Actual: " + actual);
    }

    private void logFailure(String description) {
        UtilityFactory.reportUtil().log(LogStatus.FAIL, FONT_COLOR_RED + description + FONT_END);
        Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
    }

    private void logSuccess(String description) {
        UtilityFactory.reportUtil().log(LogStatus.PASS, FONT_COLOR_BLUE + description + FONT_END);
    }

    private void assertWithBooleanExpectation(boolean condition, String description, boolean expected) {
        UtilityFactory.reportUtil().addScreenCapture(LogStatus.INFO);
        if (condition == expected) logSuccess(description);
        else logFailure(description);
    }
}
