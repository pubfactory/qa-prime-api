package com.prime.tests.userflows;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.prime.generics.BaseTest;
import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SSRUserFlow extends UserFlowDef {
    private String testCaseId;
    private String url = "";

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"AP"}, enabled = true, retryAnalyzer = Retry.class, description = "1735306  - Verify SSR User flows")
    @Story("EPIC-3818")
    public void SSRUserFlowTest() throws Exception {
//    	soft = new SoftAssert();
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		testDataInIt(testCaseId);
		browserInit();    
		url = BaseTest.properties.getProperty(application);
		System.out.println("PageResource :"+driver.getPageSource());
		verifyTheSSRIsLoaded();
//		verifytheMetaTagsForHomePage();
//		clicKOnTheFirstArticleFromOpenAccessArticle();
//		verifytheMetaTagsForArticlePage();
//		verifytheMetaTagsForIssuePage();
		verifyPageReturns200Response(testData.get("livehomeurl").toString(),"verifying the status code 200 is return on home page while enabling the SSR.");
		returnsTheServerIsUpMeassageText(url+testData.get("probe").toString());
		verifyPageReturns200Response(testData.get("livearticleurl").toString(),"verifying the status code 200 is return on article page while enabling the SSR.");
//		returnsTheServerIsUpMeassageText(url+testData.get("articleurlprobe").toString()+testData.get("probe").toString());
		VerifyPageTitleIsPresent(url,testData.get("hometitle").toString(),"Verifying the Title is present on home page while enabling the SSR.");
		VerifyPageTitleIsPresent(url+testData.get("articleurl").toString(),testData.get("articletitle").toString(),"Verifying the Title is present on Article page while enabling the SSR.");
		vreifyTheFileSizeOfPDF();
	
//		Thread.sleep(3000);
		assertClose();
    }
}
