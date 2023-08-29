package com.prime.generics;

import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class ConfigurationManager {

	private static final String EXECUTION_MODE_KEY = "executionMode";
	private static final String BROWSER_KEY = "browser";
	private static final String GRID_IP_KEY = "GridHubIP";
	private static final String TIMEOUT_KEY = "timeOut";
	private static final String TESTRAIL_MODE = "testRailIntegrationMode";
	private static final String TESTRAIL_MODE_ON = "ON";
	private static final String TESTRAIL_MODE_OFF = "OFF";
	private static final String THREAD_SLEEP_ENABLED = "sleepEnabled";
	private static final String THREAD_SLEEP_TIMEOUT = "sleepTime";
	private static final String DRIVER_PATH = "driverPath";
	private static final String GROUPNAME = "groupname";
	private static final String TESTRAILID_QA = "testRunId_qa";
	private static final String TESTRAILID_43_44 = "testRunId_4.34.4";
	private static final String TESTRAILID_CORE = "testRunId_core";
	private static final String TESTRAILID = "testRunId";

	public static XmlSuite suite;

	/**
	 * This class is being used to getting the parameter values from suite xml
	 * Example source file: Config.xml, CoreCases.xml
	 * @param context
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static void createManager(ITestContext context) {
		try {
			suite = context.getSuite().getXmlSuite();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable load settings: " + e.getMessage());
		}
	}

	/**
	 * This method returns execution mode parameter value from XML file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static String getExecutionMode() {
		return suite.getParameter(EXECUTION_MODE_KEY);
	}

	/**
	 * This method returns browser parameter value from XML file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static String getBrowser() {
		return suite.getParameter(BROWSER_KEY);
	}

	/**
	 * This method returns grip IP parameter value from XML file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static String getGridIP() {
		return suite.getParameter(GRID_IP_KEY);
	}

	/**
	 * This method returns time out parameter value from property file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static long getTimeOut() {
		String timeout = BaseTest.properties.getProperty(TIMEOUT_KEY);
		return Long.valueOf(timeout);
	}

	/**
	 * This method returns XML value based on specified parameter
	 * @param configurationKey
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static String getConfigurationValue(String configurationKey) {
		return suite.getParameter(configurationKey);
	}

	/**
	 * This method is used to check Test Rail Integration Mode from XML file
	 * @param configurationKey
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static boolean getTestRailIntegrationMode() {
		boolean testrailMode = false;
		String mode = suite.getParameter(TESTRAIL_MODE).toLowerCase();

		if (mode.equals(TESTRAIL_MODE_ON.toLowerCase())) {
			testrailMode = true;
		} else if (mode.equals(TESTRAIL_MODE_OFF.toLowerCase())) {
			testrailMode = false;
		}
		return testrailMode;
	}

	/**
	 * This method used to check thread sleep enabled or not from property file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static boolean getSleepMode() {
		return Boolean.valueOf(BaseTest.properties.getProperty(THREAD_SLEEP_ENABLED));
	}

	/**
	 * This method returns sleep time out parameter value from property file
	 * @return long
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static long getSleepTimeout() {
		long timeOut = Long.valueOf(BaseTest.properties.getProperty(THREAD_SLEEP_TIMEOUT));
		return timeOut;
	}

	/**
	 * This method returns driver path value from property file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static String getDriverPath() {
		return System.getProperty("user.dir") + BaseTest.properties.getProperty(DRIVER_PATH);
	}

//	/**
//	 * This method returns groupname parameter value from XML file
//	 * @return String
//	 * @author Rakesh.Shevale
//	 * @Created Date : 27/12/2022
//	 */
//	public static String getGroupName() {
//		return suite.getParameter(GROUPNAME);
//	}

	/**
	 * This method returns  test rail Id value from XML file
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public static String getTestRailId() {
		return suite.getParameter(TESTRAILID);
	}
}
