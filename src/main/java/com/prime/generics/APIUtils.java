package com.prime.generics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.prime.api.helpers.TestDataFetcherAPIHelper;
import com.prime.pojo.smoketestdata.SiteData;
import com.prime.pojo.smoketestdata.TestDataResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIUtils {

    RequestSpecification req;
    ResponseSpecification res;

    /**
     * 
     * This method creates an API request and return the request spec object
     * 
     * @param : baseURI,platform,endpoint,site
     * @author Veena.Mathew
     * @Created Date : 24/08/2023
     */
    public RequestSpecification requestSpecification(String baseURI, String endpoint, String platform, String site, String status)

    {
        System.out.println("INSIDE SPEC" + site + platform + status);
        RestAssured.baseURI = baseURI;
        req = new RequestSpecBuilder().setBaseUri(baseURI).addHeader("x-pf-internal", "true").addPathParam("platform", platform).addPathParam("site", site).addPathParam("status", status).setContentType(ContentType.JSON)
                .build();
        return req;

    }

    /**
     * 
     * This method creates an API response and return the response spec object
     * builder
     * 
     * @author Veena.Mathew
     * @Created Date : 24/08/2023
     */
    public ResponseSpecification responseSpecification() {
        res = new ResponseSpecBuilder().build();
        return res;
    }

    public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(json, listType);
    }

    /**
     * 
     * This method takes identifies data associated with a particular platform and
     * stores it respective JSON file object builder
     * 
     * @author Veena.Mathew
     * @Created Date : 24/08/2023
     */

    public void getTestDataForApplication(String platform, String application, String status) throws IOException {
        SiteData siteData = new SiteData();
        String prettyJson = "";
        String siteName = application.toUpperCase();
        String jsonStr = "";
        Response response = TestDataFetcherAPIHelper.getTestData();
        // Getting site data list
        JsonPath jsonPathEvaluator = response.jsonPath();
        // List<SiteData> siteDataList = jsonPathEvaluator.get();
        List<SiteData> siteDataList = jsonArrayToList(response.asString(), SiteData.class);
        TestDataResponse testdataresponse = new TestDataResponse();
        testdataresponse.setSiteInfoList(siteDataList);
        System.out.println(siteDataList);
        System.out.println("HELLO!!!");
        for (int i = 0; i < testdataresponse.getSiteInfoList().size(); i++) {
            siteData = testdataresponse.getSiteInfoList().get(i);
            if (siteData.getSiteId().name.equalsIgnoreCase(application)) {
                ObjectMapper Obj = new ObjectMapper();
                try {
                    // Converting the Java object into a JSON string
                    jsonStr = Obj.writeValueAsString(siteData);
                    Obj.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = Obj.readTree(jsonStr);
                    prettyJson = Obj.writeValueAsString(jsonNode);
                    // Displaying Java object into a JSON string
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String fileName = System.getProperty("user.dir") + "/src/test/resources/" + siteName + "_Content.json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(prettyJson);

        writer.close();

    }

}
