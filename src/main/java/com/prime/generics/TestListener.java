package com.prime.generics;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileDeleteStrategy;
import org.testng.IExecutionListener;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements IRetryAnalyzer, IExecutionListener, ITestListener, ISuiteListener {
	static int TOTALTCCOUNT;
	static int PASSTCCOUNT;
	static int FAILTCCOUNT;
	static int UNTESTEDTCCOUNT;
	private long startTime;
	private long endTime;
	private long totalTime;
	private long seconds;
	private long minutes;
	private long hours;
	Map<String, String> timeCalculation = new HashMap<String, String>();
	int retryLimit = 1;
	public int counter =0;
	public int maxAttempt =1;
	@Override
	public boolean retry(ITestResult result) {
		if(counter<maxAttempt) {
			counter++;
			return true;
		}
		return false;
	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onExecutionStart() {
		startTime = System.currentTimeMillis();
		System.out.println("---Execution has been Started--");

	}

	@Override
	public void onExecutionFinish() {
		UNTESTEDTCCOUNT = TOTALTCCOUNT - (PASSTCCOUNT + FAILTCCOUNT);
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		minutes = TimeUnit.MILLISECONDS.toMinutes(totalTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalTime));	
		seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime));
		hours = TimeUnit.MILLISECONDS.toHours(totalTime);
		timeCalculation.put("seconds", String.valueOf(String.format("%02d", seconds)));
		timeCalculation.put("minutes", String.valueOf(String.format("%02d", minutes)));
		timeCalculation.put("hours", String.valueOf(String.format("%02d", hours)));

		
		  EmailReporter.reportBuilder(String.valueOf(TOTALTCCOUNT),
		  String.valueOf(FAILTCCOUNT), String.valueOf(PASSTCCOUNT),
		  String.valueOf(UNTESTEDTCCOUNT), timeCalculation);
		 

		System.out.println("---Execution has been completed--");
		
		  try { TemplateGenerator generator=new TemplateGenerator();
		  generator.ftlGenerator(String.valueOf(TOTALTCCOUNT),
		  String.valueOf(FAILTCCOUNT), String.valueOf(PASSTCCOUNT),
		  String.valueOf(UNTESTEDTCCOUNT), timeCalculation);
		  System.out.println("--teamcity template is generated successfully--"); }
		  catch (Exception e) {
		  System.out.println("Problem in teamcity template generation");
		  e.printStackTrace(); }
		 
		
		/*try {
			Runtime.getRuntime().exec("taskkill /IM chromedriver.exe /F");
			Runtime.getRuntime().exec("taskkill /IM chrome.exe /F");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		

	}

	@Override
	public void onStart(ISuite suite) {

	}

	@Override
	public void onFinish(ISuite suite) {
		if(!suite.getName().equalsIgnoreCase("PrerequisiteSuite"))
		{
		Map<String, ISuiteResult> resultMap = suite.getResults();
		IResultMap failedTestMap = null;
		IResultMap passTestMap = null;
		IResultMap Total = null;
		for (Map.Entry<String, ISuiteResult> ent : resultMap.entrySet()) {
			ISuiteResult res = ent.getValue();
			failedTestMap = res.getTestContext().getFailedTests();
			passTestMap = res.getTestContext().getPassedTests();
			TOTALTCCOUNT = TOTALTCCOUNT + res.getTestContext().getAllTestMethods().length;
			FAILTCCOUNT = FAILTCCOUNT + failedTestMap.size();
			PASSTCCOUNT = PASSTCCOUNT + passTestMap.size();
		}

	}
	}
}
