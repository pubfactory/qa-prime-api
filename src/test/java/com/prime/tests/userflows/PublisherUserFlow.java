/**
 * Testing the below functionality in Cite An Entry 

1. Verifying if the citation button is present in the article page
2. Verifying if citation pop-up is displayed when clicking on the citation button
3. Verifying if user is able to see RIS,BIB,ENW Button is present under Export citation section.
4. Verifying if user is able to download RIS,BIB,ENW formats
5. Verifying if user is able to close citation pop-up after use.

 */
package com.prime.tests.userflows;

import org.testng.annotations.Test;
import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PublisherUserFlow extends UserFlowDef {
    private String testCaseId;


    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"fpj"}, enabled = true, retryAnalyzer = Retry.class,
            description = "1721296  - Verify that the cite button available on current content page and  Preview/Export citation pop up will be displayed when clicked on it")
    @Story("EPIC-971")
    public void publisherUserFlow() throws Exception {
        testCaseId = "1721296";
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        browserInit();
        verifyCitationButonAvailableAndPreviewExportCitationPopUpWillBeDisplayedWhenClickedOnIt();
        // pdf();
    }
}
