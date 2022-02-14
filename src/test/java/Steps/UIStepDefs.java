package Steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class UIStepDefs {

    private WebDriver driver;

    @Given("the browser is at the main page")
    public void theBrowserIsAtTheMainPage() {


        boolean OS = System.getProperty("os.name").startsWith("Linux");
        if (OS) {
            // String currentDir = System.getProperty("user.dir");
            // System.setProperty("webdriver.chrome.driver", currentDir + "/Driver/chromedriver");

            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("disable-infobars"); // disabling infobars
            opt.addArguments("--disable-extensions"); // disabling extensions
            opt.addArguments("--no-sandbox"); // Bypass OS security model
            opt.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            opt.addArguments("--window-size=1920,1080");
            opt.addArguments("--disable-gpu");
            opt.addArguments("--proxy-server='direct://'");
            opt.addArguments("--proxy-bypass-list=*");
            opt.addArguments("--start-maximized");
            opt.addArguments("--headless");
            driver = new ChromeDriver(opt);
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Given("the practice form is shown")
    public void thePracticeFormIsShown() {
        driver.get("https://demoqa.com/text-box");
    }

    @When("the user completes the data correctly")
    public void theUserCompletesTheDataCorrectly() {
        String idUserName = "userName";
        String xpathEmail = "//input[@id='userEmail']";
        String idCurrentAddress = "currentAddress";
        String idPermanentAddress = "permanentAddress";
        String submitButton = "submit";

        driver.findElement(By.id(idUserName)).sendKeys("Myuser");
        driver.findElement(By.xpath(xpathEmail)).sendKeys("Email@thisemail.com");
        driver.findElement(By.id(idCurrentAddress)).sendKeys("There should be an address here");
        driver.findElement(By.id(idPermanentAddress)).sendKeys("Another one?");

        //Had to do this because of an ad...
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)", "");

        driver.findElement(By.id(submitButton)).click();
    }

    @Then("a success message is shown and contains my user")
    public void aSuccessMessageIsShown() {
        String output = "output";
        WebElement success = driver.findElement(By.id(output));
        success.getText().contains("Myuser");
        driver.quit();

    }


    @When("the user completes the data with wrong email")
    public void theUserCompletesTheDataWithWrongEmail() {
        String idUserName = "userName";
        String xpathEmail = "//input[@id='userEmail']";
        String idCurrentAddress = "currentAddress";
        String idPermanentAddress = "permanentAddress";
        String submitButton = "submit";

        driver.findElement(By.id(idUserName)).sendKeys("Myuser");
        driver.findElement(By.xpath(xpathEmail)).sendKeys("Email");
        driver.findElement(By.id(idCurrentAddress)).sendKeys("There should be an address here");
        driver.findElement(By.id(idPermanentAddress)).sendKeys("Another one?");

        //Had to do this because of an ad...
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)", "");

        driver.findElement(By.id(submitButton)).click();
    }

    @Then("a message is shown")
    public void aMessageIsShown() {
        //well... there is no message but a red thingy. It would be nice to have at least a label.
        driver.quit();

    }

    @When("the user completes the data with special characters")
    public void theUserCompletesTheDataWithSpecialCharacters() {
        String idUserName = "userName";
        String xpathEmail = "//input[@id='userEmail']";
        String idCurrentAddress = "currentAddress";
        String idPermanentAddress = "permanentAddress";
        String submitButton = "submit";

        driver.findElement(By.id(idUserName)).sendKeys("#asda$%");
        driver.findElement(By.xpath(xpathEmail)).sendKeys("$%(()$$$");
        driver.findElement(By.id(idCurrentAddress)).sendKeys("T%&$$&e");
        driver.findElement(By.id(idPermanentAddress)).sendKeys("A%$&$/& one?");

        //Had to do this because of an ad...
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)", "");

        driver.findElement(By.id(submitButton)).click();
    }
}
