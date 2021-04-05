package krieger.extended.test;

import external.email.pageobject.GMailPage;
import krieger.framework.core.BaseTest;
import krieger.framework.utils.ExtentTestManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class VerifySubscriptionViaEmail extends BaseTest {

    @Test
    public void TestSubscriptionViaEmail() {
        ExtentTestManager.startTest("Test - 004 - Verify subscription via email",
                "Confirm subscription via email and check the success message");

        GMailPage gmailPage = new GMailPage(getDriver());
        String sender_address = "anmeldung@newsletter.hoeffner.de";
        String subject = "'Bitte bestätigen Sie Ihre Anmeldung!'";
        String email_address = "tautomation2021@gmail.com";
        String password = "Qwerty!@#$";
        String url = "https://www.hoeffner.de/nl-anmeldung";

        Reporter.log("Open GMail and perform login");
        gmailPage.openGmailPage();
        gmailPage.enterLoginInfo(email_address, password);

        Reporter.log("Search for subscription email");
        gmailPage.searchForEmail(sender_address);

        Reporter.log("Open email");
        gmailPage.openEmail(subject);

        Reporter.log("Click subscription button");
        gmailPage.clickSubscriptionButton();

        Reporter.log("Switch to a new Tab");
        gmailPage.switchToNewTab(1);

        Reporter.log("Press Confirm subsription Button");
        gmailPage.confirmSubscription();
        gmailPage.switchToNewTab(2);

        Reporter.log("Get Page Url and compare it");
        String page_url = gmailPage.getPageUrl();
        Assert.assertTrue(page_url.contains(url));

    }
}