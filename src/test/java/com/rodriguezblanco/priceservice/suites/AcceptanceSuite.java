package com.rodriguezblanco.priceservice.suites;

import org.junit.platform.suite.api.*;
import org.springframework.context.annotation.ComponentScan;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = FILTER_TAGS_PROPERTY_NAME,
        value = "@acceptance"
)
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.rodriguezblanco.priceservice"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value ="pretty,summary"
)
@ComponentScan(basePackages = {"com.rodriguezblanco.priceservice"})
public class AcceptanceSuite {
}
