package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.BrowserActions;

public class GooglePage extends BrowserActions {

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//textarea[@id='APjFqb']")
    WebElement searchBox;

    public void goToGoogle() {
        getUrl("https://www.google.com/");
    }

    public void searchText (String text) {
        inputText(searchBox, text);
    }

    public String getAbsoluteXpath() {
        return "/html/body/div/div[3]/form/div/div/div/div/div[2]/textarea[@id='APjFqb']";
    }

    public String getRelativeXpath() {
        return "//textarea[@id='APjFqb']";
    }
    
}
