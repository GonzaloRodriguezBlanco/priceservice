package com.rodriguezblanco.priceservice.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.rodriguezblanco.priceservice.integration")
@IncludeTags("integration")
public class IntegrationSuite {
}
