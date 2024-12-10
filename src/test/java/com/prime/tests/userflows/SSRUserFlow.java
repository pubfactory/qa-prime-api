package com.prime.tests.userflows;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SSRUserFlow extends UserFlowDef {
    private String testCaseId;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"AP"}, enabled = true, retryAnalyzer = Retry.class, description = "101  - Verify SSR User flows")
    @Story("EPIC-3818")
    public void SSRUserFlowTest() throws Exception {
//    	soft = new SoftAssert();
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		testDataInIt(testCaseId);
		browserInit();    
		System.out.println("PageResource :"+driver.getPageSource());
		verifyTheSSRIsLoaded();   
//		System.out.println("Page source : " +driver.getPageSource());
//		descriptionMetaTag();
//		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
//		 masterPage.clickOnThefirstArticleFromTheOpenAccessArticles();
//		Thread.sleep(5000);
//		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
//		System.out.println(articleCitationPage.getCitationLastPageMetaTagPropertyValue()); 
//		assertEqualsoftAssert(soft, driver, articleCitationPage.getCitationLastPageMetaTagPropertyValue(),"219",
//				"Verifying the ciattaion last page content value is present."); 
    }
}
