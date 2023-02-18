package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    //create a private constructor to remove access to this object

    private Driver() {}
    /*
    we make the webdriver private, because we want to close access from outside the class.
    We are making it static because we will use it in a static method (instantiating before anyhitn else, having once instance)
     */

    private static WebDriver driver; //default value here is null
/*
create a reusable utility method that will return the same driver instance once we call it.
if an instance doesnt exist, it will create first, and then it will always return same instance
 */

    public static WebDriver getDriver() {
        //create singleton design pattern
        if (driver == null) {
            /*
            we will read our browserType from configuration.properties file
            This way, we can control which browser is opened from outside our code.
             */
            String browserType = ConfigurationReader.getProperty("browser");
            //switch is faster
            /*
            Depending on the browserType returned from the configuration.properties
            switch statement will determine the 'case', and open the matching browser.
             */
            switch (browserType) {
                case "chrome":
                    //WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":
                    //WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        }
        //if not null then
        return driver;
    }

/*
Create a new Driver.closeDriver(); it will use .quit() method to quit browsers, and then set the driver value back to null.

 */

    public static void closeDriver() {
        if (driver != null) {
            /*
            this line will terminate the currently existing driver completely, it will not
            exist going forward
             */
            driver.quit();
            /*
            we assign the value back to null so that my singleton can create a newer one if needed
             */
            driver = null;

        }
    }

}
