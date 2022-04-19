package hurtMePlenty;

import hurtMePlenty.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HurtMePlentyTest {
    WebDriver driver = new ChromeDriver();

    AbstractPage.CloudGoogleHomePage cloudGoogleHomePage = new AbstractPage.CloudGoogleHomePage(driver);

    AbstractPage.EstimationResultsPage estimationResultsPage;

    public AbstractPage.EstimationResultsPage doActionsToGetEstimationResults(){
        return cloudGoogleHomePage.openPage()
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
    }

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver.get("https://cloud.google.com/");
        driver.manage().window().maximize();
        estimationResultsPage = doActionsToGetEstimationResults();
    }

    @Test(description = "JIRA binding can be here")
    public void testVMClass(){
        Assert.assertEquals(estimationResultsPage.getTextOfVMClass(), StaticTextVariables.VM_CLASS, "VM class isn't right");
    }

    @Test(description = "JIRA binding can be here")
    public void testInstanceType(){
        Assert.assertEquals(estimationResultsPage.getTextOfInstanceType(), StaticTextVariables.INSTANCE_TYPE, "Instance type isn't right");
    }

    @Test(description = "JIRA binding can be here")
    public void testRegion(){
        Assert.assertEquals(estimationResultsPage.getTextOfRegion(), StaticTextVariables.REGION, "Region isn't right");
    }

    @Test(description = "JIRA binding can be here")
    public void testLocalSSD(){
        Assert.assertEquals(estimationResultsPage.getTextOfLocalSSD(), StaticTextVariables.LOCAL_SSD, "Local SSD isn't right");
    }

    @Test(description = "JIRA binding can be here")
    public void testTotalEstimatedCost(){
        Assert.assertEquals(estimationResultsPage.getTextOfTotalEstimatedCost(), StaticTextVariables.TOTAL_ESTIMATED_COST, "Total Estimated Cost isn't right");
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown(){
        driver.quit();
        driver = null;
    }
}
