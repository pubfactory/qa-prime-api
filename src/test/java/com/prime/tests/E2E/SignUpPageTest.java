package com.prime.tests.E2E;

import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignupPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SignUpPageTest extends BaseTest {
	MasterPage masterPage;
	SignupPage signupPage;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = "SignUP", enabled = true, retryAnalyzer = Retry.class, description = "10 - Verify that user able to register")
	@Story("EPIC-")
	@Parameters({ "testcaseid" })
	public void VerifyThatUserAbletoRegister(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.verifySignUpLinkpresentOnHeader();
		masterPage.clickOnsignUplinkOnHeader();
		signupPage = BasePage.initialize(WebDriverManager.getDriver(), SignupPage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(), signupPage.getHeaderTextonSignUpPage(),testData.get("headertext").toString(), "asserting header text on SignUp Page");
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("firstnamelabel").toString(),testData.get("entrfirstname").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("lastnamelabel").toString(),testData.get("entrlastname").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("usernamelabel").toString(),testData.get("entrusername").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("emaladdresslabel").toString(),testData.get("entremail").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("passwordlabel").toString(),testData.get("entrpassword").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("confirmpasswordlabel").toString(),testData.get("entrconfirmpassword").toString());
		signupPage.clickOnVerifyCheckBoxSighUpPage();
		signupPage.acceptTermAndConditionOnSignInPage();
		signupPage.clickOnSUBMITButtonOnSignUpPage();
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = "SignUP", enabled = true, retryAnalyzer = Retry.class, description = "11 - Verify That all Error On SignUp Page")
	@Story("EPIC-")
	@Parameters({ "testcaseid" })
	public void VerifyThatallErrorOnSignUpPage(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.verifySignUpLinkpresentOnHeader();
		masterPage.clickOnsignUplinkOnHeader();
		signupPage = BasePage.initialize(WebDriverManager.getDriver(), SignupPage.class);
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("firstnamelabel").toString(),testData.get("entrfirstname").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("lastnamelabel").toString(),testData.get("entrlastname").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("usernamelabel").toString(),testData.get("entrusername").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("emaladdresslabel").toString(),testData.get("entremail").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("passwordlabel").toString(),testData.get("entrpassword").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("confirmpasswordlabel").toString(),testData.get("entrconfirmpassword").toString());
		signupPage.clickOnVerifyCheckBoxSighUpPage();
		signupPage.acceptTermAndConditionOnSignInPage();
		signupPage.clickOnSUBMITButtonOnSignUpPage();
		List<String> allerror=Arrays.asList(testData.get("allerrormsg").toString().split(","));
		BaseTest.assertEquals(WebDriverManager.getDriver(), signupPage.getAllFieldErrorOnSignUpPage().toString(),allerror.toString(), "asserting all field error on SignUp Page");
	
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = "SignUP", enabled = true, retryAnalyzer = Retry.class, description = "12 - Verify that User Able to hide and see password On SignUp Page")
	@Story("EPIC-")
	@Parameters({ "testcaseid" })
	public void VerifyThatUserAbleToHideandShowPasswordOnSignUpPage(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.clickOnsignUplinkOnHeader();
		signupPage = BasePage.initialize(WebDriverManager.getDriver(), SignupPage.class);
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("firstnamelabel").toString(),testData.get("entrfirstname").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("lastnamelabel").toString(),testData.get("entrlastname").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("usernamelabel").toString(),testData.get("entrusername").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("emaladdresslabel").toString(),testData.get("entremail").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("passwordlabel").toString(),testData.get("entrpassword").toString());
		signupPage.entertextInTextBoxOnSignUpPage(testData.get("confirmpasswordlabel").toString(),testData.get("entrconfirmpassword").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), signupPage.getHideorShowPasswordOnSignUpPage(testData.get("passwordtextbox").toString()),testData.get("hidepasswordytype").toString(), "checking the password is hide or not");
		signupPage.clickOnShowPasswordButtonOnSighUpPage(testData.get("passwordbutton").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), signupPage.getHideorShowPasswordOnSignUpPage(testData.get("passwordtextbox").toString()),testData.get("showpasswordtype").toString(), "checking the password is visible or not");
		BaseTest.assertEquals(WebDriverManager.getDriver(), signupPage.getHideorShowPasswordOnSignUpPage(testData.get("confpasswordtextbox").toString()),testData.get("hidepasswordytype").toString(), "checking the password is hide or not");
		signupPage.clickOnShowPasswordButtonOnSighUpPage(testData.get("confpasswordbutton").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), signupPage.getHideorShowPasswordOnSignUpPage(testData.get("confpasswordtextbox").toString()),testData.get("showpasswordtype").toString(), "checking the password is visible or not");
		
	}
}
