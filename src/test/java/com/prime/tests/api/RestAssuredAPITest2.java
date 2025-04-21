package com.prime.tests.api;

import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredAPITest2 extends BaseTest {
    private BasePage basePage;
    private String url = "";
    private String testCaseId;
    private String mainWindow;
    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {
            "proxy" }, enabled = true, retryAnalyzer = Retry.class, description = "1737005 - API Test Verify")
    @Story("EPIC-1943")
    public void VerifyAPITests() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
       // OpenVPNConnector2 vpnConnector = new OpenVPNConnector2();
        try {
           // vpnConnector.connectToVPN();
            //Thread.sleep(10000); // Ensure VPN is connected

            // Make API Request
            RestAssured.baseURI = "https://prime-alert.prime-dev.pubfactory.net/"; // Ensure the API is accessible via VPN

            Response response = RestAssured.given()
                    .header("x-pf-internal", "true") // Add authentication if required
                    .header("Content-Type", "application/json")
                    .get("hort/rest/alert/v1/metrics");

            System.out.println("Response Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());

            System.out.println("Response: " + response.getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // vpnConnector.disconnectVPN();
        }
    }
}

