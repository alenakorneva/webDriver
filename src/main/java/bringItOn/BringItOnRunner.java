package bringItOn;

import bringItOn.pages.PastebinHomePage;
import bringItOn.pages.ResultsPageBringItOn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BringItOnRunner {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        PastebinHomePage homePage = new PastebinHomePage(driver);

        driver.manage().window().maximize();

        ResultsPageBringItOn resultsPageBringItOn = homePage.openPage()
                .fillTextArea(TextVariables.CODE_FOR_TEXT_AREA_BRING_IT_ON)
                .setSyntaxHighlighting()
                .setPasteExpiration()
                .setPasteTitle(TextVariables.PASTE_TITLE_BRING_IT_ON)
                .clickToCreateNewPasteButton();

        System.out.println(resultsPageBringItOn.getClassAttributeOfSyntaxHighlightingList());
        System.out.println(resultsPageBringItOn.getTextOfPasteTitleWebElement());
        System.out.println(resultsPageBringItOn.getTextOfCodeForm());
    }
}
