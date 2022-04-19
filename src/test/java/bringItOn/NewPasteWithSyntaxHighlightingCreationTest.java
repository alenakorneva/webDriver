package bringItOn;

import bringItOn.pages.PastebinHomePage;
import bringItOn.pages.ResultsPageBringItOn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewPasteWithSyntaxHighlightingCreationTest {
    WebDriver driver = new ChromeDriver();

    PastebinHomePage pastebinHomePage = new PastebinHomePage(driver);

    ResultsPageBringItOn resultsPageBringItOn;

    public ResultsPageBringItOn doActionsOnPastebinHomePage(){
        return pastebinHomePage
                .fillTextArea(TextVariables.CODE_FOR_TEXT_AREA_BRING_IT_ON)
                .setSyntaxHighlighting()
                .setPasteExpiration()
                .setPasteTitle(TextVariables.PASTE_TITLE_BRING_IT_ON)
                .clickToCreateNewPasteButton();
    }

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver.get("https://pastebin.com");
        driver.manage().window().maximize();
        resultsPageBringItOn = doActionsOnPastebinHomePage();
    }

    @Test(description = "JIRA binding can be here")
    public void pasteTitleTest(){
        Assert.assertEquals(resultsPageBringItOn.getTextOfPasteTitleWebElement(), TextVariables.PASTE_TITLE_BRING_IT_ON, "Paste title isn't right");
    }

    @Test(description = "JIRA binding can be here")
    public void syntaxIsHighlighted(){
        Assert.assertEquals(resultsPageBringItOn.getClassAttributeOfSyntaxHighlightingList(), "bash", "Syntax isn't highlighted for bash");
    }

    @Test(description = "JIRA binding can be here")
    public void codeCorrespondsTask(){
        Assert.assertEquals(resultsPageBringItOn.getTextOfCodeForm(), TextVariables.CODE_FOR_TEXT_AREA_BRING_IT_ON, "Code doesn't corresponds the task");
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
