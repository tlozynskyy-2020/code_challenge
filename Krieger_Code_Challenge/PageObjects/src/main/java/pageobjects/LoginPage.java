package pageobjects;


import krieger.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


/**
 * Page Object encapsulates the Login Page
 */

public class LoginPage extends BasePage {
    //protected WebDriver driver;

    //List of Web Elements
    private final By email_input = By.xpath("//input[@id='email']");
    private final By absenden_button = By.xpath("//div[contains(text(),'Absenden')]");
    private final By confirmation_text1 = By.xpath("//div[@class='footerNewsletter__confirmation']/span");
    private final By confirmation_text2 = By.xpath("//div[@class='footerNewsletter__confirmation']/span[2]");
    private final By confirmation_email_subscribed = By.xpath(
            "//div[@class='footerNewsletter__confirmation footerNewsletter__error']");
    private final By accept_cookie = By.xpath("//div[@class='button__label button__label--']");
    private final By subscription_text = By.xpath("//div[@class='footerNewsletter__text']");
    private final By email_error = By.xpath("//label[@id='email-error']");
    private final By loading_spinner = By.xpath(
            "//div[@class='footerNewsletter__loading footerNewsletter__loading--show']");

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    /**
     * Open Login Page
     */

    public void openLoginPage() {
        driver.get("https://www.hoeffner.de/login");
        if (driver.findElement(accept_cookie) != null) {
            System.out.println("'Accept cookie' dialog found\n");
            driver.findElement(accept_cookie).click();
            Reporter.log("Login Page opened\n");
        } else {
            Reporter.log("No 'Accept cookie' dialog found\n");
        }
    }

    /**
     * Enter email address
     */

    public void enterEmailAddress(String email) {
        driver.findElement(email_input).sendKeys(email);
    }

    /**
     * Press 'Absended' button
     */

    public void pressAbsendenButton() {
        driver.findElement(absenden_button).click();
    }

    /**
     * Get the subscription Text
     *
     * @return String
     */
    public String getSubscriptionText() {
        waitForElementToDisappear(loading_spinner);
        waitForElementToAppear(subscription_text);
        return driver.findElement(subscription_text).getText();
    }

    /**
     * Get the 1st confirmation message
     *
     * @return String
     */
    public String getConfirmationMesasge1() {
        waitForElementToDisappear(loading_spinner);
        isDisplayed(driver.findElement(confirmation_text1));
        return driver.findElement(confirmation_text1).getText();
    }

    /**
     * Get the 2st confirmation message
     *
     * @return String
     */
    public String getConfirmationMesasge2() {
        waitForElementToDisappear(loading_spinner);
        isDisplayed(driver.findElement(confirmation_text2));
        return driver.findElement(confirmation_text2).getText();
    }

    /**
     * Get the error mesasage when incorrect email format entered
     *
     * @return String
     */

    public String getEMailErrorMesasge() {
        waitForElementToDisappear(loading_spinner);
        isDisplayed(driver.findElement(email_error));
        return driver.findElement(email_error).getText();
    }

    /**
     * Get the error message that email is already subscribed
     *
     * @return String
     */

    public String getEMailSubscribedMesasge() {
        waitForElementToDisappear(loading_spinner);
        isDisplayed(driver.findElement(confirmation_email_subscribed));
        return driver.findElement(confirmation_email_subscribed).getText();
    }
}
