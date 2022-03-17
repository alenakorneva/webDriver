package iCanWin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class NewPasteCreationTest {

    WebDriver driver = new ChromeDriver();

    PastebinHomePage homePage = new PastebinHomePage(driver);

    public void doActionsAtPastebinHomePage(){
        homePage.openPage();
        homePage.fillTextArea();
        homePage.setPasteExpiration();
        homePage.setPasteTitle();
    }

    //ResultsPage resultsPage = homePage.searchForResults();

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver.get("https://pastebin.com");
        driver.manage().window().maximize();
        doActionsAtPastebinHomePage();
    }

    @Test(description = "JIRA binding can be here")
    public void requiredCodeIsLoadedAtTextArea(){
        homePage.openPage();
        homePage.fillTextArea();
        homePage.setPasteExpiration();
        homePage.setPasteTitle();
        ResultsPage resultsPage = homePage.searchForResults();
        List<WebElement> textOfRequiredCode = resultsPage.driver.findElements(By.xpath("//div[text()='Hello from WebDriver']"));
        Assert.assertTrue(textOfRequiredCode.size() > 0, "Form with required code is empty");
    }

    @Test
    public void pasteExpirationIsSet(){
        homePage.openPage();
        homePage.fillTextArea();
        homePage.setPasteExpiration();
        homePage.setPasteTitle();
        ResultsPage resultsPage = homePage.searchForResults();
        List<WebElement> textOfRequiredCode = resultsPage.driver.findElements(By.xpath("//span[@id='select2-postform-expiration-container'][contains(text(), '10 Minutes')]"));
        Assert.assertTrue(textOfRequiredCode.size() > 0, "Paste Expiration isn't set");
    }

    @Test
    public void pasteTitleIsSet(){
        homePage.openPage();
        homePage.fillTextArea();
        homePage.setPasteExpiration();
        homePage.setPasteTitle();
        ResultsPage resultsPage = homePage.searchForResults();
        List<WebElement> textOfRequiredCode = resultsPage.driver.findElements(By.xpath("//h1[text()='helloweb']"));
        Assert.assertTrue(textOfRequiredCode.size() > 0, "Paste Title isn't set");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
