package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Steps",
        tags = "@PETSTORE",
        plugin = {"pretty" ,"html:reports/MyLovelyReport.html"}


)
public class Runner {

}
