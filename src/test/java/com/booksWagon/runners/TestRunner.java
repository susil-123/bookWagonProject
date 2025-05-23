package com.booksWagon.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/features", glue= {"com.booksWagon.stepDefinition"})
public class TestRunner extends AbstractTestNGCucumberTests{

}

