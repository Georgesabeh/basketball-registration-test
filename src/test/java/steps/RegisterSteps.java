package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegisterSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("the user is on the registration page")
    public void the_user_is_on_the_registration_page() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    private void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @When("the user enters first name {string}")
    public void enterFirstName(String firstName) {
        By locator = By.id("member_firstname");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(firstName);
    }

    @When("the user enters last name {string}")
    public void enterLastName(String lastName) {
        if (!lastName.isEmpty()) {
            By locator = By.id("member_lastname");
            waitForElement(locator);
            driver.findElement(locator).sendKeys(lastName);
        }
    }

    @When("the user enters email {string}")
    public void enterEmail(String email) {
        By locator = By.id("member_emailaddress");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(email);
    }

    @When("the user enters confirm email {string}")
    public void enterConfirmEmail(String confirmEmail) {
        By locator = By.id("member_confirmemailaddress");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(confirmEmail);
    }

    @When("the user enters password {string}")
    public void enterPassword(String password) {
        By locator = By.id("signup_unlicenced_password");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(password);
    }

    @When("the user enters confirm password {string}")
    public void enterConfirmPassword(String confirmPassword) {
        By locator = By.id("signup_unlicenced_confirmpassword");
        waitForElement(locator);
        driver.findElement(locator).sendKeys(confirmPassword);
    }

    @When("the user selects date of birth {string}")
    public void selectDob(String dob) {
        String[] parts = dob.split("/"); // DD/MM/YYYY
        new Select(driver.findElement(By.id("member_dateofbirth_3i"))).selectByValue(parts[0]);
        new Select(driver.findElement(By.id("member_dateofbirth_2i"))).selectByValue(parts[1]);
        new Select(driver.findElement(By.id("member_dateofbirth_1i"))).selectByValue(parts[2]);
    }

    @When("the user accepts terms {string}")
    public void acceptTerms(String accept) {
        WebElement checkbox = driver.findElement(By.id("iagree"));
        if (accept.equalsIgnoreCase("true") && !checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @When("the user clicks the register button")
    public void clickRegister() {
        By locator = By.name("commit");
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    @Then("the user should see {string}")
    public void verifyResult(String expected) throws InterruptedException {
        Thread.sleep(5000); // Gör att sidan visas innan den stängs
        boolean found = driver.getPageSource().toLowerCase().contains(expected.toLowerCase());
        Assert.assertTrue("Expected message not found: " + expected, found);
        driver.quit();
    }
}
