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

public class FinalUserFlows extends UserFlowDef {

	
    private String testCaseId;


    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"fpj"}, enabled = true, retryAnalyzer = Retry.class,
            description = "11  - Verify Publisher User flows")
    @Story("EPIC-971")
    public void publisherUserFlow() throws Exception {
    	SoftAssert soft = new SoftAssert();
        testCaseId = "1721296";
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        testDataInIt(testCaseId);
        browserInit();
        driver.get("https://meridian-anesthesiaprogress-draft.prime-dev.pubfactory.com/journal/anpr/current");
        selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList();
        
        
        
    }
}
