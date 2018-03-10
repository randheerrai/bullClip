package au.com.utils;

public class RestRequestBody {


    public String getLoginBody(String username, String password) {
        return "{\n" +
                "  \"username\": \""+username+"\",\n" +
                "  \"password\": \""+password+"\"\n" +
                "}\n";

    }

    public String getCreateUserBody(String firstName, String lastName,String email,
                                    String password, String confirmPassword){
        return "{\n" +
                "  \"firstName\": \""+firstName+"\",\n" +
                "  \"lastName\": \""+lastName+"\",\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"password\": \""+password+"\",\n" +
                "  \"confirmPassword\": \""+confirmPassword+"\",\n" +
                "  \"isOptInForEmail\": true\n" +
                "}";
    }

    public String getCreateUserProfileBody(String firstName, String lastName, String company,
                                           String department,String title, String phone){
        return "{\n" +
                "  \"firstName\": \""+firstName+"\",\n" +
                "  \"lastName\": \""+lastName+"\",\n" +
                "  \"companyName\": \""+company+"\",\n" +
                "  \"department\": \""+department+"\",\n" +
                "  \"title\": \""+title+"\",\n" +
                "  \"phone\": \""+phone+"\",\n" +
                "  \"isOptInForCommunication\": true\n" +
                "}";
    }
}
