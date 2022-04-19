package hardcore;

import hardcore.pages.CloudGoogleHomePage;
import hardcore.pages.EstimationResultsPage;
import hardcore.pages.GoogleCloudCalculator;
import hardcore.pages.SearchResultsForGoogleCloudPlatformPricingCalculator;
import hardcore.pages.YopMailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HardcoreTest {
    WebDriver driver = new ChromeDriver();
    CloudGoogleHomePage googleHomePage = new CloudGoogleHomePage(driver);
    SearchResultsForGoogleCloudPlatformPricingCalculator searchResultsForGoogleCloudPlatformPricingCalculator;
    GoogleCloudCalculator googleCloudCalculator;
    EstimationResultsPage estimationResultsPage;
    YopMailPage yopMailPage = new YopMailPage(driver);
    String totalCostOnEstimationResultPage;
    String totalCostInEmail;

    public void doActionsToGetTotalEstimationCost(){
        searchResultsForGoogleCloudPlatformPricingCalculator = googleHomePage.openPage()
                .openSearchField()
                .writeInSearchField()
                .searchForAllResults();

        googleCloudCalculator = searchResultsForGoogleCloudPlatformPricingCalculator.clickAtLinkToGoogleCloudPricingCalculator();

        estimationResultsPage = googleCloudCalculator.fillInNumberOfInstances()
                .setMachineClass()
                .setMachineType()
                .tickAtAddGPUCheckBox()
                .setGPUType()
                .setNumberOfGPU()
                .setLocalSSD()
                .setDatacenterLocation()
                .setCommittedUsage()
                .clickToEstimateButton();

        totalCostOnEstimationResultPage = estimationResultsPage.getTextOfTotalEstimatedCost();

        estimationResultsPage.clickOnEmailEstimateButton();

        openNewTab(driver);

        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        yopMailPage.openPage();
        yopMailPage.clickToAcceptRecommendedCookiesButton()
                .clickToGenerateRandomEmailAddress()
                .saveRandomEmail();

        driver.switchTo().window(tabs.get(0));

        estimationResultsPage
                .insertRandomEmailAddress()
                .clickToSendEmailButton();
        estimationResultsPage.switchToYopMailPage();

        yopMailPage.clickToCheckEmailsButton()
                .openMail();

        totalCostInEmail = yopMailPage.getSendTotalEstimationCost();
    }

    public static void openNewTab(WebDriver driver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open()");
    }

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver.get("https://cloud.google.com/");
        driver.manage().window().maximize();
        doActionsToGetTotalEstimationCost();
    }

    @Test(description = "JIRA binding can be here")
    public void testEmailAndEstimationResultsPageCostsSimilarity(){
        Assert.assertTrue(totalCostOnEstimationResultPage.contains(totalCostInEmail));
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown(){
        driver.quit();
        driver = null;
    }
}
