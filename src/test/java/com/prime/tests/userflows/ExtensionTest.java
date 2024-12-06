package com.prime.tests.userflows;


import org.testng.annotations.Test;
import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ExtensionTest extends UserFlowDef {
    private String testCaseId;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"AP"}, enabled = true, retryAnalyzer = Retry.class, description = "1733809  - Verify Librarian User flows")
    @Story("EPIC-3818")
    public void librarianUserFlow() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        testDataInIt(testCaseId);
        browserInit();
        System.out.println("Page source : " + driver.getPageSource());
        descriptionMetaTag();
    }
}
