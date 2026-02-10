package tests.paylocity;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import framework.ConfigReader;
import listeners.TestLog;
import pages.paylocity.BenefitsDashboard;
import pages.paylocity.Paylocity_loginPage;
import tests.TestBasePage;

public class BenefitCosts_previewTests extends TestBasePage {

    Paylocity_loginPage loginPaylocity;
    BenefitsDashboard benefitDashboard;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPaylocity = new Paylocity_loginPage(driverFactory.getDriver());
        benefitDashboard = new BenefitsDashboard(driverFactory.getDriver());

        loginPaylocity.goToPaylocity();
        loginPaylocity.enter_username(ConfigReader.get("paylocity_test_user"));
        loginPaylocity.enter_password(ConfigReader.get("paylocity_test_pass"));
        loginPaylocity.clickOn_loginButton();

        Assert.assertTrue(benefitDashboard.employees_table_IsPresent());
    }

    @Test(
        priority = 1,
        groups = {"regression"}
    )
    public void user_adds_employee() throws InterruptedException {
        TestLog.info("An Employeer is logged and in the Benefits Dashboard page");

        benefitDashboard.clickOn_AddButton();
        TestLog.pass("Add button is clicked.");

        benefitDashboard.enter_firstname("firstname_added");
        benefitDashboard.enter_lastname("lastname_added");
        benefitDashboard.enter_dependants("3");
        TestLog.pass("Employee details are entered.");

        benefitDashboard.clickOn_AddEmployee();
        TestLog.pass("Add Employee button is clicked to save the Employee.");
        Assert.assertTrue(benefitDashboard.isEmployee_presentInTable("firstname_added", "lastname_added", "3"));
        TestLog.pass("Employee is present in the table.");
        Thread.sleep(2000);
        Assert.assertTrue(benefitDashboard.areBenefitCostCalculationCorrect("firstname_added", 3));
        TestLog.pass("Benefit costs calculations are correct.");
    }
  
    @Test(priority = 2)
    public void user_edits_employee() {
        TestLog.info("An Employeer is logged and in the Benefits Dashboard page");

        benefitDashboard.clickOn_editButtonFor("firstname_added");
        TestLog.pass("User select employee to edit");

        benefitDashboard.enter_firstname("firstname_edited");
        benefitDashboard.enter_lastname("lastname_edited");
        benefitDashboard.enter_dependants("10");
        TestLog.pass("Employee updates details are entered.");
        
        benefitDashboard.clickOn_UpdateEmployee();
        TestLog.pass("Employee details are updated.");

        Assert.assertTrue(benefitDashboard.isEmployee_presentInTable("firstname_edited", "lastname_edited", "10"));
        TestLog.pass("Employee is present in the table.");

        Assert.assertTrue(benefitDashboard.areBenefitCostCalculationCorrect("firstname_edited", 10));
        TestLog.pass("Benefit costs calculations are correct.");

    }

    @Test(priority = 3)
    public void user_deletes_employee() {
        TestLog.info("An Employeer is logged and in the Benefits Dashboard page");

        benefitDashboard.clickOn_deleteButtonFor("firstname_edited");
        TestLog.pass("User selects the user to delete");

        benefitDashboard.clickOn_DeleteEmployee();
        TestLog.pass("User deletes the employee");

        Assert.assertTrue(benefitDashboard.employeeIsNot_presentInTable("firstname_edited"));
        TestLog.pass("Employee is not present in the table");
    }

}
