package com.mustafaaslantas.runner;


import com.mustafaaslantas.utils.DriverFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/java/com/mustafaaslantas/features",
        glue = {"com.mustafaaslantas.stepDefinitions","com.mustafaaslantas.utils","com.mustafaaslantas"},
        plugin = {"summary", "pretty", "html:Reports/cucumber-reports.html", "json:Reports/cucumber.json"}
)
@CucumberContextConfiguration
@SpringBootTest(classes = TestRunner.class)
public class TestRunner {


}

