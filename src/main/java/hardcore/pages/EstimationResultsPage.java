package hardcore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class EstimationResultsPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"cloud-site\"]/devsite-iframe/iframe")
    private WebElement firstFrame;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(xpath = "//*[@id=\"resultBlock\"]/md-card/md-card-content/div/div/div/h2/b")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']/md-toolbar/h2")
    private WebElement headerForScrollingToEmailForm;

    @FindBy(xpath = "//form[@name='emailForm']/md-content/div[3]/md-input-container/input")
    //@FindBy(xpath = "//label[text()='Email ']/following-sibling::input")
    private WebElement formToInsertRandomEmailAddress;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public EstimationResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AbstractPage openPage() {
        throw new RuntimeException("if you need direct access: " +
                "avoid inheritance of AbstractPage " +
                "or remove openPage() method from AbstractPage class.");
    }

    public String getTextOfTotalEstimatedCost() {
        return totalEstimatedCost.getText();
    }

    public EstimationResultsPage clickOnEmailEstimateButton() {
        executeScriptToClick(emailEstimateButton);
        return this;
    }

    public EstimationResultsPage insertRandomEmailAddress() {
        driver.switchTo().frame(firstFrame);
        driver.switchTo().frame(secondFrame);
        scrollUntilWebElementIsVisible(headerForScrollingToEmailForm);
        formToInsertRandomEmailAddress.sendKeys(YopMailPage.emailAddress);
        return this;
    }

    public EstimationResultsPage clickToSendEmailButton() {
        scrollUntilWebElementIsVisible(sendEmailButton);
        executeScriptToClick(sendEmailButton);
        return this;
    }

    public YopMailPage switchToYopMailPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        return new YopMailPage(driver.switchTo().window(tabs.get(1)));
    }
}
