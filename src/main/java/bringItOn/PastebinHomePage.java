package bringItOn;

import iCanWin.ResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinHomePage extends AbstractPage{

    public static final String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement textArea;

    @FindBy(xpath = "//span[text()='Syntax Highlighting']/following-sibling::div/label[@for='paste-raw-on']")
    private WebElement syntaxHighlightingToggleSwitch;

    @FindBy(xpath = "")
    private WebElement syntaxHighlightingDropDownList;



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
        String codeForTextArea = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        textArea.sendKeys(codeForTextArea);
    }

    public void setSyntaxHighlighting(){

    }

    public void setPasteExpiration(){

    }

    public void setPasteTitle(){
        pasteTitle.sendKeys("how to gain dominance among developers");
    }

    public ResultsPage searchForResults(){
        createNewPasteButton.click();
        return new ResultsPage(driver);
    }

}
