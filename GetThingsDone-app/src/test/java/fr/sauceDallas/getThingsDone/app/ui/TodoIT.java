package fr.sauceDallas.getThingsDone.app.ui;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:fr/sauceDallas/getThingsDone/app/ui",
        plugin = "pretty",
        glue = "fr.sauceDallas.getThingsDone.app.ui")
public class TodoIT {
}
