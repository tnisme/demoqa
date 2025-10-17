package demoqa.factories;

import demoqa.enums.BrowserType;
import demoqa.exceptions.InvalidHubURLException;
import demoqa.utilities.JsonUtility;
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;

public class DriverFactory {

    private static final String CONFIG_PATH = Paths.get("src", "main", "resources", "data").toString();

    private DriverFactory() {
        // private constructor to prevent instantiation
    }

    public static WebDriver createInstance(BrowserType browserType) {
        JSONObject envConfig = JsonUtility.readJson(CONFIG_PATH + File.separator + "environment.json");
        String hubUrl = envConfig.optString("hub_url", "").trim();
        boolean isRemote = envConfig.optBoolean("remote", false);

        MutableCapabilities options = browserType.getOptions();
        if (isRemote) {
            if (hubUrl.isEmpty()) {
                throw new InvalidHubURLException("Hub URL is not specified for remote execution.");
            }
            return createRemoteDriver(hubUrl, options);
        }
        return createLocalDriver(browserType, options);
    }

    private static WebDriver createLocalDriver(BrowserType browserType, MutableCapabilities options) {
        return switch (browserType) {
            case CHROME -> new ChromeDriver((ChromeOptions) options);
            case FIREFOX -> new FirefoxDriver((FirefoxOptions) options);
            case EDGE -> new EdgeDriver((EdgeOptions) options);
        };
    }

    private static WebDriver createRemoteDriver(String hubUrl, MutableCapabilities options) {
        try {
            return new RemoteWebDriver(URI.create(hubUrl).toURL(), options);
        } catch (MalformedURLException e) {
            throw new InvalidHubURLException("Invalid hub URL: " + hubUrl, e);
        }
    }
}
