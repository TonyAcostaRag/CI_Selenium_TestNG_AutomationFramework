package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.GooglePage;

public class GoogleTest extends TestBasePage {

    GooglePage googlePage;
    
    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        googlePage = new GooglePage(driverFactory.getDriver());
    }

    @Test
    public void test_searchField() {
        System.out.println("---> Test 1.");
        googlePage.goToGoogle();
        googlePage.searchText("Google");
    }
    
    @Test
    public void absoluteXpathTest() {
        System.out.println("---> Test 2.");
        String xpath = googlePage.getAbsoluteXpath();

        Assert.assertTrue( xpath.contains("html") );
        System.out.println("---> Absolute Xpath: " + xpath);
    }

    @Test
    public void relativeXpathTest() {
        System.out.println("---> Test 3.");
        String xpath = googlePage.getRelativeXpath();

        Assert.assertFalse( xpath.contains("html") );
        System.out.println("---> Relative Xpath: " + xpath);
    }
    
}
