package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import framework.DriverFactory;

public abstract class TestBasePage {

    protected DriverFactory driverFactory;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("ChromeHeadless") String browser) {
        driverFactory = new DriverFactory();
        driverFactory.initDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driverFactory != null && driverFactory.getDriver() != null) {
            driverFactory.quitDriver();
        }
    }
    
}
