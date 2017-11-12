package com.workshop;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.configuration.ConfigurationProperties.DriverLifecycle;
import org.fluentlenium.configuration.ConfigurationProperties.TriggerMode;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

@FluentConfiguration(screenshotMode = TriggerMode.AUTOMATIC_ON_FAIL, screenshotPath = "bin//Screenshots//", driverLifecycle = DriverLifecycle.CLASS)

public class BaseTestNg extends FluentTestNg {

	@BeforeSuite
	@Override
	public WebDriver newWebDriver() {
		System.setProperty("webdriver.gecko.driver", "src//test//resources//geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	// region Helper Methods
	public void clickOnElementByLinkText(String giveLinkText) {
		await().atMost(10, TimeUnit.SECONDS).until($(By.linkText(giveLinkText))).clickable();

		$(By.linkText(giveLinkText)).click();
	}

	public void clickOnElementFunctional(FluentWebElement expectElement) {
		// the method that works for chrome
		await().atMost(10, TimeUnit.SECONDS).until(expectElement).clickable();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", expectElement);
	}

	public void doubleClickOnDropdown(FluentWebElement expectElement) {
		await().atMost(10, TimeUnit.SECONDS).until(expectElement).clickable();
		expectElement.doubleClick();
	}

	public void enterTextAndSelectInDropdown(FluentWebElement expectElement, String giveText) {
		await().explicitlyFor(5, TimeUnit.SECONDS);
		expectElement.fill().with(giveText);
		expectElement.keyboard().sendKeys(Keys.ENTER);
	}

	// endregion

}
