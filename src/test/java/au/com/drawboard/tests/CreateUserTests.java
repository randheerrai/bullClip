package au.com.drawboard.tests;

import au.com.drawboard.steps.ApiSteps;
import au.com.utils.AutomationUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Date;

@RunWith(SerenityRunner.class)
public class CreateUserTests {

    @ManagedPages(defaultUrl = "http://preprod-api.bullclip.com")
    Pages pages;


    @Managed(uniqueSession = true)//, driver = "chrome")
    public WebDriver webdriver;


    @Steps
    ApiSteps apiSteps;

    Date date = new Date();

    String username = "interview-test@drawboard.com";
    String password = "drawboard-test";




    @Test
    @WithTagValuesOf({"createuser"})
    public void verify_that_new_user_cannot_be_created_with_existing_emailId(){
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first"+random;
        String lastName = "last"+random;
        String password = random;
        String confirmPassword=random;
        String email = random+"@doesnotexist.com";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.create_user(firstName,lastName,email,password,confirmPassword);
        apiSteps.verify_that_user_cannot_be_created_with_same_email(firstName,lastName,email,password,confirmPassword);


    }

    @Test
    @WithTagValuesOf({"createuser"})
    public void verify_that_user_cannot_be_created_with_password_less_than_8_characters(){
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first"+random;
        String lastName = "last"+random;
        String password = random;
        String confirmPassword=random;
        String email = random+"@doesnotexist.com";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.verify_that_user_cannot_be_created_with_password_less_than_8_characters(firstName,lastName,email,
                password.substring(4),confirmPassword.substring(4));


    }

    @Test
    @WithTagValuesOf({"createuser"})
    public void create_user_with_valid_details() {
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first"+random;
        String lastName = "last"+random;
        String password = random;
        String confirmPassword=random;
        String email = random+"@doesnotexist.com";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.verify_that_user_can_be_created_with_valid_details(firstName,lastName,email,password,confirmPassword);
    }

    @Test
    @WithTagValuesOf({"createuser"})
    public void verify_that_user_cannot_be_created_with_password_mismatch(){
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first"+random;
        String lastName = "last"+random;
        String password = random;
        String confirmPassword=random+"1";
        String email = random+"@doesnotexist.com";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.verify_that_user_cannot_be_created_with_password_mismatch(firstName,lastName,email,password,confirmPassword);
        }

    @Test
    @WithTagValuesOf({"createuser"})
    public void verify_that_user_cannot_be_created_without_password(){
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first"+random;
        String lastName = "last"+random;
        String password = random;
        String confirmPassword=random;
        String email = random+"@doesnotexist.com";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.verify_that_user_cannot_be_created_without_password(firstName,lastName,email,"","");
    }

    @Test
    @WithTagValuesOf({"createuser"})
    public void verify_that_user_cannot_be_created_with_missing_first_and_last_name(){
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first"+random;
        String lastName = "last"+random;
        String password = random;
        String confirmPassword=random;
        String email = random+"@doesnotexist.com";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.verify_that_user_cannot_be_created_with_missing_details("","",email,password,confirmPassword);
    }

}
