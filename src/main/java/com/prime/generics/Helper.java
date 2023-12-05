package com.prime.generics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.prime.testRail.APIClient;
import com.prime.testRail.APIException;
import io.qameta.allure.Allure;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public enum Helper {
    INSTANCE;

    private static final String DATETIME_FORMAT = "yyyyMMdd_HH.mm.ss a";
    private static final String CURRENTDATE_FORMAT = "MM/dd/YYYY";
    // public static boolean log4jInitialized = false;
    public static Connection connection;
    public static ResultSet resultSet;
    private static Map<String, String> transcationNumbers = new HashMap<String, String>();
    private static Map<String, String> productIds = new HashMap<String, String>();
    private static Map<String, String> ReportNames = new HashMap<String, String>();
    private String result;
    private List<String> list;
    private String failedstep;
    private static Map<String, String> ErrorMap = new HashMap<String, String>();

    private String testRunId = "";
    private String serverURL = "";
    private String serverUsername = "";
    private String serverPassword = "";
    private String error;
    private String tcId;
    private String browserstack;

    private Helper() {

    }

    /**
     * This method is used to generate random Name
     * 
     * @param base
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String generateRandomName(String base) {
        Random rand = new Random();
        return base + rand.nextInt(1000);
    }

    /**
     * This method is used to generate random number
     * 
     * @param base
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String generateRandomNumber(String base) {
        Random rand = new Random();
        return base + rand.nextInt(1000);
    }

    /**
     * This method is used to generate random number with specified size
     * 
     * @param size
     * @return Integer
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public Integer generateRandomNumberWithSize(String size) {
        Random rand = new Random();
        return rand.nextInt(Integer.valueOf(size));
    }

    /**
     * This method is used to generate shuffle charactors
     * 
     * @param numbers
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public static String shuffleCharacters(String numbers) {
        char[] isbn = numbers.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            char c = isbn[random.nextInt(isbn.length)];
            sb.append(c);
        }
        if (sb.substring(0, 1).equalsIgnoreCase("0")) {
            int zeroChanger = random.nextInt(isbn.length - 1) + 1;
            sb.setCharAt(0, String.valueOf(zeroChanger).charAt(0));
        }
        return sb.toString();
    }

    /**
     * This method get the Title
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getTitle() {
        return "Automation QA_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * This method get the ISBN
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getISBN() {
        return new SimpleDateFormat("yyyyMMddHHmm" + 1).format(new Date());
    }

    /**
     * This method get the ISBN with alphabet
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getISBNWithAlphaNumeric() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date()) + "xyz";
    }

    /**
     * This method gets specified date in specified format based on the day value
     * 
     * @param pattern
     * @param days
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getPastDays(String pattern, int days) {
        String pastDate = "";
        try {
            LocalDate day = LocalDate.now().minusDays(days);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern(pattern);
            pastDate = day.format(formatters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pastDate;
    }

    /**
     * This method get the split Title
     * 
     * @param title
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String splitTitle(String title) throws Exception {
        try {
            if (title.contains("/")) {
                return title.split("/")[1] + " - " + title.split("/")[0];
            } else {
                return title;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This method get the Date in specified format
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * This method used to shipped the data in specified format
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getDateShipped() {
        DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * This method get the current date in specified format
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat(CURRENTDATE_FORMAT);
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        return dateFormat.format(date);
    }

    /**
     * This method get the file Path
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String getFile(String filename) {
        File template = new File("Assets/" + filename);
        return template.getAbsolutePath();
    }

    /**
     * This method convert webelement into locator for reporting purpose
     * 
     * @param value
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 27/12/2022
     */
    public String byPassWebElement(Object value) {
        String logs = "";
        String[] loggerValues;
        if (value.toString().contains("->")) {
            try {
                loggerValues = value.toString().split("->");
                logs = loggerValues[1];
            } catch (Exception e) {
                e.getMessage();
            }
        } else if (value.toString().contains("Proxy element for: DefaultElementLocator")) {
            try {
                loggerValues = value.toString().split("Proxy element for: DefaultElementLocator");
                logs = loggerValues[1];
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            logs = String.valueOf(value).toString();
        }
        return logs;
    }

    /**
     * This method log Event Information To Extent and Allure Report For Test Steps
     * in synchronized manner
     * 
     * @param d
     * @param status
     * @param element
     * @param description
     * @throws Exception
     * @author Rakesh.Shevale Modified Date : 27/12/2022
     */
    public synchronized void logEventToReport(WebDriver d, String status, Object element, String description) throws Exception {
        try {
            element = byPassWebElement(element);
            if (status.equalsIgnoreCase("pass")) {
                Allure.step(StringUtils.capitalize(description) + " " + element);
            } else {
                failedstep = StringUtils.capitalize(description) + " " + element;
            }
        } catch (Exception e) {
            System.out.println("error block report");
            Allure.step("Error Block Report: " + StringUtils.capitalize(description) + " " + element);
            e.printStackTrace();
        }
    }

    /**
     * This method log Event Information To Extent and Allure Report For Test Steps
     * in synchronized manner
     * 
     * @param driver
     * @param key
     * @param description
     * @throws Exception
     * @author Vandit.Nagvekar Modified Date : 27/12/2022
     */
    public synchronized void logEventInfoToReport(WebDriver driver, String key, String description) throws Exception {
        Allure.step(key + " " + StringUtils.capitalize(description));
    }

    /**
     * This method log Event Information To Allure Report For Test Steps in
     * synchronized manner and also takes screenshot of final Passed and Failed
     * state Also update execution status on Browser Stack in case all cases
     * executed successfully
     * 
     * @param WebDriver
     * @param key
     * @param status
     * @param description
     * @throws Exception
     */
    public synchronized void logEventInfoToReportForTestSteps(WebDriver driver, String key, String status, String description) throws Exception {
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

    /**
     * This method used to update status in test rail based on testStatus and print
     * message
     * 
     * @param testRunId
     * @param tcId
     * @param testStatus
     * @param message
     * @author Rakesh.Shevale
     * @throws Exception 
     * @Created Date 8/12/2022
     */
    public void publishResults(String testRunId, String tcId, String testStatus, String message) throws Exception {
        serverURL = BaseTest.properties.getProperty("serverURL");
        serverUsername = BaseTest.properties.getProperty("serverUsername");
        serverPassword = BaseTest.properties.getProperty("serverPassword");
        //        serverURL = ConfigurationManager.getConfigurationValue("serverURL");
        //        serverUsername = ConfigurationManager.getConfigurationValue("serverUsername");
        //        serverPassword = ConfigurationManager.getConfigurationValue("serverPassword");
        APIClient client = new APIClient(serverURL);
        client.setUser(serverUsername);
        client.setPassword(serverPassword);

        String requestType = "add_result_for_case";
        String request = requestType + "/" + testRunId + "/" + tcId;
        try {
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("status_id", testStatus);
            data.put("comment", message);
            client.sendPost(request, data);
            Helper.INSTANCE.logEventInfoToReportForTestSteps("Pass", "TestcaseId-" + tcId + " Updated Successfully in Test Rail");
        } catch (IOException | APIException e) {
            Helper.INSTANCE.logEventInfoToReportForTestSteps("Pass", "Problem in updating TestcaseId-" + tcId + " in Test Rail");
            e.printStackTrace();
        }
    }

    /**
     * This method used to capture the screen shot and return the path of screen
     * shot
     * 
     * @param driver
     * @param imgpath
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 8/12/2022
     */
    public String captureScreen(WebDriver driver, String imagePath) {
        try {
            /*
             * TakesScreenshot oScn = ((TakesScreenshot) driver); File oScnShot =
             * oScn.getScreenshotAs(OutputType.FILE); File oDest = new File(imagePath);
             */
            // Full page screen shot
            Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(fpScreenshot.getImage(), "PNG", new File(imagePath));
            // FileUtils.copyFile(oScnShot, oDest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imagePath;
    }

    /**
     * This method used to add the screen shot in report
     * 
     * @param driver
     * @param imgpaths
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 8/12/2022
     */
    public String addScreenShot(WebDriver driver, String imgpath) throws Exception {
        String image = "";
        FileInputStream imageFile;
        try {
            File imgfile = new File(captureScreen(driver, imgpath));
            imageFile = new FileInputStream(imgpath);
            byte imageData[] = new byte[(int) imgfile.length()];
            imageFile.read(imageData);
            byte[] base64EncodedByteArray = org.apache.commons.codec.binary.Base64.encodeBase64(imageData);
            image = new String(base64EncodedByteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + image;
    }

    /**
     * This method connects to Mail and store the host name, user name and password
     * 
     * @param hostname
     * @param username
     * @param password
     * @return store
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 8/12/2022
     */
    public Store connectMail(String hostname, String username, String password) throws Exception {
        Store store;
        try {
            Properties properties = new Properties();
            Session session = Session.getDefaultInstance(properties);
            store = session.getStore("imaps");
            store.connect(hostname, username, password);
            return store;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Connection Problem");
        }
    }

    /**
     * This Method is used to reading the mail subject
     * 
     * @param host
     * @param username
     * @param password
     * @param subject
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 16/11/2022
     */
    public boolean readMailSubject(String host, String username, String password, String subject) throws Exception {
        Store store;
        boolean flag = false;
        try {
            store = connectMail(host, username, password);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message message[] = folder.search(new FlagTerm(new Flags(Flag.SEEN), false));
            for (int i = message.length; i > 0; i--) {
                if (message[i - 1].getSubject().equalsIgnoreCase(subject)) {
                    flag = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(subject + " Not Found");
        }
        store.close();
        return flag;
    }

    /**
     * This Method is used to send the mail
     * 
     * @param properties
     * @throws AddressException,MessagingException
     * @author Rakesh.Shevale
     * @Created Date 16/11/2022
     */
    public void sendMail(Properties properties) throws AddressException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("EMAILUSER").trim(), properties.getProperty("EMAILPASSWORD").trim());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.getProperty("EMAILUSER").trim()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(properties.getProperty("RECEPIENT").trim()));
        message.setSubject(properties.getProperty("SUBJECT").trim());
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText("BODY");
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        DataSource source = new FileDataSource(properties.getProperty("FILEPATH").trim());
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(properties.getProperty("ATTACHMENTNAME").trim());
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart2);
        multipart.addBodyPart(messageBodyPart1);
        message.setContent(multipart);
        Transport.send(message);
    }

    /**
     * This Method is used to generate random number of specified range
     * 
     * @param min
     * @param max
     * @return int
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 16/11/2022
     */
    public int getRandomNumberInRange(int min, int max) throws Exception {
        if (min >= max) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "error", "RandomNumber range", "maximum number must be grater than minimum number");
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * This Method is used to generate random string of specified length
     * 
     * @param length
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 16/11/2022
     */
    public String getRandomStringByLength(int length) {
        String allchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder bulider = new StringBuilder();
        Random rnd = new Random();
        while (bulider.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * allchars.length());
            bulider.append(allchars.charAt(index));
        }
        String finalString = bulider.toString();
        return finalString;
    }

    /**
     * This Method gets the title by date or years wise
     * 
     * @param day
     * @param hours
     * @param year
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String getTitleByDateOrYears(int day, boolean hours, boolean year) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        if (hours) {
            return "Automation QA_" + new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());

        } else if (year) {
            return "Automation QA_" + new SimpleDateFormat("yyyy").format(cal.getTime());
        } else {
            return "Automation QA_" + new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        }
    }

    /**
     * This Method gets Transaction Number from memory
     * 
     * @param testcaseID
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public synchronized String getTranscationNumber(String testcaseID) throws Exception {
        String currentTranscationNumber = "";
        try {
            currentTranscationNumber = Helper.transcationNumbers.get(testcaseID);
            //			Helper.INSTANCE.logEventInfoToReport(WebDriverManager.getDriver(), "GetTranscationNumber",
            //					"Transcation Number is Retreived : TestcaseID :" + testcaseID + " " + "Transcation number :"
            //							+ currentTranscationNumber);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "GetTranscationNumber", e.getMessage());
            e.printStackTrace();
        }
        return currentTranscationNumber;
    }

    /**
     * This Method is used to sets Transaction Number into memory
     * 
     * @param testcaseID
     * @param transcationID
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public synchronized void setTranscationNumber(String testcaseID, String transcationID) throws Exception {

        try {
            if (!transcationID.isEmpty() && !transcationID.equals("") && transcationID != null) {

                Helper.transcationNumbers.put(testcaseID, transcationID);
                Allure.step("Transcation Number : " + transcationID);

            } else {
                Assert.fail("Transcation number is empty");
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "SetTranscationNumber", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This Method get date in specified format
     * 
     * @param val
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String getDateDayYear(int val) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, val);
        String date = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH).format(cal.getTime());
        return date;
    }

    /**
     * This Method get the Journal title
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String getJournalTitle() {
        return "JT_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * This Method get the Mvs title
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String getMvsTitle() {
        return "JT_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * This Method get the Journal Acronym
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String getJournalAcronym() {
        return "JA_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * This method gets the Product Id from memory
     * 
     * @param testcaseID
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/08/2022
     */
    public String getProductId(String testcaseID) throws Exception {
        String currentProductId = "";
        try {
            currentProductId = Helper.productIds.get(testcaseID);
            Helper.INSTANCE.logEventInfoToReport(WebDriverManager.getDriver(), "Get Product Id", "Product Id is Retreived : TestcaseID :" + testcaseID + " " + "Product Id :" + currentProductId);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "GetProductId", e.getMessage());
            e.printStackTrace();
        }
        return currentProductId;
    }

    /**
     * This method is used to sets the Product Id into memory
     * 
     * @param testcaseID
     * @param productId
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/08/2022
     */
    public void setProductId(String testcaseID, String productId) throws Exception {

        try {
            if (!productId.isEmpty() && !productId.equals("") && productId != null) {
                Helper.productIds.put(testcaseID, productId);
                Helper.INSTANCE.logEventInfoToReport(WebDriverManager.getDriver(), "Set Product Id", "Product Id is Stored : TestcaseID :" + testcaseID + " " + "Product Id :" + productId);
            } else {
                throw new Exception("productId is empty");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "SetProductId", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method log Event Information To Extent and Allure Report For Test Steps
     * in synchronized manner
     * 
     * @param driver
     * @param status
     * @param description
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/08/2022
     */
    public synchronized void logEventInfoToReportForTestSteps(WebDriver driver, String status, String description) throws Exception {
        if (status.equalsIgnoreCase("pass")) {
            Allure.step(StringUtils.capitalize(description));
        } else if (status.equalsIgnoreCase("fail")) {
            Allure.step("Failed Step: " + StringUtils.capitalize(description));
        }
    }

    /**
     * This method log Event Information To Extent and Allure Report in synchronized
     * manner
     * 
     * @param driver
     * @param status
     * @param description
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/08/2022
     */
    public synchronized void logEventToReport(WebDriver driver, String status, String description) throws Exception {
        try {
            if (status.equalsIgnoreCase("pass")) {
                Allure.step(StringUtils.capitalize(description));
            } else if (status.equalsIgnoreCase("fail")) {
                Allure.step("Failed Step: " + StringUtils.capitalize(description));
            } else if (status.equalsIgnoreCase("error")) {
                Allure.step("Error Step: " + StringUtils.capitalize(description));
            }
        } catch (Exception e) {
            System.out.println("error block report");
            Allure.step("Error Block Report: " + StringUtils.capitalize(description));
            e.printStackTrace();
        }
    }

    /**
     * This method log Event Information To Extent and Allure Report after asserting
     * expected and actual result in synchronized manner
     * 
     * @param driver
     * @param status
     * @param expected
     * @param actual
     * @param desc
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 11/08/2022
     */
    public synchronized void logEventToReportForTestSteps(WebDriver driver, String status, String expected, String actual, String field) throws Exception {
        String description = "Expected Result--> " + expected + " || " + "Actual Result--> " + actual;
        try {

            if (status.equalsIgnoreCase("pass")) {
                Allure.step(StringUtils.capitalize(field) + " :: " + description);
            } else if (status.equalsIgnoreCase("fail")) {
                Allure.step("Failed Step: " + StringUtils.capitalize(field) + " :: " + description);
            }
        } catch (Exception e) {
            System.out.println("error block report");
            Allure.step("Error Block Report: " + StringUtils.capitalize(field) + " :: " + description);
            e.printStackTrace();
        }
    }

    /**
     * This method gets Current Date in specified format
     * 
     * @param format
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 16/11/2022
     */
    public String getCurrentDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 0);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }

    /**
     * This Method is used to generate random string of specified length
     * 
     * @param lengthOfString
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 16/11/2022
     */
    public static String generateRandomString(int lengthOfString) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(lengthOfString);
        for (int i = 0; i < lengthOfString; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    /**
     * This method converts array into list
     * 
     * @param String array[]
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022
     */
    public List<String> convertArrayToList(String array[]) {
        List<String> list = new ArrayList<String>();
        for (String item : array) {
            list.add(item);
        }
        return list;
    }

    /**
     * This method fetches random element from list
     * 
     * @param list
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022
     */
    public String getRandomElementFromList(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * This method sets existing Report Name into memory
     * 
     * @param ReportName
     * @param Report
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022
     */
    public synchronized void setExistingReportName(String ReportName, String Report) throws Exception {
        try {
            if (!Report.isEmpty() && !Report.equals("") && Report != null) {
                Helper.ReportNames.put(ReportName, Report);
                Helper.INSTANCE.logEventInfoToReportForTestSteps(WebDriverManager.getDriver(), "pass", "Fetching existing Report Name from Publisher Power Bi Page: " + Report);
            } else {
                throw new Exception("Report Name List is empty");
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "setExistingReportName", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method gets existing Report Name from memory
     * 
     * @param ReportName
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022
     */
    public synchronized String getExistingReportName(String ReportName) throws Exception {
        String currentReportName = "";
        try {
            currentReportName = Helper.ReportNames.get(ReportName);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "Get Report Name", e.getMessage());
            e.printStackTrace();
        }
        return currentReportName;
    }

    /**
     * This method connects to database and returns List<String> values
     * 
     * @param category
     * @param db
     * @param SQL
     * @param columnList
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date 8/12/2022
     */
    public List<String> connectDB(String category, String db, String SQL, List<String> columnList) {
        result = "";
        List<String> list = new ArrayList<String>();
        String dbURL = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_URL");
        String strUserID = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_UserID");
        String strPassword = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_Password");
        String connectionUrl = "jdbc:sqlserver://" + dbURL + ";databaseName=" + db + ";user=" + strUserID + ";password=" + strPassword + ";encrypt = true;trustServerCertificate=true;";
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                for (String item : columnList) {
                    result = rs.getString(item);
                    list.add(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method converts String to List
     * 
     * @param columns
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date 8/12/2022
     */
    public List<String> convertStringToList(String columns) {
        String arr[] = columns.split(",");
        list = this.convertArrayToList(arr);
        return list;
    }

    /**
     * This method log Event Information To Extent Report For Test Steps in
     * synchronized manner
     * 
     * @param WebDriver
     * @param status
     * @param expected
     * @param actual
     * @param field
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public synchronized void logEventToReportForTestSteps(WebDriver d, String status, boolean expected, boolean actual, String field) throws Exception {
        String description = "Expected Result --> " + expected + " || " + "Actual Result --> " + actual;
        try {
            if (status.equalsIgnoreCase("pass")) {
                Allure.step(StringUtils.capitalize(field + " :: " + description));
            } else if (status.equalsIgnoreCase("fail")) {
                Allure.step("Failed Step: " + StringUtils.capitalize(field + " :: " + description));
            }
        } catch (Exception e) {
            System.out.println("error block report");
            Allure.step("Error Block Report: " + StringUtils.capitalize(field + " :: " + description));
            e.printStackTrace();
        }
    }

    /**
     * This Method is used to generate random string of specified length
     * 
     * @param lengthOfString
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 11/01/2023
     */
    public static String generateRandomAlphabets(int lengthOfString) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(lengthOfString);
        for (int i = 0; i < lengthOfString; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    /**
     * This Method is used to generate random string of specified length
     * 
     * @param lengthOfString
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 11/01/2023
     */
    public static String generateRandomNumber(int lengthOfString) {
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(lengthOfString);
        for (int i = 0; i < lengthOfString; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    /**
     * This Method is used to generate New Journal Title of specified length
     * 
     * @param lengthOfString
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String createNewJournalTitle(int lengthOfString) {
        return "JT_" + generateRandomAlphabets(lengthOfString);
    }

    /**
     * This Method is used to generate New Journal Acronym of specified length
     * 
     * @param lengthOfString
     * @return String
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String createNewJournalAcronym(int lengthOfString) {
        return "JA_" + generateRandomAlphabets(lengthOfString);
    }

    /**
     * This method connects to database and returns String values
     * 
     * @param category
     * @param db
     * @param SQL
     * @param column
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String connectDB(String category, String db, String SQL, String column) throws Exception {
        result = "";
        String dbURL = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_URL");
        String strUserID = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_UserID");
        String strPassword = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_Password");
        String connectionUrl = "jdbc:sqlserver://" + dbURL + ";databaseName=" + db + ";user=" + strUserID + ";password=" + strPassword + ";encrypt = true;trustServerCertificate=true;";
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(SQL);
            Helper.INSTANCE.logEventInfoToReport("Executing query: " + SQL);
            while (rs.next()) {
                result = rs.getString(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method log Event Information To Extent and Allure Report For Test Steps
     * in synchronized manner
     * 
     * @param description
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/01/2023
     */
    public synchronized void logEventInfoToReport(String description) throws Exception {
        Allure.step(StringUtils.capitalize(description));
    }

    /**
     * This method uses regex To Remove Special Characters from String
     * 
     * @param issueCode
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 17/01/2023
     */
    public String regexToRemoveSpecialChar(String issueCode) {
        issueCode = issueCode.replaceAll("[^a-zA-Z0-9]+", "");
        return issueCode;
    }

    /**
     * This method writes Specific Data To Text File
     * 
     * @param fileName
     * @param fieldToUpdate
     * @author Rakesh.Shevale
     * @Created Date : 17/01/2023
     */
    public void writeToTextFile(String fileName, String fieldToUpdate) {
        String filePath = System.getProperty("user.dir") + File.separator + fileName;
        Path FILE_PATH = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write(fieldToUpdate);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads and Store File Data Line by Line Into List
     * 
     * @param fileName return List<String>
     * @throws IOException
     * @author Rakesh.Shevale
     * @Created Date : 17/01/2023
     */
    public List<String> readAndStoreFileIntoList(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return result;
    }

    /**
     * This method connects to database and returns String values
     * 
     * @param category
     * @param db
     * @param SQL
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 17/01/2023
     */
    public String executeQueryUpdate(String category, String db, String SQL) throws Exception {
        result = "";
        String dbURL = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_URL");
        String strUserID = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_UserID");
        String strPassword = BaseTest.properties.getProperty(category.toUpperCase() + "_SQLDB_Password");
        String connectionUrl = "jdbc:sqlserver://" + dbURL + ";databaseName=" + db + ";user=" + strUserID + ";password=" + strPassword + ";encrypt = true;trustServerCertificate=true;";
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
            int rowsAffected = stmt.executeUpdate(SQL);
            Helper.INSTANCE.logEventInfoToReport("Executing query: " + SQL);
            if (rowsAffected == 0) {
                Helper.INSTANCE.logEventInfoToReportForTestSteps("Fail", "No SQL Update performed: " + SQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method log Event Information To Extent and Allure Report For Test Steps
     * in synchronized manner
     * 
     * @param WebDriver
     * @param status
     * @param description
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/08/2022
     */
    public synchronized void logEventInfoToReportForTestSteps(String status, String description) throws Exception {
        if (status.equalsIgnoreCase("pass")) {
            Allure.step(StringUtils.capitalize(description));
        } else if (status.equalsIgnoreCase("fail")) {
            Allure.step("Failed Step: " + StringUtils.capitalize(description));
        }
    }

    /**
     * This method is used to fetch the test case id from test case name
     * 
     * @param testCaseName
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 24/12/2022
     */
    public String fetchTestCaseID(String testCaseName) {
        String arr[] = testCaseName.split("-");
        return arr[0].trim();
    }

    /**
     * This method used to generate random number with specified size
     * 
     * @param size
     * @param desc
     * @return Integer
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 24/12/2022
     */
    public Integer generateRandomNumberWithSize(String size, String desc) throws Exception {
        Random rand = new Random();
        int no = rand.nextInt(Integer.valueOf(size));
        Helper.INSTANCE.logEventInfoToReport(desc + " : " + no);
        return no;
    }

    /**
     * This method sets Error message
     * 
     * @param error
     * @author Rakesh.Shevale
     * @Created Date : 24/12/2022
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * This method gets Error message
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 24/12/2022
     */
    public String getError() {
        return this.error;
    }

    /**
     * This method get the test CaseId
     * 
     * @param testId
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public synchronized void setTestCaseId(String testId) {
        tcId = testId;
    }

    /**
     * This method get the test CaseId
     * 
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public synchronized String getTestCaseId() {
        return this.tcId;
    }

    /**
     * This method sets Error message into memory
     * 
     * @param testcaseId
     * @param error
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 24/12/2022
     */
    public synchronized void setErrorMessage(String testcaseId, String error) throws Exception {
        try {
            Helper.ErrorMap.put(testcaseId, error);
            Helper.INSTANCE.logEventInfoToReport(WebDriverManager.getDriver(), "SetFailedStep", "Error Message is stored for testcaseId " + testcaseId + " as :" + error);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "SetFailedStep", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method gets Error message from memory
     * 
     * @param testcaseId
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 24/12/2022
     */
    public synchronized String getErrorMessage(String testcaseId) throws Exception {
        String currentFailedStep = "";
        try {
            currentFailedStep = Helper.ErrorMap.get(testcaseId);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "Error", "GetFailedStep", e.getMessage());
            e.printStackTrace();
        }
        return currentFailedStep;
    }

    /**
     * This method set the current test CaseId
     * 
     * @param testId
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public synchronized void setCurrentTestCaseId(String testId) throws Exception {
        Helper.INSTANCE.logEventInfoToReport("Execution Started for TC-" + testId);
        tcId = testId;
    }

    /**
     * This method get the current test CaseId
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public synchronized String getCurrentTestCaseId() {
        return tcId;
    }

    /**
     * This method used to fetch the latest download file
     * 
     * @return String
     * @throws InterruptedException
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public String fetchLatestDownloadFile() throws InterruptedException {
        Thread.sleep(5000);
        String newfile = "";
        try {
            File downloadedDir = new File(System.getProperty("user.dir") + File.separator + "target\\Assets");
            File[] listofFiles = downloadedDir.listFiles();
            if (listofFiles == null || listofFiles.length == 0) {
                return "";
            }
            File LatestModifiedFile = listofFiles[0];
            for (int i = 1; i < listofFiles.length; i++) {
                if (LatestModifiedFile.lastModified() < listofFiles[i].lastModified()) {
                    LatestModifiedFile = listofFiles[i];
                }
            }
            newfile = LatestModifiedFile.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newfile;
    }

    /**
     * This method used to read properties file
     * 
     * @return String
     * @author Veena.Mathew
     * @Created Date : 08/12/2022
     */
    public String readTestDataFromFile(String filename) {
        String newfile = "";
        FileInputStream fis = null;
        try {
            File downloadedDir = new File(System.getProperty("user.dir") + "src/test/resources/" + filename);
            File[] listofFiles = downloadedDir.listFiles();
            if (listofFiles == null || listofFiles.length == 0) {
                return "";
            }
            File LatestModifiedFile = listofFiles[0];
            for (int i = 1; i < listofFiles.length; i++) {
                if (LatestModifiedFile.lastModified() < listofFiles[i].lastModified()) {
                    LatestModifiedFile = listofFiles[i];
                }
            }
            newfile = LatestModifiedFile.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newfile;
    }

    /**
     * This method used to open new tab
     * 
     * @author rakesh.Shavale
     * @Created Date : 02/11/2023
     */
    public void openNewTab() {
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("window.open()");
    }

    /**
     * This method used to switching tab with tab index
     * 
     * @param index
     * @author rakesh.Shavale
     * @Created Date : 02/11/2023
     */
    public void switchToWindowTab(int index) {
        Set<String> window = WebDriverManager.getDriver().getWindowHandles();
        List<String> list = new ArrayList<>(window);
        WebDriverManager.getDriver().switchTo().window(list.get(index));
    }

    public void switchToWindowTab(WebDriver driver, String mainwidow) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainwidow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
            }
            //                else
            //                	 driver.switchTo().window(mainwidow);
        }
    }

    public String getWindow(WebDriver driver) {
        String window = driver.getWindowHandle();
        return window;
    }

    public void closeNewTab(String mainwidow, WebDriver driver) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainwidow.equalsIgnoreCase(ChildWindow)) {
                driver.close();
            }
        }
    }

    /**
     * This method used to switching into IFrame
     * @param element
     * @param desc
     * @param driver
     * @return 
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public void switchToIFrame(WebDriver driver, WebElement element, String desc) {
        driver.switchTo().frame(element);
        Allure.step(desc);
    }
}
