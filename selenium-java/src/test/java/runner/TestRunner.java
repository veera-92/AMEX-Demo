package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features", // Path to your feature files
    glue = {"stepdefinitions"},               // Package containing step definition classes
    plugin = {"pretty", "html:target/cucumber-reports.html"} // Generates a clean HTML test report
)
public class TestRunner {
}
