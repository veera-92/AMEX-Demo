package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String LOGIN_URL = "https://practicetestautomation.com/practice-test-login/";

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(LOGIN_URL);
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("I click the submit button")
    public void iClickTheSubmitButton() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submitButton.click();
    }

    @Then("I should be redirected to the logged-in successfully page")
    public void iShouldBeRedirectedToTheLoggedInSuccessfullyPage() {
        wait.until(ExpectedConditions.urlContains("practicetestautomation.com/logged-in-successfully/"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("URL does not match expected pattern", 
            currentUrl.contains("practicetestautomation.com/logged-in-successfully/"));
    }

    @Then("the page should contain {string}")
    public void thePageShouldContain(String expectedText) {
        String pageSource = driver.getPageSource();
        Assert.assertTrue("Page does not contain expected text: " + expectedText, 
            pageSource.contains(expectedText) || pageSource.contains("successfully logged in"));
    }

    @Then("the {string} button should be displayed")
    public void theButtonShouldBeDisplayed(String buttonText) {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(buttonText)));
        Assert.assertTrue("Log out button is not displayed", logoutButton.isDisplayed());
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        Assert.assertTrue("Error message is not displayed", errorMessage.isDisplayed());
    }

    @Then("the error message text should be {string}")
    public void theErrorMessageTextShouldBe(String expectedErrorText) {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        String actualErrorText = errorMessage.getText();
        Assert.assertEquals("Error message text mismatch", expectedErrorText, actualErrorText);
    }

    // Clean up browser session automatically after every scenario
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}