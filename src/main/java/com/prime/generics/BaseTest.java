package com.prime.generics;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlSuite;
import com.prime.pageFactory.pages.fpj.MasterPage;
import io.qameta.allure.Allure;
import okhttp3.Cookie;

public class BaseTest {

    private static final String EXECUTION_MODE_WEBDRIVER = "local";
    private static final String EXECUTION_MODE_GRID = "remote";
    private static final String BROWSER_CHROME = "Chrome";
    private static final String BROWSER_FIREFOX = "Firefox";
    private static final String BROWSER_IE = "IE";
    private static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_KEY = "webdriver.gecko.driver";
    private static final String IE_DRIVER_KEY = "webdriver.ie.driver";
    private static final String CHROME_DRIVER_VALUE = "chromedriver.exe";
    private static final String FIREFOX_DRIVER_VALUE = "geckodriver.exe";
    private static final String IE_DRIVER_VALUE = "IEDriverServer.exe";
    private static final String USERNAME1 = "username";
    private static final String PASSWORD1 = "password";
    private static final String APP_URL = "appUrl";
    public static final String USERNAME = "kglselectsignal_7pEVPF";
    public static final String AUTOMATE_KEY = "3HpPxaxVR3GLRjgxWg2K";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public Set<Cookie> allCookies;
    public static List<String> domainName = new ArrayList<String>();
    protected WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();

    private DesiredCapabilities capability;
    private static String executionMode;
    private static String browser;
    private static String username;
    private static String password;
    private static String appUrl;
    private static String gridIP;
    private static String groupname;

    public String subtitle;
    public String title;
    public String acronym;
    public String volume;
    public String issue;
    public String isbn;

    private static final String DATETIME_FORMAT = "yyyyMMdd_HH.mm.ss a";
    private String tcId = "";
    private boolean integrationStatus;

    public static Properties properties;

    public MasterPage masterPage;

    public int counter = 0;
    public int maxAttempt = 1;
    private Map<String, String> map = new HashMap<String, String>();
    private JSONArray jsonarray;
    private Object id;
    private String keyStr;
    private Object keyvalue;
    private String testCaseId;
    private static String error;
    protected String status;
    protected String teststatus;
    private XmlSuite suite;
    private String browserstack;
    private JSONParser parser;
    protected String application;
    protected String baseURI;
    protected String platform;
    protected String suitefilepathnamee;
    private EdgeOptions edgeoptions;
    public static String testCaseName;

    public static String TCNUM = "";
    protected static Map<String, String> transcationNumberCollector = new HashMap<>();
    private static String testRailIdQA;
    private static String testRailId4344;
    private static String testRailIdCore;
    private static String testRailId;

    private static String twistUserName = "thenmozhi.vijay@tnqsoftware.co.in";
    private static String twistPassWord = "123456";
    public static String suiteName;
    public static List<String> userList = new ArrayList<>();
    public static List<String> publisherList = new ArrayList<>();
    public static String env;

    /**
     * 
     * This method used to initialize Database properties file which is contains url
     * and credentials , creating object for File Upload manager which is used to
     * upload the pdf files in portal and share the publisher randomly to thread
     * 
     * @param context
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    @BeforeSuite(alwaysRun = true)
    public void establishConnection(ITestContext context) throws InstantiationException, IllegalAccessException, Exception {
        try {
            Helper.INSTANCE.logEventInfoToReport("Before Suite");
            this.properties = new Properties();
            FileInputStream fStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
            properties.load(fStream);
            String env = BaseTest.properties.getProperty("Environment");
            //String env = System.getProperty("Environment");
            System.out.println("ENV=" + env);
            //this.loadUrlFromEnvProperties(env);
            this.loadUrlFromEnvProperties("testRail");
            System.out.println("PROP=" + properties);
            System.out.println("threadId " + Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * THis method is used to find url of the platform to be loaded
     * 
     * @param env
     * @author Veena.Mathew
     * @throws IOException
     * @Created Date : 21/08/2023
     */

    public void loadUrlFromEnvProperties(String env) throws IOException {

        FileInputStream fStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/" + env + ".properties"));
        properties.load(fStream);
    }

