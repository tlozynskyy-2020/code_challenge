package external.email.pageobject;

import krieger.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;


/**
 * Page Object encapsulates the GMail Page
 */

public class GMailPage extends BasePage {

    //List of Web Elements
    private final By email_input = By.xpath("//input[@id='identifierId']");
    private final By next_button = By.xpath("//div[@class='qhFLie']//button");
    private final By password_input = By.xpath("//input[@name='password']");
    private final By search_field = By.xpath("//input[@class='gb_gf' and @type='text']");
    private final By subscription_button = By.xpath("//table//a[contains(@href, 'https://email.hoeffner.de/u/gm')]");
    private final By subscription_confirmation_button = By.xpath(
            "//table//a[contains(@href, 'https://email.hoeffner.de/u/')]/font/img[2]");
    private final By accept_cookie = By.xpath("//div[@class='button__label button__label--']");

    public GMailPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open Login Page
     */

    public void openGmailPage() {
        driver.get("https://gmail.com");
    }

    /**
     * Enter email address and password
     *
     * @String email address
     * @String password
     */

    public void enterLoginInfo(String email, String password) {
        driver.findElement(email_input).sendKeys(email);
        driver.findElement(next_button).click();
        waitForElementToAppear(password_input);
        driver.findElement(password_input).sendKeys(password);
        driver.findElement(next_button).click();
    }

    /**
     * Search for the email by sender
     *
     * @String sender_address
     */
    public void searchForEmail(String sender_address) {
        waitForElementToAppear(search_field);
        driver.findElement(search_field).sendKeys(sender_address);
        driver.findElement(search_field).sendKeys(Keys.ENTER);
    }

    /**
     * Open email
     *
     * @String subject
     */
    public void openEmail(String subject) {
        String xpath = String.format("//span[@class='bog']/span[contains(text(), %s)]", subject);
        waitForElementToAppear(By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * Click Subscription button
     */
    public void clickSubscriptionButton() {
        waitForElementToAppear(subscription_button);
        driver.findElement(subscription_button).click();
    }

    /**
     * Switch to a New Tab
     *
     * @int tab_index
     */
    public void switchToNewTab(int tab_index) {
        //Switch to the new Tab
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(tab_index)); //Tab number
    }

    /**
     * Click Confirm Subscription Button
     */
    public void confirmSubscription() {
        waitForElementToAppear(subscription_confirmation_button);
        driver.findElement(subscription_confirmation_button).click();
    }

    /**
     * get Page Url
     *
     * @return string
     */
    public String getPageUrl() {
        waitForElementToAppear(accept_cookie);
        if (driver.findElement(accept_cookie) != null) {
            acceptCookie();
        }
        return driver.getCurrentUrl();
    }
}