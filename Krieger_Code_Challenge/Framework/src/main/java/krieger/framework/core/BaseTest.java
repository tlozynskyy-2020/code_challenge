package krieger.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        String driverPath = "src/main/java/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        System.setProperty("headless", "false");
        String headless = System.getProperty("headless");

        if("true".equals(headless)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("start-maximized");
            driver = new ChromeDriver(chromeOptions);
        } else {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("start-maximized");
            driver = new ChromeDriver(chromeOptions);
        }
    }

    @AfterMethod
    public void afterMethod() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }


    public WebDriver getDriver() {
        return driver;
    }
}
