package krieger.extended.test;

import krieger.framework.core.BaseTest;
import krieger.framework.utils.ExtentTestManager;
import org.testng.Assert;
import java.lang.reflect.Method;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class VerifyEmailAlreadySubscribed extends BaseTest {


    @Test
    public void TestEmailAlreadySubscribed() {
        ExtentTestManager.startTest("Test - 002 - Verify email address is already subscribe",
                "Error is shown if ot enter already subscribed email address");
        LoginPage loginPage = new LoginPage(getDriver());
        String email_adddress = "tautomation2021@gmail.com";
        String subscribsion_message = "Sie haben sich bereits für den Newsletterempfang angemeldet.";

        Reporter.log("Open the Login Page");
        loginPage.openLoginPage();

        Reporter.log("Enter email address");
        loginPage.enterEmailAddress(email_adddress);

        Reporter.log("Press 'Absend' button");
        loginPage.pressAbsendenButton();

        Reporter.log("Verify email is already subscribed");
        String error_message = loginPage.getEMailSubscribedMesasge();
        Assert.assertEquals(error_message, subscribsion_message, "Confirmation message is not present or correct");

    }
}
