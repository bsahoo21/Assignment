package cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/java/features/TaskTracking.feature" ,glue = {"stepDefinitions"},plugin = {"pretty","html:src/test/java/reports/cucumber.html"})
public class TestRunner {
}
