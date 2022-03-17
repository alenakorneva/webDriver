package iCanWin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PastebinHomePage extends AbstractPage{

    public static final String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement textArea;

    /*@FindBy(xpath = "//label[text()='Paste Expiration:']/following-sibling::div/span[@id='select2-postform-expiration-container']")
    private WebElement listOfPasteExpirations;*/
    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement pointerToGetListOfPasteExpirations;

    //xpath к уже выбранной строке списка
    @FindBy(xpath = "//span[@id='select2-postform-expiration-container'][contains(text(), '10 Minutes')]")
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
    public PastebinHomePage openPage(){
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public void fillTextArea(){
        textArea.sendKeys("Hello from WebDriver");
    }

    public void setPasteExpiration(){
        pointerToGetListOfPasteExpirations.click();
        requiredOptionOfPasteExpirations.click();
    }

    public void setPasteTitle(){
        pasteTitle.sendKeys("helloweb");
    }

    public ResultsPage searchForResults(){
        createNewPasteButton.click();
        return new ResultsPage(driver);
    }
}
