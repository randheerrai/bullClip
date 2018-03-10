package au.com.drawboard.steps;

import au.com.drawboard.pages.ApiPage;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {

    ApiPage apiPage;
    Response response;

    @Step
    public void login_to_bull_clip(String username, String passWord) {
        apiPage.loginToBullClip(username,passWord);

    }

    @Step
    public void verify_that_user_can_be_created_with_valid_details(String firstName, String lastName,
                                                                   String email, String password, String confirmPassword) {
        response = create_user(firstName,lastName,email,password,confirmPassword);
        response.then().statusCode(200);
    }

    @Step
    public Response create_user(String firstName, String lastName, String email, String password, String confirmPassword) {
        response = apiPage.createUser(firstName,lastName,email,password,confirmPassword);
        return response;
    }


    @Step
    public void verify_that_user_cannot_be_created_with_missing_details(String firstName, String lastName, String email,
                                                                        String password, String confirmPassword) {
        response = create_user(firstName,lastName,email,password,confirmPassword);
        response.then().statusCode(400);

    }

    @Step
    public void verify_that_user_cannot_be_created_with_same_email(String firstName, String lastName,
                                                                   String email, String password, String confirmPassword) {
        response = create_user(firstName,lastName,email,password,confirmPassword);
        response.then().statusCode(400);
        assertThat(response.asString().contains("UserAlreadyExists"));
    }

    @Step
    public void verify_that_user_cannot_be_created_with_password_less_than_8_characters(String firstName, String lastName, String email,
                                                                                        String password, String confirmPassword) {
        response = create_user(firstName,lastName,email,password,confirmPassword);
        response.then().statusCode(400);
        assertThat(response.asString().contains("Password is too short"));
    }

    @Step
    public void verify_that_user_cannot_be_created_with_password_mismatch(String firstName, String lastName, String email,
                                                                          String password, String confirmPassword) {
        response = create_user(firstName,lastName,email,password,confirmPassword);
        response.then().statusCode(400);
        assertThat(response.asString().contains("Provided passwords doesn't match"));
    }

    @Step
    public void verify_that_user_cannot_be_created_without_password(String firstName, String lastName, String email,
                                                                    String password, String confirmPassword) {
        response = create_user(firstName,lastName,email,password,confirmPassword);
        response.then().statusCode(400);
        assertThat(response.asString().contains("The Password field is required"));
    }

    @Step
    public Response create_user_profile(String firstName, String lastName, String company,
                                        String department,String title, String phone){
        response = apiPage.createUserProfile(firstName,lastName,company,department,title,phone);
        return response;
    }
}