    /**
     * This method is used to pick the publisher randomly
     * 
     * @param threadCount
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    private List<String> multiPublisher(int threadCount) {
        List<String> publishers = null;
        try {
            publishers = new ArrayList<>();
            String[] publisherArray = {"PROD TEST A1", "PROD TEST A2", "PROD TEST"};
            int publisherCount = publisherArray.length;
            for (int i = 0; i < threadCount; i++) {
                if (i == publisherCount) {
                    i = 0;
                    threadCount = threadCount - publisherCount;
                }
                publishers.add(publisherArray[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return publishers;

    }

    /**
     * This methods used to initialize the report generation, shared the driver
     * object to page object class trigger PrerequisiteSuite suites, Handling
     * Unexpectedly Application logged out
     * 
     * @param testcaseid
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */

    @Parameters({"browser", "browser_version", "os", "os_version"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browserName, @Optional String browser_version, @Optional String os, @Optional String os_version, @Optional Method name) {
        try {
            Helper.INSTANCE.logEventInfoToReport("Before Method");
            System.out.println(suiteName + " is Started");
            if (WebDriverManager.getFlagChecker() == null || !WebDriverManager.getFlagChecker()) {
                browserstack = BaseTest.properties.getProperty("BrowserStack");
                if (browserstack.equalsIgnoreCase("Y")) {
                    setupEnvironment(browserName, browser_version, os, os_version, name);
                } else if (!browserstack.equalsIgnoreCase("Y")) {
                    setupEnvironment();
                }
                System.out.println("*******************Suite name is :" + suiteName);
                WebDriverManager.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                WebDriverManager.setFlagChecker(true);
            } else {
                throw new SkipException("Unexpectedly Application logged out");
            }
        } catch (Exception | AssertionError e) {
        }
    }

    /**
     * This method used to get values from testng suite xml
     * 
     * @param context
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    @BeforeTest(alwaysRun = true)
    public void loadConfigurationValues(ITestContext context) {

        try {
            Helper.INSTANCE.logEventInfoToReport("Before Test");
            ConfigurationManager.createManager(context);
            executionMode = ConfigurationManager.getExecutionMode();
            gridIP = ConfigurationManager.getGridIP();
            username = ConfigurationManager.getConfigurationValue(USERNAME1);
            password = ConfigurationManager.getConfigurationValue(PASSWORD1);
            appUrl = ConfigurationManager.getConfigurationValue(APP_URL);
            // integrationStatus = ConfigurationManager.getTestRailIntegrationMode();
            browser = ConfigurationManager.getBrowser();
            //          groupname = ConfigurationManager.getGroupName();
            testRailId = BaseTest.properties.getProperty("testRunId");
            suiteName = context.getSuite().getName();
            suite = context.getSuite().getXmlSuite();
            //String env = BaseTest.properties.getProperty("Environment");
            String executionMode = BaseTest.properties.getProperty("executionMode");
            if (executionMode.equalsIgnoreCase("remote")) {
                //System.setProperty("Environment", suite.getParameter("Environment"));
                //System.setProperty("application", suite.getParameter("application"));
                env = System.getProperty("Environment");
                application = System.getProperty("application");
            } else {
                env = BaseTest.properties.getProperty("Environment");
                application = BaseTest.properties.getProperty("application");
            }
            this.loadUrlFromEnvProperties(env);
            System.out.println("PROP after loading env info = " + properties);

            //  String env = System.getProperty("env.name");
            //String env = BaseTest.properties.getProperty("Environment");

            System.out.println("Environment=" + env);
            suitefilepathnamee = suite.toString();
            if (suitefilepathnamee.contains("Core Case") && env.equalsIgnoreCase("staging")) {
                testRailId = BaseTest.properties.getProperty("staging_core_case_testrunid");
            } else if (suitefilepathnamee.contains("Gates") && env.equalsIgnoreCase("staging")) {
                testRailId = BaseTest.properties.getProperty("staging_gates_testrunid");
            }
            application = BaseTest.properties.getProperty("application");
            baseURI = BaseTest.properties.getProperty("baseURI_Search");
            platform = BaseTest.properties.getProperty("platform");
            status = BaseTest.properties.getProperty("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method used to create a driver object for given browser for execution on
     * Browser Stack Environment based on browserName,browser_version,os,os_version
     * parameters from XML File
     * 
     * @param name
     * @param os_version
     * @param os
     * @param browser_version
     * @param browserName
     * @param name
     * @param versionName
     * @param platformName
     * @param browserName
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    private void setupEnvironment(String browserName, String browser_version, String os, String os_version, Method name) {
        try {
            System.out.println("browser name is : " + browserName);
            String methodName = name.getName();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os", os);
            caps.setCapability("os_version", os_version);
            caps.setCapability("browser_version", browser_version);
            caps.setCapability("name", methodName);
            if (browserName.equals("Chrome")) {
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                caps.setCapability("browser", "Chrome");
            } else if (browserName.equals("Firefox")) {
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                caps.setCapability("browser", "Firefox");
            } else if (browserName.equals("Edge")) {
                io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                caps.setCapability("browser", "Edge");
            }
            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            WebDriverManager.setWebDriver(driver);
        } catch (Exception ex) {
            try {
                String browser = BaseTest.properties.getProperty("browser");
                String executionMode = BaseTest.properties.getProperty("executionMode");
                if (executionMode.equalsIgnoreCase("local")) {
                    if (browser.equalsIgnoreCase("chrome")) {
                        System.out.println("******Enter Chrome Browser*****" + browser);
                        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--start-maximized");
                        options.addArguments("--disable-extensions");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-extensions");
                        options.addArguments("--dns-prefetch-disable");
                        options.addArguments("--disable-gpu");
                        Thread.sleep(Integer.parseInt(BasePage.randomWait()));
                        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                        chromePrefs.put("hardware_acceleration_mode.enabled", false);
                        chromePrefs.put("download.prompt_for_download", false);
                        chromePrefs.put("profile.default_content_settings.popups", 0);
                        chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
                        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                        chromePrefs.put("profile.block_third_party_cookies", true);
                        chromePrefs.put("safebrowsing.enabled", true);
                        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "target/Assets");
                        options.setExperimentalOption("prefs", chromePrefs);
                        options.setPageLoadStrategy(PageLoadStrategy.NONE);
                        Thread.sleep(Integer.parseInt(BasePage.randomWait()));
                        System.out.println("******Before Chrome Driver*****" + browser);
                        driver = new ChromeDriver(options);
                        System.out.println("******After Chrome Driver*****" + driver);
                    }
                    WebDriverManager.setWebDriver(driver);
                }
            } catch (Exception e) {

            }
        }
    }

    /**
     * This method used to create a driver object for given browser for execution on
     * Browser Stack Environment based on browserName,browser_version,os,os_version
     * parameters from XML File
     * 
     * @param name
     * @param os_version
     * @param os
     * @param browser_version
     * @param browserName
     * @param name
     * @param versionName
     * @param platformName
     * @param browserName
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    private void setupEnvironment() {
        try {
            String browser = BaseTest.properties.getProperty("browser");
            String executionMode = BaseTest.properties.getProperty("executionMode");
            if (executionMode.equalsIgnoreCase("local") || (executionMode.equalsIgnoreCase("remote"))) {
                if (browser.equalsIgnoreCase("chrome")) {
                    System.out.println("******Enter Chrome Browser*****" + browser);
                    //               io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    System.out.println(System.getProperty("user.dir"));
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver");
                    //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--dns-prefetch-disable");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
                    if (BaseTest.properties.getProperty("headLess").equalsIgnoreCase("Y")) {
                        options.addArguments("--headless");
                        options.addArguments("--window-size=1400,600");
                    } else {
                        options.addArguments("--start-maximized");
                    }
                    Thread.sleep(Integer.parseInt(BasePage.randomWait()));
                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("hardware_acceleration_mode.enabled", false);
                    chromePrefs.put("download.prompt_for_download", false);
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
                    chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                    chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                    chromePrefs.put("profile.block_third_party_cookies", true);
                    chromePrefs.put("safebrowsing.enabled", true);
                    chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "target\\Assets");
                    options.setExperimentalOption("prefs", chromePrefs);
                    options.setPageLoadStrategy(PageLoadStrategy.NONE);
                    Thread.sleep(Integer.parseInt(BasePage.randomWait()));
                    System.out.println("******Before Chrome Driver*****" + browser);
                    driver = new ChromeDriver(options);
                    System.out.println("******After Chrome Driver*****" + driver);
                } else if (browser.equalsIgnoreCase("firefox")) {
                    System.out.println("****** Initiate Firefox Browser using " + browser + " *****");
                    FirefoxOptions options = new FirefoxOptions();
                    if (BaseTest.properties.getProperty("headLess").equalsIgnoreCase("Y")) {
                        options.setHeadless(true);
                    }
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options);
                    System.out.println("******After Firefox Driver*****" + driver);
                } else if (browser.equalsIgnoreCase("edge")) {
                    System.out.println("****** Initiate Edge Browser using " + browser + " *****");
                    DesiredCapabilities capabilities = DesiredCapabilities.edge();
                    edgeoptions = new EdgeOptions();

                    if (BaseTest.properties.getProperty("headLess").equalsIgnoreCase("Y")) {

                        // edgeoptions.addArguments("--headless");

                    }
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(edgeoptions);
                    System.out.println("******After Edge Driver*****" + driver);
                }

                WebDriverManager.setWebDriver(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method is used to login application randomly using different credentials
     * 
     * @param threadCount
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    private List<String> multiLogin(int threadCount) {
        List<String> logins = null;
        try {
            logins = new ArrayList<>();
            String[] userArray = {"auto3@sheridan.com/Qwerty12345678", "auto4@sheridan.com/Qwerty12345678", "auto2@sheridan.com/Qwerty12345678", "auto1@sheridan.com/Qwerty12345678"};
            int userCount = userArray.length;
            for (int i = 0; i < threadCount; i++) {
                if (i == userCount) {
                    i = 0;
                    threadCount = threadCount - userCount;
                }
                logins.add(userArray[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return logins;
    }

    /**
     * It is used to get test case id from testCase.json
     * 
     * @param testcaseid
     * @return JSONObject
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public JSONObject getDetails(String testcaseid) throws Exception {
        JSONObject finalObj = null;
        try {
            parser = new JSONParser();
            application = BaseTest.properties.getProperty("application");
            this.fetchTestDataApplicationWise(application);
            switch (application) {
                case "fpj":
                    jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/FPJ_TestData.json"))));
                    break;
                case "tsir":
                    jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/TSIR_TestData.json"))));
                    break;
                default:
                    throw new Exception("Not a valid application Choice");
            }

            for (Object jsonobj : jsonarray) {
                JSONObject myObj = (JSONObject) jsonobj;
                String id = myObj.get("id").toString();
                if (id.equalsIgnoreCase(testcaseid)) {
                    finalObj = (JSONObject) myObj.get("data");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to Find TestCase " + testcaseid);
        }
        return finalObj;
    }

    public void fetchTestDataApplicationWise(String application) throws Exception {
        switch (application) {
            case "fpj":
                jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/FPJ_TestData.json"))));
                break;
            case "tsir":
                jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/TSIR_TestData.json"))));
                break;

            case "anesthesiaprogress":
                jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/ANESTHESIAPROGRESS_TestData.json"))));
                break;
            default:
                throw new Exception("Not a valid application Choice");
        }
    }

    /**
     * This method performs to wait until the element visible in DOM of the page
     * without waitForDocumentReady() method
     * 
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public boolean waitForElementVisibleWDR(WebElement element) throws Exception {
        boolean elementPresent = false;
        try {
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Visible & Stable");
            elementPresent = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "Error", element, e.getMessage() + Thread.currentThread().getId());
        } finally {

        }
        return elementPresent;
    }

    /**
     * This method used to get the JSON value using key from testCase.json
     * 
     * @param object
     * @param keyName
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public String getData(JSONObject object, String keyName) throws Exception {
        try {
            return object.get(keyName.toLowerCase()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "Json File-GetData", "Keyname : " + keyName + " not found");
            throw new Exception(keyName + " not found");
        }
    }

    /**
     * This method used to close the application
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public void closeApplication() throws Exception {
        try {
            System.out.println("After quit  :" + WebDriverManager.getDriver().toString());
            Helper.INSTANCE.logEventInfoToReport(driver.getCurrentUrl());
            if (WebDriverManager.getDriver().toString().contains("null")) {
                System.out.println("check browser ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to quit the driver object
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    @AfterSuite(alwaysRun = true)
    public void closeDriver() throws Exception {
        try {
            Helper.INSTANCE.logEventInfoToReport("After Suite");

            WebDriverManager.closeDriver();
            WebDriverManager.resetFlagMap().clear();


            System.out.println("After suite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method log Event Information update execution status in case all failure
     * is observed and also captures and updates in Browser Stack
     * 
     * @param testcaseId
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    @AfterMethod(alwaysRun = true)
    @Parameters({"testcaseid"})
    public void closeApplication(@Optional String testcaseId) throws Exception {
        try {
            Helper.INSTANCE.logEventInfoToReport("After Method");
            Helper.INSTANCE.logEventInfoToReport("testcase=" + testcaseId);
            // testcaseId = WebDriverManager.getTestcaseIdTestRail();
            teststatus = Helper.INSTANCE.getErrorMessage(testcaseId);
            //          try {
            //              status = Helper.INSTANCE.getErrorMessage(testcaseId);
            //          } catch (NullPointerException e) {
            //              status = "";
            //
            //          }
            if (teststatus != null) {
                if (!suiteName.contains("Default suite")) {
                    Helper.INSTANCE.publishResults(testRailId, testcaseId, "5", teststatus);
                }
                Allure.addAttachment("Test Failed!", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                Allure.step(testCaseId + " :: " + teststatus);
                browserstack = BaseTest.properties.getProperty("BrowserStack");
                if (browserstack.equalsIgnoreCase("Y")) {
                    final JavascriptExecutor jse = (JavascriptExecutor) driver;
                    JSONObject executorObject = new JSONObject();
                    JSONObject argumentsObject = new JSONObject();
                    argumentsObject.put("status", "failed");
                    argumentsObject.put("reason", teststatus);
                    executorObject.put("action", "setSessionStatus");
                    executorObject.put("arguments", argumentsObject);
                    jse.executeScript(String.format("browserstack_executor: %s", executorObject));
                }
            } else {
                if (!suiteName.contains("Default suite")) {
                    Helper.INSTANCE.publishResults(testRailId, testcaseId, "1", "Test Passed Successfully");
                }
                Allure.addAttachment("Test Passed Successfully", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                browserstack = BaseTest.properties.getProperty("BrowserStack");
                if (browserstack.equalsIgnoreCase("Y")) {
                    final JavascriptExecutor jse = (JavascriptExecutor) driver;
                    JSONObject executorObject = new JSONObject();
                    JSONObject argumentsObject = new JSONObject();
                    argumentsObject.put("status", "passed");
                    argumentsObject.put("reason", "TEST PASSED SUCCESSFULLY");
                    executorObject.put("action", "setSessionStatus");
                    executorObject.put("arguments", argumentsObject);
                    jse.executeScript(String.format("browserstack_executor: %s", executorObject));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            Helper.INSTANCE.logEventInfoToReport("Execution Completed for " + testCaseId);
            Allure.step("Fetching Current URL: " + WebDriverManager.getDriver().getCurrentUrl());
            //  Helper.INSTANCE.setErrorMessage(testcaseId, "");

        }
    }

    /**
     * This method takes allure screenshot at end step for Pass and Fail Cases
     * 
     * @param driver
     * @param name
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 23rd Dec 2022
     */
    public synchronized void takeScreenshot(WebDriver driver, String name) throws Exception {
        try {
            Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method used to navigate the URL
     * 
     * @param url
     * @param drivers
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    private void navigateToURL(String url, WebDriver driver) {
        try {
            driver.get(url);
            Allure.step("Opening URL: " + url);
        } catch (Exception e) {
            Assert.fail("Failure while opening URL: " + url);
        }

    }

    /**
     * setter method initializes testcase id
     * 
     * @param testCaseName
     * @param testCaseId
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    protected void setTestCaseId(String testCaseName, String testCaseId) {
        map.put(testCaseName, testCaseId);
    }

    /**
     * This method used to get testcase id
     * 
     * @param testName
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    protected String getTestCaseId(String testName) {
        return map.get(testName);
    }

    /**
     * This method asserts expected and actual value
     * 
     * @param driver
     * @param actual
     * @param expected
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public static void assertEquals(WebDriver driver, String actual, String expected, String desc) throws Exception {
        String description = desc + " :: " + " Expected Result--> " + expected + " || " + "Actual Result--> " + actual;
        if (expected.equalsIgnoreCase(actual)) {
            Allure.step(description);
        } else {
            Allure.step("Assertion Failed: " + description);
            try {
                Allure.addAttachment(description, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            error = description + " mismatch found";
            Helper.INSTANCE.setErrorMessage(WebDriverManager.getTestcaseIdTestRail(), error);
            Assert.fail(description);
        }
    }

    /**
     * This method asserts expected and actual value
     * 
     * @param driver
     * @param actual
     * @param expected
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public static void assertEquals(WebDriver driver, int actual, int expected, String desc) throws Exception {
        String description = desc + " :: " + " Expected Result--> " + expected + " || " + "Actual Result--> " + actual;
        if (expected == actual) {
            Allure.step(description);
        } else {
            Allure.step("Assertion Failed: " + description);
            try {
                Allure.addAttachment(description, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            error = description + " mismatch found";
            Helper.INSTANCE.setErrorMessage(WebDriverManager.getTestcaseIdTestRail(), error);
            Assert.fail(description);
        }
    }

    public static void assertTrue(WebDriver driver, boolean expected, String desc) throws Exception {
        // String description = desc + " :: " + " Expected Result--> " + expected + " ||
        // " + "Actual Result--> " + actual;
        if (expected == true) {
            Allure.step(desc);
        } else {
            Allure.step("Assertion Failed: " + desc);
            try {
                Allure.addAttachment(desc, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            error = desc + " expected true but found false";
            Helper.INSTANCE.setErrorMessage(WebDriverManager.getTestcaseIdTestRail(), error);
            Assert.fail(desc);
        }
    }

    /**
     * This method used to get testcase id from json file
     * 
     * @param testCaseName
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public String retrieveTCID(String testCaseName) throws Exception {
        jsonarray = getTCDetails();

        for (Object jsonobj : jsonarray) {
            JSONObject myObj = (JSONObject) jsonobj;

            id = readJsonObject(myObj, testCaseName);
        }
        return id.toString();
    }

    /**
     * This method used to get key value from json file
     * 
     * @param jsonObj
     * @return Object
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public Object getTestCaseID(JSONObject jsonObj) {
        for (Object key : jsonObj.keySet()) {
            keyStr = (String) key;
            keyvalue = jsonObj.get(keyStr);
            if (keyStr.equalsIgnoreCase("id")) {
                return keyvalue;
            }
        }
        return keyvalue.toString();
    }

    /**
     * This method used to read the json object and return test case id
     * 
     * @param jsonObj
     * @param desc
     * @return Object
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public Object readJsonObject(JSONObject jsonObj, String desc) {
        for (Object key : jsonObj.keySet()) {
            if (key.toString().equals("data")) {
                // based on you key types
                String keyStr = (String) key;
                Object keyvalue = jsonObj.get(keyStr);

                // Print key and value
                if (keyvalue.toString().contains(desc)) {
                    id = getTestCaseID(jsonObj);
                    break;
                }
                return id;
            }
        }
        return id;
    }

    /**
     * This method used to get the testcase details from json file
     * 
     * @return JSONArray
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public JSONArray getTCDetails() throws Exception {
        try {
            parser = new JSONParser();
            application = BaseTest.properties.getProperty("application");
            String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
            this.getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonarray;
    }

    /**
     * This method wait for load through Javascript Executor
     * 
     * @param driver
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    public void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 50).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * This method navigates the specific URL
     * 
     * @param url
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public void navigateToURL(String url) throws Exception {
        try {
            driver.get(properties.getProperty(url));
            Allure.step("Opening URL: " + url);
            WebDriverManager.getWebdriverWait().until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
                }
            });
        } catch (Exception e) {
            Assert.fail("Failure while opening URL: " + url);
        }
    }

    /**
     * This method navigates the specific URL
     * 
     * @param url
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public void navigateToUrl(String url) throws Exception {
        try {
            url = BaseTest.properties.getProperty(url);
            WebDriverManager.getDriver().get(url);
            Allure.step("Opening URL: " + url);
            waitForLoad(driver);
        } catch (Exception e) {
            Assert.fail("Failure while opening URL: " + url);
        }
    }


    /**
    * This method navigates the specific URL when you have the link
    * 
    * @param url
    * @throws Exception
    * @author Veena.Mathew
    * @Created Date : 26 Sep 2022
    */
    public void navigateToUrlLink(String url) throws Exception {
        try {
            // url = BaseTest.properties.getProperty(url);
            WebDriverManager.getDriver().get(url);
            Allure.step("Opening Application: " + application);
            waitForLoad(driver);
            BasePage basePage = new BasePage(WebDriverManager.getDriver());
            int j = driver.findElements(By.xpath("//button[text()='Ok']")).size();
            if (j > 0) {
                WebElement Ok = WebDriverManager.getDriver().findElement(By.xpath("//button[text()='Ok']"));
                basePage.clickOnElement(Ok, "Clicking on ok button on popup On HomePage");
            }
        } catch (Exception e) {
            error = "Failure while opening URL: " + url;
            Helper.INSTANCE.setErrorMessage(WebDriverManager.getTestcaseIdTestRail(), error);
            Assert.fail(error);
        }
    }


    /**
     * It is used to get test case id from testCase.json
     * 
     * @param testcaseid
     * @return JSONObject
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 28/08/2023
     */
    public JSONObject getTestDataDetailsWithFileName(String testcaseid, String filename) throws Exception {
        JSONObject finalObj = null;
        try {
            parser = new JSONParser();
            application = BaseTest.properties.getProperty("application");
            jsonarray = (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(new File("./src/test/resources/" + filename))));

            for (Object jsonobj : jsonarray) {
                JSONObject myObj = (JSONObject) jsonobj;
                String id = myObj.get("id").toString();
                if (id.equalsIgnoreCase(testcaseid)) {
                    finalObj = (JSONObject) myObj.get("data");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Unable to Find TestCase " + testcaseid);
        }
        return finalObj;
    }

    /**
     * This method used to Verify the URL
     * @throws Exception
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 27/09/23
     */

    public static boolean verifyTextInURL(String linkText) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), 5);
            wait.until(ExpectedConditions.urlContains(linkText));
            return true;
        } catch (Exception ex) {
            Assert.fail(linkText + " not found");
            return false;
        }
    }

