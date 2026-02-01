package pages.herokuapp;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BrowserActions;
import framework.Log;

public class LoginPage_herokuapp extends BrowserActions {

    public LoginPage_herokuapp(WebDriver driver) {
        super(driver);
    }

    private static final Logger log = Log.getLogger(LoginPage_herokuapp.class.getName());

    @FindBy(xpath="//h3[contains(text(), 'Basic Auth')]")
    WebElement basicAuth_messagePage;

    @FindBy(xpath="//h3[contains(text(), 'Digest Auth')]")
    WebElement digestAuth_messagePage;

    @FindBy(xpath="//p[contains(text(), 'Congratulations')]")
    WebElement SuccessfulLogin_message;

    public void goToPage() {
        log.info("Navigating to Saucedmo");
        getUrl("https://the-internet.herokuapp.com/");
    }

    public void enterCredentials_basicAuth() {
        getUrl("https://admin:admin@the-internet.herokuapp.com/basic_auth");
    }

    public void enterCredentials_digestAuth() {
        getUrl("https://admin:admin@the-internet.herokuapp.com/digest_auth");
    }

    public boolean loginSuccessful() {
        String elementMessage = getElementText(SuccessfulLogin_message);
        return elementMessage.contains("Congratulations! You must have the proper credentials.");
    }

    public boolean digestAuth_message_IsPresent() {
        String elementMessage = getElementText(digestAuth_messagePage);
        return elementMessage.contains("Digest Auth");
    }

    public boolean basicAuth_message_IsPresent() {
        String elementMessage = getElementText(basicAuth_messagePage);
        return elementMessage.contains("Basic Auth");
    }
    
}
