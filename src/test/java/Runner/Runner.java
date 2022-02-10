package Runner;

import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Steps",
        tags = "@PETSTORE",
        plugin = {"pretty", "json:reports/MyLovelyReport.json"},
        monochrome = true
)
public class Runner {
}

