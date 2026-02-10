package pages.paylocity;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BrowserActions;
import framework.ConfigReader;
import framework.Log;

public class Paylocity_loginPage extends BrowserActions {

    public Paylocity_loginPage (WebDriver driver) {
        super(driver);
    }

    private static final Logger log = Log.getLogger(Paylocity_loginPage.class.getName());

    @FindBy(id="Username")
    WebElement input_username;

    @FindBy(id="Password")
    WebElement input_password;

    @FindBy(xpath="//button[@type='submit']")
    WebElement button_login;

    public void goToPaylocity() {
        log.info("Navigating to Paylocity page");
        getUrl(ConfigReader.get("apiHost") + "/Prod/Account/Login");
    }

    public void enter_username(String user) {
        inputText(input_username, user);
    }

    public void enter_password(String pass) {
        inputText(input_password, pass);
    }

    public void clickOn_loginButton() {
        click(button_login);
    }

}
