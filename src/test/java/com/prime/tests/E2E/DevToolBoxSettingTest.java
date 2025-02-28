
/**
 * Testing the below functionality in Home Page  


1. Verifying if the Dev tool setting button is present in the home page
2. Verifying if user is able to click on the settings button
3. Verifying if user Show Regions Button in the pop up 
4. Verifying if user is able to turn on the Show Regions Option


 */
package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class DevToolBoxSettingTest extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String url = "";
	private String testCaseId;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"Citation" }, enabled = true, retryAnalyzer = Retry.class, description = "1729937  - Verify that the dev tool box functnoality in the home page works")
	@Story("EPIC-971")
	public void veryifyDevToolboxSettingFunctionality() throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		// String application = System.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifyDevToolsSettings(), true,
				"Verifying the Dev tool setting is present or not on home page");
		masterPage.clickOnDevToolSetting();
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifyShowRegionsKeysSettings(), true,
				"Verifying the Region Key setting is present in devtool gear icon");
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifyShowMissingComponents(), true,
				"Verifying the Show Missing Components is present in devtool gear icon");
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifyBypassMetaDataCatche(), true,
				"Verifying the Bypass MetaData Cache is present in devtool gear icon");
		masterPage.clickOnRegionKeySetting();
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				masterPage.getLabelNameFromSignInPostShowRegionKeysOON().startsWith("[layout."), true,
				"Verify if ShowKeyRegion is turned ON");
Thread.sleep(3000);
	}
}
