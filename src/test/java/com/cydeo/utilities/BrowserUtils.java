package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
this class will be storing the utility methods that can be used across the project.
 */
public class BrowserUtils {

    /*
    this method will accept int in seconds, and execute Thread.sleep method
    for the given duration
    Arg: int second
     */
    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {

        }
    }

    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle) {

        Set<String> allwindowHandles = Driver.getDriver().getWindowHandles();
        for (String each : allwindowHandles) {
            Driver.getDriver().switchTo().window(each);
            System.out.println(Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
                break;
            }
        }

        //5. Assert: Title contains “Etsy”
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));

    }

    //TC #2: Create utility method
    //1. Create a new class called BrowserUtils
    //2. Create a method to make Task1 logic re-usable
    //3. When method is called, it should switch window and verify title.
    //Method info:
    //• Name: switchWindowAndVerify
    //• Return type: void
    //• Arg1: WebDriver
    //• Arg2: String expectedInUrl
    //• Arg3: String expectedTitle


    //TC #3: Create utility method
    //1. Create a new method for title verification
    //2. Create a method to make title verification logic re-usable
    //3. When method is called, it should simply verify expected title with actual
    //title
    //
    //Method info:
    //• Name: verifyTitle()
    //• Return type: void
    //• Arg1: WebDriver
    //• Arg2: String expectedTitle
    public static void verifyTitle(String expectedTitle) {
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void verifyTitleContains(String expectedInTitle) {
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedInTitle));
    }

    /*
    this method accepts web element target
    waits for that webelement to not be displayed on the page
     */
    public static void waitForInvisibilityOfElement(WebElement target) {

        //create the object of the WebDriverWait class and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        //use the wait object with the proper syntax to create explicit wait conditions
        wait.until(ExpectedConditions.invisibilityOf(target));

        //target can be an object from obe of our classes

    }

    /*
    accepts String title
    waits fot that Title to contain gives String value
     */
    public static void waitForTitleContains(String title) {

        //create the object of the WebDriverWait class and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        //use the wait object with the proper syntax to create explicit wait conditions
        wait.until(ExpectedConditions.titleContains(title));


    }

    /**
     * this method accepts a dropdown element and returns a List<String> that
     * contains all options values as a String
     *
     * @param dropdownElement
     * @return actualMonth_as_STRING
     */
    public static List<String> dropdownOptions_as_STRING(WebElement dropdownElement) {

        Select month = new Select(dropdownElement);
        //Storing all the ACTUAL options in to a List of WebElements
        List<WebElement> actualMonth_as_WEBELEMENT = month.getOptions();
        //have to extract all the string from this list
        //create empty list of string and loop and get each month from webElement list
        //then we can compare with expected months

        //Creating an Empty list of String to store ACTUAL <OPTION> as String
        List<String> actualMonth_as_STRING = new ArrayList<>();

        //Looping through the list of WebElements, getting all options texts and storing them into List<String>
        for (WebElement each : actualMonth_as_WEBELEMENT) {
            actualMonth_as_STRING.add(each.getText());
        }

        return actualMonth_as_STRING;

    }

}
