package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature", //path to feature folder for execution
        glue = "steps", //path to step definition folder
        plugin = {
                "pretty", // generate pretty console output
                "json:target/cucumber-report/cucumber.json", // generate JSON report
                "html:target/cucumber-report/report.html" // generate HTML report
        }
)
public class CucumberTestRunner {
}
