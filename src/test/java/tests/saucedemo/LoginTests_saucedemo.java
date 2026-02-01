package tests.saucedemo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataproviders.LoginDataProvider;
import listeners.TestLog;
import pages.saucedemo.LoginPage_saucedemo;
import tests.TestBasePage;

public class LoginTests_saucedemo extends TestBasePage{

    LoginPage_saucedemo loginSaucedemo;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginSaucedemo = new LoginPage_saucedemo(driverFactory.getDriver());
    }
    
    @Test(
        dataProvider = "loginData", dataProviderClass = LoginDataProvider.class,
        priority = 1,
        groups = {"regression"}
    )
    public void standard_user_login_test(String username, String password) {
        TestLog.info("Standard user test started");

        loginSaucedemo.goToSaucedemo();
        TestLog.pass("User is in saucedemo page");

        loginSaucedemo.enter_username(username);
        TestLog.pass("Username have been entered");

        loginSaucedemo.enter_password(password);
        TestLog.pass("Password have been entered");

        loginSaucedemo.clickOn_loginButton();
        TestLog.pass("Login button pressed");

        Assert.assertTrue(loginSaucedemo.products_label_IsPresent());
        TestLog.pass("Products label from home page is present");

        TestLog.info("Login Test successful");
    }
    
}
