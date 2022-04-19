package bringItOn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPageBringItOn extends AbstractPage{

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement pasteTitle;

    @FindBy(xpath = "//div[@class='source']")
    private WebElement codeInForm;

    @FindBy(xpath = "//div[@class='source']/ol")
    private WebElement syntaxHighlighting;

    public ResultsPageBringItOn(WebDriver driver){
        super(driver);
    }

    public String getTextOfPasteTitleWebElement(){
        waitUntilWebElementIsClickable(pasteTitle);
        return pasteTitle.getText();
    }

    public String getTextOfCodeForm(){
        waitUntilWebElementIsClickable(codeInForm);
        return codeInForm.getText();
    }

    public String getClassAttributeOfSyntaxHighlightingList(){
        waitUntilWebElementIsClickable(syntaxHighlighting);
        return syntaxHighlighting.getAttribute("class");
    }

    @Override
    public AbstractPage openPage(){
        throw new RuntimeException("if you need direct access: " +
                "avoid inheritance of AbstractPage " +
                "or remove openPage() method from AbstractPage class.");
    }
}
