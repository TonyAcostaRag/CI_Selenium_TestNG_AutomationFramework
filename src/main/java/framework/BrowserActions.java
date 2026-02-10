package framework;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BrowserActions {

    protected WebDriver driver;
    protected FluentWaitUtils fluentWait;

    private static final Logger log = Log.getLogger(BrowserActions.class.getName());

    public BrowserActions(WebDriver driver) {
        log.info("---> Browser Actions instantiated.");
        this.fluentWait = new FluentWaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    protected void pressEscButton(WebElement element) {
        element.sendKeys(Keys.ESCAPE);
    }

    // ------- Element Attributes -------

    protected String getTitle() {
        return driver.getTitle();
    }

    protected String getElementText(WebElement element) {
        fluentWait.waitForVisibility(element);
        return element.getText();
    }

    public void click(WebElement element) {
        fluentWait.waitForClickable(element);
        element.click();
    }

    public void click(By locator) {
        fluentWait.waitForClickable(locator).click();
    }

    // ------- Inputs -------

    public void clearInput(WebElement element) {
        fluentWait.waitForClickable(element);
        element.clear();
    }

    public void inputText(WebElement element, String text) {
        fluentWait.waitForClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    // ------- Dropdown -------

    public void selectByVisibleText(WebElement element, String value) {
        fluentWait.waitForVisibility(element);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }

}
