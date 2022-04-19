package bringItOn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PastebinHomePage extends AbstractPage{

    public static final String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement textArea;

    @FindBy(xpath = "//span[text()='Syntax Highlighting']/following-sibling::div/label[@for='paste-raw-on']")
    private WebElement syntaxHighlightingToggleSwitch;

    @FindBy(xpath = "//label[text()='Syntax Highlighting:']/following-sibling::div/span/span/span/span[@role='presentation']")
    private WebElement pointerToGetSyntaxHighlightingDropDownList;

    @FindBy(xpath = "//li[text()='Bash']")
    private static WebElement optionOfSyntaxHighlightingList;

    @FindBy(xpath = "//label[text()='Paste Expiration:']/following-sibling::div/span/span/span/span[@role='presentation']")
    private WebElement pointerToGetListOfPasteExpirations;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement requiredOptionOfPasteExpirations;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement pasteTitle;

    @FindBy(xpath = "//button[@type='submit'][contains(text(), 'Create New Paste')]")
    private WebElement createNewPasteButton;

    @FindBy(xpath = "//*[@id='w0']/div[@class='content__title -no-border']")
    private WebElement elementForScrollingToSyntaxHighlightingDropDownList;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PastebinHomePage openPage(){
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinHomePage fillTextArea(String codeForTextArea){
        textArea.sendKeys(codeForTextArea);
        return this;
    }

    public PastebinHomePage setSyntaxHighlighting(){
        scrollUntilWebElementIsVisible(elementForScrollingToSyntaxHighlightingDropDownList);

        Actions actions = new Actions(driver);
        actions.moveToElement(syntaxHighlightingToggleSwitch).click().build().perform();

        executeScriptToClick(pointerToGetSyntaxHighlightingDropDownList);

        waitUntilWebElementIsClickable(optionOfSyntaxHighlightingList);
        optionOfSyntaxHighlightingList.click();
        return this;
    }

    public PastebinHomePage setPasteExpiration(){
        scrollUntilWebElementIsVisible(driver.findElement(By.xpath("//div[text()='Optional Paste Settings']")));

        waitUntilWebElementIsClickable(pointerToGetListOfPasteExpirations);
        pointerToGetListOfPasteExpirations.click();

        waitUntilWebElementIsClickable(requiredOptionOfPasteExpirations);
        requiredOptionOfPasteExpirations.click();
        return this;
    }

    public PastebinHomePage setPasteTitle(String pasteTitle){
        this.pasteTitle.sendKeys(pasteTitle);
        return this;
    }

    public ResultsPageBringItOn clickToCreateNewPasteButton(){
        createNewPasteButton.click();
        return new ResultsPageBringItOn(driver);
    }
}
