package framework;

import java.time.Duration;
import java.util.List;
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

    public void waitForTableToHaveRows(By rowLocator) {
        getFluentWait().until(driver ->
            driver.findElements(rowLocator).size() > 0
        );
    }

    public WebElement waitForRowContainingText(By rowsLocator, String text) {
        return getFluentWait().until(driver -> {
            for (WebElement row : driver.findElements(rowsLocator)) {
                if (row.getText().contains(text)) {
                    return row;
                }
            }
            return null;
        });
    }

    public boolean waitForRowToBeInvisible(By rowsLocator, String text) {
    return getFluentWait().until(driver -> {
        try {
            List<WebElement> rows = driver.findElements(rowsLocator);

            for (WebElement row : rows) {
                if (row.getText().contains(text)) {
                    return !row.isDisplayed(); // row exists but hidden
                }
            }

            return true; // row not found = invisible
        } catch (StaleElementReferenceException e) {
            return true; // DOM refreshed, row gone
        }
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
