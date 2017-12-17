package com.workshop;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.configuration.ConfigurationProperties.DriverLifecycle;
import org.fluentlenium.configuration.ConfigurationProperties.TriggerMode;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@FluentConfiguration(screenshotMode = TriggerMode.AUTOMATIC_ON_FAIL, screenshotPath = "bin//Screenshots//", driverLifecycle = DriverLifecycle.JVM)
public class BaseTestNg extends FluentTestNg {

	@BeforeSuite
	@Override
	public WebDriver newWebDriver() {
		System.setProperty("webdriver.gecko.driver", "src//test//resources//geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	@AfterSuite
	private void killDriver() throws Exception {
		String[] cmd = { "C:\\WINDOWS\\system32\\cmd.exe", "/c", "taskkill /F /IM geckodriver.exe /T" };

		Runtime runtime = Runtime.getRuntime();

		Process p = runtime.exec(cmd);
		p.waitFor(2, TimeUnit.SECONDS);
		p.destroyForcibly();
	}

	// region Helper Methods
	// public void clickOnElementByLinkText(String giveLinkText) {
	// await().atMost(10,
	// TimeUnit.SECONDS).until($(By.linkText(giveLinkText))).clickable();
	// $(By.linkText(giveLinkText)).click();
	// }

	// public void doubleClickOnDropdown(FluentWebElement expectElement) {
	// await().atMost(10, TimeUnit.SECONDS).until(expectElement).clickable();
	// expectElement.doubleClick();
	// }

	public void clickOnElement(FluentWebElement expectElement) {
		await().atMost(10, TimeUnit.SECONDS).until(expectElement).clickable();
		expectElement.click();
	}

	public void enterTextAndPressEnter(FluentWebElement expectElement, String giveText) {
		await().atMost(10, TimeUnit.SECONDS).until(expectElement).clickable();

		expectElement.clear();
		expectElement.fill().with(giveText);
		// expectElement.keyboard().sendKeys(Keys.ENTER);
	}

	// endregion

}
