package bringItOn.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected abstract AbstractPage openPage();
    protected final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(30);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitUntilWebElementIsClickable(WebElement webElement){
        try{
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
            return wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (NoSuchElementException e){
            throw new RuntimeException("Web element " + webElement + " is not visible within the time " + WAIT_TIMEOUT_SECONDS);
        }
    }

    public void scrollUntilWebElementIsVisible(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public void executeScriptToClick(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", webElement);
        webElement.click();
    }
}
