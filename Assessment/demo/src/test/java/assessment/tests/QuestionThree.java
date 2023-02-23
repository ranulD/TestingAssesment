package assessment.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class QuestionThree 
{

    public QuestionThree() {
        //specify base URI
        //User APIs in https://petstore.swagger.io/v3 is not working, giving 500 status code when executing.Hence using following URI tocomplete the assesment
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


    @Test(description = "Add a new customer", priority = 1)
    public void createNewUser() throws InterruptedException
    {
            //request object
            RequestSpecification request = RestAssured.given();

            //request payload sending along with post request
            JSONObject requstParam=new JSONObject();
            requstParam.put("id", 111);
            requstParam.put("username", "RanulD");
            requstParam.put("firstName", "Ranul");
            requstParam.put("lastName", "De Silva");
            requstParam.put("email", "randuldesilva@gmail.com");
            requstParam.put("password", "123456");
            requstParam.put("phone", "123456789");
            requstParam.put("userStatus", 1);

            request.header("Content-Type", "application/json");
            request.body(requstParam.toJSONString());

             //response object
             Response response = request.request(Method.POST,"/user");

             String responseBody = response.getBody().asString();
             System.out.println("responseBody: "+responseBody);

             int statusCode= response.getStatusCode();
             System.out.println("statusCode: "+statusCode);
             Assert.assertEquals(statusCode, 200);

             int code = response.jsonPath().get("code");
             Assert.assertEquals(code, 200);

    }
    @Test(description = "Read created user", priority = 2)
    public void readCreatedUser() throws InterruptedException
    {
            //request object
            RequestSpecification request = RestAssured.given().pathParam("username", "RanulD");

             //response object
             Response response = request.request(Method.GET,"/user/{username}");

             String responseBody = response.getBody().asString();
             System.out.println("responseBody: "+responseBody);

             int statusCode= response.getStatusCode();
             System.out.println("statusCode: "+statusCode);
             Assert.assertEquals(statusCode, 200);

             String usernameResponse = response.jsonPath().get("username");
             Assert.assertEquals(usernameResponse, "RanulD");
    }

    @Test(description = "Update user's username", priority = 3)
    public void updateUserName() throws InterruptedException
    {
            //request object
            RequestSpecification request = RestAssured.given().pathParam("username", "RanulD");

            //request payload sending along with post request
            JSONObject requstParam=new JSONObject();
            requstParam.put("id", 111);
            requstParam.put("username", "RanulDUpdated");
            requstParam.put("firstName", "Ranul");
            requstParam.put("lastName", "De Silva");
            requstParam.put("email", "randuldesilva@gmail.com");
            requstParam.put("password", "123456");
            requstParam.put("phone", "123456789");
            requstParam.put("userStatus", 1);

            request.header("Content-Type", "application/json");
            request.body(requstParam.toJSONString());

             //response object
             Response response = request.request(Method.PUT,"/user/{username}");

             String responseBody = response.getBody().asString();
             System.out.println("responseBody: "+responseBody);

             int statusCode= response.getStatusCode();
             System.out.println("statusCode: "+statusCode);
             Assert.assertEquals(statusCode, 200);

             int code = response.jsonPath().get("code");
             Assert.assertEquals(code, 200);

    }
    @Test(description = "Read the updated user", priority = 4)
    public void readUpdatedUser() throws InterruptedException
    {
            //request object
            RequestSpecification request = RestAssured.given().pathParam("username", "RanulDUpdated");

             //response object
             Response response = request.request(Method.GET,"/user/{username}");

             String responseBody = response.getBody().asString();
             System.out.println("responseBody: "+responseBody);

             int statusCode= response.getStatusCode();
             System.out.println("statusCode: "+statusCode);
             Assert.assertEquals(statusCode, 200);

             String usernameResponse = response.jsonPath().get("username");
             Assert.assertEquals(usernameResponse, "RanulDUpdated");

    }
    @Test(description = "Delete the user", priority = 5)
    public void deleteUser() throws InterruptedException
    {
            //request object
            RequestSpecification request = RestAssured.given().pathParam("username", "RanulDUpdated");

             //response object
             Response response = request.request(Method.DELETE,"/user/{username}");

             String responseBody = response.getBody().asString();
             System.out.println("responseBody: "+responseBody);

             int statusCode= response.getStatusCode();
             System.out.println("statusCode: "+statusCode);
             Assert.assertEquals(statusCode, 200);

             int code = response.jsonPath().get("code");
             Assert.assertEquals(code, 200);

    }
    @Test(description = "Verify the user is deleted", priority = 6)
    public void veriyUserDeleted() throws InterruptedException
    {
            //request object
            RequestSpecification request = RestAssured.given().pathParam("username", "RanulDUpdated");

             //response object
             Response response = request.request(Method.GET,"/user/{username}");

             String responseBody = response.getBody().asString();
             System.out.println("responseBody: "+responseBody);

             int statusCode= response.getStatusCode();
             System.out.println("statusCode: "+statusCode);
             Assert.assertEquals(statusCode, 404);

             String usernameResponse = response.jsonPath().get("message");
             Assert.assertEquals(usernameResponse, "User not found");

    }
   
    
}
