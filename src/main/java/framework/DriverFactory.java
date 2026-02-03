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

    public void initDriver(String browser) {
        log.info("---> Initializing driver for browser: {}", browser);

        System.setProperty("browser", browser);

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

                case "ChromeHeadless":
                    ChromeOptions chromeOpts = new ChromeOptions();
                    chromeOpts.addArguments("--headless=new");
                    chromeOpts.addArguments("--window-size=1920,1080");
                    driver.set(new ChromeDriver(chromeOpts));
                    break;

                case "FirefoxHeadless":
                    FirefoxOptions ffOpts = new FirefoxOptions();
                    ffOpts.addArguments("-headless");
                    ffOpts.addArguments("--window-size=1920,1080");
                    driver.set(new FirefoxDriver(ffOpts));
                    break;

                case "EdgeHeadless":
                    EdgeOptions edgeOpts = new EdgeOptions();
                    edgeOpts.addArguments("-headless");
                    edgeOpts.addArguments("--window-size=1920,1080");
                    driver.set(new EdgeDriver(edgeOpts));
                    break;

                case "RemoteChrome":
                    ChromeOptions chromeRmtOpts = new ChromeOptions();
                    driver.set(new RemoteWebDriver(
                        new URL(System.getProperty("SELENIUM_GRID_URL")),
                        chromeRmtOpts
                    ));
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
