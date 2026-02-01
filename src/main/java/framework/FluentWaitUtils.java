package framework;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWaitUtils {

    private final WebDriver driver;
    private final int timeout;
    private final int polling;

    public FluentWaitUtils(WebDriver driver) {
        this(driver, 10, 200);
    }

    public FluentWaitUtils(WebDriver driver, int timeoutInSeconds, int pollingInMilliS) {
        this.driver = driver;
        this.timeout = timeoutInSeconds;
        this.polling = pollingInMilliS;
    }

    private Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /* =========================
       WebElement-based waits
       ========================= */

    public WebElement waitForVisibility(WebElement element) {
        return getFluentWait().until(driver -> {
            try {
                return element.isDisplayed() ? element : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public WebElement waitForClickable(WebElement element) {
        return getFluentWait().until(driver -> {
            try {
                return element.isDisplayed() && element.isEnabled()
                        ? element
                        : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    /* =========================
       Locator-based waits
       ========================= */

    public WebElement waitForVisibility(By locator) {
        return getFluentWait().until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed() ? element : null;
        });
    }

    public WebElement waitForClickable(By locator) {
        return getFluentWait().until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed() && element.isEnabled()
                    ? element
                    : null;
        });
    }

    /* =========================
       Page-level waits
       ========================= */

    public boolean waitForPageLoad() {
        return getFluentWait().until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    public boolean waitForUrlContains(String partialUrl) {
        return getFluentWait().until(driver ->
                driver.getCurrentUrl().contains(partialUrl)
        );
    }
    
}
