package com.rodriguezblanco.priceservice;

import org.junit.platform.suite.api.*;
import org.springframework.context.annotation.ComponentScan;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SuiteDisplayName("Price Service - Acceptance Test Suite")
@IncludeEngines("cucumber")
@SelectPackages("com.rodriguezblanco.priceservice")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.rodriguezblanco.priceservice")
@IncludeTags("acceptance")
@ComponentScan(basePackages = {"com.rodriguezblanco.priceservice"})
public class AcceptanceTest {
}
