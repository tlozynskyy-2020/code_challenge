package krieger.tests.suite;

import krieger.framework.core.BaseTest;
import krieger.framework.utils.ExtentManager;
import krieger.framework.utils.ExtentTestManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

import java.util.Random;

public class VerifyConfirmationText extends BaseTest {


    @Test
    public void TestConfirmationText() {
        ExtentTestManager.startTest("Test - 001 - Verify subscribsion confirmation text",
                "Subscribsion confirmation text is shown");
        LoginPage loginPage = new LoginPage(getDriver());
        String subscribsion_message = "Jetzt zum Newsletter anmelden und\n" +
                "10€-Gutschein* sichern!";
        String message1 = "Nur noch ein Klick und Sie haben es geschafft!";
        String message2 = "Bitte bestätigen Sie jetzt Ihre Anmeldung über den Klick auf " +
                "\"Anmeldung bestätigen\" in der E-Mail, die soeben an Sie versandt wurde.";
        Random rand = new Random();
        int n = rand.nextInt(500);

        Reporter.log("Open the Login Page");
        loginPage.openLoginPage();

        Reporter.log("Verify subscription message");
        String subs_text = loginPage.getSubscriptionText();
        Assert.assertEquals(subs_text, subscribsion_message, "Subscription message is not present or correct");

        Reporter.log("Enter email address");
        loginPage.enterEmailAddress(String.format("tautomation2021+%s@gmail.com", n)); //this will give us different email address each test run
        Reporter.log("Press 'Absend' button");
        loginPage.pressAbsendenButton();

        Reporter.log("Verify confirmation message");
        String conf_message1 = loginPage.getConfirmationMesasge1();
        String conf_message2 = loginPage.getConfirmationMesasge2();
        Assert.assertEquals(conf_message1, message1, "Confirmation message is not present or correct");
        Assert.assertEquals(conf_message2, message2, "Confirmation message is not present or correct");
    }
}
