package com.prime.generics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

// Custom ExpectedCondition for case-insensitive URL matching
public class CustomExpectedConditions {
    public static ExpectedCondition<Boolean> urlContainsIgnoreCase(String partialUrl) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                // System.out.println("total lower case =" + driver.getCurrentUrl().toLowerCase());
                // System.out.println("link text URL=" + partialUrl.toLowerCase());
                return driver.getCurrentUrl().toLowerCase().contains(partialUrl.toLowerCase());
            }

            public String toString() {
                return String.format("URL to contain (ignoring case) '%s'", partialUrl);
            }
        };
    }
}
