package pages.paylocity;

import java.text.DecimalFormat;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BrowserActions;
import framework.ConfigReader;
import framework.Log;

public class BenefitsDashboard extends BrowserActions {

    public BenefitsDashboard (WebDriver driver) {
        super(driver);
    }

    private static final Logger log = Log.getLogger(BenefitsDashboard.class.getName());

    @FindBy(id="add")
    WebElement button_add;

    @FindBy(id="firstName")
    WebElement input_firstname;

    @FindBy(id="lastName")
    WebElement input_lastname;

    @FindBy(id="dependants")
    WebElement input_dependants;

    @FindBy(id="addEmployee")
    WebElement button_addEmployee;

    @FindBy(id="updateEmployee")
    WebElement button_updateEmployee;

    @FindBy(id="employeesTable")
    WebElement table_employees;

    @FindBy(id="deleteEmployee")
    WebElement button_deleteEmployee;

    public void clickOn_AddButton() {
        click(button_add);
    }

    public void enter_firstname(String firstname) {
        inputText(input_firstname, firstname);
    }

    public void enter_lastname(String lastname) {
        inputText(input_lastname, lastname);
    }

    public void enter_dependants(String dependants_number) {
        inputText(input_dependants, dependants_number);
    }

    public void clickOn_AddEmployee() {
        click(button_addEmployee);
    }

    public void clickOn_UpdateEmployee() {
        click(button_updateEmployee);
    }

    public void clickOn_DeleteEmployee() {
        click(button_deleteEmployee);
    }

    public void clickOn_editButtonFor(String employeeName) {

        By employeesTable = By.id("employeesTable");
        fluentWait.waitForVisibility(employeesTable);

        By rows = By.xpath("//table[@id='employeesTable']//tbody/tr");
        fluentWait.waitForTableToHaveRows(rows);

        By employeeEditLink = By.xpath("//table[@id='employeesTable']//tr[td[text()='" + employeeName + "']]//td//i[@class='fas fa-edit']");
        fluentWait.waitForVisibility(employeeEditLink).click();
    }

    public void clickOn_deleteButtonFor(String employeeName) {
        By employeesTable = By.id("employeesTable");
        fluentWait.waitForVisibility(employeesTable);

        By rows = By.xpath("//table[@id='employeesTable']//tbody/tr");
        fluentWait.waitForTableToHaveRows(rows);

        By employeeDeleteLink = By.xpath("//table[@id='employeesTable']//tr[td[text()='" + employeeName + "']]//td//i[@class='fas fa-times']");
        fluentWait.waitForVisibility(employeeDeleteLink).click();
    }

    public boolean employees_table_IsPresent() {
        By rows = By.xpath("//table[@id='employeesTable']//tbody/tr");
        fluentWait.waitForTableToHaveRows(rows);
        return fluentWait.waitForVisibility(table_employees) != null;
    }

    public boolean isEmployee_presentInTable(String firstname, String lastname, String dependents) {

        fluentWait.waitForVisibility(table_employees);

        By rows = By.xpath("//table[@id='employeesTable']//tbody/tr");
        WebElement row = fluentWait.waitForRowContainingText(rows, firstname);

        if (row == null) {
            return false;
        }

        return row.getText().contains(dependents);
    }

    public boolean employeeIsNot_presentInTable(String firstname) {

        fluentWait.waitForVisibility(table_employees);

        By rows = By.xpath("//table[@id='employeesTable']//tbody/tr");
        return fluentWait.waitForRowToBeInvisible(rows, firstname);
    }

    public boolean areBenefitCostCalculationCorrect(String firstname, int dependants_number) {
        DecimalFormat df = new DecimalFormat("#.#");

        double grossSalaryInitial = Double.parseDouble(ConfigReader.get("EmployeeInitialSalary"));
        double salaryCalculated = grossSalaryInitial * 26;

        double benefitCost = 1000.0 + (500.0 * dependants_number);
        double employeeDeduction = benefitCost * (Double.parseDouble(ConfigReader.get("deductionRate")) / 100);
        double netPay = grossSalaryInitial - employeeDeduction;

        By rows = By.xpath("//table[@id='employeesTable']//tbody/tr");
        WebElement row = fluentWait.waitForRowContainingText(rows, firstname);

        if (row == null) {
            return false;
        }

        String salaryDisplayed = row.findElement(By.xpath("./td[5]")).getText();
        String benefitCostDisplayed = row.findElement(By.xpath("./td[7]")).getText();
        String netPayDisplayed = row.findElement(By.xpath("./td[8]")).getText();

        return df.format(Double.parseDouble(salaryDisplayed)).equals(df.format(salaryCalculated)) && 
        df.format(Double.parseDouble(benefitCostDisplayed)).equals(df.format(employeeDeduction)) && 
        df.format(Double.parseDouble(netPayDisplayed)).equals(df.format(netPay));
    }
    
}
