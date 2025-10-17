package demoqa.factories;

import demoqa.enums.BrowserType;
import demoqa.exceptions.FailedToReadFileException;
import demoqa.exceptions.InvalidHubURLException;
import org.apache.commons.io.FileUtils;
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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class DriverFactory {

    private static final String CONFIG_PATH = Paths.get("src", "main", "resources", "data").toString();

    private DriverFactory() {
        // private constructor to prevent instantiation
    }

    public static WebDriver createInstance(BrowserType browserType) {
        JSONObject envConfig = loadConfig();
        String hubUrl = envConfig.optString("hub_url", "").trim();
        boolean isRemote = envConfig.optBoolean("remote", false);

        MutableCapabilities options = browserType.getOptions();
        try {
            if (isRemote) {
                return new RemoteWebDriver(URI.create(hubUrl).toURL(), options);
            }

            return switch (browserType) {
                case CHROME -> new ChromeDriver((ChromeOptions) options);
                case FIREFOX -> new FirefoxDriver((FirefoxOptions) options);
                case EDGE -> new EdgeDriver((EdgeOptions) options);
            };
        } catch (MalformedURLException e) {
            throw new InvalidHubURLException("Invalid hub URL: " + hubUrl, e);
        }
    }

    private static JSONObject loadConfig() {
        File file = new File(CONFIG_PATH + File.separator + "environment.json");
        try {
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (IOException e) {
            throw new FailedToReadFileException("Failed to read environment config: " + e.getMessage(), e);
        }
    }
}
