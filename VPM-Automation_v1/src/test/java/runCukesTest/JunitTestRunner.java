package runCukesTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resources/features/Login.feature", plugin = { "pretty",
		"json:target/cucumber-reports/CucumberTestReport.json" }, glue = { "stepDefinitions" }, monochrome = true)
public class JunitTestRunner {

}