    /**
     * This methos automatically generates allure html report
     * 
     * @throws IOException
     */
    public void allureReporting() throws IOException {
        Runtime.getRuntime().exec(new String[] {"mvn allure:serve"});

    }

    public static void deleteDonwloadedFile() throws IOException {
        File f = new File(System.getProperty("user.dir") + "//target//Assets");
        FileUtils.deleteDirectory(f);
    }

    /**
     * This method asserts expected and actual value
     * 
     * @param driver
     * @param actual
     * @param expected
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @return 
     * @Created Date : 14/12/2022
     */
    public static void assertEquals(WebDriver driver, boolean actual, boolean expected, String desc) throws Exception {
        String description = desc + " :: " + " Expected Result--> " + expected + " || " + "Actual Result--> " + actual;
        if (expected == actual) {
            Allure.step("Assertion Passed: " + description);
        } else {
            Allure.step("Assertion Failed: " + description);
            error = description + " mismatch found";
            Helper.INSTANCE.setErrorMessage(WebDriverManager.getTestcaseIdTestRail(), error);
            Assert.fail(description);
        }
    }


    /**This method is used to verify PagInation Link size change after applying filter 
     * 
     * @param totalResult
     * @param afterApplyFilter
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/10/2023
     */
    public static boolean VerifyPagInationLinksizeChange(int defaultPagination, int selectePagination) throws Exception {
        if (defaultPagination > selectePagination) {
            return true;
        } else
            return false;
    }


    /** This method is used to verify search result count change after applying filter 
     * 
     * @param totalResult
     * @param afterApplyFilter
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/10/2023
     */
    public static boolean VerifyTotalResultCOuntChangeAfterApplyingFilter(int totalResult, int afterApplyFilter) throws Exception {
        if (totalResult > afterApplyFilter) {
            return true;
        } else
            return false;
    }

    public static String getLastsixStringCharacter(String str) {
        String finalWord = str.substring(str.length() - 6, str.length());
        return finalWord;

    }

    public static boolean verifyStringContainsSpecificWord(String str, String word) {
        return str.contains(word);
    }

    public static void deletedownloadedFiles(String fileExtension) {
        File folder = new File(System.getProperty("user.dir") + File.separator + "target\\Assets");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Check if the file is a PDF file (you can customize the check if needed)
                    if (file.isFile() && file.getName().toLowerCase().endsWith(fileExtension)) {
                        // Attempt to delete the file
                        if (file.delete()) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete file: " + file.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }

    }

    /** This method is used wait until the URL gets loads 
     * 
     * @param totalResult
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     */
    public static void waitUntilTheURLGetLoads(String linkText) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), 30);
        wait.until(ExpectedConditions.urlContains(linkText));
    }

}


