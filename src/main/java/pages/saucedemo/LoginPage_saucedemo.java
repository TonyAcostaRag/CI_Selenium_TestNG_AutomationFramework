package pages.saucedemo;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BrowserActions;
import framework.Log;

public class LoginPage_saucedemo extends BrowserActions {

    public LoginPage_saucedemo(WebDriver driver) {
        super(driver);
    }

    private static final Logger log = Log.getLogger(LoginPage_saucedemo.class.getName());

    @FindBy(id="user-name")
    WebElement input_username;

    @FindBy(id="password")
    WebElement input_password;

    @FindBy(id="login-button")
    WebElement button_login;

    @FindBy(xpath="//span[text()='Products']")
    WebElement label_products;

    public void goToSaucedemo() {
        log.info("Navigating to Saucedemo page");
        getUrl("https://www.saucedemo.com/");
    }
    
    public void enter_username(String user_name) {
        inputText(input_username, user_name);
    }

    public void enter_password(String password) {
        inputText(input_password, password);
    }

    public void clickOn_loginButton() {
        click(button_login);
    }

    public boolean products_label_IsPresent() {
        return label_products.getText().equals("Products");
    }
    
}
