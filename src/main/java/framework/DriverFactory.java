package framework;

import java.net.URL;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private static final Logger log = Log.getLogger(DriverFactory.class.getName());
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final String DEFAULT_REMOTE_URL = "http://chrome:4444";

    public void initDriver(String testngBrowser) {

        String browser =
                System.getProperty("browser",
                        System.getenv().getOrDefault("BROWSER", testngBrowser));

        String remoteUrl =
                System.getProperty("REMOTE_URL",
                        System.getenv().getOrDefault("REMOTE_URL", DEFAULT_REMOTE_URL));

        log.info("Resolved browser: {}", browser);
        log.info("Remote URL: {}", remoteUrl);

        try {

            switch (browser) {
                case "Chrome":
                    driver.set(new ChromeDriver());
                    break;

                case "Firefox":
                    driver.set(new FirefoxDriver());
                    break;

                case "Edge":
                    driver.set(new EdgeDriver());
                    break;

                case "FirefoxHeadless":
                    FirefoxOptions ffOpts = new FirefoxOptions();
                    ffOpts.addArguments("-headless");
                    ffOpts.addArguments("--window-size=1920,1080");
                    driver.set(new FirefoxDriver(ffOpts));
                    break;

                case "ChromeHeadless":
                case "RemoteChrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");

                    driver.set(new RemoteWebDriver(new URL(remoteUrl), options));
                    break;

                case "RemoteFirefox":
                    FirefoxOptions ffRmtOpts = new FirefoxOptions();
                    driver.set(
                        new RemoteWebDriver(
                            new URL("http://localhost:4444"), 
                            ffRmtOpts
                        )
                    );
                    break;

                case "RemoteEdge":
                    EdgeOptions edgeRmtOpts = new EdgeOptions();
                    driver.set(
                        new RemoteWebDriver(
                            new URL("http://localhost:4444"), 
                            edgeRmtOpts
                        )
                    );
                    break;

                default:
                    log.error("Unsupported browser: {}", browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize WebDriver for: " + browser, e);
        }

    }

    public WebDriver getDriver() {
        log.debug("---> Getting driver from ThreadLocal: " + driver.get());
        return driver.get();
    }

    public void quitDriver() {
        if (driver.get() != null) {
            log.info("---> Quitting driver.");
            driver.get().quit();
            driver.remove();
        }
    }
    
}
