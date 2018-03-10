package au.com.drawboard.tests;

import au.com.drawboard.steps.ApiSteps;
import au.com.utils.AutomationUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTagValuesOf;
import net.thucydides.core.pages.Pages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Date;

@RunWith(SerenityRunner.class)
public class CreateUserProfileTests {

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
    @WithTagValuesOf({"userprofile"})
    public void verify_that_user_profile_can_be_created_with_valid_details() {
        String random = AutomationUtils.getDateInFormat(date, "dd_HH_mm_ss");
        String firstName = "first" + random;
        String lastName = "last" + random;
        String company = "company"+random;
        String department = "department"+random;
        String title = "title"+random;
        String phone = "08080808";
        apiSteps.login_to_bull_clip(username, this.password);
        apiSteps.create_user_profile(firstName,lastName,company,department,title,phone);


    }

}
