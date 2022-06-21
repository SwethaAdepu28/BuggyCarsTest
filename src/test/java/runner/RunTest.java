package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "co.nz.stepDefinitions",
        plugin = {
                "html:target/cucumber-html-report.html"
        },
        tags = "@Regression"
)


public class RunTest {

    private static final Logger logger = LoggerFactory.getLogger(RunTest.class);


}
