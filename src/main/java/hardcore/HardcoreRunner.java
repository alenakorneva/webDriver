package hardcore;

import hardcore.pages.CloudGoogleHomePage;
import hardcore.pages.EstimationResultsPage;
import hardcore.pages.GoogleCloudCalculator;
import hardcore.pages.SearchResultsForGoogleCloudPlatformPricingCalculator;
import hardcore.pages.YopMailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

public class HardcoreRunner {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        CloudGoogleHomePage googleHomePage = new CloudGoogleHomePage(driver);
        SearchResultsForGoogleCloudPlatformPricingCalculator searchResultsForGoogleCloudPlatformPricingCalculator;
        GoogleCloudCalculator googleCloudCalculator;
        EstimationResultsPage estimationResultsPage;
        YopMailPage yopMailPage = new YopMailPage(driver);

        driver.manage().window().maximize();

        try {
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

            String totalCostOnEstimationResultPage = estimationResultsPage.getTextOfTotalEstimatedCost();

            estimationResultsPage.clickOnEmailEstimateButton();

            openNewTab(driver);

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
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

            String totalCostInEmail = yopMailPage.getSendTotalEstimationCost();

            System.out.println(totalCostOnEstimationResultPage);
            System.out.println(totalCostInEmail);

            System.out.println(totalCostOnEstimationResultPage.contains(totalCostInEmail));
        } finally {
            driver.quit();
        }
    }

    public static void openNewTab(WebDriver driver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.open()");
    }
}
