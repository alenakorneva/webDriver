package hurtMePlenty.pages;

import hurtMePlenty.StaticTextVariables;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
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

    public void executeScriptToClick(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", webElement);
    }

    public void scrollUntilWebElementIsVisible(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static class CloudGoogleHomePage extends AbstractPage {

        public static final String HOMEPAGE_URL = "https://cloud.google.com/";

        @FindBy(xpath = "//button[@aria-label='Open search']")
        private WebElement openSearchFieldButton;

        @FindBy(xpath = "//input[@aria-label='Search']")
        private WebElement searchField;

        public CloudGoogleHomePage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @Override
        public CloudGoogleHomePage openPage(){
            driver.get(HOMEPAGE_URL);
            return this;
        }

        public CloudGoogleHomePage openSearchField(){
            executeScriptToClick(openSearchFieldButton);
            return this;
        }

        public CloudGoogleHomePage writeInSearchField(){
            waitUntilWebElementIsClickable(searchField);
            searchField.sendKeys(StaticTextVariables.TEXT_TO_ENTER_IN_SEARCH_FIELD);
            return this;
        }

        public SearchResultsForGoogleCloudPlatformPricingCalculator searchForAllResults(){
            searchField.sendKeys(Keys.ENTER);
            return new SearchResultsForGoogleCloudPlatformPricingCalculator(driver);
        }
    }

    public static class EstimationResultsPage extends AbstractPage {

        @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[3]/div")
        private WebElement VMClass;

        @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[4]/div[1]")
        private WebElement instanceType;

        @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[1]/div")
        private WebElement region;

        @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[7]/div[1]")
        private WebElement localSSD;

        @FindBy(xpath = "//*[@id=\"resultBlock\"]/md-card/md-card-content/div/div/div/h2/b")
        private WebElement totalEstimatedCost;

        public EstimationResultsPage(WebDriver driver) {
            super(driver);
        }

        @Override
        public AbstractPage openPage(){
            throw new RuntimeException("if you need direct access: " +
                    "avoid inheritance of AbstractPage " +
                    "or remove openPage() method from AbstractPage class.");
        }

        public String getTextOfVMClass(){
            return VMClass.getText();
        }

        public String getTextOfInstanceType(){
            return instanceType.getText();
        }

        public String getTextOfRegion(){
            return region.getText();
        }

        public String getTextOfLocalSSD(){
            return localSSD.getText();
        }

        public String getTextOfTotalEstimatedCost(){
            return totalEstimatedCost.getText();
        }
    }

    public static class GoogleCloudCalculator extends AbstractPage {

        @FindBy(xpath = "//button[@class='devsite-snackbar-action']")
        private WebElement popUpWindow;

        @FindBy(xpath = "//*[@id=\"cloud-site\"]/devsite-iframe/iframe")
        private WebElement firstFrame;

        @FindBy(id = "myFrame")
        private WebElement secondFrame;

        @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-toolbar/div/h2")
        private WebElement inputFieldForScrollingToDropDownListOfMachineClasses;

        @FindBy(xpath = "//*[@id=\"input_81\"]")
        private WebElement numberOfInstances;

        @FindBy(xpath = "//*[@id='select_value_label_74']/span[1]")
        private WebElement dropDownListOfMachineClasses;

        @FindBy(xpath = "//md-option[@id='select_option_97']")
        private WebElement preemptibleMachineClassOption;

        @FindBy(xpath = "//*[@id=\"mainForm\"]/div[2]/div/md-card/md-card-content/div/div[1]/form/div[7]/div[1]/md-input-container/label")
        private WebElement textForScrollingToMachineType;

        @FindBy(xpath = "//*[@id=\"select_value_label_77\"]/span[1]/div")
        private WebElement dropDownListOfMachineTypes;

        @FindBy(xpath = "//*[@id=\"select_option_424\"]/div[1]")
        private WebElement optionOfDropDownListOfMachineTypes;

        @FindBy(xpath = "//*[@id=\"mainForm\"]/div[2]/div/md-card/md-card-content/div/div[1]/form/div[11]/div[1]/md-input-container/md-checkbox/div[text()]")
        private WebElement textForScrollingToAddPUTick;

        @FindBy(xpath = "//*[@id=\"mainForm\"]/div[2]/div/md-card/md-card-content/div/div[1]/form/div[11]/div[1]/md-input-container/md-checkbox/div[1]")
        private WebElement addGPUTick;

        @FindBy(xpath = "//div[@id='select_container_109']")
        private WebElement dropDownListOfGPUTypes;

        @FindBy(xpath = "//div[@id='select_container_458']/md-select-menu/md-content/md-option[@value='NVIDIA_TESLA_V100']/div[@class='md-text ng-binding']")
        private WebElement requiredOptionOfGPUTypesList;

        @FindBy(xpath = "//label[text()='Number of GPUs']/following-sibling::md-select/md-select-value/span[1]")
        private WebElement dropDownListOfNumberOfGPUs;

        @FindBy(xpath = "//div[@id='select_container_460']/md-select-menu/md-content/md-option[@value='1']/div[@class='md-text ng-binding']")
        private WebElement requiredOptionOfGPUsNumberDropDownList;

        @FindBy(xpath = "//label[text()='Local SSD']/following-sibling::md-select/md-select-value/span[1]")
        private WebElement dropDownListOfLocalSSD;

        @FindBy(xpath = "//div[@id='select_container_420']/md-select-menu/md-content/md-option[@value='2']/div[@class='md-text ng-binding']")
        private WebElement requiredOptionOfLocalSSDDropDownList;

        @FindBy(xpath = "//label[text()='Datacenter location']/following-sibling::md-select/md-select-value/span[1]")
        private WebElement dropDownListOfDatacenterLocation;

        @FindBy(xpath = "//div[@id='select_container_115']/md-select-menu/md-content/md-optgroup/md-option[@value='europe-west3']/div[@class='md-text ng-binding']")
        private WebElement requiredOptionOfDatacenterLocationDropDownList;

        @FindBy(xpath = "//*[@id='mainForm']/div[2]/div/md-card/md-card-content/div/div[1]/form/div[19]/button")
        private WebElement estimateButton;

        public GoogleCloudCalculator(WebDriver driver) {
            super(driver);
        }

        @Override
        public AbstractPage openPage(){
            throw new RuntimeException("if you need direct access: " +
                    "avoid inheritance of AbstractPage " +
                    "or remove openPage() method from AbstractPage class.");
        }

        public void getRidOfPopUpWindow(){
            popUpWindow.click();
        }

        public GoogleCloudCalculator fillInNumberOfInstances(){
            //getRidOfPopUpWindow();

            driver.switchTo().frame(firstFrame);
            driver.switchTo().frame(secondFrame);
            waitUntilWebElementIsClickable(numberOfInstances);
            numberOfInstances.sendKeys("4");
            return this;
        }

        public GoogleCloudCalculator setMachineClass(){
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

            scrollUntilWebElementIsVisible(inputFieldForScrollingToDropDownListOfMachineClasses);

            Actions actions = new Actions(driver);
            actions.moveToElement(dropDownListOfMachineClasses).click().build().perform();

            javascriptExecutor.executeScript("arguments[0].click();", preemptibleMachineClassOption);
            preemptibleMachineClassOption.click();

            return this;
        }

        public GoogleCloudCalculator setMachineType(){
            scrollUntilWebElementIsVisible(textForScrollingToMachineType);

            executeScriptToClick(dropDownListOfMachineTypes);

            executeScriptToClick(optionOfDropDownListOfMachineTypes);
            return this;
        }

        public GoogleCloudCalculator tickAtAddGPUCheckBox(){
            scrollUntilWebElementIsVisible(textForScrollingToAddPUTick);
            executeScriptToClick(addGPUTick);
            return this;
        }

        public GoogleCloudCalculator setGPUType(){
            scrollUntilWebElementIsVisible(textForScrollingToAddPUTick);
            executeScriptToClick(dropDownListOfMachineTypes);
            executeScriptToClick(requiredOptionOfGPUTypesList);
            return this;
        }

        public GoogleCloudCalculator setNumberOfGPU(){
            scrollUntilWebElementIsVisible(dropDownListOfNumberOfGPUs);
            executeScriptToClick(dropDownListOfNumberOfGPUs);
            executeScriptToClick(requiredOptionOfGPUsNumberDropDownList);
            return this;
        }

        public GoogleCloudCalculator setLocalSSD(){
            scrollUntilWebElementIsVisible(dropDownListOfLocalSSD);
            executeScriptToClick(dropDownListOfLocalSSD);
            executeScriptToClick(requiredOptionOfLocalSSDDropDownList);
            return this;
        }

        public GoogleCloudCalculator setDatacenterLocation(){
            executeScriptToClick(dropDownListOfDatacenterLocation);
            executeScriptToClick(requiredOptionOfDatacenterLocationDropDownList);
            return this;
        }

        public EstimationResultsPage clickToEstimateButton(){
            executeScriptToClick(estimateButton);
            return new EstimationResultsPage(driver);
        }
    }

    public static class SearchResultsForGoogleCloudPlatformPricingCalculator extends AbstractPage {

        public SearchResultsForGoogleCloudPlatformPricingCalculator(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        @FindBy(xpath = "//a[@data-ctorig='https://cloud.google.com/products/calculator']")
        //@FindBy(xpath = "//*[@id=\"gc-wrapper\"]/main/devsite-content/article/article/div/div/div/devsite-analytics-scope/devsite-analytics-scope[1]/div[1]/a")
        private WebElement linkToGoogleCloudPricingCalculator;

        @Override
        public AbstractPage openPage(){
            throw new RuntimeException("if you need direct access: " +
                    "avoid inheritance of AbstractPage " +
                    "or remove openPage() method from AbstractPage class.");
        }

        public GoogleCloudCalculator clickAtLinkToGoogleCloudPricingCalculator(){
            waitUntilWebElementIsClickable(linkToGoogleCloudPricingCalculator);
            linkToGoogleCloudPricingCalculator.click();
            return new GoogleCloudCalculator(driver);
        }
    }
}
