package iCanWin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage extends AbstractPage{

    public ResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AbstractPage openPage(){
        throw new RuntimeException("if you need direct access: " +
                "avoid inheritance of AbstractPage " +
                "or remove openPage() method from AbstractPage class.");
    }
}
