package com.prime.tests.FPJ;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SignInTest extends BaseTest {
	private MasterPage masterPage;
	private SignInPage signInPage;
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"SignIn"},enabled = true,retryAnalyzer = Retry.class, description = "7 - Verify that Login(SIGN IN) link should available in header")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyThatSIGNLinkShouldAvailableInHeader(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.verifySignInLinkpresentOnHeader();
		masterPage.clickOnsignInlinkOnHeader();
		signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getSignInPersonalProfilePageHeaderText(),testData.get("signinpageheader").toString(), "Comparing the actual header and expected header of signIn page");
		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("invalidemailaddress").toString());
		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("invalidpassword").toString());
		signInPage.clickOnSUBMITButtonOnSIgnInPage();
		BaseTest.assertEquals(driver, signInPage.getHeaderErrorMessageOnSignInPage(), testData.get("headererrormsg").toString(), "Checking the header error message on signIn page");
		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("validemailaddress").toString());
		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("emptypassword").toString());
		signInPage.clickOnSUBMITButtonOnSIgnInPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessage(testData.get("passworderrmsg").toString()),testData.get("passworderrormessage").toString(), "Checking the password error message");
		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("emptyemailaddress").toString());
		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("emptypassword").toString());
		signInPage.clickOnSUBMITButtonOnSIgnInPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessage(testData.get("passworderrmsg").toString()),testData.get("passworderrormessage").toString(), "Checking the password error message");
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessage(testData.get("emailiderrmsg").toString()),testData.get("usernameerrormessage").toString(), "Checking the username or email Address error message");
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"SignIn"},enabled = true,retryAnalyzer = Retry.class, description = "8 - verify That With Valid Username And Empty Password The System Throws Appropriate Error Message")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyThatWithValidUsernameAndEmptyPasswordTheSystemThrowsAppropriateErrorMessage(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.clickOnsignInlinkOnHeader();
		signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("validemailaddress").toString());
		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("emptypassword").toString());
		signInPage.clickOnSUBMITButtonOnSIgnInPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessageColor(),testData.get("errormessagecolor").toString(),"Checking color of error message");
		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("validemailaddress").toString());
		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("validpassword").toString());
		signInPage.clickOnSUBMITButtonOnSIgnInPage();
		signInPage.verifyUserNamePresentOnHeader(testData.get("usernameonheader").toString());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"SignIn"},enabled = true,retryAnalyzer = Retry.class, description = "9 - Verify that the user should return to the page they were on after successfully logging in")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void VerifyThatUserShouldReturntoPageTheyWereOnAfterSuccessfullyLoggingIn(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.clickOnArticleUnderContentListOnHomePage();
		masterPage.clickOnsignInlinkOnHeader();
		signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("validemailaddress").toString());
		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("validpassword").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getHidePassword(), testData.get("hidepassword").toString(),"");
		signInPage.clickOnShowPasswordButtonOnSignInPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getShowPassword(), testData.get("showpassword").toString(),"");
		signInPage.clickOnSUBMITButtonOnSIgnInPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.getcontentListArticleTitleText(), testData.get("contentlisttitle").toString(), "User should be Returns content list title after successfully login");
		signInPage.verifySignOutLinkpresentAfterSuccessfullyLoginOnHeader();
		signInPage.clickOnsignOutlinkOnHeader();
	}
}
