//package com.prime.tests.E2E;
//
//
//import java.io.File;
//
//import java.io.File;
//import java.time.Duration;
//import java.util.Objects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
//
//import io.qameta.allure.Allure;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
// public class TetsingJava {
//
//
//	    
//	         
//	    	
//
//	    	    
//	    	     private static void waitUntilFileIsDownloaded(WebDriver driver,String fileName, String downloadDirectory) {
//	    	         Wait<WebDriver> wait = new FluentWait<>(driver)
//	    	                 .withTimeout(Duration.ofSeconds(60))
//	    	                 .pollingEvery(Duration.ofSeconds(2))
//	    	                 .ignoring(NullPointerException.class);
//
//	    	         wait.until(webDriver -> {
//	    	             File dir = new File(downloadDirectory);
//	    	             File[] files = dir.listFiles();
//
//	    	             if (files != null) {
//	    	                 for (File file : files) {
//	    	                     if (file.getName().endsWith(fileName)) {
//	    	                        Allure.step("File not availabl");
//	    	                     }
//	    	                 }
//	    	             }
//	    	             return false;
//	    	         });
//	    	     
//	    	 }
//
//	 }
//	     
//	 
//}