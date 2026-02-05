package tests.herokuapp;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import listeners.TestLog;
import pages.herokuapp.LoginPage_herokuapp;
import tests.TestBasePage;

public class LoginTests_herokuapp extends TestBasePage {

    LoginPage_herokuapp loginPage;

    @BeforeMethod(alwaysRun=true)
    public void initPage() {
        loginPage = new LoginPage_herokuapp(driverFactory.getDriver());
    }

    @Test(
        priority = 2,
        groups = {"smoke"}
    )
    public void basicAuth_Test() {
        TestLog.info("Basic Authentication test started for browser: ");

        loginPage.goToPage();
        TestLog.pass("Navigated to herokuapp page");
        
        loginPage.enterCredentials_basicAuth();
        TestLog.pass("Credentials entered for basic authentication");

        loginPage.loginSuccessful();
        TestLog.pass("User is on basic auth home page");

        Assert.assertTrue(loginPage.basicAuth_message_IsPresent());
        TestLog.pass("User can see basic auth message.");

        TestLog.info("Login Test successful for browser: ");
    }

    @Test(
        priority = 1,
        groups = {"sanity", "regression"}
    )
    public void digestAuth_Test() {
        TestLog.info("Digest Authentication test started for browser: ");

        loginPage.goToPage();
        TestLog.pass("Navigated to herokuapp page");
        
        loginPage.enterCredentials_digestAuth();
        TestLog.pass("Credentials entered for digest authentication");

        loginPage.loginSuccessful();
        TestLog.pass("User is on digest auth home page");

        Assert.assertTrue(loginPage.digestAuth_message_IsPresent());
        TestLog.pass("User can see digest auth message.");

        TestLog.info("Login Test successful for browser: ");
    }
    
}
