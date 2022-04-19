package hurtMePlenty;

import hurtMePlenty.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HurtMePlentyRunner {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        AbstractPage.CloudGoogleHomePage homePage = new AbstractPage.CloudGoogleHomePage(driver);

        driver.manage().window().maximize();

        try {
            homePage.openPage()
                    .openSearchField()
                    .writeInSearchField()
                    .searchForAllResults()
                    .clickAtLinkToGoogleCloudPricingCalculator()
                    .fillInNumberOfInstances()
                    .setMachineClass()
                    .setMachineType()
                    .tickAtAddGPUCheckBox()
                    .setGPUType()
                    .setNumberOfGPU()
                    .setLocalSSD()
                    .setDatacenterLocation()
                    .clickToEstimateButton();
        } finally {
            driver.quit();
        }
    }
}
