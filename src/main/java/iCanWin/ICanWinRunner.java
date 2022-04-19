package iCanWin;

import iCanWin.pages.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ICanWinRunner {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        PastebinHomePage homePage = new PastebinHomePage(driver);

        driver.manage().window().maximize();

        homePage.openPage()
                .agreeWithSettings()
                .fillTextArea()
                .setPasteExpiration()
                .setPasteTitle()
                .clickToCreateNewPasteButton();
    }
}
