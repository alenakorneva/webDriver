package iCanWin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinHomePage extends AbstractPage {

    public static final String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy(xpath = "//button[text()='AGREE']")
    private WebElement agreeWithSettingsButton;

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement textArea;

    @FindBy(xpath = "//div[text()='Optional Paste Settings']")
    private WebElement optionalPasteSettings;

    @FindBy(xpath = "//label[text()='Paste Expiration:']/following-sibling::div/span/span/span/span[@role='presentation']")
    private WebElement pointerToGetListOfPasteExpirations;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement requiredOptionOfPasteExpirations;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement pasteTitle;

    @FindBy(xpath = "//button[@type='submit'][contains(text(), 'Create New Paste')]")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinHomePage agreeWithSettings(){
        agreeWithSettingsButton.click();
        return this;
    }

    public PastebinHomePage fillTextArea() {
        textArea.sendKeys("Hello from WebDriver");
        return this;
    }

    public PastebinHomePage setPasteExpiration() {

        scrollUntilWebElementIsVisible(optionalPasteSettings);
        waitUntilWebElementIsClickable(pointerToGetListOfPasteExpirations);
        pointerToGetListOfPasteExpirations.click();
        waitUntilWebElementIsClickable(requiredOptionOfPasteExpirations);
        requiredOptionOfPasteExpirations.click();
        return this;
    }

    public PastebinHomePage setPasteTitle() {
        pasteTitle.sendKeys("helloweb");
        return this;
    }

    public ResultsPage clickToCreateNewPasteButton() {
        createNewPasteButton.click();
        return new ResultsPage(driver);
    }
}
