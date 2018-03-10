package au.com.drawboard.pages;

import au.com.utils.RestEndPoint;
import au.com.utils.RestRequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.pages.Pages;

import java.util.Map;

public class ApiPage extends PageObject {
    public ApiPage() {
        this.requestBody = new RestRequestBody();
        this.restEndPoint = new RestEndPoint();
        RestAssured.useRelaxedHTTPSValidation();
    }

    RestEndPoint restEndPoint;
    RestRequestBody requestBody;
    Response response;

    Pages pages;
    public static String accessToken;
    public static String authorizationHeader;

    public  static Headers headers;
    public static Map<String, String> cookies;
    public static String bearer;



    private static String loginEnv = "https://preprod-api.bullclip.com";
    private static  String sandboxUrl = "https://sandbox.bullclip.com";
    private static String subKey = "1c2ce8bc7b8649b7b5467afcf17898de";

    public Response loginToBullClip(String username, String password) {
        response = SerenityRest.given().contentType(ContentType.JSON).body(requestBody.getLoginBody(username, password)).log().all()
                .post(loginEnv +restEndPoint.loginUrl);
        response.then().log().all();
        this.accessToken = response.jsonPath().get("accessToken");
        this.authorizationHeader = response.jsonPath().get("authorizationHeader");
        return response;
    }


    public Response createUser(String firstName, String lastName, String email, String password, String confirmPassword) {
        response = SerenityRest.given().header("accessToken",accessToken)
                .header("authorizationHeader",authorizationHeader)
                .header("Ocp-Apim-Subscription-Key",subKey)
                .contentType(ContentType.JSON)
                .body(requestBody.getCreateUserBody(firstName,lastName,email,password,confirmPassword)).log().all()
                .post(sandboxUrl+restEndPoint.createUserUrl);
        response.then().log().all();
        return response;


    }

    public Response createUserProfile(String firstName, String lastName, String company,
                                      String department,String title, String phone){
        response = SerenityRest.given()
                .header("accessToken",accessToken)
                .header("authorizationHeader",authorizationHeader)
                .header("Ocp-Apim-Subscription-Key",subKey)
                .contentType(ContentType.JSON)
                .body(requestBody.getCreateUserProfileBody(firstName,lastName,company,department,title,phone)).log().all()
                .post(sandboxUrl+restEndPoint.createUserProfileUrl);
        response.then().log().all();
        return response;
        }
}
