package demoqa.utility;

import com.relevantcodes.extentreports.LogStatus;

public class AssertUtility {

    public static void assertTrue(boolean condition, String description) {
        if (condition) ReportUtility.getInstance().log(LogStatus.PASS, "<font color=\"blue\">" + description + "</front>");
        else {
            ReportUtility.getInstance().log(LogStatus.FAIL, "<font color=\"red\">" + description + "</front>");
        }
    }

    public static void assertFalse(boolean condition, String description) {
        if (!condition) ReportUtility.getInstance().log(LogStatus.PASS, "<font color=\"blue\">" + description + "</front>");
        else ReportUtility.getInstance().log(LogStatus.FAIL, "<font color=\"red\">" + description + "</front>");
    }

    public static void assertEquals(String expected, String actual, String description) {
        if (expected.equals(actual)) ReportUtility.getInstance().log(LogStatus.PASS, "<font color=\"blue\">" + description + "</front>");
        else ReportUtility.getInstance().log(LogStatus.FAIL, "<font color=\"red\">" + description + "</front>");
    }
}
