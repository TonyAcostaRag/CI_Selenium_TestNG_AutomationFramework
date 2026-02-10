package tests.paylocity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.ConfigReader;
import listeners.TestLog;
import pages.paylocity.BenefitsDashboard;
import pages.paylocity.Paylocity_loginPage;
import tests.TestBasePage;

public class LoginTests_paylocity extends TestBasePage {

    Paylocity_loginPage loginPaylocity;
    BenefitsDashboard benefitDashboard;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPaylocity = new Paylocity_loginPage(driverFactory.getDriver());
        benefitDashboard = new BenefitsDashboard(driverFactory.getDriver());
    }

    @Test(
        priority = 1,
        groups = {"regression"}
    )
    public void user_login_to_paylocity() {
        TestLog.info("User is loggin into Paylocity");

        loginPaylocity.goToPaylocity();
        TestLog.pass("User is in paylocity page");

        loginPaylocity.enter_username(ConfigReader.get("paylocity_test_user"));
        TestLog.pass("Username have been entered");

        loginPaylocity.enter_password(ConfigReader.get("paylocity_test_pass"));
        TestLog.pass("Password have been entered");

        loginPaylocity.clickOn_loginButton();
        TestLog.pass("Login button pressed");

        Assert.assertTrue(benefitDashboard.employees_table_IsPresent());
        TestLog.pass("Employees table from home page is present");
        
    }
    
}
