package runners;
//Test Runner Class to link Feature files and Step Definitions
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-html-report/index.html","json:target/cucumber.json" },
    monochrome = true
)
public class TestRunner  {
}
