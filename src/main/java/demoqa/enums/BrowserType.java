package demoqa.enums;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public enum BrowserType {
    CHROME {
        public MutableCapabilities getOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-notifications");
            return options;
        }
    },
    FIREFOX {
        public MutableCapabilities getOptions() {
            return new FirefoxOptions();
        }
    },
    EDGE {
        public MutableCapabilities getOptions() {
            return new EdgeOptions();
        }
    };

    public abstract MutableCapabilities getOptions();
}
