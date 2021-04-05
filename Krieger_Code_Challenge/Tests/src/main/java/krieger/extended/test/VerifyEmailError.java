package krieger.extended.test;

import krieger.framework.core.BaseTest;
import krieger.framework.utils.ExtentTestManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class VerifyEmailError extends BaseTest {

    @Test
    public void TestEmailFormatError() {
        ExtentTestManager.startTest("Test - 003 - Verify email input format",
                "Wrong email format entered - error shown");

        LoginPage loginPage = new LoginPage(getDriver());
        String email_address = "qwerty112@qwecom";
        String email_error = "Bitte geben Sie eine gültige E-Mail-Adresse an!";

        Reporter.log("Open the Login Page\n");
        loginPage.openLoginPage();
        loginPage.enterEmailAddress(email_address);

        Reporter.log("Press 'Absend' button");
        loginPage.pressAbsendenButton();
        String actual_email_error = loginPage.getEMailErrorMesasge();
        Assert.assertEquals(actual_email_error, email_error, "Email Error is not present or incorrect");
    }
}